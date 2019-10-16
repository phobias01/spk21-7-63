package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
    private ProgressBar PBload;
    private ArrayAdapter adapterIp,adapterNum,adapterlistIN;
    private ArrayList<String> arrayIp,arrayNum,arraylistIN,arrayAddHomeNum,arrayAddHomeIP,arrayAllIp,arrayG1,arrayG2,arrayG3,arrayG4,arrayIpG1,arrayIpG2,arrayIpG3,arrayIpG4;
    private ListView listSetspk;
    private Button butSetnum,butScan;
    private Spinner spinIP, spinNum;
    private Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private int check = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_setspk, container, false);
        Shared();
        PBload = (ProgressBar) view.findViewById(R.id.progressBar);
        loadData();
        initsetNumIP(view);
        return view;
    }

    private void Shared() {
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        //Log.d("26J","spkload : "+sp.getString(Const.sp_channel,null));
        sp = this.getActivity().getSharedPreferences(sp.getString(Const.sp_channel,null), Context.MODE_PRIVATE);
        editor = sp.edit();
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
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initsetNumIP(View view) {
        listSetspk = (ListView) view.findViewById(R.id.listSetspk);
        butSetnum = (Button) view.findViewById(R.id.butSetnum);
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
                        SimpleTcpClient.send("CONNECT", arrayIp.get(posIP), Const.port);
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
                arrayAddHomeIP.clear();
                arrayAddHomeNum.clear();
                if (arrayNum.isEmpty()) {
                    for (int i = 1; i <= 99; i++) {
                        arrayNum.add("No." + i);
                    }
                }
                saveData();
                check = 1;
                editor.putInt(Const.check, check);
                editor.commit();
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
                final String subIp;
                if (ipAdd.substring(9,10).equals(".")){
                    subIp = ipAdd.substring(0,9)+".1";
                    if (!ipAdd.equals(device.ip)&&(!subIp.equals(device.ip))) {
                        arrayIp.add(device.ip);
                        saveData();
                    }
                }else if (ipAdd.substring(10,11).equals(".")){
                    subIp = ipAdd.substring(0,10)+".1";
                    if (!ipAdd.equals(device.ip)&&(!subIp.equals(device.ip))) {
                        arrayIp.add(device.ip);
                        saveData();
                    }
                }else if(ipAdd.substring(11,12).equals(".")){
                    subIp = ipAdd.substring(0,11)+".1";
                    if (!ipAdd.equals(device.ip)&&(!subIp.equals(device.ip))) {
                        arrayIp.add(device.ip);
                        saveData();
                    }
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
            PBload.setVisibility(View.INVISIBLE);
            butScan.setEnabled(true);
            butSetnum.setEnabled(true);
            spinIP.setAdapter(adapterIp);
            adapterIp.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Complete....", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Device "+arrayIp.size(), Toast.LENGTH_SHORT).show();
            //Log.d("26J","onPostExecute ");
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PBload.setVisibility(View.VISIBLE);
            butScan.setEnabled(false);
            butSetnum.setEnabled(false);
            Toast.makeText(getActivity(), "Scanning....", Toast.LENGTH_SHORT).show();
            //Log.d("26J","onPreExecute");
        }

    }
}
