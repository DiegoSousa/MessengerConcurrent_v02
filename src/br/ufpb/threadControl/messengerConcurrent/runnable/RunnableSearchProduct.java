/**
 * 
 */
package br.ufpb.threadControl.messengerConcurrent.runnable;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IProductManager;

/**
 * Runnable Search Product By name.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchProduct implements Runnable {
	private IProductManager productCrud;
	private String name;
	private BlockingQueue<Product> list;

	public RunnableSearchProduct(String name, BlockingQueue<Product> list, IProductManager iProductManager) {
		this.productCrud = iProductManager;
		this.name = name;
		this.list = list;
	}

	@Override
	public void run() {
		try {								
			list.put(productCrud.searchProductByName(name));			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
