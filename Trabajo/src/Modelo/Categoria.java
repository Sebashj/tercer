package Modelo;

public class Categoria {
	int idCategoria;
	String categoria;

	public Categoria () {
		
	}
	public Categoria(int idCategoria, String categoria) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
