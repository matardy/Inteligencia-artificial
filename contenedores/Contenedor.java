package contenedores;

import agentes.AG1;
import agentes.AG2;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {
    private AgentContainer agentContainer;

    public void crearContenedor(){

        jade.core.Runtime runtime = jade.core.Runtime.instance(); // Esto es un proceso como tal para el contenedor
        Profile profile = new ProfileImpl(null, 1099, null); // null : escoge aleatorio
        runtime.createMainContainer(profile);
        agentContainer = runtime.createAgentContainer(profile);
        agregarAgentes();
        // No ICP active que el puerto ya esta siendo usado

    }

    private void agregarAgentes()  { // try catch
        // Matar agentes es liberar recursos
        // El tercer argumento representa el conocimiento del agente, por ahi yo paso conocimeinto
        //  de otros agentes o una red neuronal.
        try{
            agentContainer.createNewAgent("AG1", AG1.class.getName(), null).start();
            agentContainer.createNewAgent("AG2", AG2.class.getName(), null).start();
            // El setup nunca lo llamo explicitamente, con startp lo invoco
        }catch (StaleProxyException e){
            System.out.println(e);
        }

    }

}
