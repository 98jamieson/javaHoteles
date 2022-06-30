package Vista;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Conexion.Conexion;

public class ClasePrincipal {

	JFrame principal = new JFrame();
	JTabbedPane pestañas = new JTabbedPane();
	JPanel usuarios = new JPanel();

	public static void main(String[] args) throws ClassNotFoundException {

		Conexion cn = new Conexion();
		Connection con = null;

		con = cn.conectar();

		ClasePrincipal mc = new ClasePrincipal();
		mc.Login();
	}

	public void mainFrame() {
		usuarios.setLayout(null);

		frmUsuarios fu = new frmUsuarios();

		principal.setTitle("Modulo Administrador");
		principal.setLocationRelativeTo(null);
		principal.setBounds(90, 20, 1200, 700);
		principal.setVisible(true);
		principal.setResizable(false);

		principal.add(usuarios);
		pestañas.addTab("Usuarios", usuarios);

		principal.add(pestañas);

		fu.cargarTodo();
		usuarios.add(fu.editar);
		usuarios.add(fu.eliminar);
		usuarios.add(fu.crear);
		usuarios.add(fu.sp);

	}

	public void Login() {
		frmLogin fl = new frmLogin();
		fl.cargarTodo();

	}

}
