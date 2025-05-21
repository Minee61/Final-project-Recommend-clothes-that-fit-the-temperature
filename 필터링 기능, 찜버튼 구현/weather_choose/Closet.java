package com.example.weather_choose;

import java.util.ArrayList;
public class Closet {
    // 옷장 (객체) 미리 생성
    static ArrayList<Clothes> clothesList = new ArrayList<>();

    // 기본 옷장안에 있을 옷들
    static {
        clothesList.add(new Clothes("link1.jpg", 15, "맑음", "캐주얼", "후드티", false));
        clothesList.add(new Clothes("link2.jpg", 18, "비", "댄디", "셔츠", false));
        clothesList.add(new Clothes("link3.jpg", 23, "흐림", "스트릿", "청자켓", false));
    }

    // 리스트 안에 값을 불러와줌
    public static ArrayList<Clothes> getClothesList() {
        return clothesList;
    }

    // 리스트 안에 값을 새로 넣음
    public static void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }
}


