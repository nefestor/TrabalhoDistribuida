# TrabalhoDistribuida
Trabalho de Distribuida

1 - Configurando o Projeto

Na aba Framework Config:
	
	a)Simulation Area:
		Deve definir a area de simulacao em 2 dimensoes
		As Dimensoes X e Y devem ser 1000
		
	b)Simulation:
		AsynchronousMode > false
                mobility > false
                interference > true
                Os outros parametros nao devem ser alterados
		

	c)Na aba Project Config:
		Nao deve alterar os parametros
		
Apos essas configuracoes deve se executar o projeto

2 - Configurando Sinks e Nós Sensores

	a)Criar Sinks:
		Para iniciar a simulação deve-se criar novos nos do tipo Sink (Circulos Azuis)
		Deve-se criar de 4 nós desse tipo, conforme descrito no trabalho
                Para isso vá até a aba Simulation > Generate Nodes ou apenas aperte F3
		
                Configuração:
                Node Distribution
                
                Number of Nodes: 4
                Distribution Model: Trabalho:DistrbuicaoSinks
                
                Node Properties

                Node Implementation: Trabalho:Sink
                Connectivity: UDG
                Interference Model: Trabalho:InterferenciaAleatoria
                Mobility Model: NoMobility
                Reliability Model: RealiableDelivery
			
	b)Criar Sensores:
		Apos a criacao dos Sinks, deve-se criar novo nos do tipo Sensores (Pontos Escuros)
		Deve-se criar 300 sensores
                Para isso vá até a aba Simulation > Generate Nodes ou apenas aperte F3
		
                Configuração:
                Node Distribution
                
                Number of Nodes: 300
                Distribution Model: Random
                
                Node Properties

                Node Implementation: Trabalho:Sensor
                Connectivity: UDG
                Interference Model: Trabalho:InterferenciaAleatoria
                Mobility Model: NoMobility
                Reliability Model: RealiableDelivery

	c) Executar a simulacao
		Apos a criacao dos nos deve se clicar em Run para iniciar a simulacao
		

	d)Dados da Simulacao
		Por meio da interação do usuario, eh possivel realizar a coleta de dados da simulacao
		Basta clicar com o botão direito sobre um Sink (Circulos Azuis) e escolher a opcao de Dados da simulacao
		
		Pode-se observar os dados coletados da simulacao no quadro ao lado esquerdo, onde podemos ver:
			Numero total de Mensagens de Dados enviadas
			Numero total de Mensagens de Dados recebidas
			Sensores Ativos
			Energia Total Consumida
			Taxa de Mensagens Recebidas por Mensagens Enviadas
			Porcentagem de Sensores Ativos
			Taxa de Energia Consumida por Mensagens Recebidas


3 - Descrição
	
	Os Sinks sao os nos responsaveis por criarem as rotas entre os sensores, para que a partir desta os mesmos possam enviar mensagens
	Estes Sinks sao distribuidos na metade das linhas laterais
	
	Devido a este roteamento e a comunicação entre os sensores, possibilite que estem enviem as mensagens a um Sink, que funciona neste caso como receptor de dados

	Nesta simulacao eh considerada uma interferencia aleatoria, baseada na classe de configuração

4 - Interface do Usuário

	Os Sinks sao os circulos maiores azuis
	
	Os Sensores quando ativos sao pequenos circulos pretos
	Quando desativados por falta de energia ou rota são pequenos circulos vermelhos
	
	Os envelopes azuis são as mensagens de definição de rotas
	
	Os envelopes amarelos sao as mensagens de dados
	
	Os traços entre cada par de nos, quando os mesmo estao proximos sao as conexoes entre os nos
	Quando estas estao pretas, estao em estado ocioso
	Quando estas estao vermelhas signfica que havera a transmissao de uma mensagem por aquela conexao
	
	No quadro de Output quando um Sink recebe uma mensagem de dados eh mostrado os valores da temperatura e umidade relativa de um determinado Sensor, o qual enviou a mensagem de dados.
