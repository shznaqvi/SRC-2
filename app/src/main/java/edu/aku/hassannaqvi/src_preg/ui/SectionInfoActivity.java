package edu.aku.hassannaqvi.src_preg.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.contracts.FormsContract;
import edu.aku.hassannaqvi.src_preg.core.MainApp;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySectionInfoBinding;
import edu.aku.hassannaqvi.src_preg.validation.validatorClass;

public class SectionInfoActivity extends Activity {

    ActivitySectionInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_info);

//        Get data from Main Activity
        int check = getIntent().getExtras().getInt("check");
//        Assigning data to UI binding
        checking ch = new checking(check);
        binding.setCheckFlag(ch);
        binding.setCallback(this);

//        Main Working from here

//        Skip Patterns

    }

    public Boolean formValidation() {

//        HH No
        if (!validatorClass.EmptyTextBox(this, binding.hhno, getString(R.string.hhno))){
            return false;
        }

//       W.NAME
        if (!validatorClass.EmptyTextBox(this, binding.name, getString(R.string.wname))){
            return false;
        }

//       H.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hname, getString(R.string.hname))){
            return false;
        }

//       HH.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hhname, getString(R.string.hhname))){
            return false;
        }

//        UC
        if (!validatorClass.EmptySpinner(this, binding.spUCs, "UCs")){
            return false;
        }

//        Villages
        if (!validatorClass.EmptySpinner(this, binding.spVillages, "Villages")){
            return false;
        }

//       LHV.NAME
        if (!validatorClass.EmptyTextBox(this, binding.lhvname, getString(R.string.lhvname))){
            return false;
        }

//       STATUS
        if (!validatorClass.EmptyRadioButtonWithOther(this, binding.istatus, binding.istatus88, binding.istatus88x, getString(R.string.istatus))){
            return false;
        }

        return true;
    }

    public void BtnContinue(){

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();

//        setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        /*Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }

    public class checking {
        int check;

        public checking(int check) {
            this.check = check;
        }

        public int getCheck() {
            return check;
        }
    }

}
