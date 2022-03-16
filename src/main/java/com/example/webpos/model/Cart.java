package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();
    private double total = 0;

    private void countTotal(){
        total = 0;
        for(Item item : items){
            total += item.getQuantity()*item.getProduct().getPrice();
        }
    }

    public boolean addItem(Item item) {
        items.add(item);
        countTotal();
        return true;
    }

    public void cancel(){
        items.clear();
        countTotal();
    }

    public void itemAmountAdd(int index, int amount){
        items.get(index).setQuantity(items.get(index).getQuantity() + amount);
        countTotal();
    }

    public void itemDelete(int index){
        items.remove(index);
        countTotal();
    }

    public void itemAmountDecline(int index, int amount){
        items.get(index).setQuantity(items.get(index).getQuantity() - amount);
        if(items.get(index).getQuantity()<=0){
            itemDelete(index);
        }
        countTotal();
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
