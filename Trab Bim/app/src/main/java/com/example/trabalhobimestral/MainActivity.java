package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listviewwifi;
ArrayAdapter<String> adapter;
List<String> blt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listviewwifi = findViewById(R.id.listviewbt);

        blt = new ArrayList<>();
        blt.add("blt 1");
        blt.add("blt 2");
        blt.add("blt 3");

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, blt);
        listviewwifi.setAdapter(adapter);

        listviewwifi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                String posicao = String.valueOf(i);
                Toast.makeText(MainActivity.this, posicao, Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }
}