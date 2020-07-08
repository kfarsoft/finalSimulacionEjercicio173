package simulacion;



import simulacion.admin_pedido.Pedido;
import simulacion.repartidor.EstadoRep;
import simulacion.repartidor.Repartidor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/*
 * Created by JFormDesigner on Tue Jun 23 19:34:01 ART 2020
 */

//enum Evento{
//    INICIOSIMULACION,
//    LLEGADAPEDIDO,
//    FINENTREGA,
//    REENVIOPEDIDO
//}

/**
 * @author Ivan
 */
public class Principal extends JFrame {

    Simulacion sim1;
    Simulacion sim2;
    Simulacion sim3;
    DefaultTableModel modelo1;
    DefaultTableModel modelo2;
    DefaultTableModel modelo3;


    public Principal() {
        initComponents();
    }

    private boolean validarDatos(){
        if (!validarNumeros(textField1.getText()) || !validarNumeros(textField2.getText()) || !validarNumeros(textField3.getText())){
            JOptionPane.showMessageDialog(null, "Ingrese un valor numerico", " Atención ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }


    private void okButtonActionPerformed(ActionEvent e) {
        //Validamos los datos ingresados
        if (validarDatos()){
            int cantMinutos = Integer.parseInt(textField1.getText());
            int desdeMinutos = Integer.parseInt(textField3.getText());
            int hastaMinutos = Integer.parseInt(textField4.getText());
            int cantidadRepartidores = Integer.parseInt(textField2.getText());

            //Simulacion 1
            sim1 = new Simulacion(cantidadRepartidores, cantMinutos, desdeMinutos, hastaMinutos);
            modelo1 = sim1.Simular();
            mostrarTabla(modelo1, table1);

            //Simulacion 2
            sim2 = new Simulacion((cantidadRepartidores-1), cantMinutos, desdeMinutos, hastaMinutos);
            modelo2 = sim2.Simular();
            mostrarTabla(modelo2, table2);            
            
            //Simulacion 3
            sim3 = new Simulacion((cantidadRepartidores+1), cantMinutos, desdeMinutos, hastaMinutos);
            modelo3 = sim3.Simular();
            mostrarTabla(modelo3, table3);
        }
    }
    
    private void mostrarTabla(DefaultTableModel modelo, JTable table){
        table.setModel(modelo);
        int tamFila = table.getColumnCount();
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setResizable(false);
        for(int i = 3; i <= tamFila - 1; i ++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        centrarValores(table, tamFila);
        table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }
    public void centrarValores(JTable tabla, int columnas) {
        TableCellRenderer render = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                return l;
            }
        };
        for(int i = 0; i < columnas; i ++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        Resultado resultado = new Resultado(sim1, sim2, sim3);
        resultado.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ivan
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        okButton = new JButton();
        button1 = new JButton();
        scrollPane4 = new JScrollPane();
        table3 = new JTable();
        label2 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== contentPanel ========
        {
            contentPanel.setPreferredSize(new Dimension(1209, 826));
            contentPanel.setMinimumSize(new Dimension(1120, 900));
            contentPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
            .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
            .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
            Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),contentPanel. getBorder()));contentPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
            public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
            )))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }

            //======== panel1 ========
            {

                //---- label1 ----
                label1.setText("Cantidad de minutos a simular:");

                //---- label3 ----
                label3.setText("Cantidad de repartidores:");

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                            .addGap(60, 60, 60))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(43, 43, 43))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36))))
                );
            }

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(table2);
            }

            //---- okButton ----
            okButton.setText("SIMULAR");
            okButton.addActionListener(e -> okButtonActionPerformed(e));

            //---- button1 ----
            button1.setText("Mostrar Resultados");
            button1.addActionListener(e -> button1ActionPerformed(e));

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(table3);
            }

            //---- label2 ----
            label2.setText("Desde");

            //---- label4 ----
            label4.setText("Hasta");

            GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
            contentPanel.setLayout(contentPanelLayout);
            contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup()
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPanelLayout.createParallelGroup()
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(okButton))
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(contentPanelLayout.createParallelGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(235, Short.MAX_VALUE))
                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
                    .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
            );
            contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup()
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGroup(contentPanelLayout.createParallelGroup()
                                    .addComponent(button1)
                                    .addComponent(okButton))
                                .addGap(6, 6, 6)
                                .addGroup(contentPanelLayout.createParallelGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }
        contentPane.add(contentPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public boolean validarNumeros(String nro) {
        float m;
        try {
            if(nro.isBlank() || nro.isEmpty()){
                return false;
            }
            if (nro.compareTo("") == 0) {
                return true;
            }
            m = Float.parseFloat(nro);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese solamente numeros.");
            return false;
        }
        if (m < 0) {
            JOptionPane.showMessageDialog(this, "Ingrese numeros positivos.");
            return false;
        } else {
            return true;
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ivan
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JButton okButton;
    private JButton button1;
    private JScrollPane scrollPane4;
    private JTable table3;
    private JLabel label2;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
