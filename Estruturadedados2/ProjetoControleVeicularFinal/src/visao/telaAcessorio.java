/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Acessorio;
import modelos.IAcessorioCRUD;
import modelos.VinculoCarroAcessorio;
import modelos.IVinculoCarroAcessorioCRUD;
import persistencia.AcessorioDAO;
import persistencia.VinculoCarroAcessorioDAO;

/**
 *
 * @author Computador
 */
public class telaAcessorio extends javax.swing.JFrame {


    private IAcessorioCRUD acessorioBD = null;
    private IVinculoCarroAcessorioCRUD vinculoBD = null;
    
    public telaAcessorio() {
        initComponents();
        setLocationRelativeTo(null);
        try {
            vinculoBD = new VinculoCarroAcessorioDAO();
            acessorioBD = new AcessorioDAO();
            mostrarAcessoriosNaGrid();
            mostrarVinculoNaGrid(); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar: " + erro.getMessage());
        }
    }

       private void limparTela() {
        jTextFieldDescricaoAcessorio.setText("");
        jTextFieldIDAcessorio.setText("");
        jTextFieldIDVinculo.setText("");
        jTextFieldNomeAcessorio.setText("");
        jTextFieldPlacaVinculo.setText("");
    }

private void mostrarAcessoriosNaGrid() {
    try {
        ArrayList<Acessorio> listaDeAcessorios = acessorioBD.obterListaDeAcessorios();
        DefaultTableModel model = (DefaultTableModel) jTableAcessorios.getModel();
        model.setNumRows(0);
        
        for(Acessorio acessorio : listaDeAcessorios) {
            String[] linha = new String[3];
            linha[0] = String.valueOf(acessorio.getId());
            linha[1] = acessorio.getNome();
            linha[2] = acessorio.getDescricao();
            model.addRow(linha);
        }
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
    }
}
      
