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
 * Servlet Remove Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet("/removepromotion")
public class RemovePromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int promotionCode = Integer.parseInt(request
				.getParameter("promotionCode"));

		BlockingQueue<Promotion> listPromotion = new LinkedBlockingQueue<Promotion>();
		Facade facade = Facade.getInstance();
		Promotion promotion = null;
		facade.searchPromotion(promotionCode, listPromotion);

		try {
			promotion = listPromotion.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		facade.removePromotion(promotion);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}

}
