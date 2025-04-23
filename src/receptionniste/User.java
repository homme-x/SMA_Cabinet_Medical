package receptionniste;

public class User {
    private int id;
    private String nom;
    private int age;
    private String sexe;
    private String statut;

    public User(int id, String nom, int age, String sexe) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.sexe = sexe;
        this.statut = "En attente";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
}
