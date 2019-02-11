package com.abelfresnillo.supermarket.item;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ItemCustomRepositoryImpl implements ItemCustomRepository {
    private MongoOperations mongoOperations;

    public ItemCustomRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public void update(Item item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(item.getId()));

        Item itemToUpdate = mongoOperations.findOne(query, Item.class);

        itemToUpdate.setModel(item.getModel());
        itemToUpdate.setPrice(item.getPrice());

        mongoOperations.save(itemToUpdate);
    }
}
