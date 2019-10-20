package com.hdbapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblmedico")
public class Medico {
	
	
	@Id
	private long Idmedico;
	private String Medico;
	private String Nomedr;
	private String Nomecompleto;
	private String CRM;
	private int Especialidade;
	private boolean Ativo;
	private String Cpfmed;
	
	
	public long getIdmedico() {
		return Idmedico;
	}
	public void setIdmedico(long idmedico) {
		Idmedico = idmedico;
	}
	public String getMedico() {
		if(Medico ==null) {
		return "0";
		}
		return Medico;
	}
	public void setMedico(String medico) {
		Medico = medico;
	}
	public String getNomedr() {
		return Nomedr;
	}
	public void setNomedr(String nomedr) {
		Nomedr = nomedr;
	}
	public String getNomecompleto() {
		return Nomecompleto;
	}
	public void setNomecompleto(String nomecompleto) {
		Nomecompleto = nomecompleto;
	}
	public String getCRM() {
		
		if(CRM == null) {
			return "0";
		}
		return CRM;
	}
	public void setCRM(String cRM) {
		CRM = cRM;
	}
	public int getEspecialidade() {
		return Especialidade;
	}
	public void setEspecialidade(int especialidade) {
		Especialidade = especialidade;
	}
	public boolean isAtivo() {
		return Ativo;
	}
	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}
	public String getCpfmed() {
		
		if(Cpfmed == null) {
			return "0";
		}
		return Cpfmed;
	}
	public void setCpfmed(String cpfmed) {
		Cpfmed = cpfmed;
	}

	
	
	

}