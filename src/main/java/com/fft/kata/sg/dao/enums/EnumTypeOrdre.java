package com.fft.kata.sg.dao.enums;

/**
 * @author PC
 *
 */
public enum EnumTypeOrdre {	
	
	DEPOT("DEPOSIT", "Dépot"), 
	RETRAIT("WITHDRAWAL", "Retrait");	
	
	private String libelle;	
	private String code;
	
	/**
	 * @param libelle
	 * @param code
	 */
	private EnumTypeOrdre(String libelle, String code) {
		this.libelle = libelle;
		this.code = code;
	}
	
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	
	
	
}
