package edu.aku.hassannaqvi.src_preg.ui.Recruitment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.OnClick;
import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySecRbBinding;

public class SecRBActivity extends Activity {

    private static final String TAG = SecRBActivity.class.getSimpleName();

    ActivitySecRbBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_rb);
    }

    @OnClick(R.id.btn_Continue)
    void SaveData() {
        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        /*DatabaseHelper db = new DatabaseHelper(this);

        long updcount = db.addFamilyMembers(MainApp.fmc);

        MainApp.fmc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fmc.set_UID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFamilyMemberID();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        /*MainApp.fmc = new FamilyMembersContract();
        MainApp.fmc.setFormDate(MainApp.fc.getFormDate());
        MainApp.fmc.setDeviceId(MainApp.fc.getDeviceID());
        MainApp.fmc.setInterviewer1(MainApp.userName);
        MainApp.fmc.setInterviewer2(MainApp.userName2);
        MainApp.fmc.set_UUID(MainApp.fc.get_UID());
        MainApp.fmc.setDevicetagID(sharedPref.getString("tagName", null));

        MainApp.counter++;

        JSONObject sB = new JSONObject();

        sB.put("tehsil_code", String.valueOf(MainApp.tehsilCode));
        sB.put("uc_code", String.valueOf(MainApp.ucCode));
        sB.put("village_code", String.valueOf(MainApp.villageCode));
        sB.put("spblb01Serial", String.valueOf(MainApp.counter));
        sB.put("spblb01", spblb01.getText().toString());
        sB.put("spblb02", spblb02a.isChecked() ? "1" : spblb02b.isChecked() ? "2" : "0");
        sB.put("spblb03", spblb03.getSelectedItem().toString());
        sB.put("spblb03Serial", mothersMap.get(spblb03.getSelectedItem().toString()));
        sB.put("spblb04y", spblb04y.getText().toString());
        sB.put("spblb04m", spblb04m.getText().toString());
        sB.put("spblb04d", spblb04d.getText().toString());
        sB.put("spblb05", spblb05a.isChecked() ? "1" : spblb05b.isChecked() ? "2" : spblb05c.isChecked() ? "3"
                : spblb05d.isChecked() ? "4" : spblb05e.isChecked() ? "5" : spblb05f.isChecked() ? "6" : spblb05g.isChecked() ? "7"
                : spblb0566.isChecked() ? "66" : "0");
        sB.put("spblb06", spblb0677.isChecked() ? "77" : spblb06a.isChecked() ? "1" : spblb06b.isChecked() ? "2" : spblb06c.isChecked() ? "3"
                : spblb06d.isChecked() ? "4" : spblb06e.isChecked() ? "5" : spblb06f.isChecked() ? "6"
                : spblb06g.isChecked() ? "7" : spblb06h.isChecked() ? "8" : spblb06i.isChecked() ? "9"
                : spblb06j.isChecked() ? "10" : "0");
        sB.put("spblb07", spblb07a.isChecked() ? "1" : spblb07b.isChecked() ? "2" : "0");

        MainApp.fmc.setsB(String.valueOf(sB));*/

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }


    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        finish();
    }


    public boolean ValidateForm() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (binding.rb01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb01), Toast.LENGTH_SHORT).show();
            binding.rb01.setError("This data is required");
            binding.rb01.requestFocus();
            Log.i(TAG, "rb01: This Data is Required!");
            return false;
        } else {
            binding.rb01.setError(null);
        }

        if (binding.rb02.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb02), Toast.LENGTH_SHORT).show();
            binding.rb02.setError("This data is required");
            binding.rb02.requestFocus();
            Log.i(TAG, "rb02: This Data is Required!");
            return false;
        } else {
            binding.rb01.setError(null);
        }

        if (binding.rb03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb03), Toast.LENGTH_SHORT).show();
            binding.rb03a.setError("This data is required");
            binding.rb03a.setFocusable(true);
            binding.rb03a.setFocusableInTouchMode(true);
            binding.rb03a.requestFocus();
            Log.i(TAG, "rb03: This Data is Required!");
            return false;
        } else {
            binding.rb03a.setError(null);
        }


        if (binding.rb03n.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb03n), Toast.LENGTH_SHORT).show();
            binding.rb03n.setError("This data is required");
            binding.rb03n.requestFocus();
            Log.i(TAG, "rb03n: This Data is Required!");
            return false;
        } else {
            binding.rb03n.setError(null);
        }


        if (binding.rb04.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb04), Toast.LENGTH_SHORT).show();
            binding.rb04a.setError("This data is required");
            binding.rb04a.setFocusable(true);
            binding.rb04a.setFocusableInTouchMode(true);
            binding.rb04a.requestFocus();
            Log.i(TAG, "rb04a: This Data is Required!");
            return false;
        } else {
            binding.rb04a.setError(null);
        }


        if (binding.rb04n.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb04n), Toast.LENGTH_SHORT).show();
            binding.rb04n.setError("This data is required");
            binding.rb04n.requestFocus();
            Log.i(TAG, "rb04n: This Data is Required!");
            return false;
        } else {
            binding.rb04n.setError(null);
        }


        if (binding.rb05n.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb05n), Toast.LENGTH_SHORT).show();
            binding.rb05n.setError("This data is required");
            binding.rb05n.requestFocus();
            Log.i(TAG, "rb05n: This Data is Required!");
            return false;
        } else {
            binding.rb05n.setError(null);
        }


        if (binding.rb06.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb06), Toast.LENGTH_SHORT).show();
            binding.rb06a.setError("This data is required");
            binding.rb06a.setFocusable(true);
            binding.rb06a.setFocusableInTouchMode(true);
            binding.rb06a.requestFocus();
            Log.i(TAG, "rb06a: This Data is Required!");
            return false;
        } else {
            binding.rb06a.setError(null);
        }


        if (binding.rb0799.isChecked() && binding.rb07m.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb07m), Toast.LENGTH_SHORT).show();
            binding.rb07m.setError("This data is required");
            binding.rb07m.requestFocus();
            Log.i(TAG, "rb07m: This Data is Required!");
            return false;
        } else {
            binding.rb07m.setError(null);
        }





        return true;
    }
}