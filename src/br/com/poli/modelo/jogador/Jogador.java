package br.com.poli.modelo.jogador;

/* Classe com os dados do jogador, o atributo scoreRecorde n�o foi utilizado, sendo guardado para implementa��es futuras */
public class Jogador extends Pessoa {
	private int scoreRecorde;

	public Jogador(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public int getScoreRecorde() {
		return this.scoreRecorde;
	}

	public void setScoreRecorde(int scoreRecorde) {
		this.scoreRecorde = scoreRecorde;
	}

}