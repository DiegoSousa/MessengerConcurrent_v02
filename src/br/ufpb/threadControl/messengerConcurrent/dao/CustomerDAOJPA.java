package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import br.ufpb.threadControl.messengerConcurrent.entity.Customer;
import br.ufpb.threadControl.messengerConcurrent.manager.ICustomerManager;
import br.ufpb.threadControl.messengerConcurrent.util.HibernateUtil;

/**
 * customer Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class CustomerDAOJPA implements ICustomerManager {

	private Logger logger;
	private EntityManager entityManager;

	public CustomerDAOJPA() {
		this.entityManager = HibernateUtil.getInstance().getFactory()
				.createEntityManager();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.messengerConcurrent.dao.customerDao");
	}

	/**
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	@Override
	public Customer addCustomer(Customer customer) throws Exception {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			logger.info("customer: " + customer.getName()
					+ " successfully added!");
			return customer;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error creating customer: ", exception);
		}
	}

	/**
	 * 
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	@Override
	public Customer removeCustomer(Customer customer) throws Exception {

		try {
			entityManager.getTransaction().begin();
			entityManager.remove(searchCustomerByLogin(customer.getLogin()));
			entityManager.getTransaction().commit();
			logger.info(customer.getLogin() + " successfully removed!");
			return customer;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error removing customer: ", exception);
		}
	}

	/**
	 * 
	 * @param customer
	 *            Object customer
	 * @throws Exception
	 * @return Object customer
	 */

	@Override
	public Customer editCustomer(Customer customer) throws Exception {

		/**
		 * # The searchCustomerByLogin is used to pass the object state detached to state Manager.
		 * # The merge is used to edit the object.
		 */

		try {
			entityManager.getTransaction().begin();
			Customer customerAux = searchCustomerByLogin(customer.getLogin());
			customerAux.setName(customer.getName());
			customerAux.setBirthday(customer.getBirthday());
			customerAux.setPassword(customer.getPassword());
			customerAux.setPhone(customer.getPhone());
			entityManager.merge(customerAux);
			entityManager.getTransaction().commit();
			logger.info("customer: " + customer.getName() + " edited!");
			return customer;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception("Error editing customer: ", exception);
		}
	}

	/**
	 * 
	 * @param id
	 *            belonging to the customer that will be searched
	 * @throws Exception
	 * @return Object customer
	 */

	@Override
	public Customer findCustomerById(long id) throws Exception {
		try {
			return entityManager.find(Customer.class, id);
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching customer, probably the User does not exist:  ",
					exception);
		}
	}

	/**
	 * 
	 * @param login
	 *            belonging to the customer that will be searched
	 * @throws Exception
	 * @return Object customer
	 */

	@Override
	public Customer searchCustomerByLogin(String login) throws Exception {
		try { 
			Customer customer = (Customer) entityManager
					.createQuery(
							"select c from Customer c where c.login = :login")
					.setParameter("login", login).getSingleResult();
			logger.info("customer: " + customer.getName() + " found!");
			return customer;
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error searching customer, probably the User does not exist: ",
					exception);
		}
	}

	/**
	 * @throws Exception
	 * @return List of all customer
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getListOfCustomers() throws Exception {
		try {
			return entityManager.createQuery(
					"select c from Customer c order by c.name").getResultList();
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			entityManager.clear();
			throw new Exception(
					"Error listing user, probably the list is empty: ",
					exception);
		}
	}
}
