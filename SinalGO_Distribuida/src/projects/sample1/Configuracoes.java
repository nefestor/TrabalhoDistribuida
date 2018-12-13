package projects.sample1;

/*
 * Classe que implementa diversos parametros de configura��o
 * necess�rios para execu��o da simula��o
 */
public final class Configuracoes {
	
	//porcentagem do pacote ser entregue, a cada envio de um n� para outro
	public final static int pcInterferencia = 98;
	
	//tempo de espera do Sink para envio de uma nova rota
	public final static int tempoRota = 50;
	
	//quantidade de energia na bateria de cada sensor
	public final static int qntEnergia = 1000;
	
	//gasto de energia da bateria em cada envio de mensagem
	//pelos sensores
	public final static int consumoEnvia = 5;
	
	//gasto de energia da bateria em cada recebimento de mensagem
	//pelos sensores
	public final static int consumoRecebe = 2;
	
	//gasto de energia da bateria em cada round se o sensor estiver ativo
	public final static int consumoLigado = 1;
	
	//tempo minimo de espera para o sensor gerar uma mensagem de dados a ser enviada
	public final static int minEspera = 10;
	
	//tempo maximo de espera para o sensor gerar uma mensagem de dados a ser enviada
	public final static int maxEspera = 40;
	
	//tempo maximo que um sensor � considerado ativo sem receber um novo pacote de rota
	public final static int esperaRotaSensor = (int) (tempoRota + (tempoRota*0.1));
	
	//temperatura m�nima gerada pelo sensor
	public final static int minTemperatura = 0;
	
	//temperatura m�xima gerada pelo sensor
	public final static int maxTemperatura = 35;
	
	//umidade minima gerada pelo sensor
	public final static int minUmidade = 10;
	
	//umidade m�xima gerada pelo sensor
	public final static int maxUmidade = 90;

}
