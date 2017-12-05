package edu.aku.hassannaqvi.src_preg.ui.Recruitment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.contracts.FormsContract;
import edu.aku.hassannaqvi.src_preg.core.MainApp;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySecRcBinding;
import edu.aku.hassannaqvi.src_preg.ui.EndingActivity;
import edu.aku.hassannaqvi.src_preg.validation.validatorClass;

public class SecRCActivity extends AppCompatActivity
{

    ActivitySecRcBinding bl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sec_rc);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_sec_rc);
        setupViews();
        bl.setCallback(this);


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

                if (b) {
                    bl.rc10a.setEnabled(false);
                    bl.rc10b.setEnabled(false);
                    bl.rc10c.setEnabled(false);
                    bl.rc10d.setEnabled(false);
                    bl.rc10e.setEnabled(false);
                    bl.rc10f.setEnabled(false);
                    bl.rc10g.setEnabled(false);
                    bl.rc10a.setChecked(false);
                    bl.rc10b.setChecked(false);
                    bl.rc10c.setChecked(false);
                    bl.rc10d.setChecked(false);
                    bl.rc10e.setChecked(false);
                    bl.rc10f.setChecked(false);
                    bl.rc10g.setChecked(false);


                } else {
                    bl.rc10a.setEnabled(true);
                    bl.rc10b.setEnabled(true);
                    bl.rc10c.setEnabled(true);
                    bl.rc10d.setEnabled(true);
                    bl.rc10e.setEnabled(true);
                    bl.rc10f.setEnabled(true);
                    bl.rc10g.setEnabled(true);
                }

            }
        });

        bl.rc11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc11a.isChecked()) {
                    bl.fldGrprc12.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc12.setVisibility(View.GONE);
                    bl.rc12a.setChecked(false);
                    bl.rc12b.setChecked(false);
                    bl.rc12c.setChecked(false);
                    bl.rc12d.setChecked(false);
                    bl.rc12e.setChecked(false);
                    bl.rc12f.setChecked(false);
                    bl.rc1288.setChecked(false);
                    bl.rc1288x.setText(null);
                    bl.rc1299.setChecked(false);


                }
            }
        });

    }


    public Boolean formValidation() {

//        3.1
        if (!validatorClass.EmptyTextBox(this, bl.rc01, getString(R.string.rc01))) {
            return false;
        }

//       3.2 Weeks
        if (!validatorClass.EmptyTextBox(this, bl.rc02w, getString(R.string.rc02w))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bl.rc02w, 3, 40, getString(R.string.rc02w), " weeks")) {
            return false;
        }

//       3.2 Days
        if (!validatorClass.EmptyTextBox(this, bl.rc02d, getString(R.string.rc02d))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bl.rc02d, 0, 6, getString(R.string.rc02d), " days")) {
            return false;
        }

        // 3.3
        if (!validatorClass.EmptyTextBox(this, bl.rc03, getString(R.string.rc03))) {
            return false;
        }

//       3.4
        if (!validatorClass.EmptyRadioButton(this, bl.rc04, bl.rc04a, getString(R.string.rc04))) {
            return false;
        }

//        3.5
        if (bl.rc04a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.rc05, bl.rc05a, getString(R.string.rc05))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButtonWithOther(this, bl.rc05, bl.rc0588, bl.rc0588x, getString(R.string.rc05))) {
                return false;
            }
        }
//      3.6
        if (!validatorClass.EmptyRadioButton(this, bl.rc06, bl.rc06a, getString(R.string.rc06))) {
            return false;
        }


        //      3.7

        if (bl.rc06a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.rc07, bl.rc07a, getString(R.string.rc07))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButtonWithOther(this, bl.rc07, bl.rc0788, bl.rc0788x, getString(R.string.rc07))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc08, bl.rc08a, getString(R.string.rc08))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09w, bl.rc09wa, getString(R.string.rc09w))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09bp, bl.rc09bpa, getString(R.string.rc09bp))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09st, bl.rc09sta, getString(R.string.rc09st))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09ud, bl.rc09uda, getString(R.string.rc09ud))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09hb, bl.rc09hba, getString(R.string.rc09hb))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09us, bl.rc09usa, getString(R.string.rc09us))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09ti, bl.rc09tia, getString(R.string.rc09ti))) {
                return false;
            }

        }

        // 3.10


        // 3.11
        if (!validatorClass.EmptyRadioButton(this, bl.rc11, bl.rc11a, getString(R.string.rc11))) {
            return false;
        }

        // 3.12
        if (bl.rc11a.isChecked()) {

        }

        // 3.13

        if (!validatorClass.EmptyRadioButton(this, bl.rc13, bl.rc13a, getString(R.string.rc13))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButtonWithOther(this, bl.rc13, bl.rc1388, bl.rc1388x, getString(R.string.rc13))) {
            return false;
        }

        // 3.14
        if (!validatorClass.EmptyRadioButton(this, bl.rc14c, bl.rc14ca, getString(R.string.rc14c))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14fb, bl.rc14fba, getString(R.string.rc14fb))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14la, bl.rc14laa, getString(R.string.rc14la))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14nv, bl.rc14nva, getString(R.string.rc14nv))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, bl.rc14cf, bl.rc14cfa, getString(R.string.rc14cf))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, bl.rc14hs, bl.rc14hsa, getString(R.string.rc14hs))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14fs, bl.rc14fsa, getString(R.string.rc14fs))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14sh, bl.rc14sha, getString(R.string.rc14sh))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14fv, bl.rc14fva, getString(R.string.rc14fv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14plb, bl.rc14plba, getString(R.string.rc14plb))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14bm, bl.rc14bma, getString(R.string.rc14bm))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14bv, bl.rc14bva, getString(R.string.rc14bv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14ddp, bl.rc14ddpa, getString(R.string.rc14ddp))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14gh, bl.rc14gha, getString(R.string.rc14gh))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc15, bl.rc15a, getString(R.string.rc15))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc16, bl.rc16a, getString(R.string.rc16))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17dpd, bl.rc17dpda, getString(R.string.rc17dpd))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc17dn, bl.rc17dna, getString(R.string.rc17dn))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17fs, bl.rc17fsa, getString(R.string.rc17fs))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17fp, bl.rc17fpa, getString(R.string.rc17fp))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc17b, bl.rc17ba, getString(R.string.rc17b))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ebf, bl.rc17ebfa, getString(R.string.rc17ebf))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17av, bl.rc17ava, getString(R.string.rc17av))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ahv, bl.rc17ahva, getString(R.string.rc17ahv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ar, bl.rc17ara, getString(R.string.rc17ar))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc1788, bl.rc1788a, getString(R.string.other))) {
            return false;
        }

        return validatorClass.EmptyRadioButtonWithOther(this, bl.rc1788, bl.rc1788a, bl.rc1788x, getString(R.string.rc17 + R.string.other));
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

    public class checking
    {
        int check;

        public checking(int check) {
            this.check = check;
        }

        public int getCheck() {
            return check;
        }
    }



}
