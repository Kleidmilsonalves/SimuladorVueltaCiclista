/**
 * File: Princpal.java
 * -------------------
 * Este fichero define la clase principal de nuestro programa.
 */
package principal;

import java.util.ArrayList;

import comandos.GestorComandos;

import ciclista.*;
import contador.*;
import controlador.ControladorAplicacion;
import controlador.ControladorCiclistas;
import E_S.Entrada;
import E_S.Salida;
import bicicleta.*;
import interfaces.*;
import factoresexternos.MapaCurvas;
import factoresexternos.MapaViento;
import factoresexternos.MapaPendiente;
import fecha.Fecha;
import vista.*;

/**
 * La clase Principal contiene los m�todos principales de la ejecuci�n del programa y el main principal.
 * 
 * La secuencia de ejecuci�n se organiza en: 
 * 		inicializarGlobales(), new Principal(), inicia(), ejecuta(), y finaliza()
 * 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class Principal 
{
	private Globales globales = Globales.getInstance();
	
		//Declaracion de los ArraysList para los objetos que se ejecutan y muestran.
	ArrayList<InterfaceObjetoEjecutable> listaejecutable;
	ArrayList<InterfaceObjetoMostrable> listamostrable;
	
	private ArrayList<Ciclista> listaciclistas; //ArrayList con todos los ciclistas
	
	Presentador presentador;  //Presentador, que conoce los distintos objetos, para pasarselos a los comandos
	
	static GestorComandos gestorcomandos;
	
	InterfaceVistaCiclistas ventana_ciclistas;
	VentanaDatos ventana_datos; //TODO esto ser�a mejor si fuera la interface. InterfaceVista ventana_datos;
	ControladorAplicacion control_ventana_datos;
	
	/**
	 * Inicializa los datos principales de Globales (FRECUENCIA_EJECUCION, MAX_CICLISTAS, MAX_FUERZA_CICLISTA, KILOMETROS_CARRERA, CADENCIA_MAX... etc)
	 * Se realiza mediante fichero, si lo encuentra, o desde interfaz gr�fica mediante ventanas JOptionPane, en caso contrario
	 */
	private static void inicializarGlobales()
	{
		String datosiniciales = new Entrada().leerDeFichero(Globales.getInstance().getRUTA_ENTRADA_DATOS());
		
		if (datosiniciales == null || datosiniciales.isEmpty()) //tambien habr� que comprobar que los datos ingresados son correctos, con un parser
		{
			int numerociclistas = VentanasEmergentes.getInt("el n�mero de ciclistas", 1, Globales.getInstance().getMAX_NUM_CICLISTAS());
			Globales.getInstance().setNUM_CICLISTAS(numerociclistas);
			
			int frecuencia = VentanasEmergentes.getInt("la unidad m�nima de tiempo", 1, 1000);
			Globales.getInstance().setFRECUENCIA_EJECUCION(frecuencia);	
		}
		else
		{
			//aqu� hay que cargar en las Globales los datos que se introducen por fichero
		}
	}
	
	
	/**
	 * Inicializa el programa.
	 */
	public void inicia()
	{
		//Se crean los objetos de la interface. No se debe alterar el orden al insertarlos en la listaejecutable
		
		listaciclistas = new ArrayList <Ciclista>();
		listaejecutable = new ArrayList <InterfaceObjetoEjecutable>();
		listamostrable = new ArrayList <InterfaceObjetoMostrable>();
		
		RelojSimple reloj = new RelojSimple(); //el reloj es com�n para todos los ciclistas
		listaejecutable.add(reloj);
		listamostrable.add(reloj);
		
		MapaPendiente pendiente = new MapaPendiente(listaciclistas);
		listaejecutable.add(pendiente);
		
		MapaViento viento = new MapaViento(listaciclistas);
		listaejecutable.add(viento);
		
		MapaCurvas curvas = new MapaCurvas(listaciclistas);
		listaejecutable.add(curvas);
		
		
		// Inicia los ciclistas -----------------------------------------------------------------------------
		String nombresciclistas[] = { "Miguel Indurain", "Lance Armstrong", "Alberto Contador", "Alejandro Valverde", "�scar Freyre", "Jacques Anquetil", "Eddy Merckx", "Bernard Hinault", "Greg Lemond", "Fausto Coppi" };
				
		for (int i=0; i< globales.getNUM_CICLISTAS(); i++)
		{
			Bicicleta bicicleta = new Bicicleta();
			Ciclista ciclista = new Ciclista(i, bicicleta);
			ciclista.setNombre(nombresciclistas[i%10]);
			ciclista.setReloj(reloj);
			listaciclistas.add(ciclista);
			listaejecutable.add(ciclista);
			listamostrable.add(ciclista);			
		}
		
		gestorcomandos = new GestorComandos();
			
		
		//Inicializa Presentador ----------------------------------------------------------------------------
		Presentador.setInstance(false, 0, new String(), listaciclistas, pendiente, viento, curvas, gestorcomandos, reloj);
		presentador = Presentador.getInstance();			
		
		
		//Inicia ventanas -----------------------------------------------------------------------------------
		ventana_ciclistas = new VentanaCiclistas(listaciclistas, pendiente, curvas);

		int i=0;
		for(Ciclista c: listaciclistas)
		{
			ControladorCiclistas control = new ControladorCiclistas(ventana_ciclistas, gestorcomandos, i, c);
			ventana_ciclistas.setControlador(i, control);
			i++;
		}
		ventana_ciclistas.arranca();
		
		ventana_datos = new VentanaDatos(listaciclistas.get(0), viento);
		control_ventana_datos = new ControladorAplicacion(ventana_datos);
		ventana_datos.arranca();		
		ventana_datos.setControlador(control_ventana_datos);
		
		//Localiza y formatea (borra el contenido) de los ficheros de salida -------------------------------
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_LOG() , "Registro de eventos: ", true);
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_SALIDA() , "", true);
		
	}
	
	/**
	 * M�todo de ejecuci�n del programa.
	 * Contiene el bucle principal de la aplicaci�n.
	 */	
	public void ejecuta()
	{
		presentador.aumentaNumero_iteracion();
		
		VentanasEmergentes.inicio();
		
		Presentador.getInstance().nuevoMensajeLog("Comienzo de aplicaci�n a las: " + new Fecha().getHoraActual());
		
		while (!presentador.isFinaliza())
		{	
			//el gestorcomandos se ejecuta aunque hayamos "paralizado" la aplicaci�n
			gestorcomandos.ejecuta(); //TODO comprobar si el gestor de comandos quita mucho tiempo al programa. (casi un 90%)
			
			if (!control_ventana_datos.getDetenido()) //la parada no se hace con un pause() por que queremos que el resto de controles se sigan ejecutando.
			{		
					//Se debe "desactivar" (comentar) la visualizaci�n para que la ejecuci�n sea m�s fluida.
				//System.out.println("iter: " + presentador.getNumero_iteracion());
				//new Salida().escribirEnFichero(Globales.getInstance().getRUTA_SALIDA() , "iter: " + presentador.getNumero_iteracion(), false);
				
				//Ejecuta todos los objetos de la listaejecutable (en orden de inserci�n)
				for (InterfaceObjetoEjecutable e: listaejecutable)
				{
					e.ejecuta();
				}
				
				//Muestra todos los objetos de la listaejecutable (en orden de inserci�n)
				for (InterfaceObjetoMostrable e: listamostrable)
				{
						//Se debe "desactivar la visualizaci�n para que la ejecuci�n sea m�s fluida.
					//e.muestra();
				}
				
				presentador.aumentaNumero_iteracion();
			}

			//las ventanas se actualizan aunque hayamos "paralizado" la aplicaci�n
			ventana_datos.actualizaVista();
			ventana_ciclistas.actualizaVista();
		}		
	}
	
	
	/**
	 * Acciones que se deben realizar al final del programa.
	 */	
	public void finaliza()
	{	
		
	}
	


	/**
	 * M�todo principal de ejecuci�n del programa.
	 * @param args
	 */
	public static void main(String[] args)
	{
		inicializarGlobales();		
		Principal pp = new Principal();
		pp.inicia();
		pp.ejecuta();
		pp.finaliza();
	}

}