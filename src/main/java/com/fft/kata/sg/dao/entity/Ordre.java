/**
 * 
 */
package com.fft.kata.sg.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fft.kata.sg.dao.enums.EnumTypeOrdre;

/**
 * @author PC
 *
 */
@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="toutes", query="SELECT ordre FROM Ordre AS ordre"),
	@NamedQuery(name="trenteJours", query="SELECT ordre FROM Ordre AS ordre WHERE ordre.date < :dateFrom")
})
public class Ordre implements Serializable{
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private long montant;
	@Enumerated(value = EnumType.STRING)
	private EnumTypeOrdre type;
	private long banlance;
	private Client emetteur;
	private Compte compte;
	
	/**
	 * @param date
	 * @param montant
	 * @param type
	 * @param balance
	 * @param emetteur
	 * @param compte
	 */
	public Ordre(Client emetteur, Compte compte, Date date, long montant, EnumTypeOrdre type, long balance) {
		super();
		this.emetteur = emetteur;
		this.compte = compte;
		this.date = date;
		this.montant = montant;
		this.type = type;
		this.banlance = balance;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @return the montant
	 */
	public long getMontant() {
		return montant;
	}
	/**
	 * @return the type
	 */
	public EnumTypeOrdre getType() {
		return type;
	}
	/**
	 * @return the banlance
	 */
	public long getBanlance() {
		return banlance;
	}
	/**
	 * @return the emetteur
	 */
	public Client getEmetteur() {
		return emetteur;
	}
	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EnumTypeOrdre type) {
		this.type = type;
	}
	
	
	
}
