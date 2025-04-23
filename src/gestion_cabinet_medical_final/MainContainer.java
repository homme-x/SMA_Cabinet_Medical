package gestion_cabinet_medical_final;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();

        // Activer l'interface graphique de JADE
        profile.setParameter(Profile.GUI, "true");

        AgentContainer containerPrincipal = runtime.createMainContainer(profile);

        try {
            containerPrincipal.start();
            System.out.println("MainContainer lancé avec succès !");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
