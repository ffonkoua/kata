/**
 * 
 */
package com.fft.kata.sg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fft.kata.sg.dao.entity.Client;
import com.fft.kata.sg.dao.entity.Compte;
import com.fft.kata.sg.dao.entity.Ordre;
import com.fft.kata.sg.dao.enums.EnumTypeOrdre;
import com.fft.kata.sg.dao.exception.BanqueException;
import com.fft.kata.sg.dao.impl.BanqueDAOImpl;
import com.fft.kata.sg.util.MocksUtils;

/**
 * @author PC
 *
 */
public class TestBanqueDAO {
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEnregistrerOrderDepot()throws Exception{
		BanqueDAOImpl banqueDAO = new BanqueDAOImpl();
		banqueDAO.persistance = Mockito.mock(EntityManager.class);
		EnumTypeOrdre type = EnumTypeOrdre.DEPOT;
		Compte compte = MocksUtils.getMockCompte(0);
		long ancienSolde = compte.getSolde();
		Client client = MocksUtils.getMockClient();
		long montant = 500L;
		Mockito.when(banqueDAO.persistance.find(Compte.class, compte.getNumero())).thenReturn(compte);
		Mockito.when(banqueDAO.persistance.find(Client.class, client.getId())).thenReturn(client);
		
		Ordre ordre = banqueDAO.enregistrerOrdre(type, montant, compte.getNumero(), client.getId());
		
		long nouveauSolde = compte.getSolde();
		
		Assert.assertTrue("assert that order has account with num", compte.getNumero().equals(ordre.getCompte().getNumero()));
		Assert.assertTrue("assert that balance equals to solde", ordre.getBanlance() == compte.getSolde());
		Assert.assertTrue("assert that amount is up to date", nouveauSolde == ancienSolde + montant);
	}
	
	@Test
	public void testEnregistrerOrderRetrait()throws Exception{
		BanqueDAOImpl banqueDAO = new BanqueDAOImpl();
		banqueDAO.persistance = Mockito.mock(EntityManager.class);
		EnumTypeOrdre type = EnumTypeOrdre.RETRAIT;
		Compte compte = MocksUtils.getMockCompte(3000);
		long ancienSolde = compte.getSolde();
		Client client = MocksUtils.getMockClient();
		long montant = 500L;
		Mockito.when(banqueDAO.persistance.find(Compte.class, compte.getNumero())).thenReturn(compte);
		Mockito.when(banqueDAO.persistance.find(Client.class, client.getId())).thenReturn(client);
		
		Ordre ordre = banqueDAO.enregistrerOrdre(type, montant, compte.getNumero(), client.getId());
		
		long nouveauSolde = compte.getSolde();
		
		Assert.assertTrue("assert that order has account with a num", compte.getNumero().equals(ordre.getCompte().getNumero()));
		Assert.assertTrue("assert that balance equals to solde", ordre.getBanlance() == compte.getSolde());
		Assert.assertTrue("assert that balance is up to date", nouveauSolde == ancienSolde - montant);
	}
	
	@Test(expected = BanqueException.class)
	public void testEnregistrerOrderRetraitWithInsufficientBalance()throws Exception{
		BanqueDAOImpl banqueDAO = new BanqueDAOImpl();
		banqueDAO.persistance = Mockito.mock(EntityManager.class);
		EnumTypeOrdre type = EnumTypeOrdre.RETRAIT;
		Compte compte = MocksUtils.getMockCompte(0);
		Client client = MocksUtils.getMockClient();
		long montant = 500L;
		Mockito.when(banqueDAO.persistance.find(Compte.class, compte.getNumero())).thenReturn(compte);
		Mockito.when(banqueDAO.persistance.find(Client.class, client.getId())).thenReturn(client);
		
		banqueDAO.enregistrerOrdre(type, montant, compte.getNumero(), client.getId());
	}
	
	@Test
	public void testListerOfOrders(){
		List<Ordre> mockList = Mockito.mock(ArrayList.class);
		BanqueDAOImpl banqueDAO = new BanqueDAOImpl();
		Query query = Mockito.mock(Query.class);
		banqueDAO.persistance = Mockito.mock(EntityManager.class);
		Mockito.when(banqueDAO.persistance.createNamedQuery("trenteJours")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(mockList);
		Mockito.when(mockList.size()).thenReturn(10);
		Compte compte = MocksUtils.getMockCompte(0);
		List<Ordre> listOrdres = banqueDAO.listerOrdres(compte.getNumero());
		Assert.assertTrue("assert that list of orders is less than 30", listOrdres.size() <= 30);
	}

}
