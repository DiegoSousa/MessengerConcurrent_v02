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
 * Servlet Add Procuct
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/addproductform" })
public class AddProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		double code = Double.parseDouble(request.getParameter("code"));
		String price = request.getParameter("price");
		price = price.replaceAll(",", ".");
		double priceAux = Double.parseDouble(price);
		double quantity = Double.parseDouble(request.getParameter("quantity"));				
		
		Product product = new Product(name, code, priceAux, quantity);

		Facade facade = Facade.getInstance();
		facade.addProduct(product);

		request.getRequestDispatcher("/printmessage.jsp").forward(request, response);

	}

}
