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
import android.view.View.OnClickListener;

import java.sql.SQLException;

public class CreateTab extends Fragment {
    private View view;
    private AlertDialog.Builder builder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.create_layout,container,false);
        Button btnCreate = (Button) view.findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText txfName = (EditText) view.findViewById(R.id.txfCreateName);
                System.out.println(txfName);
                EditText txfLastName = (EditText) view.findViewById(R.id.txfCreateLastName);
                EditText txnsAge = (EditText) view.findViewById(R.id.txnsCreateAge);
                if (txnsAge.getText().toString().equals("")){
                    txnsAge.setError("Ingrese una edad que pertenesca al rango [1-125]");
                } else {
                    int age = Integer.valueOf(txnsAge.getText().toString());
                    try {
                        insertOnDataBase(txfName, txfLastName, txnsAge);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        return view;
    }

    private void insertOnDataBase(EditText txfName, EditText txfLastName, EditText txnsAge) throws SQLException {
        String name = txfName.getText().toString();
        String lastName = txfLastName.getText().toString();
        int age = 0;
        try {
            age = Integer.valueOf(txnsAge.getText().toString());
        } catch (Exception e){
            txnsAge.setError("Ingrese una edad que pertenesca al rango [1-125]");
        }
        // verifica para insertar
        if(name.length() <= 0 || lastName.length() <=0 || (age <= 0 || age > 125)) {
            if(name.length() <= 0){
                txfName.setError("El nombre no puede estar en blanco");
            }
            if(lastName.length() <= 0){
                txfLastName.setError("El apellido no puede estar en blanco");
            }
            if(age <= 0 || age > 125){
                txnsAge.setError("Ingrese una edad que pertenesca al rango [1-125]");
            }
        } else {
            //try {
                MySqlConnection connection = new MySqlConnection("192.168.100.19", "3306", "ProgramaMovil", "root", "Adrian.b.g.7");
                int result = connection.getSt().executeUpdate("call usp_Create_Formulario(\"" + name + "\", \"" + lastName + "\", " + age +  ")");
                txfName.setText("");
                txnsAge.setText("");
                txfLastName.setText("");
            //} catch (Exception e){
            //    System.out.println("se cayo esta basura");
            //}

        }
    }

    
}
