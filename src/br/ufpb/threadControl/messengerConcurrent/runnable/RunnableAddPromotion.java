/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;

/**
 * Runnable adder Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableAddPromotion implements Runnable {
	private IPromotionManager promotionManager;
	private Product product;
	private int quantityProduct;
	private double promotionalPrice;

	public RunnableAddPromotion(Product product, int quantityProduct,
			double promotionalPrice, IPromotionManager iPromotionManager) {
		this.promotionManager = iPromotionManager;
		this.product = product;
		this.quantityProduct = quantityProduct;
		this.promotionalPrice = promotionalPrice;
	}

	@Override
	public void run() {
		try {
			this.promotionManager.addPromotion(product, quantityProduct,
					promotionalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
