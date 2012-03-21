package br.ufpb.threadControl.MessengerConcurrent.Manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Manager products preferred of Customer.
 * 	
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ManagerProductPreferences {

	private static ManagerProductPreferences productPreferencesManager;
	private Map<Client, List<Product>> mapOfAllPreferences;
	private Logger logger;

	private ManagerProductPreferences() {
		this.mapOfAllPreferences = Collections
				.synchronizedMap(new HashMap<Client, List<Product>>());
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Controller.ProductPreferredManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ManagerProductPreferences getInstance() {
		if (productPreferencesManager == null) {
			productPreferencesManager = new ManagerProductPreferences();
		}
		return productPreferencesManager;
	}

	public synchronized List<Product> addPreferencesClient(Client client,
			Product product) {
		if (client != null && product!=null && mapOfAllPreferences.get(client) != null) {//if there is already a list
			
			List<Product> mapAux = mapOfAllPreferences.get(client);
			mapAux.add(product);
			mapOfAllPreferences.put(client, mapAux);
			logger.info("Client preferences: " + client.getName()
					+ " added or updated successfully");
			return mapOfAllPreferences.get(client);
		}else if(client != null && product!=null && mapOfAllPreferences.get(client) == null){//if the list does not exist
			List<Product> listAux = new LinkedList<Product>();
			listAux.add(product);
			mapOfAllPreferences.put(client, listAux);
			return mapOfAllPreferences.get(client);
			}
		return null;			
		} 	

	/*
	 * Remove the complete list of preferences of a customer
	 */
	
	public synchronized void removeAllPreferencesClient(Client client) {
		mapOfAllPreferences.remove(client);
		logger.info("Client preferences: " + client.getName()
				+ " removed successfully");
	}

	public synchronized List<Product> getListOfPreferredProductsOfCustomers(Client client) {
		return mapOfAllPreferences.get(client);
	}

	public synchronized Map<Client, List<Product>> getListOfPreferredProductsOfAllCustomers() {
		return mapOfAllPreferences;
	}
}