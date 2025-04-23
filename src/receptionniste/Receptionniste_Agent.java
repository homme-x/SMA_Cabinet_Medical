package receptionniste;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.application.Platform;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Receptionniste_Agent extends Agent {
    private Receptionniste_Container gui;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0 && args[0] instanceof Receptionniste_Container) {
            gui = (Receptionniste_Container) args[0];
            gui.setAgent(this);
        }

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null && msg.getPerformative() == ACLMessage.INFORM) {
                    try {
                        JSONObject json = (JSONObject) new JSONParser().parse(msg.getContent());
                        System.out.println("Message reçu par la réceptionniste : " + json);
                        String nom = (String) json.get("nom");
                        String ageStr = (String) json.get("age");
                        String sexe = (String) json.get("sexe");

                        if (nom != null && ageStr != null && sexe != null && !nom.isEmpty() && !ageStr.isEmpty() && !sexe.isEmpty()) {
                            int age = Integer.parseInt(ageStr);
                            User user = new User(1, nom, age, sexe);
                            Platform.runLater(() -> gui.addUserToTable(user));
                        } else {
                            System.err.println("Champ manquant dans le JSON du patient.");
                        }
                    } catch (ParseException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
