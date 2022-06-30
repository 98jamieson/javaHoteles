package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controlador.RolesDAO;
import Controlador.UsuariosDAO;
import Modelo.Rol;
import Modelo.Usuario;

public class frmUsuarios {

	JTable tabla;
	JScrollPane sp;
	DefaultTableModel dtable = new DefaultTableModel();

	// BOTONES
	JButton crear = new JButton();
	JButton editar = new JButton();
	JButton eliminar = new JButton();
	JButton pdf = new JButton();

	public void botones() {

		// BOTON CREAR
		crear.setText("Crear");
		crear.setBounds(875, 100, 112, 45);

		ActionListener funcion_crear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		};

		crear.addActionListener(funcion_crear);

		// BOTON EDITAR
		editar.setText("Editar");
		editar.setBounds(1000, 100, 112, 45);

		ActionListener funcion_actualizar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int seleccionar = tabla.getSelectedRow();
				if (seleccionar != -1) {

					modificar();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");

				}
			}
		};

		editar.addActionListener(funcion_actualizar);

		// BOTON ELIMINAR
		eliminar.setText("Eliminar");
		eliminar.setBounds(1000, 160, 112, 45);

		ActionListener funcion_eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				eliminar();

			}
		};
		eliminar.addActionListener(funcion_eliminar);

	}

	public void tabla() {

		DefaultTableModel tableModel = new DefaultTableModel();
		String[] fila = new String[10];
		tableModel.addColumn("id");
		tableModel.addColumn("nombre");
		tableModel.addColumn("apellido");
		tableModel.addColumn("telefono");
		tableModel.addColumn("direccion");
		tableModel.addColumn("email");
		tableModel.addColumn("nacimiento");
		tableModel.addColumn("rol");
		tableModel.addColumn("estado");
		tableModel.addColumn("contraseña");

		UsuariosDAO udao = new UsuariosDAO();
		List<Usuario> usuariosList = new ArrayList<>();
		usuariosList = udao.listarUsuarios();
		int y = 0;
		for (int i = 0; i < usuariosList.size(); i++) {
			y++;
		}

		System.out.println("dentro de tabla");

		sp = new JScrollPane(tabla);
		sp.setBounds(10, 10, 800, 600);

	}

	public void tabla1() {
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 10) {
					return true;
				} else {
					return false;
				}

				// TODO Auto-generated method stub
