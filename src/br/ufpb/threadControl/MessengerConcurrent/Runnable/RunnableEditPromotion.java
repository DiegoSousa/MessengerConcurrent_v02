/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnable;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerPromotion;

/**
 * Runnable Edit Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableEditPromotion implements Runnable {
	private ManagerPromotion promotionManager;
	private Promotion promotion;

	public RunnableEditPromotion(ManagerPromotion manager, Promotion promotion) {
		this.promotionManager = manager;
		this.promotion = promotion;
	}

	@Override
	public void run() {
		this.promotionManager.editPromotion(promotion);
	}
}