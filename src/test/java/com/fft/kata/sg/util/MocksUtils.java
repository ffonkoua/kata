package com.fft.kata.sg.util;

import java.util.Date;

import org.mockito.Mockito;

import com.fft.kata.sg.dao.entity.Client;
import com.fft.kata.sg.dao.entity.Compte;
import com.fft.kata.sg.dao.entity.Ordre;
import com.fft.kata.sg.dao.enums.EnumTypeOrdre;

public class MocksUtils {
	
	public static final Client getMockClient(){
		Client clientMock = Mockito.mock(Client.class);
		Mockito.when(clientMock.getNom()).thenReturn("felicien");
		Mockito.when(clientMock.getPrenom()).thenReturn("charly");
		Mockito.when(clientMock.getId()).thenReturn(100L);
		return clientMock;
	}
	
	public static final Compte getMockCompte(long initSolde){
		Compte compte = new Compte("200060D", initSolde);
		return compte;
	}
	
	public static final Ordre getMockOrdre(Client emetteur, Compte compte){
		Date date = new Date();
		long montant = 1000L;
		EnumTypeOrdre type = EnumTypeOrdre.DEPOT;
		long balance = compte.getSolde();
		Ordre ordre = new Ordre(emetteur, compte, date, montant, type, balance);
		return ordre;
	}
}
