package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmNew extends Fragment {


    public fmNew() {
        // Required empty public constructor
    }
    private Button butConfirm;
    private EditText etName;
    private ArrayList<String> arrayFodder;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private ListView loadList;
    private ArrayAdapter adapter;
    private int check = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_new, container, false);
        Shared();
        loadData();
        initView(view);
        return view;
    }

    private void Shared() {
        sp = this.getActivity().getSharedPreferences(Const.save_fodder, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void initView(View view) {
        butConfirm = (Button) view.findViewById(R.id.butConfirm);
        etName = (EditText) view.findViewById(R.id.etName);
        loadList = (ListView) view.findViewById(R.id.loadList);
        arrayFodder = new ArrayList<>();
        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = etName.getText().toString();
                if(arrayFodder.size()!=0){
                    for(int i = 0;i < arrayFodder.size();i++) {
                        if (value.equals(arrayFodder.get(i))) {
                            arrayFodder.add(value);
                            saveData();
                        }
                    }
                }else {
                    arrayFodder.add(value);
                    saveData();
                    check=0;
                }
            }
        });
    }
    private void saveData() {
        Gson gson = new Gson();
        String jsonFodder = gson.toJson(arrayFodder);
        editor.putString(Const.fodder, jsonFodder);
        editor.commit();
        Log.d("26J","save Fodder : "+arrayFodder);

    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonFodder = sp.getString(Const.fodder, null);
        Log.d("26J","load Fodder : "+arrayFodder);
        Type type = new TypeToken<ArrayList>() {}.getType();

        arrayFodder = gson.fromJson(jsonFodder, type);

        if (arrayFodder == null) {
            arrayFodder = new ArrayList<>();
        }
    }

}
