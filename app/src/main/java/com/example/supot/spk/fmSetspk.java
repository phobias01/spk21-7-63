package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stealthcopter.networktools.IPTools;
import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmSetspk extends Fragment {

    public fmSetspk() {
        // Required empty public constructor
    }
    private ProgressBar pb;
    private ArrayAdapter adapterIp, adapterNum, adapterlistIN;
    private ArrayList<String> arrayIp, arrayNum, arraylistIN, arrayAddHomeNum,arrayAddHomeIP,arrayAllIp,arrayG1,arrayG2,arrayG3,arrayG4,arrayIpG1,arrayIpG2,arrayIpG3,arrayIpG4;
    private ListView listSetspk;
    private Button butSetnum, butConnect, butScan;
    private Spinner spinIP, spinNum;
    private Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_setspk, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        loadData();
        initsetNumIP(view);
        return view;
    }

    private void saveData() {
        Gson gson = new Gson();
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
       // String json12 = gson.toJson(arrayList);
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
        //editor.putString(Const.list_group_1, json12);
        editor.commit();
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

        Type type = new TypeToken<ArrayList>() {}.getType();

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
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initsetNumIP(View view) {
        listSetspk = (ListView) view.findViewById(R.id.listSetspk);
        butSetnum = (Button) view.findViewById(R.id.butSetnum);
        butConnect = (Button) view.findViewById(R.id.butConnect);
        butScan = (Button) view.findViewById(R.id.butScan);
        spinIP = (Spinner) view.findViewById(R.id.spinIP);
        spinNum = (Spinner) view.findViewById(R.id.spinNum);

        adapterIp = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayIp);
        spinIP.setAdapter(adapterIp);

        adapterNum = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayNum);
        spinNum.setAdapter(adapterNum);

        adapterlistIN = new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, arraylistIN);
        listSetspk.setAdapter(adapterlistIN);
        try {
            butSetnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!arrayIp.isEmpty()) {
                        int posIP = spinIP.getSelectedItemPosition();
                        int posNum = spinNum.getSelectedItemPosition();
                        arraylistIN.add("IP : " + arrayIp.get(posIP) + " ==>> Number is set = SPK " + arrayNum.get(posNum));
                        arrayAddHomeNum.add("SPK " + arrayNum.get(posNum));
                        arrayAddHomeIP.add(arrayIp.get(posIP));
                        arrayAllIp.add(arrayIp.get(posIP));
                        arrayIp.remove(posIP);
                        arrayNum.remove(posNum);
                        listSetspk.setAdapter(adapterlistIN);
                        spinIP.setAdapter(adapterIp);
                        spinNum.setAdapter(adapterNum);
                        saveData();
                        adapterlistIN.notifyDataSetChanged();
                        adapterIp.notifyDataSetChanged();
                        adapterNum.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getActivity(),"No ipaddress", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {}
        butConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect();
            }
        });

        butScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (arrayNum.isEmpty()) {
                    for (int i = 1; i <= 99; i++) {
                        arrayNum.add("No." + i);
                    }
                }
                listSetspk.setAdapter(adapterlistIN);
                spinIP.setAdapter(adapterIp);
                spinNum.setAdapter(adapterNum);
                saveData();
                new AsyncScan().execute();
                adapterlistIN.notifyDataSetChanged();
                adapterIp.notifyDataSetChanged();
                adapterNum.notifyDataSetChanged();
            }
        });
    }

    private void findSubnetDevices() {
        InetAddress ipAddress = IPTools.getLocalIPv4Address();
        final String ipAdd = ipAddress.getHostAddress();
        final long startTimeMillis = System.currentTimeMillis();
        SubnetDevices.fromLocalAddress().findDevices(new SubnetDevices.OnSubnetDeviceFound() {
            @Override
            public void onDeviceFound(Device device) {
                if (!ipAdd.equals(device.ip)&&(!"192.168.1.1".equals(device.ip))) {
                    arrayIp.add(device.hostname+"/"+device.ip);
                    saveData();
                }
            }

            @Override
            public void onFinished(ArrayList<Device> devicesFound) {
                String timeTaken = String.valueOf(System.currentTimeMillis() - startTimeMillis);
                int device = (devicesFound.size() - 1);
                int time = Integer.parseInt(timeTaken);
                editor.putInt(Const.time,time);
                editor.putInt(Const.device,device);
                editor.commit();
            }
        });
    }
    public class AsyncScan extends AsyncTask<Integer,Void,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                findSubnetDevices();
                int time = sp.getInt(Const.time,20000);
                Thread.sleep(time);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.INVISIBLE);
            spinIP.setAdapter(adapterIp);
            adapterIp.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Complete....", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Device "+arrayIp.size(), Toast.LENGTH_SHORT).show();
            //Log.d("26J","onPostExecute ");
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Scanning....", Toast.LENGTH_SHORT).show();
            //Log.d("26J","onPreExecute");
        }

    }
    private void Connect() {
        String dataDelay1 = String.valueOf("D/"+sp.getInt(Const.delay_bar_1,0));
        String dataDelay2 = String.valueOf("D/"+sp.getInt(Const.delay_bar_2,0));
        String dataDelay3 = String.valueOf("D/"+sp.getInt(Const.delay_bar_3,0));
        String dataDelay4 = String.valueOf("D/"+sp.getInt(Const.delay_bar_4,0));
        try {
            for (int i = 0; i < arrayG1.size(); i++) {
                SimpleTcpClient.send(dataDelay1, arrayG1.get(i), Const.port);
            }
            for (int i = 0; i < arrayG2.size(); i++) {
                SimpleTcpClient.send(dataDelay2, arrayG2.get(i), Const.port);
            }
            for (int i = 0; i < arrayG3.size(); i++) {
                SimpleTcpClient.send(dataDelay3, arrayG3.get(i), Const.port);
            }
            for (int i = 0; i < arrayG4.size(); i++) {
                SimpleTcpClient.send(dataDelay4, arrayG4.get(i), Const.port);
            }
        }catch (Exception e) {}

        String dataMtg1 = String.valueOf("MASTERVOL/"+sp.getInt(Const.group_value_1,0));
        String dataMtg2 = String.valueOf("MASTERVOL/"+sp.getInt(Const.group_value_2,0));
        String dataMtg3 = String.valueOf("MASTERVOL/"+sp.getInt(Const.group_value_3,0));
        String dataMtg4 = String.valueOf("MASTERVOL/"+sp.getInt(Const.group_value_4,0));
        try {
            for (int i = 0; i < arrayG1.size(); i++) {
                SimpleTcpClient.send(dataMtg1, arrayG1.get(i), Const.port);
            }
            for (int i = 0; i < arrayG2.size(); i++) {
                SimpleTcpClient.send(dataMtg2, arrayG2.get(i), Const.port);
            }
            for (int i = 0; i < arrayG3.size(); i++) {
                SimpleTcpClient.send(dataMtg3, arrayG3.get(i), Const.port);
            }
            for (int i = 0; i < arrayG4.size(); i++) {
                SimpleTcpClient.send(dataMtg4, arrayG4.get(i), Const.port);
            }
        }catch (Exception e) {}

        String dataCrossMax1 = String.valueOf("HF/F//"+sp.getInt(Const.crossover_max1,0));
        String dataCrossMax2 = String.valueOf("HF/F/"+sp.getInt(Const.crossover_max2,0));
        String dataCrossMax3 = String.valueOf("HF/F/"+sp.getInt(Const.crossover_max3,0));
        String dataCrossMax4 = String.valueOf("HF/F/"+sp.getInt(Const.crossover_max4,0));
        String dataCrossMin1 = String.valueOf("LF/F/"+sp.getInt(Const.crossover_min1,0));
        String dataCrossMin2 = String.valueOf("LF/F/"+sp.getInt(Const.crossover_min2,0));
        String dataCrossMin3 = String.valueOf("LF/F/"+sp.getInt(Const.crossover_min3,0));
        String dataCrossMin4 = String.valueOf("LF/F/"+sp.getInt(Const.crossover_min4,0));
        try {
            for (int i = 0; i < arrayG1.size(); i++) {
                SimpleTcpClient.send(dataCrossMin1, arrayG1.get(i), Const.port);
                SimpleTcpClient.send(dataCrossMax1, arrayG1.get(i), Const.port);
            }
            for (int i = 0; i < arrayG2.size(); i++) {
                SimpleTcpClient.send(dataCrossMin2, arrayG2.get(i), Const.port);
                SimpleTcpClient.send(dataCrossMax2, arrayG2.get(i), Const.port);
            }
            for (int i = 0; i < arrayG3.size(); i++) {
                SimpleTcpClient.send(dataCrossMin3, arrayG3.get(i), Const.port);
                SimpleTcpClient.send(dataCrossMax3, arrayG3.get(i), Const.port);
            }
            for (int i = 0; i < arrayG4.size(); i++) {
                SimpleTcpClient.send(dataCrossMin4, arrayG4.get(i), Const.port);
                SimpleTcpClient.send(dataCrossMax4, arrayG4.get(i), Const.port);
            }
        }catch (Exception e) {}
        String dataEqMaster = sp.getString(Const.master_eq_slide_string,"0");
        String dataEq1 = sp.getString(Const.master_eq_slide_value_1_string,"0");
        String dataEq2 = sp.getString(Const.master_eq_slide_value_2_string,"0");
        String dataEq3 = sp.getString(Const.master_eq_slide_value_3_string,"0");
        String dataEq4 = sp.getString(Const.master_eq_slide_value_4_string,"0");
        String dataEq5 = sp.getString(Const.master_eq_slide_value_5_string,"0");
        String dataEq6 = sp.getString(Const.master_eq_slide_value_6_string,"0");
        String dataEq7 = sp.getString(Const.master_eq_slide_value_7_string,"0");
        String dataEq8 = sp.getString(Const.master_eq_slide_value_8_string,"0");
        String dataEq9 = sp.getString(Const.master_eq_slide_value_9_string,"0");
        String dataEq10 = sp.getString(Const.master_eq_slide_value_10_string,"0");
        String dataEq11 = sp.getString(Const.master_eq_slide_value_11_string,"0");
        String dataEq12 = sp.getString(Const.master_eq_slide_value_12_string,"0");
        String dataEq13 = sp.getString(Const.master_eq_slide_value_13_string,"0");
        String dataEq14 = sp.getString(Const.master_eq_slide_value_14_string,"0");
        String dataEq15 = sp.getString(Const.master_eq_slide_value_15_string,"0");
        String dataEq16 = sp.getString(Const.master_eq_slide_value_16_string,"0");

        try {
            for(int i = 0; i < arrayAllIp.size(); i++) {
                SimpleTcpClient.send(dataEq1, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq2, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq3, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq4, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq5, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq6, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq7, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq8, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq9, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq10, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq11, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq12, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq13, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq14, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq15, arrayAllIp.get(i), Const.port);
                SimpleTcpClient.send(dataEq16, arrayAllIp.get(i), Const.port);
            }
        }catch (Exception e) {}
    }
}
