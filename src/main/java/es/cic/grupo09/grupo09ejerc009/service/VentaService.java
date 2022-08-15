package es.cic.grupo09.grupo09ejerc009.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
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

	public Venta create(List<Entrada> listaEntradas) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de venta."));

		Venta venta = new Venta();
		venta.setFhCreacion(LocalDateTime.now());
		ventaRepository.save(venta);

		// TODO metodo a mejorar.
		// La idea seria calcular la capacidad de todas las sesiones de la venta antes
		// de crear los detalles. Ahora tiene una ñapa que seria el if, pero es
		// ineficiente.
		for (Entrada entrada : listaEntradas) {
			if (entrada.getSesion().getAforo() > 0) {
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
				detalleVentaService.create(detalleVenta);
				venta.setImporteTotal(venta.getImporteTotal() + detalleVenta.getImporte());
			} else {
				throw new VentaException("Una sesion no esta diponible, sesion llena", venta);
			}
		}

		ventaRepository.save(venta);
		return ventaRepository.findById(venta.getId()).get();
	}

	public Venta updateDevolverAll(Venta venta, List<Entrada> entradas) {

		List<DetalleVenta> supuestaVenta = localizarDetallesCompraByEntradas(entradas);
		for (DetalleVenta detalleVenta : supuestaVenta) {
			if (!Objects.equals(detalleVenta.getVenta(), venta)) {
				throw new VentaException("La entrada no concuerda con la venta", venta);
			}
		}
		if (detalleVentaService.localizarEntradas(venta).size() != entradas.size()) {
			throw new VentaException("El numero de entradas no concuerda con la venta entera", venta);
		}

		for (DetalleVenta detalleVenta : supuestaVenta) {
			if (Objects.equals(detalleVenta.getVenta(), venta)) {
				venta.setImporteTotal(venta.getImporteTotal() - detalleVenta.getImporte());
			}
		}
		venta.setFhModificado(LocalDateTime.now());
		ventaRepository.save(venta);
		return venta;
	}

	public List<Venta> readBySesionAndSala(Sesion sesion, Sala sala) {
		return ventaRepository.readBySesionAndSala(sesion, sala);
	}

	public List<Venta> readAll(Sesion sesion, Sala sala) {
		return (List<Venta>) ventaRepository.findAll();
	}

	public List<Venta> readByDay(LocalDate dia) {
		// TODO Falta por hacer
		throw new UnsupportedOperationException();
	}

	public List<Venta> readBySesion(Sesion sesion) {
		return ventaRepository.readBySesion(sesion);
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
}
