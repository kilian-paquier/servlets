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


public class VoterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String votant = request.getParameter("votant");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("prenom");
        String lastName = request.getParameter("nom");
        String city = request.getParameter("ville");
        String birthDay = request.getParameter("naissance");
        String information = request.getParameter("modify");


        //modification
        if (votant != null)
        {
            Voter voter = Manager.getVoterDao().findById(1);
            request.setAttribute("voterAccount",voter);
            request.setAttribute("information","modify");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addVoter.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!information.equals(""))
        {
            Voter voter = new Voter();
            voter.setCity(city);
            voter.setFirstName(firstName);
            voter.setLastName(lastName);
            {
                int year = Integer.parseInt(birthDay.split("-")[0]);
                int month = Integer.parseInt(birthDay.split("-")[1]);
                int day = Integer.parseInt(birthDay.split("-")[2]);
                voter.setBirthDate(LocalDate.of(year, month, day));
            }

            boolean isModified = Manager.getVoterDao().saveOrUpdate(voter);

            if (isModified)
            {

            }
            else
            {

            }

        }

        //ajout
        if (login.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || city.equals("") || birthDay.equals(""))
        {
            request.setAttribute("message", "L'un des champs de l'enregistrement est vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addVoter.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Voter voter = new Voter();
        voter.setCity(city);
        voter.setFirstName(firstName);
        voter.setLastName(lastName);
        voter.setLogin(login);
        voter.setPassword(DigestUtils.sha256Hex(password));
        voter.setVote(false);

        {
            int year = Integer.parseInt(birthDay.split("-")[0]);
            int month = Integer.parseInt(birthDay.split("-")[1]);
            int day = Integer.parseInt(birthDay.split("-")[2]);
            voter.setBirthDate(LocalDate.of(year, month, day));
        }

        boolean isCreate = Manager.getVoterDao().saveOrUpdate(voter);

        if (isCreate)
        {
            request.setAttribute("registerSuccess", "Inscription réussie, veuillez vous connecter");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addVoter.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("message", "L'enregistrement de votre compte voteur n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addVoter.jsp");
            dispatcher.forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Voter");
        RequestDispatcher dispatcher = request.getRequestDispatcher(option+"Voter.jsp");
        dispatcher.forward(request, response);
    }
}
