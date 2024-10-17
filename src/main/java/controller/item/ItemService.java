package controller.item;

import javafx.collections.ObservableList;
import entity.Item;

public interface ItemService {
    ObservableList<Item> getAll();
    boolean addItem(Item item);
    boolean deleteItem(String itemCode);
    boolean updateItem(Item item);
    Item searchItem (String itemCode);
    ObservableList<String> getItemCodes();
}
