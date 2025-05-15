package visao;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import persistencia.ClienteDAO;
import modelos.IClienteCRUD;
import java.util.ArrayList;

public class telaCliente extends javax.swing.JFrame {
    
    private IClienteCRUD clienteBD = null;
    
    public telaCliente() {
        initComponents();
        setLocationRelativeTo(null);
        
            jFormattedTextFieldCNPJ.setEnabled(false);
            jFormattedTextIncricaoEstadual.setEnabled(false);
            jFormattedTextFieldContato.setEnabled(false);
            jFormattedTextFieldCNPJ.setBackground(new java.awt.Color(204, 204, 204));
            jFormattedTextIncricaoEstadual.setBackground(new java.awt.Color(204, 204, 204));
            jFormattedTextFieldContato.setBackground(new java.awt.Color(204, 204, 204));
        
        try {
            clienteBD = new ClienteDAO();
            mostrarClientesNaGrid();
            configurarCamposFormatados();
            ajustarTamanhoColunas();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar: " + erro.getMessage());
        }
    }
    
    private void ajustarTamanhoColunas() {
    jTableCliente.getColumnModel().getColumn(0).setMinWidth(20);
    jTableCliente.getColumnModel().getColumn(0).setMaxWidth(20);
    }
    
    private void configurarCamposFormatados() {
        try {
            javax.swing.text.MaskFormatter cpfMask = new javax.swing.text.MaskFormatter("###.###.###-##");
            javax.swing.text.MaskFormatter cnpjMask = new javax.swing.text.MaskFormatter("##.###.###/####-##");
            javax.swing.text.MaskFormatter telefoneMask = new javax.swing.text.MaskFormatter("(##)#####-####");
            
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(cpfMask));
            jFormattedTextFieldCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(cnpjMask));
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(telefoneMask));
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao configurar máscaras: " + ex.getMessage());
        }
    }

    private void limparTela() {
        jFormattedTextFieldId.setText("");
        jFormattedTextFieldNome.setText("");
        jFormattedTextFieldTelefone.setText("");
        jFormattedTextFieldEmail.setText("");
        jFormattedTextFieldCPF.setText("");
        jFormattedTextFieldCNPJ.setText("");
        jFormattedTextFieldContato.setText("");
        jFormattedTextIncricaoEstadual.setText("");
        jFormattedTextFieldLogradouro.setText("");
        jFormattedTextFieldNumero.setText("");
        jFormattedTextFieldNomeComplemento.setText("");
        jComboBoxTipoCliente.setSelectedIndex(0);
    }

    private void mostrarClientesNaGrid() {
        try {
            ArrayList<Cliente> listaDeClientes = clienteBD.obterListaDeClientes();
            DefaultTableModel model = (DefaultTableModel) jTableCliente.getModel();
            
            model.setColumnIdentifiers(new String[]{"ID", "Nome", "Tipo", "CPF/CNPJ", 
                "Inscrição", "Contato", "Email", "telefone","logradouro" , "numero" ,"complemento"});
            model.setNumRows(0);
            
            for(Cliente cliente : listaDeClientes) {
                model.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTipoCliente(),
                    cliente.getTipoCliente().equals("Pessoa Fisica") ? cliente.getCpf() : cliente.getCnpj(),
                    cliente.getInscricaoEstadual(),
                    cliente.getContato(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    cliente.getLogradouro(),
                    cliente.getNumero(),
                    cliente.getComplemento()
                });
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }

    private boolean validarDados(Cliente cliente) {
        if (cliente.getNome().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome é obrigatório!");
            return false;
        }
        if (cliente.getTelefone().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Telefone é obrigatório!");
            return false;
        }
        if (cliente.getTipoCliente().equals("Pessoa Fisica") && 
            getOnlyNumbers(cliente.getCpf()).length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return false;
        }
        if (cliente.getTipoCliente().equals("Pessoa Juridica") && 
            getOnlyNumbers(cliente.getCnpj()).length() != 14) {
            JOptionPane.showMessageDialog(null, "CNPJ inválido!");
            return false;
        }
        return true;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jFormattedTextIncricaoEstadual = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jComboBoxTipoCliente = new javax.swing.JComboBox<>();
        jButtonIncluir = new javax.swing.JButton();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jFormattedTextFieldNumero = new javax.swing.JFormattedTextField();
        jFormattedTextFieldNome = new javax.swing.JFormattedTextField();
        jFormattedTextFieldNomeComplemento = new javax.swing.JFormattedTextField();
        jFormattedTextFieldEmail = new javax.swing.JFormattedTextField();
        jFormattedTextFieldLogradouro = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jFormattedTextFieldId = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCNPJ = new javax.swing.JFormattedTextField();
        jFormattedTextFieldContato = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButtonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jTableCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTableCliente.setForeground(new java.awt.Color(51, 51, 51));
        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "TIPO", "NOME", "TELEFONE", "LOGRADOURO", "NÚMERO", "COMPLEMENTO", "EMAIL", "CPF", "CNPJ", "CONTATO", "INSCRIÇÃO "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCliente);

        jButtonAlterar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAlterar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAlterar.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jComboBoxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoa Fisica", "Pessoa Juridica" }));
        jComboBoxTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoClienteActionPerformed(evt);
            }
        });

        jButtonIncluir.setBackground(new java.awt.Color(204, 204, 204));
        jButtonIncluir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonIncluir.setForeground(new java.awt.Color(51, 51, 51));
        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/adicionar-usuario.png"))); // NOI18N
        jLabel1.setText("CADASTRO DE CLIENTES");

        jFormattedTextFieldCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCPFActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("ID (ALTERAR):");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("TIPO DE PESSOA:");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("NOME:");

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("TELEFONE:");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("LOGRADOURO:");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("NÚMERO:");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("COMPLEMENTO:");

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("EMAIL:");

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("CPF:");

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("CNPJ:");

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("CONTATO:");

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("INSCRIÇÃO ESTADUAL:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(249, 249, 249)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldContato, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jFormattedTextIncricaoEstadual)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFormattedTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldNomeComplemento)
                                    .addComponent(jFormattedTextFieldCPF)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jFormattedTextFieldCNPJ)))))
                .addGap(263, 263, 263))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonIncluir)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAlterar)
                                .addGap(836, 836, 836)
                                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextFieldNomeComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel9))
                    .addComponent(jFormattedTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextFieldCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextFieldContato, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextIncricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1133, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getOnlyNumbers(String text) {
        return text.replaceAll("[^0-9]", "");
    }
    
    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            Cliente cliente = new Cliente();
            cliente.setTipoCliente(jComboBoxTipoCliente.getSelectedItem().toString());
            cliente.setNome(jFormattedTextFieldNome.getText());
            cliente.setTelefone(getOnlyNumbers(jFormattedTextFieldTelefone.getText()));
            cliente.setEmail(jFormattedTextFieldEmail.getText());

            if (cliente.getTipoCliente().equals("Pessoa Fisica")) {
                cliente.setCpf(getOnlyNumbers(jFormattedTextFieldCPF.getText()));
                cliente.setCnpj(null); 
                cliente.setInscricaoEstadual(null);
                cliente.setContato(null);
            } else {
                cliente.setCpf(null);
                cliente.setCnpj(getOnlyNumbers(jFormattedTextFieldCNPJ.getText()));
                cliente.setInscricaoEstadual(jFormattedTextIncricaoEstadual.getText());
                cliente.setContato(jFormattedTextFieldContato.getText());
            }

            cliente.setLogradouro(jFormattedTextFieldLogradouro.getText());
            cliente.setNumero(Integer.parseInt(jFormattedTextFieldNumero.getText()));
            cliente.setComplemento(jFormattedTextFieldNomeComplemento.getText());
            
            if (validarDados(cliente)) {
                clienteBD.incluir(cliente);
                limparTela();
                mostrarClientesNaGrid();
                JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso!");
            }   
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir cliente: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            int linhaSelecionada = jTableCliente.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Cliente cliente = new Cliente();
                cliente.setId(Integer.parseInt(jFormattedTextFieldId.getText()));
                cliente.setNome(jFormattedTextFieldNome.getText());
                cliente.setTipoCliente(jComboBoxTipoCliente.getSelectedItem().toString());
                cliente.setTelefone(getOnlyNumbers(jFormattedTextFieldTelefone.getText()));
                cliente.setEmail(jFormattedTextFieldEmail.getText());

                if (cliente.getTipoCliente().equals("Pessoa Fisica")) {
                    cliente.setCpf(jFormattedTextFieldCPF.getText());
                    cliente.setCnpj(null);
                    cliente.setInscricaoEstadual(null);
                    cliente.setContato(null);
                } else {
                    cliente.setCnpj(jFormattedTextFieldCNPJ.getText());
                    cliente.setCpf(null);
                    cliente.setInscricaoEstadual(jFormattedTextIncricaoEstadual.getText());
                    cliente.setContato(jFormattedTextFieldContato.getText());
                }

                cliente.setLogradouro(jFormattedTextFieldLogradouro.getText());
                cliente.setNumero(Integer.parseInt(jFormattedTextFieldNumero.getText()));
                cliente.setComplemento(jFormattedTextFieldNomeComplemento.getText());
                
                clienteBD.alterar(cliente);
                limparTela();
                mostrarClientesNaGrid();
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar cliente: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jComboBoxTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoClienteActionPerformed
        String tipoSelecionado = jComboBoxTipoCliente.getSelectedItem().toString();
        if (tipoSelecionado.equals("Pessoa Fisica")) {
            jFormattedTextFieldCPF.setEnabled(true);
            jFormattedTextFieldCPF.setBackground(new java.awt.Color(255, 255, 255));

            jFormattedTextFieldCNPJ.setEnabled(false);
            jFormattedTextIncricaoEstadual.setEnabled(false);
            jFormattedTextFieldContato.setEnabled(false);
            jFormattedTextFieldCNPJ.setBackground(new java.awt.Color(204, 204, 204));
            jFormattedTextIncricaoEstadual.setBackground(new java.awt.Color(204, 204, 204));
            jFormattedTextFieldContato.setBackground(new java.awt.Color(204, 204, 204));
        } else {
            jFormattedTextFieldCPF.setEnabled(false);
            jFormattedTextFieldCPF.setBackground(new java.awt.Color(204, 204, 204));

            jFormattedTextFieldCNPJ.setEnabled(true);
            jFormattedTextIncricaoEstadual.setEnabled(true);
            jFormattedTextFieldContato.setEnabled(true);
            jFormattedTextFieldCNPJ.setBackground(new java.awt.Color(255, 255, 255));
            jFormattedTextIncricaoEstadual.setBackground(new java.awt.Color(255, 255, 255));
            jFormattedTextFieldContato.setBackground(new java.awt.Color(255, 255, 255));
        }
    }//GEN-LAST:event_jComboBoxTipoClienteActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jFormattedTextFieldCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCPFActionPerformed

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
            java.util.logging.Logger.getLogger(telaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxTipoCliente;
    private javax.swing.JFormattedTextField jFormattedTextFieldCNPJ;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JFormattedTextField jFormattedTextFieldContato;
    private javax.swing.JFormattedTextField jFormattedTextFieldEmail;
    private javax.swing.JFormattedTextField jFormattedTextFieldId;
    private javax.swing.JFormattedTextField jFormattedTextFieldLogradouro;
    private javax.swing.JFormattedTextField jFormattedTextFieldNome;
    private javax.swing.JFormattedTextField jFormattedTextFieldNomeComplemento;
    private javax.swing.JFormattedTextField jFormattedTextFieldNumero;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JFormattedTextField jFormattedTextIncricaoEstadual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCliente;
    // End of variables declaration//GEN-END:variables
}
