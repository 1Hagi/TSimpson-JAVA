package app;

import java.io.*;
import java.util.*;

public class Archivo {

	public static ArrayList<String> leerArchivo(String rutaDeArchivo) throws IOException {

		File archivo = new File(rutaDeArchivo);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		ArrayList<String> arrayLineas = new ArrayList<String>();

		try {

			while (linea != null) {
				arrayLineas.add(linea);
				linea = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return arrayLineas;
	}

	public static void cargarUsuarios(String RutadeArchivo, ArrayList<Usuario> clientes) throws IOException {
		ArrayList<String> arrayDatos = leerArchivo(RutadeArchivo);
		for (int i = 0; i < arrayDatos.size(); i++) {
			ArrayList<String> campos = new ArrayList<>(Arrays.asList(arrayDatos.get(i).split(";")));
			clientes.add(new Usuario(campos.get(0), OfertaTipo.valueOf(campos.get(1).toUpperCase()),
					Integer.parseInt(campos.get(2)), Double.parseDouble(campos.get(3))));
			// System.out.println(Turismo.usuarios.get(i));
		}
	}

	public static void cargarExcursiones(String RutadeArchivo, ArrayList<Oferta> ofertas) throws IOException {
		ArrayList<String> arrayDatos = leerArchivo(RutadeArchivo);
		for (int i = 0; i < arrayDatos.size(); i++) {
			ArrayList<String> campos = new ArrayList<>(Arrays.asList(arrayDatos.get(i).split(";")));
			ofertas.add(new Excursion(campos.get(0), OfertaTipo.valueOf(campos.get(1).toUpperCase()),
					Integer.parseInt(campos.get(2)), Double.parseDouble(campos.get(3)),
					Integer.parseInt(campos.get(4))));
			// System.out.println(Turismo.ofertas.get(i));
		}
	}

	public static void cargarPromocionAbsoluta(String rutadeArchivo, ArrayList<Oferta> ofertas) throws IOException {
		ArrayList<String> arrayDatos = leerArchivo(rutadeArchivo);
		for (int i = 0; i < arrayDatos.size(); i++) {
			ArrayList<String> campos = new ArrayList<>(Arrays.asList(arrayDatos.get(i).split(";")));
			ArrayList<Oferta> excursiones = new ArrayList<Oferta>();

			for (int j = 4; j < campos.size(); j++) {
				for (int k = 0; k < ofertas.size(); k++) {
					if (ofertas.get(k).getNombre().equals(campos.get(j))) {
						excursiones.add(ofertas.get(k));
					}
				}
			}

			ofertas.add(new PromoAbsoluta(campos.get(0), OfertaTipo.valueOf(campos.get(1).toUpperCase()), excursiones,
					Double.parseDouble(campos.get(3))));
		}
	}

	public static void cargarPromocionPorcentual(String rutadeArchivo, ArrayList<Oferta> ofertas) throws IOException {
		ArrayList<String> arrayDatos = leerArchivo(rutadeArchivo);
		for (int i = 0; i < arrayDatos.size(); i++) {
			ArrayList<String> campos = new ArrayList<>(Arrays.asList(arrayDatos.get(i).split(";")));
			ArrayList<Oferta> excursiones = new ArrayList<Oferta>();

			for (int j = 4; j < campos.size(); j++) {
				for (int k = 0; k < ofertas.size(); k++) {
					if (ofertas.get(k).getNombre().equals(campos.get(j))) {
						excursiones.add(ofertas.get(k));
					}
				}
			}

			ofertas.add(new PromoPorcentual(campos.get(0), OfertaTipo.valueOf(campos.get(1).toUpperCase()), excursiones,
					Integer.parseInt(campos.get(3))));
		}
	}

	public static void cargarPromocionAxB(String rutadeArchivo, ArrayList<Oferta> ofertas) throws IOException {
		ArrayList<String> arrayDatos = leerArchivo(rutadeArchivo);
		for (int i = 0; i < arrayDatos.size(); i++) {
			ArrayList<String> campos = new ArrayList<>(Arrays.asList(arrayDatos.get(i).split(";")));
			ArrayList<Oferta> excursiones = new ArrayList<Oferta>();

			for (int j = 4; j < campos.size(); j++) {
				for (int k = 0; k < ofertas.size(); k++) {
					if (ofertas.get(k).getNombre().equals(campos.get(j))) {
						excursiones.add(ofertas.get(k));
					}
				}
			}

			ofertas.add(new PromoAxB(campos.get(0), OfertaTipo.valueOf(campos.get(1).toUpperCase()), excursiones,
					Integer.parseInt(campos.get(3))));
		}
	}

	public static void generarItinerario(Usuario usuario, String ruta) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(ruta));
		ArrayList<String> lineas = new ArrayList<>(Arrays.asList(usuario.getItinerario().split("\n")));
		for (String linea : lineas) {
			salida.println(linea);
		}
		salida.close();
	}
}