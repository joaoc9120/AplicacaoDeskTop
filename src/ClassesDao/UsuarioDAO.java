/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Classes.Criptografiar;
import Classes.Usuarios;
import ConexaoBancoDados.ConexaoBancoDados;
import formularios.FrmListaUsuario;
import javax.swing.JOptionPane;
//import principal.GerarCodigos;

/**
 *
 * @author João Carlos Batista
 */
public class UsuarioDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados usuarioConect = new ConexaoBancoDados();

    public static void listarUsuario(String nome, String login) {

        DefaultTableModel modelo = (DefaultTableModel) FrmListaUsuario.tblUsuario.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (nome.equals("") && login.equals("")) {
            sql = "SELECT * FROM usuario ORDER BY usu_Nom";
        } else {
            if (nome.equals("")) {
                sql = "SELECT * FROM usuario WHERE usu_Log LIKE '%" + login.trim() + "%' ORDER BY usu_Nom";
            } else {
                if (login.equals("")) {
                    sql = "SELECT * FROM usuario WHERE usu_Nom LIKE '%" + nome.trim() + "%' ORDER BY usu_Nom";

                } else {
                    sql = "SELECT * FROM usuario WHERE usu_Nom LIKE '%" + nome.trim() + "%' AND usu_Log LIKE '%" + login.trim() + "%'  ORDER BY usu_Nom";
                }
            }
        }

        String dados[] = new String[5];

        if (usuarioConect.conecta()) {
            usuarioConect.executaSQL(sql);
            try {
                while (usuarioConect.resultset.next()) {
                    modelo.addRow(new Object[]{usuarioConect.resultset.getString("usu_cod"),
                        usuarioConect.resultset.getString("usu_Nom"),
                        usuarioConect.resultset.getString("usu_Log"),
                        usuarioConect.resultset.getString("usu_Ema"),
                        usuarioConect.resultset.getString("usu_Ema")});
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            usuarioConect.desconectar();
        }
    }

    public static void gravarUsuario(Usuarios usuario) {
        String sql = "";
        sql = "INSERT INTO usuario(usu_Cod, usu_Nom, usu_Log, usu_Sen, usu_Ema)" + "VALUES(?,?,?,?,?)";

        if (usuarioConect.conecta()) {

            try {
                ps = usuarioConect.conexao.prepareStatement(sql);
                ps.setInt(1, usuario.getUsu_Cod());
                ps.setString(2, usuario.getUsu_Nom());
                ps.setString(3, usuario.getUsu_Log());
                ps.setString(4, Criptografiar.encriptografar(usuario.getUsu_Sen()));
                ps.setString(5, usuario.getUsu_Ema());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo usuário." + ex);
            }
            usuarioConect.desconectar();
        }
    }

    public static void alterar(Usuarios usuario) {
        String sql = "";
        sql = "UPDATE usuario SET "
                + "usu_Nom=?, "
                + "usu_Log=?, "
                + "usu_Sen=?, "
                + "usu_Ema=? WHERE usu_Cod=?";

        if (usuarioConect.conecta()) {
            try {
                ps = usuarioConect.conexao.prepareStatement(sql);
                ps.setString(1, usuario.getUsu_Nom());
                ps.setString(2, usuario.getUsu_Log());
                ps.setString(3, Criptografiar.encriptografar(usuario.getUsu_Sen()));
                ps.setString(4, usuario.getUsu_Ema());
                ps.setInt(5, usuario.getUsu_Cod());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o cadastro." + ex);
            }

            usuarioConect.desconectar();
        }
    }

    public static void excluir(int id) {
        String sql = "";
        sql = "DELETE FROM usuario WHERE usu_Cod=?";

        if (usuarioConect.conecta()) {
            try {
                ps = usuarioConect.conexao.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Usuário excluir com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o usuário." + ex);
            }

            usuarioConect.desconectar();
        }
    }
}
