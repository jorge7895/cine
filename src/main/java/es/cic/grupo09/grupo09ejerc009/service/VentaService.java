package es.cic.grupo09.grupo09ejerc009.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.exception.SesionException;
import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

	private Logger LOGGER = LogManager.getLogger(VentaService.class);

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private DetalleVentaService detalleVentaService;

	@Autowired
	private EntradaService entradaService;

	private SesionService sesionService;

	private SalaService salaService;

	public Venta create(List<Entrada> listaEntradas) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de venta."));

		Venta venta = new Venta();
		venta.setFhCreacion(LocalDateTime.now());
		ventaRepository.save(venta);

		crearDetallesVenta(venta, listaEntradas);
		ventaRepository.save(venta);

		return ventaRepository.findById(venta.getId()).get();
	}

	public Venta readById(Long id) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de lectura de venta."));

		return ventaRepository.findById(id).get();
	}

	public List<Venta> readBySesionAndSala(long sesion, long sala) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName())
				.concat(" para intento de lecturas de ventas filtradas por sesion y sala."));

		return ventaRepository.readBySesionAndSala(sesionService.readById(sesion), salaService.readById(sala));
	}

	public List<Venta> readAll() {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de lecturas de ventas."));

		return (List<Venta>) ventaRepository.findAll();

	}

	public List<Venta> readByDay(LocalDate dia) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName())
				.concat(" para intento de lecturas de ventas filtradas por dia."));

		LocalDateTime diaAux = LocalDateTime.of(dia, LocalTime.of(0, 0));
		LocalDateTime diaAux2 = LocalDateTime.of(dia.plusDays(1), LocalTime.of(0, 0));

		return ventaRepository.readByDia(diaAux, diaAux2);

	}

	public List<Venta> readBySesion(long sesion) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName())
				.concat(" para intento de lecturas de ventas filtradas por sesion."));

		return ventaRepository.readBySesion(sesionService.readById(sesion));

	}

	@SuppressWarnings("unchecked")
	public Venta updateDevolver(Long id, List<Entrada>... entradas) {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de modificacion de ventas."));

		Venta ventaAux = ventaRepository.findById(id).get();

		switch (entradas.length) {
		case 1:
			devolverEntradas(ventaAux, entradas[0]);
			break;
		case 2:
			devolverEntradas(ventaAux, entradas[0]);
			crearDetallesVenta(ventaAux, entradas[1]);
			break;
		}

		ventaAux.setFhModificado(LocalDateTime.now());
		ventaRepository.save(ventaAux);

		return ventaAux;

	}

	private List<DetalleVenta> localizarDetallesCompraByEntradas(List<Entrada> entradas) {
		List<DetalleVenta> supuestaVenta = new ArrayList<>();
		for (Entrada supuestaEntrada : entradas) {
			DetalleVenta supuestaDetalleVenta;
			try {
				supuestaDetalleVenta = detalleVentaService.localizarEntrada(supuestaEntrada);
			} catch (Exception e) {
				throw new VentaException("La entrada no concuerda con la venta", new Venta());
			}
			if (!Objects.equals(supuestaDetalleVenta, null)) {
				supuestaVenta.add(supuestaDetalleVenta);
			}
		}
		return supuestaVenta;
	}

	private void crearDetallesVenta(Venta venta, List<Entrada> listaEntradas) {

		validatedAforo(listaEntradas);
//		validatedDescuentos(venta, listaEntradas);

		for (Entrada entrada : listaEntradas) {
			DetalleVenta detalleVenta = new DetalleVenta();
			switch (entrada.getDescuento()) {
			case GRUPO:
				detalleVenta.setImporte(5.0f - (5.0f * 0.1f));
				break;
			case JOVEN:
				detalleVenta.setImporte(5.0f - (5.0f * 0.15f));
				break;
			case TERCERA_EDAD:
				detalleVenta.setImporte(5.0f - (5.0f * 0.25f));
				break;
			}
			detalleVenta.setEntrada(entrada);
			detalleVenta.setVenta(venta);
			detalleVenta.setActive(true);
			detalleVentaService.create(detalleVenta);
			venta.setImporteTotal(venta.getImporteTotal() + detalleVenta.getImporte());

		}
	}

	private void validatedDescuentos(Venta venta, List<Entrada> listaEntradas) {
		List<Entrada> listaEntradasCopia = listaEntradas;
		for (Entrada entrada : listaEntradasCopia) {
			switch (entrada.getDescuento()) {
			case GRUPO:

				break;
			case JOVEN:
				listaEntradasCopia.remove(entrada);
				break;
			case TERCERA_EDAD:
				listaEntradasCopia.remove(entrada);
				break;
			}
		}
		if (listaEntradasCopia.size() < 2) {
			throw new VentaException("Una sesion esta llena.", venta);
		}
	}

	private void validatedAforo(List<Entrada> listaEntradas) {
		Map<Sesion, Integer> sesionesConAforo = new HashMap<>();
		for (Entrada entrada : listaEntradas) {
			if (!sesionesConAforo.containsKey(entrada.getSesion())) {
				sesionesConAforo.put(entrada.getSesion(), (int) entradaService.comprobarAforoByEntrada(entrada));
			}
		}
		Map<Sesion, Integer> sesionesSolicitas = listaEntradas.stream()
				.collect(Collectors.groupingBy(e -> e.getSesion(), Collectors.summingInt(e -> 1)));

		sesionesSolicitas.entrySet().forEach(entry -> checkAforo(sesionesConAforo, entry));
	}

	private void checkAforo(Map<Sesion, Integer> sesionesConAforo, Entry<Sesion, Integer> entry) {
		int disponibles = sesionesConAforo.get(entry.getKey());
		int solicito = entry.getValue();
		if (disponibles < solicito) {
			throw new SesionException("Una sesion esta llena.", entry.getKey());
		}
	}

	private void devolverEntradas(Venta ventaAux, List<Entrada> entradas) {
		if (detalleVentaService.localizarEntradas(ventaAux).size() != entradas.size()) {
			throw new VentaException("El numero de entradas no concuerda con la venta entera", ventaAux);
		}
		for (DetalleVenta detalleVenta : localizarDetallesCompraByEntradas(entradas)) {
			ventaAux.setImporteTotal(ventaAux.getImporteTotal() - detalleVenta.getImporte());
			detalleVenta.getEntrada().getSesion().setAforo(detalleVenta.getEntrada().getSesion().getAforo() + 1);
			detalleVenta.setActive(false);
			detalleVentaService.update(detalleVenta);
		}
	}
}