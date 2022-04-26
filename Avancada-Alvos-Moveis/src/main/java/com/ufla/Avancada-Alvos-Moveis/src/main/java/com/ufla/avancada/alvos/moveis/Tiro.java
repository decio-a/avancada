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
public class Tiro extends Thread {
    private static long totalTiro = 0;
    private long id;
    private int municao;
    private Pontos pontoOrigem;
    private Pontos pontoDestino;
    private Pontos localizacaoAtualizada;
    private long timestamp;
    private long freqAtualizarPosicao = 30;
    private boolean contatoAlvo;
    
     public Tiro() {
        Tiro.totalTiro++;
        this.id = Tiro.totalTiro;
        this.timestamp = System.currentTimeMillis();
        this.pontoOrigem = new Pontos(350, 520);
        this.localizacaoAtualizada = pontoOrigem;
    }

    public static long getTotalTiro() {
        return totalTiro;
    }

    public static void setTotalTiro(long totalTiro) {
        Tiro.totalTiro = totalTiro;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMunicao() {
        return municao;
    }

    public void setMunicao(int municao) {
        this.municao = municao;
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

    public boolean isContatoAlvo() {
        return contatoAlvo;
    }

    public void setContatoAlvo(boolean contatoAlvo) {
        this.contatoAlvo = contatoAlvo;
    }    
    
    
    
    
    public void mover (int x, int y) throws InterruptedException{
        
        //calculando trajeto
        long adj=400;
	long opo=this.getPontoDestino().getY()-this.getPontoOrigem().getY();
        
	double ang=Math.atan(opo/adj);
        
	long mover_x=(long) ((Math.cos(ang))*5);
	long mover_y=(long) ((Math.sin(ang))*5);
        
        //movendo
        
         if (this.getLocalizacaoAtualizada().getX() >= 686  || this.getLocalizacaoAtualizada().getX() <= 14){
            
            this.setLocalizacaoAtualizada(new Pontos((this.getLocalizacaoAtualizada().getX()- mover_x), (this.getLocalizacaoAtualizada().getY()- mover_y)));             
            
        } else {
             
            this.setLocalizacaoAtualizada(new Pontos((this.getLocalizacaoAtualizada().getX()+ mover_x), (this.getLocalizacaoAtualizada().getY()- mover_y)));
         
         }
    }
   
    
    @Override
    	public void run() {
		while(true) {
                    try {
                        mover(this.getPontoDestino().getX(), this.getPontoDestino().getY());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
			if(isContatoAlvo()) {
				break;
			}
			try {
				Tiro.sleep(getFreqAtualizarPosicao());
			} catch (InterruptedException e) {
			}
		}
		
		
	}
}