package com.capstone;
public class NPCFactory extends Player {
    //fields
    private String dialog = "I got nothing to say to you :<";

    //ctors
    public NPCFactory(String name, String dialog){
        super(name);
        this.dialog = dialog;

    };

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
