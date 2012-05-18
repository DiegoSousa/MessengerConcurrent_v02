/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;

/**
 * Runnable Remove customer
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 
 * 
 * Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableRemoveCustomer implements Runnable {
	private ICustomerManager customerCrud;
	private Customer customer;

	public RunnableRemoveCustomer(Customer customer, ICustomerManager iCustomerManager) {
		customerCrud = iCustomerManager;
		this.customer = customer;
	}

	@Override
	public void run() {
		try {
			customerCrud.removeCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
