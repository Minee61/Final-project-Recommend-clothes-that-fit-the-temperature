package com.example.weather_choose;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class community_J extends AppCompatActivity {

    // 필터 버튼
    private Button TempBn, WeatherBn, StyleBn, fixBn;
    // 옷 이미지뷰
    private ImageView clothes1, clothes2, clothes3, clothes4;
    // 찜 버튼
    private ImageButton star1, star2, star3, star4;
    // 임시 옷장
    private List<Clothes> makeshiftCloset = new ArrayList<>();

    private String selecteTemp = "", selecteWeather = "", selecteStyle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.community_activity);

        //필터링 버튼 눌렀을때 네비게이션 바 잘 보이게 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clothesLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 버튼
        TempBn = findViewById(R.id.tempurBn);
        WeatherBn = findViewById(R.id.weatherBn);
        StyleBn = findViewById(R.id.styleBn);
        fixBn = findViewById(R.id.fixBn);

        // + 버튼 이동
        Button addButton = findViewById(R.id.peedBn);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(community_J.this, peedAdd_J.class);
            startActivity(intent);
        });

        // 이미지, 별 버튼
        clothes1 = findViewById(R.id.clothes1);
        clothes2 = findViewById(R.id.clothes2);
        clothes3 = findViewById(R.id.clothes3);
        clothes4 = findViewById(R.id.clothes4);

        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);

        // 필터 이벤트 연결
        TempBn.setOnClickListener(v -> showFilter(TempBn, new String[]{"전체", "0~24도", "25~40도"}));
        WeatherBn.setOnClickListener(v -> showFilter(WeatherBn, new String[]{"전체", "맑음", "흐림", "비"}));
        StyleBn.setOnClickListener(v -> showFilter(StyleBn, new String[]{"전체", "캐주얼", "댄디", "스트릿"}));

        // 새로고침 이벤트 연결
        fixBn.setOnClickListener(v -> RandomClothes());
        // 화면 켜지자마자 작동 (4개 보여줘야해서)
        RandomClothes();
    }

    // 필터 조건에 맞는 옷 출력 함수
    private void RandomClothes() {
        // 조건에 맞는 옷 넣을 임시 옷자야
        List<Clothes> condiCloset = new ArrayList<>();

        // 옷들 필터 조건 확인
        for (Clothes c : Closet.getClothesList()) {  // closet클래스에 있는 옷 다 꺼냄
            boolean AllConditional = true;  // 모든 조건 확인

            if (!selecteTemp.isEmpty()) {
                if (selecteTemp.equals("0~24도") && c.temperature > 24) {
                    AllConditional = false;
                } else if (selecteTemp.equals("25~40도") && c.temperature < 25) {
                    AllConditional = false;
                }
            }

            if (!selecteWeather.isEmpty()) {
                if (!c.weather.equals(selecteWeather)) {
                    AllConditional = false;
                }
            }

            if (!selecteStyle.isEmpty()) {
                if (!c.style.equals(selecteStyle)) {
                    AllConditional = false;
                }
            }

            if (AllConditional) {
                condiCloset.add(c); // 모든 조건에 충족하는 옷만 들어감
            }
        }

        Collections.shuffle(condiCloset); // 안에 값들 순서 섞기
        makeshiftCloset = condiCloset.subList(0, Math.min(4, condiCloset.size()));
        // subList : 리스트에서 원하는 위치만 보이게 자르는 기능
        // 형태 : subList(시작 인덱스 , 끝 인덱스)

        //min을 이용해서 4보다 적을 시 그냥 그정도만 보이게.

        for (int i = 0; i < makeshiftCloset.size(); i++) {
            Clothes c = makeshiftCloset.get(i);

            // link1은 찜 했다, link2는 찜 안했다는 의미
            // 일단 jpg일지 png일지 몰라 둘 다 해놓음
            if (c.link.equals("link1.jpg") || c.link.equals("link1.png")) {
                c.pick = true;
            } else if (c.link.equals("link2.jpg") || c.link.equals("link2.png")) {
                c.pick = false;
            }

            // 사진 이름에 만약 jpg 확장자명 적었을 시 빼기
            String name = c.link.replace(".jpg", "").replace(".png", "");
            //getResources : 현재 리소스 정보 불러옴, drawable라는 폴더에서 name이라는 차일 찾기 ,  getIdentifier : 현재 이 앱 패키지명
            int resId = getResources().getIdentifier(name, "drawable", getPackageName());
            if (resId != 0) { // 저 이름의 옷이 존재한다면 순서대로 하나씩 넣기
                if (i == 0) clothes1.setImageResource(resId);
                if (i == 1) clothes2.setImageResource(resId);
                if (i == 2) clothes3.setImageResource(resId);
                if (i == 3) clothes4.setImageResource(resId);
            }

            ImageButton starBn = null;

            // 각 사진의 찜 버튼 연결을 다시 하기 위해서
            if (i == 0) starBn = star1;
            if (i == 1) starBn = star2;
            if (i == 2) starBn = star3;
            if (i == 3) starBn = star4;

            if (starBn != null) {
                ImageButton StarBn1 = starBn;
                Clothes C1 = c;

                if (C1.pick) {
                    StarBn1.setImageResource(R.drawable.link1); // 찜 O
                } else {
                    StarBn1.setImageResource(R.drawable.link2); // 찜 X
                }
                StarBn1.setOnClickListener(view -> { // 만약 찜 버튼을 눌렀을 때
                    C1.pick = !C1.pick; // 버튼을 눌렀기 때문에 찜상태가 변경되었다고 설정

                    // 찜 상황에 따라 사진 다시 넣기
                    if (C1.pick) {
                        StarBn1.setImageResource(R.drawable.link1);
                    } else {
                        StarBn1.setImageResource(R.drawable.link2);
                    }

                    //화면에 찜 추가됐다 말 이 뜨게
                    if (C1.pick) {
                        Toast.makeText(this, "찜 추가됨", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "찜 취소됨", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

    private void showFilter(Button button, String[] options) { //optoins위에 적었던거 필터링 종류
        // 필터 버튼 눌렀을 때 뜨는 작은 드롭다운창
        PopupMenu popup = new PopupMenu(this, button); // button : 버튼 밑에 뜬다.

        // 메뉴에 옵션 추가
        for (int i = 0; i < options.length; i++) {
            popup.getMenu().add(0, i, i, options[i]); //grid로 각 위치에 아까 options배열에 설정한거 순서대로 넣기
        }

        // 항목 중 하나 선택시 동작
        popup.setOnMenuItemClickListener(item -> {
            String selecteCategory = item.getTitle().toString(); // item : 사용자가 클릭한 메뉴 항목의 글자 받기

            // 버튼 텍스트를 선택한 항목 이름과 같게 설정
            String beforeText = button.getText().toString().split(":")[0];
            button.setText(beforeText + ": " + selecteCategory);

            // 어떤 버튼이 눌렸는지에 따라 선택값 저장
            if (button == TempBn) {
                button.setText("온도: " + selecteCategory);
                if (selecteCategory.equals("전체")) {
                    selecteTemp = ""; // 필터 값을 지움
                } else {
                    selecteTemp = selecteCategory; // 필터값 전체 아닐시 그에 맞는 이름으로 변경
                }
            } else if (button == WeatherBn) {
                button.setText("날씨: " + selecteCategory);
                if (selecteCategory.equals("전체")) {
                    selecteWeather = "";
                } else {
                    selecteWeather = selecteCategory;
                }
            } else if (button == StyleBn) {
                button.setText("스타일: " + selecteCategory);
                if (selecteCategory.equals("전체")) {
                    selecteStyle = "";
                } else {
                    selecteStyle = selecteCategory;
                }
            }

            // 필터 적용해서 옷 다시 보여주기
            RandomClothes();

            return true;
        });

        // 팝업 보여주기
        popup.show();
    }

}
