package es.cic.grupo09.grupo09ejerc009.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.util.DescuentoUtil;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

class DescuentoUtilTest {

	private DescuentoUtil cut;
	private List<Entrada> listaEntradas;
	
	@BeforeEach
	void setUp() throws Exception {
		
		cut = new DescuentoUtil();
		listaEntradas = new ArrayList<Entrada>();
	}

	@Test
	void validarDescuentoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(7, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(7, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.JOVEN)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.TERCERA_EDAD)).count());
	}
	
	@Test
	void validarDescuentoCriticoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(5, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(5, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.JOVEN)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.TERCERA_EDAD)).count());
	}
	
	@Test
	void validarDescuentoSinGrupoTest() {
		
		initEntradas(5, TipoEntrada.JOVEN);
		initEntradas(4, TipoEntrada.NORMAL);
		cut.validarDescuentos(listaEntradas);
		
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.GRUPO)).count());
		assertEquals(5, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.JOVEN)).count());
		assertEquals(4, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.NORMAL)).count());
		assertEquals(0, listaEntradas.stream().filter(n -> n.getDescuento().equals(TipoEntrada.TERCERA_EDAD)).count());
	}

	private void initEntradas(int nEntradas, TipoEntrada tipoEntrada) {
		
		Entrada entrada = new Entrada();

		for (int i = 0; i < nEntradas; i++) {
			entrada.setDescuento(tipoEntrada);
			this.listaEntradas.add(entrada);
		}
	}
}
