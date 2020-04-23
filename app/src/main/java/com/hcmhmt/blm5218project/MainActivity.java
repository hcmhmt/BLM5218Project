package com.hcmhmt.blm5218project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btn_lgn;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_lgn = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.et_login_username);
        password = (EditText) findViewById(R.id.et_login_password);

        btn_lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString().trim();
                String user_password = password.getText().toString().trim();
                if(user_name.equals("admin") && user_password.equals("admin")){
                    Toast.makeText(MainActivity.this,"Giriş işlemi başarılıdır. Yönlendiriliyorsunuz!",Toast.LENGTH_LONG).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, ActivityMainMenu.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
                            finish();
                        }
                    }, 2000);


                }
                else {
                    Toast.makeText(MainActivity.this,"Giriş işlemi başarısız. Lütfen bilgilerinizi kontrol ediniz!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Çıkmak istediğinize emin misiniz?")
                .setCancelable(false)
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }
                })

                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
