package com.example.weather_choose;
import java.io.Serializable;

// < 옷 정보 구조체 >

// Serializable : 파일 전송, 저장을 도우는 기능
// Serializable가 아닐시 intent로 객체 보낼 때 포장 안해줘서 못보냄
public class Clothes implements Serializable {
    public String link;

    public String imageClothes;
    public int temperature;
    public String weather;
    public String style;
    public String clothesP;
    public boolean pick;

    public Clothes(String link, String imageClothes, int temperature, String weather, String style, String clothesP, boolean pick) {
        this.link = link;
        this.imageClothes = imageClothes;
        this.temperature = temperature;
        this.weather = weather;
        this.style = style;
        this.clothesP = clothesP;
        this.pick = pick;
    }
}


