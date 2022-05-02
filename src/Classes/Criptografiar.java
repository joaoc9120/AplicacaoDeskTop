/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Base64;

/**
 *
 * @author João Carlos Batista
 */
public class Criptografiar {
    
     public static String encriptografar(String senha){
          
        String codificado = Base64.getEncoder().encodeToString(senha.getBytes());
            
        return codificado;   
    }
     
     public static String decriptografar(String senha){
          
        byte[] decodificado = Base64.getDecoder().decode(senha);
        senha = new String(decodificado);
        
        return senha;
    }     
/*
     **********MODELO ANTIGO****************
     
     public static String encriptografar(String senha){
        String retorno = "";        
        MessageDigest md;
        
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            retorno = hash.toString(16);
        } catch (Exception ex) {
           
        }
    
        return retorno;
   
    }     
     */
          
    
}
