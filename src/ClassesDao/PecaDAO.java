package ClassesDao;

import Classes.Peca;
import Classes.StatusRegistro;
import ClassesEnum.ServicoProduto;
import ConexaoBancoDados.ConexaoBancoDados;
import formularios.FrmListaPeca;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PecaDAO {
    static PreparedStatement ps;
    static ConexaoBancoDados ProdutoConect = new ConexaoBancoDados();

    public static Peca carregarProduto(int idProduto) {
        Peca produto = new Peca();

        String sql = "SELECT *FROM produto WHERE prod_Cod = " + idProduto;

        if (ProdutoConect.conecta()) {
            try {
                ProdutoConect.executaSQL(sql);
                while (ProdutoConect.resultset.next()) {
                    produto.setPro_Cod(ProdutoConect.resultset.getInt("prod_Cod"));
                    produto.setPro_Tip(ProdutoConect.resultset.getString("prod_Tip"));
                    produto.setPro_Des(ProdutoConect.resultset.getString("prod_Des"));
                    produto.setPro_Qua_Min(ProdutoConect.resultset.getInt("prod_Qua_Min"));
                    produto.setPro_Qua_Est(ProdutoConect.resultset.getInt("prod_Qua_Est"));
                    produto.setPro_Val_Cus(ProdutoConect.resultset.getDouble("prod_Val_Cus"));
                    produto.setPro_Val_Ven(ProdutoConect.resultset.getDouble("prod_Val_Ven"));
                    produto.setPro_Sta(ProdutoConect.resultset.getString("prod_Sta"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o Produto: " + ex);
            }
            ProdutoConect.desconectar();
        }
        return produto;
    }

     public static void listarPeca(String descricao){
        DefaultTableModel modelo = (DefaultTableModel) FrmListaPeca.tblPeca.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (descricao.equals("")) {
            sql = "SELECT prod_Cod, prod_Des, prod_Qua_Est, prod_Val_Ven "
                    + "FROM produto WHERE prod_Tip = " + "'" + ServicoProduto.PRODUTO.toString() + "' "
                    + "AND prod_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "ORDER BY prod_Des";
        } else {
            sql = "SELECT prod_Cod, prod_Des, prod_Qua_Est, prod_Val_Ven "
                    + "FROM produto WHERE  prod_Tip = " + "'" + ServicoProduto.PRODUTO.toString() + "' "
                    + "AND prod_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "AND prod_Des LIKE '%" + descricao.trim() + "%' "
                    + "ORDER BY prod_Des";
        }

        String dados[] = new String[4];

        if (ProdutoConect.conecta()) {
            try {
                ProdutoConect.executaSQL(sql);
                while (ProdutoConect.resultset.next()) {
                    dados[0] = ProdutoConect.resultset.getString("prod_Cod");
                    dados[1] = ProdutoConect.resultset.getString("prod_Des");
                    dados[2] = ProdutoConect.resultset.getString("prod_Qua_Est");
                    dados[3] = ProdutoConect.resultset.getString("prod_Val_Ven");
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaPeca.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProdutoConect.desconectar();
        }
    }

    public static void gravarProduto(Peca produto) {
        String sql = "";
        sql = "INSERT INTO produto(prod_Cod, prod_Tip, prod_Des, prod_Qua_Min, prod_Qua_Est, prod_Val_Cus, prod_Val_Ven, prod_Sta) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        if (ProdutoConect.conecta()) {
            try {
                ps = ProdutoConect.conexao.prepareStatement(sql);
                ps.setInt(1, produto.getPro_Cod());
                ps.setString(2, produto.getPro_Tip());
                ps.setString(3, produto.getPro_Des().trim());
                ps.setInt(4, produto.getPro_Qua_Min());
                ps.setInt(5, produto.getPro_Qua_Est());
                ps.setDouble(6, produto.getPro_Val_Cus());
                ps.setDouble(7, produto.getPro_Val_Ven());
                ps.setString(8, produto.getPro_Sta());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Peça cadastrada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir a nova Peça." + ex);
            }
            ProdutoConect.desconectar();
        }
    }

    public static void alterarProduto(Peca produto) {
        String sql = "";
        sql = "UPDATE produto SET "
                + "prod_Des         =?, "
                + "prod_Qua_Min     =?, "
                + "prod_Qua_Est     =?, "
                + "prod_Val_Cus     =?, "
                + "prod_Val_Ven     =?  "
                + "WHERE prod_Cod   =?  ";

        if (ProdutoConect.conecta()) {
            try {
                ps = ProdutoConect.conexao.prepareStatement(sql);
                ps.setString(1, produto.getPro_Des().trim());
                ps.setInt(2, produto.getPro_Qua_Min());
                ps.setInt(3, produto.getPro_Qua_Est());
                ps.setDouble(4, produto.getPro_Val_Cus());
                ps.setDouble(5, produto.getPro_Val_Ven());
                ps.setInt(6, produto.getPro_Cod());
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Peça atualizada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar a peça." + ex);
            }
            ProdutoConect.desconectar();
        }
    }

    public static void excluir(int id) {
        String sql = "";
        sql = "UPDATE produto SET "
                + "prod_Sta         =? "
                + "WHERE prod_Cod   =?";

        if (ProdutoConect.conecta()) {
            try {
                ps = ProdutoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusRegistro.INATIVO.toString());
                ps.setInt(2, id);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o Produto." + ex);
            }
            ProdutoConect.desconectar();
        }
    }
}