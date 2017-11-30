package edu.aku.hassannaqvi.src_preg.ui.Recruitment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySecRcBinding;

public class SecRCActivity extends AppCompatActivity
{

    ActivitySecRcBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sec_rc);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_sec_rc);
        setupViews();


    }

    public void setupViews() {
        bl.rc01.setManager(getSupportFragmentManager());
        bl.rc03.setManager(getSupportFragmentManager());

        bl.rc04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc04a.isChecked()) {
                    bl.fldGrprc05.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc05.setVisibility(View.GONE);
                    bl.rc05.clearCheck();
                    bl.rc0588x.setText(null);
                }
            }
        });

        bl.rc0588.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.rc0588x.setVisibility(View.VISIBLE);
                } else {
                    bl.rc0588x.setVisibility(View.GONE);
                    bl.rc0588x.setText(null);
                }
            }
        });


        bl.rc06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc06a.isChecked()) {
                    bl.fldGrprc07.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc07.setVisibility(View.GONE);
                    bl.rc07.clearCheck();
                    bl.rc0788x.setText(null);
                }
            }
        });
        bl.rc0788.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.rc0788x.setVisibility(View.VISIBLE);
                } else {
                    bl.rc0788x.setVisibility(View.GONE);
                    bl.rc0788x.setText(null);
                }
            }
        });


    }
}
