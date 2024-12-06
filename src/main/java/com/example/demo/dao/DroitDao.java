package com.example.demo.dao;

import com.example.demo.model.Priorite;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Droit;
import org.springframework.stereotype.Repository;

@Repository
public interface DroitDao extends JpaRepository<Priorite, Integer> {

}