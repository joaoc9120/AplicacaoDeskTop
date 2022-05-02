package ClassesDao;

import Classes.OrdemServico;
import Classes.StatusRegistro;
import Classes.Veiculo;
import static ClassesDao.VeiculoDAO.veiculoConect;
import ClassesEnum.StatusOS;
import ConexaoBancoDados.ConexaoBancoDados;
import GeradorID.gerarId;
import formularios.FrmListaOrcamento;
import formularios.FrmListaOrdemServico;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author João Carlos Batista
 */
public class OrdemServicoDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados ordemServicoConect = new ConexaoBancoDados();

    public static OrdemServico carregarOrdemServico(int idOrdemServico) {
        OrdemServico ordemServico = new OrdemServico();
        String sql = "SELECT os_Cod, vei_Cod, os_Dat_Orc, os_Dat_Ent, os_Dat_Sai, os_Sit "
                + "FROM ordemServico WHERE os_Cod = " + idOrdemServico;

        if (ordemServicoConect.conecta()) {
            try {
                ordemServicoConect.executaSQL(sql);
                while (ordemServicoConect.resultset.next()) {
                    ordemServico.setOs_Cod(ordemServicoConect.resultset.getInt("os_Cod"));
                    ordemServico.setVei_Cod(ordemServicoConect.resultset.getInt("vei_Cod"));
                    ordemServico.setOs_Dat_Orc(ordemServicoConect.resultset.getDate("os_Dat_Orc"));
                    ordemServico.setOs_Dat_Ent(ordemServicoConect.resultset.getDate("os_Dat_Ent"));
                    ordemServico.setOs_Dat_Sai(ordemServicoConect.resultset.getDate("os_Dat_Ent"));
                    ordemServico.setOs_Sit(ordemServicoConect.resultset.getString("os_Sit"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o O.S." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
        return ordemServico;
    }

    public static void listarOrdemServico(int osNumero, String proprietario) {
        DefaultTableModel modelo = (DefaultTableModel) FrmListaOrdemServico.tblOrdemServico.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql;
        if (osNumero > 0 && proprietario.equals("")) {
            sql = "SELECT * FROM listarordemservico "
                    + "WHERE os_Num = " + osNumero
                    + " ORDER BY os_Num";
        } else {
            if (osNumero < 1 && proprietario.length() > 0) {
                sql = "SELECT * FROM listarordemservico "
                        + "WHERE cli_Nom LIKE '%" + proprietario + "%' ORDER BY cli_Nom";
            } else {
                if (osNumero > 0 && proprietario.length() > 0) {
                    sql = "SELECT * FROM listarordemservico "
                            + "WHERE cli_Nom LIKE '%" + proprietario.trim() + "%'"
                            + "AND os_Num = " + osNumero
                            + " ORDER BY os_Num";
                } else {
                    sql = "SELECT * FROM listarordemservico"
                            + " ORDER BY os_Num";
                }
            }
        }

        String dados[] = new String[6];

        if (ordemServicoConect.conecta()) {
            ordemServicoConect.executaSQL(sql);
            try {
                while (ordemServicoConect.resultset.next()) {
                    dados[0] = ordemServicoConect.resultset.getString("os_Cod");
                    dados[1] = ordemServicoConect.resultset.getString("os_Num");
                    dados[2] = ordemServicoConect.resultset.getString("cli_Nom");
                    dados[3] = ordemServicoConect.resultset.getString("vei_Fab")
                            + " - " + ordemServicoConect.resultset.getString("vei_Mod");
                    dados[4] = ordemServicoConect.resultset.getString("vei_Pla");
                    if (ordemServicoConect.resultset.getString("os_Sit").equals(StatusOS.SEF.toString())) {
                        dados[5] = StatusOS.SEF.getDescricao();
                    } else {
                        if (ordemServicoConect.resultset.getString("os_Sit").equals(StatusOS.SEA.toString())) {
                            dados[5] = StatusOS.SEA.getDescricao();
                        }
                    }
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void listarOrcamento(int OrcNumero, String proprietario) {
        DefaultTableModel modelo = (DefaultTableModel) FrmListaOrcamento.tblOrcamento.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql;
        if (OrcNumero > 0 && proprietario.equals("")) {
            sql = "SELECT * FROM listarorcamento "
                    + "WHERE os_Cod = " + OrcNumero;
        } else {
            if (OrcNumero < 1 && proprietario.length() > 0) {
                sql = "SELECT * FROM listarorcamento "
                        + "WHERE cli_Nom LIKE '%" + proprietario + "%' ORDER BY cli_Nom";
            } else {
                if (OrcNumero > 0 && proprietario.length() > 0) {
                    sql = "SELECT * FROM listarorcamento "
                            + "WHERE cli_Nom LIKE '%" + proprietario.trim() + "%'"
                            + "AND os_Cod = " + OrcNumero
                            + " ORDER BY cli_Nom";
                } else {
                    sql = "SELECT * FROM listarorcamento";
                }
            }
        }

        String dados[] = new String[5];

        if (ordemServicoConect.conecta()) {
            ordemServicoConect.executaSQL(sql);
            try {
                while (ordemServicoConect.resultset.next()) {
                    dados[0] = ordemServicoConect.resultset.getString("os_Cod");
                    dados[1] = ordemServicoConect.resultset.getString("cli_Nom");
                    dados[2] = ordemServicoConect.resultset.getString("vei_Fab")
                            + " - " + ordemServicoConect.resultset.getString("vei_Mod");
                    dados[3] = ordemServicoConect.resultset.getString("vei_Pla");
                    dados[4] = ordemServicoConect.resultset.getString("os_Sit");
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaOrcamento.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static boolean gravarOrdemServico(OrdemServico ordemServico) {

        boolean error = true;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String sql;
        sql = "INSERT INTO ordemservico(os_Cod, vei_Cod, os_Dat_Orc, os_Dat_Ent, os_Num, os_Sit, os_Sta)"
                + "VALUES(?,?,?,?,?,?,?)";
        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setInt(1, ordemServico.getOs_Cod());
                ps.setInt(2, ordemServico.getVei_Cod());
                ps.setString(3, formatter.format(ordemServico.getOs_Dat_Orc()));
                if (ordemServico.getOs_Sit().equals(StatusOS.OR.toString())) {
                    ps.setString(4, null);
                    ps.setString(5, null);
                } else {
                    ps.setString(4, formatter.format(ordemServico.getOs_Dat_Ent()));
                    ps.setInt(5, gerarId.gerarNumeroOS());
                }
                ps.setString(6, ordemServico.getOs_Sit());
                ps.setString(7, StatusRegistro.ATIVO.toString());
                ps.executeUpdate();
                ps.close();
                error = false;
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir a O.S." + ex);
                error = true;
            } finally {
                ordemServicoConect.desconectar();
            }
        }
        return error;
    }

    public static void gravarOrdemServicoItem(OrdemServico ordemServico) {
        String sql;
        sql = "INSERT INTO ordemservico(os_Cod, vei_Cod, os_Dat_Orc, os_Dat_Ent, os_Dat_Sai, os_Sit)"
                + "VALUES(?,?,?,?,?,?)";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setInt(1, ordemServico.getOs_Cod());
                ps.setInt(2, ordemServico.getVei_Cod());
                ps.setString(3, "2018-05-05");
                ps.setString(3, "2018-05-05");
                ps.setString(4, "2018-05-05");
                ps.setString(5, "2018-05-05");
                ps.setString(6, ordemServico.getOs_Sit());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S cadastrado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir a O.S." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void alterarOrdemServico(OrdemServico ordemServico) {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dataSaida;
        String sql;
        sql = "UPDATE ordemservico SET "
                + "os_Def        =?, "
                + "os_Km_Ent     =?, "
                + "os_Dat_Sai    =?, "
                + "os_Tan        =?, "
                + "os_Sit        =?  "
                + "WHERE os_Cod =?";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, ordemServico.getOs_Def());

                if (ordemServico.getOs_Km_Ent() == 0) {
                    ps.setString(2, null);
                } else {
                    ps.setInt(2, ordemServico.getOs_Km_Ent());
                }

                if (ordemServico.getOs_Dat_Sai() == null) {
                    ps.setString(3, null);
                } else {
                    dataSaida = formatter.format(ordemServico.getOs_Dat_Sai());
                    ps.setString(3, dataSaida);
                }

                if (ordemServico.getOs_Tan().equals("::SELECIONE::") || ordemServico.getOs_Tan().equals("")) {
                    ps.setString(4, null);
                } else {
                    ps.setString(4, ordemServico.getOs_Tan());
                }

                ps.setString(5, ordemServico.getOs_Sit());
                ps.setInt(6, ordemServico.getOs_Cod());

                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar a O.S." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void finalizarOS(int os_Cod) {
        String sql;
        sql = "UPDATE ordemservico SET "
                + "os_Sit       =?  "
                + "WHERE os_Cod =?";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusOS.SF.toString());
                ps.setInt(2, os_Cod);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S Finalizada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível finalizar a O.S." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void GerarOrdemServico(int os_Cod) {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dataSaida;
        Date data = new Date();
        String sql;
        sql = "UPDATE ordemservico SET "
                + "os_sit       = ?,"
                + "os_Dat_Ent   = ?,"
                + "os_Num       = ? "
                + "WHERE os_Cod =?";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusOS.SEF.toString());
                ps.setString(2, formatter.format(data));
                ps.setInt(3, gerarId.gerarNumeroOS());
                ps.setInt(4, os_Cod);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S gerada com sucesso..");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível gerar a ordem de serviço." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void cancelarOrdemServico(int idOrdemServico) {
        String sql;
        sql = "UPDATE ordemservico SET "
                + "os_sit = ?"
                + "WHERE os_Cod=?";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusOS.SC.toString());
                ps.setInt(2, idOrdemServico);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S Cancelada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel cancelar a O.S." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void excluirOrcamento(int idOrdemServico) {
        String sql;
        sql = "UPDATE ordemservico SET "
                + "os_Sta = ?"
                + "WHERE os_Cod=?";

        if (ordemServicoConect.conecta()) {
            try {
                ps = ordemServicoConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusRegistro.INATIVO.toString());
                ps.setInt(2, idOrdemServico);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Orçamento excluído com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluído o orçamento." + ex);
            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static Veiculo PesquisarVeiculoVeiculo(String placa, int vei_Cod) {
        Veiculo veiculo = new Veiculo();
        veiculo.setVei_Cod(0);
        String sql;
        if (vei_Cod == 0) {
            sql = "SELECT CLI.cli_cod, CLI.cli_Nom, "
                    + "VEI.vei_Cod, VEI.vei_Pla, VEI.vei_Fab, "
                    + "VEI.vei_Mod, VEI.vei_Ano_Fab, VEI.vei_Com, VEI.vei_Cor "
                    + "FROM veiculo AS VEI "
                    + "JOIN cliente AS CLI "
                    + "ON VEI.cli_Cod = CLI.cli_Cod "
                    + "WHERE VEI.vei_Pla = '" + placa + "'";
        } else {
            sql = "SELECT CLI.cli_cod, CLI.cli_Nom, "
                    + "VEI.vei_Cod, VEI.vei_Pla, VEI.vei_Fab, "
                    + "VEI.vei_Mod, VEI.vei_Ano_Fab, VEI.vei_Com, VEI.vei_Cor "
                    + "FROM veiculo AS VEI "
                    + "JOIN cliente AS CLI "
                    + "ON VEI.cli_Cod = CLI.cli_Cod "
                    + "WHERE VEI.vei_Cod = " + vei_Cod;
        }

        if (veiculoConect.conecta()) {
            try {
                veiculoConect.executaSQL(sql);
                while (veiculoConect.resultset.next()) {
                    veiculo.setVei_Cli_Cod(veiculoConect.resultset.getInt("cli_cod"));
                    veiculo.setCli_Nom(veiculoConect.resultset.getString("cli_Nom"));
                    veiculo.setVei_Cod(veiculoConect.resultset.getInt("vei_cod"));
                    veiculo.setVei_Placa(veiculoConect.resultset.getString("vei_Pla"));
                    veiculo.setVei_Fab(veiculoConect.resultset.getString("vei_Fab"));
                    veiculo.setVei_Mod(veiculoConect.resultset.getString("vei_Mod"));
                    veiculo.setVei_Ano_Fab(veiculoConect.resultset.getInt("vei_Ano_Fab"));
                    veiculo.setVei_Com(veiculoConect.resultset.getString("vei_Com"));
                    veiculo.setVei_Cor(veiculoConect.resultset.getString("vei_Cor"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar os dados." + ex);
            } finally {
                veiculoConect.desconectar();
            }
        }
        return veiculo;
    }

    public static void gerarRelatorioOrcamento(int osNumero) {
        String sql;

        sql = "SELECT"
                + " cliente.`cli_Cod` AS cliente_cli_Cod,"
                + " cliente.`cli_Nom` AS cliente_cli_Nom,"
                + " cliente.`cli_Bai` AS cliente_cli_Bai,"
                + " cliente.`cli_Tel_Res` AS cliente_cli_Tel_Res,"
                + " cliente.`cli_Tel_Cel` AS cliente_cli_Tel_Cel,"
                + " cliente.`cli_Sta` AS cliente_cli_Sta,"
                + " ordemservico.`os_Cod` AS ordemservico_os_Cod,"
                + " ordemservico.`os_Dat_Orc` AS ordemservico_os_Dat_Orc,"
                + " ordemservicoitem.`os_Cod` AS ordemservicoitem_os_Cod,"
                + " ordemservicoitem.`os_item_Cod` AS ordemservicoitem_os_item_Cod,"
                + " ordemservicoitem.`os_item_qua` AS ordemservicoitem_os_item_qua,"
                + " ordemservicoitem.`os_item_val` AS ordemservicoitem_os_item_val,"
                + " ordemservicoitem.`prod_Cod` AS ordemservicoitem_prod_Cod,"
                + " veiculo.`vei_Cod` AS veiculo_vei_Cod,"
                + " veiculo.`cli_Cod` AS veiculo_cli_Cod,"
                + " veiculo.`vei_Pla` AS veiculo_vei_Pla,"
                + " veiculo.`vei_Fab` AS veiculo_vei_Fab,"
                + " veiculo.`vei_Mod` AS veiculo_vei_Mod,"
                + " veiculo.`vei_Ano_Fab` AS veiculo_vei_Ano_Fab,"
                + " veiculo.`vei_Com` AS veiculo_vei_Com,"
                + " veiculo.`vei_Cor` AS veiculo_vei_Cor,"
                + " veiculo.`vei_Sta` AS veiculo_vei_Sta,"
                + " veiculo.`vei_Cha` AS veiculo_vei_Cha,"
                + " produto.`prod_Cod` AS produto_prod_Cod,"
                + " produto.`prod_Tip` AS produto_prod_Tip,"
                + " produto.`prod_Des` AS produto_prod_Des,"
                + " ordemservico.`vei_Cod` AS ordemservico_vei_Cod,"
                + " ordemservico.`os_Sta` AS ordemservico_os_Sta,"
                + " ordemservico.`os_Sit` AS ordemservico_os_Sit,"
                + " cliente.`cli_End` AS cliente_cli_End"
                + " FROM"
                + " `ordemservico` ordemservico INNER JOIN `ordemservicoitem` ordemservicoitem ON ordemservico.`os_Cod` = ordemservicoitem.`os_Cod`"
                + " INNER JOIN `veiculo` veiculo ON ordemservico.`vei_Cod` = veiculo.`vei_Cod`"
                + " INNER JOIN `cliente` cliente ON veiculo.`cli_Cod` = cliente.`cli_Cod`"
                + " INNER JOIN `produto` produto ON ordemservicoitem.`prod_Cod` = produto.`prod_Cod`"
                + " WHERE ordemservico.`os_Cod` = " + osNumero;

        if (ordemServicoConect.conecta()) {
            ordemServicoConect.executaSQL(sql);
            try {
                JRResultSetDataSource jrRS = new JRResultSetDataSource(ordemServicoConect.resultset);
                JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/relOrcamento.jasper", new HashMap(), jrRS);
                //JasperViewer jv = new JasperViewer(jasperPrint);
                //jv.setVisible(true);

                //JasperPrintManager.printReport("relatorio/relOrcamento"+".jrprint",true);
                JasperPrintManager.printPage(jasperPrint, 0, true);

            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }
    
    public static void gerarRelatorioServico(int osNumero) {
        String sql;

        sql = "SELECT"
                + " cliente.`cli_Cod` AS cliente_cli_Cod,"
                + " cliente.`cli_Nom` AS cliente_cli_Nom,"
                + " cliente.`cli_Bai` AS cliente_cli_Bai,"
                + " cliente.`cli_Tel_Res` AS cliente_cli_Tel_Res,"
                + " cliente.`cli_Tel_Cel` AS cliente_cli_Tel_Cel,"
                + " cliente.`cli_Sta` AS cliente_cli_Sta,"
                + " ordemservico.`os_Cod` AS ordemservico_os_Cod,"
                + " ordemservico.`os_Dat_Orc` AS ordemservico_os_Dat_Orc,"
                + " ordemservicoitem.`os_Cod` AS ordemservicoitem_os_Cod,"
                + " ordemservicoitem.`os_item_Cod` AS ordemservicoitem_os_item_Cod,"
                + " ordemservicoitem.`os_item_qua` AS ordemservicoitem_os_item_qua,"
                + " ordemservicoitem.`os_item_val` AS ordemservicoitem_os_item_val,"
                + " ordemservicoitem.`prod_Cod` AS ordemservicoitem_prod_Cod,"
                + " veiculo.`vei_Cod` AS veiculo_vei_Cod,"
                + " veiculo.`cli_Cod` AS veiculo_cli_Cod,"
                + " veiculo.`vei_Pla` AS veiculo_vei_Pla,"
                + " veiculo.`vei_Fab` AS veiculo_vei_Fab,"
                + " veiculo.`vei_Mod` AS veiculo_vei_Mod,"
                + " veiculo.`vei_Ano_Fab` AS veiculo_vei_Ano_Fab,"
                + " veiculo.`vei_Com` AS veiculo_vei_Com,"
                + " veiculo.`vei_Cor` AS veiculo_vei_Cor,"
                + " veiculo.`vei_Sta` AS veiculo_vei_Sta,"
                + " veiculo.`vei_Cha` AS veiculo_vei_Cha,"
                + " produto.`prod_Cod` AS produto_prod_Cod,"
                + " produto.`prod_Tip` AS produto_prod_Tip,"
                + " produto.`prod_Des` AS produto_prod_Des,"
                + " ordemservico.`vei_Cod` AS ordemservico_vei_Cod,"
                + " ordemservico.`os_Sta` AS ordemservico_os_Sta,"
                + " ordemservico.`os_Sit` AS ordemservico_os_Sit,"
                + " cliente.`cli_End` AS cliente_cli_End,"
                + " ordemservico.`os_Num` AS ordemservico_os_Num,"
                + " ordemservico.`os_Km_Ent` AS ordemservico_os_Km_Ent,"
                + " ordemservico.`os_Dat_Ent` AS ordemservico_os_Dat_Ent,"
                + " ordemservico.`os_Dat_Sai` AS ordemservico_os_Dat_Sai,"
                + " ordemservico.`os_Tan` AS ordemservico_os_Tan"
                + " FROM"
                + " `ordemservico` ordemservico INNER JOIN `ordemservicoitem` ordemservicoitem ON ordemservico.`os_Cod` = ordemservicoitem.`os_Cod`"
                + " INNER JOIN `veiculo` veiculo ON ordemservico.`vei_Cod` = veiculo.`vei_Cod`"
                + " INNER JOIN `cliente` cliente ON veiculo.`cli_Cod` = cliente.`cli_Cod`"
                + " INNER JOIN `produto` produto ON ordemservicoitem.`prod_Cod` = produto.`prod_Cod`"
                + " WHERE ordemservico.`os_Cod` = " + osNumero;

        if (ordemServicoConect.conecta()) {
            ordemServicoConect.executaSQL(sql);
            try {
                JRResultSetDataSource jrRS = new JRResultSetDataSource(ordemServicoConect.resultset);
                JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/relOrdemServico.jasper", new HashMap(), jrRS);
                //JasperViewer jv = new JasperViewer(jasperPrint);
                //jv.setVisible(true);

                //JasperPrintManager.printReport("relatorio/relOrcamento"+".jrprint",true);
                //JasperPrintManager.printPage(jasperPrint, 0, true);
                JasperPrintManager.printPage(jasperPrint, 0, true);
                
            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

    public static void gerarRelatorioCliente(int osNumero) {
        String sql;

        sql = "SELECT"
                + " cliente.cli_Cod AS cliente_cli_Cod,"
                + " cliente.cli_Nom AS cliente_cli_Nom,"
                + " cliente.cli_Tip_Pes AS cliente_cli_Tip_Pes"
                + " FROM"
                + " cliente cliente "
                + " WHERE cliente.cli_Cod = " + osNumero;

        if (ordemServicoConect.conecta()) {
            ordemServicoConect.executaSQL(sql);
            try {
                JRResultSetDataSource jrRS = new JRResultSetDataSource(ordemServicoConect.resultset);
                //InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("relatorio/relOrcamento.jasper");
                JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/relCliente.jasper", new HashMap(), jrRS);
                JasperViewer jv = new JasperViewer(jasperPrint);
                jv.setVisible(true);
                //JasperViewer.viewReport(jasperPrint);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Relatorios_Oficina/relOrcamento.pdf");

            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {
                ordemServicoConect.desconectar();
            }
        }
    }

}
