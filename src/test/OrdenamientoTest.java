package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import app.Archivo;
import app.Oferta;
import app.Ordenamiento;
import app.Usuario;

public class OrdenamientoTest {

	public ArrayList<Usuario> usuariosTest = new ArrayList<Usuario>();
	public ArrayList<Oferta> ofertasTest = new ArrayList<Oferta>();

	@Before
	public void cargarDatos() throws IOException {
		Archivo.cargarUsuarios("src/test/archivo_test/Usuarios_test.txt", usuariosTest);
		Archivo.cargarExcursiones("src/test/archivo_test/Excursiones_test.txt", ofertasTest);
		Archivo.cargarPromocionAbsoluta("src/test/archivo_test/PromocionAbsoluta_test.txt", ofertasTest);
	}

	@Test
	public void ordenamientoPorUsuarioTest() {

		Usuario Barney = usuariosTest.get(0);

		ArrayList<Oferta> ordenEsperado = new ArrayList<Oferta>();
		ordenEsperado.add(ofertasTest.get(3));
		ordenEsperado.add(ofertasTest.get(1));
		ordenEsperado.add(ofertasTest.get(2));
		ordenEsperado.add(ofertasTest.get(0));

		Collections.sort(ofertasTest, new Ordenamiento(Barney.getFavorito()));
		ArrayList<Oferta> ordenActual = new ArrayList<Oferta>();
		for (Oferta oferta : ofertasTest) {
			if (oferta.getCupo() > 0) {
				ordenActual.add(oferta);
			}
		}

		assertArrayEquals(ordenEsperado.toArray(), ordenActual.toArray());
	}

}
