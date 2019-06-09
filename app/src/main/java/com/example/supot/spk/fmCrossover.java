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
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmCrossover extends Fragment {


    public fmCrossover() {
        // Required empty public constructor
    }

    private Context context;
    private EditText editMin,editMax;
    private TextView tvMin1,tvMin2,tvMin3,tvMin4;
    private TextView tvMax1,tvMax2,tvMax3,tvMax4;
    private RangeSeekBar crossoverBar1,crossoverBar2,crossoverBar3,crossoverBar4;
    private Switch swG1,swG2,swG3,swG4;
    private Button butSet;
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
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
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
        initCrossoverBar(view);
        initswLockdelayBar(view);
        setVolumeCrossover(view);
        return view;

    }
    private void initCrossoverBar(View view) {
        initsetCrossBar();
        crossoverBar1.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue1=0;
            int Rvalue1=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin1.setText(String.format("%.0f Hz",leftValue));
                tvMax1.setText(String.format("%.0f Hz",rightValue));
                dataOutput1 = String.format("CrossoverMin1/%.0f",leftValue);
                dataOutput2 = String.format("CrossoverMax1/%.0f",rightValue);
                if(Lvalue1!=(int)leftValue) {
                    Lvalue1 = (int)leftValue;
                    SimpleTcpClient.send(dataOutput1, Const.ip, Const.port);
                }
                if(Rvalue1!=(int)rightValue) {
                    Rvalue1 = (int)rightValue;
                    SimpleTcpClient.send(dataOutput2, Const.ip, Const.port);
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
                dataOutput1 = String.format("CrossoverMi2/%.0f",leftValue);
                dataOutput2 = String.format("CrossoverMa2/%.0f",rightValue);
                if(Lvalue2!=(int)leftValue) {
                    Lvalue2 = (int)leftValue;
                    SimpleTcpClient.send(dataOutput1, Const.ip, Const.port);
                }
                if(Rvalue2!=(int)rightValue) {
                    Rvalue2 = (int)rightValue;
                    SimpleTcpClient.send(dataOutput2, Const.ip, Const.port);
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
                dataOutput1 = String.format("CrossoverMin3/%.0f",leftValue);
                dataOutput2 = String.format("CrossoverMax3/%.0f",rightValue);
                if(Lvalue3!=(int)leftValue) {
                    Lvalue3 = (int)leftValue;
                    SimpleTcpClient.send(dataOutput1, Const.ip, Const.port);
                }
                if(Rvalue3!=(int)rightValue) {
                    Rvalue3 = (int)rightValue;
                    SimpleTcpClient.send(dataOutput2, Const.ip, Const.port);
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
                dataOutput1 = String.format("CrossoverMin4/%.0f",leftValue);
                dataOutput2 = String.format("CrossoverMax4/%.0f",rightValue);
                if(Lvalue4!=(int)leftValue) {
                    Lvalue4 = (int)leftValue;
                    SimpleTcpClient.send(dataOutput1, Const.ip, Const.port);
                }
                if(Rvalue4!=(int)rightValue) {
                    Rvalue4 = (int)rightValue;
                    SimpleTcpClient.send(dataOutput2, Const.ip, Const.port);
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
                    if(posGroup==0) {
                        if (valueMin < valueMax) {
                            if (!min.equals(null)) {
                                tvMin1.setText(min + " Hz");
                                editor.putInt(Const.set_crossover_min1, valueMin);
                                editor.commit();
                            }
                            if (!max.equals(null)) {
                                tvMax1.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_max1, valueMax);
                                editor.commit();
                            }
                            int cmin = sp.getInt(Const.crossover_min1, 50);
                            int cmax = sp.getInt(Const.crossover_max1, 500);
                            crossoverBar1.setValue(sp.getInt(Const.set_crossover_min1, cmin), sp.getInt(Const.set_crossover_max1, cmax));
                            SimpleTcpClient.send("CrossoverMin1/" + String.valueOf(cmin), Const.ip, Const.port);
                            SimpleTcpClient.send("CrossoverMax1/" + String.valueOf(cmax), Const.ip, Const.port);
                        } else {
                            Toast.makeText(getActivity(), "Please enter a minvalue to be less than the maxvalue.", Toast.LENGTH_SHORT).show();
                        }
                    }else if(posGroup==1) {
                        if (valueMin < valueMax) {
                            if (!min.equals(null)) {
                                tvMin2.setText(min + " Hz");
                                editor.putInt(Const.set_crossover_min2, valueMin);
                                editor.commit();
                            }
                            if (!max.equals(null)) {
                                tvMax2.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_max2, valueMax);
                                editor.commit();
                            }
                            int cmin = sp.getInt(Const.crossover_min2, 50);
                            int cmax = sp.getInt(Const.crossover_max2, 500);
                            crossoverBar2.setValue(sp.getInt(Const.set_crossover_min2, cmin), sp.getInt(Const.set_crossover_max2, cmax));
                            SimpleTcpClient.send("CrossoverMin2/" + String.valueOf(cmin), Const.ip, Const.port);
                            SimpleTcpClient.send("CrossoverMax2/" + String.valueOf(cmax), Const.ip, Const.port);
                        } else {
                            Toast.makeText(getActivity(), "Please enter a minvalue to be less than the maxvalue.", Toast.LENGTH_SHORT).show();
                        }
                    }else if(posGroup==2) {
                        if (valueMin < valueMax) {
                            if (!min.equals(null)) {
                                tvMin3.setText(min + " Hz");
                                editor.putInt(Const.set_crossover_min3, valueMin);
                                editor.commit();
                            }
                            if (!max.equals(null)) {
                                tvMax3.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_max3, valueMax);
                                editor.commit();
                            }
                            int cmin = sp.getInt(Const.crossover_min3, 50);
                            int cmax = sp.getInt(Const.crossover_max3, 500);
                            crossoverBar3.setValue(sp.getInt(Const.set_crossover_min3, cmin), sp.getInt(Const.set_crossover_max3, cmax));
                            SimpleTcpClient.send("CrossoverMin3/" + String.valueOf(cmin), Const.ip, Const.port);
                            SimpleTcpClient.send("CrossoverMax3/" + String.valueOf(cmax), Const.ip, Const.port);
                        } else {
                            Toast.makeText(getActivity(), "Please enter a minvalue to be less than the maxvalue.", Toast.LENGTH_SHORT).show();
                        }
                    }else if(posGroup==3) {
                        if (valueMin < valueMax) {
                            if (!min.equals(null)) {
                                tvMin4.setText(min + " Hz");
                                editor.putInt(Const.set_crossover_min4, valueMin);
                                editor.commit();
                            }
                            if (!max.equals(null)) {
                                tvMax4.setText(max + " Hz");
                                editor.putInt(Const.set_crossover_max4, valueMax);
                                editor.commit();
                            }
                            int cmin = sp.getInt(Const.crossover_min4, 50);
                            int cmax = sp.getInt(Const.crossover_max4, 500);
                            crossoverBar4.setValue(sp.getInt(Const.set_crossover_min4, cmin), sp.getInt(Const.set_crossover_max4, cmax));
                            SimpleTcpClient.send("CrossoverMin4/" + String.valueOf(cmin), Const.ip, Const.port);
                            SimpleTcpClient.send("CrossoverMax4/" + String.valueOf(cmax), Const.ip, Const.port);
                        } else {
                            Toast.makeText(getActivity(), "Please enter a minvalue to be less than the maxvalue.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e) {Toast.makeText(getActivity(),"Please enter a value.", Toast.LENGTH_SHORT).show();}

            }
        });

    }
    private void initsetCrossBar() {
        crossoverBar1.setRange(20,20000);
        crossoverBar2.setRange(20,20000);
        crossoverBar3.setRange(20,20000);
        crossoverBar4.setRange(20,20000);
        int minValue1 = sp.getInt(Const.crossover_min1,20);
        int minValue2 = sp.getInt(Const.crossover_min2,20);
        int minValue3 = sp.getInt(Const.crossover_min3,20);
        int minValue4 = sp.getInt(Const.crossover_min4,20);
        int maxValue1 = sp.getInt(Const.crossover_max1,20000);
        int maxValue2 = sp.getInt(Const.crossover_max2,20000);
        int maxValue3 = sp.getInt(Const.crossover_max3,20000);
        int maxValue4 = sp.getInt(Const.crossover_max4,20000);

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