//				return super.isCellEditable(row, column);
			}
		};
		String[] header = new String[10];

		tableModel.addColumn("id");
		tableModel.addColumn("nombre");
		tableModel.addColumn("apellido");
		tableModel.addColumn("telefono");
		tableModel.addColumn("direccion");
		tableModel.addColumn("email");
		tableModel.addColumn("nacimiento");
		tableModel.addColumn("rol");
		tableModel.addColumn("estado");
		tableModel.addColumn("contraseña");

		UsuariosDAO udao = new UsuariosDAO();
		List<Usuario> usuariosList = new ArrayList<>();
		usuariosList = udao.listarUsuarios();

		int y = 0;
		for (int i = 0; i < usuariosList.size(); i++) {
			y++;
		}

		String registros[] = new String[10];

		for (Usuario u : usuariosList) {
			registros[0] = u.getUsuario_id() + "";
			registros[1] = u.getNombre();
			registros[2] = u.getApellido();
			registros[3] = u.getTelefono() + "";
			registros[4] = u.getDireccion() + "";
			registros[5] = u.getEmail();
			registros[6] = u.getNacimiento();
			if (u.getRol_id() == 1) {
				// registros[7] = u.getRol_id() + "";
				registros[7] = "Administrador";
			}
			if (u.getRol_id() == 2) {
				registros[7] = "vendedor";
			}

			if (u.getActivo() == 1) {
				registros[8] = "activo";
			}
			if (u.getActivo() == 0) {
				registros[8] = "no activo";
			}
			// registros[8] = + "";
			registros[9] = u.getContrasenia();

			tableModel.addRow(registros);
		}
		tabla = new JTable();
		tabla.setModel(tableModel);
		sp = new JScrollPane(tabla);
		sp.setBounds(10, 10, 800, 600);

	}

	public void crear() {
		JFrame crear = new JFrame();
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		crear.setTitle("Ingresar");
		crear.add(p1);
		// crear.setLocationRelativeTo(null);
		crear.setBounds(375, 20, 600, 700);
		crear.setVisible(true);

		JLabel l1 = new JLabel();
		l1.setText("Nombre");
		l1.setFont(new Font("arial", Font.PLAIN, 20));
		l1.setBounds(50, 50, 150, 60);
		l1.setVisible(true);

		JLabel l2 = new JLabel();
		l2.setText("Apellido");
		l2.setFont(new Font("arial", Font.PLAIN, 20));
		l2.setBounds(50, 100, 150, 60);
		l2.setVisible(true);

		JLabel l3 = new JLabel();
		l3.setText("Telefono");
		l3.setFont(new Font("arial", Font.PLAIN, 20));
		l3.setBounds(50, 150, 150, 60);
		l3.setVisible(true);

		JLabel l4 = new JLabel();
		l4.setText("Direccion");
		l4.setFont(new Font("arial", Font.PLAIN, 20));
		l4.setBounds(50, 200, 150, 60);
		l4.setVisible(true);

		JLabel l5 = new JLabel();
		l5.setText("email");
		l5.setFont(new Font("arial", Font.PLAIN, 20));
		l5.setBounds(50, 250, 150, 60);
		l5.setVisible(true);

		JLabel l6 = new JLabel();
		l6.setText("nacimiento");
		l6.setFont(new Font("arial", Font.PLAIN, 20));
		l6.setBounds(50, 300, 150, 60);
		l6.setVisible(true);

		JLabel lbf = new JLabel();
		lbf.setText("formato yyyy/mm/dd");
		lbf.setFont(new Font("arial", Font.PLAIN, 10));
		lbf.setBounds(220, 315, 150, 60);
		lbf.setVisible(true);

		JLabel l9 = new JLabel();
		l9.setText("contraseña");
		l9.setFont(new Font("arial", Font.PLAIN, 20));
		l9.setBounds(50, 350, 150, 60);
		l9.setVisible(true);

		JLabel l10 = new JLabel();
		l10.setText("rol");
		l10.setFont(new Font("arial", Font.PLAIN, 20));
		l10.setBounds(50, 410, 150, 60);
		l10.setVisible(true);

		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p1.add(l9);
		p1.add(l10);
		p1.add(lbf);

		JTextField t1 = new JTextField();
		t1.setBounds(170, 65, 200, 25);

		JTextField t2 = new JTextField();
		t2.setBounds(170, 115, 200, 25);

		JTextField t3 = new JTextField();
		t3.setBounds(170, 165, 200, 25);

		JTextField t4 = new JTextField();
		t4.setBounds(170, 215, 200, 25);

		JTextField t5 = new JTextField();
		t5.setBounds(170, 265, 200, 25);

		JTextField t6 = new JTextField();
		t6.setBounds(170, 315, 200, 25);

		JTextField t9 = new JTextField();
		t9.setBounds(170, 365, 200, 25);

		p1.add(t1);
		p1.add(t2);
		p1.add(t3);
		p1.add(t4);
		p1.add(t5);
		p1.add(t6);
		p1.add(t9);

		RolesDAO rdao = new RolesDAO();
		List<Rol> rolList = new ArrayList<>();
		rolList = rdao.listarRoles();
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		JComboBox cb = new JComboBox(modelo);

		for (Rol r : rolList) {
			// cb.addItem(r);
			modelo.addElement(r);
		}

		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ide;
				Rol r1 = (Rol) cb.getSelectedItem();
				ide = r1.getRol_id();
				// JOptionPane.showMessageDialog(null,ide);

			}
		});

		cb.setBounds(170, 425, 200, 25);
		p1.add(cb);

		JButton b1 = new JButton();
		b1.setText("Guardar");
		b1.setBounds(200, 550, 100, 50);
		p1.add(b1);

		ActionListener ingresar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Usuario usu = new Usuario();
					usu.setNombre(t1.getText());
					usu.setApellido(t2.getText());
					usu.setTelefono(Integer.parseInt(t3.getText()));
					usu.setDireccion(t4.getText());
					usu.setEmail(t5.getText());
					usu.setNacimiento(t6.getText());
					usu.setContrasenia(t9.getText());
					int ide;
					Rol r1 = (Rol) cb.getSelectedItem();
					ide = r1.getRol_id();
					usu.setRol_id(ide);
					UsuariosDAO udao = new UsuariosDAO();

					try {
						udao.IngresarUsuario(usu);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// JOptionPane.showMessageDialog(null,"ingresando");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "llene todos los campos");
				}

			}
		};

		b1.addActionListener(ingresar);

	}

	public void modificar() {

		int seleccionar = tabla.getSelectedRow();
		DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
		String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

		Usuario u = new Usuario();
		UsuariosDAO udao = new UsuariosDAO();
		u = udao.buscarUsuario(Integer.parseInt(dato));

		if (seleccionar != -1) {

			JFrame crear = new JFrame();
			JPanel p1 = new JPanel();
			p1.setLayout(null);
			crear.setTitle("Modificar");
			crear.add(p1);
			// crear.setLocationRelativeTo(null);
			crear.setBounds(375, 20, 600, 700);
			crear.setVisible(true);

			JLabel l1 = new JLabel();
			l1.setText("Nombre");
			l1.setFont(new Font("arial", Font.PLAIN, 20));
			l1.setBounds(50, 50, 150, 60);
			l1.setVisible(true);

			JLabel l2 = new JLabel();
			l2.setText("Apellido");
			l2.setFont(new Font("arial", Font.PLAIN, 20));
			l2.setBounds(50, 100, 150, 60);
			l2.setVisible(true);

			JLabel l3 = new JLabel();
			l3.setText("Telefono");
			l3.setFont(new Font("arial", Font.PLAIN, 20));
			l3.setBounds(50, 150, 150, 60);
			l3.setVisible(true);

			JLabel l4 = new JLabel();
			l4.setText("Direccion");
			l4.setFont(new Font("arial", Font.PLAIN, 20));
			l4.setBounds(50, 200, 150, 60);
			l4.setVisible(true);

			JLabel l5 = new JLabel();
			l5.setText("email");
			l5.setFont(new Font("arial", Font.PLAIN, 20));
			l5.setBounds(50, 250, 150, 60);
			l5.setVisible(true);

			JLabel l6 = new JLabel();
			l6.setText("nacimiento");
			l6.setFont(new Font("arial", Font.PLAIN, 20));
			l6.setBounds(50, 300, 150, 60);
			l6.setVisible(true);

			JLabel lbf = new JLabel();
			lbf.setText("formato yyyy/mm/dd");
			lbf.setFont(new Font("arial", Font.PLAIN, 10));
			lbf.setBounds(220, 315, 150, 60);
			lbf.setVisible(true);

			JLabel l9 = new JLabel();
			l9.setText("contraseña");
			l9.setFont(new Font("arial", Font.PLAIN, 20));
			l9.setBounds(50, 350, 150, 60);
			l9.setVisible(true);

			JLabel l10 = new JLabel();
			l10.setText("rol");
			l10.setFont(new Font("arial", Font.PLAIN, 20));
			l10.setBounds(50, 410, 150, 60);
			l10.setVisible(true);

			JLabel lblID = new JLabel();
			lblID.setText(String.valueOf(u.getUsuario_id()));
			lblID.setFont(new Font("arial", Font.PLAIN, 20));
			lblID.setBounds(400, 200, 150, 60);
			lblID.setVisible(true);

			p1.add(l1);
			p1.add(l2);
			p1.add(l3);
			p1.add(l4);
			p1.add(l5);
			p1.add(l6);
			p1.add(l9);
			p1.add(l10);
			//p1.add(lblID);
			p1.add(lbf);

			JTextField t1 = new JTextField();
			t1.setBounds(170, 65, 200, 25);
			t1.setText(u.getNombre());

			JTextField t2 = new JTextField();
			t2.setBounds(170, 115, 200, 25);
			t2.setText(u.getApellido());

			JTextField t3 = new JTextField();
			t3.setBounds(170, 165, 200, 25);
			t3.setText(String.valueOf(u.getTelefono()));

			JTextField t4 = new JTextField();
			t4.setBounds(170, 215, 200, 25);
			t4.setText(u.getDireccion());

			JTextField t5 = new JTextField();
			t5.setBounds(170, 265, 200, 25);
			t5.setText(u.getEmail());

			JTextField t6 = new JTextField();
			t6.setBounds(170, 315, 200, 25);
			t6.setText(u.getNacimiento());

			JTextField t9 = new JTextField();
			t9.setBounds(170, 365, 200, 25);
			t9.setText(u.getContrasenia());

			p1.add(t1);
			p1.add(t2);
			p1.add(t3);
			p1.add(t4);
			p1.add(t5);
			p1.add(t6);
			p1.add(t9);

			RolesDAO rdao = new RolesDAO();
			List<Rol> rolList = new ArrayList<>();
			rolList = rdao.listarRoles();
			DefaultComboBoxModel modelo = new DefaultComboBoxModel();
			JComboBox cb = new JComboBox(modelo);

			for (Rol r : rolList) {
				// cb.addItem(r);
				modelo.addElement(r);
			}

			cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ide;
					Rol r1 = (Rol) cb.getSelectedItem();
					ide = r1.getRol_id();
					// JOptionPane.showMessageDialog(null,ide);

				}
			});

			cb.setBounds(170, 425, 200, 25);
			p1.add(cb);

			JButton b1 = new JButton();
			b1.setText("Guardar");
			b1.setBounds(200, 550, 100, 50);
			p1.add(b1);

			ActionListener ingresar = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						Usuario usu = new Usuario();
						usu.setUsuario_id(Integer.parseInt(lblID.getText()));
						usu.setNombre(t1.getText());
						usu.setApellido(t2.getText());
						usu.setTelefono(Integer.parseInt(t3.getText()));
						usu.setDireccion(t4.getText());
						usu.setEmail(t5.getText());
						usu.setNacimiento(t6.getText());
						usu.setContrasenia(t9.getText());
						int ide;
						Rol r1 = (Rol) cb.getSelectedItem();
						ide = r1.getRol_id();
						usu.setRol_id(ide);
						UsuariosDAO udao = new UsuariosDAO();

						udao.Modificar(usu);

						crear.dispose();
						// cargarTodo();
						/*
						 * ClasePrincipal mc= new ClasePrincipal(); mc.ventanas();
						 */

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "llene todos los campos");
					}

				}
			};

			b1.addActionListener(ingresar);

		}
	}

	public void eliminar() {

		try {
			int seleccionar = tabla.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
			String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

			Usuario u = new Usuario();
			UsuariosDAO udao = new UsuariosDAO();
			u = udao.buscarUsuario(Integer.parseInt(dato));

			udao.Eliminar(u);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Seleccione un Item");

		}

	}

	public void cargarTodo() {
		botones();
		tabla1();
	}

}
