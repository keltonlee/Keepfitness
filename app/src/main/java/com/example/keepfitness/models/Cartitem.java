package com.example.keepfitness.models;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Cartitem {

    private Product product;
    private int quantity;

    public Cartitem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cartitem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartitem cartitem = (Cartitem) o;
        return getQuantity() == cartitem.getQuantity() &&
                getProduct().equals(cartitem.getProduct());
    }

    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerValue(Spinner spinner,int quantity){
        spinner.setSelection(quantity - 1,true);
    }

    public static DiffUtil.ItemCallback<Cartitem> itemCallback = new DiffUtil.ItemCallback<Cartitem>() {
        @Override
        public boolean areItemsTheSame(@NonNull Cartitem oldItem, @NonNull Cartitem newItem) {
            return oldItem.getQuantity() == newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Cartitem oldItem, @NonNull Cartitem newItem) {
            return oldItem.equals(newItem);
        }
    };

}
