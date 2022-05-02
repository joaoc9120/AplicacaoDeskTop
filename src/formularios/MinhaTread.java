/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

/**
 *
 * @author João Carlos Batista
 */
public class MinhaTread extends Thread{
    
    private String nome;
    
    public MinhaTread(String nome){
    
        this.nome = nome;
        start();
    }
    
    public void run(){
    
        System.out.println("Executado a thread");
    
    }
}
