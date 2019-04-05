package com.servlet;

import com.manager.Manager;
import com.model.Voter;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class VoterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Voter");
        switch (option) {
            case "add":
            case "modifying":
                addVoter(request, response);
                break;
            case "modify":
                modifyVoter(request, response);
                break;
            case "delete":
                deleteVoter(request, response);
                break;
            default: {
                request.setAttribute("errorMessage", "Le paramètre d'ajout/modification/suppression a été modifié");
                RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    private void deleteVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Voter");
        String idVotant = request.getParameter("id_votant");

        if (idVotant == null) {
            List<Voter> voters = Manager.getVoterDao().findAll();
            if (voters == null)
                voters = new ArrayList<>();
            request.setAttribute("voterList", voters);

            request.setAttribute("errorMessage", "Veuillez saisir le votant à modifier");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
            dispatcher.forward(request, response);
            return;
        }

        boolean deleted = Manager.getVoterDao().delete(Manager.getVoterDao().findById(Integer.valueOf(idVotant)));

        List<Voter> voters = Manager.getVoterDao().findAll();
        if (voters == null)
            voters = new ArrayList<>();
        request.setAttribute("voterList", voters);

        if (deleted) {
            request.setAttribute("successMessage", "Le votant a bien été supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Le votant n'a pas pu être supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void modifyVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idVotant = request.getParameter("id_votant");

        if (idVotant == null) {
            List<Voter> voters = Manager.getVoterDao().findAll();
            if (voters == null)
                voters = new ArrayList<>();
            request.setAttribute("voterList", voters);

            request.setAttribute("errorMessage", "Veuillez saisir le votant à modifier");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyVoter.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Voter voter = Manager.getVoterDao().findById(Integer.valueOf(idVotant));
        request.setAttribute("voterAccount", voter);

        RequestDispatcher dispatcher = request.getRequestDispatcher("addVoter.jsp");
        dispatcher.forward(request, response);
    }

    private void addVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Voter");
        String oldId = request.getParameter("id_votant");
        String lastName = request.getParameter("nom");
        String firstName = request.getParameter("prenom");
        String login;
        String password = request.getParameter("password");
        String city = request.getParameter("ville");
        String birthDate = request.getParameter("naissance");

        if (option.equals("modifying")) {
            login = request.getParameter("loginModify");
            if (lastName.equals("") || firstName.equals("") || login.equals("") || city.equals("") || birthDate.equals("")) {
                request.setAttribute("errorMessage", "L'un des champs d'enregistrement est vide");
                RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        else {
            login = request.getParameter("loginAdd");
            if (lastName.equals("") || firstName.equals("") || login.equals("") || (password == null || password.equals("")) || city.equals("") || birthDate.equals("")) {
                request.setAttribute("errorMessage", "L'un des champs d'enregistrement est vide");
                RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }


        Voter voter;

        if (option.equals("add")) {
            voter = new Voter();
            password = DigestUtils.sha256Hex(password);
            voter.setPassword(password);
        } else {
            voter = Manager.getVoterDao().findById(Integer.valueOf(oldId));
        }

        voter.setLastName(lastName);
        voter.setFirstName(firstName);
        voter.setLogin(login);
        voter.setCity(city);

        {
            int year = Integer.parseInt(birthDate.split("-")[0]);
            int month = Integer.parseInt(birthDate.split("-")[1]);
            int day = Integer.parseInt(birthDate.split("-")[2]);
            voter.setBirthDate(LocalDate.of(year, month, day));
        }

        boolean inserted = Manager.getVoterDao().saveOrUpdate(voter);

        List<Voter> voters = Manager.getVoterDao().findAll();
        if (voters == null)
            voters = new ArrayList<>();
        request.setAttribute("voterList", voters);

        if (inserted && option.equals("add")) {
            request.setAttribute("option", "add");
            request.setAttribute("successMessage", "L'enregistrement du compte votant a réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
            dispatcher.forward(request, response);
        } else if (inserted && option.equals("modifying")) {
            request.setAttribute("successMessage", "La modification du compte votant a réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyVoter.jsp");
            dispatcher.forward(request, response);
        } else if (option.equals("add")) {
            request.setAttribute("option", "add");
            request.setAttribute("errorMessage", "L'enregistrement du compte votant n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "La modification du compte votant n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyVoter.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Voter");

        List<Voter> voters = Manager.getVoterDao().findAll();
        if (voters == null)
            voters = new ArrayList<>();
        request.setAttribute("voterList", voters);

        if (option.equals("modifying")) {
            request.setAttribute("option", "modifying");
            modifyVoter(request, response);
            return;
        }

        request.setAttribute("option", "add");
        RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Voter.jsp");
        dispatcher.forward(request, response);
    }
}
