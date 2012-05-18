/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;

/**
 * Runnable Remove Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableRemovePromotion implements Runnable {
	private IPromotionManager promotionManager;
	private Product product;

	public RunnableRemovePromotion(Product product, IPromotionManager iPromotionManager) {
		this.promotionManager = iPromotionManager;
		this.product = product;
	}

	@Override
	public void run() {
		try {
			promotionManager.removePromotion(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
