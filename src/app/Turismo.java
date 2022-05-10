package app;

import java.io.IOException;
import java.util.*;

public class Turismo {

	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

	public static void iniciarApp() throws IOException {

		for (Usuario usuario : Turismo.usuarios) {
			boolean seguirOfreciendo = true;
			Oferta unaOferta;
			Mensaje.bienvenida(usuario);
			if (Transaccion.comprobarCupoTotal(ofertas)) {
				Collections.sort(Turismo.ofertas, new Ordenamiento(usuario.getFavorito()));
				while (seguirOfreciendo) {
					if (Transaccion.hayOfertasDisponibles(Turismo.ofertas, usuario)) {
						unaOferta = Transaccion.getOferta();
						Mensaje.deseaComprar(unaOferta);
						if (usuario.responderPregunta()) {
							Transaccion.comprarOferta(usuario, unaOferta);
							Mensaje.compraExitosa(usuario);
							Mensaje.seguirComprando();
							seguirOfreciendo = usuario.responderPregunta();
						} else {
							Transaccion.denegarOferta(usuario, unaOferta);
							Mensaje.seguirComprando();
							seguirOfreciendo = usuario.responderPregunta();
						}
					} else {
						Mensaje.NoPuedeComprarMas();
						seguirOfreciendo = false;
					}
				}
			} else {
				Mensaje.sinCupos();
			}
			Mensaje.mostrarItinerario(usuario);
			Archivo.generarItinerario(usuario, "src/res/itinerarios/" + usuario.getNombre() + "_itinerario.txt");
		}
		Mensaje.finPrograma();
	}

}
