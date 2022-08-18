package es.cic.grupo09.grupo09ejerc009.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;
import es.cic.grupo09.grupo09ejerc009.util.VentaUtil;

class VentaUtilTest {

	private VentaUtil cut;
	private List<Entrada> listaEntradas;
	
	@BeforeEach
	void setUp() throws Exception {
		
		cut = new VentaUtil();
		listaEntradas = new ArrayList<Entrada>();
	}

	@Test
	void validarDescuentoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(7, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(7, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.JOVEN)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.SENIOR)).count());
	}
	
	@Test
	void validarDescuentoCriticoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(5, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(5, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.JOVEN)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.SENIOR)).count());
	}
	
	@Test
	void validarDescuentoSinGrupoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(4, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.JOVEN)).count());
		assertEquals(4, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getTipoEntrada().equals(TipoEntrada.SENIOR)).count());
	}
	
	@Test
	void actualizarImporteVentaJovenTest() {
		
		Venta venta = new Venta();
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.JOVEN);
		entrada.setVenta(venta);
		cut.actualizarImporteVenta(entrada);
		
		assertEquals(4.25f, entrada.getVenta().getImporteTotal());
	}
	
	@Test
	void actualizarImporteVentaSeniorTest() {
		
		Venta venta = new Venta();
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.SENIOR);
		entrada.setVenta(venta);
		cut.actualizarImporteVenta(entrada);
		
		assertEquals(3.75f, entrada.getVenta().getImporteTotal());
	}
	
	@Test
	void actualizarImporteVentaGrupoTest() {
		
		Venta venta = new Venta();
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.GRUPO);
		entrada.setVenta(venta);
		cut.actualizarImporteVenta(entrada);
		
		assertEquals(4.5f, entrada.getVenta().getImporteTotal());
	}
	
	@Test
	void actualizarImporteVentaNormalTest() {
		
		Venta venta = new Venta();
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.NORMAL);
		entrada.setVenta(venta);
		cut.actualizarImporteVenta(entrada);
		
		assertEquals(5f, entrada.getVenta().getImporteTotal());
	}

	private void initEntradas(int nEntradas, TipoEntrada tipoEntrada) {
		
		Entrada entrada = new Entrada();

		for (int i = 0; i < nEntradas; i++) {
			entrada.setTipoEntrada(tipoEntrada);
			this.listaEntradas.add(entrada);
		}
	}
}
