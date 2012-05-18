/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;

/**
 * Runnable Edit Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableEditPromotion implements Runnable {
	private IPromotionManager promotionCrud;
	private Product product;

	public RunnableEditPromotion(Product product, IPromotionManager iPromotionManager) {
		this.promotionCrud = iPromotionManager;
		this.product = product;
	}

	@Override
	public void run() {
		try {
			this.promotionCrud.editPromotion(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}