/**
 * 
 */
package principal;

import javax.swing.JOptionPane;

/**
 * Clase que contiene m�todos para mostrar ventanas emergentes en la aplicaci�n.
 * 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class VentanasEmergentes {

	
	/**
	 * Obtiene un valor entero entre 2 l�mites, m�ximo y m�nimo, incluidos ambos
	 * 
	 * @param nombredato Nombre del dato que se est� intentando obtener (saldr� en el mensaje emergente)
	 * @param min m�nimo n�mero admitido
	 * @param max m�ximo n�mero admitido
	 * @return N�mero v�lido introducido
	 */
	public static int getInt(String nombredato, int min, int max)
	{
		int numero = 0;
		
		boolean formatocorrecto = false;
		
		while(!formatocorrecto)
		{	
			String numero_string = JOptionPane.showInputDialog("Ingrese un numero correcto para " + nombredato + ", \n entre " + min + " y " + max + ": ");
			
			try //se intenta convertir a Int
	        {
				numero = Integer.parseInt(numero_string);
				
	            if(numero >= min && numero <= max) //se comprueba que est� entre los l�mites
	            {
	            	formatocorrecto = true;
	            }
	            else
	            {
	            	formatocorrecto = false;
	            }            	
	        }
	        catch ( NumberFormatException e )
	        {
	        	formatocorrecto = false;
	        }				
		}
		
		return numero;
	}

	/**
	 * Obtiene un valor entero entre el m�ximo y m�mino valores de Int, incluidos ambos
	 * Sobrecarga, haciendo uso de el m�todo getInt(String nombredato, int min, int max)
	 * 
	 * @param nombredato Nombre del dato que se est� intentando obtener (saldr� en el mensaje emergente)
	 * @return N�mero v�lido introducido
	 */
	public static int getInt(String nombredato)
	{
		return getInt(nombredato, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Muestra un mensaje al inicio de la aplicaci�n
	 */
	public static void inicio()
	{
		JOptionPane.showMessageDialog(null, "Para modificar los factores externos, Puede introducir comandos antes de comenzar.\n" +
				"Cuando este preparado pulse \"Start\".");
	}
	
	/**
	 * Imprime un mensaje para cuando un ciclista llega a meta
	 * @param nombreciclista Nombre del ciclista
	 * @param numerociclista N�mero del ciclista
	 */
	public static void ciclistaGanador(String nombreciclista, int numerociclista)
	{
		JOptionPane.showMessageDialog(null, "El ciclista n�mero " + Integer.toString(numerociclista) + ", " + nombreciclista + ", ha llegado a la META." );
	}
}
