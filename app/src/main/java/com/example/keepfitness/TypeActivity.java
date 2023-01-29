package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.keepfitness.views.MyMenu;

import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {

    private ArrayList<TypeItem> TypeList;
    private MyAdapter mAdapter;
    private Button type;
    GlobalVariable sharedData = GlobalVariable.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initList();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        mAdapter = new MyAdapter(this, TypeList);
        spinner.setAdapter(mAdapter);
        spinner.setSelection(TypeList.size()-1, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypeItem clickedItem = (TypeItem) parent.getItemAtPosition(position);
                String clickedTypeName = clickedItem.getTypeName();


                if(position<16){
                    Toast.makeText(TypeActivity.this, "已選擇 " + clickedTypeName, Toast.LENGTH_SHORT).show();

                    sharedData.setValue(position);

                    type=(Button) findViewById(R.id.typebtn);
                    type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent = new Intent(TypeActivity.this, MyMenu.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initList() {
        TypeList = new ArrayList<>();
        TypeList.add(new TypeItem("肉類", R.drawable.meat));
        TypeList.add(new TypeItem("澱粉類", R.drawable.starch));
        TypeList.add(new TypeItem("菇類", R.drawable.mushroom));
        TypeList.add(new TypeItem("蔬菜類", R.drawable.vegetable));
        TypeList.add(new TypeItem("穀物類", R.drawable.crops));
        TypeList.add(new TypeItem("藻類", R.drawable.seaweed));
        TypeList.add(new TypeItem("蛋類", R.drawable.egg));
        TypeList.add(new TypeItem("豆類", R.drawable.bean));
        TypeList.add(new TypeItem("魚貝類", R.drawable.fish));
        TypeList.add(new TypeItem("乳品類", R.drawable.milk));
        TypeList.add(new TypeItem("水果類", R.drawable.fruit));
        TypeList.add(new TypeItem("飲料類", R.drawable.drink));
        TypeList.add(new TypeItem("糕餅點心類", R.drawable.snack));
        TypeList.add(new TypeItem("調味料及辛香料類", R.drawable.sauce));
        TypeList.add(new TypeItem("堅果及種子類", R.drawable.nuts));
        TypeList.add(new TypeItem("加工調理食品及其他類", R.drawable.industry));
        TypeList.add(new TypeItem("請選擇食物類別", R.drawable.restaurant));
    }
}