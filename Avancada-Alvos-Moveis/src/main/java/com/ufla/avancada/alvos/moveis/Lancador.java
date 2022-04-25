/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.avancada.alvos.moveis;

import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 *
 * @author decio.mendonca
 */
public class Lancador extends Thread {
    private long id;
    private Pontos posicao;
    private Tiro tiro;
    private Alvos alvo;
    private Semaphore semaforo = new Semaphore(1);
    private boolean mirado;
    private Stack<Municao> carregador = new Stack();
    private boolean caregador_empty=false;
    private boolean preparado=false;
    private final Pontos Pontos;
    
    public Lancador(Alvos alvo_const){
        this.Pontos=new Pontos(400,600);
	this.alvo=alvo_const;
	this.tiro= new Tiro();
	this.carregador.add(new Municao());
        this.carregador.add(new Municao());
        this.carregador.add(new Municao());
        
	start();
    }

   
    @Override
    public long getId(){
      return id;  
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Pontos getPosicao() {
        return posicao;
    }

    public void setPosicao(Pontos posicao) {
        this.posicao = posicao;
    }

    public Tiro getTiro() {
        return tiro;
    }

    public void setTiro(Tiro tiro) {
        this.tiro = tiro;
    }

    public Alvos getAlvo() {
        return alvo;
    }

    public void setAlvo(Alvos alvo) {
        this.alvo = alvo;
    }

    public Semaphore getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaphore semaforo) {
        this.semaforo = semaforo;
    }

    public boolean isMirado() {
        return mirado;
    }

    public void setMirado(boolean mirado) {
        this.mirado = mirado;
    }


    public boolean isCaregador_empty() {
        return caregador_empty;
    }

    public void setCaregador_empty(boolean caregador_empty) {
        this.caregador_empty = caregador_empty;
    }

    public boolean isPreparado() {
        return preparado;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }
    
    public void carregar(){
        try {
		Thread.sleep(30);
			
                this.caregador_empty = this.carregador.isEmpty() != false;
	} catch (InterruptedException e) {
            // TODO Auto-generated catch block

	}
    }
    
    public void preparar() {
			
	try {
		Thread.sleep(30);		
            } catch (InterruptedException e) {
	}
	this.preparado=true;
                
	}
    
    	public void atirar() {
			if( this.preparado == true && this.caregador_empty == false) {
                            this.carregador.remove(carregador.size()-1);
                           
                            tiro.start();
                            this.tiro=new Tiro();
                            this.alvo=null;
                        }

		}
        public void subir_municao(){
            this.carregador.add(new Municao());
        }
        
        public void desce_municao(){
            this.carregador.remove(this.carregador.size()-1);
        }
    @Override
		public void run() {
		
		while(true) {
			try {
				semaforo.acquire();
				try {					
					preparar();
					carregar();
					atirar();
				}
				catch(Exception e) {
				}
			}
			catch(InterruptedException e) {
			}
			
		finally {
			semaforo.release();

		}
            }

	}

		
}
    

