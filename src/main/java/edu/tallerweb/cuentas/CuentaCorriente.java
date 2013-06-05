package edu.tallerweb.cuentas;

/**
 * La más compleja de las cuentas, ésta permite establecer una
 * cantidad de dinero a girar en descubierto. Es por ello que
 * cada vez que se desee extraer dinero, no sólo se considera
 * el que se posee, sino el límite adicional que el banco
 * estará brindando.
 *
 * Por supuesto esto no es gratis, ya que el banco nos cobrará
 * un 5% como comisión sobre todo el monto en descubierto
 * consumido en la operación.
 *
 * Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos
 * retirar $ 200 (con un descubierto de $ 150), podremos hacerlo.
 * Pasaremos a deberle al banco $ 105 en total: los $ 100 que
 * nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente extends AbstractCuenta{

	/**
	 * Toda cuenta corriente se inicia con un límite total
	 * para el descubierto.
	 * @param descubiertoTotal
	 */
	private Double descubierto;
	double descubiertoTotal;
	
	public CuentaCorriente(final Double descubiertoTotal) {
		this.descubiertoTotal = descubiertoTotal; 
		this.descubierto = descubiertoTotal; 
	}
	
	/**
	 * Todo depósito deberá cubrir primero el descubierto,
	 * si lo hubiera, y luego contar para el saldo de la
	 * cuenta.
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		if ( this.descubierto < this.descubiertoTotal ){
			Double saldoNegativo = ( this.descubiertoTotal - this.descubierto );
			if ( monto >= saldoNegativo ){
				super.depositar( monto - saldoNegativo );
				this.descubierto = this.descubiertoTotal;
			} else { this.descubierto += monto;}
		}else super.depositar(monto);
		}

	/**
	 * Se cobrará el 5% de comisión sobre el monto girado
	 * en descubierto.
	 * Por supuesto, no puede extraerse más que el total
	 * de la cuenta, más el descubierto (comisión incluída)
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if( monto > this.getSaldo() ) {
			Double montoConImpuesto = ( monto - this.getSaldo() ) * 1.05 ;
			if ( montoConImpuesto <= this.descubierto ){
				this.descubierto -= montoConImpuesto;
				super.saldo = 0.0;
				}
			else throw new CuentaBancariaException("No cubre descubierto");
		}
		else { super.extraer(monto); }
		}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return super.saldo;
	}
	
	/**
	 * Permite saber el saldo en descubierto
	 * @return el descubierto de la cuenta
	 */
	public Double getDescubierto() {
		return this.descubierto;
	}

}
