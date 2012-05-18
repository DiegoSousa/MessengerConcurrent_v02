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
public interface IPromotionManager {

	public abstract Product addPromotion(Product product, int quantityProduct,
			double promotionalPrice) throws Exception;

	public abstract Product removePromotion(Product product) throws Exception;

	public abstract Product editPromotion(Product product) throws Exception;
	
	public abstract List<Product> getListPromotion() throws Exception;

}