package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Purchase;
import br.ufpb.threadControl.messengerConcurrent.manager.IPurchaseManager;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 * 
 *        Copyright (C) 2012
 */
public class RunnableEditPurchase implements Runnable {

	private IPurchaseManager purchaseCrud;
	private Purchase purchase;

	public RunnableEditPurchase(Purchase purchase, IPurchaseManager iPurchaseManager) {
		this.purchaseCrud = iPurchaseManager;
		this.purchase = purchase;
	}

	@Override
	public void run() {
		try {
			purchaseCrud.editPurchase(purchase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
