package com.gruppomcr.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gruppomcr.dao.CrudDao;
import com.gruppomcr.dao.model.Persona;
import com.gruppomcr.hibernate.util.HibernateUtil;

public class UserDaoImpl implements CrudDao<Persona> {
	//Dichiaro il costruttore come private
	private UserDaoImpl() {
		
	}
	//Dichiaro l' instanza in metodo Lazy
	private static UserDaoImpl instance = null;
    
    public static UserDaoImpl getInstance() {
    	if (instance == null) {
    		instance = new UserDaoImpl();
    	}
    	return instance;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Persona> lettura(){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE         
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<Persona> persone = new ArrayList<>();
        try(Session session = sessionFactory.openSession();) {     
        	persone = (List<Persona>) session.createQuery("from Persona").list();
        } catch (HibernateException e){             
            e.printStackTrace();
        }
        return persone;     
    }
    
    @Override
    public String creazione(Persona persona){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
    	int id = 0;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            id = (int) session.save(persona);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
            return "Si è verificato un errore nella creazione";
        }
        return (persona.getNome()+" "+persona.getCognome()+" è stato creato con successo con ID = "+id);
    }
    
    @Override
    public String modifica(Persona persona){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            session.update(persona);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
            return "Si è verificato un errore nella modifica";
        }    
        return (persona.getNome()+" "+persona.getCognome()+" è stato modificato con successo");
    }
    
    @Override
    public String eliminazione(int id){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
    	Persona persona = null;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            persona = session.get(Persona.class, id);
            session.delete(persona);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
            return "Si è verificato un errore nell' eliminazione";
        } 
        return (persona.getNome()+" "+persona.getCognome()+" è stato eliminato con successo");
    }
    
    public static void main(String[] args) {
		Persona persona = new Persona();
//		persona.setId(10);
		persona.setNome("Gualtiero");
		persona.setCognome("Brambilla");
		persona.setDataNascita(Date.valueOf("1980-12-12"));
		UserDaoImpl crudPersona = UserDaoImpl.getInstance();
		String msg = crudPersona.creazione(persona);
		System.out.println(msg);
		List<Persona> persone = crudPersona.lettura();
		for (Persona pers : persone) {
			System.out.println(pers.toString());
		}
//		crudPersona.modifica(persona);
//		crudPersona.eliminazione(10);
	}
}
