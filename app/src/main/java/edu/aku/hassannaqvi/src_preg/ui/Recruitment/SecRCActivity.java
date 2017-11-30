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

    ActivitySecRcBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sec_rc);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_rc);
        setupViews();


    }

    public void setupViews() {
        binding.rc01.setManager(getSupportFragmentManager());
        binding.rc03.setManager(getSupportFragmentManager());

        binding.rc04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rc04a.isChecked()) {
                    binding.fldGrprc05.setVisibility(View.VISIBLE);
                } else {
                    binding.fldGrprc05.setVisibility(View.GONE);
                    binding.rc05.clearCheck();
                    binding.rc0588x.setText(null);
                }
            }
        });

        binding.rc0588.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.rc0588x.setVisibility(View.VISIBLE);
                } else {
                    binding.rc0588x.setVisibility(View.GONE);
                    binding.rc0588x.setText(null);
                }
            }
        });


        binding.rc06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rc06a.isChecked()) {
                    binding.fldGrprc07.setVisibility(View.VISIBLE);
                } else {
                    binding.fldGrprc07.setVisibility(View.GONE);
                    binding.rc07.clearCheck();
                    binding.rc0788x.setText(null);
                }
            }
        });
        binding.rc0788.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.rc0788x.setVisibility(View.VISIBLE);
                } else {
                    binding.rc0788x.setVisibility(View.GONE);
                    binding.rc0788x.setText(null);
                }
            }
        });


    }
}
