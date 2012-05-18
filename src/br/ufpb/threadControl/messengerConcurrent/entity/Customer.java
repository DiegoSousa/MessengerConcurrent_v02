package br.ufpb.threadControl.messengerConcurrent.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity customer
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

@Entity
@Table(name = "customers")
@XmlRootElement
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_customer")
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, length = 16)
	private String phone;
	@Column(nullable = false, unique = true)
	private String login;
	@Column(nullable = false)
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(length = 10, name = "date_of_birth", nullable = false)
	private Calendar birthday;
	@OneToMany(mappedBy = "customer")
	private List<Purchase> purchase;

	public Customer(String name, String phone, String login, String password,
			int birthday, int monthOfBirth, int yearOfbirth) {
		this.name = name;
		this.phone = phone;
		this.login = login;
		this.password = password;
		this.birthday = Calendar.getInstance();
		this.birthday.set(yearOfbirth, monthOfBirth - 1, birthday);
		this.purchase = new ArrayList<Purchase>();
	}

	public Customer() {

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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return this.birthday.get(Calendar.DAY_OF_MONTH) + "/"
				+ (this.birthday.get(Calendar.MONTH) + 1) + "/"
				+ birthday.get(Calendar.YEAR);
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.birthday = new GregorianCalendar();
		Date dateAux = null;

		try {
			dateAux = simpleDateFormat.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.birthday.setTime(dateAux);

	}

	/**
	 * @return the purchase
	 */
	public List<Purchase> getPurchase() {
		return purchase;
	}

	/**
	 * @param purchase
	 *            the purchase to set
	 */
	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

	public String toString() {
		return "Name: " + getName() + "\nPhone: " + getPhone() + "\nLogin: "
				+ getLogin() + "\nDate Of Birthday: " + getBirthday();
	}
}
