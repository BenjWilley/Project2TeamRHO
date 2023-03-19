/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Ashwin
 */
public class GUIproto extends javax.swing.JFrame {
    
    private JTextField textField;
    
    /**
     * Creates new form GUIproto
     */
    public GUIproto() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Coffee", "Espresso", "Tea", "Frappuccino", "Breakfast", "Bakery" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jTabbedPane1.addTab("Server", jComboBox1);

        jButton2.setText("Add Item to menu");

        jButton3.setText("Remove Item from menu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("View/Change Inventory");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jButton2)))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manager", jPanel1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab("Subtotal", jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String selectedOption = jComboBox1.getSelectedItem().toString();
        if(selectedOption.equals("Coffee")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel coffeeLabel = new JLabel("Enter Coffee item:");
            JLabel sizeLabel = new JLabel("Select size:");
            JButton shot = new JButton("Add Espresso shot");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JRadioButton tall = new JRadioButton("Tall");
            JRadioButton grande = new JRadioButton("Grande");
            JRadioButton venti = new JRadioButton("Venti");
            ButtonGroup sizes = new ButtonGroup();
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            sizes.add(tall);
            sizes.add(grande);
            sizes.add(venti);
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(coffeeLabel);
            panel.add(textField);
            panel.add(separator);
            panel.add(sizeLabel);
            panel.add(tall);
            panel.add(grande);
            panel.add(venti);
            panel.add(shot);
            newWindow.add(panel);
        }
        else if(selectedOption.equals("Tea")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel teaLabel = new JLabel("Enter Tea item:");
            JLabel sizeLabel = new JLabel("Select size:");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JRadioButton tall = new JRadioButton("Tall");
            JRadioButton grande = new JRadioButton("Grande");
            JRadioButton venti = new JRadioButton("Venti");
            ButtonGroup sizes = new ButtonGroup();
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            sizes.add(tall);
            sizes.add(grande);
            sizes.add(venti);
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(teaLabel);
            panel.add(textField);
            panel.add(separator);
            panel.add(sizeLabel);
            panel.add(tall);
            panel.add(grande);
            panel.add(venti);
            newWindow.add(panel);
        }
        else if(selectedOption.equals("Espresso")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel espressoLabel = new JLabel("Enter Espresso item:");
            JLabel sizeLabel = new JLabel("Select size:");
            JButton shot = new JButton("Add Espresso shot");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JRadioButton tall = new JRadioButton("Tall");
            JRadioButton grande = new JRadioButton("Grande");
            JRadioButton venti = new JRadioButton("Venti");
            ButtonGroup sizes = new ButtonGroup();
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            sizes.add(tall);
            sizes.add(grande);
            sizes.add(venti);
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(espressoLabel);
            panel.add(textField);
            panel.add(separator);
            panel.add(sizeLabel);
            panel.add(tall);
            panel.add(grande);
            panel.add(venti);
            panel.add(shot);
            newWindow.add(panel);
        }
        else if(selectedOption.equals("Frappuccino")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel frappuccinoLabel = new JLabel("Enter Frappuccino item:");
            JLabel sizeLabel = new JLabel("Select size:");
            JButton shot = new JButton("Add Espresso shot");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JRadioButton tall = new JRadioButton("Tall");
            JRadioButton grande = new JRadioButton("Grande");
            JRadioButton venti = new JRadioButton("Venti");
            ButtonGroup sizes = new ButtonGroup();
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            sizes.add(tall);
            sizes.add(grande);
            sizes.add(venti);
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(frappuccinoLabel);
            panel.add(textField);
            panel.add(separator);
            panel.add(sizeLabel);
            panel.add(tall);
            panel.add(grande);
            panel.add(venti);
            panel.add(shot);
            newWindow.add(panel);
        }
        else if(selectedOption.equals("Breakfast")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel breakfastLabel = new JLabel("Enter Breakfast item:");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(breakfastLabel);
            panel.add(textField);
            newWindow.add(panel);
        }
        else if(selectedOption.equals("Bakery")){
            JFrame newWindow = new JFrame(selectedOption);
            newWindow.setSize(400, 200);
            newWindow.setVisible(true);
            JLabel bakeryLabel = new JLabel("Enter Bakery item:");
            textField = new JTextField(20);
            textField.setMaximumSize(new Dimension(600, 30));
            JPanel panel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);
            panel.add(bakeryLabel);
            panel.add(textField);
            newWindow.add(panel);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIproto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIproto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIproto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIproto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIproto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
