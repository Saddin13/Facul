
package Visao;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Enfase;
import modelos.Curso;
import modelos.Aluno;
import persistencia.ManipularCurso;
import persistencia.ManipularEnfase;
import persistencia.ManipularAluno;
import javax.swing.JOptionPane;
import modelos.AlunoCompleto;
        
public class TelaPrincipal extends javax.swing.JFrame {

    private TreeSet<Curso > Cursos  = new TreeSet<>();
    private TreeSet<Enfase> Enfases = new TreeSet<>();
    private TreeSet<Aluno > Alunos  = new TreeSet<>();
    
    ManipularCurso  dadosCurso  = new ManipularCurso ("./src/Arquivos/Cursos.csv");
    ManipularEnfase dadosEnfase = new ManipularEnfase("./src/Arquivos/Enfase.csv");
    ManipularAluno  dadosAlunos = new ManipularAluno ("./src/Arquivos/DadosDosAlunos.csv");

    public TelaPrincipal() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
        
        Alunos  = dadosAlunos.obterListaAluno();
        Cursos  = dadosCurso.obterListaCurso();
        Enfases = dadosEnfase.obterListaEnfase();
    }
    
    private TreeSet<AlunoCompleto> alunosCompletos = new TreeSet<>();

private void carregarAlunosCompletos() {
    try {
        alunosCompletos.clear();
        for (Aluno aluno : Alunos) {
            String descricaoCurso = Cursos.stream()
                .filter(c -> c.getIdCurso() == aluno.getIDcurso())
                .map(c -> c.getdCurso())
                .findFirst()
                .orElse("Curso não encontrado");
                
            String descricaoEnfase = Enfases.stream()
                .filter(e -> e.getIdEnfase() == aluno.getIDenfase())
                .map(e -> e.getdEnfase())
                .findFirst()
                .orElse("Ênfase não encontrada");
                
            AlunoCompleto alunoCompleto = new AlunoCompleto(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getTurno(),
                aluno.getPeriodo(),
                aluno.getIDenfase(),
                descricaoEnfase,
                aluno.getIDcurso(),
                descricaoCurso
            );
            alunosCompletos.add(alunoCompleto);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar alunos completos: " + e.getMessage());
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCursos = new javax.swing.JButton();
        jEnfase = new javax.swing.JButton();
        jAlunos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jCursos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jCursos.setText("Cursos");
        jCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCursosActionPerformed(evt);
            }
        });

        jEnfase.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jEnfase.setText("Enfase");
        jEnfase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEnfaseActionPerformed(evt);
            }
        });

        jAlunos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jAlunos.setText("Alunos");
        jAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlunosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Tela Inicial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAlunos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jEnfase, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEnfase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCursosActionPerformed
    try {
        this.Cursos = dadosCurso.obterListaCurso();
        System.out.println("Cursos carregados na TelaPrincipal: " + this.Cursos.size());
        
        if (this.Cursos == null || this.Cursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há cursos cadastrados");
            return;
        }
        
        
        telaCursos telaCurso = new telaCursos(this.Cursos);
        telaCurso.setVisible(true);
        telaCurso.setLocationRelativeTo(null);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao abrir tela de cursos: " + e.getMessage());
    }
    }//GEN-LAST:event_jCursosActionPerformed

    private void jEnfaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEnfaseActionPerformed
    try {
        this.Enfases = dadosEnfase.obterListaEnfase();
        System.out.println("Enfases carregadas na TelaPrincipal: " + this.Enfases.size());
        
        if (this.Enfases == null || this.Enfases.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há Ênfases cadastradas");
            return;
        }
        
        telaEnfase telaEnfase = new telaEnfase(this.Enfases);
        telaEnfase.setVisible(true);
        telaEnfase.setLocationRelativeTo(null);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao abrir tela de ênfases: " + e.getMessage());
    }
    }//GEN-LAST:event_jEnfaseActionPerformed

    private void jAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlunosActionPerformed
        // TODO add your handling code here:
        carregarAlunosCompletos();
        try {
        System.out.println("Enfases carregadas na TelaPrincipal: " + this.Alunos.size());
        
        if (this.alunosCompletos == null || this.alunosCompletos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há Alunos cadastradas");
            return;
        }
        
        telaAlunos telaAlunos = new telaAlunos(this.alunosCompletos,this.Cursos,this.Enfases);
        telaAlunos.setVisible(true); 
        telaAlunos.setLocationRelativeTo(null);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao abrir tela de ênfases: " + e.getMessage());
    }
    }//GEN-LAST:event_jAlunosActionPerformed
    public static void main(String args[]) {
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
    private javax.swing.JButton jAlunos;
    private javax.swing.JButton jCursos;
    private javax.swing.JButton jEnfase;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
