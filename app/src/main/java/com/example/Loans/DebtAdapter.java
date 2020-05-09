package com.example.Loans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DebtAdapter extends ArrayAdapter {
    public DebtAdapter(Context context, List<Debt> debt) {
        super(context,0, debt);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Debt adebt = (Debt) getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.debts, parent, false);
        }

        TextView Name = (TextView) convertView.findViewById(R.id.Nim);
        TextView Amount = (TextView) convertView.findViewById(R.id.amn);

        Name.setText(adebt.name);
        Amount.setText(adebt.amount);

        return convertView;
    }
}
