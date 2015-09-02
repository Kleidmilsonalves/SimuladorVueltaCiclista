package interfaces;

/**
 * La interfaz InterfaceComando contiene los m�todos que tiene que contener un comando.
 * 
 * Para usar comandos en nuestra aplicaci�n empleamos el patr�n Command, en el que cada comando
 * es responsable de reconocerse (parse), asignar valores a sus variables (configuraContexto), 
 * ejecutarse (ejecuta), y mostrar un mensaje de ayuda (help).
 * 
 * Todo comando de la aplicaci�n debe implementar esta interfaz para poder ser ejecutado.
 * 
 * @author Jes�s Mart�nez Dotor & Mar�a Picado �lvarez
 */
public interface InterfaceComando
{
	public InterfaceComando parse(String cadena);
	public void configuraContexto();
	public void ejecuta();
	public String help();
}
