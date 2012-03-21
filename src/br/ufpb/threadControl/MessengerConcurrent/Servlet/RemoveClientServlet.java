package br.ufpb.threadControl.MessengerConcurrent.Servlet;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Servlet Remove Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/removeclient" })
public class RemoveClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Facade facade = Facade.getInstance();
		Client client = null;
		BlockingQueue<Client> listTake = new LinkedBlockingQueue<Client>();
		String mail = request.getParameter("mail");

		facade.searchClient(mail, listTake);

		try {
			client = listTake.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		facade.removeClient(client);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}
}
