package Formatadores;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TeclasNumericaAno extends PlainDocument {

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        int tamanho = (this.getLength() + str.length());
        
        if (tamanho <= 4) {
            super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
        }
    }
    
    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
    }
}

// 641.322.510-63 = 14
// 42.436.232/0001-37 = 18

/*
public class TeclasNumericaAno extends PlainDocument {

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        int tamanho = (this.getLength() + str.length());

        if (tamanho <= 4) {
            super.insertString(offset, str.replaceAll("[aA-zZ]", ""), attr);
        } else {
            super.insertString(offset, str.replaceAll("[aA0-zZ9]", ""), attr);
        }
    }
}
*/