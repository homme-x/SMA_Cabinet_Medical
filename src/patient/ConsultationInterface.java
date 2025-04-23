package patient;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsultationInterface extends Application {
    public TextArea textMessage = new TextArea();
    public Button boutonEnvoyer = new Button("Envoyer");
    public Button boutonPartir = new Button("Partir");
    public Label texteMedecin = new Label("Réponse du médecin ici...");

    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10,
                new Label("Discussion avec le médecin :"),
                texteMedecin,
                new Label("Votre message :"), textMessage,
                boutonEnvoyer, boutonPartir
        );

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.setTitle("Consultation Patient");
        stage.show();
    }
}
