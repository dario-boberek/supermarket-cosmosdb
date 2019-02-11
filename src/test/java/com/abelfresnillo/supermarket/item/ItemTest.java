package com.abelfresnillo.supermarket.item;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void new_WithValidArguments_ShouldCreateItem() {
        Item item = new Item("My Item");

        assertThat(item.getId()).isNotEmpty();
        assertThat(item.getName()).isNotEmpty();
        assertThat(item.getPrice()).isEqualTo(0.00);
        assertThat(item.getStatus().toString()).isEqualTo("DRAFT");
        assertThat(item.getCreatedDate()).isNotNull();
        assertThat(item.getPublishedDate()).isNull();
    }

    @Test
    public void publish_WithValidCompleteData_ShouldSetItemStatusToPublished() {
        Item item = new Item("My Item");

        item.publish("Model", 1.50);

        assertThat(item.getId()).isNotEmpty();
        assertThat(item.getName()).isEqualTo("My Item");
        assertThat(item.getModel()).isNotEmpty();
        assertThat(item.getPrice()).isPositive();
        assertThat(item.getStatus().toString()).isEqualTo("PUBLISHED");
    }

    @Test
    public void publish_WithNegativeOrZeroPrice_ShouldThrowException() {
        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("The item price must be greater than 0.00");

        Item item = new Item("My Item");

        item.publish("Model", -1.00);
    }

    @Test
    public void publish_WithEmptyModel_ShouldThrowException() {
        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("A model must be provided");

        Item item = new Item("My Item");

        item.publish(null, 1.00);
    }
}
