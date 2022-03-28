package com.udacity.GraphQL.entity.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.GraphQL.entity.Dog;
import com.udacity.GraphQL.entity.exception.BreedNotFoundException;
import com.udacity.GraphQL.entity.exception.DogNotFoundException;
import com.udacity.GraphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> dogs = dogRepository.findAll();
        for (Dog dog : dogs){
            if (dog.getBreed().equals(breed)){
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if (!deleted){
            throw new BreedNotFoundException("Breed Not Found, invalid breed:", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newDogName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newDogName);
            dogRepository.save(dog);
            return dog;
        }
        else {
            throw new DogNotFoundException("Dog not Found, invalid id:", id);
        }
    }
}
