/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.ItemOS;
import Classes.OrdemServico;
import Classes.StatusRegistro;
import Classes.Veiculo;
import static ClassesDao.ClienteDAO.clienteConect;
import static ClassesDao.PecaDAO.ProdutoConect;
import static ClassesDao.VeiculoDAO.veiculoConect;
import ClassesEnum.ServicoProduto;
import ConexaoBancoDados.ConexaoBancoDados;
import formularios.FrmInserirPecaOS;
import formularios.FrmInserirServicoOS;
import formularios.FrmListaPeca;
import formularios.FrmOrcamento;
import formularios.FrmOrdemServico;
import formularios.FrmProprietarioVeiculo;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author João Carlos Batista
 */
public class OrdemServicoItemDAO {

    static PreparedStatement ps;
    static ConexaoBancoDados ordemServicoItensConect = new ConexaoBancoDados();

    public static OrdemServico carregarOrdemServicoItem(int idOrdemServico) {
        OrdemServico ordemServico = new OrdemServico();

        String sql = "SELECT cod_OS, os_Dat_Orc, vei_Pla, vei_Fab, vei_Mod, vei_Ano_Fab, vei_Cor, cli_Nom "
                + "FROM listaordemservico "
                + "WHERE cod_OS = " + idOrdemServico;

        if (ordemServicoItensConect.conecta()) {
            try {
                ordemServicoItensConect.executaSQL(sql);
                while (ordemServicoItensConect.resultset.next()) {
                    ordemServico.setOs_Cod(ordemServicoItensConect.resultset.getInt("os_Cod"));
                    ordemServico.setVei_Cod(ordemServicoItensConect.resultset.getInt("vei_Cod"));
                    ordemServico.setOs_Dat_Orc(ordemServicoItensConect.resultset.getDate("os_Dat_Orc"));
                    ordemServico.setOs_Dat_Ent(ordemServicoItensConect.resultset.getDate("os_Dat_Ent"));
                    ordemServico.setOs_Dat_Sai(ordemServicoItensConect.resultset.getDate("os_Dat_Ent"));
                    ordemServico.setOs_Sit(ordemServicoItensConect.resultset.getString("os_Sit"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o O.S." + ex);
            }

            ordemServicoItensConect.desconectar();
        }
        return ordemServico;
    }

    public static void listarOrdemServicoItem(int idOrdemServico, String origem) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        if(origem == "O"){
            FrmOrcamento.TotalPeca = 0;
            modelo = (DefaultTableModel) FrmOrcamento.tblItens.getModel();        
            modelo.setNumRows(0);
        }
        else{
            FrmOrdemServico.TotalPeca = 0;
            modelo = (DefaultTableModel) FrmOrdemServico.tblItens.getModel();        
            modelo.setNumRows(0);            
        }
        

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
            sql = "SELECT OSI.os_Cod, OSI.os_item_Cod, OSI.os_item_qua, OSI.os_item_val, PROD.prod_Cod, PROD.prod_Des "
                + "FROM ordemservicoitem as OSI "
                + "JOIN produto as PROD "
                + "ON OSI.prod_Cod = PROD.prod_Cod "
                + "WHERE OSI.os_Cod = " + idOrdemServico
                + " AND PROD.prod_Tip = "+ "'"+ ServicoProduto.PRODUTO.toString() + "'";

        String dados[] = new String[8];
        double subTotal = 0;
        double total = 0;
        int quantidade = 0;
        int quantItem = 0;
        DecimalFormat formatador = new DecimalFormat("0.00");
        if (ordemServicoItensConect.conecta()) {
            ordemServicoItensConect.executaSQL(sql);
            try {                
                
                while (ordemServicoItensConect.resultset.next()) {
                    quantItem += 1;
                    dados[0] = ordemServicoItensConect.resultset.getString("OSI.os_Cod");
                    dados[1] = ordemServicoItensConect.resultset.getString("OSI.os_item_Cod");
                    dados[2] = ordemServicoItensConect.resultset.getString("PROD.prod_Cod");
                    dados[3] = Integer.toString(quantItem);
                    dados[4] = ordemServicoItensConect.resultset.getString("PROD.prod_Des");
                    dados[5] = ordemServicoItensConect.resultset.getString("OSI.os_item_qua");                    
                    dados[6] = ordemServicoItensConect.resultset.getString("OSI.os_item_val");
                    quantidade = ordemServicoItensConect.resultset.getInt("OSI.os_item_qua");
                    subTotal = ordemServicoItensConect.resultset.getDouble("OSI.os_item_val");                                        
                    subTotal = quantidade * subTotal;
                    
                    if(origem == "O"){
                        FrmOrcamento.TotalPeca += subTotal;
                    }
                    else{
                        FrmOrdemServico .TotalPeca += subTotal;
                    }
                    
                    dados[7] = formatador.format(subTotal);
                    subTotal = 0;
                    modelo.addRow(dados);
                    
                    //FrmOrcamento.lblTotal.setText(formatador.format(total));
                }
            } catch (SQLException ex) {
                if(origem == "O"){
                    Logger.getLogger(FrmOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                else{
                    Logger.getLogger(FrmOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(origem == "O"){
                FrmOrcamento.lblTotal.setText("R$" + formatador.format((FrmOrcamento.TotalPeca + FrmOrcamento.TotalServico)));
            }
            else{
                FrmOrdemServico.lblTotal.setText("R$" + formatador.format((FrmOrdemServico.TotalPeca + FrmOrdemServico.TotalServico)));
            }
            ordemServicoItensConect.desconectar();
        }
    }
    
    
    public static void listarOrdemServicoItemServico(int idOrdemServico, String origem) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        if(origem == "O"){
            FrmOrcamento.TotalServico = 0;
            modelo = (DefaultTableModel) FrmOrcamento.tblItensServico.getModel();
            modelo.setNumRows(0);
        }
        else{
            FrmOrdemServico.TotalServico = 0;
            modelo = (DefaultTableModel) FrmOrdemServico.tblItensServico.getModel();
            modelo.setNumRows(0);        
        }

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
            sql = "SELECT OSI.os_Cod, OSI.os_item_Cod, OSI.os_item_qua, OSI.os_item_val, PROD.prod_Cod, PROD.prod_Des "
                + "FROM ordemservicoitem as OSI "
                + "JOIN produto as PROD "
                + "ON OSI.prod_Cod = PROD.prod_Cod "
                + "WHERE OSI.os_Cod = " + idOrdemServico
                + " AND PROD.prod_Tip = "+ "'"+ ServicoProduto.SERVICO.toString() + "'";

        String dados[] = new String[8];
        double subTotal = 0;
        double total = 0;
        int quantidade = 0;
        int quantItem = 0;        
        
        DecimalFormat formatador = new DecimalFormat("0.00");
        if (ordemServicoItensConect.conecta()) {
            ordemServicoItensConect.executaSQL(sql);
            try {                                               
                
                while (ordemServicoItensConect.resultset.next()) {
                    quantItem += 1;
                    dados[0] = ordemServicoItensConect.resultset.getString("OSI.os_Cod");
                    dados[1] = ordemServicoItensConect.resultset.getString("OSI.os_item_Cod");
                    dados[2] = ordemServicoItensConect.resultset.getString("PROD.prod_Cod");
                    dados[3] = Integer.toString(quantItem);
                    dados[4] = ordemServicoItensConect.resultset.getString("PROD.prod_Des");
                    dados[5] = ordemServicoItensConect.resultset.getString("OSI.os_item_qua");                    
                    dados[6] = ordemServicoItensConect.resultset.getString("OSI.os_item_val");
                    quantidade = ordemServicoItensConect.resultset.getInt("OSI.os_item_qua");
                    subTotal = ordemServicoItensConect.resultset.getDouble("OSI.os_item_val");
                    subTotal = quantidade * subTotal;
                    
                    if(origem == "O"){
                        FrmOrcamento.TotalServico += subTotal;
                    }
                    else{
                        FrmOrdemServico.TotalServico += subTotal;
                    }
                    //total += subTotal;
                    dados[7] = formatador.format(subTotal);
                    subTotal = 0;
                    modelo.addRow(dados);
                                        
                }
            } catch (SQLException ex) {
                if(origem == "O"){
                    Logger.getLogger(FrmOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                else{
                    Logger.getLogger(FrmOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(origem == "O"){
                FrmOrcamento.lblTotal.setText("R$" + formatador.format(FrmOrcamento.TotalPeca + FrmOrcamento.TotalServico));
            }
            else{
                FrmOrdemServico.lblTotal.setText("R$" + formatador.format(FrmOrdemServico.TotalPeca + FrmOrdemServico.TotalServico));
            }
            ordemServicoItensConect.desconectar();
        }
    }    

    public static void gravarOrdemServicoItem(ItemOS itemos) {
        String sql;
        sql = "INSERT INTO ordemservicoitem(os_Cod, os_item_Cod, os_item_qua, os_item_val, prod_Cod)"
                + "VALUES(?,?,?,?,?)";

        if (ordemServicoItensConect.conecta()) {
            try {
                ps = ordemServicoItensConect.conexao.prepareStatement(sql);
                ps.setInt(1, itemos.getOs_Cod());
                ps.setInt(2, itemos.getOs_Item_Cod());
                ps.setInt(3, itemos.getOs_Item_Qua());
                ps.setDouble(4, itemos.getOs_Item_Val());
                ps.setInt(5, itemos.getOs_Item_Prod_Cod());                
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Item inserido na O.S.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo item." + ex);
            }
            ordemServicoItensConect.desconectar();
        }
    }
    
    public static void gravarOrdemServicoItemServico(ItemOS itemos) {
        String sql = "";
        sql = "INSERT INTO ordemservicoitem(os_Cod, os_item_Cod, os_item_qua, os_item_val, prod_Cod)"
                + "VALUES(?,?,?,?,?)";

        if (ordemServicoItensConect.conecta()) {
            try {
                ps = ordemServicoItensConect.conexao.prepareStatement(sql);
                ps.setInt(1, itemos.getOs_Cod());
                ps.setInt(2, itemos.getOs_Item_Cod());
                ps.setInt(3, itemos.getOs_Item_Qua());
                ps.setDouble(4, itemos.getOs_Item_Val());
                ps.setInt(5, itemos.getOs_Item_Prod_Cod());                
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Serviço inserido na O.S.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o novo serviço." + ex);
            }

            ordemServicoItensConect.desconectar();
        }
    }    

    public static void alterarOrdemServico(OrdemServico ordemServico) {

        String sql = "";
        sql = "UPDATE ordemservico SET "
                + "os_Cod       =?,"
                + "vei_Cod      =?,"
                + "os_Dat_Orc   =?,"
                + "os_Dat_Ent   =?,"
                + "os_Dat_Sai   =?,"
                + "os_Sit       =?"
                + "WHERE os_Cod =?";

        if (ordemServicoItensConect.conecta()) {
            try {
                ps = ordemServicoItensConect.conexao.prepareStatement(sql);
                ps.setInt(1, ordemServico.getOs_Cod());
                ps.setInt(2, ordemServico.getVei_Cod());
                ps.setDate(3, (Date) ordemServico.getOs_Dat_Orc());
                ps.setDate(4, (Date) ordemServico.getOs_Dat_Ent());
                ps.setDate(5, (Date) ordemServico.getOs_Dat_Sai());
                ps.setString(6, ordemServico.getOs_Sit());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar a O.S." + ex);
            }

            ordemServicoItensConect.desconectar();
        }
    }
    
    public static void alterarOrdemServicoItem(ItemOS itemOS) {

        String sql = "";
        sql = "UPDATE ordemservicoitem SET  "
                + "os_item_qua      =?, "
                + "os_item_Val      =?  "
                + "WHERE os_Cod     =? "
                + "AND os_item_Cod  =? ";

        if (ordemServicoItensConect.conecta()) {
            try {
                ps = ordemServicoItensConect.conexao.prepareStatement(sql);
                ps.setInt(1, itemOS.getOs_Item_Qua());
                ps.setDouble(2, itemOS.getOs_Item_Val());
                ps.setInt(3, itemOS.getOs_Cod());
                ps.setInt(4, itemOS.getOs_Item_Cod());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S atualizado com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar a O.S." + ex);
            }

            ordemServicoItensConect.desconectar();
        }
    }    
    
    public static void excluirVeiculo(int idOrdemServico) {

        String sql = "";
        sql = "DELETE FROM ordemservico WHERE os_Cod=?";

        if (ordemServicoItensConect.conecta()) {
            try {
                ps = ordemServicoItensConect.conexao.prepareStatement(sql);
                ps.setInt(1, idOrdemServico);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "O.S excluída com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir a O.S." + ex);
            }

            ordemServicoItensConect.desconectar();
        }
    }
        
    public static Veiculo PesquisarVeiculoVeiculo(String placa) {
        Veiculo veiculo = new Veiculo();

        String sql = "SELECT CLI.cli_cod, CLI.cli_Nom, "
                    + "VEI.vei_Cod, VEI.vei_Pla, VEI.vei_Fab, "
                    + "VEI.vei_Mod, VEI.vei_Ano_Fab, VEI.vei_Com, VEI.vei_Cor "
                    + "FROM veiculo AS VEI "
                    + "JOIN cliente AS CLI "
                    + "ON VEI.cli_Cod = CLI.cli_Cod "
                    + "WHERE VEI.vei_Pla = '" + placa +"'";

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
            }

            veiculoConect.desconectar();
        }
        return veiculo;
    }

     public static void listarPeca(String descricao){
        DefaultTableModel modelo = (DefaultTableModel) FrmInserirPecaOS.tblPeca.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql;
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
                Logger.getLogger(FrmInserirPecaOS.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProdutoConect.desconectar();
        }
    }
          
     public static void listarServico(String descricao){
        DefaultTableModel modelo = (DefaultTableModel) FrmInserirServicoOS.tblServico.getModel();
        modelo.setNumRows(0);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (descricao.equals("")) {
            sql = "SELECT prod_Cod, prod_Des, prod_Val_Ven "
                    + "FROM produto WHERE prod_Tip = " + "'" + ServicoProduto.SERVICO.toString() + "' "
                    + "AND prod_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "ORDER BY prod_Des";
        } else {
            sql = "SELECT prod_Cod, prod_Des, prod_Val_Ven "
                    + "FROM produto WHERE  prod_Tip = " + "'" + ServicoProduto.SERVICO.toString() + "' "
                    + "AND prod_Sta = " + "'" + StatusRegistro.ATIVO.toString() + "' "
                    + "AND prod_Des LIKE '%" + descricao.trim() + "%' "
                    + "ORDER BY prod_Des";
        }

        String dados[] = new String[3];

        if (ProdutoConect.conecta()) {
            try {
                ProdutoConect.executaSQL(sql);
                while (ProdutoConect.resultset.next()) {
                    dados[0] = ProdutoConect.resultset.getString("prod_Cod");
                    dados[1] = ProdutoConect.resultset.getString("prod_Des");                    
                    dados[2] = ProdutoConect.resultset.getString("prod_Val_Ven");
                    modelo.addRow(dados);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmInserirServicoOS.class.getName()).log(Level.SEVERE, null, ex);
            }

            ProdutoConect.desconectar();
        }
    }
     
    public static void excluir(int cod_Os, int cod_Item) {
        String sql = "";
        sql = "DELETE FROM ordemservicoitem "
                + "WHERE os_Cod = ? "
                + "AND os_item_Cod = ?";
        if (clienteConect.conecta()) {
            try {
                ps = clienteConect.conexao.prepareStatement(sql);
                ps.setInt(1, cod_Os);
                ps.setInt(2, cod_Item);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o item." + ex);
            }

            clienteConect.desconectar();
        }
    }     
    
}
