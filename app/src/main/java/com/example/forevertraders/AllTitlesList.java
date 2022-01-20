package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AllTitlesList extends AppCompatActivity {
    ListView listView;
    DbTools dbTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_titles_list);
        setTitle("All Cart Titles List");
        listView=findViewById(R.id.titlesList);
        dbTools=new DbTools(getApplicationContext());
        ArrayList<HashMap<String,String>> AllTitles=dbTools.getAllTitles();
        if (AllTitles.size()!=0){
            SimpleAdapter simpleAdapter=new SimpleAdapter(this,AllTitles,R.layout.activity_title_entery,
                    new String[]{"_id","cartTitle"},
                    new int[]{R.id.txtViewID,R.id.txtViewTitle}
            );
            listView.setAdapter(simpleAdapter);
        }
    }
}