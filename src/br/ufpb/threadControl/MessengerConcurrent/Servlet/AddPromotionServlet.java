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
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;

/**
 * Servlet Add Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/addpromotionform" })
public class AddPromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Double codeProduct = Double.parseDouble(request
				.getParameter("codeProduct"));
		int discountedPrice = Integer.parseInt(request
				.getParameter("discountedPrice"));
		int promotionCode = Integer.parseInt(request
				.getParameter("promotionCode"));

		Product product = null;
		BlockingQueue<Product> products = new LinkedBlockingQueue<Product>();
		Facade.getInstance().searchProduct(codeProduct, products);

		try {
			product = products.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Promotion promotion = new Promotion(product, discountedPrice,
				promotionCode);

		Facade facade = Facade.getInstance();
		facade.addPromotion(promotion);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}

}
