package br.ufpb.threadControl.messengerConcurrent.dao;

/**
 * Promotion Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 
 * 
 * Copyright (C) 2012 Diego Sousa de Azevedo
 */

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;
import br.ufpb.threadControl.messengerConcurrent.util.HibernateUtil;

public class PromotionDAOJPA implements IPromotionManager {

	private Logger logger;
	private EntityManager entityManager;
	private static long promotionCode = 0;

	public PromotionDAOJPA() {

		this.entityManager = HibernateUtil.getInstance().getFactory()
				.createEntityManager();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Model.PromotionManager");
	}

	@SuppressWarnings("static-access")
	@Override
	public Product addPromotion(Product product, int quantityProduct,
			double promotionalPrice) throws Exception {

		try {
			if (quantityProduct > 0 && promotionalPrice > 0
					&& product.getPrice() > promotionalPrice
					&& product.getQuantity() >= quantityProduct) {
				// Falta Procurar produto, setar as varias abaixo e dar um merge
				// no produto.
				entityManager.getTransaction().begin();
				product.setPromotionalPrice(promotionalPrice);
				product.setQuantityPromotion(quantityProduct);
				product.setIsPromotional(true);
				product.setPromotioncode(generatePromotionCode());

				entityManager.merge(product);
				entityManager.getTransaction().commit();
				logger.info("Promotion created with successfully!");
				return product;
			}
			throw new Exception(
					"Error editing product, probably the quantity and/or price "
							+ "promotion is 0 or product does not have sufficient or the promotion "
							+ "price is greater than the of the product.");
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error creating promotion: ", exception);
		}
	}

	//
	// @Override
	// public Product removePromotion(Product product) throws Exception {

	// try {

	// Product productAux = getPromotion(product.getPromotioncode());

	// productAux.setQuantityPromotion(0);
	// productAux.setPromotionalPrice(0);
	// productAux.setIsPromotional(false);
	// productAux.setPurchase(null);
	// entityManager.getTransaction().begin();
	// entityManager.merge(productAux);
	// entityManager.getTransaction().commit();
	// logger.info("Promotion successfully Removed!");
	// return product;
	// } catch (Exception exception) {
	// entityManager.getTransaction().rollback();
	// entityManager.clear();
	// throw new Exception("Error removing promotion: ", exception);
	// }
	// }

	@Override
	public Product editPromotion(Product product) throws Exception {

		try {
			// Product productAux = getPromotion(product.getName(),
			// product.getPromotionalPrice());
			// productAux.setQuantityPromotion(product.getQuantityPromotion());
			// productAux.setIsPromotional(product.getIsPromotional());
			// productAux.setPurchase(product.getPurchase());

			if (product.getQuantityPromotion() != 0
					&& product.getPromotionalPrice() != 0
					&& product.getPrice() > product.getPromotionalPrice()
					&& product.getQuantity() >= product.getQuantityPromotion()) {
				product.setIsPromotional(true);
				entityManager.getTransaction().begin();
				entityManager.merge(product);
				entityManager.getTransaction().commit();
				logger.info("Promotion updated with successfully!");
				return product;
			}
			throw new Exception(
					"Error editing product, probably the quantity and/or price "
							+ "promotion is 0 or product does not have sufficient or the promotion "
							+ "price is greater than the of the product.");
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			logger.info("Error updating promotion");
			throw new Exception("Error editing promotion: ", exception);
		}
	}

	@SuppressWarnings("unused")
	private Product getPromotion(long promotionCode) throws Exception {
		try {
			return (Product) entityManager
					.createQuery(
							"SELECT p FROM Product p where p.promotionCode = :promotionCode")
					.setParameter("promotionCode", promotionCode)
					.getSingleResult();

		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error listing product in promotion, probably the list is empty: ",
					exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getListPromotion() throws Exception {
		try {
			return entityManager.createQuery(
					"SELECT p FROM Product p where p.isPromotional = true")
					.getResultList();
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error listing product in promotion, probably the list is empty: ",
					exception);
		}
	}

	private static long generatePromotionCode() {
		return promotionCode++;

	}

	@Override
	public Product removePromotion(Product product) throws Exception {
		return null;
	}

}
