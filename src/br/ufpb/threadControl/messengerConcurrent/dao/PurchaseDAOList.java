package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;

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
 * Copyright (C) 2012 
 */
public class PurchaseDAOList implements IPurchaseManager {

	@Override
	public Purchase purchaseProduct(Purchase purchase) throws Exception {
		return null;
	}

	@Override
	public Purchase removePurchase(Purchase purchase) throws Exception {
		return null;
	}

	@Override
	public Purchase editPurchase(Purchase purchase) throws Exception {
		return null;
	}

	@Override
	public Purchase searchPurchase(Product product, Customer customer)
			throws Exception {
		return null;
	}

	@Override
	public List<Purchase> getListPurchase() throws Exception {
		return null;
	}

	@Override
	public List<Purchase> getPurchasesOfACustomer(Customer customer)
			throws Exception {
		return null;
	}

	@Override
	public List<Product> getListProductSold() throws Exception {
		return null;
	}

}
