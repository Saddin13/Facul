
package Visao;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import modelos.Aluno;
import modelos.AlunoCompleto;
import modelos.Curso;
import modelos.Enfase;
import persistencia.ManipularAluno;

public class telaAlunos extends javax.swing.JFrame {
    private TreeSet<AlunoCompleto> Alunos;
    private TreeSet<Curso> Cursos;
    private TreeSet<Enfase> Enfases;
    private ManipularAluno dadosAlunos;

public telaAlunos(TreeSet<AlunoCompleto> alunos, TreeSet<Curso> cursos, TreeSet<Enfase> enfases) throws ParseException {
    initComponents();
    setLocationRelativeTo(null);
    this.Alunos = alunos;
    this.Cursos = cursos;
    this.Enfases = enfases;
    this.dadosAlunos = new ManipularAluno("./src/Arquivos/DadosDosAlunos.csv");
    mask();
    configurarTabelaListener();
    listagem(this.Alunos);
}

    private void mask() throws ParseException{
    try {
        MaskFormatter formatter = new MaskFormatter("####.#.###.####");
        formatter.setPlaceholderCharacter('_');
        formatter.install(jFormattedTextFieldMatricula);
        jFormattedTextFieldMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldMatricula.setCaretPosition(0); 
            }
        });
        } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao aplicar a máscara na matrícula: " + e.getMessage());
        }
        try{
            MaskFormatter formatter = new MaskFormatter("##");
        formatter.setPlaceholderCharacter(' ');
        formatter.install(jFormattedTextFieldCurso);
        jFormattedTextFieldCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCurso.setCaretPosition(0); 
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar a máscara na Curso: " + e.getMessage());
        }
        try{
            MaskFormatter formatter = new MaskFormatter("##");
            formatter.setPlaceholderCharacter(' ');
            formatter.install(jFormattedTextFieldEnfase);
            jFormattedTextFieldEnfase.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldEnfase.setCaretPosition(0); 
            }
        });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar a máscara na Enfase: " + e.getMessage());
        }
    }
    
    private void ajustarTamanhoColunas() {
        jTableSaida.getColumnModel().getColumn(0).setMinWidth(120);       
        jTableSaida.getColumnModel().getColumn(0).setMaxWidth(120);       
        jTableSaida.getColumnModel().getColumn(1).setMinWidth(300);       
        jTableSaida.getColumnModel().getColumn(1).setPreferredWidth(400); 
        jTableSaida.getColumnModel().getColumn(1).setMaxWidth(2000);      
        jTableSaida.getColumnModel().getColumn(2).setMinWidth(120);       
        jTableSaida.getColumnModel().getColumn(2).setMaxWidth(120);
        jTableSaida.getColumnModel().getColumn(3).setMinWidth(120);       
        jTableSaida.getColumnModel().getColumn(3).setMaxWidth(120);
        jTableSaida.getColumnModel().getColumn(4).setMinWidth(80);       
        jTableSaida.getColumnModel().getColumn(4).setMaxWidth(80);       
        jTableSaida.getColumnModel().getColumn(5).setMinWidth(140);       
        jTableSaida.getColumnModel().getColumn(5).setMaxWidth(140);            
        jTableSaida.getColumnModel().getColumn(6).setMinWidth(80);       
        jTableSaida.getColumnModel().getColumn(6).setMaxWidth(80);       
        jTableSaida.getColumnModel().getColumn(7).setMinWidth(200);       
        jTableSaida.getColumnModel().getColumn(7).setMaxWidth(200);       
    }
         
    private void configurarTabelaListener() {
        jTableSaida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTableSaida.getSelectedRow();
                if (row >= 0) {
                    jFormattedTextFieldMatricula.setText(jTableSaida.getValueAt(row, 0).toString());
                    jTextFieldNome.setText(jTableSaida.getValueAt(row, 1).toString());
                    jFormattedTextFieldPeriodo.setText(jTableSaida.getValueAt(row, 2).toString());
                    jComboBoxTurno.setSelectedItem(jTableSaida.getValueAt(row, 3).toString());
                    jFormattedTextFieldEnfase.setText(jTableSaida.getValueAt(row, 4).toString());
                    jFormattedTextFieldCurso.setText(jTableSaida.getValueAt(row, 6).toString());
                }
            }
        });
    }


    
    private void limparCampos() {
        jFormattedTextFieldMatricula.setText("");
        jTextFieldNome.setText("");
        jFormattedTextFieldPeriodo.setText("");
        jComboBoxTurno.setSelectedIndex(0);
        jFormattedTextFieldEnfase.setText("");
        jFormattedTextFieldCurso.setText("");
    }
    
private void listagem(TreeSet<AlunoCompleto> alunos) {
    try {
        jTableSaida.setRowHeight(30);
        DefaultTableModel model = (DefaultTableModel) jTableSaida.getModel();
        model.setNumRows(0);
        
        ajustarTamanhoColunas();
        
        System.out.println("Iniciando listagem dos alunos");
        for (AlunoCompleto aluno : alunos) {
            model.addRow(new Object[]{
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getPeriodo(),
                aluno.getTurno(),
                aluno.getIDenfase(),
                aluno.getEnfase(),
                aluno.getIDcurso(),
                aluno.getCurso()
            });
        }
        System.out.println("Finalizada a listagem dos alunos");
        
    } catch (Exception e) {
        System.out.println("Erro na listagem: " + e.getMessage());
    }
}
   
