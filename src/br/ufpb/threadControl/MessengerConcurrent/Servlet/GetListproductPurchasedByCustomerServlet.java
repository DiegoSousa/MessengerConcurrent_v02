package br.ufpb.threadControl.MessengerConcurrent.Servlet;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/productpurchasedbycustomer")
public class GetListproductPurchasedByCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter("mail");
		BlockingQueue<Client> listTakeClient = new LinkedBlockingQueue<Client>();
		BlockingQueue<List<Product>> listTakeProduct = new LinkedBlockingQueue<List<Product>>();
		List<Product> listproduct = null;
		Client client = null;
		Facade facade = Facade.getInstance();

		facade.searchClient(mail, listTakeClient);

		try {
			client = listTakeClient.take();
		} catch (InterruptedException e) {
		} catch (NullPointerException e) {
			e.getMessage();
		}

		facade.getHistoricalCustomerPurchase(client, listTakeProduct);

		try {
			listproduct = listTakeProduct.take();
		} catch (InterruptedException e) {
		} catch (NullPointerException e) {
			e.getMessage();
		}

		request.setAttribute("ListProduct", listproduct);
		request.setAttribute("Client", client);
		request.getRequestDispatcher("/showlistproductspurchasedbycustomer.jsp").forward(request,
				response);
	}

}
