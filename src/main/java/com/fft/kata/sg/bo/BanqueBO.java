/**
 * 
 */
package com.fft.kata.sg.bo;

import java.util.Date;
import java.util.List;

import com.fft.kata.sg.dao.entity.Client;
import com.fft.kata.sg.dao.entity.Compte;
import com.fft.kata.sg.dao.entity.Ordre;

/**
 * @author PC
 *
 */
public interface BanqueBO {
	
	/**
	 * passer un ordre de dépot ou retrait sur un compte bancaire.
	 * @param clientId
	 * @param type
	 * @param montant
	 * @param numeroCompte
	 * @return ordre enregistré
	 */
	public Ordre passerOrdre(long clientId, String type, long montant, String numeroCompte);
	
	/**
	 * récupère la liste des derniers ordres
	 * @param numeroCompte
	 * @return liste des ordres
	 */	
	public List<Ordre> listerOrdres(String numeroCompte);
	
	/**
	 * récupère la liste des derniers ordres à partir de la date fromDate
	 * @param numeroCompte
	 * @param fromDate date de debut des ordres à récupérer
	 * @return liste des ordres derniers ordres depuis la date fromDate
	 */	
	public List<Ordre> listerOrdresFromDate(String numeroCompte, Date fromDate);
}
