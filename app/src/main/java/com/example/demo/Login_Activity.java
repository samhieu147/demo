package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    private EditText edEmail, edPass;
    private CheckBox checkBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        init();
        edEmail.setText(sharedPreferences.getString("email",""));
        edPass.setText(sharedPreferences.getString("password",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked",false));

    }

    private void init() {
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edpassword);
        checkBox = findViewById(R.id.ckn);
    }

    public void login(View view) {

        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        

        if(pass.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"K bo trong",Toast.LENGTH_LONG).show();
        } else if(!email.matches(emailPattern)) {
            Toast.makeText(this,"Email dung dinh dang",Toast.LENGTH_LONG).show();
        }else if (pass.length()<=6){
            Toast.makeText(this,"mat Khau lon hon 6 ky tu",Toast.LENGTH_LONG).show();
        }
        else {
            if(checkBox.isChecked()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email",email);
                editor.putString("password",pass);
                editor.putBoolean("checked",true);
                editor.commit();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.remove("checked");
                editor.commit();
            }

            startActivity(new Intent(Login_Activity.this, MainActivity.class));
        }
    }

}