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
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

@WebServlet(urlPatterns = { "/buyproduct" })
public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Facade facade = Facade.getInstance();
		String mailClient = request.getParameter("mailClient");
		double codeProduct = Double.parseDouble(request.getParameter("codeProduct"));
		int quantityOfProducts = Integer.parseInt(request.getParameter("quantityOfProducts"));
		BlockingQueue<Client> listTakeClient = new LinkedBlockingQueue<Client>();
		BlockingQueue<Product> listTakeProduct = new LinkedBlockingQueue<Product>();
		Client client = null;
		Product product = null;
		facade.searchClient(mailClient, listTakeClient);

		try {
			client = listTakeClient.take();
		} catch (InterruptedException e) {
		} catch (NullPointerException e) {
			e.getMessage();
		}
		facade.searchProduct(codeProduct, listTakeProduct);
		try {
			product = listTakeProduct.take();
		} catch (InterruptedException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		facade.buyProduct(client, product, quantityOfProducts);		

		request.getRequestDispatcher("/printmessage.jsp").forward(request,
				response);
	}
}
