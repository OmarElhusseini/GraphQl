package com.udacity.GraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.GraphQL.entity.Dog;
import com.udacity.GraphQL.entity.exception.DogNotFoundException;
import com.udacity.GraphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog dogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()){
            return optionalDog.get();
        }
        else {
            throw new DogNotFoundException("Dog Not Found, invalid id:", id);
        }
    }
}
