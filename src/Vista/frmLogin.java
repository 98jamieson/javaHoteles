package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controlador.UsuariosDAO;
import Modelo.Usuario;

public class frmLogin {

	JButton acceder = new JButton();
	JButton cancelar = new JButton();

	JFrame login = new JFrame();
	JPanel loginpanel = new JPanel();

	JTextField txtUsuario = new JTextField();
	JTextField txtContrasenia = new JTextField();

	public void frame() {

		login.setTitle("Login");
		loginpanel.setLayout(null);
		login.add(loginpanel);
		// login.setLocationRelativeTo(null);
		login.setBounds(450, 80, 400, 450);
		login.setVisible(true);
		// login.setResizable(false);

		JLabel l1 = new JLabel();
		l1.setText("usuario");
		l1.setFont(new Font("arial", Font.PLAIN, 20));
		l1.setBounds(158, 110, 150, 60);
		l1.setVisible(true);

		JLabel l2 = new JLabel();
		l2.setText("password");
		l2.setFont(new Font("arial", Font.PLAIN, 20));
		l2.setBounds(150, 190, 150, 60);
		l2.setVisible(true);

		loginpanel.add(l1);
		loginpanel.add(l2);

		txtUsuario.setBounds(115, 155, 160, 25);
		txtContrasenia.setBounds(115, 240, 160, 25);

		txtUsuario.setVisible(true);
		txtContrasenia.setVisible(true);
		loginpanel.add(txtUsuario);
		loginpanel.add(txtContrasenia);

	}

	public void botones() {

		loginpanel.add(acceder);

		acceder.setText("acceder");
		acceder.setBounds(145, 280, 100, 45);
		acceder.setVisible(true);

		ActionListener funcion_crear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loggear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		acceder.addActionListener(funcion_crear);

	}
	
	
	public void loggear() throws SQLException {
		
		
		Usuario us= new Usuario();
		
		us.setEmail(txtUsuario.getText());
		us.setContrasenia(txtContrasenia.getText());
		
		UsuariosDAO udao= new UsuariosDAO();
	
		udao.loggear(us);
		
	}

	public void cargarTodo() {
		frame();
		botones();
	}
}
