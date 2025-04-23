package medecin;

import javafx.beans.property.*;

public class User {
    private SimpleIntegerProperty numero;
    private SimpleStringProperty nom;
    private SimpleIntegerProperty age;
    private SimpleStringProperty sexe;

    public User(int numero, String nom, int age, String sexe) {
        this.numero = new SimpleIntegerProperty(numero);
        this.nom = new SimpleStringProperty(nom);
        this.age = new SimpleIntegerProperty(age);
        this.sexe = new SimpleStringProperty(sexe);
    }

    public int getNumero() { return numero.get(); }
    public void setNumero(int numero) { this.numero.set(numero); }
    public IntegerProperty numeroProperty() { return numero; }

    public String getNom() { return nom.get(); }
    public void setNom(String nom) { this.nom.set(nom); }
    public StringProperty nomProperty() { return nom; }

    public int getAge() { return age.get(); }
    public void setAge(int age) { this.age.set(age); }
    public IntegerProperty ageProperty() { return age; }

    public String getSexe() { return sexe.get(); }
    public void setSexe(String sexe) { this.sexe.set(sexe); }
    public StringProperty sexeProperty() { return sexe; }
}
