package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.dao.UsuarioDAO;
import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author Thiago Barbosa Palma 816117515
 * 
 *
 *
 *
 */

@Controller
public class LoginController {
	UsuarioService service;
	
	@Autowired
	public LoginController(UsuarioService srv){
		this.service = srv;
	}
	
	
	@RequestMapping("LoginForm")
	public String loginForm(){
		return "formulario-login";
	}
	
		
	@RequestMapping("fazer_login")
	public String fazerLogin(Usuario usuario, HttpSession session){
		try {
			if(service.validarUsuario(usuario)){
				session.setAttribute(Usuario.LOGADO, usuario);
				return "web";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.setAttribute(Usuario.LOGADO, null);
		return "formulario-login";
	}


}
