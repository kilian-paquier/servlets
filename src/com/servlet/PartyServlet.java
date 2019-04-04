package com.servlet;

import com.manager.Manager;
import com.model.Party;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Party");
        switch (option) {
            case "add":
            case "addModify":
                addParty(request, response);
                break;
            case "modify":
                modifyParty(request, response);
                break;
            case "delete":
                deleteParty(request, response);
                break;
            default: {
                request.setAttribute("errorMessage", "Le paramètre d'ajout/modification/suppression a été modifié");
                RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Party.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Party> partyList = Manager.getPartyDao().findAll();
        if (partyList == null)
            partyList = new ArrayList<>();
        request.setAttribute("partyList", partyList);

        String option = request.getParameter("Party");

        if (option.equals("addModify")) {
            modifyParty(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Party.jsp");
        dispatcher.forward(request, response);
    }

    private void addParty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Party");
        String partyName = request.getParameter("nom");
        String headquarters = request.getParameter("ville");
        String oldPartyName = request.getParameter("parti_name");

        if (partyName.equals("") || headquarters.equals("")) {
            request.setAttribute("errorMessage", "L'un des champs pour le parti n'a pas été saisi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Party party;
        if (option.equals("add")) {
            party = new Party();
            party.setHeadquarters(headquarters);
            party.setPartyName(partyName);
        } else {
            party = Manager.getPartyDao().findByPartyName(oldPartyName);
            party.setHeadquarters(headquarters);
            party.setPartyName(partyName);
        }

        boolean created = Manager.getPartyDao().saveOrUpdate(party);

        if (created && option.equals("add")) {
            request.setAttribute("successMessage", "Le parti a bien été créé");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
            dispatcher.forward(request, response);
        } else if (created && option.equals("addModify")) {
            request.setAttribute("successMessage", "Le parti a bien été modifié");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
            dispatcher.forward(request, response);
        } else if (option.equals("add")) {
            request.setAttribute("errorMessage", "Le parti n'a pas pu être ajouté à la base de données");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Le parti n'a pas pu être modifié dans la base de données");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void modifyParty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String partyName = request.getParameter("parti_name");

        if (partyName == null) {

            List<Party> partyList = Manager.getPartyDao().findAll();
            if (partyList == null)
                partyList = new ArrayList<>();
            request.setAttribute("partyList", partyList);

            request.setAttribute("errorMessage", "Veuillez saisir le parti à modifier");
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyParty.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Party party = Manager.getPartyDao().findByPartyName(partyName);
        request.setAttribute("nom", party.getPartyName());
        request.setAttribute("ville", party.getHeadquarters());
        RequestDispatcher dispatcher = request.getRequestDispatcher("addParty.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteParty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("Party");
        String partyName = request.getParameter("parti_name");

        if (partyName == null) {
            request.setAttribute("errorMessage", "Veuillez saisir le parti à supprimer");

            List<Party> partyList = Manager.getPartyDao().findAll();
            if (partyList == null)
                partyList = new ArrayList<>();
            request.setAttribute("partyList", partyList);

            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Party.jsp");
            dispatcher.forward(request, response);
            return;
        }

        boolean deleted = Manager.getPartyDao().delete(Manager.getPartyDao().findByPartyName(partyName));

        List<Party> partyList = Manager.getPartyDao().findAll();
        if (partyList == null)
            partyList = new ArrayList<>();
        request.setAttribute("partyList", partyList);

        if (deleted) {
            request.setAttribute("successMessage", "Le parti a bien été supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Party.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Le parti n'a pas pu être supprimé");
            RequestDispatcher dispatcher = request.getRequestDispatcher(option + "Party.jsp");
            dispatcher.forward(request, response);
        }
    }
}
