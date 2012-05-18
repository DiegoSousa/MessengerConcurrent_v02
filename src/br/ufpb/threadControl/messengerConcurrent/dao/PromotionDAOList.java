package br.ufpb.threadControl.messengerConcurrent.dao;

import java.util.List;

import br.ufpb.threadControl.messengerConcurrent.entity.Product;
import br.ufpb.threadControl.messengerConcurrent.manager.IPromotionManager;

/**
 * Description Class
 * 
 * @author Diego Sousa, diego[at]diegosousa[dot]com
 * @version 0.0.1
 * @since
 *
 * Copyright (C) 2012 
 */
public class PromotionDAOList implements IPromotionManager {

	@Override
	public Product addPromotion(Product product, int quantityProduct,
			double promotionalPrice) throws Exception {
		return null;
	}

	@Override
	public Product removePromotion(Product product) throws Exception {
		return null;
	}

	@Override
	public Product editPromotion(Product product) throws Exception {
		return null;
	}

	@Override
	public List<Product> getListPromotion() throws Exception {
		return null;
	}

}
