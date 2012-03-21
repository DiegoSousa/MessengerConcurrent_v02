/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnable;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerClient;

/**
 * Runnable adder client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableAddClient implements Runnable {
	private ManagerClient clientManager;
	private Client client;

	public RunnableAddClient(ManagerClient manager, Client client) {
		this.clientManager = manager;
		this.client = client;
	}

	@Override
	public void run() {
		this.clientManager.addClient(client);
	}
}