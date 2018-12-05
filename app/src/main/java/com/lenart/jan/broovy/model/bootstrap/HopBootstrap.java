package com.lenart.jan.broovy.model.bootstrap;

import com.lenart.jan.broovy.model.HopViewItem;

import java.util.ArrayList;

public class HopBootstrap {
    private ArrayList<HopViewItem> items;

    public HopBootstrap() {
        this.items = new ArrayList<>();

        HopViewItem item1 = new HopViewItem("Agnus", "20", 60);
        this.items.add(item1);

        HopViewItem item2 = new HopViewItem("Saaz Late", "50", 30);
        this.items.add(item2);

        HopViewItem item3 = new HopViewItem("Marynka", "25", 10);
        this.items.add(item3);
    }

    public ArrayList<HopViewItem> getItems() {
        return items;
    }
}
