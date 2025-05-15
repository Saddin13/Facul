
package Visao;

import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Enfase;
import Visao.TelaPrincipal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.text.MaskFormatter;
import persistencia.ManipularEnfase;

public class telaEnfase extends javax.swing.JFrame {

    private TreeSet<Enfase> Enfases;
    
    public telaEnfase(TreeSet<Enfase> Enfases) {
        initComponents();
        System.out.println("Construtor telaEnfases recebeu: " + Enfases.size() + " Enfases");
        this.Enfases = Enfases; 
//-----------------------       
//Enfases.add(new Enfase(999, "Java Programming"));
//Enfases.add(new Enfase(919, "Python Development"));
//Enfases.add(new Enfase(929, "Web Development"));
//-----------------------    
        formatarID();
        listagem(Enfases);
    }
    
    private void formatarID() {
    try{
            MaskFormatter formatter = new MaskFormatter("##");
            formatter.setPlaceholderCharacter(' ');
            formatter.install(jFormattedTextFieldID);
            jFormattedTextFieldID.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldID.setCaretPosition(0); 
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar a máscara no Id: " + e.getMessage());
        }
    }
        private void ajustarTamanhoColunas() {
    jTableSaida.getColumnModel().getColumn(0).setMinWidth(50);       
    jTableSaida.getColumnModel().getColumn(0).setMaxWidth(50);       
    jTableSaida.getColumnModel().getColumn(1).setMinWidth(300);       
    jTableSaida.getColumnModel().getColumn(1).setPreferredWidth(400); 
}
        
private void listagem(TreeSet<Enfase> Enfases) {
    try {
        // Configure table properties
        jTableSaida.setRowHeight(30);
        DefaultTableModel model = (DefaultTableModel) jTableSaida.getModel();
        model.setNumRows(0);
        
        ajustarTamanhoColunas();
        
        System.out.println("Iniciando listagem dos Enfases");
        // Direct iteration over the received collection
        for (Enfase Enfase : Enfases) {
            System.out.println("Adicionando Enfase: " + Enfase.getIdEnfase() + " - " + Enfase.getdEnfase());
            model.addRow(new Object[]{
                Enfase.getIdEnfase(),
                Enfase.getdEnfase()
            });
        }
        System.out.println("Finalizada a listagem dos Enfases");
        
    } catch (Exception e) {
        System.out.println("Erro na listagem: " + e.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSaida = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextDescricao = new javax.swing.JTextField();
        jIncluir = new javax.swing.JButton();
        jAlterar = new javax.swing.JButton();
        jFormattedTextFieldID = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTableSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descricao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSaida);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Descrição");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setText("Enfase");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("ID");

        jIncluir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jIncluir.setText("Incluir");
        jIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIncluirActionPerformed(evt);
            }
        });

        jAlterar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jAlterar.setText("Alterar");
        jAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextFieldID)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jTextDescricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIncluirActionPerformed
        // TODO add your handling code here:
            try {
        int id = Integer.parseInt(jFormattedTextFieldID.getText());
        String descricao = jTextDescricao.getText();
        
        if (descricao.isEmpty()) {
            throw new Exception("Descrição não pode estar vazia");
        }
        
        Enfase novoEnfase = new Enfase(id, descricao);
        this.Enfases.add(novoEnfase);
        
        // Atualiza arquivo
        ManipularEnfase dadosEnfase = new ManipularEnfase("./src/Arquivos/Enfase.csv");
        dadosEnfase.atualizarArquivo(this.Enfases);
        
        // Atualiza tabela
        listagem(this.Enfases);
        
        // Limpa campos
        jFormattedTextFieldID.setText("");
        jTextDescricao.setText("");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID inválido");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_jIncluirActionPerformed

    private void jAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlterarActionPerformed
        // TODO add your handling code here:
        try {
        int id = Integer.parseInt(jFormattedTextFieldID.getText());
        String descricao = jTextDescricao.getText();
        
        if (descricao.isEmpty()) {
            throw new Exception("Descrição não pode estar vazia");
        }
        
        // Remove Enfase antigo e adiciona atualizado
        Enfase EnfaseAtualizado = new Enfase(id, descricao);
        this.Enfases.removeIf(c -> c.getIdEnfase() == id);
        this.Enfases.add(EnfaseAtualizado);
        
        // Atualiza arquivo
        ManipularEnfase dadosEnfase = new ManipularEnfase("./src/Arquivos/Enfases.csv");
        dadosEnfase.atualizarArquivo(this.Enfases);
        
        // Atualiza tabela
        listagem(this.Enfases);
        
        // Limpa campos
        jFormattedTextFieldID.setText("");
        jTextDescricao.setText("");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID inválido");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_jAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(telaEnfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaEnfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaEnfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaEnfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
        TreeSet<Enfase> Enfases = new TreeSet<>();
        try {
            ManipularEnfase dadosEnfase = new ManipularEnfase("./src/Arquivos/Enfases.csv");
            Enfases = dadosEnfase.obterListaEnfase();
            new telaEnfase(Enfases).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Enfases: " + e.getMessage());
        }
        }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAlterar;
    private javax.swing.JFormattedTextField jFormattedTextFieldID;
    private javax.swing.JButton jIncluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSaida;
    private javax.swing.JTextField jTextDescricao;
    // End of variables declaration//GEN-END:variables
}
