package com.example.weather_choose;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ColorPick_J extends AppCompatActivity {

    Spinner seasonSp;
    EditText tempVa ;
    Button searchBn ;
    ImageView result1, result2, result3, result4;

    TextView result1Tx, result2Tx, result3Tx, result4Tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.colorpick_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.colorPick), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 값 불러오기
        result1 = findViewById(R.id.result1);
        result1Tx = findViewById(R.id.result1Tx);
        result2 = findViewById(R.id.result2);
        result2Tx = findViewById(R.id.result2Tx);

        result3 = findViewById(R.id.result13);
        result3Tx = findViewById(R.id.result3Tx);
        result4 = findViewById(R.id.result14);
        result4Tx = findViewById(R.id.result4Tx);


        // spinner 목록
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"봄", "여름", "가을", "겨울"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonSp.setAdapter(adapter);

        // 검색 버튼 클릭
        searchBn.setOnClickListener(v -> {
            String season = seasonSp.getSelectedItem().toString();
            String tempVal = tempVa.getText().toString();
            if (tempVal.isEmpty()) return;

            int getTemp = Integer.parseInt(tempVal);

            // 기본 색상
            int warmImg1 = R.drawable.warm1;
            int warmImg2 = R.drawable.warm2;
            String warmText1 = "", warmText2 = "";
            int coolImg1 = R.drawable.cool1;
            int coolImg2 = R.drawable.cool2;
            String coolText1 = "", coolText2 = "";

            // 봄
            if (season.equals("봄")) {
                if (getTemp <= 9) {
                    warmImg1 = R.drawable.spring_warm1;
                    warmImg2 = R.drawable.spring_warm2;
                    warmText1 = "크림색을 추천합니다!";
                    warmText2 = "연노랑도 좋아요.";
                    coolImg1 = R.drawable.spring_cool1;
                    coolImg2 = R.drawable.spring_cool2;
                    coolText1 = "연하늘색 추천!";
                    coolText2 = "연핑크도 예뻐요.";
                } else if (getTemp >= 10 && getTemp <= 20) {
                    warmImg1 = R.drawable.spring_warm11;
                    warmImg2 = R.drawable.spring_warm22;
                    warmText1 = "크림색을 추천합니다!";
                    warmText2 = "연노랑도 좋아요.";
                    coolImg1 = R.drawable.spring_cool11;
                    coolImg2 = R.drawable.spring_cool22;
                    coolText1 = "연하늘색 추천!";
                    coolText2 = "연핑크도 예뻐요.";
                }

            } else if (getTemp >= 21 && getTemp <= 30) {
                warmImg1 = R.drawable.spring_warm111;
                warmImg2 = R.drawable.spring_warm222;
                warmText1 = "오렌지 계열 추천!";
                warmText2 = "연코랄도 시원해 보여요.";
                coolImg1 = R.drawable.spring_cool111;
                coolImg2 = R.drawable.spring_cool222;
                coolText1 = "민트 추천!";
                coolText2 = "하늘색도 좋아요.";
            }

            // 여름
            else if (season.equals("여름")) {
                if (getTemp <= 9) {
                    warmImg1 = R.drawable.spring_warm1;
                    warmImg2 = R.drawable.spring_warm2;
                    warmText1 = "크림색을 추천합니다!";
                    warmText2 = "연노랑도 좋아요.";
                    coolImg1 = R.drawable.spring_cool1;
                    coolImg2 = R.drawable.spring_cool2;
                    coolText1 = "연하늘색 추천!";
                    coolText2 = "연핑크도 예뻐요.";
                }
                else if (getTemp >= 10 && getTemp <= 20){
                    warmImg1 = R.drawable.spring_warm11;
                    warmImg2 = R.drawable.spring_warm22;
                    warmText1 = "크림색을 추천합니다!";
                    warmText2 = "연노랑도 좋아요.";
                    coolImg1 = R.drawable.spring_cool11;
                    coolImg2 = R.drawable.spring_cool22;
                    coolText1 = "연하늘색 추천!";
                    coolText2 = "연핑크도 예뻐요.";
                }


                else if (getTemp >= 21 && getTemp <= 30){
                    warmImg1 = R.drawable.summer_warm11;
                    warmImg2 = R.drawable.summer_warm22;
                    warmText1 = "오렌지 계열 추천!";
                    warmText2 = "연코랄도 시원해 보여요.";
                    coolImg1 = R.drawable.summer_cool11;
                    coolImg2 = R.drawable.summer_cool22;
                    coolText1 = "민트 추천!";
                    coolText2 = "하늘색도 좋아요.";
                }
            }

            else if (season.equals("가을")) {
                int warmImg1 = R.drawable.warm1;
                int warmImg2 = R.drawable.warm2;
                String warmText1 = "추가 예정", warmText2 = "추가 예정";
                int coolImg1 = R.drawable.cool1;
                int coolImg2 = R.drawable.cool2;
                String coolText1 = "추가 예정", coolText2 = "추가 예정";
            }
            else if (season.equals("겨울")) {
                int warmImg1 = R.drawable.warm1;
                int warmImg2 = R.drawable.warm2;
                String warmText1 = "추가 예정", warmText2 = "추가 예정";
                int coolImg1 = R.drawable.cool1;
                int coolImg2 = R.drawable.cool2;
                String coolText1 = "추가 예정", coolText2 = "추가 예정";
            }

            // 결과에 출력
            result1.setImageResource(warmImg1);
            result2.setImageResource(warmImg2);
            result1Tx.setText(": " + warmText1);
            result2Tx.setText(": " + warmText2);

            result3.setImageResource(coolImg1);
            result4.setImageResource(coolImg2);
            result3Tx.setText(": " + coolText1);
            result4Tx.setText(": " + coolText2);
        });

    }
}
