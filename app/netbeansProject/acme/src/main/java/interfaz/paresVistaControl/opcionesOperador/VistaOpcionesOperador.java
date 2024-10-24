/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz.paresVistaControl.opcionesOperador;



/**
 *
 * @author diego
 */
public class VistaOpcionesOperador extends javax.swing.JFrame {

    CtrlVistaOpcionesOperador control;

    /**
     * Creates new form VistaOpcionesPersonalDeOperativo
     */
    public VistaOpcionesOperador() {
        initComponents();
        this.setLocationRelativeTo(null);
        control = new CtrlVistaOpcionesOperador(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonConsultarDatosEnEmpresa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botonSolicitarVacaciones = new javax.swing.JButton();
        botonOrganizarTurnosDeOperador = new javax.swing.JButton();
        mensajeErr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonConsultarDatosEnEmpresa.setText("Consultar mis datos en la empresa");
        botonConsultarDatosEnEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarDatosEnEmpresaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Operador");

        botonSolicitarVacaciones.setText("Solicitar Vacaciones");
        botonSolicitarVacaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSolicitarVacacionesActionPerformed(evt);
            }
        });

        botonOrganizarTurnosDeOperador.setText("Atender llamada");
        botonOrganizarTurnosDeOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOrganizarTurnosDeOperadorActionPerformed(evt);
            }
        });

        mensajeErr.setForeground(new java.awt.Color(255, 51, 51));
        mensajeErr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonConsultarDatosEnEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonSolicitarVacaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonOrganizarTurnosDeOperador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(127, Short.MAX_VALUE))
            .addComponent(mensajeErr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonConsultarDatosEnEmpresa)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(botonSolicitarVacaciones)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(botonOrganizarTurnosDeOperador)
                .addGap(30, 30, 30)
                .addComponent(mensajeErr, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConsultarDatosEnEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarDatosEnEmpresaActionPerformed
        sinImplementar();
    }//GEN-LAST:event_botonConsultarDatosEnEmpresaActionPerformed

    private void botonOrganizarTurnosDeOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOrganizarTurnosDeOperadorActionPerformed
        control.procesaAtenderLlamada();
    }//GEN-LAST:event_botonOrganizarTurnosDeOperadorActionPerformed

    private void botonSolicitarVacacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSolicitarVacacionesActionPerformed
        sinImplementar();
    }//GEN-LAST:event_botonSolicitarVacacionesActionPerformed

    public void sinImplementar() {
        mensajeErr.setText("No está implementado");
    }

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
            java.util.logging.Logger.getLogger(VistaOpcionesOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaOpcionesOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaOpcionesOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaOpcionesOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaOpcionesOperador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConsultarDatosEnEmpresa;
    private javax.swing.JButton botonOrganizarTurnosDeOperador;
    private javax.swing.JButton botonSolicitarVacaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel mensajeErr;
    // End of variables declaration//GEN-END:variables
}
