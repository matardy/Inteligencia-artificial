package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

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
       // super.takeDown();
    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        // En action controlo todas las acciones incluidas las del metodo done()
        // Lo que esta definido es el behavior, pero el agente lo creo y le paso el comportamiento
        @Override
        public void action() {
            System.out.println(getName());

        }

        @Override
        public boolean done() {
            return true;
            // false es un comportamiento ciclico, de esta forma lo puedo controlar
            // pero si extiendo la clase a CyclicBehaviour y borro el done() ahi es ciclico
            // siempre pero no lo puedo controlar.
        }
    }
}
