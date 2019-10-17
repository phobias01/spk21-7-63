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

import com.akexorcist.simpletcp.SimpleTcpClient;
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
    private ArrayList<String> arrayIp,arrayNum,arraylistIN,arrayAddHomeNum,arrayAddHomeIP,arrayAllIp,arrayG1,arrayG2,arrayG3,arrayG4,arrayIpG1,arrayIpG2,arrayIpG3,arrayIpG4;
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
        loadFodder();
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
    }
    private void loadFodder(){
        Gson gson = new Gson();
        String jsonFodder = sp.getString(Const.fodder, null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        arrayFodder = gson.fromJson(jsonFodder, type);
        if (arrayFodder == null) {
            arrayFodder = new ArrayList<>();
        }
       // Log.d("26J","load Fodder : "+arrayFodder);
    }
    private void loadData() {
        Gson gson = new Gson();
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
        /*Log.d("26J","load delay1 : "+delay1);
        Log.d("26J","load delay2 : "+delay2);
        Log.d("26J","load delay3 : "+delay3);
        Log.d("26J","load delay4 : "+delay4);*/
    }

    private void sentData() {
        sp = getActivity().getSharedPreferences(sp.getString(Const.sp_channel,null), Context.MODE_PRIVATE);
        loadData();
        int delay1 = sp.getInt(Const.delay_bar_1,0);
        int delay2 = sp.getInt(Const.delay_bar_2,0);
        int delay3 = sp.getInt(Const.delay_bar_3,0);
        int delay4 = sp.getInt(Const.delay_bar_4,0);
        int minValue1 = sp.getInt(Const.crossover_min1,20);
        int minValue2 = sp.getInt(Const.crossover_min2,20);
        int minValue3 = sp.getInt(Const.crossover_min3,20);
        int minValue4 = sp.getInt(Const.crossover_min4,20);
        int maxValue1 = sp.getInt(Const.crossover_max1,20000);
        int maxValue2 = sp.getInt(Const.crossover_max2,20000);
        int maxValue3 = sp.getInt(Const.crossover_max3,20000);
        int maxValue4 = sp.getInt(Const.crossover_max4,20000);
        String eq1 = sp.getString(Const.master_eq_slide_value_1_string,"EQ1/F/32/V/0.00");
        String eq2 = sp.getString(Const.master_eq_slide_value_2_string,"EQ2/F/125/V/0.00");
        String eq3 = sp.getString(Const.master_eq_slide_value_3_string,"EQ3/F/500/V/0.00");
        String eq4 = sp.getString(Const.master_eq_slide_value_4_string,"EQ4/F/2000/V/0.00");
        String eq5 = sp.getString(Const.master_eq_slide_value_5_string,"EQ5/F/8000/V/0.00");
        int MasterAll = sp.getInt(Const.master_eq_slide,0);
        int Master1 = sp.getInt(Const.group_value_1,0);
        int Master2 = sp.getInt(Const.group_value_2,0);
        int Master3 = sp.getInt(Const.group_value_3,0);
        int Master4 = sp.getInt(Const.group_value_4,0);
        String homeMute1;
        String homeMute2;
        String homeMute3;
        String homeMute4;
        String homeMuteAll;
        if(sp.getBoolean(Const.stuMute1,true)==false){
            homeMute1 = "MUTE";
        }else{
            homeMute1 = "UNMUTE";
        }
        if(sp.getBoolean(Const.stuMute2,true)==false){
            homeMute2 = "MUTE";
        }else{
            homeMute2 = "UNMUTE";
        }
        if(sp.getBoolean(Const.stuMute3,true)==false){
            homeMute3 = "MUTE";
        }else{
            homeMute3 = "UNMUTE";
        }
        if(sp.getBoolean(Const.stuMute4,true)==false){
            homeMute4 = "MUTE";
        }else{
            homeMute4 = "UNMUTE";
        }
        if(sp.getBoolean(Const.stuMuteAll,true)==false){
            homeMuteAll = "MUTE";
        }else{
            homeMuteAll = "UNMUTE";
        }
        try {
            for(int i = 0;i<arrayAllIp.size();i++) {
                SimpleTcpClient.send("CONNECT", arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(homeMuteAll, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq1, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq2, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq3, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq4, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(eq5, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send("MASTERVOL/"+MasterAll, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send("MASTERVOL/"+Master1, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send("MASTERVOL/"+Master2, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send("MASTERVOL/"+Master3, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send("MASTERVOL/"+Master4, arrayAllIp.get(i), Const.port);

            }
            for(int i = 0;i<arrayG1.size();i++) {
                SimpleTcpClient.send("DELAY/"+delay1, arrayIpG1.get(i), Const.port);
                SimpleTcpClient.send("LF/F/"+minValue1, arrayIpG1.get(i), Const.port);
                SimpleTcpClient.send("HF/F/"+maxValue1, arrayIpG1.get(i), Const.port);
                SimpleTcpClient.send(homeMute1, arrayIpG1.get(i), Const.port);
            }
            for(int i = 0;i<arrayG2.size();i++) {
                SimpleTcpClient.send("DELAY/"+delay2, arrayIpG2.get(i), Const.port);
                SimpleTcpClient.send("LF/F/"+minValue2, arrayIpG2.get(i), Const.port);
                SimpleTcpClient.send("HF/F/"+maxValue2, arrayIpG2.get(i), Const.port);
                SimpleTcpClient.send(homeMute2, arrayIpG2.get(i), Const.port);
            }
            for(int i = 0;i<arrayG3.size();i++) {
                SimpleTcpClient.send("DELAY/"+delay3, arrayIpG3.get(i), Const.port);
                SimpleTcpClient.send("LF/F/"+minValue3, arrayIpG3.get(i), Const.port);
                SimpleTcpClient.send("HF/F/"+maxValue3, arrayIpG3.get(i), Const.port);
                SimpleTcpClient.send(homeMute3, arrayIpG3.get(i), Const.port);
            }
            for(int i = 0;i<arrayG4.size();i++) {
                SimpleTcpClient.send("DELAY/"+delay4, arrayIpG4.get(i), Const.port);
                SimpleTcpClient.send("LF/F/"+minValue4, arrayIpG4.get(i), Const.port);
                SimpleTcpClient.send("HF/F/"+maxValue4, arrayIpG4.get(i), Const.port);
                SimpleTcpClient.send(homeMute4, arrayIpG4.get(i), Const.port);
            }
        }catch (Exception e){}
        sp = getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
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
                                    editor.remove(arrayFodder.get(i));
                                    Toast.makeText(getContext(), "DELETE " + arrayFodder.get(i), Toast.LENGTH_LONG).show();
                                }catch (Exception e){};
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
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
                                    sentData();
                                    //Log.d("26J","load : "+arrayFodder.get(i));
                                }catch (Exception e){};
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
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
