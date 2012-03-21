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
 * Servlet List Of Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@WebServlet(urlPatterns = { "/listofproduct" })
public class GetListOfProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Facade facade = Facade.getInstance();
		BlockingQueue<BlockingQueue<Product>> listTake = new LinkedBlockingQueue<BlockingQueue<Product>>();
		BlockingQueue<Product> listProduct = null;

		facade.getListProduct(listTake);

		try {
			listProduct = listTake.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		request.setAttribute("ListProduct", listProduct);
		request.getRequestDispatcher("/showlistofproduct.jsp").forward(request,
				response);
	}
}
