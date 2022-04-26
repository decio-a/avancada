/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.avancada.alvos.moveis;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author decio.mendonca
 */
public class Interface extends JPanel implements Runnable{
    
        int x=30;
        int y= 10;
        int width = 50;
        int height = 50;
        int vel;
        int max_x, max_y;
        private ArrayList<Alvos> alvo = new ArrayList<>();
	private ArrayList<Lancador> lancadores = new ArrayList<>();
	private ArrayList<Tiro> tiros = new ArrayList<>();

    
     Interface(int w, int h){
         this.max_x=w;
         this.max_y=h;
        
                  
     }
        
     public void add_alvo() {
		try {
			Thread.sleep(10);
		
		} catch (InterruptedException e) {
		}
    	alvo.add(new Alvos(new Pontos(30,0), new Pontos(150,600)));
        tiros.add(new Tiro());
        
        }
      
    @Override
    public void paint (Graphics g){
        super.paint(g);
         for (int i = 0; i < this.alvo.size(); i++) {

       
    
         vel = (int) alvo.get(i).getFreqAtualizarPosicao();
  
        g.setColor(Color.red); //alvos
        g.fillRect(alvo.get(i).getLocalizacaoAtualizada().getX(), alvo.get(i).getLocalizacaoAtualizada().getY(), width, height); // X, Y, Altura, Largura;
        g.fillRect(alvo.get(i).getLocalizacaoAtualizada().getX()+300, alvo.get(i).getLocalizacaoAtualizada().getY(), width, height);
          
         }
        
         
         for (int j = 0; j < this.tiros.size(); j++){
        //tiro
         g.setColor(Color.yellow);
         g.fillOval(tiros.get(j).getLocalizacaoAtualizada().getX(), tiros.get(j).getLocalizacaoAtualizada().getY(), 20, 20); //tiro 1
            
         }
        // lançador
        g.setColor(Color.blue);
        g.fillRect(300, 500, 100, height);

        for (int i = 0; i < this.alvo.size(); i++) {
            
       
           try {
                lancadores.add(new Lancador(alvo.get(i)));
                alvo.get(i).mover();
                tiros.get(i).mover(alvo.get(i).getLocalizacaoAtualizada().getX(), alvo.get(i).getLocalizacaoAtualizada().getY());
                if (tiros.get(i).isContatoAlvo() == true || this.acertou(tiros.get(i).getLocalizacaoAtualizada(), alvo.get(i).getLocalizacaoAtualizada()) == true ) {
                    alvo.get(i).setAtingido(true); 
                    tiros.get(i).setContatoAlvo(false);
                    lancadores.get(i).subir_municao();
                 
               }else{
                    lancadores.get(i).desce_municao();
                }
               
               
            } catch (InterruptedException e) {
            }

            repaint();
           
             }
    }
    
    private boolean acertou(Pontos tiro, Pontos alvo) {
    	if(tiro.getX()<=alvo.getX()+10 && tiro.getX()>=alvo.getX()-10 && tiro.getY()>=alvo.getY()-10 && tiro.getY()<=alvo.getY()+0) {
    		return true;
    	}
    	else {
    		return false;
    	}

        
}

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
