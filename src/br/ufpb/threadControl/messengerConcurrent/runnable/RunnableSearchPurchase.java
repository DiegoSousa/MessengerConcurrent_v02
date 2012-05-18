package br.ufpb.threadControl.messengerConcurrent.runnable;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.entity.Product;
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
public class RunnableSearchPurchase implements Runnable {

	private IPurchaseManager purchaseCrud;
	private Product product;
	private Customer customer;
	private BlockingQueue<Purchase> list;

	public RunnableSearchPurchase(Product product, Customer customer,
			BlockingQueue<Purchase> list, IPurchaseManager iPurchaseManager) {
		purchaseCrud = iPurchaseManager;
		this.product = product;
		this.customer = customer;
		this.list = list;

	}

	public void run() {
		try {
			list.put(purchaseCrud.searchPurchase(product, customer));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
