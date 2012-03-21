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
 * Servlet List Of Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/listofclient" })
public class GetListOfClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BlockingQueue<BlockingQueue<Client>> listTake = new LinkedBlockingQueue<BlockingQueue<Client>>();
		BlockingQueue<Client> listClient = null;

		Facade.getInstance().getListOfClient(listTake);

		try {
			listClient = listTake.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("ListClient", listClient);
		request.getRequestDispatcher("/showlistofclient.jsp").forward(request, response);		
	}
}
