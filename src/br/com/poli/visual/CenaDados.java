package br.com.poli.visual;

import br.com.poli.controle.ControleConfirmar;
import br.com.poli.controle.ControleVoltar;

import br.com.poli.modelo.partida.DificuldadePartida;
import br.com.poli.modelo.score.CalculaScoreComIdade;
import br.com.poli.modelo.score.CalculaScorePadrao;
import br.com.poli.modelo.score.CalculaScoreSemIdade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CenaDados implements Cena {

	private DificuldadePartida dificuldade;
	private CalculaScorePadrao score;
	private TextField inserirJogador;
	private TextField inserirIdade;
	private RadioButton escolherFacil;
	private RadioButton escolherMedio;
	private RadioButton escolherDificil;
	private ToggleGroup selecionarDificuldade;
	private RadioButton escolherSim;
	private RadioButton escolherNao;
	private ToggleGroup selecionarCalcular;

	/* Cena Das Informações */
	@Override
	public BorderPane exibirCena() {
		BorderPane informacoesTela = new BorderPane();
		informacoesTela.setPrefSize(800, 600);
		informacoesTela.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, white 0%, red 100%);");

		Label titulo;

		informacoesTela.setTop(titulo = new Label("Dados"));
		BorderPane.setAlignment(titulo, Pos.CENTER);
		titulo.setStyle("-fx-font-size: 36");

		HBox nomeJogador = new HBox(20);
		inserirJogador = new TextField();
		nomeJogador.setAlignment(Pos.CENTER);
		nomeJogador.getChildren().addAll(new Label("Nome do Jogador:"), inserirJogador);

		HBox idadeJogador = new HBox(170);
		inserirIdade = new TextField();
		inserirIdade.setPrefWidth(50);
		idadeJogador.setAlignment(Pos.CENTER);
		idadeJogador.getChildren().addAll(new Label("Idade do Jogador: "), inserirIdade);

		selecionarDificuldade = new ToggleGroup();
		HBox escolherDificuldade = new HBox(20);
		escolherDificuldade.setAlignment(Pos.CENTER);
		escolherFacil = new RadioButton("Fácil");
		escolherFacil.setOnAction(e -> setDificuldade(DificuldadePartida.FACIL));
		escolherMedio = new RadioButton("Médio");
		escolherMedio.setOnAction(e -> setDificuldade(DificuldadePartida.MEDIO));
		escolherDificil = new RadioButton("Difícil");
		escolherDificil.setOnAction(e -> setDificuldade(DificuldadePartida.DIFICIL));
		selecionarDificuldade.getToggles().addAll(escolherFacil, escolherMedio, escolherDificil);
		escolherDificuldade.getChildren().addAll(new Label("Dificuldade: "), escolherFacil, escolherMedio,
				escolherDificil);

		HBox escolherScore = new HBox(16);
		escolherScore.setAlignment(Pos.CENTER);
		escolherSim = new RadioButton("Com Idade");
		escolherSim.setOnAction(e -> setScore(new CalculaScoreComIdade()));
		escolherNao = new RadioButton("Sem Idade");
		escolherNao.setOnAction(e -> setScore(new CalculaScoreSemIdade()));
		selecionarCalcular = new ToggleGroup();
		selecionarCalcular.getToggles().addAll(escolherSim, escolherNao);
		escolherScore.getChildren().addAll(new Label("Calcular Score: "), escolherSim, escolherNao);

		VBox conjuntoDados = new VBox(20);
		conjuntoDados.setAlignment(Pos.CENTER);
		conjuntoDados.setStyle("-fx-font-size: 16");
		conjuntoDados.getChildren().addAll(nomeJogador, idadeJogador, escolherDificuldade, escolherScore);

		HBox opcoes = new HBox(20);
		opcoes.setAlignment(Pos.CENTER);
		opcoes.setStyle("-fx-font-size: 16");

		Button confirmar;
		informacoesTela.setBottom(confirmar = new Button("Confirmar"));
		confirmar.setStyle("-fx-font-size: 16");
		confirmar.setOnAction(Confirmar());

		Button voltar;
		informacoesTela.setBottom(voltar = new Button("Voltar"));
		voltar.setStyle("-fx-font-size: 16");
		voltar.setOnAction(e -> new ControleVoltar());

		Label desenvolvedores = new Label("Desenvolvido por Michael Cavalcanti e Vitor Felix");
		BorderPane.setAlignment(desenvolvedores, Pos.CENTER);
		desenvolvedores.setStyle("-fx-font-size: 16");

		VBox dev = new VBox(20);
		dev.setAlignment(Pos.CENTER);
		dev.setStyle("-fx-font-size: 16");
		dev.getChildren().addAll(opcoes, desenvolvedores);

		opcoes.getChildren().addAll(confirmar, voltar);

		informacoesTela.setCenter(conjuntoDados);
		informacoesTela.setBottom(dev);

		return informacoesTela;
	}

	private EventHandler<ActionEvent> Confirmar() {
		return e -> {
			try {
				new ControleConfirmar(inserirJogador.getText(), getDificuldade(), getScore(), inserirIdade.getText());
			} catch (IllegalArgumentException erro) {
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setContentText("Dados Inválidos! Motivo: " + erro.getMessage());
				dialogoInfo.setHeaderText("");
				dialogoInfo.showAndWait();
			}
		};
	}

	private DificuldadePartida getDificuldade() {
		return dificuldade;
	}

	private void setDificuldade(DificuldadePartida dificuldade) {
		this.dificuldade = dificuldade;
	}

	private CalculaScorePadrao getScore() {
		return score;
	}

	private void setScore(CalculaScorePadrao score) {
		this.score = score;
	}

}
