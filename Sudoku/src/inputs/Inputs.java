package src.inputs;
import src.*;
import src.sudoku.SudokuUI;	
import javax.swing.JButton;
import javax.swing.JPanel;


/*
 * Inputs.java
 * @author 
 */
public class Inputs extends javax.swing.JPanel {

    private SudokuUI tablero;
    private JPanel bloque;
    private JButton botonEntrada;
    private int noDInput;
    
    
    /** Metodo que inicializa las casillas de posibles respuesta */
    public Inputs(SudokuUI tablero, JPanel bloque, JButton inButton, int noDInput) {
        this.tablero = tablero;
        this.bloque = bloque;
        this.botonEntrada = inButton;
        this.noDInput = noDInput;
        initComponents();
    }

    /* Metodo que genera los componentes de la grilla para seleccionar que número va en que
       casilla
       @param null
    */
    private void initComponents() {

        uno = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        nueve = new javax.swing.JButton();

	
        setLayout(new java.awt.GridLayout(3, 3));


        uno.setBackground(new java.awt.Color(255, 255, 255));
        uno.setText("1");
        uno.setAlignmentY(0.0F);
        uno.setIconTextGap(1);
        uno.setMargin(new java.awt.Insets(2, 2, 2, 2));
        uno.setMinimumSize(new java.awt.Dimension(15, 15));
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(uno);

        dos.setBackground(new java.awt.Color(255, 255, 255));
        dos.setText("2");
        dos.setAlignmentY(0.0F);
        dos.setIconTextGap(1);
        dos.setMargin(new java.awt.Insets(2, 2, 2, 2));
        dos.setMinimumSize(new java.awt.Dimension(15, 15));
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(dos);

        tres.setBackground(new java.awt.Color(255, 255, 255));
        tres.setText("3");
        tres.setAlignmentY(0.0F);
        tres.setIconTextGap(1);
        tres.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tres.setMinimumSize(new java.awt.Dimension(15, 15));
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(tres);

        cuatro.setBackground(new java.awt.Color(255, 255, 255));
        cuatro.setText("4");
        cuatro.setAlignmentY(0.0F);
        cuatro.setIconTextGap(1);
        cuatro.setMargin(new java.awt.Insets(2, 2, 2, 2));
        cuatro.setMinimumSize(new java.awt.Dimension(15, 15));
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(cuatro);

        cinco.setBackground(new java.awt.Color(255, 255, 255));
        cinco.setText("5");
        cinco.setAlignmentY(0.0F);
        cinco.setIconTextGap(1);
        cinco.setMargin(new java.awt.Insets(2, 2, 2, 2));
        cinco.setMinimumSize(new java.awt.Dimension(15, 15));
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(cinco);

        seis.setBackground(new java.awt.Color(255, 255, 255));
        seis.setText("6");
        seis.setAlignmentY(0.0F);
        seis.setIconTextGap(1);
        seis.setMargin(new java.awt.Insets(2, 2, 2, 2));
        seis.setMinimumSize(new java.awt.Dimension(15, 15));
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(seis);

        siete.setBackground(new java.awt.Color(255, 255, 255));
        siete.setText("7");
        siete.setAlignmentY(0.0F);
        siete.setIconTextGap(1);
        siete.setMargin(new java.awt.Insets(2, 2, 2, 2));
        siete.setMinimumSize(new java.awt.Dimension(15, 15));
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(siete);

        ocho.setBackground(new java.awt.Color(255, 255, 255));
        ocho.setText("8");
        ocho.setAlignmentY(0.0F);
        ocho.setIconTextGap(1);
        ocho.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ocho.setMinimumSize(new java.awt.Dimension(15, 15));
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(ocho);

        nueve.setBackground(new java.awt.Color(255, 255, 255));
        nueve.setText("9");
        nueve.setAlignmentY(0.0F);
        nueve.setIconTextGap(1);
        nueve.setMargin(new java.awt.Insets(2, 2, 2, 2));
        nueve.setMinimumSize(new java.awt.Dimension(15, 15));
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAns(evt);
            }
        });
        add(nueve);
    }

    private void setAns(java.awt.event.ActionEvent evt){
        tablero.setInput(evt.getActionCommand(), bloque, botonEntrada);
    }


    private javax.swing.JButton ocho;
    private javax.swing.JButton cinco;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton nueve;
    private javax.swing.JButton uno;
    private javax.swing.JButton siete;
    private javax.swing.JButton seis;
    private javax.swing.JButton tres;
    private javax.swing.JButton dos;

}
