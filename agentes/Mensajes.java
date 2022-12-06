package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
// Investigar sobre objetos serializablers
public class Mensajes {
    // statico para no crear objetos
    public static void enviarMSJ(String receptorAlias, Agent emisor, String contenido, Serializable contenidoObject,
                                 int tipo, String idConversacion, boolean tipoContenidoObject){
        // Necesito construir un mensaje con una estrucutra acl, ahi configuro
        // el emisor, el receptor y todo.
        ACLMessage acl = new ACLMessage(tipo); // los tipos de mensajes salen de ACLMEssage. pero eso
        // Lo manda como parametro para poder escoger el tipo de mensaje. ;

        if(tipoContenidoObject){
            try {
                acl.setContentObject(contenidoObject);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            acl.setContent(contenido);

        }

        acl.setConversationId(idConversacion);
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        acl.setSender(emisor.getAID()); // Sender me pide un agent ID, de un objeto agente como tal

        AID aid = new AID();
        aid.setLocalName(receptorAlias); //  busca el aid en el contendero en base al alias
        acl.addReceiver(aid);

        emisor.send(acl);

    }
}
