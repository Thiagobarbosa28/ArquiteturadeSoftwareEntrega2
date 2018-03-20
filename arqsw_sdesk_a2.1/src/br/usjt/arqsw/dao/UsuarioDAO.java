package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author Thiago Barbosa Palma 816117515
 * 
 *
 */
@Repository
public class UsuarioDAO {
private Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException{
	try{
	this.conn = dataSource.getConnection();
	} catch (SQLException e){
	throw new IOException(e);
	}
	}
	
	public ArrayList<Usuario> consultarUsuarios() throws IOException {
		String query = "select username, password from usuario";
		ArrayList<Usuario> lista = new ArrayList<>();
		
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				lista.add(usuario);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

	public boolean validarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
