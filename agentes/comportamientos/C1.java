package agentes.comportamientos;

import agentes.Mensajes;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import model.Cliente;
import java.util.concurrent.TimeUnit;

/*
Comportamiento: Agente envia un mensaje a AG2 con id: to-AG2 luego muere.
 */
public class C1 extends Behaviour{
    @Override
    public void action() {

        // Espera 3 segundos para ver la ejecución
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Envio de mensaje
        Mensajes.enviarMSJ(
                "AG2",
                getAgent(),
                null,
                new Cliente("Ricardo","Teran", "EPN", "5123123", 55),
                ACLMessage.INFORM,
                "to-AG2",
                true
        );

        // Libero recursos del contenedor = Mato al agente
        this.getAgent().doDelete();

    }

    @Override
    public boolean done() {
        return false;
        // Con un SimpleBehavior tengo el metodo done() en donde si:
            // False es un comportamiento ciclico.
            // True es un comportamiento lineal
        // Pero si extiendo la clase a CyclicBehaviour y borro el done() ahi es ciclico
        // siempre pero no lo puedo controlar.
    }

}

