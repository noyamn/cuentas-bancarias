package edu.tallerweb.cuentas;

/**
 * Modela el concepto de Cuenta. Esta clase abstracta sirve
 * como base para una posible jerarquía (si fuese necesaria)
 *
 * Es probable que la tarea se facilite otorgando una imple-
 * mentación a los métodos proporcionados.
 */
public abstract class AbstractCuenta {

	/**
	 * Agrega a la cuenta el monto determinado
	 * @param monto a depositar
	 */
	protected Double saldo = 0.0;
	public void depositar(final Double monto) {
		if ( monto >= 0) {
			this.saldo += monto; }
		else { throw new CuentaBancariaException("El monto debe ser positivo"); }
		}

	/**
	 * Retira de la cuenta el monto determinado
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if ( monto > 0 ){
			if( this.saldo >= monto) {
				this.saldo -= monto; }
			else throw new CuentaBancariaException("No tiene saldo suficiente");
		}
		else { throw new CuentaBancariaException("El monto debe ser positivo"); }
	}
}
