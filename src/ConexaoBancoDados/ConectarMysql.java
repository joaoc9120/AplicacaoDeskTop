/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * @author João Carlos Batista
 */
public class ConectarMysql {

    Connection conect = null;

    public Connection conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/oficinahomologacao", "root", "root"); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error ao conectar no banco de dados" + e);
        }
        return conect;
    }     
    
}
