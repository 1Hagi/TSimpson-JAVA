package app;

import java.io.IOException;
import java.util.Collections;

public class App {

	public static void main(String[] args) throws IOException {
		Archivo.cargarUsuarios("src/res/Usuarios.txt", Turismo.usuarios);
		Archivo.cargarExcursiones("src/res/Excursiones.txt", Turismo.ofertas);
		Archivo.cargarPromocionAbsoluta("src/res/PromocionAbsoluta.txt", Turismo.ofertas);
		Archivo.cargarPromocionPorcentual("src/res/PromocionPorcentual.txt", Turismo.ofertas);
		Archivo.cargarPromocionAxB("src/res/PromocionAxB.txt", Turismo.ofertas);

		Collections.sort(Turismo.ofertas, new Ordenamiento(Turismo.usuarios.get(0).getFavorito()));

		Turismo.iniciarApp();
	}
}
