package com.hcmhmt.blm5218project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySendEmail extends AppCompatActivity {

    EditText et_email, et_subject, et_message;
    TextView attachment;
    Button send;
    String email, subject, message;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        et_email = findViewById(R.id.et_receiver);
        et_subject = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_msg);
        attachment = findViewById(R.id.tw_attachment);
        send = findViewById(R.id.btn_send_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            attachment.setText(URI.getLastPathSegment());
            attachment.setVisibility(View.VISIBLE);
        }
    }

    public void sendEmail() {
        try {
            email = et_email.getText().toString();
            subject = et_subject.getText().toString();
            message = et_message.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent, "Mail Gönderiliyor..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Bir hata oluştu: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void openFolder() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Tamamla"), PICK_FROM_GALLERY);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivitySendEmail.this, ActivityMainMenu.class);
        startActivity(intent);
    }

}
