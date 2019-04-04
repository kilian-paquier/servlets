package com.servlet;

import com.manager.Manager;
import com.model.Admin;
import com.model.Candidate;
import com.model.Voter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println(login + " " + password);

        //Regarde si les champs sont vides
        if (login.equals("") || password.equals(""))
        {
            request.setAttribute("message", "L'un des champs de connexion est vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        boolean isFound = false;
        Admin admin = null;
        Candidate candidate = null;
        Voter voter = Manager.getVoterDao().findByLoginAndPassword(login, password);

        if (voter == null)
            candidate = Manager.getCandidateDao().findByLoginAndPassword(login, password);
        else
            isFound = true;

        if (candidate == null && !isFound)
            admin = Manager.getAdminDao().findByLoginAndPassword(login, password);
        else
            isFound = true;

        if (isFound)
        {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            System.out.println("j'ai le login : " + login);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("message", "Login ou mot de passe incorrect");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
