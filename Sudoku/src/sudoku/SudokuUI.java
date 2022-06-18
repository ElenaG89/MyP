package src.sudoku;
import src.*;
import src.reloj.StopWatch;
import src.inputs.Inputs;
/**
 *
 * @author 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * UI.java
 *
 *
 */
public class SudokuUI extends javax.swing.JFrame {

    private JButton[][] botones;
    private ActionListener[][] actionListener;
    private JPanel[][] bloques;
    private Sudoku sudoku;
    private int modoJuego;
    private int cuadricula;
    private boolean pausard;
    private final StopWatch stopWatch;

    /* Metodo que crea la interfaz gráfica
    */
    public SudokuUI() {
        sudoku = new Sudoku();
        modoJuego = Sudoku.modoJuegoMedio;
        stopWatch = new StopWatch();
        pausard = false;
        initComponents();
        initialize();
        iniciarContador();
    }

    /** Método que inicializa los componentes de la interfaz (UI)
     * 
     */

    private void initComponents() {

        base = new javax.swing.JPanel();
        opciones = new javax.swing.JPanel();
        nvoJuego = new javax.swing.JButton();
        reiniciarJgo= new javax.swing.JButton();
        pausar = new javax.swing.JButton();
        etiquetaTiempo = new javax.swing.JLabel();
        continuar = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        holder = new javax.swing.JPanel();
        tablero = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        juego = new javax.swing.JMenu();
        nvoJuegoM = new javax.swing.JMenuItem();
        reiniciarJgoM = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(500, 500));

        base.setBackground(new java.awt.Color(204, 204, 204));
        base.setAlignmentX(0.0F);
        base.setAlignmentY(0.0F);

        //color de la barra de opciones
        opciones.setBackground(new java.awt.Color(255, 255, 255));

