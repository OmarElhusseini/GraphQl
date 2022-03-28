package com.udacity.GraphQL.repository;

import com.udacity.GraphQL.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
