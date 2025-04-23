from experta import *
import json
import time
import os

class Symptom(Fact):
    """Fait : symptôme déclaré par le patient"""
    pass

class DiagnosticExpert(KnowledgeEngine):

    @Rule(Symptom(symptom="mal à la tête"))
    def check_fievre_typhoide(self):
        with open("send_by_doctor_expert.txt", "w") as f:
            f.write(json.dumps({"request": "Avez-vous aussi une fièvre élevée, une perte d'appétit, ou des éruptions cutanées ?"}))

    @Rule(Symptom(response="oui"))
    def reponse_positive(self):
        with open("send_by_doctor_expert.txt", "w") as f:
            f.write(json.dumps({"answer": "Vous pourriez souffrir de la fièvre typhoïde."}))

    @Rule(Symptom(response="non"))
    def reponse_negative(self):
        with open("send_by_doctor_expert.txt", "w") as f:
            f.write(json.dumps({"answer": "Cela pourrait être une autre infection. Merci de consulter un médecin."}))

def read_symptom():
    try:
        with open("send_by_patient.txt", "r") as f:
            data = json.load(f)
            return data
    except Exception as e:
        return {}

if __name__ == "__main__":
    engine = DiagnosticExpert()
    engine.reset()

    print("Système expert démarré.")
    last_content = ""
    while True:
        time.sleep(2)
        data = read_symptom()
        if data:
            if data != last_content:
                last_content = data
                if "pb" in data:
                    engine.declare(Symptom(symptom=data["pb"]))
                elif "response" in data:
                    engine.declare(Symptom(response=data["response"]))
                elif "refresh" in data:
                    engine.reset()
