/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.avancada.alvos.moveis;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author decio.mendonca
 */
public class Alvos extends Thread {
    private static long totalAlvos = 0;
    private long id;
    private Pontos pontoOrigem;
    private Pontos pontoDestino;
    private Pontos localizacaoAtualizada;
    private long timestamp;
    private long freqAtualizarPosicao;
    private boolean chegouDestino;
    private boolean atingido;
    
    private int passo; // quantos passo tem
    private int passo_min; // 
    private int passo_max;
    private int k;
    private double t_passo;
  
    
    private int[] pos = new int[] {0, 0, 0, 0, 0, 0, 600};
    private double[] dp = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.02};
    private double[] v = new double[] {0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0,0004};
    private double[] A = new double[] {1,1,1,1,1,-1};
    
    
    
    public Alvos(Pontos pontoOrigem, Pontos pontoDestino) {
        Alvos.totalAlvos++;
        this.id = Alvos.totalAlvos;
        this.timestamp = System.currentTimeMillis();
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.localizacaoAtualizada = pontoOrigem;
        this.freqAtualizarPosicao = 30;
        
        this.passo = 100;
        this.passo_max = 110;
        this.passo_min = 90;
        this.k = 0;
        this.t_passo  = 1666.666;
        
        
        for (int i = 0; i < 6; i++) {
            this.pos[i]=(int)Math.floor(Math.random()*(passo_max-passo_min+1)+passo_min);
        }
        
        start();        
    }

    public static long getTotalAlvos() {
        return totalAlvos;
    }

    public static void setTotalAlvos(long totalAlvos) {
        Alvos.totalAlvos = totalAlvos;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pontos getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(Pontos pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public Pontos getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(Pontos pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public Pontos getLocalizacaoAtualizada() {
        return localizacaoAtualizada;
    }

    public void setLocalizacaoAtualizada(Pontos localizacaoAtualizada) {
        this.localizacaoAtualizada = localizacaoAtualizada;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getFreqAtualizarPosicao() {
        return freqAtualizarPosicao;
    }

    public void setFreqAtualizarPosicao(long freqAtualizarPosicao) {
        this.freqAtualizarPosicao = freqAtualizarPosicao;
    }

    public boolean isChegouDestino() {
        return chegouDestino;
    }

    public void setChegouDestino(boolean chegouDestino) {
        this.chegouDestino = chegouDestino;
    }

    public boolean isAtingido() {
        return atingido;
    }

    public void setAtingido(boolean atingido) {
        this.atingido = atingido;
    }
    
    public void mover () throws InterruptedException{

        // encontrar o passo que eu estou
        Reconciliation Rec = new Reconciliation(pos, v, A);
        Rec.printMatrix(Rec.getReconciledFlow());
        int vel = Rec.getReconciledFlow()[k]/this.t_passo; //fator de correção
        
         if ((int)this.getLocalizacaoAtualizada().getY() >= (int)this.getPontoDestino().getY() || atingido == true){
            
            this.chegouDestino = true;

        } else {
         Alvos.sleep(this.getFreqAtualizarPosicao());
         this.setLocalizacaoAtualizada(new Pontos(this.getPontoOrigem().getX(), (int) (this.getLocalizacaoAtualizada().getY()+ vel)) );
           //Ta andando
         }
    }
    
    @Override
    public void run() {
	while(true) {
			
		try {
			Alvos.sleep(30);
		} catch (InterruptedException e) {
                    // TODO Auto-generated catch block

		}
            try {
                mover();
            } catch (InterruptedException ex) {
                Logger.getLogger(Alvos.class.getName()).log(Level.SEVERE, null, ex);
            }
		if(isAtingido() || isChegouDestino()) {
			break;
		}
	}
  
}}
