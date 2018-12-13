package projects.Trabalho.nodes.timers;

import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Message;
import sinalgo.nodes.timers.Timer;

//Classe que implementa o timer(temporizador) a ser utilizado no projeto
public class RoundEnvio extends Timer{

	//Mensagem a ser enviada em determinado tempo
	private Message mensagem;
	//variável que define se o envio desta mensagem deve ser para todos os nós vizinhos ou apenas a um específico
	private boolean isBroadcast;
	//Nó para qual deve ser enviada a mensagem após o tempo determinado
	private Node destino;
	
	//construtor utilizado com mensagem a ser enviada em broadcast
	public RoundEnvio(Message mensagem) {
		this.mensagem = mensagem;
		this.isBroadcast = true;
	}
	
	//construtor utilizado com mensagem a ser enviada a um nó especifico
	public RoundEnvio(Message mensagem, Node destino){
		this.mensagem = mensagem;
		this.destino = destino;
		this.isBroadcast = false;
	}

	@Override
	public void fire() {
		if(this.isBroadcast){
			node.broadcast(this.mensagem);
		}else{
			node.send(this.mensagem, this.destino);
		}
	}

}
