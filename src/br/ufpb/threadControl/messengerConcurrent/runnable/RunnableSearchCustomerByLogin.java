package br.ufpb.threadControl.messengerConcurrent.runnable;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;

/**
 * Runnable Search Customer By Login.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchCustomerByLogin implements Runnable {

	private String login;
	private BlockingQueue<Customer> list;
	private ICustomerManager iCustomerManager;

	public RunnableSearchCustomerByLogin(String login,
			BlockingQueue<Customer> list, ICustomerManager iCustomerManager) {
		this.login = login;
		this.list = list;
		this.iCustomerManager = iCustomerManager;
	}

	@Override
	public void run() {
		try {
			list.put(iCustomerManager.searchCustomerByLogin(login));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
