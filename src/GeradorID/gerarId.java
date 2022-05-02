package GeradorID;

import Classes.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author João Carlos Batista
 */
public class gerarId {
    static Conectar cc = new Conectar();
    static Connection cn = cc.conexao();
    static PreparedStatement ps;
    static String sql;
    static int numero = 0;
    
    public static int gerarIdCliente() {
        //String sql = "";
        //int numero = 0;

        sql = "SELECT MAX(cli_Cod) FROM cliente";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }
    
    public static int gerarIdOS() {
        String sql = "";
        int numero = 0;

        sql = "SELECT MAX(os_Cod) FROM ordemservico";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }

    public static int gerarIDVeiculo() {
        String sql = "";
        int numero = 0;

        sql = "SELECT MAX(vei_Cod) FROM veiculo";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }
    
    public static int gerarIDProdutoServico() {
        String sql = "";
        int numero = 0;

        sql = "SELECT MAX(prod_Cod) FROM produto";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }
    
    
    public static int gerarIdOrdemServicoItem(int os_Cod) {
        String sql = "";
        int numero = 0;

        sql = "SELECT MAX(os_item_Cod) "
                + "FROM ordemservicoitem "
                + "WHERE os_Cod = " + os_Cod;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }
    
    public static int gerarIdUsuario() {
        //String sql = "";
        //int numero = 0;

        sql = "SELECT MAX(usu_Cod) FROM usuario";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }
    
    public static int gerarNumeroOS() {
        
        sql = "SELECT MAX(os_Num) FROM ordemservico";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                numero = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {

        }
        return numero + 1;
    }    
}
