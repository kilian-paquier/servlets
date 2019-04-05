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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCandidat = request.getParameter("id_candidat");

        if (idCandidat.equals("")) {
            request.setAttribute("errorMessage", "Veuillez saisir le candidat pour lequel vosu voulez voter");
            RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Candidate candidate = Manager.getCandidateDao().findById(Integer.valueOf(idCandidat));
        candidate.incrementVote();
        boolean voteCommitted = Manager.getCandidateDao().saveOrUpdate(candidate);

        if (voteCommitted) {
            request.setAttribute("successMessage", "Votre vote pour " + candidate.toString() + " a bien été pris en compte");
            RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Votre vote pour " + candidate.toString() + " n'a pas pu être pris en compte");
            RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString();
        String login = session.getAttribute("login").toString();
        String password = session.getAttribute("password").toString();
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
                List<Candidate> candidateList = Manager.getCandidateDao().findAll();
                if (candidateList == null)
                    candidateList = new ArrayList<>();
                request.setAttribute("candidateList", candidateList);
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
                List<Candidate> candidateList = Manager.getCandidateDao().findAll();
                if (candidateList == null)
                    candidateList = new ArrayList<>();
                request.setAttribute("candidateList", candidateList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
