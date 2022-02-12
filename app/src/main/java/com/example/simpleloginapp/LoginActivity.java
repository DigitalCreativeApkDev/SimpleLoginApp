package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    // This class is inspired by the code in the following source.
    // https://www.sourcecodester.com/android/11968/android-simple-login-application-beginners.html

    final int MIN_PASSWORD_LENGTH = 8;
    final Pattern EMAIL_VALIDITY_PATTERN = Pattern.compile("^(.+)@(.+)$");
    final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=~`,.<>?/:;\"\'|\\{}[]";
    final String DIGITS = "1234567890";
    final String UPPERCASE_LETTERS = "QWERTYUIOPASDFGHJKLZXCVBNM";
    final String LOWERCASE_LETTERS = "qwertyuiopasdfghjklzxcvbnm";
    EditText etEmailAddress, etPersonName, etPhoneNumber, etPassword;
    Button loginButton;

    private int getNumSpecialCharacters(String string) {
        int numSpecialCharacters = 0; // initial value

        for (int i = 0; i < string.length(); i++) {
            if (SPECIAL_CHARACTERS.contains(string.substring(i, i + 1))) {
                numSpecialCharacters++;
            }
        }

        return numSpecialCharacters;
    }

    private int getNumDigits(String string) {
        int numDigits = 0; // initial value

        for (int i = 0; i < string.length(); i++) {
            if (DIGITS.contains(string.substring(i, i + 1))) {
                numDigits++;
            }
        }

        return numDigits;
    }

    private int getNumUppercaseLetters(String string) {
        int numUppercaseLetters = 0; // initial value

        for (int i = 0; i < string.length(); i++) {
            if (UPPERCASE_LETTERS.contains(string.substring(i, i + 1))) {
                numUppercaseLetters++;
            }
        }

        return numUppercaseLetters;
    }

    private int getNumLowercaseLetters(String string) {
        int numLowercaseLetters = 0; // initial value

        for (int i = 0; i < string.length(); i++) {
            if (LOWERCASE_LETTERS.contains(string.substring(i, i + 1))) {
                numLowercaseLetters++;
            }
        }

        return numLowercaseLetters;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
    }

    public void login() {
        // Getting the edit text fields.
        etEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        etPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        etPassword = (EditText) findViewById(R.id.editTextTextPassword);

        // Getting the login button.
        loginButton = (Button) findViewById(R.id.login_button);

        // Add functionality to the login button.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Conditions need to be met for the login to be successful.
                // 1. Username filled
                // 2. Email address filled in valid format.
                // 3. Password has at least 8 characters.
                // 4. Password must have at least one digit, one uppercase letter,
                // one lowercase letter, and one special character.
                boolean usernameFilled = etPersonName.getText().toString().length() > 0;
                boolean validEmail = EMAIL_VALIDITY_PATTERN.
                        matcher(etEmailAddress.getText().toString()).matches();
                boolean passwordLongEnough = etPassword.getText().toString().length() > MIN_PASSWORD_LENGTH;
                boolean hasAllCharacterTypes = getNumDigits(etPassword.getText().toString()) > 0 &&
                        getNumSpecialCharacters(etPassword.getText().toString()) > 0 &&
                        getNumLowercaseLetters(etPassword.getText().toString()) > 0 &&
                        getNumUppercaseLetters(etPassword.getText().toString()) > 0;
                if (usernameFilled && validEmail && passwordLongEnough && hasAllCharacterTypes) {
                    Toast.makeText(LoginActivity.this, "Your login information is valid!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                    intent.putExtra("username", etPersonName.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid email address, username, or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}