package br.ufpb.threadControl.messengerConcurrent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity Purchase
 * 
 * @author diego sousa - www.diegosousa.com
 * @version 2.0
 * 
 *          copyright (c) 2012 diego sousa de azevedo
 */

@Entity
@Table(name = "purchase")
@XmlRootElement
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id_purchase")
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_customer", nullable = false)
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false)
	private Product product;
	@Column(name = "quantityProduct")
	private int quantity;

	public Purchase(Customer customer, Product product, int quantity) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}

	public Purchase() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getcustomer() {
		return customer;
	}

	public void setcustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		Purchase other = (Purchase) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase \n Code" + id + ", Customer: " + customer
				+ ", Product: " + product +"Quantity of products purchased: "+ quantity;
	}

}
