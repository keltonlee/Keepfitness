package com.example.keepfitness.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.keepfitness.models.Cartitem;
import com.example.keepfitness.models.Product;
import com.example.keepfitness.repositories.CartRepo;
import com.example.keepfitness.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();

    MutableLiveData<Product>mutableProduct=new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){

        return shopRepo.getProducts();
    }

    public void setProduct (Product product){
        mutableProduct.setValue(product);
    }

    public LiveData<Product>getProduct(){
        return mutableProduct;
    }

    public LiveData<List<Cartitem>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(Cartitem cartitem){
        cartRepo.removeItemFromCart(cartitem);
    }

    public void changeQuantity(Cartitem cartitem, int quantity){
        cartRepo.changeQuantity(cartitem,quantity);
    }

    public LiveData<Double> getTotalCalories(){
        return cartRepo.getTotalCalories();
    }

    public void resetCart(){
        cartRepo.initCart();
    }
}
