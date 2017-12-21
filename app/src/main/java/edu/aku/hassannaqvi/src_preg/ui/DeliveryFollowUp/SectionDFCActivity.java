package edu.aku.hassannaqvi.src_preg.ui.DeliveryFollowUp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.core.MainApp;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySectionDfcBinding;

public class SectionDFCActivity extends Activity
{
    ActivitySectionDfcBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_section_dfc);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_section_dfc);
        //setupViews();
        bl.setCallback(this);
    }

    public void BtnEnd() {
        finish();
        Toast.makeText(this, "complete Section", Toast.LENGTH_SHORT).show();
        MainApp.endActivity(this,this);
    }

    public void BtnContinue() {

    }
}
