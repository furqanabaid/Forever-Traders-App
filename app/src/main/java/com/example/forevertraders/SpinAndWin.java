package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpinAndWin extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Intent serviceIntent;
    TextView textView;
    String fileName="MyFile";
    Boolean isSpin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_and_win);
        setTitle("Spin and Win");
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        lottieAnimationView=findViewById(R.id.spinANdWin_animation);
        textView=findViewById(R.id.fileResult);
        serviceIntent=new Intent(getApplicationContext(),MyService.class);
    }

    public void startService(View view) {
        isSpin=true;
        lottieAnimationView.playAnimation();
        startService(serviceIntent);
    }

    public void stopService(View view) {
        lottieAnimationView.pauseAnimation();
        stopService(serviceIntent);
    }

    public void storeResult(View view) {
        if(isSpin){
        String Data="Free Delivery";
        FileOutputStream FOS;
        try {
            FOS =openFileOutput(fileName, Context.MODE_PRIVATE);
            FOS.write(Data.getBytes());
            textView.setText("");
            //Snackbar.make(view,"Data Saved",Snackbar.LENGTH_LONG).show();
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
            FOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }}
        else{
            Toast.makeText(getApplicationContext(), "Please Spin First", Toast.LENGTH_SHORT).show();
        }
    }

    public void showResult(View view) {
        String readData="";
        textView.setVisibility(TextView.VISIBLE);
        FileInputStream FIS;
        try {
            FIS=openFileInput(fileName);
            InputStreamReader ISR_Reader=new InputStreamReader(FIS);
            BufferedReader BIR_Reader=new BufferedReader(ISR_Reader);
            String readLine=BIR_Reader.readLine();
            if (readLine==null){
                Toast.makeText(this, "Not Data Found", Toast.LENGTH_SHORT).show();
            }
            else{
                String append=textView.getText().toString();
                while (readLine!=null){
                    if(append!=""){
                        readData=append+readData+readLine;
                        readLine=BIR_Reader.readLine();
                    }
                    else{
                        readData=readData+readLine;
                        readLine=BIR_Reader.readLine();
                    }

                }
                BIR_Reader.close();
                ISR_Reader.close();
                FIS.close();

            }
            textView.setText(readData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeResultSP(View view) {
    }

    public void showResultSP(View view) {
    }
}