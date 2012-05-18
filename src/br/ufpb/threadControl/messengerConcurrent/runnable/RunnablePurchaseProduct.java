/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Purchase;
import br.ufpb.threadControl.messengerConcurrent.manager.IPurchaseManager;

/**
 * Runnable Product Purchase.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnablePurchaseProduct implements Runnable {

	private IPurchaseManager purchaseManager;
	private Purchase purchase;

	public RunnablePurchaseProduct(Purchase purchase, IPurchaseManager iPurchaseManager) {
		purchaseManager = iPurchaseManager;
		this.purchase = purchase;
	}

	public void run() {
		try {
			purchaseManager.purchaseProduct(purchase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}