        nvoJuego.setBackground(new java.awt.Color(255, 255, 255));
        nvoJuego.setFont(new java.awt.Font("Tahoma", 0, 20));
        nvoJuego.setText("Nuevo");
        nvoJuego.setMargin(new java.awt.Insets(0, 0, 0, 0));
        nvoJuego.setMaximumSize(new java.awt.Dimension(63, 19));
        nvoJuego.setMinimumSize(new java.awt.Dimension(63, 19));
        nvoJuego.setPreferredSize(new java.awt.Dimension(63, 19));
        nvoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionNvoJuego(evt);
            }
        });

        reiniciarJgo.setBackground(new java.awt.Color(255, 255, 255));
        reiniciarJgo.setFont(new java.awt.Font("Tahoma", 0, 20));
        reiniciarJgo.setText("Reiniciar");
        reiniciarJgo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        reiniciarJgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               accionReiniciarJgo(evt);
            }
        });

        pausar.setBackground(new java.awt.Color(255, 255, 255));
        pausar.setFont(new java.awt.Font("Tahoma", 0, 20));
        pausar.setText("Pausar");
        pausar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausarAccion(evt);
            }
        });

        etiquetaTiempo.setFont(new java.awt.Font("Tahoma", 0, 18));
        etiquetaTiempo.setForeground(new java.awt.Color(51, 51, 51));
        etiquetaTiempo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaTiempo.setText("00:00"); 
        etiquetaTiempo.setIconTextGap(0);

        continuar.setBackground(new java.awt.Color(255, 255, 255));
        continuar.setFont(new java.awt.Font("Tahoma", 0, 20));
        continuar.setText("Continuar");
        continuar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarAccion(evt);
            }
        });

        enviar.setBackground(new java.awt.Color(255, 255, 255));
        enviar.setFont(new java.awt.Font("Tahoma", 0, 20));
        enviar.setText("Evaluar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarAccion(evt);
            }
        });

        javax.swing.GroupLayout opcionesLayout = new javax.swing.GroupLayout(opciones);
        opciones.setLayout(opcionesLayout);
        opcionesLayout.setHorizontalGroup(
            opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionesLayout.createSequentialGroup()
                .addComponent(nvoJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reiniciarJgo, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pausar, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continuar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(etiquetaTiempo)
                .addContainerGap())
        );
        opcionesLayout.setVerticalGroup(
            opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nvoJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(reiniciarJgo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(pausar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(continuar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(etiquetaTiempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
        );

        holder.setBackground(new java.awt.Color(255, 255, 255));
        holder.setAlignmentX(0.0F);
        holder.setAlignmentY(0.0F);
        holder.setLayout(new java.awt.GridLayout(1, 1));

        tablero.setBackground(new java.awt.Color(255, 255, 255));
        tablero.setAlignmentX(0.0F);
        tablero.setAlignmentY(0.0F);
        tablero.setMinimumSize(new java.awt.Dimension(100, 100));
        tablero.setPreferredSize(new java.awt.Dimension(100, 100));
        tablero.setLayout(new java.awt.GridLayout(1, 1)); //**/
        holder.add(tablero);

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(holder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(opciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(holder, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );

        juego.setText("Juego");

        nvoJuegoM.setText("Nuevo Juego 9 X 9");
        nvoJuegoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionNvoJuego(evt);
            }
        });
        juego.add(nvoJuegoM);


        reiniciarJgoM.setText("Reiniciar Juego");
        reiniciarJgoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionReiniciarJgo(evt);
            }
        });
        juego.add(reiniciarJgoM);
        juego.add(jSeparator1);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionSalir(evt);
            }
        });
        juego.add(salir);

        menu.add(juego);


        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

     /* Método para iniciar una nueva partida 
       @param evt Evento que detona la acción
    */     
    private void accionNvoJuego(java.awt.event.ActionEvent evt) {
        int[][] rc = sudoku.getNvoRC(cuadricula, modoJuego);
        creartablero(rc);
    }

     /* Método para reiniciar el juego 
       @param evt Evento que detona la acción
    */  
    private void accionReiniciarJgo(java.awt.event.ActionEvent evt) {
        int[][] rc = sudoku.resetPuzzle();
        creartablero(rc);
    }

    /* Método para salir 
       @param evt Evento que detona la acción
    */  
    private void accionSalir(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /* Método que pausa el juego y el reloj 
       @param evt Evento que detona la acción
    */    
    private void pausarAccion(java.awt.event.ActionEvent evt) {
        stopWatch.pausar();
        pausard = true;
        mostrarMensaje("Juego Pausado");
    }

    /* Método para continuar con el juego despues de una pausa 
       @param evt Evento que detona la acción
    */  
    private void continuarAccion(java.awt.event.ActionEvent evt) {
        stopWatch.continuar();
        pausard = false;
        holder.removeAll();
        holder.add(tablero);
        holder.repaint();
        this.setVisible(true);
    }

  /** Método que nos arroja si el envío de la respuesta fue correcto o no
  */
    private void enviarAccion(java.awt.event.ActionEvent evt) {
        if (!estaCompleta()) {
            JOptionPane.showMessageDialog(this, "Su respuesta no está completa.");
        } else {
            stopWatch.stop();
            boolean esRCorrecta = sudoku.check(getRespuesta());
            String mensajeStr = "";
            if (esRCorrecta) {
                mensajeStr = "Felicidades!! Tu respuesta es correcta" + etiquetaTiempo.getText();
            } else {
                mensajeStr = "La respuesta enviada es incorrecta. ";
            }
            mostrarMensaje(mensajeStr);
        }
    }

    /* Metodo que crea el tablero, incluyendo las casilla y el fondo del tablero
       @param rc Array para crear la grilla donde se ponen los numeros que
       componen al juego
    */
    private void creartablero(int[][] rc) {
        tablero.removeAll();
        cuadricula = rc.length;
        bloques = new JPanel[cuadricula][cuadricula];
        botones = new JButton[cuadricula][cuadricula];
        actionListener = new ActionListener[cuadricula][cuadricula];
        tablero.setLayout(new GridLayout(cuadricula, cuadricula, 3, 3));

        int filasCuad =3 ;

        for (int i = 0; i < cuadricula; i++) {
            for (int j = 0; j < cuadricula; j++) {
                bloques[i][j] = new JPanel();
                botones[i][j] = new JButton();
                String text = "";
                if (0 < rc[i][j] && rc[i][j] <= cuadricula) {
                    text += rc[i][j];
                } else {
                    final JButton botontemp = botones[i][j];
                    final JPanel bloqueTemp = bloques[i][j];
                    actionListener[i][j] = new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            verInputs(bloqueTemp, botontemp, cuadricula);
                        }
                    };
                    botones[i][j].addActionListener(actionListener[i][j]);
                }
                botones[i][j].setText(text);
                botones[i][j].setFont(new java.awt.Font("Tahoma", 0, 24));

                if (((0 <= i && i < filasCuad) || (filasCuad * 2 <= i && i < cuadricula)) && (3 <= j && j < 6)) {
                    botones[i][j].setBackground(new java.awt.Color(178, 255, 102));
                } else if ((filasCuad <= i && i < filasCuad * 2) && ((0 <= j && j < 3) || (6 <= j && j < 9))) {
                //morado 204, 153, 204
                    botones[i][j].setBackground(new java.awt.Color(153,255,255));
                }               
                else {
                    botones[i][j].setBackground(new java.awt.Color(255, 204, 229));
                }
                bloques[i][j].setLayout(new GridLayout(1, 1));
                bloques[i][j].add(botones[i][j]);
                tablero.add(bloques[i][j]);
            }
        }

        holder.removeAll();
        holder.add(tablero);
        tablero.repaint();
        holder.repaint();
        this.setVisible(true);
        stopWatch.iniciar();
    }

    /* Metodo que llama al metodo que distribuye numeros aleatorios en el tablero
       @param null
    */
    private void initialize() {
        int[][] rc = sudoku.getNvoRC(cuadricula, modoJuego);
        creartablero(rc);
    }

    /* Metodo que permite ver el input que se agrega a las casillas vacias
       @param bloque
       @param botonEntrada Boton donde se guarda la entrada/input
       @param noDInput No de posibles inputs
    */
    private void verInputs(JPanel bloque, JButton botonEntrada, int noDInput) {
        JPanel inputs = new Inputs(this, bloque, botonEntrada, noDInput);
        bloque.remove(botonEntrada);
        bloque.add(inputs);
        this.setVisible(true);
    }

    /* Metodo que poner el input que se agrega a las casillas vacias en la cuadricula
       @param respuesta Respuesta
       @param bloque 
       @param botonEntrada Boton donde se aloja la entrada/input
    */
    public void setInput(String respuesta, JPanel bloque, JButton botonEntrada) {
        bloque.removeAll();
        botonEntrada.setText(respuesta);
        botonEntrada.setFont(new java.awt.Font("Tahoma", 1, 24));
        bloque.add(botonEntrada);
        this.repaint();
    }

    /* Metodo que permite obtener las respuestas en las casillas vacias en la cuadricula
       @param respuesta Respuesta
       @param bloque 
       @param botonEntrada Boton donde se aloja la entrada/input
    */
    private int[][] getRespuesta() {
        int respuesta[][] = new int[cuadricula][cuadricula];

        for (int i = 0; i < cuadricula; i++) {
            for (int j = 0; j < cuadricula; j++) {
                try {
                    respuesta[i][j] = Integer.parseInt(botones[i][j].getText());
                } catch (NumberFormatException e) {
                    respuesta[i][j] = 0;
                }
            }
        }

        return respuesta;
    }

    /* Metodo que permite revisar la respuesta al Sudoku está completa
    */
    private boolean estaCompleta() {
        boolean estaCompleta = true;
        for (int i = 0; i < cuadricula; i++) {
            for (int j = 0; j < cuadricula; j++) {
                try {
                    Integer.parseInt(botones[i][j].getText());
                } catch (NumberFormatException e) {
                    estaCompleta = false;
                    break;
                }
            }
        }
        return estaCompleta;
    }

    /* Metodo que permite mostar mensaje en la interfaz (UI)
    */
    private void mostrarMensaje(String message) {
        JLabel etiquetaMensaje = new JLabel();
        etiquetaMensaje.setFont(new java.awt.Font("Tahoma", 1, 20));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaMensaje.setText(message);
        holder.removeAll();
        holder.add(etiquetaMensaje);
        holder.repaint();
        this.setVisible(true);
    }

    /* Metodo que permite iniciar el contador del tiempo que uno tarda en resolver el Sudoku
    */
    private void iniciarContador() {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                stopWatch.iniciar();
                while (true) {
                    if (!pausard) {
                        final String timeString = new SimpleDateFormat("mm:ss").format(stopWatch.tiempoTranscu());
                        etiquetaTiempo.setText("" + timeString);
                    }
                }
            }
        });
        thread.start();
    }

 
    private javax.swing.JPanel base;
    private javax.swing.JPanel tablero;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenu juego;
    private javax.swing.JPanel holder;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem nvoJuegoM;
    private javax.swing.JButton nvoJuego;
    private javax.swing.JPanel opciones;
    private javax.swing.JButton pausar;
    private javax.swing.JMenuItem reiniciarJgoM;
    private javax.swing.JButton reiniciarJgo;
    private javax.swing.JButton continuar;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel etiquetaTiempo;
}
