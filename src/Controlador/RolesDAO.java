package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Rol;


public class RolesDAO {

	PreparedStatement ps;
	ResultSet rs;
	Connection cn;

	Conexion acceso = new Conexion();

	public List listarRoles() {
		String query = "select *from roles";

		List<Rol> rolList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Rol rol = new Rol();
				
				rol.setRol_id(rs.getInt(1));
				rol.setNombre(rs.getString(2));
				
				rolList.add(rol);				

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return rolList;

	}
	
	
	
}
