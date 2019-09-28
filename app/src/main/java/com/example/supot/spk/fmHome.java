package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmHome extends Fragment {


    public fmHome() {
        // Required empty public constructor
    }
    private boolean isOnMute1 = true,isOnMute2 = true,isOnMute3 = true,isOnMute4 = true,isOnMuteAll = true;
    private TextView tvMaster;
    private SeekBar masterBar;
    private Context context;
    private ListView listSpk,listG1,listG2,listG3,listG4;
    private ArrayList<String> arraySpk,arrayG1,arrayG2,arrayG3,arrayG4,arrayIpSpk,arrayIpG1,arrayIpG2,arrayIpG3,arrayIpG4,arrayAllIp;
    private ArrayAdapter adapterSpk,adapterG1,adapterG2,adapterG3,adapterG4;
    private Button butExport,butG1,butG2,butG3,butG4,butMute1,butMute2,butMute3,butMute4,butMuteAll;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String dataOutput = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_home, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        loadData();
        initmasterBar(view);
        initmanegeGroup(view);
        initbuttonMute(view);
        return view;
    }
    public void initbuttonMute(View view){
        final String dataOutput1 = "MUTE";
        final String dataOutput2 = "UNMUTE";
        butMute1 = (Button) view.findViewById(R.id.butMute1);
        butMute2 = (Button) view.findViewById(R.id.butMute2);
        butMute3 = (Button) view.findViewById(R.id.butMute3);
        butMute4 = (Button) view.findViewById(R.id.butMute4);
        butMuteAll = (Button) view.findViewById(R.id.butMuteAll);
        if(isOnMute1 == sp.getBoolean(Const.stuMute1,true)) {
            butMute1.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMute1 = true;
        }else {
            butMute1.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMute1 = false;
        }
        butMute1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMute1==true) {
                    try {
                        for (int i = 0; i < arrayIpG1.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayIpG1.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute1.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMute1 = false;
                    editor.putBoolean(Const.stuMute1,isOnMute1);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayIpG1.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayIpG1.get(i), Const.port);
                           // Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMute1.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMute1 = true;
                    editor.putBoolean(Const.stuMute1,isOnMute1);
                    editor.commit();
                }
            }
        });
        if(isOnMute2 == sp.getBoolean(Const.stuMute2,true)) {
            butMute2.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMute2 = true;
        }else {
            butMute2.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMute2 = false;
        }
        butMute2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMute2==true) {
                    try {
                        for (int i = 0; i < arrayIpG2.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayIpG2.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute2.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMute2 = false;
                    editor.putBoolean(Const.stuMute2,isOnMute2);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayIpG2.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayIpG2.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute2.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMute2 = true;
                    editor.putBoolean(Const.stuMute2,isOnMute2);
                    editor.commit();
                }
            }
        });
        if(isOnMute3 == sp.getBoolean(Const.stuMute3,true)) {
            butMute3.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMute3 = true;
        }else {
            butMute3.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMute3 = false;
        }
        butMute3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMute3==true) {
                    try {
                        for (int i = 0; i < arrayIpG3.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayIpG3.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute3.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMute3 = false;
                    editor.putBoolean(Const.stuMute3,isOnMute3);
                    editor.commit();
                }else{try {
                    for (int i = 0; i < arrayIpG3.size(); i++) {
                        SimpleTcpClient.send(dataOutput2, arrayIpG3.get(i), Const.port);
                        //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                    }
                }catch (Exception e) {}
                    butMute3.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMute3 = true;
                    editor.putBoolean(Const.stuMute3,isOnMute3);
                    editor.commit();
                }
            }
        });
        if(isOnMute4 == sp.getBoolean(Const.stuMute4,true)) {
            butMute4.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMute4 = true;
        }else {
            butMute4.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMute4 = false;
        }
        butMute4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMute4==true) {
                    try {
                        for (int i = 0; i < arrayIpG4.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayIpG4.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute4.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMute4 = false;
                    editor.putBoolean(Const.stuMute4,isOnMute4);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayIpG4.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayIpG4.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMute4.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMute4 = true;
                    editor.putBoolean(Const.stuMute4,isOnMute4);
                    editor.commit();
                }
            }
        });
        if(isOnMuteAll == sp.getBoolean(Const.stuMuteAll,true)) {
            butMuteAll.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteAll = true;
        }else {
            butMuteAll.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteAll = false;
        }
        butMuteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteAll==true) {
                    try {
                        for (int i = 0; i < arrayAllIp.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayAllIp.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteAll.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteAll = false;
                    editor.putBoolean(Const.stuMuteAll,isOnMuteAll);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayAllIp.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayAllIp.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayIpG1.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteAll.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteAll = true;
                    editor.putBoolean(Const.stuMuteAll,isOnMuteAll);
                    editor.commit();
                }
            }
        });
    }

    public void initmasterBar(View view){
        masterBar = (SeekBar) view.findViewById(R.id.masterBar);
        tvMaster = (TextView) view.findViewById(R.id.tvMaster);
        int saveProgress = sp.getInt(Const.master_eq_slide,40)-80;
        tvMaster.setText(String.valueOf("MASTER : "+saveProgress+" dB"));
        masterBar.setMax(80);
        masterBar.setProgress(40);
        masterBar.setProgress(sp.getInt(Const.master_eq_slide,40));
        masterBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvMaster.setText(String.format("MASTER : %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOL/%.0f",progressChanged);
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide,value);
                editor.commit();

            }
        });
    }

    private void saveData() {
        Gson gson = new Gson();
        String jsonSpk = gson.toJson(arraySpk);
        String json1 = gson.toJson(arrayG1);
        String json2 = gson.toJson(arrayG2);
        String json3 = gson.toJson(arrayG3);
        String json4 = gson.toJson(arrayG4);
        String jsonIpSpk = gson.toJson(arrayIpSpk);
        String jsonIpG1 = gson.toJson(arrayIpG1);
        String jsonIpG2 = gson.toJson(arrayIpG2);
        String jsonIpG3 = gson.toJson(arrayIpG3);
        String jsonIpG4 = gson.toJson(arrayIpG4);
        editor.putString(Const.list_group_spk, jsonSpk);
        editor.putString(Const.list_group_1, json1);
        editor.putString(Const.list_group_2, json2);
        editor.putString(Const.list_group_3, json3);
        editor.putString(Const.list_group_4, json4);
        editor.putString(Const.list_IpSpk, jsonIpSpk);
        editor.putString(Const.list_IpG1, jsonIpG1);
        editor.putString(Const.list_IpG2, jsonIpG2);
        editor.putString(Const.list_IpG3, jsonIpG3);
        editor.putString(Const.list_IpG4, jsonIpG4);
        editor.commit();
    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonSpk = sp.getString(Const.list_group_spk, null);
        String json1 = sp.getString(Const.list_group_1, null);
        String json2 = sp.getString(Const.list_group_2, null);
        String json3 = sp.getString(Const.list_group_3, null);
        String json4 = sp.getString(Const.list_group_4, null);
        String jsonIpSpk = sp.getString(Const.list_IpSpk, null);
        String jsonIpG1 = sp.getString(Const.list_IpG1, null);
        String jsonIpG2 = sp.getString(Const.list_IpG2, null);
        String jsonIpG3 = sp.getString(Const.list_IpG3, null);
        String jsonIpG4 = sp.getString(Const.list_IpG4, null);
        String jsonAllIp = sp.getString(Const.list_AllIp, null);

        Type type = new TypeToken<ArrayList>(){}.getType();

        arrayAllIp = gson.fromJson(jsonAllIp, type);
        arraySpk = gson.fromJson(jsonSpk, type);
        arrayG1 = gson.fromJson(json1, type);
        arrayG2 = gson.fromJson(json2, type);
        arrayG3 = gson.fromJson(json3, type);
        arrayG4 = gson.fromJson(json4, type);
        arrayIpSpk = gson.fromJson(jsonIpSpk, type);
        arrayIpG1 = gson.fromJson(jsonIpG1, type);
        arrayIpG2 = gson.fromJson(jsonIpG2, type);
        arrayIpG3 = gson.fromJson(jsonIpG3, type);
        arrayIpG4 = gson.fromJson(jsonIpG4, type);

        if (arrayAllIp == null) {
            arrayAllIp = new ArrayList<>();
        }
        if (arraySpk == null) {
            arraySpk = new ArrayList<>();
        }
        if (arrayG1 == null) {
            arrayG1 = new ArrayList<>();
        }
        if (arrayG2 == null) {
            arrayG2 = new ArrayList<>();
        }
        if (arrayG3 == null) {
            arrayG3 = new ArrayList<>();
        }
        if (arrayG4 == null) {
            arrayG4 = new ArrayList<>();
        }
        if (arrayIpSpk == null) {
            arrayIpSpk = new ArrayList<>();
        }
        if (arrayIpG1 == null) {
             arrayIpG1 = new ArrayList<>();
        }
        if (arrayIpG2 == null) {
            arrayIpG2 = new ArrayList<>();
        }
        if (arrayIpG3 == null) {
            arrayIpG3 = new ArrayList<>();
        }
        if (arrayIpG4 == null) {
            arrayIpG4 = new ArrayList<>();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public void initmanegeGroup (View view){
        listSpk = (ListView) view.findViewById(R.id.listSpk);
        listG1 = (ListView) view.findViewById(R.id.listG1);
        listG2 = (ListView) view.findViewById(R.id.listG2);
        listG3 = (ListView) view.findViewById(R.id.listG3);
        listG4 = (ListView) view.findViewById(R.id.listG4);
        butExport = (Button) view.findViewById(R.id.butExport);
        butG1 = (Button) view.findViewById(R.id.butG1);
        butG2 = (Button) view.findViewById(R.id.butG2);
        butG3 = (Button) view.findViewById(R.id.butG3);
        butG4 = (Button) view.findViewById(R.id.butG4);

        adapterSpk = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arraySpk);
        listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listSpk.setAdapter(adapterSpk);

        adapterG1= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG1);
        listG1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG1.setAdapter(adapterG1);

        adapterG2= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG2);
        listG2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG2.setAdapter(adapterG2);

        adapterG3= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG3);
        listG3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG3.setAdapter(adapterG3);

        adapterG4= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG4);
        listG4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG4.setAdapter(adapterG4);

        listSpk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butG1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();
                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG1.add(arraySpk.get(i));
                                listG1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG1.setAdapter(adapterG1);
                                arrayIpG1.add(arrayIpSpk.get(i));
                                arrayIpSpk.remove(arrayIpSpk.get(i));
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG1.notifyDataSetChanged();
                    }
                });
                butG2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG2.add(arraySpk.get(i));
                                listG2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG2.setAdapter(adapterG2);
                                arrayIpG2.add(arrayIpSpk.get(i));
                                arrayIpSpk.remove(arrayIpSpk.get(i));
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG2.notifyDataSetChanged();
                    }
                });
                butG3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG3.add(arraySpk.get(i));
                                listG3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG3.setAdapter(adapterG3);
                                arrayIpG3.add(arrayIpSpk.get(i));
                                arrayIpSpk.remove(arrayIpSpk.get(i));
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG3.notifyDataSetChanged();
                    }
                });
                butG4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG4.add(arraySpk.get(i));
                                listG4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG4.setAdapter(adapterG4);
                                arrayIpG4.add(arrayIpSpk.get(i));
                                arrayIpSpk.remove(arrayIpSpk.get(i));
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG4.notifyDataSetChanged();
                    }
                });
            }
        });
        listG1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG1.getCheckedItemPositions();
                        int itemCount = adapterG1.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG1.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                arrayIpSpk.add(arrayIpG1.get(i));
                                arrayIpG1.remove(arrayIpG1.get(i));
                                arrayG1.remove(arrayG1.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG1.notifyDataSetChanged();
                    }
                });
            }
        });
        listG2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG2.getCheckedItemPositions();
                        int itemCount = adapterG2.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG2.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                arrayIpSpk.add(arrayIpG2.get(i));
                                arrayIpG2.remove(arrayIpG2.get(i));
                                adapterG2.remove(arrayG2.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG2.notifyDataSetChanged();
                    }
                });
            }
        });
        listG3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG3.getCheckedItemPositions();
                        int itemCount = adapterG3.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG3.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                arrayIpSpk.add(arrayIpG3.get(i));
                                arrayIpG3.remove(arrayIpG3.get(i));
                                adapterG3.remove(arrayG3.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG3.notifyDataSetChanged();
                    }
                });
            }
        });
        listG4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG4.getCheckedItemPositions();
                        int itemCount = adapterG4.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG4.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                arrayIpSpk.add(arrayIpG4.get(i));
                                arrayIpG4.remove(arrayIpG4.get(i));
                                adapterG4.remove(arrayG4.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG4.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
