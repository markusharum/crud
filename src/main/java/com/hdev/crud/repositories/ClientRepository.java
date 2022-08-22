package com.hdev.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdev.crud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>  {
   

}
