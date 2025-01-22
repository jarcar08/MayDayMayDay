package com.unu.examenFinal.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.unu.examenFinal.models.rolModel;
import com.unu.examenFinal.beans.rol;
import com.unu.examenFinal.beans.usuario;

/**
 * Servlet implementation class usuarioController
 */
public class rolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	rolModel modelo = new rolModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public rolController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if (request.getParameter("op") == null) {
			listar(request, response);
			return;
		}
		String operacion = request.getParameter("op");
		switch (operacion) {
		case "listar":
			listar(request, response);
			break;
		case "nuevo":
			request.getRequestDispatcher("/rol/nuevoRol.jsp").forward(request, response);
			break;
		case "insertar":
			insertar(request, response);
			break;
		case "eliminar":
			eliminar(request, response);
			break;
		case "obtener":
			obtener(request, response);
			break;
		case "modificar":
			modificar(request, response);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ListaRoles", modelo.listarRoles());
		request.getRequestDispatcher("/rol/ListaRoles.jsp").forward(request, response);
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			rol role = new rol();
			role.setNamerol(request.getParameter("namerol")); // Asegúrate de que coincida con el formulario

			if (modelo.insertarRol(role) > 0) {
				request.getSession().setAttribute("exito", "rol registrada exitosamente");
			} else {
				request.getSession().setAttribute("fracaso", "No se pudo registrar el rol");
			}
			response.sendRedirect(request.getContextPath() + "/rolController?op=listar");
		} catch (SQLException ex) {
			Logger.getLogger(rolController.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServletException("Error al insertar rol", ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int idrol = Integer.parseInt(request.getParameter("id"));

			if (modelo.eliminarRol(idrol) > 0) {
				request.getSession().setAttribute("exito", "Rol eliminada exitosamente");
			} else {
				request.getSession().setAttribute("fracaso", "No se pudo eliminar el rol");
			}
			response.sendRedirect(request.getContextPath() + "/rolController?op=listar");
		} catch (NumberFormatException | SQLException ex) {
			Logger.getLogger(rolController.class.getName()).log(Level.SEVERE, null, ex);
			throw new IOException("Error al eliminar rol", ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {

			String id = request.getParameter("id");
			rol role = modelo.obtenerRol(Integer.parseInt(id));
			if (role != null) {
				request.setAttribute("rol", role);
				request.getRequestDispatcher("/rol/editarRol.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void modificar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			rol role = new rol();
			role.setIdrol(Integer.parseInt(request.getParameter("id")));
			role.setNamerol(request.getParameter("namerol"));

			if (modelo.modificarRol(role) > 0) {
				request.getSession().setAttribute("exito", "rol modificada correctamente");
			} else {
				request.getSession().setAttribute("fracaso", "No se pudo modificar el rol");
			}
			response.sendRedirect(request.getContextPath() + "/rolController?op=listar");
		} catch (NumberFormatException | SQLException ex) {
			Logger.getLogger(rolController.class.getName()).log(Level.SEVERE, null, ex);
			throw new ServletException("Error al modificar rol", ex);
		}
	}
}