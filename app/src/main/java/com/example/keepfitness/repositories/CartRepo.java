package com.example.keepfitness.repositories;

import android.nfc.Tag;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.keepfitness.GlobalVariable;
import com.example.keepfitness.Globalclass;
import com.example.keepfitness.models.Cartitem;
import com.example.keepfitness.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<Cartitem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalCalories = new MutableLiveData<>();
    GlobalVariable sharedData = GlobalVariable.getInstance();

    public LiveData<List<Cartitem>> getCart(){
        if (mutableCart.getValue()==null){
            initCart();
        }
        return  mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<Cartitem>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product){
        if (mutableCart.getValue()==null){
            initCart();
        }

        List<Cartitem> cartitemList = new ArrayList<>(mutableCart.getValue());
        for (Cartitem cartitem:cartitemList){
            if (cartitem.getProduct().getId().equals(product.getId())){
                if (cartitem.getQuantity() == 5){
                    return false;
                }

                int index = cartitemList.indexOf(cartitem);
                cartitem.setQuantity(cartitem.getQuantity()+1);
                cartitemList.set(index,cartitem);

                mutableCart.setValue(cartitemList);
                calculateCartTotal();
                return true;
            }
        }

        Cartitem cartitem = new Cartitem(product,1);
        cartitemList.add(cartitem);
        mutableCart.setValue(cartitemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(Cartitem cartitem){
        if (mutableCart.getValue()==null){
            return;
        }

        List<Cartitem> cartitemList = new ArrayList<>(mutableCart.getValue());
        cartitemList.remove(cartitem);
        mutableCart.setValue(cartitemList);
        calculateCartTotal();
    }

    public void changeQuantity(Cartitem cartitem, int quantity){
        if (mutableCart.getValue() == null) return;

        List<Cartitem> cartitemList = new ArrayList<>(mutableCart.getValue());

        Cartitem updatedItem = new Cartitem(cartitem.getProduct(),quantity);
        cartitemList.set(cartitemList.indexOf(cartitem),updatedItem);

        mutableCart.setValue(cartitemList);
        calculateCartTotal();
    }

    private void calculateCartTotal(){
        if(mutableCart.getValue() == null) return;
        double total = 0.0;
        List<Cartitem> cartitemList = mutableCart.getValue();

        for (Cartitem cartitem: cartitemList){

            sharedData.setGlobalArrayList(cartitemList);

            total +=cartitem.getProduct().getCalories()*cartitem.getQuantity();
        }
        mutableTotalCalories.setValue(total);
    }

    public LiveData<Double> getTotalCalories(){
        if (mutableTotalCalories.getValue() == null){
            mutableTotalCalories.setValue(0.0);
        }
        return mutableTotalCalories;
    }


}