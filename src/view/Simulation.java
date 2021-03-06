/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.RouteTable;
import messages.TableLine;
import tools.Colors;
import tools.Edges;

/**
 *
 * @author Yan Kaic
 */
public class Simulation extends javax.swing.JFrame {

  private Node incompleteNode;
  private Edge incompleteEdge;
  private ArrayList<Node> nodes;
  private MouseAdapter nodeMouseClicked;
  private ArrayList<Edge> edgeList;
  private Edges edges;

  /**
   * Creates new form Simulation
   */
  public Simulation() {
    initComponents();

    init();

  }

  private void init() {
    nodePanel.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        if (incompleteNode != null) {
          incompleteNode.moveMouse();
        }
      }
    }
    );
    nodes = new ArrayList<>();
    edgeList = new ArrayList();
    edges = new Edges();

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (incompleteNode != null) {
          nodePanel.remove(incompleteNode);
          incompleteNode = null;
          repaint();
          System.out.println((int) (e.getKeyChar()));
        }
      }
    }
    );

    nodeMouseClicked = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        nodes.add(incompleteNode);
        incompleteNode.removeMouseListener(nodeMouseClicked);
        incompleteNode.createRouter();
        incompleteNode = null;
      }
    };

    refresh();

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jToolBar1 = new javax.swing.JToolBar();
    addNodeButton = new javax.swing.JButton();
    addEdgeButton = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JToolBar.Separator();
    jScrollPane1 = new javax.swing.JScrollPane();
    nodePanel = new javax.swing.JPanel(){
      @Override
      public void paintComponent(Graphics gph) {
        super.paintComponent(gph);
        Graphics2D graphics = (Graphics2D) gph.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintNodePanel(graphics);

      }
    };
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenu2 = new javax.swing.JMenu();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMaximumSize(new java.awt.Dimension(819, 511));
    setMinimumSize(new java.awt.Dimension(819, 511));

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    addNodeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addIcon.png"))); // NOI18N
    addNodeButton.setFocusable(false);
    addNodeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addNodeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addNodeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addNodeButtonActionPerformed(evt);
      }
    });
    jToolBar1.add(addNodeButton);

    addEdgeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edgeIcon.png"))); // NOI18N
    addEdgeButton.setFocusable(false);
    addEdgeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addEdgeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addEdgeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addEdgeButtonActionPerformed(evt);
      }
    });
    jToolBar1.add(addEdgeButton);
    jToolBar1.add(jSeparator1);

    getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

    jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
    jScrollPane1.setOpaque(false);

    nodePanel.setBackground(new java.awt.Color(255, 255, 255));
    nodePanel.setMinimumSize(new java.awt.Dimension(500, 400));
    nodePanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        nodePanelMouseEntered(evt);
      }
    });
    nodePanel.setLayout(null);
    jScrollPane1.setViewportView(nodePanel);

    getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

    jMenu1.setText("File");
    jMenuBar1.add(jMenu1);

    jMenu2.setText("Edit");
    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void addNodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNodeButtonActionPerformed
    incompleteNode = new Node();
    incompleteNode.setLocation(20, 20);
    incompleteNode.setBackground(Color.GRAY);
    nodePanel.add(incompleteNode);

    incompleteNode.addMouseListener(nodeMouseClicked);
    incompleteNode.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (incompleteEdge != null) {
          if (incompleteEdge.isEmpty()) {
            Node node = (Node) e.getSource();
            incompleteEdge.add(node);
          }
          else {
            Node A = incompleteEdge.getA();
            Node B = (Node) e.getSource();
            A.connect(B);
            B.connect(A);
            incompleteEdge.add(B);
            edgeList.add(incompleteEdge);
            incompleteEdge = null;
          }
        }
      }
    });
  }//GEN-LAST:event_addNodeButtonActionPerformed

  private void addEdgeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEdgeButtonActionPerformed
    createEdge();
  }//GEN-LAST:event_addEdgeButtonActionPerformed

  private void nodePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nodePanelMouseEntered

  }//GEN-LAST:event_nodePanelMouseEntered

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
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Simulation().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addEdgeButton;
  private javax.swing.JButton addNodeButton;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToolBar.Separator jSeparator1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JPanel nodePanel;
  // End of variables declaration//GEN-END:variables

  private void createEdge() {
    incompleteEdge = new Edge();
  }

  public void refresh() {
    new Thread() {
      @Override
      public void run() {
        while (true) {
          try {
            nodePanel.repaint();
            sleep(1000 / 60);
          }
          catch (InterruptedException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }.start();
  }

  public void paintNodePanel(Graphics2D graphics) {
    paintIncompleteLine(graphics);
    paintDirectConnections(graphics);
  }

  private void paintDirectConnections(Graphics2D graphics) {
    for (int i = 0; i < edgeList.size(); i++) {
      Edge edge = edgeList.get(i);
      Node origin = edge.getA();
      Node destination = edge.getB();
      
      Point A = origin.getCenter();
      Point B = destination.getCenter();
      edges.clear();
      addLines(origin, destination,edges);
      addLines(destination, origin, edges);
      int d = -edges.size() / 2;

      for (int k = 0; k < edges.size(); k++) {
        if (d == 0) {
          d++;
        }
        graphics.setPaint(Color.GRAY);
        graphics.drawLine(A.x, A.y, B.x, B.y);
//        d = paintCurve(graphics, k, A, B, d);
      }

    }

  }

  private int paintCurve(Graphics2D graphics, int k, Point A, Point B, int d) {
    graphics.setPaint(edges.get(k).getColor());
    Point C = getCurvePoint(A, B, d++ * 15);
    QuadCurve2D curve = new QuadCurve2D.Double(A.x, A.y, C.x, C.y, B.x, B.y);
    graphics.draw(curve);
    return d;
  }

  public Node getNode(String name, Node node) {
    final ArrayList<Node> list = node.getConnections();
    for (int i = 0; i < list.size(); i++) {
      if (name.equals(list.get(i).getRouter().getPort() + "")) {
        return list.get(i);
      }
    }
    return null;
  }

  private void paintIncompleteLine(Graphics2D graphics) throws HeadlessException {
    if (incompleteEdge != null && incompleteEdge.isIncomplete()) {
      Point center = incompleteEdge.getA().getCenter();
      Point panel = nodePanel.getLocationOnScreen();
      Point pointer = MouseInfo.getPointerInfo().getLocation();

      int x = pointer.x - panel.x;
      int y = pointer.y - panel.y;

      graphics.drawLine(center.x, center.y, x, y);
    }
  }

  private Point getCurvePoint(Point A, Point B, int h) {
    double ca, co, hip, x, y, cos, sen;
    co = B.getY() - A.getY();
    ca = B.getX() - A.getX();
    hip = A.distance(B);
    sen = ca / hip;
    cos = co / hip;
    x = (cos * h);
    y = (sen * h);
    co /= 2;
    ca /= 2;

    return new Point((int) (A.x + ca - x), (int) (A.y + co + y));
  }

  private void addLines(Node origin, Node destination, Edges edges) {
    String nameB = destination.getRouter().getPort() + "";
    RouteTable tableA = origin.getRouter().getTable();
    for (int i = 1; i < tableA.size(); i++) {
      TableLine line = tableA.get(i);
      if (line.getLink().equals(nameB)) {
        Edge edge = new Edge(origin, destination, Colors.staticColor(Integer.parseInt(line.getTarget())));
        edges.addEdge(edge);
      }
    }
  }
}
