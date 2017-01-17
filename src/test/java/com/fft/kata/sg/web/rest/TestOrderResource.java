package com.fft.kata.sg.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
public class TestOrderResource extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(OrderResource.class);
    }
    
    @Test
    public void TestPathParamEnregisterOrderDepot() throws BanqueException{
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(0);
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.DEPOT, 100L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	String url = "order/" + EnumTypeOrdre.DEPOT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId();
    	Response res = target(url).request().post(Entity.json(compte.getNumero()));
    	
//    	Ordre res = target(url).request().get(Ordre.class);
    	Assert.assertNotNull("asseert that Date is not null", res.getDate());
    	
    }
    
    @Test
    public void TestPathParamEnregisterOrderRetrait() throws BanqueException{
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(3000L);
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 100L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	String url = "order/" + EnumTypeOrdre.RETRAIT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId();
    	Ordre res = target(url).request().get(Ordre.class);
    	Assert.assertNotNull("asseert that Date is not null", res.getDate());
    	
    }
    
    
    @Test(expected=BanqueException.class)
    public void TestPathParamEnregisterOrderRetraitWithExceptionSoldeInsuffisant() throws BanqueException{
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(0);
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 100L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	//Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 1000L, compte.getNumero(), emetteur.getId())).thenThrow(new BanqueException("Exception"));
    	String url = "order/" + EnumTypeOrdre.RETRAIT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId();
    	target(url).request().get(Ordre.class);
    	
    }
    
    @Test
    public void TestPathParamListerOdres(){
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(0);
    	List<Ordre> listOrdres = new ArrayList<>();
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
    	listOrdres.add(ordre);
    	Mockito.when(OrderResource.banqueDAO.listerOrdres(compte.getNumero())).thenReturn(listOrdres);
    	String url = "order/" + compte.getNumero();
    	List<Ordre> ordres = target(url).request().get(ArrayList.class);
    	Assert.assertTrue("list ordre is less than 30", ordres.size() < 30);
    }
}
