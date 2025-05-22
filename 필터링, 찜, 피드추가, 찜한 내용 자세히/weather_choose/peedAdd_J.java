package com.example.weather_choose;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class peedAdd_J extends AppCompatActivity {

    EditText link_text, tem_text, weather_text, style_text, clothes_name, imageName;
    Button button_add;
    ImageButton pictureBn_add;

    private static final int PICK_IMAGE_REQUEST = 1;

    // 추가
    private boolean imageSelected = false;
    private String selectedImageName = "";

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

        link_text = findViewById(R.id.PeedLink);
        tem_text = findViewById(R.id.PeedTem);
        weather_text = findViewById(R.id.PeedWea);
        style_text = findViewById(R.id.PeedSty);
        button_add = findViewById(R.id.PeedAdd);
        pictureBn_add = findViewById(R.id.image_down);
        clothes_name = findViewById(R.id.clothesNm);
        imageName = findViewById(R.id.imageName);

        pictureBn_add.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), PICK_IMAGE_REQUEST);
        });

        button_add.setOnClickListener(v -> {
            String link = link_text.getText().toString();
            String tempStr = tem_text.getText().toString();
            String weather = weather_text.getText().toString();
            String style = style_text.getText().toString();
            String clothesP = clothes_name.getText().toString();
            String manualImageName = imageName.getText().toString();

            // 추가
            String finalImageName;
            if (imageSelected) {
                finalImageName = selectedImageName;
            } else {
                finalImageName = manualImageName;
            }

            if (!finalImageName.isEmpty() && !link.isEmpty() && !tempStr.isEmpty() && !weather.isEmpty() && !style.isEmpty()) {
                int temperature = Integer.parseInt(tempStr);
                String clothesName = clothesP.isEmpty() ? "이름 없음" : clothesP;

                // 추가
                Clothes clothes = new Clothes(link, finalImageName, temperature, weather, style, clothesName, false);
                Closet.addClothes(clothes);

                Intent intent = new Intent(peedAdd_J.this, community_J.class);
                startActivity(intent);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            // 추가
            imageSelected = true;

            Cursor cursor = getContentResolver().query(selectedImageUri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                selectedImageName = cursor.getString(nameIndex); // 추가
                cursor.close();
            }

            pictureBn_add.setImageURI(selectedImageUri);
        }

    }
}
