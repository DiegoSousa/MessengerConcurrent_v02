package br.ufpb.threadControl.MessengerConcurrent.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Servlet Edit Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/editclientform" })
public class EditClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));

		Facade facade = Facade.getInstance();

		Client client = new Client(name, phone, mail, day, month, year);

		facade.editClient(client);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}
}
