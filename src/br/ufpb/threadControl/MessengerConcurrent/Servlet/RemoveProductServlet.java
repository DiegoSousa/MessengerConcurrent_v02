package br.ufpb.threadControl.MessengerConcurrent.Servlet;

/**
 * Servlet Remove Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

@WebServlet(urlPatterns = { "/removeproduct" })
public class RemoveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Facade facade = Facade.getInstance();
		Product product = null;
		BlockingQueue<Product> listTake = new LinkedBlockingQueue<Product>();
		double code = Double.parseDouble(request.getParameter("code"));

		facade.searchProduct(code, listTake);

		try {
			product = listTake.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		facade.removeProduct(product);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);
	}
}
