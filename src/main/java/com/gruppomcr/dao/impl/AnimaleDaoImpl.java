package com.gruppomcr.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gruppomcr.dao.CrudDao;
import com.gruppomcr.dao.model.Animale;
import com.gruppomcr.hibernate.util.HibernateUtil;

public class AnimaleDaoImpl implements CrudDao<Animale> {
	//Dichiaro il costruttore come private
	private AnimaleDaoImpl() {
		
	}
	//Dichiaro l' instanza in metodo Lazy
	private static AnimaleDaoImpl instance = null;
    
    public static AnimaleDaoImpl getInstance() {
    	if (instance == null) {
    		instance = new AnimaleDaoImpl();
    	}
    	return instance;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Animale> lettura(){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE         
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<Animale> animali = new ArrayList<>();
        try(Session session = sessionFactory.openSession();) {             
        	animali = (List<Animale>) session.createQuery("from Animale").list();
        } catch (HibernateException e){             
            e.printStackTrace();
        }
        return animali;     
    }
    
    @Override
    public String creazione(Animale animale){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
    	int id = 0;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            id = (int) session.save(animale);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
        }
        return (animale.getNome()+" è stato creato con successo con ID = "+id);
    }
    
    @Override
    public String modifica(Animale animale){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            session.update(animale);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
        }    
        return (animale.getNome()+" è stato modificato con successo");
    }
    
    @Override
    public String eliminazione(int id){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Transaction tx = null;
    	Animale animale = null;
        try(Session session = sessionFactory.openSession();) {             
            tx = session.beginTransaction();
            animale = session.get(Animale.class, id);
            session.delete(animale);
            tx.commit();
        } catch (HibernateException e){       
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
            e.printStackTrace();
        } 
        return (animale.getNome()+" è stato eliminato con successo");
    }

	public static void main(String[] args) {
		Animale animale = new Animale();
//		animale.setId(1);
		animale.setNome("Doge");
		animale.setRazza("Cane");
		animale.setEta(3);
		animale.setDataNascita(Date.valueOf("2018-06-12"));
		AnimaleDaoImpl crudAnimale = AnimaleDaoImpl.getInstance();
		String msg = crudAnimale.creazione(animale);
		List<Animale> animali = crudAnimale.lettura();
		System.out.println(msg);
		for (Animale anim : animali) {
			System.out.println(anim.toString());
		}
//		crudAnimale.modifica(animale);
//		crudAnimale.eliminazione(1);

	}

}
