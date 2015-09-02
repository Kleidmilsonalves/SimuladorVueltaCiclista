/**
 * 
 */
package interfaces;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorAplicacion;

/**
 * La interfaz InterfaceVistaAuxiliar contiene los m�todos y constantes de la parte de Vista del MVC.
 * Estos son todos los m�todos con lso que interactuar� el controlador.
 * 
 * Al principio los setActionCommand se hac�an directamente con un string, pero despu�s se vio
 * que era mejor hacerlo con unas constantes en la interfaz, ya que como la clase la implementa, 
 * puede acceder a ellas mediate el autocompletado de Eclipse y no da lugar a equivocaciones.
 * 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 */
public interface InterfaceVistaAuxiliar {
	public void arranca(); // organiza los elementos y los visualiza
	public void actualizaVista(); //actualiza los elementos de la vista
	public void setControlador(ControladorAplicacion c);
	
	public void escribeDatos(String s, JTextField nomb_txt);
	public void escribeDatos(String s, JTextArea nomb_txt);
	public String leeDatos(JTextField nomb_txt);
	
	// Constantes que definen las posibles operaciones
	static final String ENVIAR = "Enviar";
	static final String MAS_LENTO = "<<";
	static final String START_STOP = "Start/Stop";
	static final String MAS_RAPIDO = ">>";
}
