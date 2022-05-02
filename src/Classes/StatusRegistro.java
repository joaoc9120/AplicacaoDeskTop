/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author João Carlos Batista
 */
public enum StatusRegistro {
    
    ATIVO("A"), INATIVO("I");
    
    private String status;

    StatusRegistro(String status) {
        this.status = status;
    }
    
    public String getStatus(){
    
        return this.status;
    }        
}
