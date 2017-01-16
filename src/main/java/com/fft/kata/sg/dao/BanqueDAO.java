/**
 * 
 */
package com.fft.kata.sg.dao;

import java.util.Date;
import java.util.List;

import com.fft.kata.sg.dao.entity.Client;
import com.fft.kata.sg.dao.entity.Compte;
import com.fft.kata.sg.dao.entity.Ordre;
import com.fft.kata.sg.dao.enums.EnumTypeOrdre;
import com.fft.kata.sg.dao.exception.BanqueException;

/**
 * @author PC
 *
 */
public interface BanqueDAO {
	
	/**
	 * enregister un ordre en base de donn�es
	 * @param type
	 * @param montant
	 * @param numeroCompte
	 * @param clientId
	 * @return ordre enregistr�
	 * @exception exception si le solde est insuffisant pour le retrait
	 */
	public Ordre enregistrerOrdre(EnumTypeOrdre type, long montant, String numeroCompte, long clientId) throws BanqueException;
	
	/**
	 * r�cup�re la liste des derniers ordres
	 * @param numeroCompte
	 * @return liste des ordres
	 */	
	public List<Ordre> listerOrdres(String numeroCompte);
	
	/**
	 * r�cup�re la liste des derniers ordres � partir de la date fromDate
	 * @param numeroCompte
	 * @param fromDate date de debut des ordres � r�cup�rer
	 * @return liste des ordres
	 */	
	public List<Ordre> listerOrdresFromDate(String numeroCompte, Date fromDate);
	
	/**
	 * r�cup�rer un compte via son num�ro de compte
	 * @param numeroCompte du compte � r�cup�rer
	 * @return Le compte ayant le num�ro fournit en param�tre.
	 */
	public Compte getCompteByNumeroCompte(String numeroCompte);
	
	/**
	 * enregistrer un compt en base
	 * @param compte
	 * @return compte enregistr� en base.
	 */
	public Compte enregistrerComte(Compte compte);
	
	public List<Compte> getListCompteByIdClient(long idClient);
	
	public List<Compte> getListCompteByNomPrenomClient(String nomClient, String prenomClient);
	
	public List<Client> findListClientByNomPrenom(String nom, String prenom);
	
	public Client getClienById(long id);
}