    private void mostrarVinculoNaGrid() {
        try {
            ArrayList<VinculoCarroAcessorio> listaDeVinculos = vinculoBD.obterListaDeVinculos();
            DefaultTableModel model = (DefaultTableModel) jTableVinculo.getModel();
            model.setNumRows(0);
            
            for(VinculoCarroAcessorio VinculoCarroAcessorio : listaDeVinculos) {
                String[] linha = new String[2];
                linha[0] = String.valueOf(VinculoCarroAcessorio.getIdAcessorio());
                linha[1] = VinculoCarroAcessorio.getPlacaVeiculo();
                model.addRow(linha);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescricaoAcessorio = new javax.swing.JTextField();
        jTextFieldNomeAcessorio = new javax.swing.JTextField();
        jTextFieldIDAcessorio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAcessorios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPlacaVinculo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIDVinculo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAlterarVinculo = new javax.swing.JButton();
        jButtonIncluirVinculo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVinculo = new javax.swing.JTable();
        jButtonIncluirAcessorio = new javax.swing.JButton();
        jButtonAlterarAcessorio = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/cabo-usb.png"))); // NOI18N
        jLabel1.setText("CADASTRO DE ACESSÓRIO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("NOME:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("DESCRIÇÃO:");

        jTextFieldDescricaoAcessorio.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldDescricaoAcessorio.setForeground(new java.awt.Color(51, 51, 51));

        jTextFieldNomeAcessorio.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldNomeAcessorio.setForeground(new java.awt.Color(51, 51, 51));

        jTextFieldIDAcessorio.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldIDAcessorio.setForeground(new java.awt.Color(51, 51, 51));

        jTableAcessorios.setBackground(new java.awt.Color(204, 204, 204));
        jTableAcessorios.setForeground(new java.awt.Color(51, 51, 51));
        jTableAcessorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRIÇÃO"
            }
        ));
        jScrollPane1.setViewportView(jTableAcessorios);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID DO ACESSORIO:");

        jTextFieldPlacaVinculo.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldPlacaVinculo.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PLACA:");

        jTextFieldIDVinculo.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldIDVinculo.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Vinculo Carro Acessorio");

        jButtonAlterarVinculo.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAlterarVinculo.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAlterarVinculo.setText("ALTERAR");
        jButtonAlterarVinculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarVinculoActionPerformed(evt);
            }
        });

        jButtonIncluirVinculo.setBackground(new java.awt.Color(204, 204, 204));
        jButtonIncluirVinculo.setForeground(new java.awt.Color(51, 51, 51));
        jButtonIncluirVinculo.setText("INCLUIR");
        jButtonIncluirVinculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirVinculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPlacaVinculo, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(jTextFieldIDVinculo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonIncluirVinculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAlterarVinculo)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIDVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPlacaVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterarVinculo)
                    .addComponent(jButtonIncluirVinculo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableVinculo.setBackground(new java.awt.Color(204, 204, 204));
        jTableVinculo.setForeground(new java.awt.Color(51, 51, 51));
        jTableVinculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID ACESSORIO", "PLACA CARRO"
            }
        ));
        jScrollPane3.setViewportView(jTableVinculo);

        jButtonIncluirAcessorio.setBackground(new java.awt.Color(204, 204, 204));
        jButtonIncluirAcessorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonIncluirAcessorio.setForeground(new java.awt.Color(51, 51, 51));
        jButtonIncluirAcessorio.setText("INCLUIR");
        jButtonIncluirAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirAcessorioActionPerformed(evt);
            }
        });

        jButtonAlterarAcessorio.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAlterarAcessorio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonAlterarAcessorio.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAlterarAcessorio.setText("ALTERAR");
        jButtonAlterarAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarAcessorioActionPerformed(evt);
            }
        });

        jButtonVoltar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonVoltar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonVoltar.setForeground(new java.awt.Color(255, 51, 51));
        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonIncluirAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterarAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(529, 529, 529)
                        .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldIDAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNomeAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDescricaoAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldNomeAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldDescricaoAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIncluirAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAlterarAcessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonIncluirAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirAcessorioActionPerformed
        try {
            String nome = jTextFieldNomeAcessorio.getText();
            String descricao = jTextFieldDescricaoAcessorio.getText();
            Acessorio acessorio = new Acessorio(0, nome, descricao);
            acessorioBD.incluir(acessorio);
            limparTela();
            mostrarAcessoriosNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirAcessorioActionPerformed

    private void jButtonAlterarAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarAcessorioActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jTextFieldIDAcessorio.getText());
            String nome = jTextFieldNomeAcessorio.getText();
            String descricao = jTextFieldDescricaoAcessorio.getText();

            Acessorio acessorio = new Acessorio(id, nome, descricao);
            acessorioBD.alterar(acessorio);

            limparTela();
            mostrarAcessoriosNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar acessório: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonAlterarAcessorioActionPerformed

    private void jButtonIncluirVinculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirVinculoActionPerformed
        try {
            int idAcessorio = Integer.parseInt(jTextFieldIDVinculo.getText());
            String placa = jTextFieldPlacaVinculo.getText();

            VinculoCarroAcessorio vinculo = new VinculoCarroAcessorio(placa, idAcessorio);
            vinculoBD.incluir(vinculo);

            limparTela();
            mostrarVinculoNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir vínculo: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirVinculoActionPerformed

    private void jButtonAlterarVinculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarVinculoActionPerformed
        // TODO add your handling code here:
        try {
            int idAcessorio = Integer.parseInt(jTextFieldIDVinculo.getText());
            String placa = jTextFieldPlacaVinculo.getText();

            VinculoCarroAcessorio vinculo = new VinculoCarroAcessorio(placa, idAcessorio);
            vinculoBD.alterar(vinculo);

            limparTela();
            mostrarVinculoNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar vínculo: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonAlterarVinculoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaAcessorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaAcessorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaAcessorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaAcessorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaAcessorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterarAcessorio;
    private javax.swing.JButton jButtonAlterarVinculo;
    private javax.swing.JButton jButtonIncluirAcessorio;
    private javax.swing.JButton jButtonIncluirVinculo;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableAcessorios;
    private javax.swing.JTable jTableVinculo;
    private javax.swing.JTextField jTextFieldDescricaoAcessorio;
    private javax.swing.JTextField jTextFieldIDAcessorio;
    private javax.swing.JTextField jTextFieldIDVinculo;
    private javax.swing.JTextField jTextFieldNomeAcessorio;
    private javax.swing.JTextField jTextFieldPlacaVinculo;
    // End of variables declaration//GEN-END:variables
}
