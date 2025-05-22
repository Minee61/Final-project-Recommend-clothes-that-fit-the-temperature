package com.example.weather_choose;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class bookMark_J extends AppCompatActivity {

    private List<Clothes> bookmarkedClothes = new ArrayList<>();
    // 몇 번째 찜 데이터를 보여주는지 위치 저장
    private int currentPage = 0;

    // ✅ 이미지 뷰 개별 선언
    private ImageView image1, image2, image3, image4;
    private ImageButton star1, star2, star3, star4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bookmark_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bookm), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 찜한 것만 골라서 보여주기
        for (Clothes c : Closet.getClothesList()) {
            if (c.pick) bookmarkedClothes.add(c);
        }

        image1 = findViewById(R.id.bookmartpic1);
        image2 = findViewById(R.id.bookmartpic2);
        image3 = findViewById(R.id.bookmartpic3);
        image4 = findViewById(R.id.bookmartpic4);

        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);

        // 다음 버튼 누를시 찜한 목록 다음거 보여줌
        Button nextBtn = findViewById(R.id.bookmarkNext);
        nextBtn.setOnClickListener(v -> {
            currentPage++;
            updatePage();
        });

        updatePage();
    }

    private void updatePage() {
        int start = currentPage * 4;

        // 현재 페이지 어디 찜 까지 보여줘야하는지 설정
        updateSlot(start, image1, star1);
        updateSlot(start + 1, image2, star2);
        updateSlot(start + 2, image3, star3);
        updateSlot(start + 3, image4, star4);
    }

    // 찜했던 이미지 화면에 보이게 하기 위한 함수
    private void updateSlot(int index, ImageView imageView, ImageButton starButton) {
        if (index < bookmarkedClothes.size()) {
            Clothes c = bookmarkedClothes.get(index);
            String name = c.imageClothes.replace(".jpg", "").replace(".png", "");
            int resId = getResources().getIdentifier(name, "drawable", getPackageName());

            if (resId != 0) {
                imageView.setImageResource(resId);
            } else {
                imageView.setImageDrawable(null);
            }

            // 찜했는지 안했는지 값 받아오기
            if (c.pick) {
                starButton.setImageResource(R.drawable.link1);
            } else {
                starButton.setImageResource(R.drawable.link2);
            }

            starButton.setOnClickListener(view -> {
                c.pick = !c.pick;

                if (c.pick) {
                    starButton.setImageResource(R.drawable.link1);
                    Toast.makeText(this, "찜 추가됨", Toast.LENGTH_SHORT).show();
                } else {
                    starButton.setImageResource(R.drawable.link2);
                    Toast.makeText(this, "찜 취소됨", Toast.LENGTH_SHORT).show();
                }
            });


            // 이미지를 클릭하면 이미지 관련 정보창 보여줌
            imageView.setOnClickListener(v -> {
                String msg = "사진 이름: " + c.imageClothes + "\n"
                        + "온도: " + c.temperature + "도\n"
                        + "날씨: " + c.weather + "\n"
                        + "스타일: " + c.style + "\n"
                        + "이름: " + c.clothesP + "\n"
                        + "링크: " + c.link;

                // AlertDialog.Builder 팝업창을 만드는 클래스
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("옷 정보")
                        .setMessage(msg)
                        .setPositiveButton("확인", null); // 누를시 그냥 다이얼로그 화면 닫김
                // 링크가 있을 경우 링크열기 누르면 그 해당 uri로 이동
                if (c.link != null && c.link.startsWith("http")) {
                    builder.setNeutralButton("링크 열기", (dialog, which) -> {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c.link));
                        startActivity(intent);
                    });
                }

                builder.show();
            });

        } else {
            // 아무 것도 없을 시 null로 표시
            imageView.setImageDrawable(null);
            starButton.setImageDrawable(null);
            imageView.setOnClickListener(null);
            starButton.setOnClickListener(null);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(bookMark_J.this, community_J.class);
        startActivity(intent);
    }
}
