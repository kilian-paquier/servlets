package com.servlet;

import com.manager.Manager;
import com.model.Candidate;
import com.model.Party;
import com.model.Result;
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

public class CandidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Candidate");
        switch (option) {
            case "add":
            case "modifying":
                addCandidate(request, response);
                break;
            case "modify":
                modifyCandidate(request, response);
                break;
            case "delete":
                deleteCandidate(request, response);
                break;
            default: {
                request.setAttribute("errorMessage", "Le paramètre d'ajout/modification/suppression a été modifié");
                RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidate> candidateList = Manager.getCandidateDao().findAll();
        if (candidateList == null)
            candidateList = new ArrayList<>();
        request.setAttribute("candidateList", candidateList);

        List<Party> partyList = Manager.getPartyDao().findAll();
        if (partyList == null)
            partyList = new ArrayList<>();
        request.setAttribute("partyList", partyList);

        String option = request.getParameter("Candidate");


        if (option.equals("modifying")) {
            request.setAttribute("option", "modifying");
            modifyCandidate(request, response);
            return;
        }

        request.setAttribute("option", "add");
        RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
        dispatcher.forward(request, response);
    }

    private void addCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Candidate");
        String oldId = request.getParameter("id_candidat");
        String lastName = request.getParameter("nom");
        String firstName = request.getParameter("prenom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String city = request.getParameter("ville");
        String partyName = request.getParameter("parti");
        String birthDate = request.getParameter("naissance");

        if (lastName.equals("") || firstName.equals("") || login.equals("") || (option.equals("add") && (password == null || password.equals(""))) || city.equals("") || partyName == null || birthDate.equals("")) {
            request.setAttribute("errorMessage", "L'un des champs d'enregistrement est vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Candidate candidate;

        if (option.equals("add")) {
            candidate = new Candidate();
            candidate.setResult(new Result(0));
            password = DigestUtils.sha256Hex(password);
            candidate.setPassword(password);
        } else {
            candidate = Manager.getCandidateDao().findById(Integer.valueOf(oldId));
        }

        candidate.setLastName(lastName);
        candidate.setFirstName(firstName);
        candidate.setLogin(login);
        candidate.setCity(city);

        {
            int year = Integer.parseInt(birthDate.split("-")[0]);
            int month = Integer.parseInt(birthDate.split("-")[1]);
            int day = Integer.parseInt(birthDate.split("-")[2]);
            candidate.setBirthDate(LocalDate.of(year, month, day));
        }

        Party party = Manager.getPartyDao().findByPartyName(partyName);
        candidate.setParty(party);

        boolean inserted = Manager.getCandidateDao().saveOrUpdate(candidate);

        List<Party> partyList = Manager.getPartyDao().findAll();
        if (partyList == null)
            partyList = new ArrayList<>();
        request.setAttribute("partyList", partyList);

        if (inserted && option.equals("add")) {
            request.setAttribute("successMessage", "L'enregistrement du compte candidat a réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
        } else if (inserted && option.equals("modifying")) {
            List<Candidate> candidateList = Manager.getCandidateDao().findAll();
            if (candidateList == null)
                candidateList = new ArrayList<>();
            request.setAttribute("candidateList", candidateList);

            request.setAttribute("successMessage", "La modification du compte candidat a réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyCandidate.jsp");
            dispatcher.forward(request, response);
        } else if (option.equals("add")) {
            request.setAttribute("errorMessage", "L'enregistrement du compte candidat n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "La modification du compte candidat n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addCandidate.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void modifyCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCandidate = request.getParameter("id_candidat");

        if (idCandidate == null) {
            List<Candidate> candidateList = Manager.getCandidateDao().findAll();
            if (candidateList == null)
                candidateList = new ArrayList<>();
            request.setAttribute("candidateList", candidateList);

            request.setAttribute("errorMessage", "Veuillez saisir le parti à modifier");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyCandidate.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Candidate candidate = Manager.getCandidateDao().findById(Integer.valueOf(idCandidate));
        request.setAttribute("candidate", candidate);

        RequestDispatcher dispatcher = request.getRequestDispatcher("addCandidate.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Candidate");
        String idCandidate = request.getParameter("id_candidat");

        if (idCandidate == null) {
            List<Candidate> candidateList = Manager.getCandidateDao().findAll();
            if (candidateList == null)
                candidateList = new ArrayList<>();
            request.setAttribute("candidateList", candidateList);

            request.setAttribute("errorMessage", "Veuillez saisir le parti à modifier");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
            return;
        }

        boolean deleted = Manager.getCandidateDao().delete(Manager.getCandidateDao().findById(Integer.valueOf(idCandidate)));

        List<Candidate> candidateList = Manager.getCandidateDao().findAll();
        if (candidateList == null)
            candidateList = new ArrayList<>();
        request.setAttribute("candidateList", candidateList);

        if (deleted) {
            request.setAttribute("successMessage", "Le candidat a bien été supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Le candidat n'a pas pu être supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Candidate.jsp");
            dispatcher.forward(request, response);
        }
    }
}
