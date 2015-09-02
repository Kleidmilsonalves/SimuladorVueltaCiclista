/**
 * 
 */
package comandos;

import java.util.StringTokenizer;

import principal.Globales;
import principal.Presentador;
import ciclista.Ciclista;
import interfaces.InterfaceComando;

/**
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 *
 */
public class CMD_Pinon implements InterfaceComando {

	// A estas variables las tendr� que dar valor el parser y el configuracontexto
	Ciclista ciclista;
	int numerociclista;
	int numeropinon;

	
	/**
	 * SINTAXIS: "ciclista N cambia pi�on NUMEROP"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[5];
		CMD_Pinon comando = null;

		//guardo las 5 primeras palabras de cadena, que son mis argumentos, ignorando el resto de elementos de la cadena
		int i = 0;
		while(tokens.hasMoreElements() && i < 5)
		{
			stringanalizado[i] = tokens.nextToken();
            i++;
        }
		if (stringanalizado.length == 5)
		{		
			/* Comprobamos que corresponde al comando
			Si no, devolvemos un Comando NULL */
			
			//Primero comprobamos que la sintaxis es correcta
			if(stringanalizado[0].equalsIgnoreCase("ciclista") &&
					ComprobarCasting.isInt(stringanalizado[1]) &&
					stringanalizado[2].equalsIgnoreCase("cambia") &&  
					stringanalizado[3].equalsIgnoreCase("pi�on") &&
					ComprobarCasting.isInt(stringanalizado[4]) )
			{
				//Luego comprobamos que los valores son v�lidos para la aplicaci�n
				int numerociclista = Integer.parseInt(stringanalizado[1]);
				int numeropinon = Integer.parseInt(stringanalizado[4]);
				
				if (numerociclista >= 0 && numerociclista < Globales.getInstance().getNUM_CICLISTAS() &&
						numeropinon >= 0)
				{
					comando = new CMD_Pinon();
					comando.numerociclista = numerociclista;
					comando.numeropinon = numeropinon;				
				}		
				//else, comando = null (por defecto)		
			}
		}
		return comando;
		
	}

	@Override
	public void configuraContexto()
	{
		this.ciclista = Presentador.getInstance().getListaciclistas().get(numerociclista);
	}
	
	@Override
	public void ejecuta()
	{
		ciclista.getBici().setPinonSeleccionado(numeropinon);
	}
	
	@Override
	public String help()
	{
		return "ciclista N cambia pi�on NUMEROP";		
	}
}
