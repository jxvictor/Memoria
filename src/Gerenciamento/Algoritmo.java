package Gerenciamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Algoritmo {

    private List<Processo> firstFitList = new ArrayList<>();
    private List<Processo> nextFitList = new ArrayList<>();
    private List<Processo> bestFitList = new ArrayList<>();
    private List<Processo> worstFitList = new ArrayList<>();
    private int totalProcessosDescartados = 0;
    private static final int TAMANHO_PADRAO = 1000;


    public void inserirFirstFit(Processo processo) {
        int ultimaPos = 0;
        int numEspLivres = 0;
        for (int i = 0; i <= TAMANHO_PADRAO; i++) {
            if (i == TAMANHO_PADRAO) {
                descarte();
            }
            if (Objects.isNull(this.firstFitList.get(i))) {
                numEspLivres++;
                if (numEspLivres == processo.getTamanhoAloc()) {
                    this.firstFitList.add(ultimaPos, processo);
                }
            }
            else {
                numEspLivres = 0;
                ultimaPos = i;
            }
        }
    }

    public void inserirNextFit(Processo processo) {
        int ultimaPos = 0;
        int numEspLivres = 0;
        boolean fim = false;
        for (int i = ultimaPos; i <= TAMANHO_PADRAO; i++) {
            if (i == TAMANHO_PADRAO) {
                i = 0;
                fim = true;
            }
            if (i == ultimaPos && fim) {
                descarte();
            }
            if (Objects.isNull(this.firstFitList.get(i))) {
                numEspLivres++;
                if (numEspLivres == processo.getTamanhoAloc()) {
                    this.nextFitList.add(ultimaPos, processo);
                }
            }
            else {
                numEspLivres = 0;
                ultimaPos = i;
            }
        }
    }

    public void inserirWorstFit(Processo processo) {
        int numEspLivres = 0;
        int espacoMaior = processo.getTamanhoAloc();
        int posicaoEspacoMaior = 0;
        boolean achouPosicaoDisponivel = false;
        for (int i = 0; i < TAMANHO_PADRAO; i++) {
            if (i == TAMANHO_PADRAO - 1 && !achouPosicaoDisponivel) {
                descarte();
            }
            if (Objects.isNull(this.firstFitList.get(i))) {
                numEspLivres++;
                if (numEspLivres >= espacoMaior) {
                    espacoMaior = numEspLivres;
                    posicaoEspacoMaior = i - numEspLivres + 1;
                    achouPosicaoDisponivel = true;
                }
            }
            else {
                numEspLivres = 0;
            }
        }
        if (achouPosicaoDisponivel) {
            this.worstFitList.add(posicaoEspacoMaior, processo);
        }
    }

    public void inserirBestFit(Processo processo) {
        int numEspLivres = 0;
        int espacoMaior = 0;
        int bestFit = TAMANHO_PADRAO;
        int bestPosition = 0;
        boolean achouPosicaoDisponivel = false;
        for (int i = 0; i < TAMANHO_PADRAO; i++) {
            if (i == TAMANHO_PADRAO - 1 && !achouPosicaoDisponivel) {
                descarte();
            }
            if (Objects.isNull(this.firstFitList.get(i))) {
                numEspLivres++;
            }
            else {
                numEspLivres = 0;
            }
            if (numEspLivres >= processo.getTamanhoAloc() && numEspLivres < bestFit) {
                bestFit = numEspLivres;
                //Posição inicial do trecho da memória com o maior espaço livre
                bestPosition = i - numEspLivres + 1;
                achouPosicaoDisponivel = true;
            }
        }
        if (achouPosicaoDisponivel) {
            this.bestFitList.add(bestPosition, processo);
        }

    }

    private void descarte() {
        this.totalProcessosDescartados++;
    }

    public int getTotalProcessosDescartados() {
        return totalProcessosDescartados;
    }
}
