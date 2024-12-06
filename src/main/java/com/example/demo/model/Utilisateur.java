package com.example.demo.model;

import com.example.demo.model.Droit;
import com.example.demo.model.Tache;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nom;

    String password;

    @ManyToOne
    Droit droit;

    // Liste des tâches créées
    @OneToMany(mappedBy = "créateur")
    List<Tache> creerTaches;

    // Liste des tâches affectées
    @ManyToMany(mappedBy = "utilisateurs")
    List<Tache> affecterTaches;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setDroit(Droit droit) {
        this.droit = droit;
    }

    public List<Tache> getCreerTaches() {
        return creerTaches;
    }

    public void setCreerTaches(List<Tache> creerTaches) {
        this.creerTaches = creerTaches;
    }

    public List<Tache> getAffecterTaches() {
        return affecterTaches;
    }

    public void setAffecterTaches(List<Tache> affecterTaches) {
        this.affecterTaches = affecterTaches;
    }
}
