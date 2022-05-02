/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formatadores;

import javax.swing.JOptionPane;

/**
 *
 * @author João Carlos Batista
 */
public class TratarMoeda {
    
    public static Double Moeda(String valor){
        
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        
        return Double.parseDouble(valor);
    }
    
    public static String FormatarMoeda(double valor){

        String moeda = String.format("%.2f", valor);
        //JOptionPane.showMessageDialog(null, "Erro ao carregar o Produto: " + moeda);
        
        return moeda.replace(",", "");
    }
    
}
