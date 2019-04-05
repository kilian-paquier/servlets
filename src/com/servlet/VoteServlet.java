package com.servlet;

import com.manager.Manager;
import com.model.Candidate;
import com.model.Voter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean hasVoted;

        if (type.equals("candidat"))
        {
            Candidate candidate = Manager.getCandidateDao().findByLoginAndPassword(login,password);
            hasVoted = candidate.hasVoted();
            if (hasVoted)
            {
                request.setAttribute("message", "Vous avez déjà voté");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
                dispatcher.forward(request, response);
            }
        }
        else {
            Voter voter = Manager.getVoterDao().findByLoginAndPassword(login,password);
            hasVoted = voter.hasVoted();

            if (hasVoted)
            {
                request.setAttribute("message", "Vous avez déjà voté");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
