/**
 * File: ContadorSaturado.java
 * -------------------
 * Este fichero define la clase contador saturado de nuestro programa.
 */
package contador;

/**
 * La clase ContadorSaturado supone una mejora en la clase Cuenta, a�adiendo un l�mite.
 * De manera que no se puede superar un valor mayor que el l�mite indicado. 
 * 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class ContadorSaturado extends Contador 
{
	/*Variables privadas*/
	protected int limitecontador = 0;
	
	/**
	 * Crea un nuevo ContadorSaturado inicializado a 0 con l�mite 99.	
	 */
	public ContadorSaturado()
	{
		this(99);
	}
	
	/**
	 * Crea un nuevo ContadorSaturado con un l�mite espec�fico.	
	 * @param limite L�mite m�ximo del contador
	 */
	public ContadorSaturado(int limite)
	{
		super(); //Crea un contador con valor 0
		limitecontador = limite;
	}
	
	/**
	 * Crea un nuevo ContadorSaturado inicializado a cierto vaor y con un l�mite espec�fico.	
	 * @param valorinicio Valor de inicio.
	 * @param limite L�mite m�ximo del contador
	 */
	public ContadorSaturado(int valorinicio, int limite)
	{
		super(valorinicio);
		limitecontador = limite;
	}
	
	/**
	 * Modifica el valor del l�mite.
	 * @param valor Valor del l�mite
	 */
	public void setLimite(int valor) 
	{
		limitecontador = valor;
	}
	
	
	/**
	 * Devuelve el valor del l�mite.
	 * @return limitecontador Valor del l�mite.
	 */
	public int getLimite() 
	{
		return limitecontador;
	}
	
	/**
	 * Incrementa el valor del contador en cierto valor sin salirse del l�mite.
	 * @param incremento Valor a incrementar
	 */
	public void incrementa(int incremento) 
	{
		if ((getCuenta()+ incremento) < limitecontador)
			setCuenta(getCuenta() + incremento) ;
		else
			setCuenta (limitecontador-1);
	}
	
	/**
	 * Modifica el valor del contador sin salirse del l�mite.
	 * @param valor Valor del contador
	 */
	public void setCuenta(int valor) 
	{
		if (valor < limitecontador)
			super.setCuenta(valor);
		else
			super.setCuenta(limitecontador-1);
	}
	
}