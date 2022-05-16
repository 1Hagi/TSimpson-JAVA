package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.Archivo;
import app.Oferta;
import app.Transaccion;
import app.Usuario;

public class FuncionalidadTest {

	public ArrayList<Usuario> usuariosTest = new ArrayList<Usuario>();
	public ArrayList<Oferta> ofertasTest = new ArrayList<Oferta>();

	@Before
	public void cargarDatos() throws IOException {
		Archivo.cargarUsuarios("src/test/archivo_test/Usuarios_test.txt", usuariosTest);
		Archivo.cargarExcursiones("src/test/archivo_test/Excursiones_test.txt", ofertasTest);
		Archivo.cargarPromocionAbsoluta("src/test/archivo_test/PromocionAbsoluta_test.txt", ofertasTest);
	}

	@Test
	public void funcionCompraExcursionTest() {
		Usuario Barney = usuariosTest.get(0);
		Usuario Apu = usuariosTest.get(1);

		Barney.comprar(ofertasTest.get(0));
		Apu.comprar(ofertasTest.get(2));

		assertEquals(1750, Barney.getDineroDisponible(), 0.001);
		assertTrue(150 == Barney.getTiempoDisponible());

		assertEquals(720, Apu.getDineroDisponible(), 0.001);
		assertTrue(30 == Apu.getTiempoDisponible());
	}

	@Test
	public void funcionCompraPromocionTest() {
		Usuario Barney = usuariosTest.get(0);

		Barney.comprar(ofertasTest.get(3));
		assertEquals(1500, Barney.getDineroDisponible(), 0.001);
		assertTrue(30 == Barney.getTiempoDisponible());
	}

	@Test
	public void disponibilidadTest() {
		Usuario Apu = usuariosTest.get(1);

		// Comprobar cupo
		Transaccion.comprobarCupoTotal(ofertasTest);
		// Comprobar ofertas disponibles
		assertTrue(Transaccion.hayOfertasDisponibles(ofertasTest, Apu));
		assertEquals(2, Transaccion.ofertasDisponibles.size());

		Usuario Barney = usuariosTest.get(0);

		// Comprobar cupo
		Transaccion.comprobarCupoTotal(ofertasTest);
		// Comprobar ofertas disponibles
		assertTrue(Transaccion.hayOfertasDisponibles(ofertasTest, Barney));
		assertEquals(4, Transaccion.ofertasDisponibles.size());

		// Comprobacion despues de una compra
		Barney.comprar(ofertasTest.get(3));
		assertTrue(Transaccion.hayOfertasDisponibles(ofertasTest, Barney));
		assertEquals(1, Transaccion.ofertasDisponibles.size());
	}

}
