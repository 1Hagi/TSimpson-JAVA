package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import app.Archivo;
import app.Excursion;
import app.Oferta;
import app.OfertaTipo;
import app.Promocion;
import app.Usuario;

public class ArchivoLecturaTest {

	public ArrayList<Usuario> usuariosTest = new ArrayList<Usuario>();
	public ArrayList<Oferta> ofertasTest = new ArrayList<Oferta>();

	@Before
	public void cargarDatos() throws IOException {
		Archivo.cargarUsuarios("src/test/archivo_test/Usuarios_test.txt", usuariosTest);
		Archivo.cargarExcursiones("src/test/archivo_test/Excursiones_test.txt", ofertasTest);
		Archivo.cargarPromocionAbsoluta("src/test/archivo_test/PromocionAbsoluta_test.txt", ofertasTest);
	}

	@Test
	public void lecturaUsuariosTest() throws IOException {

		// Nombre de usuario
		assertTrue("Barney".equals(usuariosTest.get(0).getNombre()));
		assertTrue("Apu".equals(usuariosTest.get(1).getNombre()));
		// Dinero de usuario
		assertEquals(2000, usuariosTest.get(0).getDineroDisponible(), 0.001);
		assertEquals(1000, usuariosTest.get(1).getDineroDisponible(), 0.001);
		// Tiempo de usuario
		assertTrue(180 == usuariosTest.get(0).getTiempoDisponible());
		assertTrue(60 == usuariosTest.get(1).getTiempoDisponible());
		// Atraccion favorita de usuario
		assertTrue(OfertaTipo.TIENDA == usuariosTest.get(0).getFavorito());
		assertTrue(OfertaTipo.RESTORAN == usuariosTest.get(1).getFavorito());
	}

	@Test
	public void lecturaExcursionesTest() {

		// Nombre de excursion
		assertTrue("742 Evergreen Terrace".equals(ofertasTest.get(0).getNombre()));
		assertTrue("Mapple".equals(ofertasTest.get(1).getNombre()));
		assertTrue("Police Station".equals(ofertasTest.get(2).getNombre()));
		// Costo de excursion
		assertEquals(250, ofertasTest.get(0).getCosto(), 0.001);
		assertEquals(400, ofertasTest.get(1).getCosto(), 0.001);
		assertEquals(280, ofertasTest.get(2).getCosto(), 0.001);
		// Tiempo de excursion
		assertTrue(30 == ofertasTest.get(0).getTiempo());
		assertTrue(120 == ofertasTest.get(1).getTiempo());
		assertTrue(30 == ofertasTest.get(2).getTiempo());
		// Tipo de excursion
		assertTrue(OfertaTipo.VISITAGUIADA == ofertasTest.get(0).getTipo());
		assertTrue(OfertaTipo.VISITAGUIADA == ofertasTest.get(0).getTipo());
		assertTrue(OfertaTipo.VISITAGUIADA == ofertasTest.get(0).getTipo());
	}

	@Test
	public void lecturaPromocionTest() {
		// Nombre de promocion
		assertTrue("Combo Basico Springfield".equals(ofertasTest.get(3).getNombre()));
		// Costo de promocion
		assertEquals(500, ofertasTest.get(3).getCosto(), 0.001);
		// Tiempo de promocion
		assertTrue(150 == ofertasTest.get(3).getTiempo());
		// Tipo de promocion
		assertTrue(OfertaTipo.VISITAGUIADA == ofertasTest.get(0).getTipo());
	}

	@Test
	public void excursionesIncluidasEnPromocionTest() {
		Excursion excursionEsperada01 = (Excursion) ofertasTest.get(0);
		Excursion excursionEsperada02 = (Excursion) ofertasTest.get(1);
		ArrayList<Oferta> excursionesEnPromocion = ((Promocion) ofertasTest.get(3)).getExcursiones();

		assertEquals(excursionEsperada01, excursionesEnPromocion.get(0));
		assertEquals(excursionEsperada02, excursionesEnPromocion.get(1));
	}
}
