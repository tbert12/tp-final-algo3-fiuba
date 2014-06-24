package modelo.caracteristicas;

public enum Vehiculo {
	DESCAPOTABLE("DESCAPOTABLE"), LIMUSINA("LIMUSINA") , DEPORTIVO("DEPORTIVO") , MOTO("MOTO");
	private String stringVehiculo;
	
	Vehiculo(String valor){
		stringVehiculo = valor;
	}
	
	public static Vehiculo fromString(String value) {  
		if (value != null) {  
			for (Vehiculo vehiculo : values()) {  
				if (vehiculo.stringVehiculo.equals(value)) {  
					return vehiculo;
				}
			}
		}
		return null;
	}
	
	public String getString(){
		return stringVehiculo;
	}
	
}
