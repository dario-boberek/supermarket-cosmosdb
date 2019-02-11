package com.abelfresnillo.supermarket.item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataMongoTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    private Item item1;
    private Item item2;

    @Before
    public void setup() {
        item1 = new Item("Item One");
        item2 = new Item("Item Two");

        itemRepository.save(item1);
    }

    @Test
    public void update_WithUpdatedItem_ShouldUpdateItem() {
        List<Item> itemListSearchOne = itemRepository.findByName("Item One");
        Item originalItem = itemListSearchOne.get(0);

        assertThat(originalItem.getName()).isEqualTo("Item One");
        assertThat(originalItem.getModel()).isNull();
        assertThat(originalItem.getPrice()).isEqualTo(0.00);

        originalItem.publish("Model", 1.00);
        itemRepository.update(originalItem);

        List<Item> itemListSearchTwo = itemRepository.findByName("Item One");
        Item updatedItem = itemListSearchTwo.get(0);

        assertThat(updatedItem.getName()).isEqualTo("Item One");
        assertThat(updatedItem.getModel()).isEqualTo("Model");
        assertThat(updatedItem.getPrice()).isEqualTo(1.00);
    }

    @Test
    public void deleteByName_WithExistingName_ShouldDeleteOneItem() {
        int numberOfDeletedItems = itemRepository.deleteByName("Item One");

        assertThat(numberOfDeletedItems).isEqualTo(1);
    }

    @Test
    public void save_WIthValidItem_ShouldStoreItem() {
        int numberOfItems = itemRepository.findAll().size();

        itemRepository.save(item2);

        assertThat(itemRepository.findAll().size()).isEqualTo(numberOfItems + 1);
    }

    @Test
    public void findByName_WithExistingName_ReturnsItem() {
        List<Item> items = itemRepository.findByName("Item One");

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo("Item One");
    }

    @Test
    public void findByModel_WithValidExistingModel_ReturnsItem() {
        item1.publish("ABC", 1.50);
        itemRepository.update(item1);

        List<Item> items = itemRepository.findByModel("ABC");

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo("Item One");
    }

    @After
    public void teardown() {
        itemRepository.deleteAll();
    }
}
