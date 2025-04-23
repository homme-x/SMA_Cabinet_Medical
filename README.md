
# 💊 Système Multi-Agent de Gestion d’un Cabinet Médical

Ce projet implémente un système intelligent de gestion des patients dans un cabinet médical à l’aide de la plateforme **JADE** (Java Agent DEvelopment Framework) et d’interfaces graphiques construites avec **JavaFX**.

---

## 📋 Fonctionnalités principales

- ✅ Inscription des patients via une interface JavaFX
- ✅ Envoi des informations à l'agent réceptionniste
- ✅ Affichage dynamique de la file d'attente dans l'interface réceptionniste
- ✅ Possibilité d’accepter un patient en consultation
- ✅ Interaction entre agents patients, réceptionnistes et médecins
- ✅ Transmission de symptômes au médecin via agent

---

## 🧱 Architecture du projet

```
SMA_Cabinet_Medical/src
├── gestion_cabinet_medical_final/  
│   ├── GESTION_CABINET_MEDICAL_FINAL.java  
│   ├── MainContaiber.java 
├── patient/
│   ├── Patient_Container.java
│   ├── Patient_Agent.java
│   ├── InscriptionInterface.java
│   └── ConsultationInterface.java
├── receptionniste/
│   ├── Receptionniste_Container.java
│   ├── Receptionniste_Agent.java
│   └── User.java
├── medecin/
│   ├── Medecin_Container.java
│   ├── Medecin_Agent.java
│   └── MedecinInterface.java
├── expert/
│   └── send_by_doctor_expert
└── README.md
```

---

## 📦 Dépendances requises

Assure-toi d’avoir les bibliothèques suivantes :

### 1. JavaFX (>= v21.0.1)

- Téléchargement : [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Modules à inclure :
  - `javafx.base`
  - `javafx.controls`
  - `javafx.fxml`
  - `javafx.graphics`

### 2. JADE (>= v4.6.0)

- Téléchargement : [https://jade.tilab.com/](https://jade.tilab.com/)
- Ajouter `jade.jar` au classpath

### 3. JSON.simple (v1.1.1)

- Téléchargement : [https://code.google.com/archive/p/json-simple/](https://code.google.com/archive/p/json-simple/)
- Ajouter `json-simple-1.1.1.jar` au classpath

---

## ⚙️ Configuration du projet dans IntelliJ

1. Crée un **projet Java avec JavaFX**.
2. Ajoute les `.jar` externes dans **File > Project Structure > Libraries** :
   - `jade.jar`
   - `json-simple-1.1.1.jar`
   - tous les `.jar` de JavaFX (`/lib`)
3. Classes avec `main()` à exécuter :
   - `patient.Patient_Container`
   - `receptionniste.Receptionniste_Container`
   - `medecin.Medecin_Container`

---

## 🚀 Lancement des interfaces

### Classe MainContainer

```
java gestion_cabinet_medical_final.MainContainer
```

### Interface Patient

```
java patient.Patient_Container
```

### Interface Réceptionniste

```
java receptionniste.Receptionniste_Container
```

### Interface Médecin

```
java medecin.Medecin_Container
```

💡 Lance chaque interface avec votre IDE(Intellij) ainsi que le fichier send_by_doctor_expert.py dans le terminal.

---



