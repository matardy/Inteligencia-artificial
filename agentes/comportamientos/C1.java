package agentes.comportamientos;

import agentes.AgSon;
import agentes.Mensajes;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.persistence.DeleteAgent;
import jade.lang.acl.ACLMessage;
import model.Cliente;

import java.util.concurrent.TimeUnit;

public class C1 extends Behaviour{

    @Override
    public void action() {
//            Mensajes.enviarMSJ("AG2", getAgent(),"Hola Agente dos", null,
//                    ACLMessage.INFORM,"codAg1-Ag2",false);
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
                "to-AG2",
                true
        );

        this.getAgent().doDelete();
        // Investigar mensajes ACL, Inform es que envio un mensaje pero no espero respuesta.
        //doDelete(); // doDelete librea el recurso del contenedor
    }

    @Override
    public boolean done() {
        return true;
        // false es un comportamiento ciclico, de esta forma lo puedo controlar
        // pero si extiendo la clase a CyclicBehaviour y borro el done() ahi es ciclico
        // siempre pero no lo puedo controlar.
    }

}

