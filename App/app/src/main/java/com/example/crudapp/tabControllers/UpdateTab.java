package com.example.crudapp.tabControllers;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crudapp.Modelo.Form;
import com.example.crudapp.Modelo.SqlServerConnection;
import com.example.crudapp.R;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateTab extends Fragment {
    private View v;

    private Button btnLoad, btnUpdate;
    private ListView lstRegistros;
    private EditText txfUpdateName, txfUpdateLastName, txnsUpdateAge;

    private ArrayList<Form> registrosLogic = new ArrayList<>();
    private ArrayList<String> registrosString = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private String selectedID = "";
    private int selectedRow  = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.update_layout,container,false);

        txfUpdateName = v.findViewById(R.id.txfUpdateName);
        txfUpdateLastName = v.findViewById(R.id.txfUpdateLastName);
        txnsUpdateAge = v.findViewById(R.id.txnsUpdateAge);

        btnLoad = v.findViewById(R.id.btnLoadUpdate);
        btnUpdate = v.findViewById(R.id.btnUpdate);
        lstRegistros = v.findViewById(R.id.lstUpdateRegistros);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtItem, registrosString);
        lstRegistros.setAdapter(adapter);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromDatabase();
            }
        });
        lstRegistros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Form item = registrosLogic.get(position);
                selectedID = item.getFormId();
                selectedRow = position;
                for(int i=0; i<parent.getChildCount(); i++)
                {
                    if(i == position)
                    {
                        parent.getChildAt(i).setBackgroundColor(Color.parseColor("#03a9f4"));
                    }
                    else
                    {
                        parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                txfUpdateName.setText(item.getChSeqName());
                txfUpdateLastName.setText(item.getChSeqLastName());
                txnsUpdateAge.setText(item.getChSeqAge());
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToDatabase();
                selectedID = "";
                selectedRow = -1;
            }
        });

        return v;
    }

    private void loadFromDatabase(){
        try {
            registrosString.clear();
            registrosLogic.clear();
            SqlServerConnection connection = new SqlServerConnection("192.168.100.32",  "ProgramaMovil", "programaMovil", "Adrian.b.g.7");
            ResultSet result = connection.getSt().executeQuery("exec usp_Read_Formulario");
            while (result.next()){
                String id = String.valueOf(result.getInt("ID"));
                String name = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String age = String.valueOf(result.getInt("Age"));
                registrosLogic.add(new Form(id, name, lastName, age));
                registrosString.add(name + " " + lastName + ", " + age + " años");
            }
            selectedRow = -1;
            selectedID = "";
            adapter.notifyDataSetChanged();
            connection.getConnection().close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateToDatabase(){
        if(selectedRow != -1){
            String id = selectedID;
            String newName = txfUpdateName.getText().toString();
            String newLastName = txfUpdateLastName.getText().toString();
            String newAge = txnsUpdateAge.getText().toString();
            try {
                SqlServerConnection connection = new SqlServerConnection("192.168.100.32",  "ProgramaMovil", "programaMovil", "Adrian.b.g.7");
                int result = connection.getSt().executeUpdate("exec usp_Update_Formulario " + id + ", '" + newName + "', '" + newLastName + "',  " + newAge + ";");
                txfUpdateName.setText("");
                txfUpdateLastName.setText("");
                txnsUpdateAge.setText("");
                loadFromDatabase();
                connection.getConnection().close();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        } else {
            Toast.makeText(getActivity(), "No se ha seleccionado ningún elemento",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
