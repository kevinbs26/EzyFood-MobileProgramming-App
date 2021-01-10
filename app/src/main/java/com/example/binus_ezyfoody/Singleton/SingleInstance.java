package com.example.binus_ezyfoody.Singleton;

import com.example.binus_ezyfoody.EzFood.Drink;
import com.example.binus_ezyfoody.EzFood.EzFood;
import com.example.binus_ezyfoody.EzFood.Food;
import com.example.binus_ezyfoody.EzFood.Snack;

import java.util.ArrayList;

public class SingleInstance {

    public static ArrayList<EzFood> arrCart;
    public static ArrayList<EzFood> drinkArr;
    public static ArrayList<EzFood> foodArr;
    public static ArrayList<EzFood> snackArr;
    public static int totalOrderPrice = 0;
    public static int Funds = 0;

    public static ArrayList<EzFood> getInstanceCart(){
        if(arrCart == null) {
            synchronized (SingleInstance.class) {
                if(arrCart == null) {
                    arrCart = new ArrayList<>();
                }
            }
        }

        return arrCart;
    }

    public static ArrayList<EzFood> getInstanceDrink(){
        if(drinkArr == null) {
            synchronized (SingleInstance.class) {
                if(drinkArr == null) {
                    drinkArr = new ArrayList<>();
                }
            }
        }

        return drinkArr;
    }

    public static ArrayList<EzFood> getInstanceFood(){
        if(foodArr == null) {
            synchronized (SingleInstance.class) {
                if(foodArr == null) {
                    foodArr = new ArrayList<>();
                }
            }
        }

        return foodArr;
    }

    public static ArrayList<EzFood> getInstanceSnack(){
        if(snackArr == null) {
            synchronized (SingleInstance.class) {
                if(snackArr == null) {
                    snackArr = new ArrayList<>();
                }
            }
        }

        return snackArr;
    }

}
