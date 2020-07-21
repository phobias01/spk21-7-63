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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmCrossover extends Fragment {


    public fmCrossover() {
        // Required empty public constructor
    }
    private boolean isOnMuteHigh1 = true,isOnMuteHigh2 = true,isOnMuteHigh3 = true,isOnMuteHigh4 = true, isOnMuteLow1 = true,isOnMuteLow2 = true,isOnMuteLow3 = true,isOnMuteLow4 = true;
    private ArrayList<String> arrayG1,arrayG2,arrayG3,arrayG4;
    private Context context;
    private EditText editMin,editMax;
    private TextView tvMin1,tvMin2,tvMin3,tvMin4;
    private TextView tvMax1,tvMax2,tvMax3,tvMax4;
    private RangeSeekBar crossoverBar1,crossoverBar2,crossoverBar3,crossoverBar4;
    private Switch swG1,swG2,swG3,swG4;
    private Button butSet,butMuteHigh1,butMuteHigh2,butMuteHigh3,butMuteHigh4,butMuteLow1,butMuteLow2,butMuteLow3,butMuteLow4;
    private Spinner spinGroup;
    private ArrayList<String> arrayGroup;
    private ArrayAdapter adapterGroup;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String dataOutput1 = null;
    private String dataOutput2 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_crossover, container, false);
        Shared();
        loadData();
        initCrossoverBar(view);
        initswLockdelayBar(view);
        setVolumeCrossover(view);
        initbuttonMute(view);
        return view;

    }


    private void Shared() {
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        sp = this.getActivity().getSharedPreferences(sp.getString(Const.sp_channel,null), Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void initbuttonMute(View view){
        final String dataOutput1 = "MUTE/";
        final String dataOutput2 = "UNMUTE/";
        butMuteHigh1 = (Button) view.findViewById(R.id.butMuteHigh1);
        butMuteHigh2 = (Button) view.findViewById(R.id.butMuteHigh2);
        butMuteHigh3 = (Button) view.findViewById(R.id.butMuteHigh3);
        butMuteHigh4 = (Button) view.findViewById(R.id.butMuteHigh4);
        butMuteLow1 = (Button) view.findViewById(R.id.butMuteLow1);
        butMuteLow2 = (Button) view.findViewById(R.id.butMuteLow2);
        butMuteLow3 = (Button) view.findViewById(R.id.butMuteLow3);
        butMuteLow4 = (Button) view.findViewById(R.id.butMuteLow4);
        if(isOnMuteHigh1 == sp.getBoolean(Const.stuMuteHigh1,true)) {
            butMuteHigh1.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteHigh1 = true;
        }else {
            butMuteHigh1.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteHigh1 = false;
        }
        butMuteHigh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteHigh1==true) {
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput1, arrayG1.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG1.get(i)+"HF/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteHigh1.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteHigh1 = false;
                    editor.putBoolean(Const.stuMuteHigh1,isOnMuteHigh1);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput2, arrayG1.get(i), Const.port);
                            // Log.d("26J", "BUTMUTE : " + arrayG1.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteHigh1.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteHigh1 = true;
                    editor.putBoolean(Const.stuMuteHigh1,isOnMuteHigh1);
                    editor.commit();
                }
            }
        });
        if(isOnMuteHigh2 == sp.getBoolean(Const.stuMuteHigh2,true)) {
            butMuteHigh2.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteHigh2 = true;
        }else {
            butMuteHigh2.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteHigh2 = false;
        }
        butMuteHigh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteHigh2==true) {
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput1, arrayG2.get(i), Const.port);
                           // Log.d("26J", "BUTMUTE : " + arrayG2.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteHigh2.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteHigh2 = false;
                    editor.putBoolean(Const.stuMuteHigh2,isOnMuteHigh2);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput2, arrayG2.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG2.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteHigh2.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteHigh2 = true;
                    editor.putBoolean(Const.stuMuteHigh2,isOnMuteHigh2);
                    editor.commit();
                }
            }
        });
        if(isOnMuteHigh3 == sp.getBoolean(Const.stuMuteHigh3,true)) {
            butMuteHigh3.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteHigh3 = true;
        }else {
            butMuteHigh3.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteHigh3 = false;
        }
        butMuteHigh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteHigh3==true) {
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput1, arrayG3.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG3.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteHigh3.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteHigh3 = false;
                    editor.putBoolean(Const.stuMuteHigh3,isOnMuteHigh3);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput2, arrayG3.get(i), Const.port);
                           // Log.d("26J", "BUTMUTE : " + arrayG3.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteHigh3.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteHigh3 = true;
                    editor.putBoolean(Const.stuMuteHigh3,isOnMuteHigh3);
                    editor.commit();
                }
            }
        });
        if(isOnMuteHigh4 == sp.getBoolean(Const.stuMuteHigh4,true)) {
            butMuteHigh4.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteHigh4 = true;
        }else {
            butMuteHigh4.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteHigh4 = false;
        }
        butMuteHigh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteHigh4==true) {
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput1, arrayG4.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteHigh4.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteHigh4 = false;
                    editor.putBoolean(Const.stuMuteHigh4,isOnMuteHigh4);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput2, arrayG4.get(i), Const.port);
                           // Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteHigh4.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteHigh4 = true;
                    editor.putBoolean(Const.stuMuteHigh4,isOnMuteHigh4);
                    editor.commit();
                }
            }
        });
        if(isOnMuteLow1 == sp.getBoolean(Const.stuMuteLow1,true)) {
            butMuteLow1.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteLow1 = true;
        }else {
            butMuteLow1.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteLow1 = false;
        }
        butMuteLow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteLow1==true) {
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send("HF/"+dataOutput1, arrayG1.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteLow1.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteLow1 = false;
                    editor.putBoolean(Const.stuMuteLow1,isOnMuteLow1);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput2, arrayG1.get(i), Const.port);
                            // Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteLow1.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteLow1 = true;
                    editor.putBoolean(Const.stuMuteLow1,isOnMuteLow1);
                    editor.commit();
                }
            }
        });
        if(isOnMuteLow2 == sp.getBoolean(Const.stuMuteLow2,true)) {
            butMuteLow2.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteLow2 = true;
        }else {
            butMuteLow2.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteLow2 = false;
        }
        butMuteLow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteLow2==true) {
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput1, arrayG2.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteLow2.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteLow2 = false;
                    editor.putBoolean(Const.stuMuteLow2,isOnMuteLow2);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput2, arrayG2.get(i), Const.port);
                            // Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteLow2.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteLow2 = true;
                    editor.putBoolean(Const.stuMuteLow2,isOnMuteLow2);
                    editor.commit();
                }
            }
        });
        if(isOnMuteLow3 == sp.getBoolean(Const.stuMuteLow3,true)) {
            butMuteLow3.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteLow3 = true;
        }else {
            butMuteLow3.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteLow3 = false;
        }
        butMuteLow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteLow3==true) {
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput1, arrayG3.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteLow3.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteLow3 = false;
                    editor.putBoolean(Const.stuMuteLow3,isOnMuteLow3);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput2, arrayG3.get(i), Const.port);
                            // Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteLow3.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteLow3 = true;
                    editor.putBoolean(Const.stuMuteLow3,isOnMuteLow3);
                    editor.commit();
                }
            }
        });
        if(isOnMuteLow4 == sp.getBoolean(Const.stuMuteLow4,true)) {
            butMuteLow4.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnMuteLow4 = true;
        }else {
            butMuteLow4.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnMuteLow4 = false;
        }
        butMuteLow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnMuteLow4==true) {
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput1, arrayG4.get(i), Const.port);
                            //Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput1);
                        }
                    }catch (Exception e) {}
                    butMuteLow4.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnMuteLow4 = false;
                    editor.putBoolean(Const.stuMuteLow4,isOnMuteLow4);
                    editor.commit();
                }else{
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send("LF/"+dataOutput2, arrayG4.get(i), Const.port);
                            // Log.d("26J", "BUTMUTE : " + arrayG4.get(i)+"/"+dataOutput2);
                        }
                    }catch (Exception e) {}
                    butMuteLow4.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnMuteLow4 = true;
                    editor.putBoolean(Const.stuMuteLow4,isOnMuteLow4);
                    editor.commit();
                }
            }
        });
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
    }
    private void initCrossoverBar(View view) {
        tvMin1 = (TextView) view.findViewById(R.id.tvMin1);
        tvMin2 = (TextView) view.findViewById(R.id.tvMin2);
        tvMin3 = (TextView) view.findViewById(R.id.tvMin3);
        tvMin4 = (TextView) view.findViewById(R.id.tvMin4);
        tvMax1 = (TextView) view.findViewById(R.id.tvMax1);
        tvMax2 = (TextView) view.findViewById(R.id.tvMax2);
        tvMax3 = (TextView) view.findViewById(R.id.tvMax3);
        tvMax4 = (TextView) view.findViewById(R.id.tvMax4);
        crossoverBar1 = (RangeSeekBar)view.findViewById(R.id.crossoverBar1);
        crossoverBar2 = (RangeSeekBar)view.findViewById(R.id.crossoverBar2);
        crossoverBar3 = (RangeSeekBar)view.findViewById(R.id.crossoverBar3);
        crossoverBar4 = (RangeSeekBar)view.findViewById(R.id.crossoverBar4);
        initsetCrossBar();
        crossoverBar1.setProgressDefaultColor(getResources().getColor(R.color.colorAccent));
        crossoverBar1.setProgressColor(getResources().getColor(R.color.rsbColorSeekBarDefault));
        crossoverBar2.setProgressDefaultColor(getResources().getColor(R.color.colorAccent));
        crossoverBar2.setProgressColor(getResources().getColor(R.color.rsbColorSeekBarDefault));
        crossoverBar3.setProgressDefaultColor(getResources().getColor(R.color.colorAccent));
        crossoverBar3.setProgressColor(getResources().getColor(R.color.rsbColorSeekBarDefault));
        crossoverBar4.setProgressDefaultColor(getResources().getColor(R.color.colorAccent));
        crossoverBar4.setProgressColor(getResources().getColor(R.color.rsbColorSeekBarDefault));
        crossoverBar1.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue1=0;
            int Rvalue1=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin1.setText(String.format("%.0f Hz",leftValue));
                tvMax1.setText(String.format("%.0f Hz",rightValue));
                dataOutput1 = String.format("LF/F/%.0f",leftValue);
                dataOutput2 = String.format("HF/F/%.0f",rightValue);
                if(Lvalue1!=(int)leftValue) {
                    Lvalue1 = (int)leftValue;
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayG1.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 1 : " + arrayG1.get(i) + "/" + dataOutput1);
                        }
                    }catch (Exception e){}
                }
                if(Rvalue1!=(int)rightValue) {
                    Rvalue1 = (int)rightValue;
                    try {
                        for (int i = 0; i < arrayG1.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayG1.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 1 : " + arrayG1.get(i) + "/" + dataOutput2);
                        }
                    }catch (Exception e){}
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                editor.putInt(Const.crossover_min1,Lvalue1);
                editor.putInt(Const.crossover_max1,Rvalue1);
                editor.commit();
            }
        });

        crossoverBar2.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue2=0;
            int Rvalue2=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin2.setText(String.format("%.0f Hz",leftValue));
                tvMax2.setText(String.format("%.0f Hz",rightValue));
                dataOutput1 = String.format("LF/F/%.0f",leftValue);
                dataOutput2 = String.format("HF/F/%.0f",rightValue);
                if(Lvalue2!=(int)leftValue) {
                    Lvalue2 = (int)leftValue;
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayG2.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 2 : " + arrayG2.get(i) + "/" + dataOutput1);
                        }
                    }catch (Exception e){}
                }
                if(Rvalue2!=(int)rightValue) {
                    Rvalue2 = (int)rightValue;
                    try {
                        for (int i = 0; i < arrayG2.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayG2.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 2 : " + arrayG2.get(i) + "/" + dataOutput2);
                        }
                    }catch (Exception e){}
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                editor.putInt(Const.crossover_min2,Lvalue2);
                editor.putInt(Const.crossover_max2,Rvalue2);
                editor.commit();
            }
        });

        crossoverBar3.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue3=0;
            int Rvalue3=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin3.setText(String.format("%.0f Hz",leftValue));
                tvMax3.setText(String.format("%.0f Hz",rightValue));
                dataOutput1 = String.format("LF/F/%.0f",leftValue);
                dataOutput2 = String.format("HF/F/%.0f",rightValue);
                if(Lvalue3!=(int)leftValue) {
                    Lvalue3 = (int)leftValue;
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayG3.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 3 : " + arrayG3.get(i) + "/" + dataOutput1);
                        }
                    }catch (Exception e){}
                }
                if(Rvalue3!=(int)rightValue) {
                    Rvalue3 = (int)rightValue;
                    try {
                        for (int i = 0; i < arrayG3.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayG3.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 3 : " + arrayG3.get(i) + "/" + dataOutput2);
                        }
                    }catch (Exception e){}
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                editor.putInt(Const.crossover_min3,Lvalue3);
                editor.putInt(Const.crossover_max3,Rvalue3);
                editor.commit();
            }
        });

        crossoverBar4.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue4=0;
            int Rvalue4=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin4.setText(String.format("%.0f Hz",leftValue));
                tvMax4.setText(String.format("%.0f Hz",rightValue));
                dataOutput1 = String.format("LF/F/%.0f",leftValue);
                dataOutput2 = String.format("HF/F/%.0f",rightValue);
                if(Lvalue4!=(int)leftValue) {
                    Lvalue4 = (int)leftValue;
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send(dataOutput1, arrayG4.get(i), Const.port);
                            //Log.d("26J", "Cross Bar : 4 " + arrayG4.get(i) + "/" + dataOutput1);
                        }
                    }catch (Exception e){}
                }
                if(Rvalue4!=(int)rightValue) {
                    Rvalue4 = (int)rightValue;
                    try {
                        for (int i = 0; i < arrayG4.size(); i++) {
                            SimpleTcpClient.send(dataOutput2, arrayG4.get(i), Const.port);
                            //Log.d("26J", "Cross Bar 4 : " + arrayG4.get(i) + "/" + dataOutput2);
                        }
                    }catch (Exception e){}
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                editor.putInt(Const.crossover_min4,Lvalue4);
                editor.putInt(Const.crossover_max4,Rvalue4);
                editor.commit();
            }
        });


    }
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void setVolumeCrossover (View view) {
        spinGroup = (Spinner) view.findViewById(R.id.spinGroup);
        editMin = (EditText)view.findViewById(R.id.editMin);
        editMax = (EditText)view.findViewById(R.id.editMax);
        butSet = (Button) view.findViewById(R.id.butSet);
        arrayGroup = new ArrayList<>();
        arrayGroup.add("GROUP1");
        arrayGroup.add("GROUP2");
        arrayGroup.add("GROUP3");
        arrayGroup.add("GROUP4");
        adapterGroup = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_dropdown_item,arrayGroup);
        spinGroup.setAdapter(adapterGroup);

        butSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int posGroup = spinGroup.getSelectedItemPosition();
                    String min = editMin.getText().toString();
                    String max = editMax.getText().toString();
                    int valueMin = Integer.valueOf(min);
                    int valueMax = Integer.valueOf(max);
                    switch (posGroup) {
                        case 0:
                            if (valueMin < valueMax) {
                                tvMin1.setText(min + " Hz");
                                tvMax1.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_min1, valueMin);
                                editor.putInt(Const.set_crossover_max1, valueMax);
                                editor.commit();
                                crossoverBar1.setValue(valueMin,valueMax);
                                if(arrayG1.size()!=0) {
                                    dataOutput1 = String.format("LF/F/%.0f", valueMin);
                                    dataOutput2 = String.format("HF/F/%.0f", valueMax);
                                    for (int i = 0; i < arrayG1.size(); i++) {
                                        SimpleTcpClient.send(dataOutput1, arrayG1.get(i), Const.port);
                                        SimpleTcpClient.send(dataOutput2, arrayG1.get(i), Const.port);
                                        //Log.d("26J", "Cross Bar 1: " + arrayG1.get(i) + "/" + dataOutput1);
                                        //Log.d("26J", "Cross Bar 1: " + arrayG1.get(i) + "/" + dataOutput2);
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter a LF value to be less than the HF value.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 1:
                            if (valueMin < valueMax) {
                                tvMin2.setText(min + " Hz");
                                tvMax2.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_min2, valueMin);
                                editor.putInt(Const.set_crossover_max2, valueMax);
                                editor.commit();
                                crossoverBar2.setValue(valueMin,valueMax);
                                if(arrayG2.size()!=0) {
                                    dataOutput1 = String.format("LF/F/%.0f", valueMin);
                                    dataOutput2 = String.format("HF/F/%.0f", valueMax);
                                    for (int i = 0; i < arrayG2.size(); i++) {
                                        SimpleTcpClient.send(dataOutput1, arrayG2.get(i), Const.port);
                                        SimpleTcpClient.send(dataOutput2, arrayG2.get(i), Const.port);
                                        //Log.d("26J", "Cross Bar 2: " + arrayG2.get(i) + "/" + dataOutput1);
                                        //Log.d("26J", "Cross Bar 2: " + arrayG2.get(i) + "/" + dataOutput2);
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter a LF value to be less than the HF value.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 2:
                            if (valueMin < valueMax) {
                                tvMin3.setText(min + " Hz");
                                tvMax3.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_min3, valueMin);
                                editor.putInt(Const.set_crossover_max3, valueMax);
                                editor.commit();
                                crossoverBar3.setValue(valueMin,valueMax);
                                if(arrayG3.size()!=0) {
                                    dataOutput1 = String.format("LF/F/%.0f", valueMin);
                                    dataOutput2 = String.format("HF/F/%.0f", valueMax);
                                    for (int i = 0; i < arrayG3.size(); i++) {
                                        SimpleTcpClient.send(dataOutput1, arrayG3.get(i), Const.port);
                                        SimpleTcpClient.send(dataOutput2, arrayG3.get(i), Const.port);
                                        //Log.d("26J", "Cross Bar 3: " + arrayG3.get(i) + "/" + dataOutput1);
                                        //Log.d("26J", "Cross Bar 3: " + arrayG3.get(i) + "/" + dataOutput2);
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter a LF value to be less than the HF value.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 3:
                            if (valueMin < valueMax) {
                                tvMin4.setText(min + " Hz");
                                tvMax4.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_min4, valueMin);
                                editor.putInt(Const.set_crossover_max4, valueMax);
                                editor.commit();
                                crossoverBar4.setValue(valueMin,valueMax);
                                if(arrayG4.size()!=0) {
                                    dataOutput1 = String.format("LF/F/%.0f", valueMin);
                                    dataOutput2 = String.format("HF/F/%.0f", valueMax);
                                    for (int i = 0; i < arrayG4.size(); i++) {
                                        SimpleTcpClient.send(dataOutput1, arrayG4.get(i), Const.port);
                                        SimpleTcpClient.send(dataOutput2, arrayG4.get(i), Const.port);
                                        //Log.d("26J", "Cross Bar 4: " + arrayG4.get(i) + "/" + dataOutput1);
                                        //Log.d("26J", "Cross Bar 4: " + arrayG4.get(i) + "/" + dataOutput2);
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter a LF value to be less than the HF value.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                }catch (Exception e) {Toast.makeText(getActivity(),"Please enter value.", Toast.LENGTH_SHORT).show();}
            }
        });

    }
    private void initsetCrossBar() {
        crossoverBar1.setRange(20,20000);
        crossoverBar2.setRange(20,20000);
        crossoverBar3.setRange(20,20000);
        crossoverBar4.setRange(20,20000);
        int minValue1 = sp.getInt(Const.crossover_min1,1000);
        int minValue2 = sp.getInt(Const.crossover_min2,1000);
        int minValue3 = sp.getInt(Const.crossover_min3,1000);
        int minValue4 = sp.getInt(Const.crossover_min4,1000);
        int maxValue1 = sp.getInt(Const.crossover_max1,1001);
        int maxValue2 = sp.getInt(Const.crossover_max2,1001);
        int maxValue3 = sp.getInt(Const.crossover_max3,1001);
        int maxValue4 = sp.getInt(Const.crossover_max4,1001);

        crossoverBar1.setValue(minValue1,maxValue1);
        crossoverBar2.setValue(minValue2,maxValue2);
        crossoverBar3.setValue(minValue3,maxValue3);
        crossoverBar4.setValue(minValue4,maxValue4);

        tvMin1.setText(String.valueOf(minValue1+" Hz"));
        tvMin2.setText(String.valueOf(minValue2+" Hz"));
        tvMin3.setText(String.valueOf(minValue3+" Hz"));
        tvMin4.setText(String.valueOf(minValue4+" Hz"));
        tvMax1.setText(String.valueOf(maxValue1+" Hz"));
        tvMax2.setText(String.valueOf(maxValue2+" Hz"));
        tvMax3.setText(String.valueOf(maxValue3+" Hz"));
        tvMax4.setText(String.valueOf(maxValue4+" Hz"));
    }
    private void initswLockdelayBar(View view){
        swG1 = (Switch) view.findViewById(R.id.swCross1);
        swG2 = (Switch) view.findViewById(R.id.swCross2);
        swG3 = (Switch) view.findViewById(R.id.swCross3);
        swG4 = (Switch) view.findViewById(R.id.swCross4);
        initsetSwitchCrossover();
        swG1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    crossoverBar1.setEnabled(false);
                }else{
                    crossoverBar1.setEnabled(true);
                }
                editor.putBoolean(Const.switch_crossover_1, isChecked);
                editor.commit();
            }
        });

        swG2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    crossoverBar2.setEnabled(false);
                }else{
                    crossoverBar2.setEnabled(true);
                }
                editor.putBoolean(Const.switch_crossover_2, isChecked);
                editor.commit();
            }
        });

        swG3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    crossoverBar3.setEnabled(false);
                }else{
                    crossoverBar3.setEnabled(true);
                }
                editor.putBoolean(Const.switch_crossover_3, isChecked);
                editor.commit();
            }
        });

        swG4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    crossoverBar4.setEnabled(false);
                }else{
                    crossoverBar4.setEnabled(true);
                }
                editor.putBoolean(Const.switch_crossover_4, isChecked);
                editor.commit();
            }
        });
    }
    private void initsetSwitchCrossover() {
        swG1.setChecked(sp.getBoolean(Const.switch_crossover_1, false));
        swG2.setChecked(sp.getBoolean(Const.switch_crossover_2, false));
        swG3.setChecked(sp.getBoolean(Const.switch_crossover_3, false));
        swG4.setChecked(sp.getBoolean(Const.switch_crossover_4, false));

        if(sp.getBoolean(Const.switch_crossover_1, false)==true){
            crossoverBar1.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_crossover_2, false)==true){
            crossoverBar2.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_crossover_3, false)==true){
            crossoverBar3.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_crossover_4, false)==true){
            crossoverBar4.setEnabled(false);
        }
    }
}
