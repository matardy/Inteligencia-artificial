package agentes;

import agentes.comportamientos.C1;
import contenedores.Contenedor;
import jade.core.Agent;


public class AgSon extends Agent {

    @Override
    protected void setup() {
        System.out.println("Ha nacido un nuevo hijo:, " + getName());
        addBehaviour(new C1());

    }


    @Override
    protected void takeDown() {
        System.out.println("Soy el agente hijo " + getName() + " y he muerto.");
        Contenedor.crearHijo("AgSon");

    }

}