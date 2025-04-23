package gestion_cabinet_medical_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GESTION_CABINET_MEDICAL_FINAL {
    private ObservableList<String> ListConversationPatientMedecin;

    public GESTION_CABINET_MEDICAL_FINAL() {
        ListConversationPatientMedecin = FXCollections.observableArrayList();
    }

    public ObservableList<String> getListConversationPatientMedecin() {
        return ListConversationPatientMedecin;
    }

    public void addItem(String item) {
        ListConversationPatientMedecin.add(item);
    }
}
