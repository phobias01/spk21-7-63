package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmDelay extends Fragment {


    public fmDelay() {
        // Required empty public constructor
    }
    private ArrayList<String> arrayG1,arrayG2,arrayG3,arrayG4;
    private SeekBar delayBar1,delayBar2,delayBar3,delayBar4;
    private TextView msView1,msView2,msView3,msView4,mView1,mView2,mView3,mView4,tv1;
    private Switch swG1,swG2,swG3,swG4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String dataOutput = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_delay, container, false);
        Shared();
        loadData();
        initDelayBar(view);
        initswLockdelayBar(view);
        return view;
    }
    private void Shared() {
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        String folder = sp.getString(Const.sp_channel,null);
        sp = this.getActivity().getSharedPreferences(folder, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void loadData() {
        Gson gson = new Gson();
        String json1 = sp.getString(Const.list_IpG1, null);
        String json2 = sp.getString(Const.list_IpG2, null);
        String json3 = sp.getString(Const.list_IpG3, null);
        String json4= sp.getString(Const.list_IpG4, null);

        Type type = new TypeToken<ArrayList>(){}.getType();
        arrayG1 = gson.fromJson(json1, type);
        arrayG2 = gson.fromJson(json2, type);
        arrayG3 = gson.fromJson(json3, type);
        arrayG4 = gson.fromJson(json4, type);

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
        /*Log.d("26J", "arrayG1 : " + arrayG1);
        Log.d("26J", "arrayG2 : " + arrayG2);
        Log.d("26J", "arrayG3 : " + arrayG3);
        Log.d("26J", "arrayG4 : " + arrayG4);*/
    }
    private void initDelayBar(View view){
        delayBar1 = (SeekBar) view.findViewById(R.id.delayBar1);
        delayBar2 = (SeekBar) view.findViewById(R.id.delayBar2);
        delayBar3 = (SeekBar) view.findViewById(R.id.delayBar3);
        delayBar4 = (SeekBar) view.findViewById(R.id.delayBar4);

        msView1 = (TextView) view.findViewById(R.id.msView1);
        msView2 = (TextView) view.findViewById(R.id.msView2);
        msView3 = (TextView) view.findViewById(R.id.msView3);
        msView4 = (TextView) view.findViewById(R.id.msView4);

        mView1 = (TextView) view.findViewById(R.id.mView1);
        mView2 = (TextView) view.findViewById(R.id.mView2);
        mView3 = (TextView) view.findViewById(R.id.mView3);
        mView4 = (TextView) view.findViewById(R.id.mView4);
        initsetDelayBar();
        delayBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = Float.valueOf(progress)*0.1f;
                value = progress;
                msView1.setText(String.format("%.2f",progressChanged) + " ms");
                mView1.setText(String.format("%.2f", progressChanged * 0.343) + " m");
                dataOutput = String.format("DELAY/%.2f",progressChanged);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < arrayG1.size(); i++) {
                                SimpleTcpClient.send(dataOutput, arrayG1.get(i), Const.port);
                               // Log.d("26J","delayload : "+sp.getString(Const.sp_channel,null));
                                //Log.d("26J", "Delay Bar 1: " + arrayG1.get(i) + "/" + dataOutput);
                            }
                        }catch (Exception e){}
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_1,value);
                editor.putFloat(Const.delay_bar_1_float,progressChanged);
                editor.commit();
            }
        });

        delayBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = Float.valueOf(progress)*0.1f;
                value = progress;
                msView2.setText(String.format("%.2f",progressChanged) + " ms");
                mView2.setText(String.format("%.2f", progressChanged * 0.343) + " m");
                dataOutput = String.format("DELAY/%.2f",progressChanged);
                try {
                    for(int i = 0;i<arrayG2.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG2.get(i), Const.port);
                        //Log.d("26J", "Delay Bar 2: " + arrayG2.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_2,value);
                editor.putFloat(Const.delay_bar_2_float,progressChanged);
                editor.commit();
            }
        });

        delayBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = Float.valueOf(progress)*0.1f;
                value = progress;
                msView3.setText(String.format("%.2f",progressChanged) + " ms");
                mView3.setText(String.format("%.2f", progressChanged * 0.343) + " m");
                dataOutput = String.format("DELAY/%.2f",progressChanged);
                try {
                    for(int i = 0;i<arrayG3.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG3.get(i), Const.port);
                        //Log.d("26J", "Delay Bar 3: " + arrayG3.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_3,value);
                editor.putFloat(Const.delay_bar_3_float,progressChanged);
                editor.commit();
            }
        });

        delayBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = Float.valueOf(progress)*0.1f;
                value = progress;
                msView4.setText(String.format("%.2f",progressChanged) + " ms");
                mView4.setText(String.format("%.2f", progressChanged * 0.343) + " m");
                dataOutput = String.format("DELAY/%.2f",progressChanged);
                try {
                    for(int i = 0;i<arrayG4.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG4.get(i), Const.port);
                        //Log.d("26J", "Delay Bar 4: " + arrayG4.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_4,value);
                editor.putFloat(Const.delay_bar_4_float,progressChanged);
                editor.commit();
            }
        });
    }
    private void initswLockdelayBar(View view){
        swG1 = (Switch) view.findViewById(R.id.swG1);
        swG2 = (Switch) view.findViewById(R.id.swG2);
        swG3 = (Switch) view.findViewById(R.id.swG3);
        swG4 = (Switch) view.findViewById(R.id.swG4);
        initsetSwitchDelay();
        swG1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar1.setEnabled(false);
                }else{
                    delayBar1.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_1, isChecked);
                editor.commit();
            }
        });

        swG2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar2.setEnabled(false);
                }else{
                    delayBar2.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_2, isChecked);
                editor.commit();
            }
        });

        swG3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar3.setEnabled(false);
                }else{
                    delayBar3.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_3, isChecked);
                editor.commit();
            }
        });

        swG4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar4.setEnabled(false);
                }else{
                    delayBar4.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_4, isChecked);
                editor.commit();
            }
        });
    }
    private void initsetDelayBar(){
        delayBar1.setMax(100);
        delayBar2.setMax(100);
        delayBar3.setMax(100);
        delayBar4.setMax(100);

        delayBar1.setProgress(sp.getInt(Const.delay_bar_1,0));
        delayBar2.setProgress(sp.getInt(Const.delay_bar_2,0));
        delayBar3.setProgress(sp.getInt(Const.delay_bar_3,0));
        delayBar4.setProgress(sp.getInt(Const.delay_bar_4,0));

        float saveProgress1 = sp.getFloat(Const.delay_bar_1_float,0);
        float saveProgress2 = sp.getFloat(Const.delay_bar_2_float,0);
        float saveProgress3 = sp.getFloat(Const.delay_bar_3_float,0);
        float saveProgress4 = sp.getFloat(Const.delay_bar_4_float,0);


        msView1.setText(String.format("%.2f",saveProgress1)+" ms");
        mView1.setText(String.format("%.2f",saveProgress1*0.343)+" m");
        msView2.setText(String.format("%.2f",saveProgress2)+" ms");
        mView2.setText(String.format("%.2f",saveProgress2*0.343)+" m");
        msView3.setText(String.format("%.2f",saveProgress3)+" ms");;
        mView3.setText(String.format("%.2f",saveProgress3*0.343)+" m");
        msView4.setText(String.format("%.2f",saveProgress4)+" ms");
        mView4.setText(String.format("%.2f",saveProgress4*0.343)+" m");
    }
    private void initsetSwitchDelay() {
        swG1.setChecked(sp.getBoolean(Const.switch_delay_1, false));
        swG2.setChecked(sp.getBoolean(Const.switch_delay_2, false));
        swG3.setChecked(sp.getBoolean(Const.switch_delay_3, false));
        swG4.setChecked(sp.getBoolean(Const.switch_delay_4, false));

        if(sp.getBoolean(Const.switch_delay_1, false)==true){
            delayBar1.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_2, false)==true){
            delayBar2.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_3, false)==true){
            delayBar3.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_4, false)==true){
            delayBar4.setEnabled(false);
        }
    }
}
