package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {
    private Button addUserBtn;
    private EditText nameEditText;
    private EditText lastnameEditText;
    private EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        nameEditText = findViewById(R.id.nameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        addUserBtn = findViewById(R.id.addUserBtn);
        editTextPhone = findViewById(R.id.editTextPhone);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameEditText.getText().toString();
                String userLastname = lastnameEditText.getText().toString();
                String userPhone = editTextPhone.getText().toString();
                User user = new User();
                user.setUserName(userName);
                user.setUserLastName(userLastname);
                user.setPhone(userPhone);
                UserList userList = UserList.get(AddUserActivity.this);
                userList.addUser(user);
                onBackPressed();
            }
        });
    }
}