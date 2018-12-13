package projects.sample1.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;

import projects.sample1.Configuracoes;
import projects.sample1.Estatisticas;
import projects.sample1.nodes.messages.MensagemDados;
import projects.sample1.nodes.messages.MensagemRota;
import projects.sample1.nodes.timers.RoundEnvio;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;

public class Sink extends Node{
	
	//contagem do tempo at� o pr�ximo roteamento
	int cont = Configuracoes.tempoRota;
	
	//numero da rota criada pelo sink
	int rota = 0;

	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		drawAsDisk(g, pt, highlight, 15);
		setColor(Color.BLUE);
	}
	
	//trata mensagem de dados recebida pelo Sink
	//e imprime na tela da simula��o
	private void tratarMensagemDados(MensagemDados msg) {
		//adiciona uma mensagem recebida as estatisticas
		Estatisticas.msgsRecebidas++;
		Tools.appendToOutput("n�: " + msg.getIdOrigem() + "   Temp: " + msg.getTemperatura() + "�C   UR: " +msg.getUmidade() + "%\n");
	}
	
	//met�do que permite a aquisi��o dos dados pelo usu�rio a partir da interface
	@NodePopupMethod(menuText = "Dados da Simula��o")
	public void dadosSimulacao(){
		Tools.appendToOutput("Foram Recebidas " + Estatisticas.msgsRecebidas + " mensagens.\n");
		Tools.appendToOutput("Foram Enviadas "+ Estatisticas.msgsEnviadas + " mensagens.\n");
		Tools.appendToOutput("Sensores Ativos " + Estatisticas.sensoresAtivos + "\n");
		Tools.appendToOutput("Energia Consumida " + Estatisticas.energiaConsumida + "\n");
		Tools.appendToOutput("A Taxa de Mensagens Recebidas � "+ Estatisticas.txMsgs() + "\n");
		Tools.appendToOutput("A porcentagem de Sensores Ativos � " + Estatisticas.pcSensores() + "\n");
		Tools.appendToOutput("A Taxa de Energia Consumida por Mensagens Recebidas � " + Estatisticas.txEnergia() + "\n");
	}
	
	//m�todo que contr�i as rotas para um determinado Sink
	public void realizarRoteamento() {
		//define o n�mero da nova rota executada
		rota++;
		//cria uma nova mensagem a ser enviada para os n�s
		MensagemRota m = new MensagemRota(ID,rota);
		//utiliza��o do RoundEnvio para enviar mensagens de rota em broadcast para criar o roteamento
		RoundEnvio timer = new RoundEnvio(m);
		timer.startRelative(1, this);
	}
	
	/*
	 * M�todo utilizado para verificar se � o momento de
	 * executar um novo roteamento
	 */
	public void contaRoteamento(){
		if(cont>0){
			cont--;
		}else{
			realizarRoteamento();
			cont = Configuracoes.tempoRota;
		}
	}
	
	//ignora quando recebe uma mensagem de rota
	private void tratarMensagemRota(MensagemRota msg) {
		
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message msg = inbox.next();
			if (msg instanceof MensagemRota) tratarMensagemRota((MensagemRota) msg);
			else if (msg instanceof MensagemDados) tratarMensagemDados((MensagemDados) msg);
		}
	}

	@Override
	public void preStep() {
		//verifica se � o momento de realizar o roteamento
		contaRoteamento();
	}

	@Override
	public void init() {
		//realiza o roteamento ao iniciar
		realizarRoteamento();
	}

	@Override
	public void neighborhoodChange() {
		
	}

	@Override
	public void postStep() {
		
	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		
	}
	
}
