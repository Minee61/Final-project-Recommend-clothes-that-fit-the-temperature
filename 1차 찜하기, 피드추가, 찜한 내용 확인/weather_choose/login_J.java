package com.example.weather_choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_J extends AppCompatActivity {

    Button regiBn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }

    public void idTx(View view) {
    }

    public void passwordTx(View view) {
    }

    public void registerBn(View view) {
        Intent intent = new Intent(login_J.this, register_J.class);
        startActivity(intent);
    }

    public void startCheckBn(View view) {
    }

    public void loginHelp(View view) {
    }
}