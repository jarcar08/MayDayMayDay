package com.unu.examenFinal.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.examenFinal.beans.rol;

public class rolModel extends Conexion {
	CallableStatement cs;
	ResultSet rs;

	public List<rol> listarRoles() {
		try {
			List<rol> lista = new ArrayList<>();
			String sql = "Call examen.sp_listarRoles()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				rol role = new rol();
				role.setIdrol(rs.getInt("idrol"));
				role.setNamerol(rs.getString("namerol"));
				lista.add(role);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}

	public int insertarRol(rol role) throws SQLException {
		try {
			int filasAfectadas = 0;

			String sql = "CALL sp_insertarRol(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, role.getNamerol());

			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.cerrarConexion();
			return 0;
		}
	}

	public int eliminarRol(int idrol) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarRol(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idrol);
			filasAfectadas = cs.executeUpdate();
			System.out.println("Filas afectadas: " + filasAfectadas);
			this.cerrarConexion();
			return filasAfectadas;

		} catch (SQLException e) {
			e.printStackTrace();
			this.cerrarConexion();
			return 0;
		}
	}

	public rol obtenerRol(int idrol) {
		rol role = new rol();
		try {
			String sql = "CALL sp_obtenerRol(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idrol);
			rs = cs.executeQuery();
			if (rs.next()) {
				role.setIdrol(rs.getInt("idrol"));
				role.setNamerol(rs.getString("namerol"));
				this.cerrarConexion();
			}

		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return role;
	}

	public int modificarRol(rol role) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_editarRol(?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, role.getIdrol());
			cs.setString(2, role.getNamerol());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;

		} catch (SQLException e) {
			e.printStackTrace();
			this.cerrarConexion();
			return 0;
		}
	}

}
