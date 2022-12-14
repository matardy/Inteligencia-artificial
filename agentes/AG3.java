package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import model.Cliente;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/*
Estructura de una agente, set up, comportamiento (varios tipos de comportamientos),
el done() hace que un comportamiento secuencial sea ciclico.
 */

public class AG3 extends Agent { // Para que esta clase sea una agente extiendo, el sniffer es un policia


    /*
    Este metodo es la configuracion inicial del agente. Aqui programo al agente
     */
    @Override
    protected void setup() {
        //System.out.println("Agent name: " + getName());
        //super.setup(); super constructor
        addBehaviour(new CompotamientoAgente());
    }


    /*
    Es el ultimo suspiro del agente
     */
    @Override
    protected void takeDown() {
        System.out.println("Soy "+getName()+ " y he muerto.");
        //System.out.println("Soy el agente dos y he muerto");
    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        // En action controlo todas las acciones incluidas las del metodo done()
        // Lo que esta definido es el behavior, pero el agente lo creo y le paso el comportamiento
        @Override
        public void action() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Mensajes.enviarMSJ("AG2",
            getAgent(),
            null,
            new Cliente("Matardy","Teran", "EPN", "5123123", 55),
            ACLMessage.REQUEST,
            "codAg3-Ag2",
            true
            );

            ACLMessage acl = blockingReceive();
            if(Objects.equals(acl.getConversationId(), "codAg2-Ag3")){

                System.out.println("--------------------------------");
                System.out.println("AGENTE: " + getName());
                System.out.println(acl.getContent());
                System.out.println("Este mensaje ha sido enviado por: " + acl.getSender().getName());;
                System.out.println("--------------------------------");
            }else{
                System.out.println("Algo extra??o pas??");
            }
        }

        @Override
        public boolean done() {
            return false;

        }
    }
}
