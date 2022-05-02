package formularios;

import ClassesDao.ClienteDAO;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author João Carlos Batista
 */
public class FrmListaClienteTeste extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmListaUsuario
     */
    FrmUsuario usuariocad;

    public FrmListaClienteTeste() {
        initComponents();

        tblCliente.getTableHeader().setDefaultRenderer(new principal.EstiloTabelaHeader());
        tblCliente.setDefaultRenderer(Object.class, new principal.EstiloTabelaRenderer());
        tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCliente.getColumnModel().getColumn(0).setMinWidth(0);
        tblCliente.getColumnModel().getColumn(0).setMaxWidth(0);

        limparCampos();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPaneCliente = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(900, 500));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(900, 500));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCabecalho.setBackground(new java.awt.Color(255, 255, 255));
        pnlCabecalho.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Consulta de clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        pnlCabecalho.setPreferredSize(new java.awt.Dimension(860, 110));
        pnlCabecalho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNome.setText("Nome");
        lblNome.setName("lblNome"); // NOI18N
        pnlCabecalho.add(lblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtNome.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtNome.setName("txtNome"); // NOI18N
        pnlCabecalho.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 290, -1));

        btnPesquisar.setBackground(new java.awt.Color(255, 255, 255));
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons/search_P.png"))); // NOI18N
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setContentAreaFilled(false);
        btnPesquisar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons/search_M.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        pnlCabecalho.add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 41, -1, -1));

        pnlPrincipal.add(pnlCabecalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 860, -1));

        jScrollPaneCliente.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneCliente.setAutoscrolls(true);
        jScrollPaneCliente.setName("jScrollPaneCliente"); // NOI18N
        jScrollPaneCliente.setPreferredSize(new java.awt.Dimension(860, 240));

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF/CNPJ", "Tel. Residencial", "Tel. Celular"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCliente.setName("tblCliente"); // NOI18N
        tblCliente.setPreferredSize(new java.awt.Dimension(840, 140));
        jScrollPaneCliente.setViewportView(tblCliente);
        if (tblCliente.getColumnModel().getColumnCount() > 0) {
            tblCliente.getColumnModel().getColumn(0).setResizable(false);
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCliente.getColumnModel().getColumn(1).setResizable(false);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblCliente.getColumnModel().getColumn(2).setResizable(false);
            tblCliente.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblCliente.getColumnModel().getColumn(3).setResizable(false);
            tblCliente.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblCliente.getColumnModel().getColumn(4).setResizable(false);
            tblCliente.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        pnlPrincipal.add(jScrollPaneCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 860, -1));

        btnNovo.setBackground(new java.awt.Color(0, 123, 255));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setText("Novo");
        btnNovo.setBorderPainted(false);
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.setName("btnNovo"); // NOI18N
        btnNovo.setPreferredSize(new java.awt.Dimension(90, 40));
        btnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNovoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNovoMouseExited(evt);
            }
        });
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        btnAlterar.setBackground(new java.awt.Color(40, 167, 69));
        btnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar");
        btnAlterar.setBorderPainted(false);
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setName("btnAlterar"); // NOI18N
        btnAlterar.setPreferredSize(new java.awt.Dimension(90, 40));
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlterarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlterarMouseExited(evt);
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        btnExcluir.setBackground(new java.awt.Color(220, 53, 69));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        btnExcluir.setBorderPainted(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.setName("btnExcluir"); // NOI18N
        btnExcluir.setPreferredSize(new java.awt.Dimension(90, 40));
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExcluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExcluirMouseExited(evt);
            }
        });
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        getContentPane().add(pnlPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        if (txtNome.equals("")) {
            ClienteDAO.listarCliente("");
        } else {
            ClienteDAO.listarCliente(txtNome.getText().trim());
        }
        
        int altura = 16 * tblCliente.getRowCount();
        tblCliente.setPreferredSize(new java.awt.Dimension(840, altura));        
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        FrmProprietarioVeiculo cliente = new FrmProprietarioVeiculo(0, "M");
        frmMenuPrincipal.JDesktopPanePrincipall.add(cliente);
        cliente.setVisible(true);
        cliente.setPosicao();
        dispose();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnNovoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMouseEntered
        btnNovo.setBackground(new Color(0, 80, 217));
        btnNovo.setForeground(new Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 18));
    }//GEN-LAST:event_btnNovoMouseEntered

    private void btnNovoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMouseExited
        btnNovo.setBackground(new Color(0, 123, 255));
        btnNovo.setForeground(new Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 16));
    }//GEN-LAST:event_btnNovoMouseExited

    private void btnAlterarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseEntered
        btnAlterar.setBackground(new Color(33, 126, 56));
        btnAlterar.setForeground(new Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 18));
    }//GEN-LAST:event_btnAlterarMouseEntered

    private void btnAlterarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseExited
        btnAlterar.setBackground(new Color(40, 167, 69));
        btnAlterar.setForeground(new Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 16));
    }//GEN-LAST:event_btnAlterarMouseExited

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        atualizar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseEntered
        btnExcluir.setBackground(new Color(200, 35, 51));
        btnExcluir.setForeground(new Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 18));
    }//GEN-LAST:event_btnExcluirMouseEntered

    private void btnExcluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseExited
        btnExcluir.setBackground(new Color(220, 53, 69));
        btnExcluir.setForeground(new Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16));
    }//GEN-LAST:event_btnExcluirMouseExited

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    public void limparCampos() {
        if (tblCliente.getSelectedRow() > -1) {
            tblCliente.removeRowSelectionInterval(tblCliente.getSelectedRow(), tblCliente.getSelectedRow());
        }
        txtNome.setText("");
        
        ClienteDAO.listarCliente("");
        
        //int altura = 16 * tblCliente.getRowCount();
        //tblCliente.setPreferredSize(new java.awt.Dimension(840, altura));
        //tblCliente.setPreferredSize(new java.awt.Dimension(840, 140));
    
    }
    
    

    public void atualizar() {

        if (tblCliente.getRowCount() > 0) {

            if (tblCliente.getSelectedRow() > -1) {

                int linha = tblCliente.getSelectedRow();
                int id = Integer.parseInt(tblCliente.getValueAt(linha, 0).toString());

                FrmProprietarioVeiculo cliente = new FrmProprietarioVeiculo(id, "M");
                frmMenuPrincipal.JDesktopPanePrincipall.add(cliente);
                cliente.setVisible(true);
                cliente.setPosicao();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela abaixo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há registro na tabela para ser atualizado.");
        }
    }

    public void excluir() {

        if (tblCliente.getRowCount() > 0) {

            if (tblCliente.getSelectedRow() > -1) {

                int linha = tblCliente.getSelectedRow();
                int id = Integer.parseInt(tblCliente.getValueAt(linha, 0).toString());
                String nome = tblCliente.getValueAt(linha, 1).toString();

                int ok = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir"
                        + " cliente " + nome + "?", "Aviso", JOptionPane.YES_NO_OPTION);

                if (ok == 0) {
                    ClienteDAO.excluir(id);
                    ClienteDAO.listarCliente("");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione o cliente na tabela abaixo para ser excluído.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há registro na tabela para ser excluído.");
        }
    }    
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JScrollPane jScrollPaneCliente;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlPrincipal;
    public static javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
