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
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;

/**
 * Servlet search Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet("/searchpromotion")
public class SearchPromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Promotion promotion = null;
		BlockingQueue<Promotion> listTake = new LinkedBlockingQueue<Promotion>();
		double code = Double.parseDouble(request.getParameter("code"));

		Facade.getInstance().searchPromotion(code, listTake);

		try {
			promotion = listTake.take();
		} catch (InterruptedException e) {
		} catch (NullPointerException e) {
			e.getMessage();
		}

		request.setAttribute("Object", promotion);
		request.getRequestDispatcher("/printsearch.jsp").forward(request,
				response);

	}
}
