package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
{
    EditText editTextTextPersonName, editTextTextEmailAddress, editTextTextPassword;
    Button button3;
    DatabaseReference reff;
    User user; //만들어놓은 자바 클래스 활용
    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        button3=findViewById(R.id.button3);

        reff= FirebaseDatabase.getInstance().getReference().child("User");

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertuserdata();
            }
        });
    }
    private void  insertuserdata(){

        String name = editTextTextPersonName.getText().toString();
        String email = editTextTextEmailAddress.getText().toString();
        String password = editTextTextPassword.getText().toString();

        User user = new User(name, email, password);

        reff.child("user").setValue(user);
        Toast.makeText(MainActivity.this,"data inserted sucessfully",Toast.LENGTH_LONG).show();
    }
}

