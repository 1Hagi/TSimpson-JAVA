package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.Archivo;
import app.Oferta;
import app.Usuario;

public class ItinerarioTest {

	public ArrayList<Usuario> usuariosTest = new ArrayList<Usuario>();
	public ArrayList<Oferta> ofertasTest = new ArrayList<Oferta>();

	@Before
	public void cargarDatos() throws IOException {
		Archivo.cargarUsuarios("src/test/archivo_test/Usuarios_test.txt", usuariosTest);
		Archivo.cargarExcursiones("src/test/archivo_test/Excursiones_test.txt", ofertasTest);
		Archivo.cargarPromocionAbsoluta("src/test/archivo_test/PromocionAbsoluta_test.txt", ofertasTest);
	}

	@Test
	public void itinerarioGeneradoTest() throws IOException {

		Usuario Barney = usuariosTest.get(0);
		Oferta oferta01 = ofertasTest.get(0);
		Oferta oferta02 = ofertasTest.get(1);

		Barney.comprar(oferta01);
		Barney.comprar(oferta02);

		Archivo.generarItinerario(Barney, "src/test/archivo_test/itinerario_generado.txt");

		byte[] ofertas1Bytes = Files.readAllBytes(Paths.get("src/test/archivo_test/itinerario_esperado.txt"));
		byte[] ofertas2Bytes = Files.readAllBytes(Paths.get("src/test/archivo_test/itinerario_generado.txt"));

		String itinerario01 = new String(ofertas1Bytes, StandardCharsets.UTF_8);
		String itinerario02 = new String(ofertas2Bytes, StandardCharsets.UTF_8);

		assertEquals(itinerario01, itinerario02);

		File itinerario_generado = new File("src/test/archivo_test/itinerario_generado.txt");
		itinerario_generado.delete();
	}

	@Test
	public void itinerarioContenidoTest() {

		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();
		itinerarioEsperado.add(ofertasTest.get(0));
		itinerarioEsperado.add(ofertasTest.get(1));

		Usuario Barney = usuariosTest.get(0);
		Oferta oferta01 = ofertasTest.get(0);
		Oferta oferta02 = ofertasTest.get(1);

		Barney.comprar(oferta01);
		Barney.comprar(oferta02);

		ArrayList<Oferta> itinerarioActual = new ArrayList<Oferta>(Barney.getOfertasCompradas());

		assertArrayEquals(itinerarioEsperado.toArray(), itinerarioActual.toArray());

	}
}
