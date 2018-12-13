package projects.sample1;

/*
 * Classe a qual possui apenas m�todos e atributos est�ticos,
 * possibilitando a coleta de dados para an�lise do simulador
 * 
 * Os m�todos disponibilizam o acesso a taxas e porcentagens dos dados
 * 
 * Os atributos possibilitam o acesso aos dados, al�m disso
 * como s�o estaticos, permitem o incremento ou modifica��o
 * deste mesmos dados a partir das classes utilizadas no simulador
 * 
*/
public final class Estatisticas {

	//quantidade de mensagens recebidas por todos os Sinks
	public static int msgsRecebidas = 0;
	
	/*quantidade de mensagens enviadas por todos os Sensores
	 *
	 * S�o consideradas mensagens enviadas, as mensagens geradas
	 * por cada n�, ou seja, n�o s�o consideradas as mensagens
	 * repassadas de um n� a outro
	 */
	public static int msgsEnviadas = 0;
	
	//quantidade de sensores ativos
	public static int sensoresAtivos = 0;
	
	//numero total de sensores na simula��o
	public static int numeroSensores = 0;
	
	//quantidade de energia consumida por todos os n�s
	public static int energiaConsumida = 0;
	
	//taxa de mensagens recebidas por mensagens enviadas
	public final static double txMsgs(){
		return ((double)Estatisticas.msgsRecebidas/(double)Estatisticas.msgsEnviadas);
	}
	
	//porcentagem de sensores ativos em funcionamento
	//por sensores totais na simula��o
	public final static double pcSensores(){
		return ((double)Estatisticas.sensoresAtivos/(double)Estatisticas.numeroSensores);
	}
	
	//taxa de energia consumida por mensagem recebida
	public final static double txEnergia(){
		return ((double)Estatisticas.energiaConsumida/(double)Estatisticas.msgsRecebidas);
	}
	
}
