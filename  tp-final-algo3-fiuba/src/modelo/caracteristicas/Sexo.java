package modelo.caracteristicas;

public enum Sexo {
	FEMENINO("FEMENINO"),MASCULINO("MASCULINO");
	
	private String stringSexo;
	
	Sexo(String valor){
		stringSexo = valor;
	}
	
	public static Sexo fromString(String value) {  
		if (value != null) {  
			for (Sexo sexo : values()) {  
				if (sexo.stringSexo.equals(value)) {  
					return sexo;
				}
			}
		}
		return null;
	}
	
	public String getString(){
		return stringSexo;
	}
	
	
}
