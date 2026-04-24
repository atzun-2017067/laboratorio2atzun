/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas.laboratorio2atzun;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antony
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());
    private String rol;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal(String rol) {
        initComponents();
        
        this.rol = rol;
        
        if ("Administrador".equalsIgnoreCase(rol)) {
            btnMantenimientoUsuario.setVisible(true);
        } else {
            btnMantenimientoUsuario.setVisible(false);
        }
        
        
    }
    
    public void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) tblUsuario.getModel();
        model.setRowCount(0); // limpiar tabla

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); // separar por coma
                model.addRow(datos);
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage());
        }
    }

    private void abrirFormularioEdicion(String usuario, String password, String rol) {

        txtUsuario.setText(usuario);
        txtPassword.setText(password);
        txtConfirmPassword.setText(password);

        cmbRol.setSelectedItem(rol);

        dlCreaUsuario.pack();                         // ajusta a contenido
        dlCreaUsuario.setLocationRelativeTo(this);
        dlCreaUsuario.setVisible(true);
    }
    
    private void abrirFormularioCreacion() {

        txtUsuario.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");

        cmbRol.setSelectedItem("Usuario");

        dlCreaUsuario.pack();                         // ajusta a contenido
        dlCreaUsuario.setLocationRelativeTo(this);
        dlCreaUsuario.setVisible(true);
    }
    
    private boolean esEdicion = false;
    private int filaEditando = -1;
    private int filaSeleccionada = -1;
    
    private void guardarUsuario(String usuario, String pass, String rol, String estado, String sesion) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", true));
        bw.write(usuario + "," + pass + "," + rol + "," + estado + "," + sesion);
        bw.newLine();
        bw.close();
    }
    
    private void actualizarUsuario(String usuario, String pass, String rol, String estado, String sesion) throws Exception {

        ArrayList<String> lineas = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
        String linea;

        while ((linea = br.readLine()) != null) {
            lineas.add(linea);
        }
        br.close();

        // reemplazar línea
        lineas.set(filaEditando, usuario + "," + pass + "," + rol + "," + estado + "," + sesion);

        BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"));
        for (String l : lineas) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }
    
    private String obtenerSesion(String usuarioBuscado) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length == 5) {

                    String userFile = datos[0].trim();

                    if (userFile.equals(usuarioBuscado)) {
                        br.close();
                        return datos[4].trim(); // sesion
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error leyendo sesión: " + e.getMessage());
        }

        return "1"; // default por seguridad
    }
    
    private boolean actualizarPassword(String usuarioBuscado, String nuevaPass) {

        ArrayList<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length == 5) {

                    String user = datos[0].trim();

                    if (user.equals(usuarioBuscado)) {

                        // ✔ mantener todo excepto la contraseña
                        String rol = datos[2].trim();
                        String estado = datos[3].trim();
                        String sesion = "0"; // 👈 importante: ya cambió contraseña

                        lineas.add(usuarioBuscado + "," + nuevaPass + "," + rol + "," + estado + "," + sesion);

                        encontrado = true;

                    } else {
                        lineas.add(linea);
                    }
                }
            }

            br.close();

            // ✔ reescribir archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"));
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return encontrado;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlMantenimientoUsuario = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        dlActualizaPassword = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuario1 = new javax.swing.JTextField();
        txtConfirmPassword1 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        txtPassword1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        btnActualizaPass = new javax.swing.JButton();
        btnCancelaPass = new javax.swing.JButton();
        dlCreaUsuario = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnCreaUsuario = new javax.swing.JButton();
        btnCancelaUsuario = new javax.swing.JButton();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        btnMantenimientoUsuario = new javax.swing.JMenu();
        btnReinicioPassword = new javax.swing.JMenu();
        btnCerrarSesion = new javax.swing.JMenu();

        jLabel1.setText("Mantenimiento de Usuarios");

        btnCrear.setBackground(new java.awt.Color(204, 255, 255));
        btnCrear.setText("Crear");
        btnCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearMouseClicked(evt);
            }
        });
        btnCrear.addActionListener(this::btnCrearActionPerformed);

        btnEditar.setBackground(new java.awt.Color(255, 255, 204));
        btnEditar.setText("Editar");
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 153, 153));
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        tblUsuario.setBackground(new java.awt.Color(204, 204, 204));
        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Contraseña", "Rol", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuario);

        javax.swing.GroupLayout dlMantenimientoUsuarioLayout = new javax.swing.GroupLayout(dlMantenimientoUsuario.getContentPane());
        dlMantenimientoUsuario.getContentPane().setLayout(dlMantenimientoUsuarioLayout);
        dlMantenimientoUsuarioLayout.setHorizontalGroup(
            dlMantenimientoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlMantenimientoUsuarioLayout.createSequentialGroup()
                .addGroup(dlMantenimientoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlMantenimientoUsuarioLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(dlMantenimientoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dlMantenimientoUsuarioLayout.createSequentialGroup()
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(151, 151, 151)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dlMantenimientoUsuarioLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        dlMantenimientoUsuarioLayout.setVerticalGroup(
            dlMantenimientoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlMantenimientoUsuarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(dlMantenimientoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel3.setText("Cambio de Contraseña");

        jLabel8.setText("Contraseña");

        txtUsuario1.setEditable(false);
        txtUsuario1.setBackground(new java.awt.Color(210, 249, 255));
        txtUsuario1.addActionListener(this::txtUsuario1ActionPerformed);

        txtConfirmPassword1.setBackground(new java.awt.Color(210, 249, 255));

        jLabel9.setText("Confirmar contraseña");

        txtPassword1.setBackground(new java.awt.Color(210, 249, 255));
        txtPassword1.addActionListener(this::txtPassword1ActionPerformed);

        jLabel10.setText("Usuario");

        btnActualizaPass.setBackground(new java.awt.Color(204, 255, 204));
        btnActualizaPass.setText("Actualizar");
        btnActualizaPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaPassMouseClicked(evt);
            }
        });
        btnActualizaPass.addActionListener(this::btnActualizaPassActionPerformed);

        btnCancelaPass.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelaPass.setText("Cancelar");
        btnCancelaPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelaPassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dlActualizaPasswordLayout = new javax.swing.GroupLayout(dlActualizaPassword.getContentPane());
        dlActualizaPassword.getContentPane().setLayout(dlActualizaPasswordLayout);
        dlActualizaPasswordLayout.setHorizontalGroup(
            dlActualizaPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlActualizaPasswordLayout.createSequentialGroup()
                .addGroup(dlActualizaPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlActualizaPasswordLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(dlActualizaPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario1)
                            .addComponent(jLabel8)
                            .addComponent(txtPassword1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(txtConfirmPassword1)))
                    .addGroup(dlActualizaPasswordLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(btnActualizaPass, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelaPass, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        dlActualizaPasswordLayout.setVerticalGroup(
            dlActualizaPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlActualizaPasswordLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(dlActualizaPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizaPass)
                    .addComponent(btnCancelaPass))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        dlCreaUsuario.setPreferredSize(new java.awt.Dimension(550, 450));

        jLabel4.setText("Usuario");

        jLabel5.setText("Contraseña");

        txtUsuario.setBackground(new java.awt.Color(210, 249, 255));
        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        txtConfirmPassword.setBackground(new java.awt.Color(210, 249, 255));

        jLabel6.setText("Confirmar contraseña");

        txtPassword.setBackground(new java.awt.Color(210, 249, 255));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnCreaUsuario.setBackground(new java.awt.Color(204, 255, 204));
        btnCreaUsuario.setText("Crear");
        btnCreaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreaUsuarioMouseClicked(evt);
            }
        });

        btnCancelaUsuario.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelaUsuario.setText("Cancelar");
        btnCancelaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelaUsuarioMouseClicked(evt);
            }
        });

        cmbRol.setBackground(new java.awt.Color(146, 163, 248));
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Administrador" }));
        cmbRol.addActionListener(this::cmbRolActionPerformed);

        jLabel7.setText("Rol");

        javax.swing.GroupLayout dlCreaUsuarioLayout = new javax.swing.GroupLayout(dlCreaUsuario.getContentPane());
        dlCreaUsuario.getContentPane().setLayout(dlCreaUsuarioLayout);
        dlCreaUsuarioLayout.setHorizontalGroup(
            dlCreaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCreaUsuarioLayout.createSequentialGroup()
                .addGroup(dlCreaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlCreaUsuarioLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnCreaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancelaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dlCreaUsuarioLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(dlCreaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario)
                            .addComponent(jLabel5)
                            .addComponent(txtPassword)
                            .addComponent(jLabel6)
                            .addComponent(txtConfirmPassword)
                            .addComponent(cmbRol, 0, 160, Short.MAX_VALUE)
                            .addComponent(jLabel7))))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        dlCreaUsuarioLayout.setVerticalGroup(
            dlCreaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCreaUsuarioLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(dlCreaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreaUsuario)
                    .addComponent(btnCancelaUsuario))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 3, 18)); // NOI18N
        jLabel2.setText("Gestiones");

        btnMantenimientoUsuario.setText("Mantenimiento de usuario");
        btnMantenimientoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMantenimientoUsuarioMouseClicked(evt);
            }
        });
        jMenuBar2.add(btnMantenimientoUsuario);
        btnMantenimientoUsuario.getAccessibleContext().setAccessibleDescription("");

        btnReinicioPassword.setText("Reinicio de contraseña");
        btnReinicioPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReinicioPasswordMouseClicked(evt);
            }
        });
        jMenuBar2.add(btnReinicioPassword);

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });
        jMenuBar2.add(btnCerrarSesion);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel2)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMantenimientoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuarioMouseClicked
        // TODO add your handling code here:
        cargarTabla();
        dlMantenimientoUsuario.pack();                         // ajusta a contenido
        dlMantenimientoUsuario.setLocationRelativeTo(this);
        dlMantenimientoUsuario.setVisible(true);
    }//GEN-LAST:event_btnMantenimientoUsuarioMouseClicked

    private void btnReinicioPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReinicioPasswordMouseClicked
        // TODO add your handling code here:
        dlActualizaPassword.pack();                         // ajusta a contenido
        dlActualizaPassword.setLocationRelativeTo(this);
        dlActualizaPassword.setVisible(true);
        
    }//GEN-LAST:event_btnReinicioPasswordMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseClicked
        // TODO add your handling code here:
        abrirFormularioCreacion();
        dlCreaUsuario.pack();                         // ajusta a contenido
        dlCreaUsuario.setLocationRelativeTo(this);
        dlCreaUsuario.setVisible(true);
        dlMantenimientoUsuario.setVisible(false);
        dlCreaUsuario.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
    }//GEN-LAST:event_btnCrearMouseClicked

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRolActionPerformed

    private void btnCreaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreaUsuarioMouseClicked
        // TODO add your handling code here:
        try {
            btnCreaUsuario.setText("Crear");
            String usuario = txtUsuario.getText();
            String pass = new String(txtPassword.getPassword());
            String confirmPass = new String(txtConfirmPassword.getPassword());

            // ✔ Validaciones (las que ya hiciste)
            if (usuario.isEmpty() || usuario.isBlank()|| pass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar los campos");
                return;
            }

            if (!pass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                return;
            }

            if (pass.length() < 13 || 
                !pass.matches(".*[A-Z].*") || 
                !pass.matches(".*[^a-zA-Z0-9].*")) {

                JOptionPane.showMessageDialog(null,
                    "Debe tener mínimo 13 caracteres, 1 mayúscula y 1 símbolo");
                return;
            }
            
            String rol = cmbRol.getSelectedItem().toString();
            String estado = "Activo";
            String sesion = "1";

            if(esEdicion){
                sesion = obtenerSesion(usuario);
                actualizarUsuario(usuario, pass, rol, estado, sesion);
            } else {
                
                sesion = "1";
                guardarUsuario(usuario, pass, rol, estado, sesion);
            }

            cargarTabla();

            esEdicion = false;
            filaEditando = -1;
            
            dlCreaUsuario.setVisible(false);
            dlMantenimientoUsuario.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(dlCreaUsuario, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCreaUsuarioMouseClicked

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // TODO add your handling code here:
        filaSeleccionada = tblUsuario.getSelectedRow();
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        // TODO add your handling code here:
        btnCreaUsuario.setText("Actualizar");
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(dlMantenimientoUsuario, "Seleccione una fila");
            return;
        }

        String usuario = tblUsuario.getValueAt(filaSeleccionada, 0).toString();
        String password = tblUsuario.getValueAt(filaSeleccionada, 1).toString();
        String rol = tblUsuario.getValueAt(filaSeleccionada, 2).toString();

        // guardar estado para update
        esEdicion = true;
        filaEditando = filaSeleccionada;

        abrirFormularioEdicion(usuario, password, rol);
    }//GEN-LAST:event_btnEditarMouseClicked

    private void txtUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuario1ActionPerformed

    private void txtPassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassword1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassword1ActionPerformed

    private void btnActualizaPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaPassMouseClicked
        // TODO add your handling code here:
        String usuario = txtUsuario1.getText();
        String pass = new String(txtPassword1.getPassword());
        String confirmPass = new String(txtConfirmPassword1.getPassword());
        
        // ✔ Validaciones (las que ya hiciste)
        if (pass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar los campos");
            return;
        }

        if (!pass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return;
        }

        if (pass.length() < 13 || 
            !pass.matches(".*[A-Z].*") || 
            !pass.matches(".*[^a-zA-Z0-9].*")) {

            JOptionPane.showMessageDialog(null,
                "Debe tener mínimo 13 caracteres, 1 mayúscula y 1 símbolo");
            return;
        }

        // 🔥 actualizar solo password
        boolean actualizado = actualizarPassword(usuario, pass);

        if (actualizado) {
            JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
            dlActualizaPassword.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
        
        
    }//GEN-LAST:event_btnActualizaPassMouseClicked

    private void btnActualizaPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizaPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizaPassActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        // TODO add your handling code here:
        
        Login login = new Login();
        login.setVisible(true);

        
        this.dispose();
        
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnCancelaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelaUsuarioMouseClicked
        // TODO add your handling code here:
        dlMantenimientoUsuario.pack();                         // ajusta a contenido
        dlMantenimientoUsuario.setLocationRelativeTo(this);
        dlMantenimientoUsuario.setVisible(true);
        dlCreaUsuario.setVisible(false);
    }//GEN-LAST:event_btnCancelaUsuarioMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(dlMantenimientoUsuario, "Seleccione una fila");
            return;
        }
        
        String usuario = tblUsuario.getValueAt(filaSeleccionada, 0).toString();

        int opcion = JOptionPane.showConfirmDialog(
            dlMantenimientoUsuario,
            "¿Desea inactivar este usuario?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            ArrayList<String> lineas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length == 5) {

                    String userFile = datos[0].trim();

                    if (userFile.equals(usuario)) {

                        // mantener todo, solo cambiar estado
                        String pass = datos[1].trim();
                        String rol = datos[2].trim();
                        String sesion = datos[4].trim();

                        lineas.add(userFile + "," + pass + "," + rol + ",Inactivo," + sesion);

                    } else {
                        lineas.add(linea);
                    }
                }
            }

            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"));
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

            JOptionPane.showMessageDialog(dlMantenimientoUsuario, "Usuario inactivado correctamente");

            cargarTabla(); // refrescar tabla

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnCancelaPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelaPassMouseClicked
        // TODO add your handling code here:
        
        dlActualizaPassword.setVisible(false);
        
    }//GEN-LAST:event_btnCancelaPassMouseClicked
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizaPass;
    public javax.swing.JButton btnCancelaPass;
    public javax.swing.JButton btnCancelaUsuario;
    private javax.swing.JMenu btnCerrarSesion;
    private javax.swing.JButton btnCreaUsuario;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JMenu btnMantenimientoUsuario;
    private javax.swing.JMenu btnReinicioPassword;
    private javax.swing.JComboBox<String> cmbRol;
    public javax.swing.JDialog dlActualizaPassword;
    private javax.swing.JDialog dlCreaUsuario;
    private javax.swing.JDialog dlMantenimientoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtConfirmPassword1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPassword1;
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JTextField txtUsuario1;
    // End of variables declaration//GEN-END:variables
}
