package com.gruppomcr.dao;

import java.util.List;

public interface CrudDao<T> {
	//Ritorna una lista di oggetti con i dati provenienti dal db
	List<T> lettura();
	//Crea un inserimento nel db con i dati presenti nell' oggetto
	String creazione(T t);
	//Modifica un inserimento nel db con i dati presenti nell' oggetto
	String modifica(T t);
	//Elimina un inserimento
	String eliminazione(int id);
}
