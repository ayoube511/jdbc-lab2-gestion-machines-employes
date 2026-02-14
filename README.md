# JDBC Lab 2 – Gestion des Machines et Employés

**Étudiant** : Moubssite  
**Date** : Février 2026  
**Module** : Atelier JDBC

## Objectifs réalisés
- Architecture en couches : DAO ↔ Service ↔ Présentation (app)
- Connexion JDBC thread-safe via Singleton
- DAO générique CRUD (interface IDao<T>)
- Entités `Employe` et `Machine` avec relation objet (Machine contient Employe)
- PreparedStatement + try-with-resources partout
- Services qui manipulent des objets métier
- Tests console montrant CRUD + suppression en cascade (ON DELETE CASCADE)

## Structure du projet
<img width="819" height="928" alt="image" src="https://github.com/user-attachments/assets/6563edd8-d478-4cef-b48a-55a3044175c1" />
- `util/Connexion.java`          → Singleton de connexion
- `entities/Employe.java` & `Machine.java`
- `dao/IDao.java` + `EmployeDao` & `MachineDao`
- `service/EmployeService` & `MachineService`
- `app/TestEmploye.java` & `TestMachine.java`  → points d’entrée de test

## Prérequis
- Base de données `atelier` créée
- Tables `employe` et `machine` (avec FOREIGN KEY + CASCADE)
- Driver `mysql-connector-j-8.x.x.jar` ajouté dans Project Structure → Libraries

## Comment exécuter
1. Adapter USER / PASS dans `util/Connexion.java`
2. Run `app.TestEmploye.main()` → CRUD simple sur Employe
<img width="1920" height="1080" alt="Capture d’écran (251)" src="https://github.com/user-attachments/assets/963c230e-1534-41d2-99e2-9d84d01ccf19" />
4. Run `app.TestMachine.main()` → CRUD sur Machine + relation + suppression cascade
<img width="1920" height="1080" alt="Capture d’écran (252)" src="https://github.com/user-attachments/assets/cb9c3c61-ffd3-4648-9646-9455e905b7e4" />

## Résultats typiques (TestMachine)
- Création employé + machine
- Affichage liste avec nom employé
- Update machine
- Delete machine
- Delete employé → machines supprimées automatiquement (CASCADE)
