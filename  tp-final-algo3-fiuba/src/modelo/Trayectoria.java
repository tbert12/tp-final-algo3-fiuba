package modelo;

public class Trayectoria {
	
	private Pais PaisActual;
	private Pais PaisFinal;
	private Pais[] Paises;
	private int indice = 0;

	public Trayectoria (Pais[] Paises){
		this.Paises = Paises;
		this.PaisActual = Paises[0];
		this.PaisFinal = Paises[-1];
	}
	
	public Pais avanzar(){
		if (indice < Paises.length){
			indice += 1;
			PaisActual = Paises[indice];
			return PaisActual;
		}
		else {
			throw new ErrorNoHayMasPaisesParaAvanzar;
		}
	}
	
	public Pais paisActual(){
		return PaisActual;
	}
	public Pais paisFinal(){
		return PaisFinal;
	}
}
