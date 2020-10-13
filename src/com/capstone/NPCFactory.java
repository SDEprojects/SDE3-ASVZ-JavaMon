package com.capstone;

import java.util.Collection;

public class NPCFactory extends Player {
    //fields
    private String dialog = "I got nothing to say to you :<";

    //ctors
//    public NPCFactory(String name, String dialog){  //outdates, going with full constructor
//        super(name);
//        this.dialog = dialog;
//
//    };

    public NPCFactory(String name, String dialog, String items, int money){
        super(name,money);
        this.dialog = dialog;
        String[] itemList = items.split(",");
        for (String item: itemList) {
            if (!item.equals("none")) {
                this.addInventory(item);
            }
        }
    }

    //methods
    public String getDialog(){
        return this.dialog;
    }


    @Override
    public String toString() {
        return "NPCFactory{" +
                "name='" + getName() + '\'' +
                "dialog='" + getDialog() + '\'' +
                '}';
    }

}
