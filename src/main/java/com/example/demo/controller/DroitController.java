package com.example.demo.controller;

import com.example.demo.dao.DroitDao;
import com.example.demo.model.Droit;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class DroitController {

    @Autowired
    private DroitDao droitDao;
    private Droit droit;
    private Integer id;

    @GetMapping("/droit")
    public List<Droit> getAlldroit() {
        return Collections.unmodifiableList(droitDao.findAll());



    }

    @GetMapping("/droit/{id}")
    public ResponseEntity<Droit> get(@PathVariable Integer id) {

        //On vérifie que l'droit existe bien dans la base de donnée
        Optional<Droit> optionalDroit = droitDao.findById(id);

        //si l'droit n'existe pas
        if (optionalDroit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalDroit.get(), HttpStatus.OK);
    }

}
