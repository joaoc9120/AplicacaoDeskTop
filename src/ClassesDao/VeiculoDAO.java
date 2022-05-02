package ClassesDao;

import Classes.Veiculo;
import ClassesEnum.StatusRegistro;
import ConexaoBancoDados.ConexaoBancoDados;
import GeradorID.gerarId;
import formularios.FrmListarVeiculo;
import formularios.FrmProprietarioVeiculo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author João Carlos Batista
 */
public class VeiculoDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados veiculoConect = new ConexaoBancoDados();

    public static Veiculo carregarVeiculo(int idVeiculo) {
        Veiculo veiculo = new Veiculo();
        String sql = "SELECT vei_Cod, cli_Cod, vei_Pla, vei_Fab, vei_Mod, vei_Ano_Fab, vei_Com,"
                + "vei_Cor, vei_Cha FROM veiculo WHERE vei_Cod =" + idVeiculo;

        if (veiculoConect.conecta()) {
            try {
                veiculoConect.executaSQL(sql);
                while (veiculoConect.resultset.next()) {
                    veiculo.setVei_Cod(veiculoConect.resultset.getInt("vei_Cod"));
                    veiculo.setVei_Cli_Cod(veiculoConect.resultset.getInt("cli_Cod"));
                    veiculo.setVei_Placa(veiculoConect.resultset.getString("vei_Pla"));
                    veiculo.setVei_Fab(veiculoConect.resultset.getString("vei_Fab"));
                    veiculo.setVei_Mod(veiculoConect.resultset.getString("vei_Mod"));
                    veiculo.setVei_Ano_Fab(veiculoConect.resultset.getInt("vei_Ano_Fab"));
                    veiculo.setVei_Com(veiculoConect.resultset.getString("vei_Com"));
                    veiculo.setVei_Cor(veiculoConect.resultset.getString("vei_Cor"));
                    veiculo.setVei_Chassi(veiculoConect.resultset.getString("vei_Cha"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o veículo." + ex);
            }
            veiculoConect.desconectar();
        }
        return veiculo;
    }

    public static void listarVeiculo(int idCliente, String placa) {
        DefaultTableModel modelo = (DefaultTableModel) FrmProprietarioVeiculo.tblVeiculo.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (placa.equals("")) {
            sql = "SELECT * FROM veiculo"
                    + " WHERE cli_Cod=" + idCliente
                    + " AND vei_Sta=" + "'" + StatusRegistro.ATIVO.toString() + "'";
        } else {
            sql = "SELECT * FROM veiculo "
                    + "WHERE cli_Cod=" + idCliente
                    + " AND vei_Sta=" + "'" + StatusRegistro.ATIVO.toString() + "'"
                    + " AND vei_Pla LIKE '%" + placa.trim() + "%'";
        }

        String dados[] = new String[5];

        if (veiculoConect.conecta()) {
            veiculoConect.executaSQL(sql);
            try {
                while (veiculoConect.resultset.next()) {
                    dados[0] = veiculoConect.resultset.getString("vei_Cod");
                    dados[1] = veiculoConect.resultset.getString("vei_Pla");
                    dados[2] = veiculoConect.resultset.getString("vei_Fab");
                    dados[3] = veiculoConect.resultset.getString("vei_Mod");
                    dados[4] = veiculoConect.resultset.getString("vei_Ano_Fab");
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmProprietarioVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            veiculoConect.desconectar();
        }
    }

    public static void gravarVeiculo(Veiculo veiculo) {
        String sql = "";
        sql = "INSERT INTO veiculo(vei_cod, cli_Cod, vei_Pla, vei_Fab, vei_Mod, vei_Ano_Fab, vei_Com, vei_Cor, vei_Cha, vei_Sta)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        if (veiculoConect.conecta()) {
            try {
                ps = veiculoConect.conexao.prepareStatement(sql);
                ps.setInt(1, veiculo.getVei_Cod());
                ps.setInt(2, veiculo.getVei_Cli_Cod());
                ps.setString(3, veiculo.getVei_Placa());
                ps.setString(4, veiculo.getVei_Fab());
                ps.setString(5, veiculo.getVei_Mod());
                if (veiculo.getVei_Ano_Fab() > 0) {
                    ps.setInt(6, veiculo.getVei_Ano_Fab());
                } else {
                    ps.setString(6, null);
                }
                ps.setString(7, veiculo.getVei_Com());
                ps.setString(8, veiculo.getVei_Cor());
                ps.setString(9, veiculo.getVei_Chassi());
                ps.setString(10, StatusRegistro.ATIVO.toString());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo veículo." + ex);
            }
            veiculoConect.desconectar();
        }
    }

    public static void alterarVeiculo(Veiculo veiculo) {

        String sql = "";
        sql = "UPDATE veiculo SET "
                + "vei_Pla      =?,"
                + "vei_Fab      =?,"
                + "vei_Mod      =?,"
                + "vei_Ano_Fab  =?,"
                + "vei_Com      =?,"
                + "vei_Cha      =?,"
                + "vei_Cor      =?"
                + "WHERE cli_Cod=? AND vei_Cod =?";

        if (veiculoConect.conecta()) {
            try {
                ps = veiculoConect.conexao.prepareStatement(sql);
                ps.setString(1, veiculo.getVei_Placa());
                ps.setString(2, veiculo.getVei_Fab());
                ps.setString(3, veiculo.getVei_Mod());
                if (veiculo.getVei_Ano_Fab() > 0) {
                    ps.setInt(4, veiculo.getVei_Ano_Fab());
                } else {
                    ps.setString(4, null);
                }
                ps.setString(5, veiculo.getVei_Com());
                ps.setString(6, veiculo.getVei_Chassi());
                ps.setString(7, veiculo.getVei_Cor());
                ps.setInt(8, veiculo.getVei_Cli_Cod());
                ps.setInt(9, veiculo.getVei_Cod());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o cadastro." + ex);
            }
            veiculoConect.desconectar();
        }
    }

    public static void excluirVeiculo(int idVeiculo) {

        String sql = "";
        sql = "UPDATE veiculo SET "
                + "vei_Sta =? "
                + "WHERE vei_Cod =?";

        if (veiculoConect.conecta()) {
            try {
                ps = veiculoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusRegistro.INATIVO.toString());
                ps.setInt(2, idVeiculo);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o veículo." + ex);
            }
            veiculoConect.desconectar();
        }
    }

    public static void recuperarVeiculo(String proprietario, String placa) {
        DefaultTableModel modelo = (DefaultTableModel) FrmListarVeiculo.tblVeiculo.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (proprietario.equals("") && placa.length() <= 1) {
            sql = "SELECT * FROM recuperar_veiculo";
        } else {
            if (proprietario.length() > 0 && placa.length() <= 1) {
                sql = "SELECT * FROM recuperar_veiculo "
                        + "WHERE cli_Nom LIKE '%" + proprietario.trim() + "%'";
            } else {
                if (proprietario.equals("") && placa.length() > 0) {
                    sql = "SELECT * FROM recuperar_veiculo "
                            + "WHERE vei_Pla LIKE '%" + placa.trim() + "%'";
                } else {
                    sql = "SELECT * FROM recuperar_veiculo "
                            + "WHERE vei_Pla LIKE '%" + placa.trim() + "%' "
                            + "AND cli_Nom LIKE '%" + proprietario.trim() + "%'";
                }
            }
        }

        String dados[] = new String[5];

        if (veiculoConect.conecta()) {
            veiculoConect.executaSQL(sql);
            try {
                while (veiculoConect.resultset.next()) {
                    dados[0] = veiculoConect.resultset.getString("vei_Cod");
                    dados[1] = veiculoConect.resultset.getString("cli_Nom");
                    dados[2] = veiculoConect.resultset.getString("vei_Pla");
                    dados[3] = veiculoConect.resultset.getString("vei_Fab");
                    dados[4] = veiculoConect.resultset.getString("vei_Mod");                    
                    
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            veiculoConect.desconectar();
        }
    }
    
    public static Veiculo consultarExistenciaVeiculo(String placa) {
        Veiculo veiculo = new Veiculo();
        veiculo.setVei_Cli_Cod(0);
        String sql = "SELECT VEI.cli_Cod, CLI.cli_Nom "
                     + "FROM veiculo AS VEI "
                     + "JOIN cliente AS CLI "
                     + "ON VEI.cli_Cod = CLI.cli_Cod "
                     + "WHERE VEI.vei_Pla = '" + placa +"'"
                     + " AND VEI.vei_Sta = '" +StatusRegistro.ATIVO.toString()+"'";     

        if (veiculoConect.conecta()) {
            try {
                veiculoConect.executaSQL(sql);
                while (veiculoConect.resultset.next()) {
                    veiculo.setCli_Nom(veiculoConect.resultset.getString("CLI.cli_Nom"));
                    veiculo.setVei_Cli_Cod(veiculoConect.resultset.getInt("VEI.cli_Cod"));
                }
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Erro ao carregar o veículo." + ex);
            }
            veiculoConect.desconectar();
        }
        return veiculo;
    }    
}
