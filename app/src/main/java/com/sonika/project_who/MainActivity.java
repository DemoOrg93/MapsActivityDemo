package com.sonika.project_who;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText loginname, loginpassword;
    Button btnlogin;
    TextView textView_register, textView_forgotpwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginname = (EditText) findViewById(R.id.login_name);
        loginpassword = (EditText) findViewById(R.id.login_pwd);
        btnlogin = (Button) findViewById(R.id.btn_login);
        textView_register = (TextView) findViewById(R.id.login_register);
        textView_forgotpwd = (TextView) findViewById(R.id.login_forgotpwd);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

    }
}
