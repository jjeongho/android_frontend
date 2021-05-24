package com.deu.cse.volt.Dibs;

import java.util.ArrayList;

public class DibsItem {
    public String word;
    public String meaning;

    // 화면에 표시될 문자열 초기화
    public DibsItem(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // 입력받은 숫자의 리스트생성
    public static ArrayList<DibsItem> createContactsList(int numContacts) {
        ArrayList<DibsItem> contacts = new ArrayList<DibsItem>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new DibsItem("Ipad", "IpadAir"));
            contacts.add(new DibsItem("Iphon", "Iphon6"));
            contacts.add(new DibsItem("Macbook", "Macbook"));

        }

        return contacts;
    }

}