package model;

import java.util.Date;

public class Score {

	private int tentativas;
	private float aproveitamento;
	private int erros;
	private int ordem;
	private int acertos;
	private Date data;
	private User user;
	private Assembly assembly;
	
	public Score (int erros, int ordem, int acertos, User user, Assembly assembly) {
		this.erros = erros;
		this.acertos = acertos;
		tentativas = erros + acertos;
		aproveitamento = acertos / tentativas;
		this.user = user;
		this.assembly = assembly;
		data = this.getData();		
		
	}
	
	public int getTentativas() {
		return tentativas;
	}

	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}

	public float getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(float aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		this.erros = erros;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	
	
	
	
	
}
