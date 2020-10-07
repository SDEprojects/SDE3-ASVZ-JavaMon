package com.capstone;
public class NPCFactory extends Player {

    private String dialog = "";


    public NPCFactory(String name, String dialog){
        super(name);
        this.dialog = dialog;

    };

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
