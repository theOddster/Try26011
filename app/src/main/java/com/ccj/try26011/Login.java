package com.ccj.try26011;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button bLogin, bRegister;
    DBHelper db = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameL);
        password = findViewById(R.id.passwordL);

        bRegister = (Button) findViewById(R.id.signup);
        bLogin = (Button) findViewById(R.id.login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Blank input not allowed", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserAccount = db.checkUserAccount(user, pass);
                    if (checkUserAccount == true) {
                        Toast.makeText(Login.this, "Sign in Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Home.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Invalid username and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
            }
        });
    }

}