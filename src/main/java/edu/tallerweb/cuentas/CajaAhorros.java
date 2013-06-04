package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros extends AbstractCuenta{

	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	private Integer cantidadExtracciones = 0;
	public void depositar(final Double monto) {
		super.depositar(monto);
	}

	/**
	 * Se cobran $6 adicionales por cada extracción luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		super.extraer(monto);
		cantidadExtracciones ++;
		if( cantidadExtracciones > 5){
			super.extraer(6.0);			
		}
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return super.saldo;
	}
}
