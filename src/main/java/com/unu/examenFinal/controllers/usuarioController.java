package com.unu.examenFinal.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.unu.examenFinal.models.usuarioModel;
import com.unu.examenFinal.beans.usuario;
import com.unu.examenFinal.beans.rol;

/**
 * Servlet implementation class usuarioController
 */
public class usuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	usuarioModel modelo = new usuarioModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public usuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op") == null) {
			listar(request, response);
			return;
		}
		String operacion = request.getParameter("op");
		switch (operacion) {
		case "listar":
			cargarRol(request, response);
			listar(request, response);
			break;
		case "nuevo":
			cargarRol(request, response);
			request.getRequestDispatcher("/usuarios/nuevoUsuario.jsp").forward(request, response);
			break;
		case "insertar":
			insertar(request, response);
			break;
		case "obtener":
			cargarRol(request, response);
			obtener(request, response);
			break;
		case "modificar":
			modificar(request, response);
			break;
		case "eliminar":
			eliminar(request, response);
			break;
		case "buscar":
			buscar(request, response);
			break;

		}

	}

	private boolean validar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean res = false;
		List<String> listaError = new ArrayList<>();
		try {
			if (request.getParameter("username").equals("")) {
				res = true;
				listaError.add("Ingrese el usuario");

			}
			if (request.getParameter("password").equals("")) {
				res = true;
				listaError.add("Ingrese el password");

			}
			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", listaError);

		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		return res;
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listaUsuarios", modelo.listarUsuarios2());
		request.getRequestDispatcher("/usuarios/listaUsuarios.jsp").forward(request, response);
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			if (!validar(request, response)) {
				usuario user = new usuario();
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				user.setIdrol(Integer.parseInt(request.getParameter("idrol")));
				if (modelo.insertarUsuario(user) > 0) {
					request.getSession().setAttribute("exito", "usuario registrado exitosamente");
					System.out.println("exito" + " usuario registrado exitosamente" + "nombre: " + user.getUsername());
				} else {
					request.getSession().setAttribute("fracaso", "usuario no registrado");
					System.out.println("Error" + " usuario no registrado" + " nombre: " + user.getUsername());
				}

				request.setAttribute("usuario", user);
				response.sendRedirect(request.getContextPath() + "/usuarioController?op=listar");

			} else {

				request.getRequestDispatcher("/usuarios/nuevoUsuario.jsp").forward(request, response);
			}

		} catch (Exception ex) {
			//
			ex.getStackTrace();
		}

	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {

			String id = request.getParameter("id");
			usuario user = modelo.obtenerUsuario(Integer.parseInt(id));
			if (user != null) {
				List<rol> role = modelo.listarRoles();
				request.setAttribute("usuario", user);
				request.setAttribute("listarol", role);
				request.getRequestDispatcher("/usuarios/editarUsuario.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {

			usuario user = new usuario();
			user.setId(Integer.parseInt(request.getParameter("iduser")));
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setNamerol(request.getParameter("idrol"));
			request.setAttribute("usuario", user);

			if (modelo.modificarUsuario(user) > 0) {
				request.getSession().setAttribute("exito", "usuario modificado exitosamente");
				System.out.println("exito " + "usuario modificado exitosamente " + " Nombre : " + user.getUsername());
				response.sendRedirect(request.getContextPath() + "/usuarioController?op=listar");

			} else {
				request.getSession().setAttribute("fracaso",
						"El usuario no ha sido modificado" + "ya hay un usuario con este codigo");
				System.out.println("Error " + " ya hay un usuario con este codigo " + " Nombre: " + user.getUsername());
				response.sendRedirect(request.getContextPath() + "/usuarioController?op=listar");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));

			if (modelo.eliminarUsuario(id) > 0) {
				request.getSession().setAttribute("exito", "usuario eliminado exitosamente");
				System.out.println("exito " + " usuario eliminado exitosamente" + " ID: " + id);
				response.sendRedirect(request.getContextPath() + "/usuarioController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El usuario no ha sido eliminado" + "ya hay un usuario con este codigo");
				System.out.println("fracaso " + " usuario no eliminado" + " ID: " + id);
			}
			response.sendRedirect(request.getContextPath() + "/usuarioController?op=listar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		List<usuario> listaUsuarios;
		if (query != null && !query.isEmpty()) {
			listaUsuarios = modelo.buscarUsuario(query);

		} else {
			listaUsuarios = modelo.listarUsuarios();

		}
		request.setAttribute("listaUsuarios", listaUsuarios);
		request.getRequestDispatcher("/usuarios/listaUsuarios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private void cargarRol(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<rol> role = modelo.listarRoles();

			request.setAttribute("listarol", role);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
