package br.ufpb.threadControl.messengerConcurrent.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@Entity
@Table(name = "products")
@XmlRootElement
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id_product")
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private int quantity;
	@Column(unique = true)
	private static long promotionCde;
	@Column
	private double promotionalPrice = 0;
	@Column
	private int quantityPromotion = 0;
	@Column(nullable = false)
	private boolean isPromotional = false;
	@OneToMany(mappedBy = "product")
	private List<Purchase> purchase;

	public Product(String name, double price, int quantity) {
		this.purchase = new ArrayList<Purchase>();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Product() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 * @throws Exception
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 * @throws Exception
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
	/**
	 * @return the promotioncode
	 */
	public static long getPromotioncode() {
		return promotionCde;
	}

	/**
	 * @param promotioncode the promotioncode to set
	 */
	public static void setPromotioncode(long promotioncode) {
		Product.promotionCde = promotioncode;
	}

	/**
	 * @return the promotionalPrice
	 */
	public double getPromotionalPrice() {
		return promotionalPrice;
	}

	/**
	 * @param promotionalPrice
	 *            the promotionalPrice to set
	 * @throws Exception
	 */
	public void setPromotionalPrice(double promotionalPrice) {
		this.promotionalPrice = promotionalPrice;
	}

	/**
	 * @return the quantityPromotion
	 */
	public int getQuantityPromotion() {
		return quantityPromotion;
	}

	/**
	 * @param quantityPromotion
	 *            the quantityPromotion to set
	 * @throws Exception
	 */
	public void setQuantityPromotion(int quantityPromotion) {
		this.quantityPromotion = quantityPromotion;
	}

	/**
	 * @return the isPromotional
	 */
	public boolean getIsPromotional() {
		return isPromotional;
	}

	/**
	 * @param isPromotional
	 *            the isPromotional to set
	 */
	public void setIsPromotional(boolean isPromotional) {
		this.isPromotional = isPromotional;
	}

	/**
	 * @return the promotions
	 */
	public List<Purchase> getPurchase() {
		return purchase;
	}

	/**
	 * @param promotions
	 *            the promotions to set
	 */
	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {

		return "\nProduct: " + this.name + "\nProduct Code: " + this.id
				+ "\nPrice: " + this.price + "\nQuantity: " + this.quantity
				+ "\nPromotional price: " + this.promotionalPrice
				+ "\nNumber of Promotional Products: " + this.quantityPromotion;
	}

}
