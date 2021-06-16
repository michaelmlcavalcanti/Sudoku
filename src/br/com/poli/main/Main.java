package br.com.poli.main;

import br.com.poli.modelo.partida.Partida;
import br.com.poli.visual.CenaDados;
import br.com.poli.visual.CenaInstrucoes;
import br.com.poli.visual.CenaMenu;
import br.com.poli.visual.CenaSudoku;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application {
	private static Stage windows;
	private static Scene menu, instrucoes, informacoes, sudoku;

	@Override
	public void start(Stage primaryStage) throws Exception {

		setWindows(primaryStage);
		setMenu(new Scene(menuCena()));
		windows.setScene(getMenu());

		windows.setTitle("Sudoku");

		windows.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Parent menuCena() {
		CenaMenu cenaMenu = new CenaMenu();
		return cenaMenu.exibirCena();
	}

	public Parent instrucoesCena() {
		CenaInstrucoes cenaInstrucoes = new CenaInstrucoes();
		return cenaInstrucoes.exibirCena();
	}

	public Parent informacoesCena() {
		CenaDados cenaDados = new CenaDados();
		return cenaDados.exibirCena();
	}

	public Parent sudoku(Partida partida, Main main) {
		CenaSudoku cenaSudoku = new CenaSudoku(partida, main);
		cenaSudoku.iniciarTempo();
		return cenaSudoku.exibirCena();
	}

	public void chamarCenaMenu() {
		windows.setScene(getMenu());
	}

	public void chamarCenaInstrucoes() {
		setInstrucoes(new Scene(instrucoesCena()));
		windows.setScene(getInstrucoes());
	}

	public void chamarCenaInformacoes() {
		setInformacoes(new Scene(informacoesCena()));
		windows.setScene(getInformacoes());
	}

	public void chamarCenaSudoku(Partida partida, Main main) {
		setSudoku(new Scene(sudoku(partida, main)));
		windows.setScene(getSudoku());

	}

	private static void setWindows(Stage windows) {
		Main.windows = windows;
	}

	private static Scene getMenu() {
		return menu;
	}

	private static void setMenu(Scene menu) {
		Main.menu = menu;
	}

	private static Scene getInstrucoes() {
		return instrucoes;
	}

	private static void setInstrucoes(Scene instrucoes) {
		Main.instrucoes = instrucoes;
	}

	private static Scene getInformacoes() {
		return informacoes;
	}

	private static void setInformacoes(Scene informacoes) {
		Main.informacoes = informacoes;
	}

	private static Scene getSudoku() {
		return sudoku;
	}

	private static void setSudoku(Scene sudoku) {
		Main.sudoku = sudoku;
	}
}

/**
 * Sudoku
 *
 * @author Michael Cavalcanti
 * @author Vitor Felix version 1.0.5
 */
