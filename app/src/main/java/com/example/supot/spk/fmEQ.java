package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmEQ extends Fragment {


    public fmEQ() {
        // Required empty public constructor
    }
    private Spinner spFrequencyEq1,spFrequencyEq2,spFrequencyEq3,spFrequencyEq4,spFrequencyEq5;
    private ArrayAdapter adapterEq1,adapterEq2,adapterEq3,adapterEq4,adapterEq5;
    private Context context;
    private ArrayList<String> arrayG1,arrayG2,arrayG3,arrayG4,arrayAllIp,arrayFrequency,arrayFrequency2;
    private boolean isOnEQ = true;
    private SeekBar eqBar1,eqBar2,eqBar3,eqBar4,eqBar5,eqBar_Master,BarG1,BarG2,BarG3,BarG4;
    private Button butStopEq,butReEq;
    private TextView tvEq,tvFq1,tvFq2,tvFq3,tvFq4,tvFq5;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String dataOutput = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_eq, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("EQUALIZER");
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        loadData();
        initEq(view);
        createFrequency(view);
        tvFq1.setText(arrayFrequency.get(sp.getInt(Const.position_EQ1, 1))+"Hz");
        tvFq2.setText(arrayFrequency.get(sp.getInt(Const.position_EQ2, 1))+"Hz");
        tvFq3.setText(arrayFrequency.get(sp.getInt(Const.position_EQ3, 1))+"Hz");
        tvFq4.setText(arrayFrequency.get(sp.getInt(Const.position_EQ4, 1))+"Hz");
        tvFq5.setText(arrayFrequency.get(sp.getInt(Const.position_EQ5, 1))+"Hz");
        if(isOnEQ == sp.getBoolean(Const.stuEQ,true)) {
            onSeekBar();
            butStopEq.setBackgroundColor(getResources().getColor(R.color.color3));
            isOnEQ = true;
        }else {
            offSeekBar();
            butStopEq.setBackgroundColor(getResources().getColor(R.color.color5));
            isOnEQ = false;
        }
        return view;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    private void createFrequency(View view){
        spFrequencyEq1 = (Spinner) view.findViewById(R.id.spinner1);
        spFrequencyEq2 = (Spinner) view.findViewById(R.id.spinner2);
        spFrequencyEq3 = (Spinner) view.findViewById(R.id.spinner3);
        spFrequencyEq4 = (Spinner) view.findViewById(R.id.spinner4);
        spFrequencyEq5 = (Spinner) view.findViewById(R.id.spinner5);
        arrayFrequency = new ArrayList<>();
            arrayFrequency.add("32");
            arrayFrequency.add("64");
            arrayFrequency.add("125");
            arrayFrequency.add("250");
            arrayFrequency.add("500");
            arrayFrequency.add("1k");
            arrayFrequency.add("2k");
            arrayFrequency.add("4k");
            arrayFrequency.add("8k");
            arrayFrequency.add("16k");
        arrayFrequency2 = new ArrayList<>();
            arrayFrequency2.add("32");
            arrayFrequency2.add("64");
            arrayFrequency2.add("125");
            arrayFrequency2.add("250");
            arrayFrequency2.add("500");
            arrayFrequency2.add("1");
            arrayFrequency2.add("2");
            arrayFrequency2.add("4");
            arrayFrequency2.add("8");
            arrayFrequency2.add("16");
        adapterEq1 = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayFrequency);
        spFrequencyEq1.setAdapter(adapterEq1);
        adapterEq2 = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayFrequency);
        spFrequencyEq2.setAdapter(adapterEq2);
        adapterEq3 = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayFrequency);
        spFrequencyEq3.setAdapter(adapterEq3);
        adapterEq4 = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayFrequency);
        spFrequencyEq4.setAdapter(adapterEq4);
        adapterEq5 = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, arrayFrequency);
        spFrequencyEq5.setAdapter(adapterEq5);
    }
    private void loadData() {
        Gson gson = new Gson();
        String jsonAllIp = sp.getString(Const.list_AllIp, null);
        String json1 = sp.getString(Const.list_IpG1, null);
        String json2 = sp.getString(Const.list_IpG2, null);
        String json3 = sp.getString(Const.list_IpG3, null);
        String json4= sp.getString(Const.list_IpG4, null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        arrayAllIp = gson.fromJson(jsonAllIp, type);
        arrayG1 = gson.fromJson(json1, type);
        arrayG2 = gson.fromJson(json2, type);
        arrayG3 = gson.fromJson(json3, type);
        arrayG4 = gson.fromJson(json4, type);

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
    }

    private void  initEq(View view){
        eqBar1= (SeekBar) view.findViewById(R.id.eqBar1);
        eqBar2 = (SeekBar) view.findViewById(R.id.eqBar2);
        eqBar3 = (SeekBar) view.findViewById(R.id.eqBar3);
        eqBar4 = (SeekBar) view.findViewById(R.id.eqBar4);
        eqBar5 = (SeekBar) view.findViewById(R.id.eqBar5);
        BarG1 = (SeekBar)  view.findViewById(R.id.BarG1);
        BarG2 = (SeekBar)  view.findViewById(R.id.BarG2);
        BarG3 = (SeekBar)  view.findViewById(R.id.BarG3);
        BarG4 = (SeekBar)  view.findViewById(R.id.BarG4);
        eqBar_Master = (SeekBar) view.findViewById(R.id.eqBar_Master);
        butReEq = (Button) view.findViewById(R.id.butReEq);
        butStopEq = (Button) view.findViewById(R.id.butStopEq);
        tvEq = (TextView) view.findViewById(R.id.tvEq);
        tvFq1 = (TextView) view.findViewById(R.id.tvFq1);
        tvFq2 = (TextView) view.findViewById(R.id.tvFq2);
        tvFq3 = (TextView) view.findViewById(R.id.tvFq3);
        tvFq4 = (TextView) view.findViewById(R.id.tvFq4);
        tvFq5 = (TextView) view.findViewById(R.id.tvFq5);

        int saveProgress = sp.getInt(Const.master_eq_slide,40)-80;
        tvEq.setText(String.valueOf("MASTER : "+saveProgress+" dB"));
        initEqBar();

        eqBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int posFq = spFrequencyEq1.getSelectedItemPosition();
                progressChanged = (progress-150)*0.1f;
                value = progress;
                tvEq.setText(String.format(arrayFrequency.get(posFq)+"Hz : %.2f dB",progressChanged));
                tvFq1.setText(arrayFrequency.get(sp.getInt(Const.position_EQ1, 1))+"Hz");
                /*if(sp.getInt(Const.position_EQ1,1)>4){
                    int Fq = Integer.valueOf(arrayFrequency2.get(sp.getInt(Const.position_EQ1, 1)));
                    dataOutput = String.format("EQ1/F/" + Fq*1000 + "/V/%.2f", progressChanged);
                }else {*/
                    dataOutput = String.format("EQ1/F/" + arrayFrequency.get(sp.getInt(Const.position_EQ1, 1)) + "/V/%.2f", progressChanged);
                //}

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < arrayAllIp.size(); i++) {
                                SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                                //Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                            }
                        }catch (Exception e) {}
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ1,spFrequencyEq1.getSelectedItemPosition());
                editor.commit();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ1,spFrequencyEq1.getSelectedItemPosition());
                editor.putInt(Const.master_eq_slide_value_1,value);
                editor.putString(Const.master_eq_slide_value_1_string,dataOutput);
                editor.commit();
            }
        });
        eqBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int posFq = spFrequencyEq2.getSelectedItemPosition();
                progressChanged = (progress-150)*0.1f;
                value = progress;
                tvEq.setText(String.format(arrayFrequency.get(posFq)+"Hz : %.2f dB",progressChanged));
                tvFq2.setText(arrayFrequency.get(sp.getInt(Const.position_EQ2, 1))+"Hz");
                if(sp.getInt(Const.position_EQ2,1)>4){
                    int Fq = Integer.valueOf(arrayFrequency2.get(sp.getInt(Const.position_EQ2, 1)));
                    dataOutput = String.format("EQ2/F/" + Fq*1000 + "/V/%.2f", progressChanged);
                }else {
                    dataOutput = String.format("EQ2/F/" + arrayFrequency.get(sp.getInt(Const.position_EQ2, 1)) + "/V/%.2f", progressChanged);
                }
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        //Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ2,spFrequencyEq2.getSelectedItemPosition());
                editor.commit();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide_value_2,value);
                editor.putString(Const.master_eq_slide_value_2_string,dataOutput);
                editor.commit();

            }
        });
        eqBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int posFq = spFrequencyEq3.getSelectedItemPosition();
                progressChanged = (progress-150)*0.1f;
                value = progress;
                tvEq.setText(String.format(arrayFrequency.get(posFq)+"Hz : %.2f dB",progressChanged));
                tvFq3.setText(arrayFrequency.get(sp.getInt(Const.position_EQ3, 1))+"Hz");
                if(sp.getInt(Const.position_EQ3,1)>4){
                    int Fq = Integer.valueOf(arrayFrequency2.get(sp.getInt(Const.position_EQ3, 1)));
                    dataOutput = String.format("EQ3/F/" + Fq*1000 + "/V/%.2f", progressChanged);
                }else {
                    dataOutput = String.format("EQ3/F/" + arrayFrequency.get(sp.getInt(Const.position_EQ3, 1)) + "/V/%.2f", progressChanged);
                }
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        //Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ3,spFrequencyEq3.getSelectedItemPosition());
                editor.commit();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide_value_3,value);
                editor.putString(Const.master_eq_slide_value_3_string,dataOutput);
                editor.commit();
            }
        });
        eqBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int posFq = spFrequencyEq4.getSelectedItemPosition();
                progressChanged = (progress-150)*0.1f;
                value = progress;
                tvEq.setText(String.format(arrayFrequency.get(posFq)+"Hz : %.2f dB",progressChanged));
                tvFq4.setText(arrayFrequency.get(sp.getInt(Const.position_EQ4, 1))+"Hz");
                if(sp.getInt(Const.position_EQ4,1)>4){
                    int Fq = Integer.valueOf(arrayFrequency2.get(sp.getInt(Const.position_EQ4, 1)));
                    dataOutput = String.format("EQ4/F/" + Fq*1000 + "/V/%.2f", progressChanged);
                }else {
                    dataOutput = String.format("EQ4/F/" + arrayFrequency.get(sp.getInt(Const.position_EQ4, 1)) + "/V/%.2f", progressChanged);
                }
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        //Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ4,spFrequencyEq4.getSelectedItemPosition());
                editor.commit();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide_value_4,value);
                editor.putString(Const.master_eq_slide_value_4_string,dataOutput);
                editor.commit();
            }
        });
        eqBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int posFq = spFrequencyEq5.getSelectedItemPosition();
                progressChanged = (progress-150)*0.1f;
                value = progress;
                tvEq.setText(String.format(arrayFrequency.get(posFq)+"Hz : %.2f dB",progressChanged));
                tvFq5.setText(arrayFrequency.get(sp.getInt(Const.position_EQ5, 1))+"Hz");
                if(sp.getInt(Const.position_EQ5,1)>4){
                    int Fq = Integer.valueOf(arrayFrequency2.get(sp.getInt(Const.position_EQ5, 1)));
                    dataOutput = String.format("EQ5/F/" + Fq*1000 + "/V/%.2f", progressChanged);
                }else {
                    dataOutput = String.format("EQ5/F/" + arrayFrequency.get(sp.getInt(Const.position_EQ5, 1)) + "/V/%.2f", progressChanged);
                }
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        //Log.d("26J", "EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.position_EQ5,spFrequencyEq5.getSelectedItemPosition());
                editor.commit();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide_value_5,value);
                editor.putString(Const.master_eq_slide_value_5_string,dataOutput);
                editor.commit();
            }
        });

        eqBar_Master.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvEq.setText(String.format("MASTER : %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOL/%.0f",progressChanged);
                try {
                    for (int i = 0; i < arrayAllIp.size(); i++) {
                        SimpleTcpClient.send(dataOutput, arrayAllIp.get(i), Const.port);
                        //Log.d("26J", "MAS EQ : " + arrayAllIp.get(i) + "/" + dataOutput);
                    }
                }catch (Exception e) {}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide,value);
                editor.putString(Const.master_eq_slide_string,dataOutput);
                editor.commit();
            }
        });
        BarG1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            float progressChanged;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvEq.setText(String.format("G1 : %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOLG1/%.0f",progressChanged);
                try {
                    for(int i = 0;i<arrayG1.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG1.get(i), Const.port);
                        //Log.d("26J","MAS Bar 1 : "+arrayG1.get(i)+"/"+dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_1,value);
                editor.commit();
            }
        });
        BarG2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            float progressChanged;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvEq.setText(String.format("G2: %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOLG2/%.0f",progressChanged);
                try {
                    for(int i = 0;i<arrayG2.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG2.get(i), Const.port);
                        //Log.d("26J","MAS Bar 2 : "+arrayG2.get(i)+"/"+dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_2,value);
                editor.commit();
            }
        });
        BarG3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            float progressChanged;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvEq.setText(String.format("G3 : %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOLG3/%.0f",progressChanged);
                try {
                    for(int i = 0;i<arrayG3.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG3.get(i), Const.port);
                        //Log.d("26J","MAS Bar 3 : "+arrayG3.get(i)+"/"+dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_3,value);
                editor.commit();
            }
        });
        BarG4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            float progressChanged;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvEq.setText(String.format("G4 : %.0f dB",progressChanged));
                dataOutput = String.format("MASTERVOLG4/%.0f",progressChanged);
                try {
                    for(int i = 0;i<arrayG4.size();i++) {
                        SimpleTcpClient.send(dataOutput, arrayG4.get(i), Const.port);
                        //Log.d("26J","MAS Bar 4 : "+arrayG4.get(i)+"/"+dataOutput);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_4,value);
                editor.commit();
            }
        });
        butStopEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnEQ==true) {
                    offSeekBar();
                    butStopEq.setBackgroundColor(getResources().getColor(R.color.color5));
                    isOnEQ = false;
                    editor.putBoolean(Const.stuEQ,isOnEQ);
                    editor.commit();
                }else{
                    onSeekBar();
                    butStopEq.setBackgroundColor(getResources().getColor(R.color.color3));
                    isOnEQ = true;
                    editor.putBoolean(Const.stuEQ,isOnEQ);
                    editor.commit();
                }
            }
        });

        butReEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnEQ) {
                    eqBar1.setProgress(150);
                    eqBar2.setProgress(150);
                    eqBar3.setProgress(150);
                    eqBar4.setProgress(150);
                    eqBar5.setProgress(150);
                    eqBar_Master.setProgress(80);
                    BarG1.setProgress(80);
                    BarG2.setProgress(80);
                    BarG3.setProgress(80);
                    BarG4.setProgress(80);

                    editor.putInt(Const.master_eq_slide_value_1,150);
                    editor.putInt(Const.master_eq_slide_value_2,150);
                    editor.putInt(Const.master_eq_slide_value_3,150);
                    editor.putInt(Const.master_eq_slide_value_4,150);
                    editor.putInt(Const.master_eq_slide_value_5,150);
                    editor.putInt(Const.master_eq_slide,80);
                    editor.putInt(Const.group_value_1,80);
                    editor.putInt(Const.group_value_2,80);
                    editor.putInt(Const.group_value_3,80);
                    editor.putInt(Const.group_value_4,80);
                    editor.commit();
                }
            }
        });
        /*butMtg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MTG.class);
                startActivity(intent);
            }
        });*/
    }
    private void offSeekBar() {
        eqBar1.setEnabled(false);
        eqBar2.setEnabled(false);
        eqBar3.setEnabled(false);
        eqBar4.setEnabled(false);
        eqBar5.setEnabled(false);
        eqBar_Master.setEnabled(false);
        BarG1.setEnabled(false);
        BarG2.setEnabled(false);
        BarG3.setEnabled(false);
        BarG4.setEnabled(false);
    }

    private void onSeekBar() {
        eqBar1.setEnabled(true);
        eqBar2.setEnabled(true);
        eqBar3.setEnabled(true);
        eqBar4.setEnabled(true);
        eqBar5.setEnabled(true);
        eqBar_Master.setEnabled(true);
        BarG1.setEnabled(true);
        BarG2.setEnabled(true);
        BarG3.setEnabled(true);
        BarG4.setEnabled(true);
    }
    private void initEqBar() {
        eqBar1.setMax(300);
        eqBar2.setMax(300);
        eqBar3.setMax(300);
        eqBar4.setMax(300);
        eqBar5.setMax(300);
        eqBar_Master.setMax(80);
        BarG1.setMax(80);
        BarG2.setMax(80);
        BarG3.setMax(80);
        BarG4.setMax(80);

        eqBar1.setProgress(sp.getInt(Const.master_eq_slide_value_1,150));
        eqBar2.setProgress(sp.getInt(Const.master_eq_slide_value_2,150));
        eqBar3.setProgress(sp.getInt(Const.master_eq_slide_value_3,150));
        eqBar4.setProgress(sp.getInt(Const.master_eq_slide_value_4,150));
        eqBar5.setProgress(sp.getInt(Const.master_eq_slide_value_5,150));
        eqBar_Master.setProgress(sp.getInt(Const.master_eq_slide,80));
        BarG1.setProgress(sp.getInt(Const.group_value_1,80));
        BarG2.setProgress(sp.getInt(Const.group_value_2,80));
        BarG3.setProgress(sp.getInt(Const.group_value_3,80));
        BarG4.setProgress(sp.getInt(Const.group_value_4,80));
    }
}
