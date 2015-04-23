/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdapp;

/**
 *
 * @author dani__000
 */

import org.jfree.chart.*;
import org.jfree.data.xy.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;

public class Grafico extends javax.swing.JFrame {
    
    /**
     * Creates new form Grafico
     */
    public JFreeChart chart;
    
    public Grafico() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.GraficoLineas.setVisible(true);
        this.Tabla.setVisible(false);
        this.jScrollPane1.setVisible(false);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Capa = new javax.swing.JLayeredPane();
        GraficoLineas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Capa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Capa.setPreferredSize(new java.awt.Dimension(574, 170));

        GraficoLineas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GRAFICO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "X", "Y"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla);

        javax.swing.GroupLayout GraficoLineasLayout = new javax.swing.GroupLayout(GraficoLineas);
        GraficoLineas.setLayout(GraficoLineasLayout);
        GraficoLineasLayout.setHorizontalGroup(
            GraficoLineasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GraficoLineasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(578, Short.MAX_VALUE))
        );
        GraficoLineasLayout.setVerticalGroup(
            GraficoLineasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GraficoLineasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 500, Short.MAX_VALUE))
        );

        jButton1.setText("Dibujar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CapaLayout = new javax.swing.GroupLayout(Capa);
        Capa.setLayout(CapaLayout);
        CapaLayout.setHorizontalGroup(
            CapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GraficoLineas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CapaLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        CapaLayout.setVerticalGroup(
            CapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GraficoLineas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(6, 6, 6))
        );
        Capa.setLayer(GraficoLineas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Capa.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Capa, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Capa, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ChartPanel panel;

        XYSplineRenderer renderer = new XYSplineRenderer();
        XYSeriesCollection dataset = new XYSeriesCollection();

        ValueAxis x = new NumberAxis();
        ValueAxis y = new NumberAxis();

        XYSeries serie = new XYSeries("Dinero Exacto");
        XYSeries serie2 = new XYSeries("Max");
        XYSeries serie3 = new XYSeries("Min");
        XYPlot plot;
        GraficoLineas.removeAll();
        double i;

        for (i = 0; i < Calculos.SubDivision; i++){
            serie.add(i,Calculos.v1[(int)i]);
        }
        for (i = 0; i < Calculos.SubDivision; i++){
            serie2.add(i,Calculos.max[(int)i]);
        }
        for (i = 0; i < Calculos.SubDivision; i++){
            serie3.add(i,Calculos.min[(int)i]);
        }
        dataset.addSeries(serie);
        dataset.addSeries(serie2);
        dataset.addSeries(serie3);
        x.setLabel("Num Tirada(1M:10000)");
        y.setLabel("Current Mony");

        plot = new XYPlot(dataset,x,y,renderer);
        chart = new JFreeChart(plot);
        chart.setTitle("Grafico");
        panel = new ChartPanel(chart);
        panel.setBounds(0, 0, 1350, 620);
        GraficoLineas.add(panel);
        GraficoLineas.repaint();

        try {
            ChartUtilities.saveChartAsJPEG(new File("../bdapp/grafico2.jpg"), chart, 1500, 500);
        } catch (IOException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Capa;
    private javax.swing.JPanel GraficoLineas;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
