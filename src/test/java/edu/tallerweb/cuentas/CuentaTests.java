package edu.tallerweb.cuentas;

import org.junit.Assert;
import org.junit.Test;

public class CuentaTests {

	@Test
	public void queVerifiqueLaConsigna() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuenta.getSaldo(), 0.0);
	}
	
	@Test
	public void verificarCajaAhorros() {
		CajaAhorros cuenta = new CajaAhorros();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(2000.0);
	
		Assert.assertEquals(
				"al extraer $ 2000 de una cuenta con $ 4000.0 se obtienen $ 2000",
				2000.0, cuenta.getSaldo(), 0.0);
	}
	@Test
	public void queAlaSextaExtraccionCobre6pesos() {
		CajaAhorros cuenta = new CajaAhorros();
		cuenta.depositar(100.0);

		cuenta.extraer(10.0);
		cuenta.extraer(10.0);
		cuenta.extraer(10.0);
		cuenta.extraer(10.0);
		cuenta.extraer(10.0);
		
		Assert.assertEquals(
				"al extraer 5 veces no cobre adicional",
				50.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(10.0);
		cuenta.extraer(10.0);
	
		Assert.assertEquals(
				"al extraer 7 veces cobre 12$ adicionales",
				18.0, cuenta.getSaldo(), 0.0);
	}
	
	@Test
	public void verificarCuentaCorriente() {
		CuentaCorriente cuenta = new CuentaCorriente(150.0);
		
		cuenta.depositar(100.0);

		Assert.assertEquals(
				"al depositar $ 100.0 en una cuenta vacía, tiene $ 100",
				100.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(50.0);
	
		Assert.assertEquals(
				"al extraer $ 50.0 de una cuenta con $ 100.0 se obtienen $ 100.0",
				50.0, cuenta.getSaldo(), 0.0);
	}
	
	@Test
	public void verificarDescubiertoCuentaCorriente() {
		CuentaCorriente cuenta = new CuentaCorriente(150.0);
		
		cuenta.depositar(100.0);

		Assert.assertEquals(
				"al depositar $ 100.0 en una cuenta vacía, tiene $ 100",
				100.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(200.0);
	
		Assert.assertEquals(
				"al extraer $ 150.0 de una cuenta con $ 100.0 debe 100 mas 5 de impuesto",
				0, cuenta.getSaldo(), 0.0);
	}
	
	@Test(expected=CuentaBancariaException.class)
	public void verificarExcepcionSinoCubreDescubierto() {
		CuentaCorriente cuenta = new CuentaCorriente(150.0);
		cuenta.depositar(100.0);
		cuenta.extraer(251.0);
	}

	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);

		cuenta.extraer(4000.0);
	}

}
