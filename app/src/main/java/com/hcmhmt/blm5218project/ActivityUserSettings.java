package com.hcmhmt.blm5218project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUserSettings extends AppCompatActivity {

    private Spinner s_gender, s_mode;
    private EditText et_username, et_yas, et_height, et_kilo;
    private Button btnSave;
    private SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "pref_settings" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        s_gender = findViewById(R.id.s_gender);
        s_mode = findViewById(R.id.s_appmode);
        et_username = findViewById(R.id.et_settings_username);
        et_yas = findViewById(R.id.et_settings_yas);
        et_height = findViewById(R.id.et_settings_height);
        et_kilo = findViewById(R.id.et_settings_kilo);
        btnSave = findViewById(R.id.btn_settings_save);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        addItemsOnGender();
        addItemsOnAppMode();

        if(!sharedPreferences.getString("username","").isEmpty())
            et_username.setText(sharedPreferences.getString("username",""));

        if(!sharedPreferences.getString("age","").isEmpty())
            et_yas.setText(sharedPreferences.getString("age",""));

        if(!sharedPreferences.getString("kilo","").isEmpty())
            et_kilo.setText(sharedPreferences.getString("kilo",""));

        if(!sharedPreferences.getString("height","").isEmpty())
            et_height.setText(sharedPreferences.getString("height",""));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username  = et_username.getText().toString();
                String gender  = s_gender.getSelectedItem().toString();
                String yas  = et_yas.getText().toString();
                String kilo  = et_kilo.getText().toString();
                String height  = et_height.getText().toString();
                String mode  = s_mode.getSelectedItem().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username", username);
                editor.putString("gender", gender);
                editor.putString("age", yas);
                editor.putString("kilo", kilo);
                editor.putString("height", height);
                editor.putString("appmode", mode);
                editor.commit();

                Toast.makeText(ActivityUserSettings.this,"Bilgileriniz Geçici Olarak Kaydedildi!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void addItemsOnAppMode() {
        List<String> list = new ArrayList<String>();

        if(!sharedPreferences.getString("appmode","").isEmpty()){
            String gender = sharedPreferences.getString("appmode","");
            if(gender.equals("Light")){
                list.add("Light");
                list.add("Dark");
            }else if(gender.equals("Dark")){
                list.add("Dark");
                list.add("Light");
            }
        }else{
            list.add("Dark");
            list.add("Light");
        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_mode.setAdapter(dataAdapter);
    }

    public void addItemsOnGender() {

        List<String> list = new ArrayList<String>();

        if(!sharedPreferences.getString("gender","").isEmpty()){
            String gender = sharedPreferences.getString("gender","");
            if(gender.equals("Erkek")){
                list.add("Erkek");
                list.add("Kadın");
            }else if(gender.equals("Kadın")){
                list.add("Kadın");
                list.add("Erkek");
            }
        }else{
            list.add("Erkek");
            list.add("Kadın");
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_gender.setAdapter(dataAdapter);
    }

}
