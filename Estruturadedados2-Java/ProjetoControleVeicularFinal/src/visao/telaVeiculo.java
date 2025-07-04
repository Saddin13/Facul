/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import javax.swing.JOptionPane;
import modelos.Posse;
import ferramentas.ConexaoBD;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import modelos.Servicos;
import modelos.Veiculo;
import persistencia.ServicosDAO;
import persistencia.PosseDAO;
import persistencia.VeiculoDAO;
import modelos.IVeiculoCRUD;
import modelos.IPosseCRUD;
import modelos.Marca;
import modelos.Modelo;
import persistencia.MarcaDAO;
import persistencia.ModeloDAO;

public class telaVeiculo extends javax.swing.JFrame {
    private IVeiculoCRUD veiculoBD = null;
  
    public telaVeiculo() {
      initComponents();
      setLocationRelativeTo(null);
      configurarMascaras();
      maskcpf();
      try {
          veiculoBD = new VeiculoDAO();
          mostrarVeiculosNaGrid();
          configurarComboBoxes();
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
      }
    }
    
    private void maskcpf(){
        try{
        javax.swing.text.MaskFormatter cpfMask = new javax.swing.text.MaskFormatter("###.###.###-##");
        cpfMask.setPlaceholderCharacter('_');
        jFormattedTextFieldDono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(cpfMask));
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao configurar máscara CPF: " + ex.getMessage());
        }
    }

    private void maskCNPJ() {
        try {
            javax.swing.text.MaskFormatter cnpjMask = new javax.swing.text.MaskFormatter("##.###.###/####-##");
            cnpjMask.setPlaceholderCharacter('_');
            jFormattedTextFieldDono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(cnpjMask));
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao configurar máscara CNPJ: " + ex.getMessage());
        }
    }    

    private void configurarMascaras() {
        try {
            javax.swing.text.MaskFormatter placaMask = new javax.swing.text.MaskFormatter("UUU#A##");
            placaMask.setPlaceholderCharacter('_');
            jFormattedTextFieldPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(placaMask));
            
            javax.swing.text.MaskFormatter dataMask = new javax.swing.text.MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            jFormattedTextFieldDataCastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(dataMask));
            
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao configurar máscaras: " + ex.getMessage());
        }
    }
    
    private HashMap<String, Integer> modeloMap = new HashMap<>();
    
    private void configurarComboBoxes() {
        try {
            jComboBoxModelo.removeAllItems();
            ModeloDAO modeloDAO = new ModeloDAO();
            ArrayList<Modelo> modelos = modeloDAO.obterListaDeModelos();

            for (Modelo modelo : modelos) {
                jComboBoxModelo.addItem(modelo.getNome());
                modeloMap.put(modelo.getNome(), modelo.getId());
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao configurar ComboBoxes: " + erro.getMessage());
        }
    }
  private void limparTela() {
    jFormattedTextFieldPlaca.setText("");
    jComboBoxModelo.setSelectedIndex(0);
    jFormattedTextFieldAnoModelo.setText("");
    jFormattedTextFieldAnoFabricacao.setText("");
    jFormattedTextFieldDataCastro.setText("");
    jFormattedTextFieldKm.setText("");
    jFormattedTextFieldDono.setText("");
}

private void mostrarVeiculosNaGrid() {
    try {
        ArrayList<Veiculo> listaDeVeiculos = veiculoBD.obterListaDeVeiculos();
        DefaultTableModel model = (DefaultTableModel) jTableVeiculoProprietario.getModel();
        model.setNumRows(0);

        for(Veiculo veiculo : listaDeVeiculos) {
            String[] linha = new String[8];
            linha[0] = veiculo.getPlaca();
            linha[1] = veiculo.getModelo();
            linha[2] = String.valueOf(veiculo.getAnoModelo());
            linha[3] = String.valueOf(veiculo.getAnoFabricacao());
            linha[4] = veiculo.getDataCadastro().toString();
            linha[5] = String.valueOf(veiculo.getKm());
            linha[6] = veiculo.getCpfDono();
            model.addRow(linha);
        }
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
    }
}
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxModelo = new javax.swing.JComboBox<>();
        jFormattedTextFieldDono = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextFieldDataCastro = new javax.swing.JFormattedTextField();
        jFormattedTextFieldKm = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonIncluir = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButtonAlterar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculoProprietario = new javax.swing.JTable();
        jFormattedTextFieldPlaca = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAnoModelo = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAnoFabricacao = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonVoltar = new javax.swing.JButton();
        jButtonHoje = new javax.swing.JButton();
        jComboBoxTipoCliente = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jComboBoxModelo.setBackground(new java.awt.Color(204, 204, 204));
        jComboBoxModelo.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jFormattedTextFieldDono.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldDono.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDonoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("ANO DE FABRICAÇÃO:");

        jFormattedTextFieldDataCastro.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldDataCastro.setForeground(new java.awt.Color(51, 51, 51));

        jFormattedTextFieldKm.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldKm.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/candidato.png"))); // NOI18N
        jLabel1.setText("CADASTRO VEÍCULO/PROPRIETÁRIO");

        jButtonIncluir.setBackground(new java.awt.Color(204, 204, 204));
        jButtonIncluir.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButtonIncluir.setForeground(new java.awt.Color(51, 51, 51));
        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("DATA DE CADASTRO:");

        jButtonAlterar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAlterar.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButtonAlterar.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("ANO DO MODELO:");

        jTableVeiculoProprietario.setBackground(new java.awt.Color(204, 204, 204));
        jTableVeiculoProprietario.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jTableVeiculoProprietario.setForeground(new java.awt.Color(51, 51, 51));
        jTableVeiculoProprietario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "PLACA", "MODELO", "ANO MODELO", "ANO FABRICACAO", "DATA CADASTRO", "KM", "PROPRIETARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableVeiculoProprietario);
        if (jTableVeiculoProprietario.getColumnModel().getColumnCount() > 0) {
            jTableVeiculoProprietario.getColumnModel().getColumn(0).setResizable(false);
            jTableVeiculoProprietario.getColumnModel().getColumn(1).setResizable(false);
            jTableVeiculoProprietario.getColumnModel().getColumn(2).setResizable(false);
            jTableVeiculoProprietario.getColumnModel().getColumn(3).setResizable(false);
            jTableVeiculoProprietario.getColumnModel().getColumn(4).setResizable(false);
            jTableVeiculoProprietario.getColumnModel().getColumn(5).setResizable(false);
        }

        jFormattedTextFieldPlaca.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldPlaca.setForeground(new java.awt.Color(51, 51, 51));

        jFormattedTextFieldAnoModelo.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldAnoModelo.setForeground(new java.awt.Color(51, 51, 51));

        jFormattedTextFieldAnoFabricacao.setBackground(new java.awt.Color(204, 204, 204));
        jFormattedTextFieldAnoFabricacao.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("MODELO:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("TIPO DONO:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("KM:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("PLACA DO VEÍCULO:");

        jButtonVoltar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonVoltar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonVoltar.setForeground(new java.awt.Color(255, 51, 51));
        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jButtonHoje.setText("Hoje");
        jButtonHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHojeActionPerformed(evt);
            }
        });

        jComboBoxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoa Fisica", "Pessoa Juridica" }));
        jComboBoxTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoClienteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("CPF/CNPJ DONO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldAnoModelo)
                            .addComponent(jFormattedTextFieldDataCastro)
                            .addComponent(jFormattedTextFieldAnoFabricacao))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonHoje)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(jLabel13)
                                        .addGap(54, 54, 54))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldKm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldDono, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 89, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(97, 97, 97))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jFormattedTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jFormattedTextFieldDataCastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonHoje))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jFormattedTextFieldAnoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jFormattedTextFieldAnoFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jFormattedTextFieldKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
  private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
    try {
        if (jFormattedTextFieldPlaca.getText().trim().isEmpty() ||
            jFormattedTextFieldDono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa e CPF do proprietário são obrigatórios!");
            return;
        }

String selectedModeloName = jComboBoxModelo.getSelectedItem().toString();
int modeloId = modeloMap.get(selectedModeloName);


        String dataCadastroStr = jFormattedTextFieldDataCastro.getText();
        java.time.LocalDate dataCadastro = java.time.LocalDate.parse(
            dataCadastroStr, 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
        java.sql.Date sqlDate = java.sql.Date.valueOf(dataCadastro);

        Veiculo objVeiculo = new Veiculo(
    jFormattedTextFieldPlaca.getText().trim(),
    String.valueOf(modeloId), // Converting modeloId to String
    Integer.parseInt(jFormattedTextFieldAnoModelo.getText()),
    Integer.parseInt(jFormattedTextFieldAnoFabricacao.getText()),
    sqlDate,
    Integer.parseInt(jFormattedTextFieldKm.getText()),
    jFormattedTextFieldDono.getText().trim()
        );

        ConexaoBD.getConexao().setAutoCommit(false);
        
        try {
            veiculoBD.incluir(objVeiculo);
            
            Posse posse = new Posse();
            posse.setPlacaVeiculo(objVeiculo.getPlaca());
            int cpfNumerico = Integer.parseInt(objVeiculo.getCpfDono().replaceAll("[^0-9]", ""));
            posse.setCPF(cpfNumerico);
            posse.setDataAquisicao(new java.sql.Date(System.currentTimeMillis()));
            
            IPosseCRUD posseBD = new PosseDAO();
            posseBD.incluir(posse);
            
            ConexaoBD.getConexao().commit();
            
            JOptionPane.showMessageDialog(this, "Veículo e propriedade registrados com sucesso!");
            limparTela();
            mostrarVeiculosNaGrid();
            
        } catch (Exception e) {
            ConexaoBD.getConexao().rollback();
            throw e;
        } finally {
            ConexaoBD.getConexao().setAutoCommit(true);
        }
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(this, "Erro ao incluir veículo: " + erro.getMessage());
    }
  }//GEN-LAST:event_jButtonIncluirActionPerformed

  private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
        String placa = jFormattedTextFieldPlaca.getText();
        String modelo = jComboBoxModelo.getSelectedItem().toString();
        String anoModelo = jFormattedTextFieldAnoModelo.getText();
        String anoFabricacao = jFormattedTextFieldAnoFabricacao.getText();
        String dataCadastro = jFormattedTextFieldDataCastro.getText();
        String km = jFormattedTextFieldKm.getText();
        String cpfDono = jFormattedTextFieldDono.getText();

        String dataCadastroStr = jFormattedTextFieldDataCastro.getText();
        java.time.LocalDate datacadastro = java.time.LocalDate.parse(
            dataCadastroStr, 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
        java.sql.Date sqlDate = java.sql.Date.valueOf(dataCadastro);

        Veiculo objVeiculo = new Veiculo(
            placa,
            modelo,
            Integer.parseInt(anoModelo),
            Integer.parseInt(anoFabricacao),
            sqlDate,
            Integer.parseInt(km),
            cpfDono
        );

        veiculoBD.alterar(objVeiculo);
        limparTela();
        mostrarVeiculosNaGrid();
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, "Alterar Veículo: "+erro.getMessage());
    }  }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHojeActionPerformed
        // TODO add your handling code here:
        java.time.LocalDate hoje = java.time.LocalDate.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jFormattedTextFieldDataCastro.setText(hoje.format(formatter));
    }//GEN-LAST:event_jButtonHojeActionPerformed

    private void jComboBoxTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoClienteActionPerformed
        // TODO add your handling code here:
        String selectedType = jComboBoxTipoCliente.getSelectedItem().toString();

        if (selectedType.equals("Pessoa Fisica")) {
            maskcpf();
        } else if (selectedType.equals("Pessoa Juridica")) {
            maskCNPJ();
        }
    
    jFormattedTextFieldDono.setValue(null);
    }//GEN-LAST:event_jComboBoxTipoClienteActionPerformed

    private void jFormattedTextFieldDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDonoActionPerformed

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
      java.util.logging.Logger.getLogger(telaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(telaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(telaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(telaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new telaVeiculo().setVisible(true);
      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonHoje;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxModelo;
    private javax.swing.JComboBox<String> jComboBoxTipoCliente;
    private javax.swing.JFormattedTextField jFormattedTextFieldAnoFabricacao;
    private javax.swing.JFormattedTextField jFormattedTextFieldAnoModelo;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataCastro;
    private javax.swing.JFormattedTextField jFormattedTextFieldDono;
    private javax.swing.JFormattedTextField jFormattedTextFieldKm;
    private javax.swing.JFormattedTextField jFormattedTextFieldPlaca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVeiculoProprietario;
    // End of variables declaration//GEN-END:variables
}
