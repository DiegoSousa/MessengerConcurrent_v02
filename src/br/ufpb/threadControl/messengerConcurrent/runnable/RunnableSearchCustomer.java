/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;

/**
 * Runnable Search customer
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchCustomer implements Runnable {
	private ICustomerManager customerCrud;
	private String login;
	private BlockingQueue<Customer> list;

	public RunnableSearchCustomer(String login, BlockingQueue<Customer> list, ICustomerManager iCustomerManager) {
		this.customerCrud = iCustomerManager;
		this.login = login;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(customerCrud.searchCustomerByLogin(login));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
