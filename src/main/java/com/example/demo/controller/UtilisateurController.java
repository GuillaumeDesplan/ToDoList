package com.example.demo.controller;

import ch.qos.logback.core.status.Status;
import com.example.demo.model.Tache;
import com.example.demo.model.Utilisateur;
import com.example.demo.dao.TacheDao;
import com.example.demo.dao.UtilisateurDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    // Récupérer tous les utilisateurs
    @GetMapping("/utilisateur")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> get(@PathVariable Integer id) {

        //On vérifie que l'utilisateur existe bien dans la base de donnée
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        //si l'utilisateur n'existe pas
        if(optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalUtilisateur.get(),HttpStatus.OK);
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> create(
            @RequestBody Utilisateur utilisateur) {

        //on force l'id à null au cas où le client en aurait fourni un
        utilisateur.setId(null);

        utilisateur.setPassword(utilisateur.getPassword());
        utilisateur.setDroit(null);
        utilisateur.setNom(utilisateur.getNom());

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> update(
            @RequestBody  Utilisateur utilisateurEnvoye, @PathVariable Integer id) {

        //on force le changement de l'id de l'utilisateur à enregitrer à l'id passé en paramètre
        utilisateurEnvoye.setId(id);

        //On vérifie que l'utilisateur existe bien dans la base de donnée
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        //si l'utilisateur n'existe pas
        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Utilisateur utilisateurBaseDeDonne = optionalUtilisateur.get();

        //On recupère de la base de donnée toutes les informations
        // que l'on ne veut pas laisser l'utilisateur modifier

        //Si l'utilisateur a un nouveau mot de passe, on le hash le nouveau
        if(utilisateurEnvoye.getPassword() != null) {
            utilisateurEnvoye.setPassword(utilisateurEnvoye.getPassword());
        }
        if(utilisateurEnvoye.getNom() != null) {
            utilisateurEnvoye.setNom(utilisateurEnvoye.getNom());
        }

        //si le json envoyé n'a pas de competences, on gardent les anciennes
        // (mais si le json contient un tableau vide pour les competences,
        // alors elles seront bien supprimées)
        //idem pour status
        if(utilisateurEnvoye.getDroit() == null) {
            utilisateurEnvoye.setDroit(utilisateurBaseDeDonne.getDroit());
        }

        utilisateurDao.save(utilisateurEnvoye);

        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id) {

        //On vérifie que l'utilisateur existe bien dans la base de donnée
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        //si l'utilisateur n'existe pas
        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.deleteById(id);

        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);

    }
}
