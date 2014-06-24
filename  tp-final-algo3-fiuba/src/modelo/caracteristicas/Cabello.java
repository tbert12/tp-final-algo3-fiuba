package modelo.caracteristicas;

public enum Cabello {
	CASTANIO("CASTANIO") , RUBIO("RUBIO") ,ROJO("ROJO") ,NEGRO("NEGRO");
	
	private String cabelloNombre;

	Cabello(String valor){
		cabelloNombre = valor;
	}
	
	public static Cabello fromString(String value) {  
		if (value != null) {  
			for (Cabello cabello : values()) {  
				if (cabello.cabelloNombre.equals(value)) {  
					return cabello;
				}
			}
		}
		return null;
	}
	
	public String getString(){
		return cabelloNombre;
	}
	
}