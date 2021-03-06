package app;

import java.util.ArrayList;

public class PromoAbsoluta extends Promocion {

	private Double costo;

	public PromoAbsoluta(String nombre, OfertaTipo tipo, ArrayList<Oferta> excursiones, Double costo) {
		super(nombre, tipo, excursiones);
		this.costo = costo;
	}

	public Double getCosto() {
		return this.costo;
	}

	@Override
	public String toString() {
		return "Promocion Absoluta: '" + this.nombre + "' incluye las excursiones:" + super.mostrarExcursiones()
				+ "\n\tTiempo total necesario: " + this.getTiempo() + " min.\n\tCosto en dinero total: $ "
				+ this.getCosto();
	}
}
