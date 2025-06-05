
package visao;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Professor;
import persistencia.ManipularArquivo;

public class TelaPrincipal extends javax.swing.JFrame {

    private TreeSet<Professor> professores = new TreeSet<>();
    ManipularArquivo dados = new ManipularArquivo("./src/arquivo/Testes.csv");

    public TelaPrincipal() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
        
        professores = dados.obterListaProfessores();
        listagem(professores);
    }

    private void ajustarTamanhoColunas() {
    jTableSaida.getColumnModel().getColumn(0).setMinWidth(100);       // 1
    jTableSaida.getColumnModel().getColumn(0).setMaxWidth(100);       // matricula
    jTableSaida.getColumnModel().getColumn(1).setMinWidth(300);       // 1
    jTableSaida.getColumnModel().getColumn(1).setPreferredWidth(400); // Nome
    jTableSaida.getColumnModel().getColumn(1).setMaxWidth(2000);      // 1
    jTableSaida.getColumnModel().getColumn(2).setMinWidth(120);       // turno
    jTableSaida.getColumnModel().getColumn(2).setMaxWidth(120);       // 2
    jTableSaida.getColumnModel().getColumn(3).setMinWidth(180);        // periodo
    jTableSaida.getColumnModel().getColumn(3).setMaxWidth(180);        // 3
    jTableSaida.getColumnModel().getColumn(4).setMinWidth(140);       // Ênfase
    jTableSaida.getColumnModel().getColumn(4).setMaxWidth(280);       // 4
}
    
    private void listagem(TreeSet<Professor> professores) {
        try {
            jTableSaida.setRowHeight(30);
            jTableSaida.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"Matricula", "Nome", "Escola", "Titulação", "Tipo de Contrato"}
            ));

            ajustarTamanhoColunas();

            DefaultTableModel model = (DefaultTableModel) jTableSaida.getModel();
            model.setNumRows(0);
            
            for (Professor professor : professores) {
                model.addRow(new Object[] {
                    professor.getMatricula(),
                    professor.getNome(), 
                    professor.getEscola(),
                    professor.getTitulacao(),
                    professor.getTipoContrato()
                });
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage() + " Listar Dados\n");
        }
    }

    private void atualizar() {
    try {
        dados.atualizarArquivo(professores);
        professores = dados.obterListaProfessores();
        listagem(professores);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela: " + e.getMessage());
    }
    }


    
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSaida = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxOrdenacao = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButtonIncluir = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldMatricula = new javax.swing.JTextField();
        jTextFieldEscola = new javax.swing.JTextField();
        jComboBoxTitulacao = new javax.swing.JComboBox<>();
        jComboBoxTipoDeContrato = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(23, 1, 38));
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setDoubleBuffered(false);
        jPanel6.setEnabled(false);
        jPanel6.setFocusable(false);
        jPanel6.setRequestFocusEnabled(false);
        jPanel6.setVerifyInputWhenFocusTarget(false);

        jTableSaida.setBackground(new java.awt.Color(51, 51, 51));
        jTableSaida.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jTableSaida.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTableSaida.setForeground(new java.awt.Color(204, 204, 204));
        jTableSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posição", "MATRICULA", "NOME", "ESCOLA", "TITULAÇÃO", "CONTRATO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSaida.setShowGrid(true);
        jTableSaida.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTableSaidaMouseMoved(evt);
            }
        });
        jTableSaida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSaidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSaida);

        jPanel5.setBackground(new java.awt.Color(68, 68, 68));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(245, 245, 245));
        jLabel8.setText("ORDENAÇÃO");

        jComboBoxOrdenacao.setBackground(new java.awt.Color(102, 102, 102));
        jComboBoxOrdenacao.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jComboBoxOrdenacao.setForeground(new java.awt.Color(180, 180, 180));
        jComboBoxOrdenacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordem Alfabetica", "Matricula" }));
        jComboBoxOrdenacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxOrdenacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrdenacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(68, 68, 68));

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAlterar)
                .addGap(30, 30, 30))
        );

        jPanel2.setBackground(new java.awt.Color(68, 68, 68));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 245, 245));
        jLabel2.setText("MATRICULA");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(245, 245, 245));
        jLabel3.setText("NOME COMPLETO");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(245, 245, 245));
        jLabel4.setText("ESCOLA");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(245, 245, 245));
        jLabel5.setText("TITULAÇÃO");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(245, 245, 245));
        jLabel6.setText("TIPO DO CONTRATO");

        jTextFieldNome.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldNome.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jTextFieldNome.setForeground(new java.awt.Color(180, 180, 180));
        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyReleased(evt);
            }
        });

        jTextFieldMatricula.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldMatricula.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jTextFieldMatricula.setForeground(new java.awt.Color(180, 180, 180));

        jTextFieldEscola.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldEscola.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jTextFieldEscola.setForeground(new java.awt.Color(180, 180, 180));

        jComboBoxTitulacao.setBackground(new java.awt.Color(102, 102, 102));
        jComboBoxTitulacao.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jComboBoxTitulacao.setForeground(new java.awt.Color(180, 180, 180));
        jComboBoxTitulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mestre(a)", "Doutor(a)", "Especialista", "Pos-Doutor(a)", "Bacharelado" }));
        jComboBoxTitulacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBoxTipoDeContrato.setBackground(new java.awt.Color(102, 102, 102));
        jComboBoxTipoDeContrato.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jComboBoxTipoDeContrato.setForeground(new java.awt.Color(180, 180, 180));
        jComboBoxTipoDeContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TI", "Horista" }));
        jComboBoxTipoDeContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMatricula))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEscola))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipoDeContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTitulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxTipoDeContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTitulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 245, 245));
        jLabel1.setText("SISTEMA DE CONTROLE DOS PROFESSORES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(458, 458, 458))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
    try {
        int matricula = Integer.parseInt(jTextFieldMatricula.getText());
        String nome = jTextFieldNome.getText();
        String escola = jTextFieldEscola.getText();
        String titulacao = jComboBoxTitulacao.getSelectedItem().toString();
        String tipoContrato = jComboBoxTipoDeContrato.getSelectedItem().toString();
        
        Professor novoProfessor = new Professor(matricula, nome, escola, titulacao, tipoContrato);
        
        professores.add(novoProfessor);
        
        jComboBoxOrdenacaoActionPerformed(evt);
        
        jTextFieldMatricula.setText("");
        jTextFieldNome.setText("");
        jTextFieldEscola.setText("");
        jComboBoxTitulacao.setSelectedIndex(0);
        jComboBoxTipoDeContrato.setSelectedIndex(0);
        
        JOptionPane.showMessageDialog(this, "Professor incluído com sucesso!");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Matrícula deve ser um número válido!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao incluir professor: " + e.getMessage());
    }
  }//GEN-LAST:event_jButtonIncluirActionPerformed

  private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try{
        int matricula = Integer.parseInt(jTextFieldMatricula.getText());
        professores.removeIf(p -> p.getMatricula() == matricula);
        atualizar();

        jTextFieldMatricula.setText("");
        jTextFieldNome.setText("");
        jTextFieldEscola.setText("");
        jComboBoxTitulacao.setSelectedIndex(0);
        jComboBoxTipoDeContrato.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Professor excluido com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Matrícula deve ser um número válido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluido professor: " + e.getMessage());
        }
  }//GEN-LAST:event_jButtonExcluirActionPerformed

  private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
    try{
        int matricula = Integer.parseInt(jTextFieldMatricula.getText());
        String nome = jTextFieldNome.getText();
        String escola = jTextFieldEscola.getText();
        String titulacao = jComboBoxTitulacao.getSelectedItem().toString();
        String tipoContrato = jComboBoxTipoDeContrato.getSelectedItem().toString();
        
        professores.removeIf(p -> p.getMatricula() == matricula);

        Professor novoProfessor = new Professor(matricula, nome, escola, titulacao, tipoContrato);

        professores.add(novoProfessor);

        jComboBoxOrdenacaoActionPerformed(evt);

        jTextFieldMatricula.setText("");
        jTextFieldNome.setText("");
        jTextFieldEscola.setText("");
        jComboBoxTitulacao.setSelectedIndex(0);
        jComboBoxTipoDeContrato.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Professor alterado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Matrícula deve ser um número válido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar professor: " + e.getMessage());
        }
  }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTableSaidaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSaidaMouseMoved

    }//GEN-LAST:event_jTableSaidaMouseMoved

    private void jTableSaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSaidaMouseClicked
        try {
            int rowSelecionada = jTableSaida.getSelectedRow();
            if (rowSelecionada != -1) {
                jTextFieldMatricula.setText(jTableSaida.getValueAt(rowSelecionada, 0).toString());
                jTextFieldNome.setText(jTableSaida.getValueAt(rowSelecionada, 1).toString());
                jTextFieldEscola.setText(jTableSaida.getValueAt(rowSelecionada, 2).toString());
                jComboBoxTitulacao.setSelectedItem(jTableSaida.getValueAt(rowSelecionada, 3).toString());
                jComboBoxTipoDeContrato.setSelectedItem(jTableSaida.getValueAt(rowSelecionada, 4).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTableSaidaMouseClicked

    private void jTextFieldNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyReleased
    }//GEN-LAST:event_jTextFieldNomeKeyReleased

    private void jComboBoxOrdenacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrdenacaoActionPerformed
        String ordem = jComboBoxOrdenacao.getSelectedItem().toString();
        TreeSet<Professor> professoresOrdenados;

        if (ordem.equals("Ordem Alfabetica")) {
            professoresOrdenados = new TreeSet<>((p1, p2) -> 
                p1.getNome().compareToIgnoreCase(p2.getNome()));
        } else {
            professoresOrdenados = new TreeSet<>((p1, p2) -> 
                Integer.compare(p1.getMatricula(), p2.getMatricula()));
        }
        
        professoresOrdenados.addAll(professores);
        listagem(professoresOrdenados);
    }//GEN-LAST:event_jComboBoxOrdenacaoActionPerformed

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
          java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaPrincipal().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JComboBox<String> jComboBoxOrdenacao;
    private javax.swing.JComboBox<String> jComboBoxTipoDeContrato;
    private javax.swing.JComboBox<String> jComboBoxTitulacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSaida;
    private javax.swing.JTextField jTextFieldEscola;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
