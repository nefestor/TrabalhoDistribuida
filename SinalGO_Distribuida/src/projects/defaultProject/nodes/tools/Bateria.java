package projects.defaultProject.nodes.tools;

import projects.sample1.Configuracoes;
import projects.sample1.Estatisticas;

//Classe que implementa 
public class Bateria {
	
	//quantidade de energia gasta
	int energiaGasta;
	
	//os m�todos abaixo s�o para realizar o consumo de energia de acordo com a a��o executada
	//Este m�todo � executado quando h� o envio de uma �nica mensagem
	public void consomeEnvia(){
		energiaGasta=energiaGasta+Configuracoes.consumoEnvia;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida+Configuracoes.consumoEnvia;
	}
	
	//Este m�todo � executado quando h� o recebimento de uma mensagem
	public void consomeRecebe(){
		energiaGasta = energiaGasta + Configuracoes.consumoRecebe;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + Configuracoes.consumoRecebe;
	}
	
	//Este m�todo � executado sempre que um n� que contenha bateria est� ativo
	public void consomeLigado(){
		energiaGasta = energiaGasta + Configuracoes.consumoLigado;
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + Configuracoes.consumoLigado;
	}
	
	//Este m�todo � executado quando h� o envio de diversas mensagens por um mesmo n�
	//utilizando o broadcast
	public void consomeBroadcast(int numeronos){
		energiaGasta = energiaGasta + (Configuracoes.consumoEnvia*numeronos);
		Estatisticas.energiaConsumida = Estatisticas.energiaConsumida + (Configuracoes.consumoEnvia*numeronos);
	}
	
	//Este m�todo verifica se ainda h� energia na bateria
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
