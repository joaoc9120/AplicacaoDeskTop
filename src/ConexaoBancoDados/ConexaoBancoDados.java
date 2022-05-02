/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author João Carlos Batista
 */
public class ConexaoBancoDados {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/oficinahomologacao";
    String usuario = "root";
    String senha = "root";

    public Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    public PreparedStatement ps;

    public boolean conecta() {

        boolean result = true;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar com banco de dados. " + ex);
        }

        return result;
    }

    public void desconectar() {

        boolean result = true;

        try {
            conexao.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar descnectar do banco de dados. " + ex);
        }
    }

    public void executaSQL(String sql) {

        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, resultset.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível executar a ação. " + ex);
        }
    }
}
