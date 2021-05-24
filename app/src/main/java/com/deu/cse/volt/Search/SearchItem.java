package com.deu.cse.volt.Search;

import java.util.ArrayList;

public class SearchItem {
    public String word;
    public String meaning;

    // 화면에 표시될 문자열 초기화
    public SearchItem(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // 입력받은 숫자의 리스트생성
    public static ArrayList<SearchItem> createContactsList(int numContacts) {
        ArrayList<SearchItem> contacts = new ArrayList<SearchItem>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new SearchItem("Ipad", "1. 아이폰 12 Pro Max"));


        }

        return contacts;
    }

}