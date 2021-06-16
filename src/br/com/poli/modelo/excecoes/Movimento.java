package br.com.poli.modelo.excecoes;

/* Interface do metodo executa movimento */
public interface Movimento {
	public void executaMovimento(int x, int y, int valor)
			throws MovimentoInvalidoException, MovimentoIncorretoException;

}
