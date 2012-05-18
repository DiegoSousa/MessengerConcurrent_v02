package br.ufpb.threadControl.messengerConcurrent.runnable;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPurchaseManager;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 *
 * Copyright (C) 2012 
 */
public class RunnableGetListProductSold implements Runnable {

	private IPurchaseManager purchaseCrud;
	private BlockingQueue<List<Product>> list;

	public RunnableGetListProductSold(BlockingQueue<List<Product>> list, IPurchaseManager iPurchaseManager){
		this.purchaseCrud = iPurchaseManager;
		this.list = list;
	}
		
	@Override
	public void run() {
		try {
			list.put(purchaseCrud.getListProductSold());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
