package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmLoad extends Fragment {


    public fmLoad() {
        // Required empty public constructor
    }

    private Context context;
    private ListView loadList;
    private ArrayList<String> arrayFodder;
    private ArrayAdapter adapter;
    private Button butOpen,butDel;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_load, container, false);
        Shared();
        loadData();
        initloadList(view);
        return view;
    }
    private void Shared() {
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    private void saveData() {
        Gson gson = new Gson();
        String jsonFodder = gson.toJson(arrayFodder);
        editor.putString(Const.fodder, jsonFodder);
        editor.commit();
        //Log.d("26J","save Fodder : "+arrayFodder);
    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonFodder = sp.getString(Const.fodder, null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        arrayFodder = gson.fromJson(jsonFodder, type);
        //Log.d("26J","load Fodder : "+arrayFodder);
        if (arrayFodder == null) {
            arrayFodder = new ArrayList<>();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initloadList(View view){
        loadList = (ListView) view.findViewById(R.id.loadList);
        butOpen = (Button) view.findViewById(R.id.butOpen);
        butDel = (Button) view.findViewById(R.id.butDel);
        adapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayFodder);
        loadList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        loadList.setAdapter(adapter);

        loadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SparseBooleanArray checkedItemPositions = loadList.getCheckedItemPositions();
                        int itemCount = adapter.getCount();
                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                try {
                                    adapter.remove(arrayFodder.get(i));
                                    Toast.makeText(getContext(), "DELETE " + arrayFodder.get(i), Toast.LENGTH_LONG).show();
                                }catch (Exception e){};
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(), "DELETE", Toast.LENGTH_LONG).show();
                        saveData();
                    }
                });
                butOpen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SparseBooleanArray checkedItemPositions = loadList.getCheckedItemPositions();
                        int itemCount = adapter.getCount();
                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                try {
                                    editor.putString(Const.sp_channel,  arrayFodder.get(i));
                                    editor.commit();
                                    Log.d("26J","load : "+arrayFodder.get(i));
                                }catch (Exception e){};
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(), "DELETE", Toast.LENGTH_LONG).show();
                        saveData();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.maincontent, new fmSetspk())
                                .commit();
                    }
                });
            }
        });
    }
}
