/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formatadores;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 *
 * @author João Carlos Batista
 */
public class LetrasMaiusculas extends PlainDocument{
    
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.toUpperCase(), attr);
    }
    
    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.toUpperCase(), attr);
    }
    
}


/*
public class LetrasMaiusculas extends PlainDocument{
    
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.toUpperCase().replaceAll("[^0-9|^a-z|^A-Z|^à|^á|^ã|^Ã|^À|^Á|^è|^é|^È|^É|^ì|^í|^Ì|^Í|"
                + "^ò|^ó|^Ò|^Ó|^õ|^Õ|^ù|^ú|^Ù|^Ú|^ç|^Ç|^ |^,|^<|^.|^>|^;|^:|^?|^/|^~|^^|^!|^@|^$|^%|^&|^*|^(|^)|^ -|^+|^=|^#]", ""), attr);
    }
    
    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.toUpperCase().replaceAll("[^0-9|^a-z|^A-Z|^à|^á|^ã|^Ã|^À|^Á|^è|^é|^È|^É|^ì|^í|^Ì|^Í|"
                + "^ò|^ó|^Ò|^Ó|^õ|^Õ|^ù|^ú|^Ù|^Ú|^ç|^Ç|^ |^,|^<|^.|^>|^;|^:|^?|^/|^~|^^|^!|^@|^$|^%|^&|^*|^(|^)|^ -|^+|^=|^#]", ""), attr);
    }
    
}*/