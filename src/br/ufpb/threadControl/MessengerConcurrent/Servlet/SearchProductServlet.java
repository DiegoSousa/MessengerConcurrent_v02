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
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Servlet Search Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/searchproduct" })
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Product product = null;
		BlockingQueue<Product> listTake = new LinkedBlockingQueue<Product>();
		Double code = Double.parseDouble(request.getParameter("code"));

		Facade.getInstance().searchProduct(code, listTake);

		try {
			product = listTake.take();
		} catch (InterruptedException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}

		request.setAttribute("Object", product);
		request.getRequestDispatcher("/printsearch.jsp").forward(request,
				response);

	}
}
