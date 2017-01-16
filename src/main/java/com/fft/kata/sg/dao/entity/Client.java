/**
 * 
 */
package com.fft.kata.sg.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author PC
 *
 */
@Entity
public class Client implements Serializable{
	@Id
	@GeneratedValue
	private long id;
	private String  nom;
	private String  prenom;
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	

}
