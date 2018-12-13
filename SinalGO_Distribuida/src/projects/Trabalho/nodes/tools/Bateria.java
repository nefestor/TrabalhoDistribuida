package projects.Trabalho.nodes.tools;

import projects.Trabalho.Configuracoes;
import projects.Trabalho.Estatisticas;

//Classe que implementa 
public class Bateria {
	
	//quantidade de energia gasta
	int energiaGasta;
	
	//os métodos abaixo são para realizar o consumo de energia de acordo com a ação executada
	//Este método é executado quando há o envio de uma única mensagem
	public void consomeEnvia(){
		energiaGasta=energiaGasta+Configuracoes.consumoEnvia;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida+Configuracoes.consumoEnvia;
	}
	
	//Este método é executado quando há o recebimento de uma mensagem
	public void consomeRecebe(){
		energiaGasta = energiaGasta + Configuracoes.consumoRecebe;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + Configuracoes.consumoRecebe;
	}
	
	//Este método é executado sempre que um nó que contenha bateria está ativo
	public void consomeLigado(){
		energiaGasta = energiaGasta + Configuracoes.consumoLigado;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + Configuracoes.consumoLigado;
	}
	
	//Este método é executado quando há o envio de diversas mensagens por um mesmo nó
	//utilizando o broadcast
	public void consomeBroadcast(int numeronos){
		energiaGasta = energiaGasta + (Configuracoes.consumoEnvia*numeronos);
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + (Configuracoes.consumoEnvia*numeronos);
	}
	
	//Este método verifica se ainda há energia na bateria
	public boolean isActive(){
		if(energiaGasta>=Configuracoes.qntEnergia){
			return false;
		}else{
			return true;
		}
	}
	
	public int getEnergiaGasta() {
		return energiaGasta;
	}

	public Bateria(){
		energiaGasta = 0;
	}

}
