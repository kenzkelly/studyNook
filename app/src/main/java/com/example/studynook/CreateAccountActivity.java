package com.example.studynook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    static final String TAG = "CreateAccountActivity";
    private UserOpenHelper helper;

    private Button create;

    private String usernameStr;
    private String passwordStr;

    EditText username;
    EditText password;
    EditText confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        create = findViewById(R.id.createButton);

        helper = new UserOpenHelper(this);

        username = findViewById(R.id.usernameInp);
        password = findViewById(R.id.passwordInp);
        confirm_password = findViewById(R.id.passwordConf);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameStr = username.getText().toString();
                passwordStr = password.getText().toString();

                if(isEmpty(usernameStr) || isEmpty(passwordStr))
                    requirementsFailed();
                else{
                    if(passwordStr.length() < 5)
                        requirementsFailed();
                    else{
                        User user = new User(usernameStr, passwordStr);
                        Intent intent = new Intent();
                        intent.putExtra("username", usernameStr);
                        intent.putExtra("password", passwordStr);
                        setResult(1,intent);
                        CreateAccountActivity.this.finish();
                    }
                }
            }
        });
    }

    private void requirementsFailed()
    {
        if(isEmpty(usernameStr))
        {
            Toast toast = Toast.makeText(CreateAccountActivity.this, "looks like you forgot a username", Toast.LENGTH_LONG);
            toast.show();
        }

        if(isEmpty(passwordStr))
        {
            Toast toast = Toast.makeText(CreateAccountActivity.this, "looks like you forgot a password", Toast.LENGTH_LONG);
            toast.show();
        }

        if(checkPassword(passwordStr))
        {
            Toast toast = Toast.makeText(CreateAccountActivity.this, "you need at least 5 characters in your password", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private boolean checkPassword(String p){
        //checking it's more than 5 characters
        if(p.length() < 5)
            return false;

        return true;
    }

    private boolean isEmpty(String s)
    {
        if(s.length() == 0)
            return true;

        return false;
    }

}
