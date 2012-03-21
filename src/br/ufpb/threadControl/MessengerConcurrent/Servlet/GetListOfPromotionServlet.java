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
 * Servlet List Of Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/listofpromotion" })
public class GetListOfPromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BlockingQueue<BlockingQueue<Promotion>> listTake = new LinkedBlockingQueue<BlockingQueue<Promotion>>();
		BlockingQueue<Promotion> listPromotion = null;

		Facade.getInstance().getListPromotion(listTake);

		try {
			listPromotion = listTake.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("ListPromotion", listPromotion);
		request.getRequestDispatcher("/showlistofpromotion.jsp").forward(
				request, response);
	}

}
