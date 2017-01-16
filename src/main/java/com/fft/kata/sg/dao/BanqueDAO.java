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
	 * enregister un ordre en base de données
	 * @param type
	 * @param montant
	 * @param numeroCompte
	 * @param clientId
	 * @return ordre enregistré
	 * @exception exception si le solde est insuffisant pour le retrait
	 */
	public Ordre enregistrerOrdre(EnumTypeOrdre type, long montant, String numeroCompte, long clientId) throws BanqueException;
	
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
	 * @return liste des ordres
	 */	
	public List<Ordre> listerOrdresFromDate(String numeroCompte, Date fromDate);
	
	/**
	 * récupérer un compte via son numéro de compte
	 * @param numeroCompte du compte à récupérer
	 * @return Le compte ayant le numéro fournit en paramètre.
	 */
	public Compte getCompteByNumeroCompte(String numeroCompte);
	
	/**
	 * enregistrer un compt en base
	 * @param compte
	 * @return compte enregistré en base.
	 */
	public Compte enregistrerComte(Compte compte);
	
	public List<Compte> getListCompteByIdClient(long idClient);
	
	public List<Compte> getListCompteByNomPrenomClient(String nomClient, String prenomClient);
	
	public List<Client> findListClientByNomPrenom(String nom, String prenom);
	
	public Client getClienById(long id);
}
