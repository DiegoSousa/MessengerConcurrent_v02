package br.ufpb.threadControl.messengerConcurrent.entity;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import br.ufpb.threadControl.messengerConcurrent.controller.Facade;
import br.ufpb.threadControl.messengerConcurrent.managerFactory.ManagerFactoryJPA;


/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 * 
 *        Copyright (C) 2012
 */
public class Main {

	 public static void main(String[] args) throws Exception {

			BlockingQueue<List<Customer>> listCustomer = new LinkedBlockingQueue<List<Customer>>();		

			Facade facade =  Facade.getInstance(new ManagerFactoryJPA());
			
			facade.getListOfCustomer(listCustomer);

			List<Customer> listAux = null;
			try {
				listAux = listCustomer.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

//			ICustomerManager  a = new CustomerDAOJPA();
//			
//			List <Customer> b = a.getListOfCustomers();
			
			System.out.println(listAux);
		}
	
	 }

