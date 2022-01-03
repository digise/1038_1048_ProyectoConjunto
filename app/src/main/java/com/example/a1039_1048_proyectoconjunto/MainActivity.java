package com.example.a1039_1048_proyectoconjunto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a1039_1048_proyectoconjunto.activities.GeocodingActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("hola");
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, GeocodingActivity.class));
    }
}