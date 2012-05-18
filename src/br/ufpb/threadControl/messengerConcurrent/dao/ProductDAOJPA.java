package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IProductManager;
import br.ufpb.threadControl.messengerConcurrent.util.HibernateUtil;

/**
 * Product Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ProductDAOJPA implements IProductManager {

	private Logger logger;
	private EntityManager entityManager;

	public ProductDAOJPA() {

		entityManager = HibernateUtil.getInstance().getFactory()
				.createEntityManager();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.messengerConcurrent.dao.ProductDao");
	}

	@Override
	public Product addProduct(Product product) throws Exception {		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(product);
			entityManager.getTransaction().commit();
			logger.info("Product: " + product.getName() +",successfully added!");
			return product;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error adding product: ", exception);
		}
	}

	@Override
	public Product removeProduct(Product product) throws Exception {
		
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(searchProductByName(product.getName()));
			entityManager.getTransaction().commit();
			logger.info("Product: " + product.getName() + " ,successfully removed!");
			return product;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error removing product: ", exception);
		}
	}

	@Override
	public Product editProduct(Product product) throws Exception {
		
		/**
		 * # The searchProductByName is used to pass the object state detached to state Manager.
		 * # The merge is used to edit the object.
		 */
		
		try {
			Product productAux = searchProductByName(product.getName());
			productAux.setPrice(product.getPrice());
			productAux.setQuantity(product.getQuantity());						
			entityManager.getTransaction().begin();
			entityManager.merge(productAux);
			entityManager.getTransaction().commit();
			logger.info("Product: " + productAux.getName() + " Code: "
					+ productAux.getId() + " updated!");
			return product;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error editing product: ", exception);
		}
	}

	@Override
	public Product findProductById(Long id) throws Exception {

		try {
			return entityManager.find(Product.class, id);
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching product, probably the Product does not exist:  ",
					exception);
		}
	}

	@Override
	public Product searchProductByName(String name) throws Exception {
		try {
			Product product = (Product) entityManager
					.createQuery("select p from Product p where p.name = :name")
					.setParameter("name", name).getSingleResult();
			logger.info("Product: " + product.getName() + " found!");
			return product;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching product, probably the product does not exist: ",
					exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getListProduct() throws Exception {
		try {
			return entityManager.createQuery(
					"SELECT p FROM Product p order by p.name").getResultList();
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error listing product, probably the list is empty: ",
					exception);
		}
	}

}
