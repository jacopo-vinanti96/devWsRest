package com.gruppomcr.dao.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "persone")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	@Column(name = "nome")
    private String nome;
	@Column(name = "cognome")
    private String cognome;
	@Column(name = "data_nascita")
    private Date dataNascita;

    public void setId(int id) {
		this.id = id;
	}

    public void setNome(String nome) {
		this.nome = nome;
	}

    public void setCognome(String cognome) {
		this.cognome = cognome;
	}

    public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

    public int getId() {
		return id;
	}

    public String getNome() {
		return nome;
	}

    public String getCognome() {
		return cognome;
	}

    public Date getDataNascita() {
		return dataNascita;
	}

	@Override
	public String toString() {
		return "id: " + id + "\t nome: " + nome + "\t cognome: " + cognome + "\t data di nascita: " + dataNascita;
	}
    
}