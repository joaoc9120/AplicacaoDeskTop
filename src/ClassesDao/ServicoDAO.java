package ClassesDao;

import Classes.Servico;
import Classes.StatusRegistro;
import ClassesEnum.ServicoProduto;
import ConexaoBancoDados.ConexaoBancoDados;
import formularios.FrmListaCliente;
import formularios.FrmListaServico;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ServicoDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados ServicoConect = new ConexaoBancoDados();

    public static Servico carregarServico(int idServico) {
        Servico servico = new Servico();

        String sql = "SELECT prod_Cod, prod_Des, prod_Val_Ven "
                + "FROM produto WHERE prod_Cod = " + idServico;

        if (ServicoConect.conecta()) {
            try {
                ServicoConect.executaSQL(sql);
                while (ServicoConect.resultset.next()) {
                    servico.setSer_Cod(ServicoConect.resultset.getInt("prod_Cod"));
                    servico.setSer_Des(ServicoConect.resultset.getString("prod_Des"));
                    servico.setSer_Val(ServicoConect.resultset.getDouble("prod_Val_Ven"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o servico: " + ex);
            }
            ServicoConect.desconectar();
        }
        return servico;
    }

    public static void listarServico(String descricao) {
        DefaultTableModel modelo = (DefaultTableModel) FrmListaServico.tblServico.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (descricao.equals("")) {
            sql = "SELECT prod_Cod, prod_Des, prod_Val_Ven "
                    + "FROM produto WHERE prod_Tip = " + "'" +ServicoProduto.SERVICO.toString()+"' " 
                    + "AND prod_Sta = "+ "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "ORDER BY prod_Des";
        } else {
            sql = "SELECT prod_Cod, prod_Des, prod_Val_Ven "
                    + "FROM produto WHERE  prod_Tip = " + "'" +ServicoProduto.SERVICO.toString()+"' "
                    + "AND prod_Sta = "+ "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "AND prod_Des LIKE '%" + descricao.trim() + "%' "
                    + "ORDER BY prod_Des";
        }

        String dados[] = new String[3];

        if (ServicoConect.conecta()) {
            try {
                ServicoConect.executaSQL(sql);
                while (ServicoConect.resultset.next()) {
                    dados[0] = ServicoConect.resultset.getString("prod_Cod");
                    dados[1] = ServicoConect.resultset.getString("prod_Des");
                    dados[2] = ServicoConect.resultset.getString("prod_Val_Ven");
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaServico.class.getName()).log(Level.SEVERE, null, ex);
            }

            ServicoConect.desconectar();
        }
    }

    public static void gravarServico(Servico servico) {
        String sql = "";
        sql = "INSERT INTO produto(prod_Cod, prod_Des, prod_Val_Ven, prod_Tip, prod_Sta) "
                + "VALUES(?,?,?,?,?)";

        if (ServicoConect.conecta()) {
            try {
                ps = ServicoConect.conexao.prepareStatement(sql);
                ps.setInt(1, servico.getSer_Cod());
                ps.setString(2, servico.getSer_Des().trim());
                ps.setDouble(3, servico.getSer_Val());
                ps.setString(4, servico.getSer_Tip());
                ps.setString(5, servico.getSer_Sta());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo serviço." + ex);
            }

            ServicoConect.desconectar();
        }
    }

    public static void alterarServico(Servico servico) {
        String sql = "";
        sql = "UPDATE produto SET "
                + "prod_Des         =?,"
                + "prod_Val_Ven     =? "
                + "WHERE prod_Cod   =?";

        if (ServicoConect.conecta()) {
            try {
                ps = ServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, servico.getSer_Des().trim());
                ps.setDouble(2, servico.getSer_Val());
                ps.setInt(3, servico.getSer_Cod());
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o serviço." + ex);
            }

            ServicoConect.desconectar();
        }
    }

    public static void excluir(int id) {
        String sql = "";
        sql = "UPDATE produto SET "
                + "prod_Sta         =? "
                + "WHERE prod_Cod   =?";

        if (ServicoConect.conecta()) {
            try {
                ps = ServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusRegistro.INATIVO.toString() );
                ps.setInt(2, id );
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o serviço." + ex);
            }

            ServicoConect.desconectar();
        }
    }
}
