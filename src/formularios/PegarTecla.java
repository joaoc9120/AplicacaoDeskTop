/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;
//import java.awt.event.KeyEvent evt;
/**
 *
 * @author João Carlos Batista
 */
public class PegarTecla {

    //public static void main(String[] args){
    // 
    //     MinhaTread thread = new MinhaTread("Thread #1");
    //thread.start();
    //}
    public int verificaTecla(java.awt.event.KeyEvent evt) {

        // if (evt.getKeyCode() == 10) {
        //    tecla = 10;
        //}
        
        return evt.getKeyCode();
    }

}
