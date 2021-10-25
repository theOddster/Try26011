package com.ccj.try26011;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signup = (Button) findViewById(R.id.register);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    private void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}