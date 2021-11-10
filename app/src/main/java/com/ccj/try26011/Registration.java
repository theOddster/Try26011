package com.ccj.try26011;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText username,password,confirmpass,firstName,lastName,email,contactNumber,Address;
    Button bRegister;
    DBUser db = new DBUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username=(EditText) findViewById(R.id.usernameR);
        password=(EditText) findViewById(R.id.passwordR);
        confirmpass=(EditText) findViewById(R.id.confirmPass);

        firstName=(EditText) findViewById(R.id.firstName);
        lastName=(EditText) findViewById(R.id.lastName);
        email= (EditText) findViewById(R.id.email);
        contactNumber=(EditText) findViewById(R.id.Cnumber);
        Address=(EditText) findViewById(R.id.address);

        bRegister=(Button) findViewById(R.id.register);

        bRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String u=username.getText().toString();
                String p=password.getText().toString();
                String pc=confirmpass.getText().toString();

                String f=firstName.getText().toString();
                String l=lastName.getText().toString();
                String e=email.getText().toString();
                String a=Address.getText().toString();
                String cn=contactNumber.getText().toString();

                if (u.contentEquals("") || p.contentEquals("") || pc.contentEquals("")|| f.contentEquals("") || l.contentEquals("") || e.contentEquals("") || a.contentEquals("") || cn.contentEquals("") )
                    Toast.makeText(Registration.this,"Please enter all information.",Toast.LENGTH_LONG).show();
                else if (p.contentEquals(pc))
                {
                    Boolean chkuser = db.checkUsername(u);
                    if (!chkuser)
                    {
                        Boolean insert = db.insertData(u, p, f, l, e, a, cn);
                        if (insert==true)
                        {
                            Toast.makeText(Registration.this, "Registration successful.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Registration.this, "Registration failed.", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(Registration.this, "User already exist kindly go to login screen.", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(Registration.this, "Password doesn't match.", Toast.LENGTH_LONG).show();
            }
        });
    }
}