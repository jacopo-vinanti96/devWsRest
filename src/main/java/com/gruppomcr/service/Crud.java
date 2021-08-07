package com.gruppomcr.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gruppomcr.dao.impl.UserDaoImpl;
import com.gruppomcr.dao.model.Persona;

@Path(value="/crud")
public class Crud {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Path(value="/crea")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String crea(String persJson){
        // invoca il dao per salvare
        Persona persona = gson.fromJson(persJson, Persona.class);
        UserDaoImpl crudPersona = UserDaoImpl.getInstance();
        String msg = crudPersona.creazione(persona);
        return msg;
    }
    
    @Path(value="/leggi")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String leggi(){
        // invoca il dao per salvare
        UserDaoImpl crudPersona = UserDaoImpl.getInstance();
        List<Persona> listaPersone = (List<Persona>) crudPersona.lettura();
        return gson.toJson(listaPersone);
    }
}
