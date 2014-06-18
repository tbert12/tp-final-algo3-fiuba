package modelo;

public class ObjetoRobado {
	
	private String Valor,Nombre;
	
	public ObjetoRobado(String UnNombre,String UnValor){
		this.Nombre = UnNombre;
		this.Valor = UnValor;
	}
	
	public String getValor(){
		return Valor;
	}
	
	public String getNombre(){
		return this.Nombre;
	}

}
