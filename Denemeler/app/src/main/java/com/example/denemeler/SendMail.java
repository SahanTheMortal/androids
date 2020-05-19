package com.example.denemeler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendMail extends AppCompatActivity {

    private String to,subject,mail,username;
    private EditText editTo,editSubject,editMail;
    private TextView files;
    private Button attachment,send,back;

    android.net.Uri URI = null;
    private static final int PICKFILE_RESULT_CODE = 101;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent i=getIntent();
        username=i.getStringExtra("username");

        editTo=(EditText) findViewById(R.id.editText1);
        editSubject=(EditText) findViewById(R.id.editText2);
        editMail=(EditText) findViewById(R.id.editText3);
        files=(TextView) findViewById(R.id.textView);
        attachment=(Button) findViewById(R.id.button1);
        send=(Button) findViewById(R.id.button2);
        back=(Button) findViewById(R.id.button3);

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("*/*");
                String[] extraMimeTypes = {"image/*", "video/*","sound/*","application/*"};
                i.putExtra(Intent.EXTRA_MIME_TYPES, extraMimeTypes);
                startActivityForResult(i, PICKFILE_RESULT_CODE);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to=editTo.getText().toString();
                subject=editSubject.getText().toString();
                mail=editMail.getText().toString();
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_EMAIL,new String[] {to} );
                i.putExtra(Intent.EXTRA_SUBJECT,subject);
                i.putExtra(android.content.Intent.EXTRA_TEXT, mail);
                if (URI != null) {
                    i.putExtra(Intent.EXTRA_STREAM, URI);
                }

                startActivity(Intent.createChooser(i, "Sending email..."));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainPage.class);
                i.putExtra("username",username);
                startActivity(i);
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    URI=data.getData();
                    String FilePath = data.getData().getPath();
                    files.setText(FilePath);
                }
                break;

        }
    }
}
