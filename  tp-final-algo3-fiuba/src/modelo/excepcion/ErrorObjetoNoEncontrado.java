package modelo.excepcion;

public class ErrorObjetoNoEncontrado extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String NombreObjetoConflictivo;
	
	public ErrorObjetoNoEncontrado(String nombreAlgo) {
		NombreObjetoConflictivo = nombreAlgo;
	}
	
	public String toString(){
		return "''ERROR'' Objeto Conflictivo: " + NombreObjetoConflictivo;
	}
	

}

