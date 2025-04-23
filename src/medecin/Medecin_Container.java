package medecin;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import jade.gui.GuiEvent;
import javafx.application.Application;
import javafx.stage.Stage;

public class Medecin_Container extends Application {
    public MedecinInterface medecin_interface;
    public String fichierLecture = "send_by_doctor_expert.txt";
    public String fichierEcriture = "SendByPatient.txt";
    private ContainerController container;
    private Medecin_Agent medecin_agent;

    public static void main(String[] args) {
        launch(args);
    }

    public void startContainer() {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        container = rt.createAgentContainer(profile);
        try {
            AgentController ac = container.createNewAgent("Medecin_Agent", "medecin.Medecin_Agent", new Object[]{this});
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        startContainer();
        medecin_interface = new MedecinInterface();
        medecin_interface.start(primaryStage);

        medecin_interface.Benvoyer.setOnAction(e -> {
            String contenu = medecin_interface.textAreaSaisie.getText();
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.setContent(contenu);
            aclMessage.addReceiver(new AID("Patient_Agent", AID.ISLOCALNAME));
            medecin_agent.send(aclMessage);
            medecin_interface.items.add("Vous: " + contenu);
            medecin_interface.textAreaSaisie.clear();
        });
    }

    public void viewMessage(GuiEvent guiEvent) {
        String msg = (String) guiEvent.getParameter(0);
        medecin_interface.items.add(msg);
    }

    public String format_message(String message) {
        return "{\"pb\":\"" + message + "\"}";
    }
}
