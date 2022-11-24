package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

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
        //System.out.println("Agent name: " + getName());
        //super.setup(); super constructor
        addBehaviour(new CompotamientoAgente());
    }


    /*
    Es el ultimo suspiro del agente
     */
    @Override
    protected void takeDown() {
        System.out.println("He Muerto");
    }

    // Sub clase, del comportamiento del agente y esto lo agrego al set up.
    class CompotamientoAgente extends Behaviour {

        @Override
        public void action() {
            System.out.println(getName());
            doDelete(); // doDelete librea el recurso del contenedor
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
