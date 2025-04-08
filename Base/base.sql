-- Active: 1744091031836@@172.80.237.53@3306@db_s2_ETU003263
drop DATABASE gestionwebdynamique;
create DATABASE GestionWebDynamique;
use db_s2_ETU003263;
create Table GestionWebDynamique_prevision(
    id int PRIMARY key AUTO_INCREMENT,
    libelle VARCHAR(20),
    montant DOUBLE,
    DateDebut Date,
    DateFin Date
);
CREATE Table GestionWebDynamique_depense(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_prevision INT, 
    montant DOUBLE,
    date Date,
    Foreign Key (id_prevision) REFERENCES GestionWebDynamique_prevision(id)
);
drop table utilisateur;
CREATE Table GestionWebDynamique_Utilisateur(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(20), 
    password VARCHAR(20)
);
INSERT into GestionWebDynamique_Utilisateur(nom,password)VALUES("admin","1234");
SELECT*from GestionWebDynamique_prevision;
SELECT*from GestionWebDynamique_depense;
SELECT SUM(montant) montantTotal FROM GestionWebDynamique_depense where  id_prevision = 1;

