package com.example.keepfitness.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keepfitness.GlobalVariable;
import com.example.keepfitness.R;
import com.example.keepfitness.adapters.CartListAdapter;
import com.example.keepfitness.databinding.FragmentCartBinding;
import com.example.keepfitness.databinding.FragmentShopBinding;
import com.example.keepfitness.models.Cartitem;
import com.example.keepfitness.models.Product;
import com.example.keepfitness.viewmodels.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface{

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    GlobalVariable sharedData = GlobalVariable.getInstance();
    private List<Cartitem> arrayList;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<Cartitem>>() {
            @Override
            public void onChanged(List<Cartitem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0 && cartItems.size() < 11);
                fragmentCartBinding.press.setText("Please press the order button");

                if (cartItems.size() > 10 || cartItems.size() == 0) {
                    fragmentCartBinding.errorText.setError("");
                    if (cartItems.size() > 10) {
                        fragmentCartBinding.press.setText("請選擇少於10種食物");
                    }
                    if (cartItems.size() == 0) {
                        fragmentCartBinding.press.setText("請至少選擇1種食物");
                    }
                } else {
                    fragmentCartBinding.errorText.setVisibility(View.INVISIBLE);
                }

            }
        });

        shopViewModel.getTotalCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalTextView.setText("Total: " + aDouble.toString() + " kcal/100g");
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //送出
                navController.navigate(R.id.action_cartFragment2_to_orderFragment);
            }
        });
    }
    @Override
    public void deleteItem(Cartitem cartitem) {
        shopViewModel.removeItemFromCart(cartitem);
    }

    @Override
    public void changeQuantity(Cartitem cartitem, int quantity) {
        shopViewModel.changeQuantity(cartitem,quantity);
    }
}