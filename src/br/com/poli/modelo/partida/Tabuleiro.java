package br.com.poli.modelo.partida;

import java.util.Random;
import br.com.poli.modelo.excecoes.*;

public class Tabuleiro implements Movimento, ResolvedorSudoku {
	private static final int n = 3;
	private int gabarito[][];
	private int grid[][];
	private int backupGrid[][];

	public Tabuleiro() {
		this.gabarito = new int[n * n][n * n];
		this.grid = new int[n * n][n * n];
		this.backupGrid = new int[n * n][n * n];
	}

	/* Metodo que checa se o movimento é válido ou inválido e lança caso tenha erro no movimento */
	public void executaMovimento(int x, int y, int valor)
			throws MovimentoInvalidoException, MovimentoIncorretoException, NumberFormatException {
		if (x < 0 || x > 8 || y < 0 || y > 8 || valor < 1 || valor > 9 || this.grid[x][y] != 0) {
			throw new MovimentoInvalidoException("");
		} else if (valor != this.gabarito[x][y]) {
			throw new MovimentoIncorretoException("");
		} else {
			this.grid[x][y] = valor;
		}
	}

	public boolean isTabuleiroPreenchido() {
		/* Metodo que checa se o tabuleiro já foi totalmente preenchido */
		for (int x = 0; x < this.grid.length; x++) {
			for (int y = 0; y < this.grid.length; y++) {
				if (this.grid[x][y] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public void gerarTabuleiro(DificuldadePartida dificuldade) {

		this.gerarGabarito();
		this.embaralharGabarito();
		this.copiarGabarito();
		this.gerarGrid(dificuldade);
		this.backupGrid();
	}

	public void gerarGabarito() {
		/* Gerando o Gabarito */
		Random random = new Random();
		int valor = random.nextInt(100);
		for (int i = 0; i < n; i++, valor++) {
			for (int j = 0; j < n; j++, valor += n) {
				for (int y = 0; y < n * n; y++, valor++) {
					int x = (n * i) + j;
					gabarito[x][y] = (valor % (n * n)) + 1;
				}
			}
		}
	}

	private void embaralharGabarito() {
		/* Embaralhando o Gabarito */
		Random random = new Random();
		int[][] tabuleiroAuxiliar = new int[1][1];
		for (int embaralhar = 0; embaralhar < gabarito.length; embaralhar++) {
			int embaralhar1 = random.nextInt(3);
			int embaralhar2 = embaralhar1 + random.nextInt(6);
			int auxiliar = 0;
			int e = 0;
			if (gabarito[e][embaralhar1] == gabarito[e + 1][embaralhar2]
					|| gabarito[e][embaralhar1] == gabarito[e + 2][embaralhar2]) {
				for (int x = 0; x < gabarito.length; x++) {
					tabuleiroAuxiliar[auxiliar][auxiliar] = gabarito[x][embaralhar1];
					gabarito[x][embaralhar1] = gabarito[x][embaralhar2];
					gabarito[x][embaralhar2] = tabuleiroAuxiliar[auxiliar][auxiliar];
				}
			}
		}
	}

	private void copiarGabarito() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				this.grid[x][y] = this.gabarito[x][y];
			}
		}
	}

	public void gerarGrid(DificuldadePartida dificuldade) {
		Random random = new Random();
		for (int i = 0; i < dificuldade.getQuantidadeDeEspacosVazios(); i++) {
			int adicionarZeroX = random.nextInt(grid.length);
			int adicionarZeroY = random.nextInt(grid.length);
			if (this.grid[adicionarZeroX][adicionarZeroY] == 0) {
				i--;
			} else {
				this.grid[adicionarZeroX][adicionarZeroY] = 0;
			}
		}
	}

	public void backupGrid() {
		for (int x = 0; x < backupGrid.length; x++) {
			for (int y = 0; y < backupGrid.length; y++) {
				this.backupGrid[x][y] = this.grid[x][y];
			}
		}
	}

	public void chamarBackup() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				this.grid[x][y] = this.backupGrid[x][y];
			}
		}
	}

	public void resolveTabuleiro() {

		for (int x = 0; x < this.grid.length; x++) {
			for (int y = 0; y < this.grid.length; y++) {
				{
					if (this.grid[x][y] == 0) {
						int[] array = embaralharArray();
						for (int i = 0; i < array.length; i++) {
							while (true) {
								boolean linha = this.checarLinha(x, y, array[i]);
								boolean coluna = this.checarColuna(x, y, array[i]);
								boolean grupo = this.checarGrupo(x, y, array[i]);
								if (!linha && !coluna && !grupo) {
									this.inserirValor(x, y, array[i]);
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
		if (!this.isTabuleiroPreenchido()) {
			this.chamarBackup();
			this.resolveTabuleiro();
		}
	}

	public int[] embaralharArray() {
		int array[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random random = new Random();
		for (int s = 0; s < array.length; s++) {
			int trocarArrayX = random.nextInt(array.length);
			int trocarArrayY = random.nextInt(array.length);
			int trocarAuxiliar;
			trocarAuxiliar = array[trocarArrayX];
			array[trocarArrayX] = array[trocarArrayY];
			array[trocarArrayY] = trocarAuxiliar;
		}
		return array;
	}

	public boolean checarLinha(int x, int y, int valorGerado) {
		// Verifica se o valor não está em uma linha
		for (int coluna = 0; coluna < 9; coluna++) {
			if (this.grid[x][coluna] == valorGerado) {
				return true;
			}
		}
		return false;
	}

	public boolean checarColuna(int x, int y, int valorGerado) {
		// Verifica se o valor não está em uma coluna
		for (int linha = 0; linha < this.grid.length; linha++) {
			if (this.grid[linha][y] == valorGerado) {
				return true;
			}
		}
		return false;
	}

	public boolean checarGrupo(int x, int y, int valorGerado) {
		// Verifica se o valor não está em um quadrante
		if (x < 3) {
			if (y < 3) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else if (y > 2 && y < 6) {
				for (int i = 0; i < 3; i++) {
					for (int j = 3; j < 6; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else {
				for (int i = 0; i < 3; i++) {
					for (int j = 6; j < 9; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			}
		} else if (x > 2 && x < 6) {
			if (y < 3) {
				for (int i = 3; i < 6; i++) {
					for (int j = 0; j < 3; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else if (y > 2 && y < 6) {
				for (int i = 3; i < 6; i++) {
					for (int j = 3; j < 6; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else {
				for (int i = 3; i < 6; i++) {
					for (int j = 6; j < 9; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			}
		} else {
			if (y < 3) {
				for (int i = 6; i < 9; i++) {
					for (int j = 0; j < 3; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else if (y > 2 && y < 6) {
				for (int i = 6; i < 9; i++) {
					for (int j = 3; j < 6; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			} else {
				for (int i = 6; i < 9; i++) {
					for (int j = 6; j < 9; j++) {
						if (this.grid[i][j] == valorGerado) {
							return true;
						}
					}
				}
				return false;
			}
		}
	}

	public void inserirValor(int x, int y, int valorGerado) {
		this.grid[x][y] = valorGerado;

	}

	public void pedirAjuda() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if (this.grid[x][y] == 0) {
					this.grid[x][y] = this.gabarito[x][y];
					return;
				}

			}
		}
	}

	public int[][] getGabarito() {
		return this.gabarito;
	}

	public void setGabarito(int[][] gabarito) {
		this.gabarito = gabarito;
	}

	public int[][] getGrid() {
		return this.grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
}
