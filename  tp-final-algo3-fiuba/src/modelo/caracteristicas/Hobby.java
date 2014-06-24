package modelo.caracteristicas;

public enum Hobby {
	TENNIS("TENNIS") , MUSICA("MUSICA") ,ALPINISMO("ALPINISMO"), PARACAIDISMO("PARACAIDISMO"),
	NATACION("NATACION") ,CROQUET("CROQUET");
	
	private String stringHobby;
	
	Hobby(String valor){
		stringHobby = valor;
	}
	
	public static Hobby fromString(String value) {  
		if (value != null) {  
			for (Hobby hobby : values()) {  
				if (hobby.stringHobby.equals(value)) {  
					return hobby;
				}
			}
		}
		return null;
	}
	
	public String getString(){
		return stringHobby;
	}
	
	
}
