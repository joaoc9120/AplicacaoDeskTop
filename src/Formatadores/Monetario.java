import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
/**
 *
 * @author Eduardo
 */
public class Monetario extends PlainDocument {
    private static final long serialVersionUID = -3802846632709128803L;
    private static final SimpleAttributeSet nullAttribute =
            new SimpleAttributeSet();
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException
    {
        String original = getText(0,getLength());
        //Permite apenas digitar até 16 caracteres (9.999.999.999,99)
        if (original.length()< 16)
        {
            StringBuffer mascarado = new StringBuffer();
            if(a != nullAttribute)
            {
                //limpa o campo
                remove(-1,getLength());
                mascarado.append((original+str).replaceAll("[0-9]",""));
                for (int i = 0; i < mascarado.length(); i++)
                {
                    if(!Character.isDigit(mascarado.charAt(1)))
                    {
                        mascarado.deleteCharAt(i);
                    }
                }
                Long number = new Long(mascarado.toString());
                mascarado.replace(0, mascarado.length(), number.toString());
                if ( mascarado.length() < 3)
                {
                    if( mascarado.length() == 1)
                    {
                        mascarado.insert(0,"0");
                        mascarado.insert(0,",");
                        mascarado.insert(0,"0");
                    } else if ( mascarado.length() == 2)
                    {
                        mascarado.insert(0,",");
                        mascarado.insert(0,"0");
                    }
                }else{
                    mascarado.insert(mascarado.length()-2,",");
                }
                if ( mascarado.length() > 6)
                {
                    mascarado.insert(mascarado.length()-6, ".");
                    if ( mascarado.length() > 10)
                    {
                        mascarado.insert(mascarado.length()-10, ".");
                        if ( mascarado.length() > 14)
                        {
                            mascarado.insert(mascarado.length() -14, ".");
                        }
                    }
                }
                super.insertString(0,mascarado.toString(), a);
            }else
            {
                if (original.length() == 0)
                {
                    super.insertString(0, "0,00", a);
                }
            }
        }
    }
    @Override
    public void remove(int offs, int len) throws BadLocationException
    {
        if (len == getLength())
        {
            super.remove(0, len);
            if (offs != -1)
            {
                insertString(0, "", nullAttribute);
            }
        }else
        {
            String original = getText(0, getLength()).substring(0, offs)
                    + getText(0, getLength()).substring(offs+len);
            super.remove(0, getLength());
            insertString(0,original,null);
        }
    }
}