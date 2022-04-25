/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.ufla.avancada.alvos.moveis;

//Importação de bibliotecas
import java.awt.Color;
import java.util.ArrayList; 
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author decio.mendonca
 */
public class AvancadaAlvosMoveis extends JFrame {

    AvancadaAlvosMoveis(){
        setSize(800,600);
        setTitle("Alvos moveis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Interface panel=new Interface(800,600);
        Thread nova=new Thread(panel);
        nova.start();
        
        panel.setBackground(Color.white);
        add(panel);
                
                }
    public static void main(String[] args) {
        new AvancadaAlvosMoveis().setVisible(true);
        
    }
}
