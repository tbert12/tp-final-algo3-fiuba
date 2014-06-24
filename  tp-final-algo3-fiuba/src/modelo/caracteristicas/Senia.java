package modelo.caracteristicas;

public enum Senia {
	COJERA("COJERA"),ANILLO("ANILLO"),TATUAJE("TATUAJE"),CICATRIZ("CICATRIZ"),JOYAS("JOYAS");
	
	private String stringSenia;
	
	Senia(String valor){
		stringSenia = valor;
	}
	
	public static Senia fromString(String value) {  
		if (value != null) {  
			for (Senia senia : values()) {  
				if (senia.stringSenia.equals(value)) {  
					return senia;
				}
			}
		}
		return null;
	}
	
	public String getString(){
		return stringSenia;
	}
	
	
}
