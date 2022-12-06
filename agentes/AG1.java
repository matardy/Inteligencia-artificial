package agentes;

import contenedores.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import model.Cliente;

import java.util.concurrent.TimeUnit;


/*
Estructura de una agente, set up, comportamiento (varios tipos de comportamientos),
el done() hace que un comportamiento secuencial sea ciclico.
 */

public class AG1 extends Agent { // Para que esta clase sea una agente extiendo, el sniffer es un policia

    /*
    Este metodo es la configuracion inicial del agente. Aqui programo al agente
     */
    @Override
    protected void setup() {
        // Añado el comportamiento
        addBehaviour(new CompotamientoAgente());
    }


    /*
    Es el ultimo suspiro del agente
     */
    @Override
    protected void takeDown() {

        System.out.println("Soy "+getName()+ " y he muerto.");
        // Muere AG1 y crea un Hijo.
        Contenedor.crearHijo("AgSon");

    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        @Override
        public void action() {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Mensajes.enviarMSJ(
                    "AG2",
                    getAgent(),
                    null,
                    new Cliente("Ricardo","Teran", "EPN", "5123123", 55),
                    ACLMessage.INFORM,
                    "codAg1-Ag2",
                    true
            );



            // Investigar mensajes ACL, Inform es que envio un mensaje pero no espero respuesta.
            doDelete(); // doDelete librea el recurso del contenedor
        }

        @Override
        public boolean done() {
            return true;

        }
    }
}
