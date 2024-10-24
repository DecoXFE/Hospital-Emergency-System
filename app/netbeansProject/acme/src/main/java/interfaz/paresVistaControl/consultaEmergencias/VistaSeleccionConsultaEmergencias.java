/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz.paresVistaControl.consultaEmergencias;

import javax.swing.DefaultListModel;

/**
 *
 * @author elena
 */
public class VistaSeleccionConsultaEmergencias extends javax.swing.JFrame {

    public CtrlVistaSeleccionConsultaEmergencias control;
    DefaultListModel modelo = new DefaultListModel();

    /**
     * Creates new form VistaInicioSesion
     */
    public VistaSeleccionConsultaEmergencias() {
        initComponents();
        textoFechaSelec.setEditable(true);
        this.setLocationRelativeTo(null);
        control = new CtrlVistaSeleccionConsultaEmergencias(this);
        listaActivaciones.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textoFechaSelec = new javax.swing.JTextField();
        botonConsultar = new javax.swing.JToggleButton();
        mensajeErr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaActivaciones = new javax.swing.JList<>();
        filtroTraslado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        selectButton = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionEmergenciaArea = new javax.swing.JTextArea();
        idTextfield = new javax.swing.JTextField();
        direccionTextField = new javax.swing.JTextField();
        fechaActivacionField = new javax.swing.JTextField();
        horaActivacionField = new javax.swing.JTextField();
        fechaSeHaceCargoField = new javax.swing.JTextField();
        horaSeHaceCargoField = new javax.swing.JTextField();
        trasladoField = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(485, 287));

        jPanel1.setMinimumSize(new java.awt.Dimension(485, 287));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONSULTAR EMERGENCIAS ATENDIDAS POR OPERATIVO ");

        textoFechaSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoFechaSelecActionPerformed(evt);
            }
        });

        botonConsultar.setText("Consultar");
        botonConsultar.setAlignmentX(0.5F);
        botonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarActionPerformed(evt);
            }
        });

        mensajeErr.setForeground(new java.awt.Color(255, 51, 0));
        mensajeErr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listaActivaciones.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        listaActivaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaActivaciones);

        filtroTraslado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ambas activaciones", "Con traslado", "Sin traslado" }));
        filtroTraslado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroTrasladoItemStateChanged(evt);
            }
        });
        filtroTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroTrasladoActionPerformed(evt);
            }
        });

        jLabel4.setText("Filtrar por traslado a hospital");

        selectButton.setText("Mostrar Detalles");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filtroTraslado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mensajeErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(textoFechaSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(botonCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectButton)
                .addGap(231, 231, 231))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoFechaSelec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(botonConsultar)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButton)
                    .addComponent(botonCancelar))
                .addGap(18, 18, 18)
                .addComponent(mensajeErr, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jLabel5.setFont(new java.awt.Font("JetBrainsMono Nerd Font", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DETALLES DE LA ACTIVACION");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("- ID Activación:  ");

        jLabel7.setText("- Nombre de la vía donde acudió:");

        jLabel8.setText("- Fecha de activacion:");

        jLabel9.setText("- Hora  de activacion:");

        jLabel10.setText("- Fecha se hace cargo: ");

        jLabel11.setText("- Hora se hace cargo:");

        jLabel12.setText("- Se decidió su traslado al hospital:");

        jLabel13.setText("- Descripcion de la emergentcia");

        descripcionEmergenciaArea.setEditable(false);
        descripcionEmergenciaArea.setColumns(20);
        descripcionEmergenciaArea.setRows(5);
        descripcionEmergenciaArea.setText("No se ha seleccionado activación");
        jScrollPane2.setViewportView(descripcionEmergenciaArea);

        idTextfield.setText("No se ha seleccionado activación");
        idTextfield.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        idTextfield.setEnabled(false);
        idTextfield.setFocusable(false);
        idTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextfieldActionPerformed(evt);
            }
        });

        direccionTextField.setText("No se ha seleccionado activación");
        direccionTextField.setToolTipText("");
        direccionTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        direccionTextField.setEnabled(false);
        direccionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionTextFieldActionPerformed(evt);
            }
        });

        fechaActivacionField.setText("No se ha seleccionado activación");
        fechaActivacionField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fechaActivacionField.setEnabled(false);
        fechaActivacionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActivacionFieldActionPerformed(evt);
            }
        });

        horaActivacionField.setText("No se ha seleccionado activación");
        horaActivacionField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        horaActivacionField.setEnabled(false);

        fechaSeHaceCargoField.setText("No se ha seleccionado activación");
        fechaSeHaceCargoField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fechaSeHaceCargoField.setEnabled(false);

        horaSeHaceCargoField.setText("No se ha seleccionado activación");
        horaSeHaceCargoField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        horaSeHaceCargoField.setEnabled(false);
        horaSeHaceCargoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaSeHaceCargoFieldActionPerformed(evt);
            }
        });

        trasladoField.setText("No se ha seleccionado activación");
        trasladoField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        trasladoField.setEnabled(false);
        trasladoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trasladoFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(horaSeHaceCargoField, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(trasladoField)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(direccionTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(fechaActivacionField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(horaActivacionField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaSeHaceCargoField)
                            .addComponent(idTextfield))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fechaActivacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(horaActivacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fechaSeHaceCargoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(horaSeHaceCargoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(trasladoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarActionPerformed
        control.procesaSeleccionarFecha();
    }//GEN-LAST:event_botonConsultarActionPerformed

    private void textoFechaSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoFechaSelecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoFechaSelecActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        control.cancelarOperacion();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void filtroTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroTrasladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroTrasladoActionPerformed

    private void idTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextfieldActionPerformed

    private void direccionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionTextFieldActionPerformed

    private void fechaActivacionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActivacionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActivacionFieldActionPerformed

    private void horaSeHaceCargoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaSeHaceCargoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaSeHaceCargoFieldActionPerformed

    private void trasladoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trasladoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trasladoFieldActionPerformed

    private void filtroTrasladoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtroTrasladoItemStateChanged
        control.mostrarActivaciones();
    }//GEN-LAST:event_filtroTrasladoItemStateChanged

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        control.procesaMostrarDetalles();
    }//GEN-LAST:event_selectButtonActionPerformed

    public String getFecha() {
        return textoFechaSelec.getText();
    }

    public void muestraMensajeError(String err) {
        mensajeErr.setText(err);
        resetearVista();
    }

    public void muestraMensajeErrorSinResetearVista(String err) {
        mensajeErr.setText(err);
    }

    public void resetearVista() {
        textoFechaSelec.setText("");
    }

    public void fijarFecha() {
        textoFechaSelec.setEditable(false);
    }

    public void agregarActivacionALista(String activacion) {
        modelo.addElement(activacion);
    }

    public String getFiltroActivacion() {
        return filtroTraslado.getSelectedItem().toString();
    }

    public DefaultListModel resetearActivaciones() {
        modelo.removeAllElements();
        return modelo;
    }

    public String getActivacionSeleccionada() {
        return listaActivaciones.getSelectedValue();
    }

    public void mostrarDatosActivacion(String[] datos) {
        idTextfield.setText(datos[0]);
        direccionTextField.setText(datos[1]);
        fechaActivacionField.setText(datos[2]);
        horaActivacionField.setText(datos[3]);
        fechaSeHaceCargoField.setText(datos[4]);
        horaSeHaceCargoField.setText(datos[5]);
        trasladoField.setText(datos[6]);
        descripcionEmergenciaArea.setText(datos[7]);
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
            java.util.logging.Logger.getLogger(VistaSeleccionConsultaEmergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionConsultaEmergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionConsultaEmergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionConsultaEmergencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaSeleccionConsultaEmergencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JToggleButton botonConsultar;
    private javax.swing.JTextArea descripcionEmergenciaArea;
    private javax.swing.JTextField direccionTextField;
    private javax.swing.JTextField fechaActivacionField;
    private javax.swing.JTextField fechaSeHaceCargoField;
    private javax.swing.JComboBox<String> filtroTraslado;
    private javax.swing.JTextField horaActivacionField;
    private javax.swing.JTextField horaSeHaceCargoField;
    private javax.swing.JTextField idTextfield;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> listaActivaciones;
    private javax.swing.JLabel mensajeErr;
    private javax.swing.JButton selectButton;
    private javax.swing.JTextField textoFechaSelec;
    private javax.swing.JTextField trasladoField;
    // End of variables declaration//GEN-END:variables

}
