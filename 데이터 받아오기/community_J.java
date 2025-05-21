package com.example.weather_choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class community_J extends AppCompatActivity {
    ImageView pic1, pic2, pic3, pic4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.community_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.community), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pic1 = findViewById(R.id.CommunityPic1);
        pic2 = findViewById(R.id.CommunityPic2);
        pic3 = findViewById(R.id.CommunityPic3);
        pic4 = findViewById(R.id.CommunityPic4);

        // Clothes 리스트 가져오기
        ArrayList<Clothes> clothesList = Closet.getClothesList();

        // size는 저 데이터셋에 몇개 데이터가 저장되어 있는지
        if (clothesList.size() > 0) {
            Clothes c = clothesList.get(0); // 0번째에 있는거 꺼내기
            String name = c.link.replace(".jpg", "").replace(".png", ""); // 확장자 제거
            int resId = getResources().getIdentifier(name, "drawable", getPackageName()); // 사진에 값 받으면
            if (resId != 0) { // 현재 화면에 사진명 받은거 넣기
                pic1.setImageResource(resId);
            } else {
                Toast.makeText(this, "이미지 로딩 실패: " + name, Toast.LENGTH_SHORT).show(); // 사진 오류 확인용
            }
        }

        if (clothesList.size() > 1) {
            Clothes c = clothesList.get(1);
            String name = c.link.replace(".jpg", "").replace(".png", "");
            int resId = getResources().getIdentifier(name, "drawable", getPackageName());
            if (resId != 0) {
                pic2.setImageResource(resId);
            } else {
                Toast.makeText(this, "이미지 로딩 실패: " + name, Toast.LENGTH_SHORT).show();
            }
        }

        if (clothesList.size() > 2) {
            Clothes c = clothesList.get(2);
            String name = c.link.replace(".jpg", "").replace(".png", "");
            int resId = getResources().getIdentifier(name, "drawable", getPackageName());
            if (resId != 0) {
                pic3.setImageResource(resId);
            } else {
                Toast.makeText(this, "이미지 로딩 실패: " + name, Toast.LENGTH_SHORT).show();
            }
        }

        if (clothesList.size() > 3) {
            Clothes c = clothesList.get(3);
            String name = c.link.replace(".jpg", "").replace(".png", "");
            int resId = getResources().getIdentifier(name, "drawable", getPackageName());
            if (resId != 0) {
                pic4.setImageResource(resId);
            } else {
                Toast.makeText(this, "이미지 로딩 실패: " + name, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void peedOpen(View view) {
        Intent intent = new Intent(community_J.this, peedAdd_J.class);
        startActivity(intent);
    }
}