package principal;

import java.util.ArrayList;

import E_S.Salida;

import comandos.GestorComandos;
import contador.RelojSimple;

import ciclista.Ciclista;
import factoresexternos.MapaCurvas;
import factoresexternos.MapaPendiente;
import factoresexternos.MapaViento;


/**
 * Clase que contiene referencias a todos los objetos "importantes" de la aplicaci�n.
 * Una vez inicializada la clase no de pueden modificar las referencias, ya que no posee m�todos "set"
 * Esta clase hace que no sea necesario pasar tantos par�metros al resto de clases que utilicen objetos comunes.
 * 
 * Emplea el patr�n Singleton modificado, pues al patr�n singleton no suelen pasarse atributos,
 * Lo que se hace es inicializarlo con setInstance, y luego lo recogemos con getInstance().
 *
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class Presentador {

	private static Presentador instancia = null;
	
	private boolean finaliza; //indica si la ejecuci�n debe finalizar o no
	private int numero_iteracion; //numero de iteraciones del bucle principal
	private String log; //String que contiene el log producido durante la ejecuci�n
	private ArrayList<Ciclista> listaciclistas;
	private MapaPendiente pendiente;
	private MapaViento viento;
	private MapaCurvas curvas;
	private GestorComandos gestorcomandos;
	private RelojSimple tiempo;
	
	
	private Presentador(boolean finaliza, int numero_iteracion, String log, ArrayList<Ciclista> listaciclistas, MapaPendiente pendiente, MapaViento viento, MapaCurvas curvas, GestorComandos gestorcomandos, RelojSimple tiempo)
	{
		this.finaliza = finaliza;
		this.numero_iteracion = numero_iteracion;
		this.log = log;
		this.listaciclistas = listaciclistas;
		this.pendiente = pendiente;
		this.viento = viento;
		this.curvas = curvas;
		this.gestorcomandos = gestorcomandos;
		this.tiempo = tiempo;
	}
	
	/**
	 * M�todo de inicializaci�n e instanciaci�n de la clase Presentador.
	 * S�lo se admite una �nica llamada a este m�todo (para el buen funcionamiento de la aplicaci�n).
	 * 
	 * Si se realiza m�s de una llamada a este m�todo, produce una excepci�n "IllegalStateException"
	 */
	public static void setInstance(boolean finaliza, int numero_iteracion, String log, ArrayList<Ciclista> listaciclistas, MapaPendiente pendiente, MapaViento viento, MapaCurvas curvas, GestorComandos gestorcomandos, RelojSimple tiempo) {
		if (instancia == null)
			instancia = new Presentador(finaliza, numero_iteracion, log, listaciclistas, pendiente, viento, curvas, gestorcomandos, tiempo);
		else
			throw new IllegalStateException("Clase Presentador ya instanciada." + " No se pueden modificar las referencias una vez instanciada la clase.");
	}	
	
	/**
	 * Devuelve una instancia �nica de la clase Presentador
	 * @return Instancia de la clase
	 */
	public static Presentador getInstance()
	{
		return instancia;
	}
	
	//---------------------getters and setters--------------------------------
	
	/**
	 * @return the finaliza
	 */
	public boolean isFinaliza() {
		return finaliza;
	}
	
	/**
	 * @param finaliza the finaliza to set
	 */
	public void setFinaliza(boolean finaliza)
	{
		this.finaliza = finaliza;
	}
	
	/**
	 * @return the numero_iteracion
	 */
	public int getNumero_iteracion() {
		return numero_iteracion;
	}
	
	/**
	 * Aumenta el n�mero de iteraci�n en 1
	 */
	public void aumentaNumero_iteracion() {
		this.numero_iteracion++;
	}
	
	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * A�ade una nueva l�nea al log
	 * @param cadena String a concatenar
	 */
	public void nuevoMensajeLog(String cadena) {
		this.log = log.concat(cadena + "\n");
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_LOG() , cadena, false); //false = sin sobreescribir
	}
	
	/**
	 * @return the listaciclistas
	 */
	public ArrayList<Ciclista> getListaciclistas()
	{
		return listaciclistas;
	}

	/**
	 * @return the pendiente
	 */
	public MapaPendiente getPendiente()
	{
		return pendiente;
	}

	/**
	 * @return the viento
	 */
	public MapaViento getViento()
	{
		return viento;
	}

	/**
	 * @return the curvas
	 */
	public MapaCurvas getCurvas()
	{
		return curvas;
	}

	/**
	 * @return the gestorcomandos
	 */
	public GestorComandos getGestorcomandos()
	{
		return gestorcomandos;
	}

	/**
	 * @return the tiempo
	 */
	public RelojSimple getTiempo() {
		return tiempo;
	}

}
