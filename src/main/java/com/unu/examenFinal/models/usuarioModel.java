package com.unu.examenFinal.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.examenFinal.beans.rol;
import com.unu.examenFinal.beans.usuario;

public class usuarioModel extends Conexion {
	CallableStatement cs;
	ResultSet rs;

	public List<usuario> listarUsuarios() {
		try {
			List<usuario> lista = new ArrayList<>();
			String sql = "Call sp_mostrarusuarios()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				usuario user = new usuario();
				user.setId(rs.getInt("idusuario"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password1"));
				lista.add(user);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}

	public List<usuario> listarUsuarios2() {
		try {
			List<usuario> lista = new ArrayList<>();
			String sql = "Call sp_mostrarusuarios2()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				usuario user = new usuario();
				user.setId(rs.getInt("idusuario"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password1"));
				user.setNamerol(rs.getString("namerol"));
				lista.add(user);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}

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
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
			this.cerrarConexion();
			return null;
		}

	}

	public int insertarUsuario(usuario user) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarusuarios(?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setInt(3, user.getIdrol());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			this.cerrarConexion();
			return 0;
		}

	}

	public usuario obtenerUsuario(int id) {
		usuario user = new usuario();
		try {
			String sql = "CALL sp_obtenerusuarios2(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, id);
			rs = cs.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("idusuario"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password1"));
				user.setNamerol(rs.getString("namerol"));
				user.setIdrol(Integer.parseInt(rs.getString("idrol")));
				this.cerrarConexion();
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.cerrarConexion();
			return null;
		}
		return user;
	}

	public int modificarUsuario(usuario user) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarusuarios(?,?,?,?)";
			this.abrirConexion();

			// Imprimir los datos enviados para depuración
			System.out.println("Datos enviados: id=" + user.getId() + ", username=" + user.getUsername() + ", password="
					+ user.getPassword() + ", idrol=" + user.getNamerol());
			
			cs = conexion.prepareCall(sql);
			cs.setInt(1, user.getId());
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.setString(4,user.getNamerol());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			e.printStackTrace();
			this.cerrarConexion();
			return 0;
		}

	}

	public int eliminarUsuario(int id) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarusuarios(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, id);
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			this.cerrarConexion();
			return 0;
		}

	}

	public List<usuario> buscarUsuario(String query) {
		try {
			List<usuario> lista = new ArrayList<>();
			String sql = "Call sp_buscarusuarios2(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, "%" + query + "%");
			rs = cs.executeQuery();
			while (rs.next()) {
				usuario user = new usuario();
				user.setId(rs.getInt("idusuario"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password1"));
				user.setNamerol(rs.getString("namerol"));
				lista.add(user);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}

}
