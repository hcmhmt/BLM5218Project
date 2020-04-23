package com.hcmhmt.blm5218project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityMainMenu extends AppCompatActivity {

    private TextView email, info, light, ivme, settings, list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        email = findViewById(R.id.tw_email);
        settings = findViewById(R.id.tw_settings);
        list = findViewById(R.id.tw_list_users);
        light = findViewById(R.id.tw_light);
        ivme = findViewById(R.id.tw_ivme);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivitySendEmail.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivityUserSettings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivityListUsers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivitySensor.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
        ivme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this, ActivityMotionlessPhone.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right, R.anim.slide_out_left);
            }
        });
    }

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
