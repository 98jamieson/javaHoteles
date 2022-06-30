package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.Usuario;
import Vista.ClasePrincipal;

public class UsuariosDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection cn;

	Conexion acceso = new Conexion();

	public List listarUsuarios() {
		String query = "select *from usuarios";

		List<Usuario> usuariosList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario us = new Usuario();

				us.setUsuario_id(rs.getInt(1));
				us.setNombre(rs.getString(2));
				us.setApellido(rs.getString(3));
				us.setTelefono(rs.getInt(4));
				us.setDireccion(rs.getString(5));
				us.setEmail(rs.getString(6));
				us.setNacimiento(rs.getString(7));
				us.setRol_id(rs.getInt(8));
				us.setActivo(rs.getInt(9));
				us.setContrasenia(rs.getString(10));
				usuariosList.add(us);

			}
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return usuariosList;

	}

	public void IngresarUsuario(Usuario us) throws SQLException {

		// JOptionPane.showMessageDialog(null,us.getNombre());
		String sql;
		sql = "INSERT INTO usuarios(nombre,apellido,telefono,direccion,email,nacimiento,rol_id,activo,contrasenia)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		cn = acceso.conectar();
		PreparedStatement preparedStmt = cn.prepareStatement(sql);
		preparedStmt.setString(1, us.getNombre());
		preparedStmt.setString(2, us.getApellido());
		preparedStmt.setInt(3, us.getTelefono());
		preparedStmt.setString(4, us.getDireccion());
		preparedStmt.setString(5, us.getEmail());
		preparedStmt.setString(6, us.getNacimiento());
		preparedStmt.setInt(7, us.getRol_id());
		preparedStmt.setInt(8, 1);
		preparedStmt.setString(9, us.getContrasenia());

		preparedStmt.execute();
		cn.close();

	}

	public Usuario buscarUsuario(int u) {
		String query = "select *from usuarios where usuario_id=" + u + ";";
		Usuario us = new Usuario();

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				us.setUsuario_id(rs.getInt(1));
				us.setNombre(rs.getString(2));
				us.setApellido(rs.getString(3));
				us.setTelefono(rs.getInt(4));
				us.setDireccion(rs.getString(5));
				us.setEmail(rs.getString(6));
				us.setNacimiento(rs.getString(7));
				us.setRol_id(rs.getInt(8));
				us.setActivo(rs.getInt(9));
				us.setContrasenia(rs.getString(10));

			}
			System.out.println(us.getApellido());

			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return us;

	}

	public void Modificar(Usuario us) {
		String sql;
		sql = "UPDATE usuarios SET nombre=?, apellido=?,telefono=?,direccion=?,email=?,nacimiento=?,rol_id=?,activo=?,contrasenia=? WHERE usuario_id=?";

		try {
			cn = acceso.conectar();
			// PreparedStatement preparedStmt = cn.prepareStatement(sql);
			ps = cn.prepareStatement(sql);
			ps.setString(1, us.getNombre());
			ps.setString(2, us.getApellido());
			ps.setInt(3, us.getTelefono());
			ps.setString(4, us.getDireccion());
			ps.setString(5, us.getEmail());
			ps.setString(6, us.getNacimiento());
			ps.setInt(7, us.getRol_id());
			ps.setInt(8, 1);
			ps.setString(9, us.getContrasenia());
			ps.setInt(10, us.getUsuario_id());
			if (ps.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null,"Actualizado");
			}

			cn.close();

		} catch (Exception ex) {

		}

	}

	public void Eliminar(Usuario us) {
		String sql;
		sql = "DELETE FROM usuarios WHERE usuario_id=?";

		try {

			cn = acceso.conectar();

			ps = cn.prepareStatement(sql);
			ps.setInt(1, us.getUsuario_id());

			ps.executeUpdate();

		} catch (Exception e) {

		}

	}

	public Usuario loggear(Usuario u) throws SQLException {

		String query = "SELECT *FROM usuarios WHERE email=? and contrasenia=?";
		Usuario us = new Usuario();

		try {

			cn = acceso.conectar();
			PreparedStatement st = cn.prepareStatement(query);
			st.setString(1, u.getEmail());
			st.setNString(2, u.getContrasenia());
			ResultSet rs = st.executeQuery();
			
			
			 if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Accediendo...");
					ClasePrincipal cp = new ClasePrincipal();
					cp.mainFrame();

			 }else {
				 JOptionPane.showMessageDialog(null, "Usuario no valido!");
			 }
			 
			 
			while (rs.next()) {
				us.setUsuario_id(rs.getInt(1));
				us.setNombre(rs.getString(2));
				us.setApellido(rs.getString(3));
				us.setTelefono(rs.getInt(4));
				us.setDireccion(rs.getString(5));
				us.setEmail(rs.getString(6));
				us.setNacimiento(rs.getString(7));
				us.setRol_id(rs.getInt(8));
				us.setActivo(rs.getInt(9));
				us.setContrasenia(rs.getString(10));

			}

			
			
		
		} catch (Exception ex) {

		}

		cn.close();
		return us;

	}

}
