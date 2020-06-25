package com.example.attachfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private  final String TAG = getClass().getSimpleName();
    public static String getStorageCard = Environment.getExternalStorageDirectory().toString();
    private File folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initFile("indo");
//        createPdf("hello");
       // sendEmail();
    }


    private void createPdf(String sometext){
        // create a new document
        folder= new File(getStorageCard + "/G-Force-SFA-BDN/Config/password.txt");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"kps@gmail.com","skaa@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Sending Email Using Implicit Intent");
        intent.putExtra(Intent.EXTRA_TEXT,"Email body message");
        Uri uri = Uri.fromFile(folder);
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("message/rfc822");
        Intent  chooserIntent = Intent.createChooser(intent,"Send Email");
        startActivity(chooserIntent);


    }

    public  void initFile(String value) {
        File  folder= new File(getStorageCard + "/G-Force-SFA-BDN/Config/password.txt");
        if (!folder.exists()) {
            initFile("");
        }
        String pass;
        File file;
        file = new File(getStorageCard + "/G-Force-SFA-BDN/Config/password.txt");
        if (value.trim().equals("")) {
            pass = "admin";
        } else {
            pass = value;
        }
        byte[] encodeValue = pass.getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.encodeToString(encodeValue, Base64.DEFAULT);
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(encoded);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "initFile: "+ e.getLocalizedMessage() );
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void sendEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"kps@gmail.com","skaa@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Sending Email Using Implicit Intent");
        intent.putExtra(Intent.EXTRA_TEXT,"Email body message");
        Uri uri = Uri.parse("cd");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("message/rfc822");
        Intent  chooserIntent = Intent.createChooser(intent,"Send Email");
        startActivity(chooserIntent);
    }
}
