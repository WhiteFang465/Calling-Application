package com.atulj.callingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "registerName";
    public static final String EXTRA_NUMBER = "register_Number";
    public static final String  IS_REGISTERED = "isRegistered";
    private EditText registerName;
    private EditText registerPhone;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerName = findViewById(R.id.RegisterActivityName);
        registerPhone = findViewById(R.id.RegisterActivityPhone);
        registerButton = findViewById(R.id.RegisterActivityPhoneRegisterButton);

        registerButton.setOnClickListener(v -> {

            Intent sendDataToMainActivity = new Intent()
                    .putExtra(EXTRA_NAME, registerName.getText().toString())
                    .putExtra(EXTRA_NUMBER, registerPhone.getText().toString())
                    .putExtra(IS_REGISTERED,"true");
            setResult(RESULT_OK, sendDataToMainActivity);
            finish();
        });
    }
}