package patient;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

public class Patient_Container extends Application {
    private Patient_Agent agent;
    private InscriptionInterface interfaceInscription;
    private ConsultationInterface interfaceConsultation;
    private boolean verrou_inforFormulaire = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();

        interfaceInscription = new InscriptionInterface();
        interfaceInscription.start(stage);

        interfaceInscription.boutonEnregistrer.setOnAction(e -> {
            verrou_inforFormulaire = true;

            JSONObject patientJson = new JSONObject();
            patientJson.put("nom", interfaceInscription.nomField.getText());
            patientJson.put("prenom", interfaceInscription.prenomField.getText());
            patientJson.put("age", interfaceInscription.ageField.getText());
            patientJson.put("sexe", interfaceInscription.sexeF.isSelected() ? "F" : "M");
            patientJson.put("adresse", interfaceInscription.adresseField.getText());
            patientJson.put("telephone", interfaceInscription.telephoneField.getText());

            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setContent(patientJson.toJSONString());
            message.addReceiver(new AID("Receptionniste_Agent", AID.ISLOCALNAME));
            agent.send(message);

            interfaceInscription.labelEtat.setText("Informations envoyées à la réceptionniste.");
        });

        interfaceInscription.boutonConsulter.setOnAction(e -> {
            if (verrou_inforFormulaire) {
                interfaceConsultation = new ConsultationInterface();
                interfaceConsultation.start(new Stage());

                interfaceConsultation.boutonEnvoyer.setOnAction(ev -> {
                    JSONObject obj = new JSONObject();
                    obj.put("pb", interfaceConsultation.textMessage.getText());

                    ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                    aclMessage.setContent(obj.toJSONString());
                    aclMessage.addReceiver(new AID("Medecin_Agent", AID.ISLOCALNAME));
                    agent.send(aclMessage);
                });

                interfaceConsultation.boutonPartir.setOnAction(ev -> {
                    ((Stage) interfaceConsultation.boutonPartir.getScene().getWindow()).close();
                });
            } else {
                interfaceInscription.labelEtat.setText("Veuillez d'abord enregistrer vos informations !");
            }
        });

    }

    public void startContainer() {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer container = rt.createAgentContainer(profile);
        try {
            AgentController ac = container.createNewAgent("Patient_Agent", "patient.Patient_Agent", new Object[]{this});
            ac.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewMessage(GuiEvent guiEvent) {
        int code = guiEvent.getType();
        String msg = (String) guiEvent.getParameter(0);
        if (code == 1 && interfaceConsultation != null) {
            interfaceConsultation.texteMedecin.setText(msg);
        } else if (code == 2) {
            interfaceInscription.labelEtat.setText(msg);
        }
    }

    public void setPatientAgent(Patient_Agent agent) {
        this.agent = agent;
    }
}
