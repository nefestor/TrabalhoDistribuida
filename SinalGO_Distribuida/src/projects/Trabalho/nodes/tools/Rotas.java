package projects.Trabalho.nodes.tools;

import java.util.ArrayList;
import java.util.List;

import projects.Trabalho.Configuracoes;
import projects.Trabalho.nodes.messages.MensagemRota;
import projects.Trabalho.nodes.nodeImplementations.Sensor;
import sinalgo.nodes.Node;
import sinalgo.nodes.edges.Edge;
import sinalgo.tools.Tools;
import sinalgo.tools.storage.ReusableListIterator;

public class Rotas {

	//Lista de rotas poss�veis com a menor dist�ncia a um Sink
	List<Sensor> nosRota;
	
	//Lista das Mensagens de Rota recebidas
	List<MensagemRota> mensagensRotasRecebidas = new ArrayList<>();

	//dist�ncia do nó até o Sink
	int distanciaSink = Integer.MAX_VALUE;
	
	//Rotas ativas ou não
	boolean active = false;

	//Salva o sink, se o nó o qual a rota pertence tiver conexao com o Sink
	Node sink;
	
	//Tempo de espera para que as rotas sejam desativadas se não receber uma rota no tempo estipulado
	int esperaRota;
	
	public Rotas(){
		//estipula o tempo máximo de espera para uma mensagem de rota
		esperaRota = Configuracoes.esperaRotaSensor;
	}
	
	/*
	 * Construtor que gera as novas rotas considerando as rotas que possuem
	 * a mesma mínima distância ao Sink
	 */
	public Rotas(MensagemRota msg, ReusableListIterator<Edge> edges){
		//dist�ncia até o Sink
		this.distanciaSink = msg.getQntPassos();
		this.nosRota = new ArrayList<Sensor>();
		this.mensagensRotasRecebidas.add(msg);
		//ativas as rotas
		this.active = true;
		//verifica a que Sink pertence está rota
		this.sink = Tools.getNodeByID(msg.getIdOrigem());
		Edge edge;
		Sensor sensor;
		int menorDist = Integer.MAX_VALUE;
		
		//verifica qual a menor distância ao Sink, de acordo com os nós vizinhos
		while(edges.hasNext()){
			edge = edges.next();
			if(edge.endNode instanceof Sensor){
				sensor = (Sensor)edge.endNode;
				if(menorDist>sensor.getRotas().getDistanciaSink()&&sensor.getBateria().isActive()){
					menorDist=sensor.getRotas().getDistanciaSink();
				}
			}
		}
		edges.reset();
		//Adiciona os nós de mesma distância mínima ao Sink a Lista de rotas
		while(edges.hasNext()){
			edge = edges.next();
			if(edge.endNode instanceof Sensor){
				sensor = (Sensor)edge.endNode;
				if(sensor.getRotas().getDistanciaSink()==menorDist){
					nosRota.add(sensor);
				}
			}
		}
	}
	
	/*
	 * Verifica quando há um nó com a mesma distância dos outros nós
	 * de menor distância a um Sink, e adiciona este as rotas
	 */
	public void adicionaNo(MensagemRota msg){
		if(distanciaSink==msg.getQntPassos()){
			nosRota.add((Sensor) Tools.getNodeByID(msg.getIdAnterior()));
		}
		mensagensRotasRecebidas.add(msg);
	}
	
	//Inicia novamente a espera de uma nova mensagem de rota
	public void iniciaEspera(){
		esperaRota = Configuracoes.esperaRotaSensor;
	}
	
	/*
	 * Este método disponibiliza o Nó a ser enviada uma mensagem
	 * a partir da distancia ao Sink
	 */
	public Node retornaRota(){
		if(distanciaSink==1){
			return sink;
		}else{
			return melhorSensor();
		}
	}
	
	//desativa as rotas
	public void semRota(){
		active = false;
	}
	
	/*
	 * Método que verifica o melhor Sensor para realizar uma transmissão
	 * baseando no nó de menor gasto de bateria
	 */
	private Sensor melhorSensor(){
		Sensor melhorSensor=new Sensor();
		int maxVida = Integer.MAX_VALUE;
		for(Sensor no:nosRota){
				if(maxVida>=((Sensor) no).getBateria().getEnergiaGasta()){
					maxVida=((Sensor) no).getBateria().getEnergiaGasta();
					melhorSensor=(Sensor) no;
				}
		}
		return melhorSensor;
	}
	
	/*
	 * Método que verifica se o Sensor ja recebeu essa mensagem de rota
	 * considerando a mensagem, a rodada de roteamento e o Sink que 
	 * enviou a rota
	 */
	public boolean passouRota(MensagemRota msg){
		for(MensagemRota m:mensagensRotasRecebidas){
			if(msg.equals(m)){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Método que verifica se o Sensor ja recebeu uma mensagem de rota
	 * de algum dos Sink's nesta rodada de roteamento
	 */
	public boolean passouRodada(MensagemRota msg){
		for(MensagemRota m:mensagensRotasRecebidas){
			if(msg.equalsBasic(m)){
				return true;
			}
		}
		return false;
	}
	
	//verifica se existe rota ativa ou não
	public boolean rotaAtiva(){
		if(esperaRota<=0){
			return false;
		}else{
			return true;
		}
	}
	
	//realiza um decremento no tempo de espera da rota
	public void esperaNovaRota(){
		esperaRota--;
	}


	public int getDistanciaSink() {
		return distanciaSink;
	}
	
	public boolean possuiRota() {
		return active;
	}
	
}
