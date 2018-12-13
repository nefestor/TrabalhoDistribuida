package projects.sample1.models.distributionModels;

import sinalgo.configuration.Configuration;
import sinalgo.models.DistributionModel;
import sinalgo.nodes.Position;

//classe que implementa a distribui��o dos sinks para o problema a ser resolvido
public class DistribuicaoSinks extends DistributionModel{
	
	int numeroSink=0;
	
	//este m�todo implementa a distribui��o dos sinks deste projeto, de acordo com o determinado pelo cen�rio
	//como este trabalho tem como objetivo trabalhar com no m�ximo 4 Sinks, foi implementada o posicionamento de 4 Sinks
	public Position getNextPosition(){
		switch(numeroSink){
			case 0:{
				numeroSink++;
				return new Position(20,Configuration.dimY/2,0);
			}
			case 1:{
				numeroSink++;
				return new Position(Configuration.dimX-20,Configuration.dimY/2,0);
			}
			case 2:{
				numeroSink++;
				return new Position(Configuration.dimX/2,20,0);
			}
			case 3:{
				numeroSink++;
				return new Position(Configuration.dimX/2,Configuration.dimY-20,0);
			}
			default:return new Position(Configuration.dimX/2,Configuration.dimY-20,0);
		}
		
			
	}

}
