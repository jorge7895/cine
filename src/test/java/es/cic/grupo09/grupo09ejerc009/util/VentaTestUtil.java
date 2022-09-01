package es.cic.grupo09.grupo09ejerc009.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

public class VentaTestUtil {
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;

	public VentaTestUtil() {
		sala = new Sala();
		sala.setAforo(50);
		sala.setButacasFila(5);
		sala.setFilas(10);
		
		proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setEntradasVendidas(0);
		proyeccion.setSesion(1);
		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
		proyeccion.setHoraProyeccion(LocalDateTime.of(2022, Month.SEPTEMBER, 11, 17, 00));
		proyeccion.setPelicula("La dura vida del programador");
		proyeccion.setSala(sala);
		
		venta = new Venta();
		venta.setImporteTotal(5);
		venta.setDiaDeVenta(LocalDateTime.of(2022, Month.SEPTEMBER, 11, 17, 00));
		venta.setActiva(true);
	}
	
	public Entrada crearEntrada(Proyeccion proyeccion, Venta venta) {
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.SENIOR);
		entrada.setProyeccion(proyeccion);
		entrada.setVenta(venta);
		entrada.setButaca(5);
		entrada.setFila(2);
		entrada.setActiva(true);
		
		return entrada;
	}

	public Sala getSala() {
		return sala;
	}

	public Proyeccion getProyeccion() {
		return proyeccion;
	}

	public Venta getVenta() {
		return venta;
	}
}
