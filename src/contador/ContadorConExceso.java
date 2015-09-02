/**
 * File: ContadorConExceso.java
 * -------------------
 * Este fichero define la clase contador con exceso de nuestro programa.
 * Notas: No se recomienda utilizar el m�todo setLimite una vez se ha empezado a contar.
 */
package contador;

/**
 * La clase ContadorConExceso supone una mejora en la clase Cuenta, excediendo a un segundo contador.
 * De manera que si se alcanza el l�mite indicado, el Contador principal se reinicia 
 * y se incrementa en 1 e segundo contador (el del exceso)
 * . 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class ContadorConExceso extends ContadorSaturado 
{

	
	/*Variables privadas*/
	protected Contador contadorsuperior; //�Por qu� tiene que ser static? 

	/**
	 * Crea un nuevo ContadorConExceso inicializado a 0 con l�mite 99.	
	 */
	/*public ContadorConExceso()
	{
		this(contadorsuperior);
	}
	*/
	
	/**
	 * Crea un nuevo ContadorConExceso con un l�mite espec�fico.	
	 * @param contador Contador al que incrementar� 1 cuando llegue al l�mite
	 */
	public ContadorConExceso(Contador contador)
	{
		super();
		contadorsuperior = contador;
	}
	
	/**
	 * Crea un nuevo ContadorConExceso inicializado a 0 con un l�mite espec�fico.	
	 * @param limite L�mite m�ximo del contador
	 * @param contador Contador al que incrementar� 1 cuando llegue al l�mite
	 */
	public ContadorConExceso(int limite, Contador contador)
	{
		super(limite);
		contadorsuperior = contador;
	}
	
	/**
	 * Modifica el contador superior que incrementar� cuando llegue al l�mite.
	 * @param contador Contador al que incrementar� 1 cuando llegue al l�mite
	 */
	public void setContadorSuperior(Contador contador) 
	{
		contadorsuperior = contador;
	}
	
	
	/**
	 * Devuelve el contador superior que incrementar� cuando llegue al l�mite.
	 * @return contadorsuperior Contador al que incrementar� 1 cuando llegue al l�mite
	 */
	public Contador getContadorSuperior() 
	{
		return contadorsuperior;
	}
	
	/**
	 * Incrementa el valor del contador en 1 y si llega al l�mite incrementa el contador superior y reinicia a 0.
	 */
	public void	incrementa()
	{
		incrementa(1);
	}
	
	/**
	 * Incrementa el valor del contador en cierto valor y si llega al l�mite incrementa el contador superior y reinicia a 0.
	 * @param incremento Valor a incrementar
	 */
	public void incrementa(int incremento) 
	{
		if ((getCuenta()+ incremento) < limitecontador)
		{	
			setCuenta(getCuenta() + incremento) ;
		}
		else
		{
			contadorsuperior.incrementa((getCuenta()+ incremento)/limitecontador);
			setCuenta ((getCuenta()+ incremento)%limitecontador);
		}
	}
}
