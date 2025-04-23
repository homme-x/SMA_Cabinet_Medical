
package patient;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class Patient_Agent extends GuiAgent {
    private Patient_Container gui;

    @Override
    protected void setup() {
        gui = (Patient_Container) getArguments()[0];
        gui.setPatientAgent(this);

        addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    String exp = message.getSender().getLocalName();
                    String content = message.getContent();

                    GuiEvent guiEvent = null;

                    switch (exp) {
                        case "Receptionniste_Agent":
                            if (message.getPerformative() == ACLMessage.INFORM) {
                                guiEvent = new GuiEvent(this, 2);
                                guiEvent.addParameter(content);
                            }
                            break;
                        case "Medecin_Agent":
                            if (message.getPerformative() == ACLMessage.AGREE) {
                                guiEvent = new GuiEvent(this, 1);
                                guiEvent.addParameter(content);
                            }
                            break;
                    }

                    if (guiEvent != null) gui.viewMessage(guiEvent);
                }
                block();
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("Destruction Agent Patient.");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
