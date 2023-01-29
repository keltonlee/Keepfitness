package com.example.keepfitness.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.keepfitness.GlobalVariable;
import com.example.keepfitness.R;
import com.example.keepfitness.models.Cartitem;
import com.example.keepfitness.viewmodels.ShopViewModel;
import com.example.keepfitness.views.CartFragment;
import com.example.keepfitness.views.OrderFragment;
import com.example.keepfitness.views.ShopFragment;

import java.util.List;

public class MyMenu extends AppCompatActivity {

    NavController navController;
    ShopViewModel shopViewModel;

    private int cartQuantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_menu);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<Cartitem>>() {
            @Override
            public void onChanged(List<Cartitem> cartitems) {
                int quantity =0;
                for (Cartitem cartitem:cartitems){
                    quantity+= cartitem.getQuantity();
                }
                cartQuantity=quantity;
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.cartFragment2);
        View actionView = menuItem.getActionView();

        TextView cartBadgeTV=actionView.findViewById(R.id.cart_badge_tv);

        cartBadgeTV.setText(String.valueOf(cartQuantity));
        cartBadgeTV.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);
    }


}