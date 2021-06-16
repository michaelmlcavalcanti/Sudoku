package br.com.poli.modelo.excecoes;

public class MovimentoInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581795924084639962L;

	public MovimentoInvalidoException(String erro) {
		super(erro);
	}
}
