package fahad.android.loan_list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class Dialog extends AppCompatDialogFragment {

    EditText name;
    EditText amount;
//    EditText des;
    private DataPassOn passData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            passData = (DataPassOn) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "implement data pass on");
        }

    }


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstancestate){
        AlertDialog.Builder bd = new AlertDialog.Builder(getActivity());

        LayoutInflater infal = getActivity().getLayoutInflater();
        View v = (View) infal.inflate(R.layout.ldialog, null);

        bd.setView(v).setTitle("ADD").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Closed!", Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               try {
                   double am = Double.parseDouble(amount.getText().toString());
                   String nm = name.getText().toString();
//                String de = des.getText().toString();
                   if (!nm.equals("")) {
                       passData.info(nm, "", Double.toString(am));
                   }
               }catch (NumberFormatException e){
                   Toast.makeText(getContext(), "Empty input",Toast.LENGTH_SHORT);
               }catch (NullPointerException e){
                   Toast.makeText(getContext(), "Empty input",Toast.LENGTH_SHORT);

               }
            }
        });

        name = v.findViewById(R.id.name);
        amount = v.findViewById(R.id.Amount);
//        des = v.findViewById(R.id.Des);

        return bd.create();



    }


    public interface DataPassOn {
        void info(String n, String d, String a);
    }
}
