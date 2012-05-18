package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 *
 * Copyright (C) 2012 
 */
public class CustomerDAOList implements ICustomerManager{

	@Override
	public Customer addCustomer(Customer customer) throws Exception {
		return null;
	}

	@Override
	public Customer removeCustomer(Customer customer) throws Exception {
		return null;
	}

	@Override
	public Customer editCustomer(Customer customer) throws Exception {
		return null;
	}

	@Override
	public Customer findCustomerById(long id) throws Exception {
		return null;
	}

	@Override
	public Customer searchCustomerByLogin(String login) throws Exception {
		return null;
	}

	@Override
	public List<Customer> getListOfCustomers() throws Exception {
		return null;
	}

}
