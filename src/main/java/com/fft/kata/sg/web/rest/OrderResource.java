/**
 * 
 */
package com.fft.kata.sg.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fft.kata.sg.dao.BanqueDAO;
import com.fft.kata.sg.dao.entity.Ordre;
import com.fft.kata.sg.dao.enums.EnumTypeOrdre;
import com.fft.kata.sg.dao.exception.BanqueException;
import com.fft.kata.sg.dao.impl.BanqueDAOImpl;

/**
 * @author PC
 *
 */
@Path("order")
@Produces({"text/plain", "application/xml", "application/json"})
@Consumes({"text/plain", "application/xml", "application/json"})
public class OrderResource {
	public static BanqueDAO banqueDAO = new BanqueDAOImpl();
	
	@GET
    @Path("{numeroCompte}")
    public List<Ordre> listerOdres(@PathParam("numeroCompte") String numeroCompte){
		List<Ordre> listOrdres = banqueDAO.listerOrdres(numeroCompte);
		return listOrdres;
    }
	
	@POST
	@Path("{type}/{montant}/{numeroCompte}/{clientId}")
	public Ordre enregistrer(@PathParam("type") String type, @PathParam("montant") long montant, @PathParam("numeroCompte") String numeroCompte, @PathParam("type") long clientId) throws BanqueException{
		EnumTypeOrdre EnumType = Enum.valueOf(EnumTypeOrdre.class, type);
		Ordre ordre = banqueDAO.enregistrerOrdre(EnumType, montant, numeroCompte, clientId);
		return ordre;
	}
}
