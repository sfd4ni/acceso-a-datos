/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author laia
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ficheroTextArea = new javax.swing.JTextArea();
        principalMenuBar = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        abrirMenuItem = new javax.swing.JMenuItem();
        guardarMenuItem = new javax.swing.JMenuItem();
        guardarComoMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ficheroTextArea.setColumns(20);
        ficheroTextArea.setRows(5);
        jScrollPane1.setViewportView(ficheroTextArea);

        archivoMenu.setText("Archivo");

        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(abrirMenuItem);

        guardarMenuItem.setText("Guardar");
        archivoMenu.add(guardarMenuItem);

        guardarComoMenuItem.setText("Guardar como");
        guardarComoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarComoMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(guardarComoMenuItem);

        principalMenuBar.add(archivoMenu);

        setJMenuBar(principalMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abrirMenuItemActionPerformed

    private void guardarComoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarComoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarComoMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem abrirMenuItem;
    public javax.swing.JMenu archivoMenu;
    public javax.swing.JTextArea ficheroTextArea;
    public javax.swing.JMenuItem guardarComoMenuItem;
    public javax.swing.JMenuItem guardarMenuItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar principalMenuBar;
    // End of variables declaration//GEN-END:variables
}