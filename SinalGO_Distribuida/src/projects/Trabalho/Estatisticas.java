package projects.Trabalho;

/*
 * Classe a qual possui apenas métodos e atributos estáticos,
 * possibilitando a coleta de dados para análise do simulador
 * 
 * Os métodos disponibilizam o acesso a taxas e porcentagens dos dados
 * 
 * Os atributos possibilitam o acesso aos dados, além disso
 * como são estaticos, permitem o incremento ou modificação
 * deste mesmos dados a partir das classes utilizadas no simulador
 * 
*/
public final class Estatisticas {

	//quantidade de mensagens recebidas por todos os Sinks
	public static int msgsRecebidas = 0;
	
	/*quantidade de mensagens enviadas por todos os Sensores
	 *
	 * São consideradas mensagens enviadas, as mensagens geradas
	 * por cada nó, ou seja, não são consideradas as mensagens
	 * repassadas de um nó a outro
	 */
	public static int msgsEnviadas = 0;
	
	//quantidade de sensores ativos
	public static int sensoresAtivos = 0;
	
	//numero total de sensores na simulação
	public static int numeroSensores = 0;
	
	//quantidade de energia consumida por todos os nós
	public static int energiaConsumida = 0;
	
	//taxa de mensagens recebidas por mensagens enviadas
	public final static double txMsgs(){
		return ((double)Estatisticas.msgsRecebidas/(double)Estatisticas.msgsEnviadas);
	}
	
	//porcentagem de sensores ativos em funcionamento
	//por sensores totais na simulação
	public final static double pcSensores(){
		return ((double)Estatisticas.sensoresAtivos/(double)Estatisticas.numeroSensores);
	}
	
	//taxa de energia consumida por mensagem recebida
	public final static double txEnergia(){
		return ((double)Estatisticas.energiaConsumida/(double)Estatisticas.msgsRecebidas);
	}
	
}
