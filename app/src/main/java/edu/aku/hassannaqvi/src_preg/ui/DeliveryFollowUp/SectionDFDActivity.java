package edu.aku.hassannaqvi.src_preg.ui.DeliveryFollowUp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySectionDfdBinding;

public class SectionDFDActivity extends Activity {

    ActivitySectionDfdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_section_dfd);
    }
}