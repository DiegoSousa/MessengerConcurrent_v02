/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnable;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerClient;

/**
 * Runnable Search Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchClient implements Runnable {
	private ManagerClient clientManager;
	private String mail;
	private BlockingQueue<Client> list;

	public RunnableSearchClient(ManagerClient manager, String mail,
			BlockingQueue<Client> list) {
		this.clientManager = manager;
		this.mail = mail;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(clientManager.searchClient(mail));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
