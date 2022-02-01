package br.com.beans;

import java.io.Serializable;

public class Caixa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id_caixa;
	int id_veiculo;
	int id_sub;
	char tipo;
	int espMadeira;
	double comprimento, altura, larguraInf, larguraSup, dutoDiamentro,
			dutoComprimento, vInterno, vExterno;
	int chave_teste;
	
	public Caixa(){
		this(0,0,0,'S',0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0);
	}
	
	public Caixa(int id_caixa,int id_veiculo,int id_sub,char tipo,int espMadeira,double comprimento,double altura,double larguraInf,double larguraSup,double dutoDiamentro,
			double dutoComprimento,double vInterno,double vExterno,int chave ){
		this.id_caixa=id_caixa;
		this.id_veiculo=id_veiculo;
		this.id_sub=id_sub;
		this.tipo=tipo;
		this.espMadeira=espMadeira;
		this.comprimento=comprimento;
		this.altura=altura;
		this.larguraInf=larguraInf;
		this.larguraSup=larguraSup;
		this.dutoDiamentro=dutoDiamentro;
		this.dutoComprimento=dutoComprimento;
		this.vInterno=vInterno;
		this.vExterno=vExterno;
		this.chave_teste=chave;
	}

	public int getId_caixa() {
		return id_caixa;
	}

	public void setId_caixa(int id_caixa) {
		this.id_caixa = id_caixa;
	}

	public int getId_veiculo() {
		return id_veiculo;
	}

	public void setId_veiculo(int id_veiculo) {
		this.id_veiculo = id_veiculo;
	}

	public int getId_sub() {
		return id_sub;
	}

	public void setId_sub(int id_sub) {
		this.id_sub = id_sub;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getEspMadeira() {
		return espMadeira;
	}

	public void setEspMadeira(int espMadeira) {
		this.espMadeira = espMadeira;
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

	public double getLarguraInf() {
		return larguraInf;
	}

	public void setLarguraInf(double larguraInf) {
		this.larguraInf = larguraInf;
	}

	public double getLarguraSup() {
		return larguraSup;
	}

	public void setLarguraSup(double larguraSup) {
		this.larguraSup = larguraSup;
	}

	public double getDutoDiamentro() {
		return dutoDiamentro;
	}

	public void setDutoDiamentro(double dutoDiamentro) {
		this.dutoDiamentro = dutoDiamentro;
	}

	public double getDutoComprimento() {
		return dutoComprimento;
	}

	public void setDutoComprimento(double dutoComprimento) {
		this.dutoComprimento = dutoComprimento;
	}

	public double getvInterno() {
		return vInterno;
	}

	public void setvInterno(double vInterno) {
		this.vInterno = vInterno;
	}

	public double getvExterno() {
		return vExterno;
	}

	public void setvExterno(double vExterno) {
		this.vExterno = vExterno;
	}

	public int getChave_teste() {
		return chave_teste;
	}

	public void setChave_teste(int chave_teste) {
		this.chave_teste = chave_teste;
	}
	
	

}
