package Modelo;

public class Producto {
	int idproducto, cantidad, categoria;
	String descripcion;
    double precio;
    
    public Producto() {
    	
    }
	
	public Producto(int idproducto, int cantidad, int categoria, String descripcion, double precio) {
		super();
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
