package com.fft.kata.sg.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;

import org.eclipse.persistence.mappings.EmbeddableMapping;
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
    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.DEPOT, 1000L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	Ordre res = target("order/" + EnumTypeOrdre.DEPOT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId()).request().get(Ordre.class);
    	Assert.assertNotNull("asseert that Date is not null", res.getDate());
    	
    }
    
    @Test
    public void TestPathParamEnregisterOrderRetrait() throws BanqueException{
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(3000L);
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 1000L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	Ordre res = target("order/" + EnumTypeOrdre.DEPOT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId()).request().get(Ordre.class);
    	Assert.assertNotNull("asseert that Date is not null", res.getDate());
    	
    }
    
    
    @Test(expected=BanqueException.class)
    public void TestPathParamEnregisterOrderRetraitWithExceptionSoldeInsuffisant() throws BanqueException{
    	OrderResource.banqueDAO = Mockito.mock(BanqueDAOImpl.class);
    	Client emetteur = MocksUtils.getMockClient();
    	Compte compte = MocksUtils.getMockCompte(0);
    	Ordre ordre = MocksUtils.getMockOrdre(emetteur, compte);
//    	Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 1000L, compte.getNumero(), emetteur.getId())).thenReturn(ordre);
    	//Mockito.when(OrderResource.banqueDAO.enregistrerOrdre(EnumTypeOrdre.RETRAIT, 1000L, compte.getNumero(), emetteur.getId())).thenThrow(new BanqueException("Exception"));
    	target("order/" + EnumTypeOrdre.DEPOT.name() + "/" + 100L + "/" + compte.getNumero() + "/" + emetteur.getId()).request().get(Ordre.class);
    	
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
//    	target("order/" + compte.getNumero()).request().get(ArrayList.class);
    	List<Ordre> res = target("order/" + compte.getNumero()).request().get(ArrayList.class);
    	Assert.assertTrue("list ordre is less than 30", res.size() < 30);
    }
}
