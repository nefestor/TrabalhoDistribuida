package projects.sample1.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


import projects.sample1.Configuracoes;
import projects.sample1.Estatisticas;
import projects.sample1.nodes.messages.MensagemDados;
import projects.sample1.nodes.messages.MensagemRota;
import projects.sample1.nodes.timers.RoundEnvio;
import projects.defaultProject.nodes.tools.Bateria;
import projects.defaultProject.nodes.tools.Rotas;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class Sensor extends Node {	
	
	
	//gerador de n�mero ale�t�rios respons�vel por gerar o tempo de espera para
	//gerar uma nova mensagem
	Random rand = new Random();
	int cont;
	
	//Bateria (Energia)
	Bateria bateria = new Bateria();
	
	//Classe das rotas dispon�veis a esse Sensor
	Rotas rotas = new Rotas();

	//variavel que define se o Sensor est� ativo
	boolean isActive = true;
	
	//variavel utilizada para verificar o tempo de espera at� o sink
	//poder receber a mensagem a partir da dist�ncia at� o mesmo
	int esperaMsg=Integer.MIN_VALUE+100;
	
	/*
	 * Este m�todo implementa a gera��o de uma nova mensagem de dados
	 * pelo Sensor
	 */
	@NodePopupMethod(menuText = "Enviar Mensagem ao Sink")
	public void geraMensagemDados(){
		//inicia o tempo para a incrementa��o nas mensagens enviadas
		esperaMsg=rotas.getDistanciaSink();
		//realiza a cria��o e o envio de uma nova mensagem de dados
		MensagemDados msg = new MensagemDados(ID);
		RoundEnvio timer = new RoundEnvio(msg, rotas.retornaRota());
		timer.startRelative(1, this);
		//gera um novo tempo para gerar outra mensagem
		cont = rand.nextInt(Configuracoes.maxEspera-Configuracoes.minEspera)+Configuracoes.minEspera;
		bateria.consomeEnvia();
	}
	
	/*
	 * Este m�todo verifica se o tempo para gera��o de uma nova
	 * mensagem j� foi atingido
	 */
	public void enviaMensagemDados(){
		if(rotas.possuiRota()&&isActive){
			if(cont==0){
				geraMensagemDados();
			}
			cont--;
		}
	}

	/*
	 * Este m�todo realiza o reencaminhamento das mensagens de dados recebidas
	 */
	public void tratarMensagemDados(MensagemDados msg){
		if((rotas.possuiRota()) && (isActive)){
			bateria.consomeEnvia();
			send(msg, rotas.retornaRota());
		}
	}
	
	/*
	 * Este m�todo � utilizado para tratar mensagens de rota
	 * 
	 */
	public void tratarMensagemRota(MensagemRota msg){
		if(isActive){
			//verifica se o Sensor j� recebeu est� mensagem de rota
			if(!rotas.passouRota(msg)){
				//verifica se o Sensor j� recebeu uma mensagem de rota nesta rodada de roteamento
				if(!rotas.passouRodada(msg)){
					if(!rotas.possuiRota()){
						//inicializa o atributo para in�cio da contagem para gerar novas mensagens de dados
						cont = rand.nextInt(Configuracoes.maxEspera-Configuracoes.minEspera)+Configuracoes.minEspera;
						//incrementa o numero de sensores ativos
						Estatisticas.sensoresAtivos++;
					}
					msg.setIdAnterior(ID);
					//envia a mensagem de rota a todos os n�s vizinhos
					broadcast(msg);
					//implementa uma nova rota
					rotas = new Rotas(msg,this.outgoingConnections.iterator());
					bateria.consomeBroadcast(this.outgoingConnections.size());
				}else{
					rotas.adicionaNo(msg);
				}
				//inicia a espera por uma nova rota
				rotas.iniciaEspera();
			}
		}
	}
	
	public Rotas getRotas() {
		return rotas;
	}

	/*
	 * Este m�todo � implementado para realizar uma verifica��o coerente
	 * dos dados obtidos da taxa de mensagens recebidas, onde ap�s o envio
	 * da mensagem pelo sensor, � aguardado a dist�ncia do Sensor ao Sink
	 * para s� ap�s passado esse tempo, sem incrementado o n�mero de mensagens
	 * enviadas
	 * 
	 */
	public void recebeuMensagem(){
		if(esperaMsg==0){
			Estatisticas.msgsEnviadas++;
		}
		esperaMsg--;
	}
	
	/*
	 * Neste m�todo � verifica os requisitos para o metodo ser considerado ativo:
	 * Ter Bateria
	 * Possuir uma rota ativa
	 * 
	 */
	public void desativarSensor(){
		if((isActive)&&(!bateria.isActive()||!rotas.rotaAtiva())){
			//retira dos dados
			Estatisticas.sensoresAtivos--;
			rotas.semRota();
			isActive = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sinalgo.nodes.Node#draw(java.awt.Graphics, sinalgo.gui.transformation.PositionTransformation, boolean)
	 * 
	 * define a cor do n�
	 */
	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		drawAsDisk(g, pt, highlight, 6);
		setColor(Color.BLACK);
		//se um n� � inativo, muda a cor do n�
		if(!isActive){
			setColor(Color.RED);
		}
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			 Message msg = inbox.next();
			 if(isActive){
				 if (msg instanceof MensagemRota) tratarMensagemRota((MensagemRota) msg);
				 else if (msg instanceof MensagemDados) tratarMensagemDados((MensagemDados) msg);
				 bateria.consomeRecebe();
			 }
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sinalgo.nodes.Node#preStep()
	 * 
	 * Neste m�todo est�o implementadas diversas l�gicas para o funcionamento
	 * do Sensor
	 * 
	 * H� a realiza��o do consumo de energia quando o Sensor est� ativo
	 * H� a realiza��o da contagem de tempo para obter uma nova rota
	 */
	@Override
	public void preStep() {
		if(isActive){
			bateria.consomeLigado();
			rotas.esperaNovaRota();
		} 
		recebeuMensagem();
	}

	@Override
	public void init() {
		//incrementa o numero total de sensores ao iniciar cada sensor
		Estatisticas.numeroSensores++;
	}

	@Override
	public void neighborhoodChange() {

	}

	/*
	 * (non-Javadoc)
	 * @see sinalgo.nodes.Node#postStep()
	 * 
	 * Neste m�todo, que � chamada a cada round, � realizada a chamada
	 * de dois m�todos essenciais a l�gica do simulador
	 * 
	 */
	@Override
	public void postStep() {
		enviaMensagemDados();
		desativarSensor();
	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {

	}
	
	public Bateria getBateria() {
		return bateria;
	}

}
