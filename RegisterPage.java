package com.jg00989.android_cw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    String username;
    String password;
    String password1;
    SQLiteDatabase mydatabase;
    TextView users;
    TextView password_field;
    TextView password1_field;


    public void registerButton(View view){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginPage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE,null);
        users = (TextView)findViewById(R.id.user);
        password_field = (TextView)findViewById(R.id.password);
        password1_field = (TextView)findViewById(R.id.passwordReentry);

        Button btn = (Button)findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = users.getText().toString().trim();
                password1 = password_field.getText().toString().trim();
                password =  password1_field.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty() || password1.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.equals(password1))
                    {
                        String query="SELECT * FROM Accounts WHERE Username='"+username+"';";
                        Cursor resultSet = mydatabase.rawQuery(query,null);
                        if(resultSet.moveToFirst())
                        {
                            Toast.makeText(getApplicationContext(),"The username already exists!",Toast.LENGTH_LONG).show();
                            resultSet.close();
                        }
                        else
                        {
                            resultSet.close();
                            try{
                                //String query1 = String.format("");
                                mydatabase.execSQL("INSERT INTO Accounts VALUES('"+username+"','"+password+"');");
                            }
                            catch(Exception e)
                            {
                                Toast.makeText(getApplicationContext(), "Did not add for some reason", Toast.LENGTH_LONG).show();
                            }
                            startActivity(new Intent(RegisterPage.this, LoginPage.class));
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Passwords do not match!!!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button btn1 = (Button)findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this, LoginPage.class));
            }
        });


    }
}
