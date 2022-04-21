package com.atulj.callingapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private String name;
    private String phone;
    private Button register;
    private Button call;
    private TextView textView;
    private ActivityResultLauncher<Intent> getRegisteredDetails = registerForActivityResult(
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.appbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.more) {
            Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.MainActivityRegisterButton);
        textView = findViewById(R.id.MainActivityTextView);

        register.setOnClickListener(this::changeActivityToRegister);

        call = findViewById(R.id.MainActivityCallButton);

        Intent getData = getIntent();

        Log.e("Name", "Name " + name);
        Log.e("phone", "phone :" + phone);


        if (getData.hasExtra(RegisterActivity.EXTRA_NAME) || getData.hasExtra(RegisterActivity.EXTRA_NUMBER)) {


            name = getData.getExtras().getString(RegisterActivity.EXTRA_NAME);
            phone = getData.getExtras().getString(RegisterActivity.EXTRA_NUMBER);
            register.setText("Logout");
            Log.e("Registration done", " done registration");
            Log.e("Name", "Name " + name);
            Log.e("phone", "phone :" + phone);
            textView.setText("Hello " + name);

            call.setOnClickListener(v -> {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                Snackbar.make(register, "Call", BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .setAction(R.string.call_label, v1 -> {
                            phoneIntent.setData(Uri.parse("tel:" + phone));
                            startActivity(phoneIntent);
                        })
                        .show();


            });

        } else {
            Log.e("Name", "Name " + name);
            Log.e("phone", "phone :" + phone);
            Log.e("Not registered", "not registered");
            call.setOnClickListener(v -> {
                Snackbar.make(register, "Please Register to use this feature", BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .show();
            });
        }
    }

    private void changeActivityToRegister(View view) {
        Intent goToRegisterScreen = new Intent(this, RegisterActivity.class);
        getRegisteredDetails.launch(goToRegisterScreen);

    }

}