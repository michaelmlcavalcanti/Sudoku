package br.com.poli.modelo.score;

import br.com.poli.modelo.partida.*;

public class CalculaScoreSemIdade extends CalculaScorePadrao implements CalculaScore {

	/*
	 * O calculo é feito da seguinte forma: ((score do jogador + qtd de jogadas)
	 * - (qts de erros + tempo decorrido do jogo em minutos) * dificuldade da
	 * partida)
	 */
	public void calcula(Partida partida) {

		this.soma(partida);
		this.subtracao(partida);
		this.multiplicacao(partida);
		partida.setScore((this.getSoma() - this.getSubtracao()) * this.getMultiplicacao());
	}

	public void soma(Partida partida) {

		this.setSoma(partida.getScore() + partida.getDificuldade().getQuantidadeMaximaDeErros());
	}

	public void subtracao(Partida partida) {

		this.setSubtracao(partida.getQuantidadeDeErros() + partida.getQuantidadeDeAjuda() + partida.getTempo());
	}

	public void multiplicacao(Partida partida) {

		this.setMultiplicacao(partida.getDificuldade().getDificuldade());
	}

	private int getSoma() {
		return this.soma;
	}

	private void setSoma(int soma) {
		this.soma = soma;
	}

	private int getSubtracao() {
		return this.subtracao;
	}

	private void setSubtracao(int subtracao) {
		this.subtracao = subtracao;
	}

	private int getMultiplicacao() {
		return this.multiplicacao;
	}

	private void setMultiplicacao(int multiplicacao) {
		this.multiplicacao = multiplicacao;
	}

}