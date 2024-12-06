package com.example.demo.controller;

import com.example.demo.dao.TacheDao;
import com.example.demo.model.Tache;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class TacheController {

    @Autowired
    private TacheDao tacheDao;
    private Tache tache;
    private Integer id;

    @GetMapping("/tache")
    public List<Tache> getAlltache() {
        return tacheDao.findAll();


    }

    @GetMapping("/tache/{id}")
    public ResponseEntity<Tache> get(@PathVariable Integer id) {

        //On vérifie que l'tache existe bien dans la base de donnée
        Optional<Tache> optionalTache = tacheDao.findById(id);

        //si l'tache n'existe pas
        if(optionalTache.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalTache.get(),HttpStatus.OK);
    }

    @PostMapping("/tache")
    public ResponseEntity<Tache> create(@RequestBody Tache tache) {

        //on force l'id à null au cas où le client en aurait fourni un
        tache.setId(null);
        tacheDao.save(tache);

        return new ResponseEntity<>(tache, HttpStatus.CREATED);
    }

    @PutMapping("/tache/{id}")
    public ResponseEntity<Tache> update(@RequestBody Tache tache, @PathVariable Integer id) {
        this.tache = tache;
        this.id = id;

        //on force le changement de l'id de l'tache à enregitrer à l'id passé en paramètre
        tache.setId(id);

        //On vérifie que l'tache existe bien dans la base de donnée
        Optional<Tache> optionalTache = tacheDao.findById(id);

        //si l'tache n'existe pas
        if(optionalTache.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tacheDao.save(tache);

        return new ResponseEntity<>(tache, HttpStatus.OK);
    }

    @DeleteMapping("/tache/{id}")
    public ResponseEntity<Tache> delete(@PathVariable Integer id) {

        //On vérifie que l'tache existe bien dans la base de donnée
        Optional<Tache> optionalTache = tacheDao.findById(id);

        //si l'tache n'existe pas
        if(optionalTache.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tacheDao.deleteById(id);

        return new ResponseEntity<>(optionalTache.get(), HttpStatus.OK);

    }

}
