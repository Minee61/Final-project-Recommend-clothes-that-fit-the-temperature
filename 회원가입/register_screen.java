package com.example.chamchamgame_v1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register_screen extends AppCompatActivity {

    private EditText userid, userpass, userpassck;
    private Button passcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_code);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userid = findViewById(R.id.userId);
        userpass = findViewById(R.id.userPass);
        userpassck = findViewById(R.id.userPassCk);
        passcheck = findViewById(R.id.passwordCheck);
    }


    public void paawordCheckC(View view) {
        
    }
}
