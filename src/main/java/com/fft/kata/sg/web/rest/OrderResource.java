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
import javax.ws.rs.core.Response;

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
@Consumes({"application/json;charset=UTF-8"})
public class OrderResource {
	public static BanqueDAO banqueDAO = new BanqueDAOImpl();
	
	@GET
    @Path("{numeroCompte}")
    public Response listerOdres(@PathParam("numeroCompte") String numeroCompte){
		List<Ordre> listOrdres = banqueDAO.listerOrdres(numeroCompte);
		return Response.status(200).entity(listOrdres).build();
    }
	
	@POST
	@Path("{type}/{montant}/{numeroCompte}/{clientId}")
	public Response enregistrer(@PathParam("type") String type, @PathParam("montant") long montant, @PathParam("numeroCompte") String numeroCompte, @PathParam("type") long clientId) throws BanqueException{
		EnumTypeOrdre EnumType = Enum.valueOf(EnumTypeOrdre.class, type);
		Ordre ordre = banqueDAO.enregistrerOrdre(EnumType, montant, numeroCompte, clientId);
		return Response.status(200).entity(ordre).build();
	}
}
