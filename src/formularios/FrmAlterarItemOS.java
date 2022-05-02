/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import Classes.ItemOS;
import ClassesDao.OrdemServicoItemDAO;
import Formatadores.MonetarioDocument;
import Formatadores.TratarMoeda;
import static formularios.FrmOrdemServico.ordemServicoConect;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author João Carlos Batista
 */
public class FrmAlterarItemOS extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmOrdemServico
     */
    int os_Cod = 0;
    int os_Item_Cod = 0;
    String origem;
    
    public FrmAlterarItemOS(int cod_Os, int cod_Item, String origem) {
        initComponents();
        
        this.os_Cod      = cod_Os;
        this.os_Item_Cod = cod_Item;
        this.origem = origem;
        jtxtValor.setDocument(new MonetarioDocument());
       
        carregarOrdemServico(os_Cod, os_Item_Cod);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlprincipal = new javax.swing.JPanel();
        pnlVeiculo = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        jtxtValor = new javax.swing.JFormattedTextField();
        btnInserirItem = new javax.swing.JButton();
        btnLimparCampos = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setPreferredSize(new java.awt.Dimension(708, 175));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlprincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlprincipal.setName("pnlprincipal"); // NOI18N
        pnlprincipal.setPreferredSize(new java.awt.Dimension(870, 540));
        pnlprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        pnlVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        pnlVeiculo.setName("pnlVeiculo"); // NOI18N
        pnlVeiculo.setPreferredSize(new java.awt.Dimension(860, 143));
        pnlVeiculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescricao.setText("Descrição");
        lblDescricao.setName("lblDescricao"); // NOI18N
        pnlVeiculo.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtDescricao.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtDescricao.setName("txtDescricao"); // NOI18N
        txtDescricao.setPreferredSize(new java.awt.Dimension(230, 25));
        pnlVeiculo.add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 450, -1));

        lblQuantidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQuantidade.setText("Quantidade");
        lblQuantidade.setName("lblQuantidade"); // NOI18N
        pnlVeiculo.add(lblQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        txtQuantidade.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtQuantidade.setName("txtQuantidade"); // NOI18N
        txtQuantidade.setPreferredSize(new java.awt.Dimension(120, 25));
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusLost(evt);
            }
        });
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyPressed(evt);
            }
        });
        pnlVeiculo.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 70, -1));

        lblValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValor.setText("Valor Unit.");
        lblValor.setName("lblValor"); // NOI18N
        pnlVeiculo.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        jtxtValor.setName("jtxtValorSugerido"); // NOI18N
        jtxtValor.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlVeiculo.add(jtxtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 110, -1));

        pnlprincipal.add(pnlVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 670, 80));

        btnInserirItem.setText("Salvar");
        btnInserirItem.setName("btnInserirItem"); // NOI18N
        btnInserirItem.setPreferredSize(new java.awt.Dimension(150, 30));
        btnInserirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirItemActionPerformed(evt);
            }
        });
        pnlprincipal.add(btnInserirItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 70, 30));

        btnLimparCampos.setText("Fechar");
        btnLimparCampos.setName("btnLimparCampos"); // NOI18N
        btnLimparCampos.setPreferredSize(new java.awt.Dimension(150, 30));
        btnLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCamposActionPerformed(evt);
            }
        });
        pnlprincipal.add(btnLimparCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 70, 30));

        getContentPane().add(pnlprincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusLost

    }//GEN-LAST:event_txtQuantidadeFocusLost

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void btnInserirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirItemActionPerformed
        
        ItemOS itemos = new ItemOS();
        itemos.setOs_Cod(os_Cod);
        itemos.setOs_Item_Cod(os_Item_Cod);
        itemos.setOs_Item_Qua(Integer.parseInt(txtQuantidade.getText()));
        itemos.setOs_Item_Val(TratarMoeda.Moeda(jtxtValor.getText()));
        
        OrdemServicoItemDAO.alterarOrdemServicoItem(itemos);
        
        if(origem == "S"){
            OrdemServicoItemDAO.listarOrdemServicoItem(os_Cod, "S");
            OrdemServicoItemDAO.listarOrdemServicoItemServico(os_Cod, "S");
        }
        else{
            OrdemServicoItemDAO.listarOrdemServicoItem(os_Cod, "O");
            OrdemServicoItemDAO.listarOrdemServicoItemServico(os_Cod, "O");
        }
        
    }//GEN-LAST:event_btnInserirItemActionPerformed

    private void btnLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCamposActionPerformed
        
        dispose();        
    }//GEN-LAST:event_btnLimparCamposActionPerformed

    
    public void carregarOrdemServico(int os_Cod, int os_Item_Cod) {

         String sql = "SELECT OS.os_Cod, OS.os_item_Cod, OS.os_item_qua, OS.os_item_val, PRO.prod_Des "
                + "FROM ordemservicoitem AS OS "
                 + "JOIN produto AS PRO "
                 + "ON OS.prod_Cod = PRO.prod_Cod "
                 + "WHERE OS.os_Cod = " + os_Cod
                 + " AND os_item_Cod = " + os_Item_Cod;

        if (ordemServicoConect.conecta()) {
            try {
                ordemServicoConect.executaSQL(sql);
                while (ordemServicoConect.resultset.next()) {
                    txtDescricao.setText(ordemServicoConect.resultset.getString("PRO.prod_Des"));
                    txtQuantidade.setText(ordemServicoConect.resultset.getString("OS.os_item_qua"));
                    jtxtValor.setText(TratarMoeda.FormatarMoeda(ordemServicoConect.resultset.getDouble("OS.os_item_val")));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o O.S." + ex);
            }

            ordemServicoConect.desconectar();
        }
    }    
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInserirItem;
    private javax.swing.JButton btnLimparCampos;
    private javax.swing.JFormattedTextField jtxtValor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlVeiculo;
    private javax.swing.JPanel pnlprincipal;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables

}
