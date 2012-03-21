package br.ufpb.threadControl.MessengerConcurrent.Manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Client Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ManagerClient {

	private static ManagerClient clientManager;
	private ManagerProductPreferences productPreferencesManager;
	private ManagerPurchasesOfProducts productBuyManager;
	private BlockingQueue<Client> listClient;
	private Logger logger;
	
	private ManagerClient() {
		this.productPreferencesManager = ManagerProductPreferences.getInstance();
		this.productBuyManager = ManagerPurchasesOfProducts.getInstance();
		this.listClient = new LinkedBlockingQueue<Client>();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Model.ClientManager");		
	}

	/*
	 * Singleton
	 */

	public static synchronized ManagerClient getInstance() {
		if (clientManager == null) {
			clientManager = new ManagerClient();
		}
		return clientManager;
	}

	public synchronized Client addClient(Client client) {
		if (client != null) {
			try {
				listClient.put(client);
				logger.info("Client: " + client.getName()
						+ " successfully added!");
			} catch (InterruptedException e) {
				e.getMessage();
			} catch (NullPointerException e) {
				e.getMessage();
			}
			return client;
		} else {
			return null;
		}
	}

	public synchronized Client removeClient(Client client) {

		if (client != null) {
			listClient.remove(client);
			productPreferencesManager.removeAllPreferencesClient(client);
			productBuyManager.removeHistoricalOfProductsPurchasedOfCustomer(client);
			logger.info(client.getMail() + " successfully removed!");
			return client;
		} else {
			return null;
		}
	}

	public synchronized  Client editClient(Client client) {
		if (client != null) {
			for (Client clientAux : listClient) {

				if (clientAux.getMail().equals(client.getMail())) {
					listClient.remove(clientAux);					
					try {
						listClient.put(client);						
					} catch (InterruptedException e) {
						e.getMessage();
					} catch (NullPointerException e) {
						e.getMessage();
					}
					logger.info("Client: " + client.getName() + " atualizado!");
					return clientAux;
				}
			}
		}
		return null;
	}

	public synchronized Client searchClient(String mail) {

		for (Client client : listClient) {
			if (client.getMail().equals(mail)) {
				logger.info("Client: " + client.getName() + " found!");
				return client;
			}
		}
		logger.info("Client not found!");
		return null;
	}

	public synchronized BlockingQueue<Client> getListOfClient() {
		return listClient;
	}
}
