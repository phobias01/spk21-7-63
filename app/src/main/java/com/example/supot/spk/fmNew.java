package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.akexorcist.simpletcp.SimpleTcpClient;
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
    private ArrayList<String> arrayIp,arrayNum,arraylistIN,arrayAddHomeNum,arrayAddHomeIP,arrayAllIp,arrayG1,arrayG2,arrayG3,arrayG4,arrayIpG1,arrayIpG2,arrayIpG3,arrayIpG4;
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
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void sent() {
        //Log.d("26J", "sent");
        final String eq1 = "EQ1/F/32" + "/V/0.00";
        final String eq2 = "EQ2/F/125" + "/V/0.00";
        final String eq3 = "EQ3/F/500" + "/V/0.00";
        final String eq4 = "EQ4/F/2000" + "/V/0.00";
        final String eq5 = "EQ5/F/8000" + "/V/0.00";
        final String master = "MASTERVOL/-80";
        final String delay = "DELAY/0.01";
        final String crossLF = "LF/F/20";
        final String crossHF = "HF/F/20000";
        final String unmute = "UNMUTE";
        final String unconnect = "UNCONNECT";
       // Log.d("26J", "EQ : " + arrayAllIp.get(1) + "/" + eq1);
        try {
            for (int i = 0; i < arrayAllIp.size(); i++) {
                SimpleTcpClient.send(eq1, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq2, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq3, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq4, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq5, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(master, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(delay, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(crossLF, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(crossHF, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(unmute, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(unconnect, arrayAllIp.get(i), Const.port);
                //  Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + eq1);
            }
        }catch (Exception e) {}
    }


    private void initView(View view) {
        butConfirm = (Button) view.findViewById(R.id.butConfirm);
        etName = (EditText) view.findViewById(R.id.etName);
        loadList = (ListView) view.findViewById(R.id.loadList);
        //Log.d("26J","arrayFodder.size : "+arrayFodder.size());
        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = etName.getText().toString();
                if(!value.isEmpty()) {
                    if(arrayFodder.size()!=0){
                        for(int i = 0;i < arrayFodder.size();i++) {
                            if (value.equalsIgnoreCase(arrayFodder.get(i))) {
                                check = 0;
                                Toast.makeText(getContext(), "This name is already used.", Toast.LENGTH_LONG).show();
                                break;
                            }else {check++;}
                        }
                        if(check!=0){
                            arrayFodder.add(value);
                            check = 0;
                            editor.putString(Const.sp_channel,value);
                            editor.commit();
                            saveData();
                            sent();
                            resetData();
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.maincontent, new fmSetspk())
                                    .commit();
                         }
                    }else {
                        arrayFodder.add(value);
                        check = 0;
                        editor.putString(Const.sp_channel,value);
                        editor.commit();
                        saveData();
                        sent();
                        resetData();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.maincontent, new fmSetspk())
                                .commit();
                    }
                }else{
                    Toast.makeText(getContext(), "Please enter a message." , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveData() {
        Gson gson = new Gson();
        String jsonFodder = gson.toJson(arrayFodder);
        String jsonlistIN = gson.toJson(arraylistIN);
        String jsonIp = gson.toJson(arrayIp);
        String jsonNum = gson.toJson(arrayNum);
        String jsonAddHomeNum = gson.toJson(arrayAddHomeNum);
        String jsonAddHomeIP = gson.toJson(arrayAddHomeIP);
        String jsonAddAllIp = gson.toJson(arrayAllIp);
        String json1 = gson.toJson(arrayG1);
        String json2 = gson.toJson(arrayG2);
        String json3 = gson.toJson(arrayG3);
        String json4 = gson.toJson(arrayG4);
        String jsonIpG1 = gson.toJson(arrayIpG1);
        String jsonIpG2 = gson.toJson(arrayIpG2);
        String jsonIpG3 = gson.toJson(arrayIpG3);
        String jsonIpG4 = gson.toJson(arrayIpG4);

        editor.putString(Const.spk_setnumip, jsonlistIN);
        editor.putString(Const.spk_ip, jsonIp);
        editor.putString(Const.spk_number, jsonNum);
        editor.putString(Const.list_group_spk, jsonAddHomeNum);
        editor.putString(Const.list_IpSpk, jsonAddHomeIP);
        editor.putString(Const.list_AllIp, jsonAddAllIp);
        editor.putString(Const.list_group_1, json1);
        editor.putString(Const.list_group_2, json2);
        editor.putString(Const.list_group_3, json3);
        editor.putString(Const.list_group_4, json4);
        editor.putString(Const.list_IpG1, jsonIpG1);
        editor.putString(Const.list_IpG2, jsonIpG2);
        editor.putString(Const.list_IpG3, jsonIpG3);
        editor.putString(Const.list_IpG4, jsonIpG4);
        editor.putString(Const.fodder, jsonFodder);
        editor.commit();
        //Log.d("26J","save Fodder : "+arrayFodder);

    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonFodder = sp.getString(Const.fodder, null);
        String jsonlistIN = sp.getString(Const.spk_setnumip, null);
        String jsonIp = sp.getString(Const.spk_ip, null);
        String jsonNum = sp.getString(Const.spk_number, null);
        String jsonIpG1 = sp.getString(Const.list_IpG1, null);
        String jsonIpG2 = sp.getString(Const.list_IpG2, null);
        String jsonIpG3 = sp.getString(Const.list_IpG3, null);
        String jsonIpG4 = sp.getString(Const.list_IpG4, null);
        String json1 = sp.getString(Const.list_group_1, null);
        String json2 = sp.getString(Const.list_group_2, null);
        String json3 = sp.getString(Const.list_group_3, null);
        String json4 = sp.getString(Const.list_group_4, null);
        String jsonAllIp = sp.getString(Const.list_AllIp, null);
        String jsonAddHomeNum = sp.getString(Const.list_group_spk, null);
        Type type = new TypeToken<ArrayList>() {}.getType();

        arrayFodder = gson.fromJson(jsonFodder, type);
        arrayAddHomeNum = gson.fromJson(jsonAddHomeNum, type);
        arraylistIN = gson.fromJson(jsonlistIN, type);
        arrayIp = gson.fromJson(jsonIp, type);
        arrayNum = gson.fromJson(jsonNum, type);
        arrayG1 = gson.fromJson(json1, type);
        arrayG2 = gson.fromJson(json2, type);
        arrayG3 = gson.fromJson(json3, type);
        arrayG4 = gson.fromJson(json4, type);
        arrayAllIp = gson.fromJson(jsonAllIp, type);
        arrayIpG1 = gson.fromJson(jsonIpG1, type);
        arrayIpG2 = gson.fromJson(jsonIpG2, type);
        arrayIpG3 = gson.fromJson(jsonIpG3, type);
        arrayIpG4 = gson.fromJson(jsonIpG4, type);

        if (arraylistIN == null) {
            arraylistIN = new ArrayList<>();
        }
        if (arrayIp == null) {
            arrayIp = new ArrayList<>();
        }
        if (arrayNum == null) {
            arrayNum = new ArrayList<>();
        }
        if (arrayAddHomeNum == null) {
            arrayAddHomeNum = new ArrayList<>();
        }
        if (arrayAddHomeIP == null) {
            arrayAddHomeIP = new ArrayList<>();
        }
        if (arrayAllIp == null) {
            arrayAllIp = new ArrayList<>();
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
        if (arrayAllIp == null) {
            arrayAllIp = new ArrayList<>();
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
        if (arrayFodder == null) {
            arrayFodder = new ArrayList<>();
        }
    }
    private void resetData(){
        //editor.putString(Const.sp_channel,)
        sp = this.getActivity().getSharedPreferences(arrayFodder.get(arrayFodder.size()-1), Context.MODE_PRIVATE);
        editor = sp.edit();
        //Log.d("26J","new Fodder : "+arrayFodder.get(arrayFodder.size()-1));
        //////////////////EQ/////////////////////
        editor.putBoolean(Const.stuEQ,true);
        editor.putInt(Const.position_EQ1,0);
        editor.putInt(Const.position_EQ2,0);
        editor.putInt(Const.position_EQ3,0);
        editor.putInt(Const.position_EQ4,0);
        editor.putInt(Const.position_EQ5,0);
        editor.putInt(Const.master_eq_slide_value_1,150);
        editor.putInt(Const.master_eq_slide_value_2,150);
        editor.putInt(Const.master_eq_slide_value_3,150);
        editor.putInt(Const.master_eq_slide_value_4,150);
        editor.putInt(Const.master_eq_slide_value_5,150);
        editor.putInt(Const.master_eq_slide,0);
        editor.putInt(Const.group_value_1,0);
        editor.putInt(Const.group_value_2,0);
        editor.putInt(Const.group_value_3,0);
        editor.putInt(Const.group_value_4,0);
        ////////////////CROSS OVER///////////////
        editor.putInt(Const.crossover_min1,20);
        editor.putInt(Const.crossover_max1,20000);
        editor.putInt(Const.crossover_min2,20);
        editor.putInt(Const.crossover_max2,20000);
        editor.putInt(Const.crossover_min3,20);
        editor.putInt(Const.crossover_max3,20000);
        editor.putInt(Const.crossover_min4,20);
        editor.putInt(Const.crossover_max4,20000);
        editor.putBoolean(Const.switch_crossover_1, false);
        editor.putBoolean(Const.switch_crossover_2, false);
        editor.putBoolean(Const.switch_crossover_3, false);
        editor.putBoolean(Const.switch_crossover_4, false);
        //////////////////DELAY//////////////////
        editor.putBoolean(Const.switch_delay_1, false);
        editor.putBoolean(Const.switch_delay_2, false);
        editor.putBoolean(Const.switch_delay_3, false);
        editor.putBoolean(Const.switch_delay_4, false);
        editor.putInt(Const.delay_bar_1,0);
        editor.putInt(Const.delay_bar_2,0);
        editor.putInt(Const.delay_bar_3,0);
        editor.putInt(Const.delay_bar_4,0);
        //////////////////HOME///////////////////
        editor.putBoolean(Const.stuMute1,true);
        editor.putBoolean(Const.stuMute2,true);
        editor.putBoolean(Const.stuMute3,true);
        editor.putBoolean(Const.stuMute4,true);
        editor.putBoolean(Const.stuMuteAll,true);
        /////////////////SETSPK//////////////////
        arrayG1.clear();
        arrayG2.clear();
        arrayG3.clear();
        arrayG4.clear();
        arrayIpG1.clear();
        arrayIpG2.clear();
        arrayIpG3.clear();
        arrayIpG4.clear();
        arraylistIN.clear();
        arrayIp.clear();
        arrayNum.clear();
        arrayAllIp.clear();
        arrayAddHomeIP.clear();
        arrayAddHomeNum.clear();
        if (arrayNum.isEmpty()) {
            for (int i = 1; i <= 99; i++) {
                arrayNum.add("No." + i);
            }
        }
        editor.commit();
        saveData();
    }

}
