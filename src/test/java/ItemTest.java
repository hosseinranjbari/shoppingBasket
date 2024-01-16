import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    ShoppingListService shoppingList;
    List<Item> itemList;
    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingListServiceImpl();
        itemList = new LinkedList<>();
    }

    @Test
    void should_not_throw_anything_when_name_and_item_are_correct() {
        itemList.add(new Item("apple", 10));
        itemList.add(new Item("orange", 5));

        assertDoesNotThrow(() -> shoppingList.saveItems(itemList));
    }

    @Test
    void should_throw_ItemNameException_when_an_item_name_is_null() {
        itemList.add(new Item("banana", 5));
        itemList.add(new Item(null, 10));
        itemList.add(new Item("apple", 5));

        assertThrows(ItemNameException.class, () -> shoppingList.saveItems(itemList));
    }

    @Test
    void should_throw_ItemNameException_when_an_item_name_is_empty() {
        itemList.add(new Item("banana", 5));
        itemList.add(new Item("orange", 10));
        itemList.add(new Item("", 5));

        assertThrows(ItemNameException.class, () -> shoppingList.saveItems(itemList));
    }

    @Test
    void should_throw_ItemQuantityException_when_an_item_has_less_than_1_quantity() {
        itemList.add(new Item("apple", 5));
        itemList.add(new Item("orange", 0));

        assertThrows(ItemQuantityException.class, () -> shoppingList.saveItems(itemList));
    }

    @Test
    void should_throw_ItemQuantityException_when_an_item_has_greater_than_100_quantity() {
        itemList.add(new Item("apple", 5));
        itemList.add(new Item("orange", 101));

        assertThrows(ItemQuantityException.class, () -> shoppingList.saveItems(itemList));
    }
}
