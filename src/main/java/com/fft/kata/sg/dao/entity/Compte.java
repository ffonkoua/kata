/**
 * 
 */
package com.fft.kata.sg.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author PC
 *
 */
@Entity
public class Compte implements Serializable{

	@Id
	private String numero;
	private long solde;
	
	
	/**
	 * @param numero
	 * @param solde
	 */
	public Compte(String numero, long solde) {
		super();
		this.numero = numero;
		this.solde = solde;
	}
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @return the solde
	 */
	public long getSolde() {
		return solde;
	}
	/**
	 * @param solde the solde to set
	 */
	public void setSolde(long solde) {
		this.solde = solde;
	}
	
	
	
}
