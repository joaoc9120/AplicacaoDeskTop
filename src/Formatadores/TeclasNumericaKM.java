package Formatadores;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TeclasNumericaKM extends PlainDocument{
    
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        int tamanho = (this.getLength() + str.length());
        
        if (tamanho <= 9) {
            super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
        }
    }
    
    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
    }    
    
}
