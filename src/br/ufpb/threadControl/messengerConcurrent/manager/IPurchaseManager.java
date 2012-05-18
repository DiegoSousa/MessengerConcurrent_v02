package br.ufpb.threadControl.messengerConcurrent.manager;

import java.util.List;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.entity.Purchase;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 * 
 *        Copyright (C) 2012
 */
public interface IPurchaseManager {

	public abstract Purchase purchaseProduct(Purchase purchase)
			throws Exception;

	public abstract Purchase removePurchase(Purchase purchase) throws Exception;

	public abstract Purchase editPurchase(Purchase purchase) throws Exception;

	public abstract Purchase searchPurchase(Product product, Customer customer)
			throws Exception;

	public abstract List<Purchase> getListPurchase() throws Exception;

	public abstract List<Purchase> getPurchasesOfACustomer(Customer customer)
			throws Exception;

	public abstract List<Product> getListProductSold() throws Exception;

}