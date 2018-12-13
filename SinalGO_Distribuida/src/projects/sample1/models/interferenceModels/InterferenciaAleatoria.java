package projects.sample1.models.interferenceModels;

import java.util.Random;

import projects.sample1.Configuracoes;
import sinalgo.models.InterferenceModel;
import sinalgo.nodes.messages.Packet;

//classe que implementa um modelo de interferencia aleat�ria
public class InterferenciaAleatoria extends InterferenceModel{

	Random random = new Random();
	
	//neste m�todo � implementado a defini��o se um pacote sofre ou n�o interfer�ncia
	// a partir da gera��o de um numero aleat�rio de 0 a 100
	// onde se este for maior que a porcentagem definida na configuracao de interferencia
	@Override
	public boolean isDisturbed(Packet p) {
		if(random.nextInt(100)>Configuracoes.pcInterferencia){
			return true;
		}else{
			return false;
		}
	}

}
