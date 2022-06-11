package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutapp.roomdatabase.User;
import com.example.workoutapp.roomdatabase.UserDAO;
import com.example.workoutapp.roomdatabase.UserDataBase;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail,editPassword;
    Button buttonLogin;
    TextView textViewRegister;

    UserDAO db;
    UserDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        dataBase = Room.databaseBuilder(this,UserDataBase.class,"User")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                User user = db.getUser(email,password);

                if (user != null) {
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("User",user);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "UnRegistered User , or incorrect username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}