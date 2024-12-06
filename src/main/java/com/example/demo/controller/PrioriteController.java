package com.example.demo.controller;

import com.example.demo.dao.PrioriteDao;
import com.example.demo.model.Priorite;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class PrioriteController {

    @Autowired
    private PrioriteDao prioriteDao;
    private Priorite priorite;
    private Integer id;

    @GetMapping("/priorite")
    public List<Priorite> getAllpriorite() {
        return Collections.unmodifiableList(prioriteDao.findAll());


    }

    @GetMapping("/priorite/{id}")
    public ResponseEntity<Priorite> get(@PathVariable Integer id) {

        //On vérifie que l'priorite existe bien dans la base de donnée
        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        //si l'priorite n'existe pas
        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPriorite.get(),HttpStatus.OK);
    }

    @PostMapping("/priorite")
    public ResponseEntity<Priorite> create(@RequestBody Priorite priorite) {

        //on force l'id à null au cas où le client en aurait fourni un
        priorite.setId(null);
        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.CREATED);
    }

    @PutMapping("/priorite/{id}")
    public ResponseEntity<Priorite> update(@RequestBody Priorite priorite, @PathVariable Integer id) {
        this.priorite = priorite;
        this.id = id;

        //on force le changement de l'id de l'priorite à enregitrer à l'id passé en paramètre
        priorite.setId(id);

        //On vérifie que l'priorite existe bien dans la base de donnée
        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        //si l'priorite n'existe pas
        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.OK);
    }

    @DeleteMapping("/priorite/{id}")
    public ResponseEntity<Priorite> delete(@PathVariable Integer id) {

        //On vérifie que l'priorite existe bien dans la base de donnée
        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        //si l'priorite n'existe pas
        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.deleteById(id);

        return new ResponseEntity<>(optionalPriorite.get(), HttpStatus.OK);

    }

}
