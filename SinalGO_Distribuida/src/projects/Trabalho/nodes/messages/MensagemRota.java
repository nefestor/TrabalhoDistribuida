package projects.Trabalho.nodes.messages;

import java.awt.Color;

import sinalgo.nodes.messages.Message;


public class MensagemRota extends Message{

	//possibilita a partir deste atributo verificar a distancia do nó ao sink
	private int qntPassos;
	private int idMensagem;
	
	//atributo que identifica qual a rodada de roteamento da mensagem de rota
	private int rota;
	protected int idAnterior;
	protected int idOrigem;
	
	//construtor de mensagem de rota gerada pelo sink
	public MensagemRota(int idOrigem, int rota) {
		this.idOrigem=idOrigem;
		this.idAnterior=idOrigem;
		this.qntPassos = 0;
		this.rota=rota;
	}
	
	//construtor de mensagem de rota gerada pela redistribui��o das mensagens pelos n�s
	protected MensagemRota(int idMensagem, int idOrigem, int idAnterior, int qntPassos, int rota) {
		this.idOrigem=idOrigem;
		this.idAnterior=idAnterior;
		this.idMensagem = idMensagem;
		this.qntPassos = qntPassos+1;
		this.rota = rota;
	}

	@Override
	public Message clone(){
		return new MensagemRota(idMensagem, idOrigem, idAnterior, qntPassos, rota);
	}
	
	public int getQntPassos() {
		return qntPassos;
	}
	
	public void setIdAnterior(int idAnterior) {
		this.idAnterior = idAnterior;
	}

	public int getIdAnterior() {
		return idAnterior;
	}

	public int getIdOrigem() {
		return idOrigem;
	}

	public void setQntPassos(int qntPassos) {
		this.qntPassos = qntPassos;
	}

	public int getIdMensagem() {
		return idMensagem;
	}
	
	
	//verifica se a mensagem contem o mesmo id, se é da mesma rodada de roteamento e se é do mesmo sink
	@Override
	public boolean equals(Object obj){
		if(obj instanceof MensagemRota){
			if(idMensagem==((MensagemRota) obj).getIdMensagem()){
				if(rota==((MensagemRota)obj).getRota()){
					if(idOrigem==((MensagemRota)obj).getIdOrigem()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//verifica se a mensagem contem o mesmo id e se é da mesma rodada de roteamento, independente do sinks
	public boolean equalsBasic(MensagemRota msg){
		if(idMensagem==msg.getIdMensagem()){
			if(rota==msg.getRota()){
				return true;
			}
		}
		return false;
	}

	public int getRota() {
		return rota;
	}

	public void setRota(int rota) {
		this.rota = rota;
	}
	
	//deixa os envelopes de rota na cor Ciano
	@Override
	public Color getEnvelopeColor(){
		return Color.CYAN;
	}
	
}
