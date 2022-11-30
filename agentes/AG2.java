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

public class AG2 extends Agent { // Para que esta clase sea una agente extiendo, el sniffer es un policia

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


    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        // En action controlo todas las acciones incluidas las del metodo done()
        // Lo que esta definido es el behavior, pero el agente lo creo y le paso el comportamiento
        @Override
        public void action() {

            ACLMessage acl = blockingReceive(); // bloqueo el agente y recibo el mensaje de un receptor
            // Solo bloqueo una vez porque tengo una cola de espera
            // Condicional que diga que si recibo trrafico de 3 le responde



            if(Objects.equals(acl.getConversationId(), "codAg3-Ag2")){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Mensajes.enviarMSJ("AG3",
                                            getAgent(),
                                    "Mensaje de AG2 a AG3",
                                    null,
                                    ACLMessage.REQUEST,
                                    "codAg2-Ag3",
                                    false
                );

            }else{
                if(Objects.equals(acl.getConversationId(), "to-AG2")){
                    try {



                        Cliente c = (Cliente) acl.getContentObject();

                        System.out.println("--------------------------------");
                        System.out.println("AGENTE: " + getName());
                        System.out.println(c);
                        System.out.println("Este mensaje ha sido enviado por: " + acl.getSender().getName());;
                        System.out.println("--------------------------------");


                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                }
                if(Objects.equals(acl.getConversationId(), "codAg1-Ag2")){

                    try {

                        Cliente c = (Cliente) acl.getContentObject();

                        System.out.println("--------------------------------");
                        System.out.println("AGENTE: " + getName());
                        System.out.println(c);
                        System.out.println("Este mensaje ha sido enviado por: " + acl.getSender().getName());;
                        System.out.println("--------------------------------");

                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                }

            }





            //doDelete();

        }

        @Override
        public boolean done() {
            return false;
            // false es un comportamiento ciclico, de esta forma lo puedo controlar
            // pero si extiendo la clase a CyclicBehaviour y borro el done() ahi es ciclico
            // siempre pero no lo puedo controlar.
        }
    }
}
