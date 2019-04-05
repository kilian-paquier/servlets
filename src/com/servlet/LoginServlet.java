package com.servlet;

import com.manager.Manager;
import com.model.Admin;
import com.model.Candidate;
import com.model.Voter;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (!login.equals("") && !password.equals("")) {
            password = DigestUtils.sha256Hex(password);
            String type = "";
            boolean isFound = false;
            Admin admin;
            Candidate candidate;
            Voter voter = Manager.getVoterDao().findByLoginAndPassword(login, password);

            if (voter == null) {
                candidate = Manager.getCandidateDao().findByLoginAndPassword(login, password);
                if (candidate == null) {
                    admin = Manager.getAdminDao().findByLoginAndPassword(login, password);
                    if (admin != null) {
                        isFound = true;
                        type = "admin";
                    }
                } else {
                    isFound = true;
                    type = "candidat";
                }
            } else {
                isFound = true;
                type = "votant";
            }

            if (isFound) {
                HttpSession session = request.getSession();
                session.setAttribute("type", type);
                session.setAttribute("login", login);
                session.setAttribute("password", password);

                List<Candidate> candidateList = Manager.getCandidateDao().findAll();
                if (candidateList == null)
                    candidateList = new ArrayList<>();

                request.setAttribute("candidateList", candidateList);

                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Login ou mot de passe incorrect");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("message", "L'un des champs de connexion est vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString();
        if (type != null) {
            session.invalidate();
            request.setAttribute("registerSuccess", "Déconnexion réussit");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
