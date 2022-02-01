package br.com.beans;

import java.io.Serializable;

public class Veiculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id_veiculo;
	String nome_veiculo;
	char categoria_veiculo;
	double largura;
	double comprimento;
	double altura;
	int chave_teste;

	public Veiculo() {
		this(0, "", 'N', 0.0, 0.0, 0.0,0);
	}

	public Veiculo(int id, String nome, char categoria, double largura,
			double comprimento, double altura,int chave) {
		this.id_veiculo = id;
		this.nome_veiculo = nome;
		this.categoria_veiculo = categoria;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
		this.chave_teste=chave;
	}

	public int getChave_teste() {
		return chave_teste;
	}

	public void setChave_teste(int chave_teste) {
		this.chave_teste = chave_teste;
	}

	public int getId_veiculo() {
		return id_veiculo;
	}

	public void setId_veiculo(int id_veiculo) {
		this.id_veiculo = id_veiculo;
	}

	public String getNome_veiculo() {
		return nome_veiculo;
	}

	public void setNome_veiculo(String nome_veiculo) {
		this.nome_veiculo = nome_veiculo;
	}

	public char getCategoria_veiculo() {
		return categoria_veiculo;
	}

	public void setCategoria_veiculo(char categoria_veiculo) {
		this.categoria_veiculo = categoria_veiculo;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

}
