package com.abelfresnillo.supermarket.item;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String>, ItemCustomRepository {

    int deleteByName(String name);

    List<Item> findByName(String name);
    List<Item> findByModel(String model);
}
