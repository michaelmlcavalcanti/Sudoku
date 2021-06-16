package br.com.poli.visual;

import br.com.poli.main.Main;
import br.com.poli.modelo.excecoes.MovimentoIncorretoException;
import br.com.poli.modelo.excecoes.MovimentoInvalidoException;
import br.com.poli.modelo.partida.Partida;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CenaSudoku implements Cena {

	private TextField inserirLinha;
	private TextField inserirColuna;
	private TextField inserirValor;
	private Partida partida;
	private Main main;
	private static Timeline tempoJogo;
	private static Label tempo;
	private static KeyFrame KeyFrame1;
	private static KeyFrame KeyFrame2;
	private static int minuto;
	private static int segundo;

	public CenaSudoku(Partida partida, Main main) {
		this.partida = partida;
		this.main = main;
	}

	/* Cena do Sudoku */
	@Override
	public BorderPane exibirCena() {

		BorderPane sudokuTela = new BorderPane();
		sudokuTela.setPrefSize(800, 600);
		sudokuTela.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, white 0%, red 100%);");

		Label titulo;
		sudokuTela.setTop(titulo = new Label("Sudoku"));
		BorderPane.setAlignment(titulo, Pos.CENTER);
		titulo.setStyle("-fx-font-size: 36");

		HBox caixaNome = new HBox(30);
		caixaNome.setAlignment(Pos.CENTER_LEFT);
		caixaNome.setStyle("-fx-font-size: 16");
		caixaNome.getChildren().addAll(new Label("Nome: "), new Label(partida.getJogador().getNome()));

		HBox caixaIdade = new HBox(58);
		caixaIdade.setAlignment(Pos.CENTER_LEFT);
		caixaIdade.setStyle("-fx-font-size: 16");
		caixaIdade.getChildren().addAll(new Label("Idade: "), new Label("" + partida.getJogador().getIdade()));

		HBox caixaDificuldade = new HBox(10);
		caixaDificuldade.setAlignment(Pos.CENTER_LEFT);
		caixaDificuldade.setStyle("-fx-font-size: 16");
		caixaDificuldade.getChildren().addAll(new Label("Dificuldade: "), new Label("" + partida.getDificuldade()));

		HBox caixaScore = new HBox(60);
		caixaScore.setAlignment(Pos.CENTER_LEFT);
		caixaScore.setStyle("-fx-font-size: 16");
		caixaScore.getChildren().addAll(new Label("Score: "), new Label("" + partida.getScore()));

		HBox caixaErros = new HBox(63);
		caixaErros.setAlignment(Pos.CENTER_LEFT);
		caixaErros.setStyle("-fx-font-size: 16");
		caixaErros.getChildren().addAll(new Label("Erros: "), new Label(
				"" + (partida.getDificuldade().getQuantidadeMaximaDeErros() - partida.getQuantidadeDeErros())));

		HBox caixaAjudas = new HBox(51);
		caixaAjudas.setAlignment(Pos.CENTER_LEFT);
		caixaAjudas.setStyle("-fx-font-size: 16");
		caixaAjudas.getChildren().addAll(new Label("Ajudas: "),
				new Label("" + (partida.getDificuldade().getQuantidadeDeAjuda() - partida.getQuantidadeDeAjuda())));

		if (tempo == null) {
			tempo = new Label(+minuto + ":" + segundo);
		}

		HBox caixaTime = new HBox(49);
		caixaTime.setAlignment(Pos.CENTER_LEFT);
		caixaTime.setStyle("-fx-font-size: 16");
		caixaTime.getChildren().addAll(new Label("Tempo: "), tempo);

		VBox caixaDados = new VBox(10);
		caixaDados.setAlignment(Pos.CENTER);
		caixaDados.setStyle("-fx-font-size: 16");
		caixaDados.getChildren().addAll(caixaNome, caixaIdade, caixaDificuldade, caixaScore, caixaErros, caixaAjudas,
				caixaTime);

		HBox visualJogo = new HBox(20);
		visualJogo.setAlignment(Pos.CENTER);
		visualJogo.setStyle("-fx-font-size: 14");

		GridPane grid = new GridPane();
		grid.setPrefWidth(250);

		for (int x = 0; x < partida.getTabuleiro().getGrid().length; x++) {
			for (int y = 0; y < partida.getTabuleiro().getGrid().length; y++) {

				grid.add(new TextField("" + partida.getTabuleiro().getGrid()[x][y]), y, x);
			}
		}

		visualJogo.getChildren().addAll(grid, caixaDados);

		VBox caixaLinha = new VBox();
		caixaLinha.setAlignment(Pos.CENTER);
		caixaLinha.setStyle("-fx-font-size: 14");
		inserirLinha = new TextField();
		inserirLinha.setPrefWidth(50);
		caixaLinha.getChildren().addAll(new Label("Linha "), inserirLinha);

		VBox caixaColuna = new VBox();
		caixaColuna.setAlignment(Pos.CENTER);
		caixaColuna.setStyle("-fx-font-size: 14");
		inserirColuna = new TextField();
		inserirColuna.setPrefWidth(50);
		caixaColuna.getChildren().addAll(new Label("Coluna "), inserirColuna);

		VBox caixaValor = new VBox();
		caixaValor.setAlignment(Pos.CENTER);
		caixaValor.setStyle("-fx-font-size: 14");
		inserirValor = new TextField();
		inserirValor.setPrefWidth(50);
		caixaValor.getChildren().addAll(new Label("Valor "), inserirValor);

		VBox caixaInserir = new VBox();
		caixaInserir.setAlignment(Pos.CENTER);
		caixaInserir.setStyle("-fx-font-size: 14");
		Button inserirJogada = new Button("Inserir");
		caixaInserir.getChildren().addAll(new Label("Confirmar "), inserirJogada);
		inserirJogada.setOnAction(
				e -> executaMovimento(inserirLinha.getText(), inserirColuna.getText(), inserirValor.getText()));

		HBox caixaJogada = new HBox(40);
		caixaJogada.setAlignment(Pos.CENTER);
		caixaJogada.setStyle("-fx-font-size: 14");
		caixaJogada.getChildren().addAll(caixaLinha, caixaColuna, caixaValor, caixaInserir);

		HBox caixaOpcoes = new HBox(10);
		caixaOpcoes.setAlignment(Pos.CENTER);
		caixaOpcoes.setStyle("-fx-font-size: 14");

		Button pedirAjuda = new Button("Ajuda");
		pedirAjuda.setAlignment(Pos.CENTER);
		pedirAjuda.setStyle("-fx-font-size: 14");
		pedirAjuda.setOnAction(e -> pedirAjuda());

		Button desistir = new Button("Desistir");
		desistir.setAlignment(Pos.CENTER);
		desistir.setStyle("-fx-font-size: 14");
		desistir.setOnAction(e -> desistir());

		Button sair = new Button("Sair");
		sair.setAlignment(Pos.CENTER);
		sair.setStyle("-fx-font-size: 14");
		sair.setOnAction(e -> System.exit(0));

		caixaOpcoes.getChildren().addAll(pedirAjuda, desistir, sair);

		VBox caixaAcoes = new VBox(20);
		caixaAcoes.setAlignment(Pos.CENTER);
		caixaAcoes.setStyle("-fx-font-size: 14");

		caixaAcoes.getChildren().addAll(caixaJogada, caixaOpcoes);

		VBox caixaInformacoes = new VBox(10);
		caixaInformacoes.setAlignment(Pos.CENTER);
		caixaInformacoes.setStyle("-fx-font-size: 14");

		Label desenvolvedores = new Label("Desenvolvido por Michael Cavalcanti e Vitor Felix");
		BorderPane.setAlignment(desenvolvedores, Pos.CENTER);
		desenvolvedores.setStyle("-fx-font-size: 16");

		caixaInformacoes.getChildren().addAll(visualJogo, caixaAcoes);

		sudokuTela.setCenter(caixaInformacoes);
		sudokuTela.setBottom(desenvolvedores);

		return sudokuTela;
	}

	/* Tratamento do executa movimento antes de enviar a partida */
	public void executaMovimento(String linha, String coluna, String valor) {
		if ((inserirLinha.getText().trim().isEmpty() || inserirColuna.getText().trim().isEmpty()
				|| inserirValor.getText().trim().isEmpty()) && partida.checarGameOver() == true) {
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
			dialogoInfo.setContentText("Fim de Jogo!");
			dialogoInfo.setHeaderText("");
			dialogoInfo.showAndWait();
		} else if (inserirLinha.getText().trim().isEmpty() || inserirColuna.getText().trim().isEmpty()
				|| inserirValor.getText().trim().isEmpty()) {
			Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);
			dialogoInfo.setContentText("Movimento Inválido!");
			dialogoInfo.setHeaderText("");
			dialogoInfo.showAndWait();
		} else {
			int x = Integer.parseInt(linha);
			int y = Integer.parseInt(coluna);
			int v = Integer.parseInt(valor);
			if (partida.checarGameOver() == true) {

				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
				dialogoInfo.setContentText("Fim de Jogo!");
				dialogoInfo.setHeaderText("");
				dialogoInfo.showAndWait();
			} else {
				try {
					partida.executaMovimento(x, y, v);
					main.chamarCenaSudoku(partida, main);
				} catch (MovimentoInvalidoException e) {
					Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);
					dialogoInfo.setContentText("Movimento Inválido!");
					dialogoInfo.setHeaderText("");
					dialogoInfo.showAndWait();
					main.chamarCenaSudoku(partida, main);
				} catch (MovimentoIncorretoException e) {
					Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
					dialogoInfo.setContentText("Movimento Incorreto!");
					dialogoInfo.setHeaderText("");
					dialogoInfo.showAndWait();
					main.chamarCenaSudoku(partida, main);
				}
			}
		}
	}

	/* Metodo que é chamado quando o jogador pede ajuda */
	private void pedirAjuda() {
		if (partida.checarGameOver() == true) {
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
			dialogoInfo.setContentText("Fim de Jogo!");
			dialogoInfo.setHeaderText("");
			dialogoInfo.showAndWait();
		} else {
			partida.pedirAjuda();
			main.chamarCenaSudoku(partida, main);
		}
	}

	/* Metodo que é chamado quando o jogador aperta o botão desistir */
	private void desistir() {
		if (partida.checarGameOver() == true) {
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
			dialogoInfo.setContentText("Fim de Jogo!");
			dialogoInfo.setHeaderText("");
			dialogoInfo.showAndWait();
		} else {
			partida.resolveTabuleiro();
			partida.getCalculaScore().calcula(partida);
			main.chamarCenaSudoku(partida, main);
		}
	}

	/* Metodo que roda o tempo de jogo do sudoku */
	public void iniciarTempo() {
		KeyFrame1 = new KeyFrame(Duration.ZERO, e -> {
			segundo += 1;
			if (segundo == 60) {
				segundo = 0;
				minuto += 1;
			}
			tempo.setText(+minuto + ":" + segundo);
		});

		KeyFrame2 = new KeyFrame(Duration.seconds(1));

		if (tempoJogo == null) {
			tempoJogo = new Timeline(KeyFrame1, KeyFrame2);
			tempoJogo.setCycleCount(Animation.INDEFINITE);
		}

		if (partida.checarGameOver() == true) {
			tempoJogo.pause();
		} else {
			tempoJogo.play();
		}
	}

}
