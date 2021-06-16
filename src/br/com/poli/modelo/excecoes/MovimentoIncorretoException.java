package br.com.poli.modelo.excecoes;

public class MovimentoIncorretoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7194849412600542308L;

	public MovimentoIncorretoException(String erro) {
		super(erro);
	}
}