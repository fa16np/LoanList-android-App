package com.example.Loans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Dialog.DataPassOn {

    List<Debt> items;
//    ArrayAdapter<Debt> arrayAdt;
    ListView list;
    DebtAdapter arrayAdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items =  new ArrayList<>();
        arrayAdt =  new DebtAdapter(this, items );
        list = findViewById(R.id.ls);
        list.setAdapter(arrayAdt);

    }

    public void addDebt(View v){
        Dialog dg = new Dialog();
        dg.show(getSupportFragmentManager(),"Sample");
    }

//
//    https://www.youtube.com/watch?v=jcliHGR3CHo
//    https://www.youtube.com/watch?v=J7d1iseTV68&t=1426s


//    public void saveData(){
//        try{
//            File file = new File(this.getFilesDir(), "Saved");
//
//            FileOutputStream outSt = new FileOutputStream(file);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outSt));
//
//            for (int i = 0; i <items.size() ; i++) {
//                writer.write(String.valueOf(items.get(i)));
//
//            }
//
//
//        } catch (Exception e){
//
//        }
//    }


    public void saveData(){
        SharedPreferences sPreference = getSharedPreferences("shared preference",MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreference.edit();

    }



    @Override
    public void info(String n, String d, String a) {
        items.add(new Debt(a,n,d));
    }
}
