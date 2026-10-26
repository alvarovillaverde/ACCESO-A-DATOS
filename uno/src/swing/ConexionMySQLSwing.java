package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ConexionMySQLSwing extends JFrame {

    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtServidor;
    private JTextField txtPuerto;
    private JComboBox<String> cbBasesDatos;
    private JButton btnCargarBD;
    private JButton btnConectar;

    public ConexionMySQLSwing() {
        setTitle("Práctica Conexión a MySQL desde Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 320);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Usuario
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        txtUsuario = new JTextField("root", 15);
        add(txtUsuario, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtContrasena = new JPasswordField(15);
        add(txtContrasena, gbc);

        // Servidor
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Servidor:"), gbc);
        gbc.gridx = 1;
        txtServidor = new JTextField("127.0.0.1", 15);
        add(txtServidor, gbc);

        // Puerto
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Puerto:"), gbc);
        gbc.gridx = 1;
        txtPuerto = new JTextField("3306", 15);
        add(txtPuerto, gbc);

        // Base de Datos (JComboBox + Botón Cargar)
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Base de datos:"), gbc);
        
        JPanel panelBD = new JPanel(new BorderLayout(5, 0));
        cbBasesDatos = new JComboBox<>();
        cbBasesDatos.addItem("sakila"); // Opción por defecto
        btnCargarBD = new JButton("Cargar BDs");
        panelBD.add(cbBasesDatos, BorderLayout.CENTER);
        panelBD.add(btnCargarBD, BorderLayout.EAST);
        
        gbc.gridx = 1;
        add(panelBD, gbc);

        // Botón CONECTAR
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnConectar = new JButton("CONECTAR");
        add(btnConectar, gbc);

        // --- LISTENERS Y EVENTOS ---

        // Listener al pasar el ratón por encima del botón CONECTAR (Requisito 2a)
        btnConectar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                validarCamposPrevios();
            }
        });

        // Evento al pulsar el botón CONECTAR
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCamposPrevios() && validarPuerto()) {
                    realizarConexionYConsulta();
                }
            }
        });

        // Evento para obtener las BDs del servidor (Requisito 4)
        btnCargarBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarBasesDeDatos();
            }
        });
    }

    /**
     * Valida que los campos usuario, servidor y puerto no estén vacíos. (Requisito 2a)
     */
    private boolean validarCamposPrevios() {
        String usuario = txtUsuario.getText().trim();
        String servidor = txtServidor.getText().trim();
        String puerto = txtPuerto.getText().trim();

        if (usuario.isEmpty() || servidor.isEmpty() || puerto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Es necesario cumplimentar los campos Usuario, Servidor y Puerto.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Valida que el puerto sea numérico y esté entre 0 y 45003. (Requisito 2b)
     */
    private boolean validarPuerto() {
        try {
            int puerto = Integer.parseInt(txtPuerto.getText().trim());
            if (puerto < 0 || puerto > 45003) {
                JOptionPane.showMessageDialog(this,
                        "El puerto debe ser un valor numérico entre 0 y 45003.",
                        "Error en el puerto", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El puerto debe ser un número entero válido.",
                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Obtiene y lista las bases de datos disponibles en el servidor (Requisito 4)
     */
    private void cargarBasesDeDatos() {
        if (!validarCamposPrevios() || !validarPuerto()) return;

        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String servidor = txtServidor.getText().trim();
        String puerto = txtPuerto.getText().trim();

        String url = "jdbc:mysql://" + servidor + ":" + puerto + "/";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW DATABASES")) {

            cbBasesDatos.removeAllItems();
            while (rs.next()) {
                cbBasesDatos.addItem(rs.getString(1));
            }
            JOptionPane.showMessageDialog(this, "Bases de datos cargadas correctamente.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Imposible conectar para obtener las Bases de Datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Conecta a la BD seleccionada y ejecuta una sentencia SELECT por consola. (Requisitos 1 y 3)
     */
    private void realizarConexionYConsulta() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String servidor = txtServidor.getText().trim();
        String puerto = txtPuerto.getText().trim();
        String bd = (String) cbBasesDatos.getSelectedItem();

        if (bd == null || bd.trim().isEmpty()) {
            bd = "sakila"; // Base de datos por defecto si no hay selección
        }

        String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + bd;

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            
            // Diálogo de confirmación (Requisito 1)
            JOptionPane.showMessageDialog(this,
                    "Conexión realizada con éxito.",
                    "Conectado", JOptionPane.INFORMATION_MESSAGE);

            // Ejecución de la sentencia SELECT y muestra por consola (Requisito 3)
            System.out.println("=== CONSULTA SELECT EJECUTADA EN BASE DE DATOS: " + bd + " ===");
            try (Statement stmt = conn.createStatement()) {
                
                // Intenta realizar un SELECT de prueba (se adapta según la tabla existente)
                String sql = "SELECT * FROM actor LIMIT 5"; 
                System.out.println("SQL: " + sql);
                System.out.println("---------------------------------------------------------");

                try (ResultSet rs = stmt.executeQuery(sql)) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numColumnas = rsmd.getColumnCount();

                    // Imprimir cabecera
                    for (int i = 1; i <= numColumnas; i++) {
                        System.out.print(rsmd.getColumnName(i) + "\t");
                    }
                    System.out.println();

                    // Imprimir datos
                    while (rs.next()) {
                        for (int i = 1; i <= numColumnas; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                    System.out.println("---------------------------------------------------------");
                }
            } catch (SQLException e) {
                System.out.println("Conexión exitosa, pero no se pudo ejecutar la consulta SELECT de prueba:");
                System.out.println(e.getMessage());
            }

        } catch (SQLException ex) {
            // Diálogo de error (Requisito 1)
            JOptionPane.showMessageDialog(this,
                    "Imposible conectar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error de conexión: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConexionMySQLSwing().setVisible(true);
        });
    }
}