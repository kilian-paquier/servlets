package com.servlet;

import com.dao.CandidateUserDao;
import com.manager.Manager;
import com.model.Candidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String birthDay = request.getParameter("birthDay");

        //Regarde si les champs sont vides
        if (login.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || city.isEmpty() || birthDay.isEmpty())
        {
            System.out.println("Champ vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }

        //Cherche l'utilisateur
        boolean isCreate = true;
        if (isCreate)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
        }
        else
        {
            System.out.println("PB BDD");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidate> candidateList = Manager.getCandidateDao().findAll();
        if (candidateList == null)
            candidateList = new ArrayList<>();

        request.setAttribute("candidateList", candidateList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
