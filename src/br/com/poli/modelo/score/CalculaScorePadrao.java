package br.com.poli.modelo.score;

import br.com.poli.modelo.partida.*;

 /* Classe abstrata que pega os metodos comuns de calcular o score */
public abstract class CalculaScorePadrao implements CalculaScore {

	protected int soma;
	protected int subtracao;
	protected int multiplicacao;

	public abstract void soma(Partida partida);

	public abstract void subtracao(Partida partida);

	public abstract void multiplicacao(Partida partida);

}