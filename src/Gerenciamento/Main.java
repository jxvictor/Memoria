package Gerenciamento;

import java.util.Timer;
import java.util.TimerTask;

import javax.management.InstanceNotFoundException;

public class Main {

    public static void main(String[] args) throws InstanceNotFoundException {
		
    	int numProcessosRemovidos = Processo.getNumeroAleatorio(1, 2);
        int cont = 0;
        final long time = 1000; // a cada 1 segundo = 1000
		Timer timer = new Timer();
		
		while(true) {
			TimerTask tarefa = new TimerTask() {
				private int count = 0;
				double tamTotalProcessosGerados = 0;
				public void run() {
					
					Processo processo;
					processo = GeradorProcessos.gerar();
					System.out.println("PROCESSO CRIADO!");
		        	System.out.println("ID: " + processo.getId());
		        	System.out.println("Tamanho Aloca��o: " + processo.getTamanhoAloc());
		        	System.out.println("Posi��o: " + processo.getPosicao());
		        	System.out.println("----------------------------------------------------");
		        	tamTotalProcessosGerados += processo.getTamanhoAloc();
		        	
					Processo processo1;
					processo1 = GeradorProcessos.gerar();
					System.out.println("PROCESSO CRIADO!");
		        	System.out.println("ID: " + processo1.getId());
		        	System.out.println("Tamanho Aloca��o: " + processo1.getTamanhoAloc());
		        	System.out.println("Posi��o: " + processo1.getPosicao());
		        	System.out.println("----------------------------------------------------");
		        	
		        	tamTotalProcessosGerados += processo1.getTamanhoAloc();
					Memoria memoria = new Memoria();
					memoria.adicionar(processo);
					memoria.adicionar(processo1);
		        	
		        	Memoria.removerAleatorio();
		        	System.out.println(Memoria.getProcessos());

					Algoritmo algoritmo = new Algoritmo();

					int contadorDeProcessos = 0;
					while (contadorDeProcessos < 100) {
						algoritmo.inserirFirstFit(processo);
						algoritmo.inserirNextFit(processo);
						algoritmo.inserirWorstFit(processo);
						algoritmo.inserirBestFit(processo);
						tamTotalProcessosGerados += processo.getTamanhoAloc();

						algoritmo.inserirFirstFit(processo1);
						algoritmo.inserirNextFit(processo1);
						algoritmo.inserirWorstFit(processo1);
						algoritmo.inserirBestFit(processo1);
						tamTotalProcessosGerados += processo1.getTamanhoAloc();
						contadorDeProcessos++;
					}

		        	count++;
		        	tamTotalProcessosGerados ++;
		        	if (count == 100) {
		        		timer.cancel();
		        		System.out.println("-------------RELAT�RIO--------------");
		                System.out.println("Tamanho m�dio dos processos gerados: " + tamTotalProcessosGerados/1000);
						System.out.println("Taxa de descarte: " + algoritmo.getTotalProcessosDescartados());
		        	}
				}
			};
			cont ++;
			if (cont == 2) {
        		break;
        	}
			timer.scheduleAtFixedRate(tarefa, time, time);
		}
    }
}