package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.entity.Purchase;
import br.ufpb.threadControl.messengerConcurrent.manager.IPurchaseManager;
import br.ufpb.threadControl.messengerConcurrent.util.HibernateUtil;

/**
 * Buy Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class PurchaseDAOJPA implements IPurchaseManager {

	private Logger logger;
	private EntityManager entityManager;

	public PurchaseDAOJPA() {
		this.entityManager = HibernateUtil.getInstance().getFactory()
				.createEntityManager();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Model.PurchasesOfProductsDAO");
	}

	@Override
	public Purchase purchaseProduct(Purchase purchase) throws Exception {
		
		try {
			if (purchase.getProduct().getIsPromotional() == true
					&& purchase.getProduct().getQuantityPromotion() > 0
					&& purchase.getQuantity() <= purchase.getProduct()
							.getQuantityPromotion()) {
				purchase.getProduct().setQuantityPromotion(
						purchase.getProduct().getQuantityPromotion()
								- purchase.getQuantity());
				if (purchase.getProduct().getQuantityPromotion() == 0) {
					purchase.getProduct().setIsPromotional(false);
				}
			} else if (purchase.getProduct().getIsPromotional() == false
					&& purchase.getProduct().getQuantity() > 0) {
				purchase.getProduct().setQuantity(
						purchase.getProduct().getQuantity()
								- purchase.getQuantity());
			} else if (purchase.getProduct().getQuantity() == 0
					&& purchase.getProduct().getQuantityPromotion() == 0) {
				throw new Exception("This product is not in stock");
			}

			entityManager.getTransaction().begin();
			entityManager.persist(purchase);
			entityManager.getTransaction().commit();
			logger.info("Purchase realization witch successfully! Promotion Code: "
					+ purchase.getId());
			return purchase;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error to sell the product: ", exception);
		}
	}

	@Override
	public Purchase removePurchase(Purchase purchase) throws Exception {
		
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(purchase));
			entityManager.getTransaction().commit();
			logger.info("Purchase: " + purchase.getId()
					+ " successfully removed!");
			return purchase;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error removing purchase: ", exception);
		}
	}

	@Override
	public Purchase editPurchase(Purchase purchase) throws Exception {
		

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(purchase);
			entityManager.getTransaction().commit();
			logger.info("Purchase: " + purchase.getId() + " updated!");
			return purchase;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error editing product: ", exception);
		}
	}

	@Override
	public Purchase searchPurchase(Product product, Customer customer)
			throws Exception {

		try {
			Purchase purchase = (Purchase) entityManager
					.createQuery(
							"select p from Purchase p where p.id_product = :id_product and p.id_customer = : id_customer")
					.setParameter("id_product", product.getId())
					.setParameter("id_customer", customer.getId())
					.getSingleResult();
			logger.info("Historical Customer Purchases: " + customer.getName()
					+ " found!");
			return purchase;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error searching purchase.", exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Purchase> getListPurchase() throws Exception {

		try {
			List<Purchase> list = entityManager.createQuery(
					"select p from Purchase p").getResultList();
			logger.info("List Purchase found!");
			return list;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching list purchase, probably the list does not exist: ",
					exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Purchase> getPurchasesOfACustomer(Customer customer)
			throws Exception {

		try {
			List<Purchase> list = entityManager
					.createQuery(
							"select p from Purchase p where p.id_customer = :id_customer")
					.setParameter("id_customer", customer.getId())
					.getResultList();
			logger.info("Historical Customer Purchases: " + customer.getName()
					+ " found!");
			return list;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching historical purchase of a Customer, probably the list does not exist",
					exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getListProductSold() throws Exception {

		try {
			List<Product> list = entityManager.createQuery(
					"select p.id_product from Purchase p").getResultList();
			logger.info("Historical Product Sold found!");
			return list;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching historical product sold, probably the list does not exist",
					exception);
		}
	}

}