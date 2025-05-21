package com.example.weather_choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class peedAdd_J extends AppCompatActivity {

    // 변수 선언
    EditText link_text, tem_text, weather_text, style_text;
    Button button_add, pictureBn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.peedadd_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.peed), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Id 연결하기
        link_text = findViewById(R.id.PeedLink);
        tem_text = findViewById(R.id.PeedTem);
        weather_text = findViewById(R.id.PeedWea);
        style_text = findViewById(R.id.PeedSty);
        button_add = findViewById(R.id.PeedAdd);
        pictureBn_add = findViewById(R.id.PeedPicAdd);

        // 추가한 값 다른 파일에 저장
        // 버튼 클릭 시 옷 추가
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = link_text.getText().toString();
                String tempStr = tem_text.getText().toString();
                String weather = weather_text.getText().toString();
                String style = pictureBn_add.getText().toString();

                //빈값이 있는 경우 대체한다"
                if (!link.isEmpty() && !tempStr.isEmpty() && !weather.isEmpty() && !style.isEmpty()) {
                    int temperature = Integer.parseInt(tempStr);
                    String clothesName = "이름 없음";
                    boolean pick = false;

                    // 옷 객체 만들고 저장소에 추가
                    Clothes newClothes = new Clothes(link, temperature, weather, style, clothesName, pick);
                    Closet.addClothes(newClothes);


                    Intent intent = new Intent(peedAdd_J.this, community_J.class);
                    startActivity(intent);
                }

            }
        });
    }
}
