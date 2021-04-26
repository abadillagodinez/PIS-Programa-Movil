package com.example.crudapp.tabControllers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crudapp.Modelo.SqlServerConnection;
import com.example.crudapp.R;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReadTab extends Fragment {
    private View view;
    private Button btnRead;
    private ListView lstRegistros;
    private ArrayList<String> registros = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.read_layout,container,false);
        btnRead = view.findViewById(R.id.btnRead);
        lstRegistros = view.findViewById(R.id.lstReadRegistros);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtItem, registros);
        lstRegistros.setAdapter(adapter);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromDatabase();
            }
        });

        return view;
    }

    private void readFromDatabase(){
        try {
            registros.clear();
            SqlServerConnection connection = new SqlServerConnection("192.168.100.32",  "ProgramaMovil", "programaMovil", "Adrian.b.g.7");
            ResultSet result = connection.getSt().executeQuery("exec usp_Read_Formulario");
            while (result.next()){
                String name = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String age = String.valueOf(result.getInt("Age"));
                registros.add(name + " " + lastName + ", " + age + " años");
            }
            adapter.notifyDataSetChanged();
            connection.getConnection().close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
