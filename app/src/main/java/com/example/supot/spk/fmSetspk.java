package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

    private ArrayAdapter adapterIp, adapterNum, adapterlistIN;
    private ArrayList<String> arrayIp, arrayNum, arraylistIN, arrayAddHomeNum,arrayAddHomeIP;
    private ListView listSetspk;
    private Button butSetnum, butClear, butScan;
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
        editor.putString(Const.spk_setnumip, jsonlistIN);
        editor.putString(Const.spk_ip, jsonIp);
        editor.putString(Const.spk_number, jsonNum);
        editor.putString(Const.list_group_spk, jsonAddHomeNum);
        editor.putString(Const.list_IpSpk, jsonAddHomeIP);
        editor.commit();
    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonlistIN = sp.getString(Const.spk_setnumip, null);
        String jsonIp = sp.getString(Const.spk_ip, null);
        String jsonNum = sp.getString(Const.spk_number, null);

        Type type = new TypeToken<ArrayList>() {
        }.getType();

        arraylistIN = gson.fromJson(jsonlistIN, type);
        arrayIp = gson.fromJson(jsonIp, type);
        arrayNum = gson.fromJson(jsonNum, type);

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
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initsetNumIP(View view) {
        listSetspk = (ListView) view.findViewById(R.id.listSetspk);
        butSetnum = (Button) view.findViewById(R.id.butSetnum);
        butClear = (Button) view.findViewById(R.id.butClear);
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
        } catch (Exception e) {
        }
        butClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraylistIN.clear();
                arrayIp.clear();
                arrayNum.clear();
                if (arrayNum.isEmpty()) {
                    for (int i = 1; i <= 99; i++) {
                        arrayNum.add("No." + i);
                    }
                }
                listSetspk.setAdapter(adapterlistIN);
                spinNum.setAdapter(adapterNum);
                saveData();
                adapterlistIN.notifyDataSetChanged();
                adapterIp.notifyDataSetChanged();
                adapterNum.notifyDataSetChanged();
            }
        });
        butScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayIp.clear();
                findSubnetDevices();
                spinIP.setAdapter(adapterIp);
                adapterIp.notifyDataSetChanged();
            }
        });
    }

    private void findSubnetDevices() {
        InetAddress ipAddress = IPTools.getLocalIPv4Address();
        final String ipAdd = ipAddress.getHostAddress();
        //final long startTimeMillis = System.currentTimeMillis();
        SubnetDevices.fromLocalAddress().findDevices(new SubnetDevices.OnSubnetDeviceFound() {
            @Override
            public void onDeviceFound(Device device) {
                if (!ipAdd.equals(device.ip)/*&&ipAdd.equals("")*/) {
                    arrayIp.add(device.ip);
                    saveData();
                }
            }

            @Override
            public void onFinished(ArrayList<Device> devicesFound) {
                //float timeTaken = (System.currentTimeMillis() - startTimeMillis) / 1000.0f;
                Toast.makeText(getActivity(), "Devices Found: " + (devicesFound.size() - 1), Toast.LENGTH_SHORT).show();
                //appendResultsText("Finished "+timeTaken+" s");
            }
        });
    }
}
