package com.example.keepfitness.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.keepfitness.OwnDietActivity;
import com.example.keepfitness.R;
import com.example.keepfitness.TypeActivity;
import com.example.keepfitness.databinding.FragmentOrderBinding;
import com.example.keepfitness.viewmodels.ShopViewModel;

public class OrderFragment extends Fragment {

    FragmentOrderBinding fragmentOrderBinding;
    ShopViewModel shopViewModel;
    Button shopbtn,chartbtn;
    String item,num;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_order, container, false);
        fragmentOrderBinding = FragmentOrderBinding.inflate(inflater,container,false);
        return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        shopbtn=(Button) view.findViewById(R.id.continueShoppingButton);
        shopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TypeActivity.class);
                startActivity(intent);
            }
        });
        chartbtn=(Button) view.findViewById(R.id.goToChart);
        chartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OwnDietActivity.class);
                startActivity(intent);
            }
        });
    }
}