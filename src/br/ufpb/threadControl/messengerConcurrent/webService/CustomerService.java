package br.ufpb.threadControl.messengerConcurrent.webService;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.ufpb.threadControl.messengerConcurrent.controller.Facade;
import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.managerFactory.ManagerDAOFactoryJPA;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 * 
 *        Copyright (C) 2012
 */

@Path(value = "/customer")
public class CustomerService {

	Facade facade = Facade.getInstance(new ManagerDAOFactoryJPA());

	@POST
	@Path("/addcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCustomer(Customer customer) {
		facade.addCustomer(customer);
	}

	@DELETE
	@Path("/removercustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removerCustomer(Customer customer) {
		facade.removeCustomer(customer);
	}

	@PUT
	@Path("/editcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void editCustomer(Customer customer) {
		facade.editCustomer(customer);
	}

	@GET
	@Path("/searchcustomerbylogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer searchcustomerbylogin(String login) {
		BlockingQueue<Customer> listCustomer = new LinkedBlockingQueue<Customer>();
		Customer customerAux = null;

		try {
			JSONObject jsonObject = new JSONObject(login);
			facade.searchCustomerByLogin(jsonObject.getString("login"), listCustomer);
			customerAux = listCustomer.take();
		} catch (JSONException je) {
			je.printStackTrace(); 
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		return customerAux;
	}

	@GET
	@Path("/searchcustomerbyid")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer searchcustomerbyid(String id) {
		BlockingQueue<Customer> listCustomer = new LinkedBlockingQueue<Customer>();
		Customer customerAux = null;

		try {
			JSONObject jsonObject = new JSONObject(id);
			facade.searchCustomerById(jsonObject.getLong("id"), listCustomer);
			customerAux = listCustomer.take();
		} catch (JSONException je) {
			je.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		return customerAux;
	}

	@GET
	@Path("/getlistofcustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getListOfCustomers() {
		BlockingQueue<List<Customer>> listCustomer = new LinkedBlockingQueue<List<Customer>>();		

		facade.getListOfCustomer(listCustomer);

		List<Customer> listAux = null;
		try {
			listAux = listCustomer.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return listAux;
	}

}
