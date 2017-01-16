package com.fft.kata.sg.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fft.kata.sg.dao.BanqueDAO;
import com.fft.kata.sg.dao.entity.Client;
import com.fft.kata.sg.dao.entity.Compte;
import com.fft.kata.sg.dao.entity.Ordre;
import com.fft.kata.sg.dao.enums.EnumTypeOrdre;
import com.fft.kata.sg.dao.exception.BanqueException;

public class BanqueDAOImpl implements BanqueDAO {
	
	@PersistenceContext
	public EntityManager persistance;


	/**
	 * enregister un ordre en base de données
	 * @param type
	 * @param montant
	 * @param numeroCompte
	 * @param clientId
	 * @return ordre enregistré
	 * @exception exception si le solde est insuffisant pour le retrait
	 */
	@Override
	public Ordre enregistrerOrdre(EnumTypeOrdre type, long montant, String numeroCompte, long clientId) throws BanqueException {
		// on retrouve l'emmeteur et le compte en bd		
		Compte compte = persistance.find(Compte.class, numeroCompte);
		Client client = persistance.find(Client.class, clientId);
		long soldeInitiale = compte.getSolde();
		Date dateOrdre = new Date();
		long balance = soldeInitiale;
		if(type == EnumTypeOrdre.DEPOT){
			balance += montant;
		}else{
			boolean isRetraitAutoriser = checkSoldeIsMoreThanAmount(compte, montant);
			if(isRetraitAutoriser){
				balance -= montant;
			}else{
				throw new BanqueException("Votre solde est insuffisant");
			}
		}
		compte.setSolde(balance);
		Ordre ordre = new Ordre(client, compte, dateOrdre, montant, type, balance);
		persistance.persist(ordre);
		return ordre;
	}

	private boolean checkSoldeIsMoreThanAmount(Compte compte, long montant) {
		return compte.getSolde() >= montant;
	}
	
	/**
	 * récupère la liste des 30 derniers ordres
	 * @param numeroCompte
	 * @return liste des ordres
	 */
	@Override
	public List<Ordre> listerOrdres(String numeroCompte) {
		Query query = persistance.createNamedQuery("trenteJours");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -30);
		query.setParameter("date", date.getTime());
		List<Ordre> listOrdres = query.getResultList();
		return listOrdres;
	}

	@Override
	public List<Ordre> listerOrdresFromDate(String numeroCompte, Date fromDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte getCompteByNumeroCompte(String numeroCompte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte enregistrerComte(Compte compte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> getListCompteByIdClient(long idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> getListCompteByNomPrenomClient(String nomClient, String prenomClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findListClientByNomPrenom(String nom, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClienById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
