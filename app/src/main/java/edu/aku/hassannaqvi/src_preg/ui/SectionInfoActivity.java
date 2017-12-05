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
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_info);

//        Get data from Main Activity
        check = getIntent().getExtras().getInt("check");
//        Assigning data to UI binding
        checking ch = new checking(check);
        binding.setCheckFlag(ch);
        binding.setCallback(this);

//        Main Working from here

//        Skip Patterns

    }

    public Boolean formValidation() {

//        HH No
        if (!validatorClass.EmptyTextBox(this, binding.hhno, getString(R.string.hhno))) {
            return false;
        }

//       W.NAME
        if (!validatorClass.EmptyTextBox(this, binding.name, getString(R.string.wname))) {
            return false;
        }

//        W.AGE
        if (check != 2) {
            if (!validatorClass.EmptyTextBox(this, binding.age, getString(R.string.age))) {
                return false;
            }
        }

//       H.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hname, getString(R.string.hname))) {
            return false;
        }

//       HH.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hhname, getString(R.string.hhname))) {
            return false;
        }

//        Child.Name
        if (check == 1) {
            if (!validatorClass.EmptyTextBox(this, binding.dfa06, getString(R.string.dfa06))) {
                return false;
            }
        }

//        UC
        if (!validatorClass.EmptySpinner(this, binding.spUCs, "UCs")) {
            return false;
        }

//        Villages
        if (!validatorClass.EmptySpinner(this, binding.spVillages, "Villages")) {
            return false;
        }

//        DA 07
        if (check == 2) {
            if (!validatorClass.EmptyTextBox(this, binding.da07, getString(R.string.da07))) {
                return false;
            }
        }

//       LHV.NAME
        if (!validatorClass.EmptyTextBox(this, binding.lhvname, getString(R.string.lhvname))) {
            return false;
        }

//        DFA 10
        if (check == 1) {
            if (!validatorClass.EmptyTextBox(this, binding.dfa10, getString(R.string.dfa10))) {
                return false;
            }

//            dfa 11
            if (!validatorClass.EmptySpinner(this, binding.dfa11, getString(R.string.dfa11))) {
                return false;
            }
        }

//        PFA 10
        if (check == 3) {
            if (!validatorClass.EmptySpinner(this, binding.pfa10, getString(R.string.pfa10))) {
                return false;
            }

//            pfa 11
            if (!validatorClass.EmptySpinner(this, binding.pfa11, getString(R.string.pfa11))) {
                return false;
            }
        }

//        RA 10
        if (check == 4) {
            if (!validatorClass.EmptySpinner(this, binding.ra10, getString(R.string.ra10))) {
                return false;
            }
        }

//       STATUS
        if (!validatorClass.EmptyRadioButtonWithOther(this, binding.istatus, binding.istatus88, binding.istatus88x, getString(R.string.istatus))) {
            return false;
        }

//        DFA 15
        if (check == 1) {
            if (!validatorClass.EmptyRadioButton(this, binding.dfa15a, binding.dfa15888, getString(R.string.dfa15))) {
                if (!validatorClass.EmptyTextBox(this, binding.dfa15, getString(R.string.dfa15))) {
                    binding.dfa15888.setError(null);
                    return false;
                }
            }
        }

//        PFA 14
        if (check == 3) {
            if (!validatorClass.EmptyRadioButton(this, binding.pfa14a, binding.pfa14888, getString(R.string.pfa14))) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa14, getString(R.string.pfa14))) {
                    binding.pfa14888.setError(null);
                    return false;
                }
            }

//            pfa 15
            if (!validatorClass.EmptyRadioButton(this, binding.pfa15, binding.pfa15c, getString(R.string.pfa15))) {
                return false;
            }
//            pfa 16
            if (!validatorClass.EmptyRadioButton(this, binding.pfa16, binding.pfa16g, getString(R.string.pfa16))) {
                return false;
            }
//            pfa 17
            if (!validatorClass.EmptyRadioButton(this, binding.pfa17a, binding.pfa17888, getString(R.string.pfa17))) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa17, getString(R.string.pfa17))) {
                    binding.pfa17888.setError(null);
                    return false;
                }
            }
//            pfa 18
            if (!validatorClass.EmptyTextBox(this, binding.pfa18, getString(R.string.pfa18))) {
                return false;
            }
//            pfa 19
            if (!binding.pfa19888.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa19, getString(R.string.pfa19))) {
                    return false;
                }
            }
        }

        return true;
    }

    public void BtnContinue() {

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
