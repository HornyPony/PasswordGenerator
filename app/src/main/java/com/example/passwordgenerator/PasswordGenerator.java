package com.example.passwordgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {

    public static String generatePassword(int length, ArrayList<Character> usableSymbolsArrayList){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++){
            char c = usableSymbolsArrayList.get(random.nextInt(usableSymbolsArrayList.size()));
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
