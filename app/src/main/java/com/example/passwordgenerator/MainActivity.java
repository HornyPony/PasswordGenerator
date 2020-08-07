package com.example.passwordgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.passwordgenerator.PasswordGenerator.generatePassword;

public class MainActivity extends AppCompatActivity {
    private CheckBox numbersCheckBox;
    private CheckBox lowercaseCheckBox;
    private CheckBox uppercaseCheckBox;
    private CheckBox specialSymbolsCheckBox;
    private EditText passwordLengthEditText;
    private EditText passwordsAmountEditText;
    private TextView generatedPasswordsTextView;
    private Button generatePasswordsButton;
    private ArrayList<Character> usableSymbolsArrayList;
    private String password;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbersCheckBox = findViewById(R.id.numbersCheckBox);
        lowercaseCheckBox = findViewById(R.id.lowercaseCheckBox);
        uppercaseCheckBox = findViewById(R.id.uppercaseCheckBox);
        specialSymbolsCheckBox = findViewById(R.id.specialSymbolsCheckBox);
        passwordLengthEditText = findViewById(R.id.passwordLengthEditText);
        passwordsAmountEditText = findViewById(R.id.passwordsAmountEditText);
        generatedPasswordsTextView = findViewById(R.id.generatedPasswordsTextView);
        generatePasswordsButton = findViewById(R.id.generatePasswordsButton);



        generatePasswordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder = new StringBuilder();
                if (!passwordLengthEditText.getText().toString().isEmpty()
                        && !passwordsAmountEditText.getText().toString().isEmpty()) {
                    usableSymbolsArrayList = new ArrayList<>();
                    String lengthAsString = passwordLengthEditText.getText().toString();
                    int length = Integer.parseInt(lengthAsString);
                    String amountAsString = passwordsAmountEditText.getText().toString();
                    int amount = Integer.parseInt(amountAsString);

                    checkBoxes();
                    if (!usableSymbolsArrayList.isEmpty()) {

                        for (int j = 0; j < amount; j++) {
                            password = generatePassword(length, usableSymbolsArrayList) + "  ";
                            stringBuilder.append(password);
                        }
                    }
                    generatedPasswordsTextView.setText(stringBuilder.toString());
                }else {
                    Toast toast = Toast.makeText(MainActivity.this, "Введите необходимые данные", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });

    }

    private void checkBoxes() {
        if (numbersCheckBox.isChecked()) {
            List<Character> numbers = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
            usableSymbolsArrayList.addAll(numbers);
        }
        if (lowercaseCheckBox.isChecked()) {
            String lowerCaseString = "qwertyuiopasdfghjklzxcvbnm";
            List<Character> lowercaseSymbols = convertStringToCharList(lowerCaseString);
            usableSymbolsArrayList.addAll(lowercaseSymbols);
        }
        if (uppercaseCheckBox.isChecked()) {
            String upperCaseString = "QWERTYUIOPASDFGHJKLZXCVBNM";
            List<Character> uppercaseSymbols = convertStringToCharList(upperCaseString);
            usableSymbolsArrayList.addAll(uppercaseSymbols);
        }
        if (specialSymbolsCheckBox.isChecked()) {
            String specialSymbolsString = "%*)?@#$~";
            List<Character> specialSymbols = convertStringToCharList(specialSymbolsString);
            usableSymbolsArrayList.addAll(specialSymbols);
        }
        if (usableSymbolsArrayList.isEmpty()){
            Toast.makeText(MainActivity.this, "Выберите символы, которые нужно использовать при генерации пароля", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String passwords = generatedPasswordsTextView.getText().toString();
        outState.putString("passwords", passwords);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String passwords = savedInstanceState.getString("passwords");
        generatedPasswordsTextView.setText(passwords);
    }

    private List<Character> convertStringToCharList(String str) {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }
}