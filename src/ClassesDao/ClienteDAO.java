package ClassesDao;

import Classes.Cliente;
import ClassesEnum.StatusRegistro;
import ConexaoBancoDados.ConexaoBancoDados;
import Formatadores.FormatadorMascara;
import formularios.FrmInserirClienteOS;
import formularios.FrmListaCliente;
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
public class ClienteDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados clienteConect = new ConexaoBancoDados();

    public static Cliente carregarCliente(int idCliente) {
        Cliente cliente = new Cliente();

        String sql = "SELECT cli_Nom, cli_tip_Pes, cli_Cpf_Cnpj, cli_End, cli_Bai,"
                + "cli_Tel_Res, cli_Tel_Cel, cli_Ema FROM Cliente WHERE cli_cod =" + idCliente;

        if (clienteConect.conecta()) {
            try {
                clienteConect.executaSQL(sql);
                while (clienteConect.resultset.next()) {
                    cliente.setCli_Nom(clienteConect.resultset.getString("cli_Nom"));
                    cliente.setCli_Tip_Pes(clienteConect.resultset.getString("cli_Tip_Pes"));

                    if (clienteConect.resultset.getString("cli_Cpf_Cnpj").equals("")) {
                        cliente.setCli_Cpf_Cnpj(clienteConect.resultset.getString("cli_Cpf_Cnpj"));
                    } else {
                        if (clienteConect.resultset.getString("cli_Tip_Pes").equals("FÍSICA")) {
                            cliente.setCli_Cpf_Cnpj(FormatadorMascara.formatMascCpf(clienteConect.resultset.getString("cli_Cpf_Cnpj")));
                        } else {
                            cliente.setCli_Cpf_Cnpj(FormatadorMascara.formatMascCnpj(clienteConect.resultset.getString("cli_Cpf_Cnpj")));
                        }
                    }
                    cliente.setCli_End(clienteConect.resultset.getString("cli_End"));
                    cliente.setCli_Bai(clienteConect.resultset.getString("cli_Bai"));
                    cliente.setCli_Tel_Res(clienteConect.resultset.getString("cli_Tel_Res"));
                    cliente.setCli_Tel_Cel(clienteConect.resultset.getString("cli_Tel_Cel"));
                    cliente.setCli_Ema(clienteConect.resultset.getString("cli_Ema"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o cliente: " + ex);
            }
            clienteConect.desconectar();
        }
        return cliente;
    }

    public static void listarCliente(String nome) {
        DefaultTableModel modelo = (DefaultTableModel) FrmListaCliente.tblCliente.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (nome.equals("")) {
            sql = "SELECT * FROM cliente WHERE cli_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "'"
                    + " ORDER BY cli_Nom";
        } else {
            sql = "SELECT * FROM cliente WHERE cli_Sta =" + "'" + StatusRegistro.ATIVO.toString() + "'"
                    + "AND cli_Nom LIKE '%" + nome.trim() + "%' ORDER BY cli_Nom";
        }

        String dados[] = new String[5];

        if (clienteConect.conecta()) {
            try {
                clienteConect.executaSQL(sql);
                while (clienteConect.resultset.next()) {
                    dados[0] = clienteConect.resultset.getString("cli_Cod");
                    dados[1] = clienteConect.resultset.getString("cli_Nom");

                    if (clienteConect.resultset.getString("cli_Cpf_Cnpj").equals("")) {
                        dados[2] = clienteConect.resultset.getString("cli_Cpf_Cnpj");
                    } else {

                        if (clienteConect.resultset.getString("cli_Tip_Pes").equals("FÍSICA")) {
                            dados[2] = FormatadorMascara.formatMascCpf(clienteConect.resultset.getString("cli_Cpf_Cnpj"));
                        } else {
                            dados[2] = FormatadorMascara.formatMascCnpj(clienteConect.resultset.getString("cli_Cpf_Cnpj"));
                        }
                    }

                    if (clienteConect.resultset.getString("cli_Tel_Res").equals("")) {
                        dados[3] = clienteConect.resultset.getString("cli_Tel_Res");
                    } else {
                        dados[3] = FormatadorMascara.formatMascTelRes(clienteConect.resultset.getString("cli_Tel_Res"));
                    }

                    if (clienteConect.resultset.getString("cli_Tel_Cel").equals("")) {
                        dados[4] = clienteConect.resultset.getString("cli_Tel_Cel");
                    } else {
                        dados[4] = FormatadorMascara.formatMascTelCel(clienteConect.resultset.getString("cli_Tel_Cel"));
                    }
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmListaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            clienteConect.desconectar();
        }
    }

    public static void gravarCliente(Cliente cliente) {
        String sql = "";
        sql = "INSERT INTO cliente(cli_Cod, cli_Nom, cli_Tip_Pes, cli_Cpf_Cnpj, cli_End, cli_Bai, cli_Tel_Res,"
                + " cli_Tel_Cel, cli_Ema, cli_Sta)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        if (clienteConect.conecta()) {
            try {
                ps = clienteConect.conexao.prepareStatement(sql);
                ps.setInt(1, cliente.getCli_Cod());
                ps.setString(2, cliente.getCli_Nom().trim());
                ps.setString(3, cliente.getCli_Tip_Pes());
                ps.setString(4, FormatadorMascara.limpaMasc_Cpf_Cnpj(cliente.getCli_Cpf_Cnpj()));
                ps.setString(5, cliente.getCli_End().trim());
                ps.setString(6, cliente.getCli_Bai().trim());
                ps.setString(7, FormatadorMascara.limpaMascTel(cliente.getCli_Tel_Res()).trim());
                ps.setString(8, FormatadorMascara.limpaMascTel(cliente.getCli_Tel_Cel()).trim());
                ps.setString(9, cliente.getCli_Ema().trim());
                ps.setString(10, StatusRegistro.ATIVO.toString());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo cliente." + ex);
            }
            clienteConect.desconectar();
        }
    }

    public static void alterarCliente(Cliente cliente) {
        String sql = "";
        sql = "UPDATE cliente SET "
                + "cli_Nom      =?,"
                + "cli_tip_Pes  =?,"
                + "cli_Cpf_Cnpj =?,"
                + "cli_End      =?,"
                + "cli_Bai      =?,"
                + "cli_Tel_Res  =?,"
                + "cli_Tel_Cel  =?,"
                + "cli_Ema=? WHERE cli_Cod=?";

        if (clienteConect.conecta()) {
            try {
                ps = clienteConect.conexao.prepareStatement(sql);
                ps.setString(1, cliente.getCli_Nom().trim());
                ps.setString(2, cliente.getCli_Tip_Pes());
                ps.setString(3, FormatadorMascara.limpaMasc_Cpf_Cnpj(cliente.getCli_Cpf_Cnpj()).trim());
                ps.setString(4, cliente.getCli_End().trim());
                ps.setString(5, cliente.getCli_Bai().trim());
                ps.setString(6, FormatadorMascara.limpaMascTel(cliente.getCli_Tel_Res()).trim());
                ps.setString(7, FormatadorMascara.limpaMascTel(cliente.getCli_Tel_Cel()).trim());
                ps.setString(8, cliente.getCli_Ema().trim());
                ps.setInt(9, cliente.getCli_Cod());
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o cadastro." + ex);
            }
            clienteConect.desconectar();
        }
    }

    public static void excluir(int id) {
        String sql = "";
        sql = "UPDATE cliente SET "
                + "cli_Sta =? WHERE cli_Cod=?";
        if (clienteConect.conecta()) {
            try {
                ps = clienteConect.conexao.prepareStatement(sql);
                ps.setString(1, StatusRegistro.INATIVO.toString());
                ps.setInt(2, id);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o cliente." + ex);
            }
            clienteConect.desconectar();
        }
    }

    public static void listarClienteOS(String nome) {
        DefaultTableModel modelo = (DefaultTableModel) FrmInserirClienteOS.tblCliente.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (nome.equals("")) {
            sql = "SELECT * FROM cliente WHERE cli_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "'"
                    + " ORDER BY cli_Nom";
        } else {
            sql = "SELECT * FROM cliente WHERE cli_Sta =" + "'" + StatusRegistro.ATIVO.toString() + "'"
                    + "AND cli_Nom LIKE '%" + nome.trim() + "%' ORDER BY cli_Nom";
        }

        String dados[] = new String[5];

        if (clienteConect.conecta()) {
            try {
                clienteConect.executaSQL(sql);
                while (clienteConect.resultset.next()) {
                    dados[0] = clienteConect.resultset.getString("cli_Cod");
                    dados[1] = clienteConect.resultset.getString("cli_Nom");

                    if (clienteConect.resultset.getString("cli_Cpf_Cnpj").equals("")) {
                        dados[2] = clienteConect.resultset.getString("cli_Cpf_Cnpj");
                    } else {
                        if (clienteConect.resultset.getString("cli_Tip_Pes").equals("FÍSICA")) {
                            dados[2] = FormatadorMascara.formatMascCpf(clienteConect.resultset.getString("cli_Cpf_Cnpj"));
                        } else {
                            dados[2] = FormatadorMascara.formatMascCnpj(clienteConect.resultset.getString("cli_Cpf_Cnpj"));
                        }
                    }

                    if (clienteConect.resultset.getString("cli_Tel_Res").equals("")) {
                        dados[3] = clienteConect.resultset.getString("cli_Tel_Res");
                    } else {
                        dados[3] = FormatadorMascara.formatMascTelRes(clienteConect.resultset.getString("cli_Tel_Res"));
                    }

                    if (clienteConect.resultset.getString("cli_Tel_Cel").equals("")) {
                        dados[4] = clienteConect.resultset.getString("cli_Tel_Cel");
                    } else {
                        dados[4] = FormatadorMascara.formatMascTelCel(clienteConect.resultset.getString("cli_Tel_Cel"));
                    }
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmInserirClienteOS.class.getName()).log(Level.SEVERE, null, ex);
            }
            clienteConect.desconectar();
        }
    }
}
