package medecin;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class Medecin_Agent extends GuiAgent {
    private Medecin_Container gui;
    public static boolean fichierModifierMedecin = false;

    @Override
    protected void setup() {
        gui = (Medecin_Container) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    String content = message.getContent();
                    GuiEvent guiEvent = new GuiEvent(this, 1);
                    guiEvent.addParameter(content);
                    gui.viewMessage(guiEvent);

                    medecinFichier fichier = new medecinFichier();
                    String formatMessage = gui.format_message(content);
                    fichier.write(gui.fichierEcriture, formatMessage);

                    String reponseExperta = fichier.read(gui.fichierLecture);
                    ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                    aclMessage.setContent(reponseExperta);
                    aclMessage.addReceiver(new AID("Patient_Agent", AID.ISLOCALNAME));
                    send(aclMessage);

                    GuiEvent guiEvent2 = new GuiEvent(this, 2);
                    guiEvent2.addParameter(reponseExperta);
                    gui.viewMessage(guiEvent2);
                }
                block();
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("Agent médecin détruit.");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
