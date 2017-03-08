package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	List<Client> findAll();
}
