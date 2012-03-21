package br.ufpb.threadControl.MessengerConcurrent.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Servlet Edit Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/editproductform" })
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		int code = Integer.parseInt(request.getParameter("code"));
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Product product = new Product(name, code, price, quantity);

		Facade facade = Facade.getInstance();
		facade.editProduct(product);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}
}