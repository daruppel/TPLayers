package ar.unrn.tp3.modelo;

public class Telefono {
	private String numero;

	public Telefono(String telefono) {
		if (telefono.equals("")) {
			throw new RuntimeException("Debe cargar un telefono");
		}
		if (!validarTelefono(telefono)) {
			throw new RuntimeException("El teléfono debe ingresarse de la siguienteforma: NNNN-NNNNNN");
		}
		this.numero = telefono;
	}

	private boolean validarTelefono(String telefono) {
		String regex = "\\d{4}-\\d{6}";
		return telefono.matches(regex);
	}

	public String numero() {
		return this.numero;
	}
}
