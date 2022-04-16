package com.atulj.callingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static boolean isRegistered = false;
    String name;
    String phone;
    private Button register;
    private Button call;
    private TextView textView;
    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent resultFromSendDataBackActivity = result.getData();
                    if (resultFromSendDataBackActivity == null) return;

                    if (resultFromSendDataBackActivity.hasExtra(RegisterActivity.EXTRA_NAME)) {

                        name = resultFromSendDataBackActivity.getStringExtra(RegisterActivity.EXTRA_NAME);

                    }
                    if (resultFromSendDataBackActivity.hasExtra(RegisterActivity.EXTRA_NUMBER)) {

                        phone = resultFromSendDataBackActivity.getStringExtra(RegisterActivity.EXTRA_NUMBER);

                    }

                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.MainActivityRegisterButton);

        call = findViewById(R.id.MainActivityCallButton);
        Log.e("isRegistered", "is registered" + isRegistered);


        if (name != null && phone != null) {

            register.setText("Register");
            Log.e("Registered", "Registration DOne" + name);
        } else {
            Log.e("Name", "Name " + name);
            Log.e("phone", "phone :" + phone);
            register.setOnClickListener(v -> {
               changeActivityToRegister(v);
            });
        }
    }
    private void changeActivityToRegister(View view){
        Intent goToRegisterScreen = new Intent(this, RegisterActivity.class);
        launcher.launch(goToRegisterScreen);
    }
}