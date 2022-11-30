package contenedores;

import agentes.AG1;
import agentes.AG2;
import agentes.AG3;
import agentes.AgSon;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Contenedor {
    private static int sonNumber = 0;
    private static ContainerController agentContainer; // Debo convertir el agentContainer en static
    // para poder modificarlo sin declarar un objeto
    //private AgentContainer agentContainer;

    public void crearContenedor(){

        jade.core.Runtime runtime = jade.core.Runtime.instance(); // Esto es un proceso como tal para el contenedor
        Profile profile = new ProfileImpl(null, 1099, null); // null : escoge aleatorio
        runtime.createMainContainer(profile);
        agentContainer = runtime.createAgentContainer(profile);
        agregarAgentes();
        // No ICP active que el puerto ya esta siendo usado
    }
    // Crear un behaviour comun para enviar mensaje
    // En el uno le paso el contendero en si mismo
    public static void crearHijo(String alias){
        sonNumber++;
        try {
            agentContainer.createNewAgent(alias+sonNumber, AgSon.class.getName(),null).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
    // Que el agente 1 se muera despues de morir nazca un nuevo hijo y mande mensjae al agente 2 , en el takeDown creo al hijo
    private void agregarAgentes()  { // try catch
        // Matar agentes es liberar recursos
        // El tercer argumento representa el conocimiento del agente, por ahi yo paso conocimeinto
        //  de otros agentes o una red neuronal.
        try{
            agentContainer.createNewAgent("AG2", AG2.class.getName(), null).start();
            agentContainer.createNewAgent("AG3", AG3.class.getName(), null).start();
            agentContainer.createNewAgent("AG1", AG1.class.getName(), null).start();


            // El setup nunca lo llamo explicitamente, con startp lo invoco
        }catch (StaleProxyException e){
            System.out.println(e);
        }

    }


}
