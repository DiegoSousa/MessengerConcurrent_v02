package br.ufpb.threadControl.messengerConcurrent.manager;

import java.util.List;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 *
 * Copyright (C) 2012 
 */
public interface IProductManager {

	public abstract Product addProduct(Product product) throws Exception;

	public abstract Product removeProduct(Product product) throws Exception;

	public abstract Product editProduct(Product product) throws Exception;

	public abstract Product findProductById(Long id) throws Exception;

	public abstract Product searchProductByName(String name) throws Exception;
	
	public abstract List<Product> getListProduct() throws Exception;

}