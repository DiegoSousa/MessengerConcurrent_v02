package br.ufpb.threadControl.messengerConcurrent.manager;

import java.util.List;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 *
 * Copyright (C) 2012 
 */
public interface ICustomerManager {

	/**
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	public abstract Customer addCustomer(Customer customer) throws Exception;

	/**
	 * 
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	public abstract Customer removeCustomer(Customer customer) throws Exception;

	/**
	 * 
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	public abstract Customer editCustomer(Customer customer) throws Exception;

	/**
	 * 
	 * @param id
	 *            belonging to the customer that will be searched
	 * @throws Exception
	 * @return Object customer
	 */

	public abstract Customer findCustomerById(long id) throws Exception;

	/**
	 * 
	 * @param login
	 *            belonging to the customer that will be searched
	 * @throws Exception
	 * @return Object customer
	 */

	public abstract Customer searchCustomerByLogin(String login)
			throws Exception;

	/**
	 * @throws Exception
	 * @return List of all customer
	 */
	
	public abstract List<Customer> getListOfCustomers() throws Exception;

}