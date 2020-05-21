package fahad.android.loan_list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Dialog.DataPassOn {

    List<Debt> items;
    ListView list;
    DebtAdapter arrayAdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadContent();
    }

    public void loadContent(){
        loadAcc();
        arrayAdt =  new DebtAdapter(this, items );
        list = findViewById(R.id.ls);
        list.setAdapter(arrayAdt);
    }

    public void addDebt(View v){
        Dialog dg = new Dialog();
        dg.show(getSupportFragmentManager(),"Sample");
    }


    private void savAcc(){
        SharedPreferences pref =  getSharedPreferences("Udhar",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson =  new Gson();
        String json = gson.toJson(items);
        editor.putString("udhar",json);
        editor.apply();
    }


    private void loadAcc(){
        SharedPreferences pref =  getSharedPreferences("Udhar",MODE_PRIVATE);
        Gson gson =  new Gson();
        String json = pref.getString("udhar",null);
        Type type = new TypeToken<ArrayList<Debt>>() {}.getType();
        items = gson.fromJson(json,type);

        if ((items == null)) {
            items = new ArrayList<Debt>();
        }
    }



    public void deleteLoan(View view){
        View prnt = (View) view.getParent().getParent();
        TextView loanName = (TextView) prnt.findViewById(R.id.Nim);
        TextView loanAmnt = (TextView) prnt.findViewById(R.id.amn);
        String loan = String.valueOf(loanName.getText());
        String loanA = String.valueOf(loanAmnt.getText());
        for (int i =0; i<items.size(); i++ ){
            if(items.get(i).name.equals(loan) && items.get(i).amount.equals(loanA)){
                System.out.println(items.get(i).name + "   " + loan);
                items.remove(i);
            }
        }
        savAcc();
        loadContent();

    }

    @Override
    public void info(String n, String d, String a) {
        items.add(new Debt(a,n,d));
        savAcc();
    }
}
