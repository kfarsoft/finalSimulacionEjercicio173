/*
 * Created by JFormDesigner on Sun Jun 28 20:24:49 ART 2020
 */

package simulacion;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Ivan
 */
public class Resultado extends JFrame {

    //Simulacion 1
    int getColaEspera1;
    double getGanancia1;
    int cantRepartidores1;
    //Simulacion 2
    int getColaEspera2;
    double getGanancia2;
    int cantRepartidores2;

    //Simulacion 3
    int getColaEspera3;
    double getGanancia3;
    int cantRepartidores3;



    public Resultado(Simulacion sim1, Simulacion sim2, Simulacion sim3 ) {
        initComponents();

        this.getColaEspera1 = sim1.getMaxColaEspera;
        this.getColaEspera2 = sim2.getMaxColaEspera;
        this.getColaEspera3 = sim3.getMaxColaEspera;

        this.getGanancia1 = sim1.getColaGananciaPerdida();
        this.getGanancia2 = sim2.getColaGananciaPerdida();
        this.getGanancia3 = sim3.getColaGananciaPerdida();

        this.cantRepartidores1 = sim1.getCantidadRepartidores();
        this.cantRepartidores2 = sim2.getCantidadRepartidores();
        this.cantRepartidores3 = sim3.getCantidadRepartidores();

        label10.setText("Cantidad de repartidores: "+cantRepartidores1);
        label11.setText("Cantidad de repartidores: "+cantRepartidores2);
        label12.setText("Cantidad de repartidores: "+cantRepartidores3);


        label4.setText("Pedidos en espera maximo: "+getColaEspera1);
        label5.setText("Pedidos en espera maximo: "+getColaEspera2);
        label6.setText("Pedidos en espera maximo: "+getColaEspera3);

        label7.setText("Ganancia / Perdida: "+getGanancia1);
        label8.setText("Ganancia / Perdida: "+getGanancia2);
        label9.setText("Ganancia / Perdida: "+getGanancia3);

        Vector<Double> gananciList = new Vector();
        gananciList.add(getGanancia1);
        gananciList.add(getGanancia2);
        gananciList.add(getGanancia3);
        Collections.sort(gananciList);
        double getMax = gananciList.lastElement();

        if (getMax == getGanancia1){
            label13.setText("Conviene utilizar "+cantRepartidores1+" repartidores");
        }else if(getMax == getGanancia2){
            label13.setText("Conviene utilizar "+cantRepartidores2+" repartidores");
        }else if(getMax == getGanancia3){
            label13.setText("Conviene utilizar "+cantRepartidores3+" repartidores");
        }

    }

    private void okButtonActionPerformed(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ivan
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("Simulacion 1");

                //---- label2 ----
                label2.setText("Simulacion 2");

                //---- label3 ----
                label3.setText("Simulacion 3");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                    .addGap(146, 146, 146)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label13, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addComponent(label10, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                            .addGap(35, 35, 35)
                                            .addComponent(label11, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))))
                                    .addGap(23, 23, 23))))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label10, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                .addComponent(label11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label12, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
                            .addGap(11, 11, 11)
                            .addComponent(label13, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ivan
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
