package es.cic.grupo09.grupo09ejerc009.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.util.EnumDescuento;

@SpringBootTest
@Transactional
class VentaServiceTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private VentaService ventaService;

	private static Sala[] salas = new Sala[3];
	private static Sesion[] sesiones = new Sesion[3];

	@BeforeEach
	void setUp() throws Exception {
		initSalas();
		initSesiones("Pelicula-01", "Pelicula-02", "Pelicula-03", "Pelicula-04", "Pelicula-05", "Pelicula-06",
				"Pelicula-07", "Pelicula-08", "Pelicula-09", "Pelicula-10");
	}

	@Test
	void createVentaValidTest() {
		int nEntradas = 10;
		Venta nuevaVenta = ventaService.create(initEntradas(nEntradas));

		assertNotNull(nuevaVenta);
	}

	@Test
	void createVentaAforoErrorTest() {
		int nEntradas = 200;
		Venta nuevaVenta = ventaService.create(initEntradas(nEntradas));

		assertNotNull(nuevaVenta);
	}

	@Test
	void updateDevolverTodoValidTest() {
		int nEntradas = 10;
		List<Entrada> listaEntradas = initEntradas(nEntradas);
		Venta nuevaVenta = ventaService.create(listaEntradas);

		ventaService.updateDevolverAll(nuevaVenta, listaEntradas);
		assertEquals(0, nuevaVenta.getImporteTotal());
	}

	@Test
	void updateDevolverTodoErrorUnaMenosTest() {
		int nEntradas = 10;
		List<Entrada> listaEntradas = initEntradas(nEntradas);
		Venta nuevaVenta = ventaService.create(listaEntradas);

		listaEntradas.remove(0);
		assertThrows(VentaException.class, () -> ventaService.updateDevolverAll(nuevaVenta, listaEntradas));
	}

	@Test
	void updateDevolverTodoErrorRemplazoFalsoTest() {
		int nEntradas = 10;

		Entrada entradaFalsa = new Entrada();
		entradaFalsa.setDescuento(EnumDescuento.GRUPO);
		entradaFalsa.setSesion(sesiones[0]);

		List<Entrada> listaEntradas = initEntradas(nEntradas);
		Venta nuevaVenta = ventaService.create(listaEntradas);

		listaEntradas.set(0, entradaFalsa);

		assertThrows(VentaException.class, () -> ventaService.updateDevolverAll(nuevaVenta, listaEntradas));
	}

	@Test
	void updateDevolverTodoErrorRemplazoOtraTest() {
		int nEntradas = 10;

		List<Entrada> listaEntradas1 = initEntradas(nEntradas);
		Venta nuevaVenta1 = ventaService.create(listaEntradas1);

		List<Entrada> listaEntradas2 = initEntradas(nEntradas);

		listaEntradas1.set(0, listaEntradas2.get(0));

		assertThrows(VentaException.class, () -> ventaService.updateDevolverAll(nuevaVenta1, listaEntradas1));
	}

	@Test
	void readBySesionAndSalaTest() {
		List<Entrada> listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);
		listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);
		listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);

		assertTrue(ventaService.readBySesionAndSala(sesiones[0], salas[0]).size() > 0);
	}

	@Test
	void readBySesionTest() {
		List<Entrada> listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);
		listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);
		listaEntradas1 = initEntradas(10);
		ventaService.create(listaEntradas1);

		assertTrue(ventaService.readBySesion(sesiones[0]).size() > 0);
	}

	private List<Entrada> initEntradas(int nEntradas) {
		List<Entrada> listaEntradasVenta = new ArrayList<>();
		for (int i = 0; i < nEntradas; i++) {
			Entrada auxEntrada = new Entrada();
			auxEntrada.setSesion(sesiones[(int) (Math.random() * sesiones.length)]);
			switch ((int) (Math.random() * 3)) {
			case 0:
				auxEntrada.setDescuento(EnumDescuento.GRUPO);
				break;
			case 1:
				auxEntrada.setDescuento(EnumDescuento.JOVEN);
				break;
			case 2:
				auxEntrada.setDescuento(EnumDescuento.TERCERA_EDAD);
				break;
			}
			listaEntradasVenta.add(auxEntrada);
		}
		return listaEntradasVenta;
	}

	private void initSalas() {

		for (int i = 0; i < salas.length; i++) {
			salas[i] = new Sala();
			switch (i) {
			case 0:
				salas[i].setCapacidad(100);
				break;
			case 1:
				salas[i].setCapacidad(50);
				break;
			case 2:
				salas[i].setCapacidad(30);
				break;
			}
			em.persist(salas[i]);
		}

	}

	private void initSesiones(String... pelicula) {

		for (int i = 0; i < sesiones.length; i++) {
			sesiones[i] = new Sesion();
			sesiones[i].setSala(salas[(int) (Math.random() * salas.length)]);
			sesiones[i].setAforo(sesiones[i].getSala().getCapacidad());
			sesiones[i].setPelicula(pelicula[i]);
			sesiones[i].setHoraEmpieza(LocalDateTime.now());
			sesiones[i].setDuracionMin(120);
			em.persist(sesiones[i]);
		}

	}

}
