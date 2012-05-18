package br.ufpb.threadControl.messengerConcurrent.controller;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.entity.Purchase;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;
import br.ufpb.threadControl.messengerConcurrent.manager.IProductManager;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;
import br.ufpb.threadControl.messengerConcurrent.manager.IPurchaseManager;
import br.ufpb.threadControl.messengerConcurrent.managerFactory.ManagerFactory;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableAddCustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableAddProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableAddPromotion;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableEditCustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableEditProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableEditPromotion;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableEditPurchase;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableFindCustomerById;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableFindProductById;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetListCustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetListProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetListProductSold;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetListPromotion;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetListPurchase;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableGetPurchasesOfACustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnablePurchaseProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableRemoveCustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableRemoveProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableRemovePromotion;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableRemoverPurchase;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableSearchCustomer;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableSearchProduct;
import br.ufpb.threadControl.messengerConcurrent.runnable.RunnableSearchPurchase;

/**
 * Facade
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo.
 */

public class Facade {

	public static Facade facade;
	private ExecutorService executor;
	private static ManagerFactory iManagerFactory;
	private static ICustomerManager customerManager;
	private static IProductManager productManager;
	private static IPromotionManager promotionManager;
	private static IPurchaseManager purchaseManager;

	private Facade() {
		this.executor = Executors.newFixedThreadPool(10);
	}

	public synchronized static Facade getInstance(ManagerFactory managerFactory) {
		try {
			if (facade == null) {
				facade = new Facade();
			}
			iManagerFactory = managerFactory;
			customerManager = iManagerFactory.getCustomerDao();
			productManager = iManagerFactory.getProductDao();
			promotionManager = iManagerFactory.getPromotionDao();
			purchaseManager = iManagerFactory.getPurchaseDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facade;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD customers

	public void addCustomer(Customer customer) {
		executor.execute(new RunnableAddCustomer(customer, customerManager));
	}

	public void removeCustomer(Customer customer) {
		executor.execute(new RunnableRemoveCustomer(customer, customerManager));
	}

	public void editCustomer(Customer customer) {
		executor.execute(new RunnableEditCustomer(customer, customerManager));
	}

	public void searchCustomer(String login, BlockingQueue<Customer> list) {
		executor.execute(new RunnableSearchCustomer(login, list,
				customerManager));
	}

	public void findCustomerById(Long id, BlockingQueue<Customer> list) {
		executor.execute(new RunnableFindCustomerById(id, list, customerManager));
	}

	public void getListOfCustomer(BlockingQueue<List<Customer>> list) {
		executor.execute(new RunnableGetListCustomer(list, customerManager));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Products

	public void addProduct(Product product) {
		executor.execute(new RunnableAddProduct(product, productManager));
	}

	public void removeProduct(Product product) {
		executor.execute(new RunnableRemoveProduct(product, productManager));
	}

	public void editProduct(Product product) {
		executor.execute(new RunnableEditProduct(product, productManager));
	}

	public void searchProduct(String name, BlockingQueue<Product> list) {
		executor.execute(new RunnableSearchProduct(name, list, productManager));
	}

	public void findProductById(Long id, BlockingQueue<Product> list) {
		executor.execute(new RunnableFindProductById(id, list, productManager));
	}

	public void getListProduct(BlockingQueue<List<Product>> listProduct) {
		executor.execute(new RunnableGetListProduct(listProduct, productManager));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Promotion

	public void addPromotion(Product product, int quantityProduct,
			double promotionalPrice) {
		executor.execute(new RunnableAddPromotion(product, quantityProduct,
				promotionalPrice, promotionManager));
	}

	public void removePromotion(Product product) {
		executor.execute(new RunnableRemovePromotion(product, promotionManager));
	}

	public void editPromotion(Product product) {
		executor.execute(new RunnableEditPromotion(product, promotionManager));
	}

	public void getListPromotion(BlockingQueue<List<Product>> listPromotion) {
		executor.execute(new RunnableGetListPromotion(listPromotion, promotionManager));
	}

	// -------------------------------------------------------------------------
	// methods concurrent of ManagerPurchasesOfProducts

	public void purchaseProduct(Purchase purchase) {
		executor.execute(new RunnablePurchaseProduct(purchase, purchaseManager));
	}

	public void removePurchase(Purchase purchase) {
		executor.execute(new RunnableRemoverPurchase(purchase, purchaseManager));
	}

	public void editPurchase(Purchase purchase) {
		executor.execute(new RunnableEditPurchase(purchase, purchaseManager));
	}

	public void searchPurchase(Product product, Customer customer,
			BlockingQueue<Purchase> list) {
		executor.execute(new RunnableSearchPurchase(product, customer, list, purchaseManager));
	}

	public void getListPurchase(BlockingQueue<List<Purchase>> list) {
		executor.execute(new RunnableGetListPurchase(list, purchaseManager));
	}

	public void getPurchasesOfACustomer(Customer customer,
			BlockingQueue<List<Purchase>> list) {
		executor.execute(new RunnableGetPurchasesOfACustomer(customer, list, purchaseManager));
	}

	public void getProductSold(BlockingQueue<List<Product>> list) {
		executor.execute(new RunnableGetListProductSold(list, purchaseManager));
	}
}