private int validarCursoEnfase(int idCurso, int idEnfase) {
    if (!Cursos.stream().anyMatch(c -> c.getIdCurso() == idCurso)) {
        return 1; // Course not found error code
    }
    
    if (!Enfases.stream().anyMatch(e -> e.getIdEnfase() == idEnfase)) {
        return 2; // Emphasis not found error code
    }
    
    return 0; // Success code
}
    
    private String obterNomeCurso(int idCurso) {
    return Cursos.stream()
        .filter(c -> c.getIdCurso() == idCurso)
        .map(c -> c.getdCurso())
        .findFirst()
        .orElse("");
}

private String obterNomeEnfase(int idEnfase) {
    return Enfases.stream()
        .filter(e -> e.getIdEnfase() == idEnfase)
        .map(e -> e.getdEnfase())
        .findFirst()
        .orElse("");
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Alunos = new javax.swing.JLabel();
        jButtonAlterar = new javax.swing.JButton();
        jButton_Incluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSaida = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldMatricula = new javax.swing.JFormattedTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jFormattedTextFieldPeriodo = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTurno = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldCurso = new javax.swing.JFormattedTextField();
        jFormattedTextFieldEnfase = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));

        jLabel_Alunos.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel_Alunos.setText("Aluno");

        jButtonAlterar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton_Incluir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Incluir.setText("Incluir");
        jButton_Incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IncluirActionPerformed(evt);
            }
        });

        jTableSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Nome", "Periodo", "Turno", "ID Enfase", "Enfase", "ID Curso", "Curso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSaida);

        jLabel1.setText("Matricula");

        jLabel2.setText("Nome");

        jLabel4.setText("Periodo");

        jFormattedTextFieldPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldPeriodoActionPerformed(evt);
            }
        });
        jFormattedTextFieldPeriodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPeriodoKeyTyped(evt);
            }
        });

        jLabel3.setText("Turno");

        jComboBoxTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MATUTINO", "VESPERTINO", "NOTURNO" }));
        jComboBoxTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTurnoActionPerformed(evt);
            }
        });

        jLabel7.setText("Enfase");

        jLabel8.setText("Curso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel_Alunos))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jFormattedTextFieldEnfase, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jFormattedTextFieldCurso)
                                            .addComponent(jFormattedTextFieldPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(25, 25, 25)
                                        .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Incluir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel_Alunos)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldEnfase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Incluir)
                            .addComponent(jButtonAlterar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jFormattedTextFieldPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPeriodoActionPerformed
        // TODO add your handling code here:
        try {
        String matricula = jFormattedTextFieldMatricula.getText();
        this.Alunos.removeIf(a -> a.getMatricula().equals(matricula));
        jButton_IncluirActionPerformed(evt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar aluno: " + e.getMessage());
        }
    }//GEN-LAST:event_jFormattedTextFieldPeriodoActionPerformed

    private void jButton_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IncluirActionPerformed
        // TODO add your handling code here:
    try {
        int idCurso = Integer.parseInt(jFormattedTextFieldCurso.getText());
        int idEnfase = Integer.parseInt(jFormattedTextFieldEnfase.getText());
        
        int validationResult = validarCursoEnfase(idCurso, idEnfase);
        switch(validationResult) {
            case 1:
                JOptionPane.showMessageDialog(this, "Curso não encontrado!");
                return;
            case 2:
                JOptionPane.showMessageDialog(this, "Ênfase não encontrada!");
                return;
        }


        AlunoCompleto novoAluno = new AlunoCompleto(
            jFormattedTextFieldMatricula.getText(),
            jTextFieldNome.getText(),
            jComboBoxTurno.getSelectedItem().toString(),
            Integer.parseInt(jFormattedTextFieldPeriodo.getText()),
            idEnfase,
            obterNomeEnfase(idEnfase),
            idCurso,
            obterNomeCurso(idCurso)
        );
        
        this.Alunos.add(novoAluno);
        dadosAlunos.gravarLista(this.Alunos);
        listagem(this.Alunos);
        limparCampos();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao incluir aluno: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton_IncluirActionPerformed

    private void jFormattedTextFieldPeriodoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPeriodoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jFormattedTextFieldPeriodoKeyTyped

    private void jComboBoxTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTurnoActionPerformed

public static void main(String args[]) {
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(telaAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            TreeSet<AlunoCompleto> alunos = new TreeSet<>();
            TreeSet<Curso> cursos = new TreeSet<>();
            TreeSet<Enfase> enfases = new TreeSet<>();
            try {
                new telaAlunos(alunos, cursos, enfases).setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(telaAlunos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}

public void gravarLista(TreeSet<AlunoCompleto> alunosCompletos) throws Exception {
    TreeSet<Aluno> alunos = new TreeSet<>();
    
    for(AlunoCompleto ac : alunosCompletos) {
        Aluno aluno = new Aluno(
            ac.getMatricula(),
            ac.getNome(),
            ac.getTurno(),
            ac.getPeriodo(),
            ac.getIDenfase(),
            ac.getIDcurso()
        );
        alunos.add(aluno);
    }
    
    gravarArquivo(alunos);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButton_Incluir;
    private javax.swing.JComboBox<String> jComboBoxTurno;
    private javax.swing.JFormattedTextField jFormattedTextFieldCurso;
    private javax.swing.JFormattedTextField jFormattedTextFieldEnfase;
    private javax.swing.JFormattedTextField jFormattedTextFieldMatricula;
    private javax.swing.JFormattedTextField jFormattedTextFieldPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_Alunos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSaida;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables

    private void gravarArquivo(TreeSet<Aluno> alunos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
