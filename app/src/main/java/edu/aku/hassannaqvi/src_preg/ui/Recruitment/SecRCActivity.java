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
                    bl.rc08.clearCheck();
                    bl.rc09w.clearCheck();
                    bl.rc09bp.clearCheck();
                    bl.rc09st.clearCheck();
                    bl.rc09ud.clearCheck();
                    bl.rc09hb.clearCheck();
                    bl.rc09us.clearCheck();
                    bl.rc09ti.clearCheck();
                }
            }
        });

        bl.rc10h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });


    }
}
