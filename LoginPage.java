package com.jg00989.android_cw;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LoginPage extends AppCompatActivity {

    private TextView uname;
    private TextView pword;
    private Button login;
    private String username="";
    private String password="";
    SQLiteDatabase mydatabase;

    int REQ_ACCESS_FINE_LOCATION;
    int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startActivity(new Intent(Login_Page.this, TableView.class));



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQ_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }


            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
            }

        }



        setContentView(R.layout.activity_login);
        uname = (TextView)findViewById(R.id.username);
        pword = (TextView)findViewById(R.id.password);
        mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Accounts(Username VARCHAR(255) PRIMARY KEY,Password VARCHAR(255));");

        try{
            mydatabase.execSQL("INSERT INTO Accounts VALUES('admin','1234');");
          }
        catch(Exception e){
            //Toast.makeText(getApplicationContext(),"Failed to add two rows",Toast.LENGTH_LONG).show();

//            Toast.makeText(getApplicationContext(),"Not autoincrementing",Toast.LENGTH_LONG).show();
        }
        login = (Button)findViewById(R.id.login);
        Button notMember = (Button)findViewById(R.id.register);
        notMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, RegisterPage.class));
            }
        });


    }


    public void login(View view){

        this.username=uname.getText().toString().trim();
        this.password=pword.getText().toString().trim();
        String query= "Select * from Accounts where Username='"+username+"'";
        Cursor resultSet1 = mydatabase.rawQuery(query,null);
        if(resultSet1.moveToFirst()){
            if(resultSet1.getString(0).equals(username))
            {
                if(resultSet1.getString(1).equals(password))
                {
                    resultSet1.close();
                    Intent loggingIn = new Intent(LoginPage.this,MainActivity.class);
                    loggingIn.putExtra("user",this.username);
                    startActivity(loggingIn);
                }
                else
                {
                    resultSet1.close();
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT);
                }
            }

        }
        else
        {
            resultSet1.close();


        }


    }

//    public void tableView(View view){
//        startActivity(new Intent(Login_Page.this,TableView.class));
//    }



}
