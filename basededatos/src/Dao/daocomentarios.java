package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.comentarios;

public class daocomentarios {
	Conexion cx =null;
	
	public daocomentarios() {
		cx=new Conexion();
	}
	
	public boolean insertarcomentarios(comentarios user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO comentarios VALUES(null,?,?)");
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getTexto());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<comentarios> fetchUsuarios(){
		ArrayList<comentarios> lista=new ArrayList<comentarios>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM comentarios");
			rs=ps.executeQuery();
			while(rs.next()) {
				comentarios u=new comentarios();
				u.setId(rs.getInt("ID"));
				u.setTexto(rs.getString("texto"));
				u.setUsuario(rs.getString("usuario"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	
	public boolean eliminarcomentarios(int id) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM comentarios WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarcomentarios(comentarios user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE comentarios SET texto=?, usuario=? WHERE id=?");
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getTexto());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	

}
