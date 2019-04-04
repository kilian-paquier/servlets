package com.servlet;

import com.manager.Manager;
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
import java.time.LocalDate;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("prenom");
        String lastName = request.getParameter("nom");
        String city = request.getParameter("ville");
        String birthDay = request.getParameter("naissance");

        //Regarde si les champs sont vides
        if (login.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || city.isEmpty() || birthDay.isEmpty())
        {
            request.setAttribute("message", "L'un des champs de l'enregistrement est vide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }

        Voter voter = new Voter();
        voter.setCity(city);
        voter.setFirstName(firstName);
        voter.setLastName(lastName);
        voter.setLogin(login);
        voter.setPassword(DigestUtils.sha256Hex(password));
        voter.setVote(false);

        System.out.println(birthDay);

        {
            int year = Integer.parseInt(birthDay.split("-")[0]);
            int month = Integer.parseInt(birthDay.split("-")[1]);
            int day = Integer.parseInt(birthDay.split("-")[2]);
            voter.setBirthDate(LocalDate.of(year, month, day));
        }

        boolean isCreate = Manager.getVoterDao().saveOrUpdate(voter);

        if (isCreate)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("message", "L'enregistrement de votre compte voteur n'a pas réussi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}
