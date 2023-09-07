package javahongkong.bootcamp.exercise;

import java.util.HashMap;

public class FurnitureOrder implements FurnitureOrderInterface {
    HashMap<Furniture, Integer> ordersOfFurnitures;

    FurnitureOrder() {
        this.ordersOfFurnitures = new HashMap<>();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        this.ordersOfFurnitures.put(type, furnitureCount);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        return this.ordersOfFurnitures;
    }

    // Normal version
    public float getTotalOrderCost() {
        float total = 0.0f;
        for (Furniture i : ordersOfFurnitures.keySet()) {
            int quantity = ordersOfFurnitures.get(i);
            float costPerItem = i.cost();
            total += quantity * costPerItem;
        }
        return total;
    }

    // stream() version
    public float getTotalOrderCost2() {
        return (float) this.ordersOfFurnitures.entrySet()//
                .stream()//
                .mapToDouble(e -> e.getKey().cost() * e.getValue())//
                .sum();
    }

    //
    public int getTypeCount(Furniture type) {
        return ordersOfFurnitures.getOrDefault(type, 0);
    }

    public float getTypeCost(Furniture type) {
        int quantity = getTypeCount(type);
        return quantity * type.cost();
    }

    // Normal version
    public int getTotalOrderQuantity() {
        int total = 0;
        for (Furniture i : this.ordersOfFurnitures.keySet()) {
            total += getTypeCount(i);
        }
        return total;
    }

    // stream() version
    public int getTotalOrderQuantity2() {
        return this.ordersOfFurnitures.values().stream()//
                .mapToInt(Integer::intValue)//
                .sum();
    }
}