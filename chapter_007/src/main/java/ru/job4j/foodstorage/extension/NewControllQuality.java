package ru.job4j.foodstorage.extension;

import ru.job4j.foodstorage.ControllQuality;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;
import ru.job4j.foodstorage.storage.WarehouseFreese;

import java.util.ArrayList;

public class NewControllQuality extends ControllQuality {

    private Shop shop = new Shop();
    private Trash trash = new Trash();
    private Warehouse warehouse = new Warehouse();
    private WarehouseFreese warehouseFreese = new WarehouseFreese();

    public void newAnalize(ArrayList<NewFood> foods) {
        for (var f: foods) {
            System.out.println("Название продукта:" + f.getName());
            int actual = percentageOfLife(f.getCreateDate(), f.getExpaireDate());
            System.out.println("Процент срока годности: " + actual);
            if (actual >= 0 && actual < 25) {
                if (f.getName().equals("овощи")) {
                    System.out.println("Добавляем " + f.getName() + " на склад с охлождением.");
                } else {
                    System.out.println("Добавляем " + f.getName() + " на склад.");
                    warehouse.add(f);
                }
            } else if (actual >= 25 && actual < 75) {
                System.out.println("Добавляем " + f.getName() + " в магазин.");
                shop.add(f);
            } else if (actual >= 75 && actual < 100) {
                System.out.println("Добавляем " + f.getName() + " в магазин со скидкой.");
                f.setDisscount(actual - 50);
                shop.add(f);
            } else {
                if (f.isCanReproduct()) {
                    System.out.println("Идет на переработку: "  + f.getName());
                } else {
                    System.out.println("Выкидываем " + f.getName());
                    trash.add(f);
                }
            }
        }
    }
}
