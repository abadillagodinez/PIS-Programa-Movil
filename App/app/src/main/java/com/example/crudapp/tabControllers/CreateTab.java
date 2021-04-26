package com.example.crudapp.tabControllers;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crudapp.*;
import com.example.crudapp.Modelo.MySqlConnection;
import com.example.crudapp.Modelo.SqlServerConnection;

import android.view.View.OnClickListener;

import java.sql.SQLException;

public class CreateTab extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.create_layout,container,false);
        Button btnCreate = view.findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText txfName = (EditText) view.findViewById(R.id.txfCreateName);
                EditText txfLastName = (EditText) view.findViewById(R.id.txfCreateLastName);
                EditText txnsAge = (EditText) view.findViewById(R.id.txnsCreateAge);
                if (verifyData(txfName, txfLastName, txnsAge)){
                    insertOnDataBase(txfName,txfLastName,txnsAge);
                }
            }
        });
        return view;
    }

    public boolean verifyData(EditText pTxfName, EditText pTxfLastName, EditText pTxnsAge){
        boolean result = false;
        String name = pTxfName.getText().toString();
        String lastName = pTxfLastName.getText().toString();
        String age = pTxnsAge.getText().toString();
        if(name.equals("")){
            pTxfName.setError("Ingrese un nombre.");
            return result;
        } else if(lastName.equals("")){
            pTxfLastName.setError("Ingrese un apellido.");
            return result;
        } else if(age.equals("")){
            pTxnsAge.setError("Ingrese una edad.");
            return result;
        } else if(!(age.equals(""))){
            int numberAge = Integer.parseInt(age);
            if(numberAge > 125 || numberAge <= 0){
                pTxnsAge.setError("Ingrese una edad en el siguiente rango [1,125].");
                return result;
            }
        }
        return !result;
    }

    private void insertOnDataBase(EditText txfName, EditText txfLastName, EditText txnsAge) {
        String name = txfName.getText().toString();
        String lastName = txfLastName.getText().toString();
        String age = txnsAge.getText().toString();

        try {
            //MySqlConnection connection = new MySqlConnection("192.168.100.19", "3306", "ProgramaMovil", "root", "Adrian.b.g.7");
            SqlServerConnection connection = new SqlServerConnection("192.168.100.32",  "ProgramaMovil", "programaMovil", "Adrian.b.g.7");
            int result = connection.getSt().executeUpdate("exec usp_Create_Formulario '"+ name + "', '" + lastName + "'," + age);
            txfName.setText("");
            txnsAge.setText("");
            txfLastName.setText("");
            connection.getConnection().close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
