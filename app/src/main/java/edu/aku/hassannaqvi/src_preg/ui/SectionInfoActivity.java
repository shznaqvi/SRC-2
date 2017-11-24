package edu.aku.hassannaqvi.src_preg.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import edu.aku.hassannaqvi.src_preg.R;

public class SectionInfoActivity extends Activity {

    SectionInfoActivity binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_info);

//        binding = DataBindingUtil.setContentView(this,R.layout.activity_section_info);
    }
}
