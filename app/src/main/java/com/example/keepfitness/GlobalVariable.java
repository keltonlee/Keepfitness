package com.example.keepfitness;

import com.example.keepfitness.models.Cartitem;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariable {


    private static GlobalVariable instance = new GlobalVariable();

    // Getter-Setters
    public static GlobalVariable getInstance() {
        return instance;
    }

    public static void setInstance(GlobalVariable instance) {
        GlobalVariable.instance = instance;
    }

    private int notification_index;

    private List<Cartitem> globalArrayList;

    private GlobalVariable() {

    }


    public int getValue() {
        return notification_index;
    }
    public List<Cartitem> getGlobalArrayList() {
        return globalArrayList;
    }


    public void setValue(int notification_index) {
        this.notification_index = notification_index;
    }
    public void setGlobalArrayList(List<Cartitem> globalArrayList) {
        this.globalArrayList = globalArrayList;
    }

}