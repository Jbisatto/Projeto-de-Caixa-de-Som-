package br.com.beans;

import java.io.Serializable;

public class Equipamento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id_equipamento;
	String nome_equipamento;
	double volume_selado;
	double volume_dutado;
	double diamentro;
	double comprimento;
	int chave_teste;

	public Equipamento() {
		this(0, "", 0.0, 0.0, 0.0, 0.0, 0);
	}

	public int getId_equipamento() {
		return id_equipamento;
	}

	public void setId_equipamento(int id_equipamento) {
		this.id_equipamento = id_equipamento;
	}

	public String getNome_equipamento() {
		return nome_equipamento;
	}

	public void setNome_equipamento(String nome_equipamento) {
		this.nome_equipamento = nome_equipamento;
	}

	public double getVolume_selado() {
		return volume_selado;
	}

	public void setVolume_selado(double volume_selado) {
		this.volume_selado = volume_selado;
	}

	public double getVolume_dutado() {
		return volume_dutado;
	}

	public void setVolume_dutado(double volume_dutado) {
		this.volume_dutado = volume_dutado;
	}

	public double getDiamentro() {
		return diamentro;
	}

	public int getChave_teste() {
		return chave_teste;
	}

	public void setChave_teste(int chave_teste) {
		this.chave_teste = chave_teste;
	}

	public void setDiamentro(double diamentro) {
		this.diamentro = diamentro;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public Equipamento(int id, String nome, double volume_selado,
			double volume_dutado, double diamentro, double comprimento,
			int chave) {
		this.id_equipamento = id;
		this.nome_equipamento = nome;
		this.volume_selado = volume_selado;
		this.volume_dutado = volume_dutado;
		this.diamentro = diamentro;
		this.comprimento = comprimento;
		this.chave_teste=chave;
	}

}
