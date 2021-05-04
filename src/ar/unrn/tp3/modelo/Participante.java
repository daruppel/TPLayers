package ar.unrn.tp3.modelo;

public class Participante {
	private String nombre;
	private Telefono telefono;
	private String region;

	public Participante(String nombre, Telefono telefono, String region) {
		if (nombre.equals("")) {
			throw new RuntimeException("Debe cargar un nombre");
		}
		if (!region.equals("China") && !region.equals("US") && !region.equals("Europa")) {
			throw new RuntimeException("Region desconocida. Las conocidas son:China, US, Europa");
		}
		this.nombre = nombre;
		this.region = region;
		this.telefono = telefono;
	}

	public String nombre() {
		return this.nombre;
	}

	public Telefono telefono() {
		return this.telefono;
	}

	public String region() {
		return this.region;
	}
}
