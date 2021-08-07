package com.gruppomcr.dao.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "animali")
public class Animale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	@Column(name = "nome")
    private String nome;
	@Column(name = "eta")
    private int eta;
	@Column(name = "razza")
    private String razza;
	@Column(name = "data_nascita")
    private Date dataNascita;

    public void setId(int id) {
		this.id = id;
	}

    public void setNome(String nome) {
		this.nome = nome;
	}

    public void setEta(int eta) {
		this.eta = eta;
	}
    
    public void setRazza(String razza) {
		this.razza = razza;
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

    public int getEta(int eta) {
		return eta;
	}
    
    public String getRazza(String razza) {
		return razza;
	}

    public Date getDataNascita() {
		return dataNascita;
	}

	@Override
	public String toString() {
		return "id: " + id + "\t nome: " + nome + "\t eta: " + eta + "\t razza: " + razza + "\t data di nascita: " + dataNascita;
	}
    
}
