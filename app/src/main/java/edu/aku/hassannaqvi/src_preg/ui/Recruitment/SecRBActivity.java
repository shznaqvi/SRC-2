package edu.aku.hassannaqvi.src_preg.ui.Recruitment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.core.DatabaseHelper;
import edu.aku.hassannaqvi.src_preg.core.MainApp;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySecRbBinding;
import edu.aku.hassannaqvi.src_preg.validation.validatorClass;

public class SecRBActivity extends Activity {

    private static final String TAG = SecRBActivity.class.getSimpleName();

    ActivitySecRbBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sec_rb);
        setupViews();
        binding.setCallback(this);
    }


    public void BtnContinue() {
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

        //Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);


        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSB();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        JSONObject sB = new JSONObject();

        sB.put("rb01", binding.rb01.getText().toString());
        sB.put("rb03", binding.rb03a.isChecked() ? "1" : binding.rb03b.isChecked() ? "2" : "0");

        sB.put("rb03n", binding.rb03n.getText().toString());
        sB.put("rb04", binding.rb04a.isChecked() ? "1" : binding.rb04b.isChecked() ? "2"
                : "0");

        sB.put("rb04n", binding.rb04n.getText().toString());
        sB.put("rb05n", binding.rb05n.getText().toString());
        sB.put("rb06", binding.rb06a.isChecked() ? "1" : binding.rb06b.isChecked() ? "2"
                : "0");

        if (binding.rb0799.isChecked()) {
            sB.put("rb07m", "");
            sB.put("rb0799", binding.rb0799.isChecked() ? "99" : "0");
        } else {
            sB.put("rb07m", binding.rb07m.getText().toString());
        }


        if (binding.rb0899.isChecked()) {
            sB.put("rb08n", "");
            sB.put("rb0899", binding.rb0899.isChecked() ? "99" : "0");
        } else {
            sB.put("rb08n", binding.rb08n.getText().toString());
        }


        sB.put("rb09vd", binding.rb09vda.isChecked() ? "1" : binding.rb09vdb.isChecked() ? "2" : "0");
        sB.put("rb09i", binding.rb09ia.isChecked() ? "1" : binding.rb09ib.isChecked() ? "2" : "0");
        sB.put("rb09fa", binding.rb09faa.isChecked() ? "1" : binding.rb09fab.isChecked() ? "2" : "0");
        sB.put("rb09m", binding.rb09ma.isChecked() ? "1" : binding.rb09mb.isChecked() ? "2" : "0");
        sB.put("rb09c", binding.rb09ca.isChecked() ? "1" : binding.rb09cb.isChecked() ? "2" : "0");
        sB.put("rb0988", binding.rb0988a.isChecked() ? "1" : binding.rb0988b.isChecked() ? "2" : "0");

        sB.put("rb0988x", binding.rb0988x.getText().toString());


        sB.put("rb10", binding.rb10a.isChecked() ? "1" : binding.rb10b.isChecked() ? "2"
                : binding.rb1099.isChecked() ? "99"
                : "0");


        sB.put("rb11", binding.rb11a.isChecked() ? "1" : binding.rb11b.isChecked() ? "2"
                : binding.rb11c.isChecked() ? "3"
                : binding.rb11d.isChecked() ? "4"
                : binding.rb11e.isChecked() ? "5"
                : binding.rb1199.isChecked() ? "99"
                : "0");


        sB.put("rb12", binding.rb12a.isChecked() ? "1" : binding.rb12b.isChecked() ? "2"
                : binding.rb12c.isChecked() ? "3"
                : binding.rb12d.isChecked() ? "4"
                : binding.rb12e.isChecked() ? "5"
                : binding.rb12f.isChecked() ? "6"
                : "0");


        sB.put("rb13", binding.rb13a.isChecked() ? "1" : binding.rb13b.isChecked() ? "2"
                : binding.rb13c.isChecked() ? "3"
                : binding.rb13d.isChecked() ? "4"
                : binding.rb1388.isChecked() ? "88"
                : "0");


        sB.put("rb1388x", binding.rb1388x.getText().toString());


        sB.put("rb14", binding.rb14a.isChecked() ? "1" : binding.rb14b.isChecked() ? "2"
                : binding.rb14c.isChecked() ? "3"
                : binding.rb14d.isChecked() ? "4"
                : binding.rb1488.isChecked() ? "88"
                : "0");


        sB.put("rb1488x", binding.rb1488x.getText().toString());


        sB.put("rb15", binding.rb15a.isChecked() ? "1" : binding.rb15b.isChecked() ? "2"
                : binding.rb15c.isChecked() ? "3"
                : binding.rb1588.isChecked() ? "88"
                : "0");


        sB.put("rb1588x", binding.rb1588x.getText().toString());

        if (binding.rb1699.isChecked()) {
            sB.put("rb16", "");
            sB.put("rb1699", binding.rb1699.isChecked() ? "99" : "0");
        } else {
            sB.put("rb16", binding.rb16.getText().toString());
        }


        sB.put("rb17", binding.rb17a.isChecked() ? "1" : binding.rb17b.isChecked() ? "2"
                : "0");


        sB.put("rb18a", binding.rb18a.isChecked() ? "1" : "0");
        sB.put("rb18b", binding.rb18b.isChecked() ? "2" : "0");
        sB.put("rb18c", binding.rb18c.isChecked() ? "3" : "0");
        sB.put("rb18d", binding.rb18d.isChecked() ? "4" : "0");
        sB.put("rb1888", binding.rb1888.isChecked() ? "88" : "0");


        sB.put("rb1888", binding.rb1888.getText().toString());

        sB.put("rb19", binding.rb19a.isChecked() ? "1" : binding.rb19b.isChecked() ? "2"
                : "0");


        MainApp.fc.setsB(String.valueOf(sB));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }

/*
    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        finish();
    }*/


    private boolean ValidateForm() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.rb01, getString(R.string.rb01))) {
            return false;
        }


        if (Integer.parseInt(binding.rb01.toString()) > MainApp.womanage) {
            Toast.makeText(this, "Age at 1st pregnancy cannot be greater", Toast.LENGTH_SHORT).show();
            binding.rb01.requestFocus();
            return false;
        }


        if (!validatorClass.RangeTextBox(this, binding.rb01, 15, 50, getString(R.string.day), "days")) {
            return false;
        }


        /*if (!validatorClass.EmptyTextBox(this, binding.rb02, getString(R.string.rb02))) {
            return false;
        }*/


        if (!validatorClass.EmptyRadioButton(this, binding.rb03, binding.rb03b, getString(R.string.rb03))) {
            return false;
        }

        if (binding.rb03a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, binding.rb03n, getString(R.string.rb03n))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb04, binding.rb04b, getString(R.string.rb04))) {
            return false;
        }


        if (binding.rb04a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, binding.rb04n, getString(R.string.rb04n))) {
                return false;
            }
        }


        if (!validatorClass.EmptyTextBox(this, binding.rb05n, getString(R.string.rb05n))) {
            return false;
        }


        if (!validatorClass.RangeTextBox(this, binding.rb05n, 1, 12, getString(R.string.rb05n), "live birth")) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb06, binding.rb06b, getString(R.string.rb06))) {
            return false;
        }

        if (binding.rb06a.isChecked()) {
            if (!binding.rb0799.isChecked() && !validatorClass.EmptyTextBox(this, binding.rb07m, getString(R.string.rb07m))) {
                return false;
            }


            if (!binding.rb0799.isChecked() && !validatorClass.RangeTextBox(this, binding.rb07m, 0, 9, getString(R.string.rb07m), "months")) {
                return false;
            }


            if (!binding.rb0899.isChecked() && !validatorClass.EmptyTextBox(this, binding.rb08n, getString(R.string.rb08n))) {
                return false;
            }


            if (!binding.rb0899.isChecked() && !validatorClass.RangeTextBox(this, binding.rb08n, 1, 9, getString(R.string.rb08n), "number of time")) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb09vd, binding.rb09vdb, getString(R.string.rb09vd))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb09i, binding.rb09ib, getString(R.string.rb09i))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb09fa, binding.rb09fab, getString(R.string.rb09fa))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb09m, binding.rb09mb, getString(R.string.rb09m))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb09c, binding.rb09cb, getString(R.string.rb09c))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.rb0988, binding.rb0988a, getString(R.string.other))) {
                return false;
            }

            if (binding.rb0988a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.rb0988x, getString(R.string.other))) {
                    return false;
                }
            }
        }

        if (!validatorClass.EmptyRadioButton(this, binding.rb10, binding.rb1099, getString(R.string.rb10))) {
            return false;
        }

        if (binding.rb10a.isChecked()) {

            if (!validatorClass.EmptyRadioButton(this, binding.rb11, binding.rb1199, getString(R.string.rb11))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, binding.rb12, binding.rb12f, getString(R.string.rb12))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb13, binding.rb1388, binding.rb1388x, getString(R.string.rb13))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb14, binding.rb1488, binding.rb1488x, getString(R.string.rb14))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb15, binding.rb1588, binding.rb1588x, getString(R.string.rb15))) {
            return false;
        }


        if (!binding.rb1699.isChecked() && !validatorClass.EmptyTextBox(this, binding.rb16, getString(R.string.rb16))) {
            return false;
        }


        if (!binding.rb1699.isChecked() && !validatorClass.RangeTextBox(this, binding.rb16, 1.00, 7.00, getString(R.string.rb16), "KG (weight)")) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.rb17, binding.rb17b, getString(R.string.rb17))) {
            return false;
        }

        if (binding.rb17a.isChecked()) {
            if (!validatorClass.EmptyCheckBox(this, binding.fldGrprb18, binding.rb1888, binding.rb1888x, getString(R.string.other))) {
                return false;
            }
        }


        return validatorClass.EmptyRadioButton(this, binding.rb19, binding.rb19b, getString(R.string.rb19));
    }


    public void setupViews() {

        binding.rb03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb03b.isChecked()) {
                    binding.fldGrprb03n.setVisibility(View.GONE);
                    binding.rb03n.setText(null);
                } else {
                    binding.fldGrprb03n.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb04b.isChecked()) {
                    binding.fldGrprb04n.setVisibility(View.GONE);
                    binding.rb04n.setText(null);
                } else {
                    binding.fldGrprb04n.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb06b.isChecked()) {

                    binding.rb07m.setText(null);
                    binding.rb0799.setChecked(false);
                    binding.rb08n.setText(null);
                    binding.rb0899.setChecked(false);
                    binding.rb09vd.clearCheck();
                    binding.rb09i.clearCheck();
                    binding.rb09fa.clearCheck();
                    binding.rb09m.clearCheck();
                    binding.rb09c.clearCheck();
                    binding.rb0988.clearCheck();
                    binding.rb0988x.setText(null);

                    binding.fldGrprb07m.setVisibility(View.GONE);
                } else {
                    binding.fldGrprb07m.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb0799.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb0799.isChecked()) {
                    binding.rb07m.setText(null);
                    binding.rb07m.setVisibility(View.GONE);
                } else {
                    binding.rb07m.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb0899.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb0899.isChecked()) {
                    binding.rb08n.setText(null);
                    binding.rb08n.setVisibility(View.GONE);
                } else {
                    binding.rb08n.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb0988.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb0988a.isChecked()) {
                    binding.rb0988x.setVisibility(View.VISIBLE);
                } else {
                    binding.rb0988x.setText(null);
                    binding.rb0988x.setVisibility(View.GONE);
                }
            }
        });

        binding.rb10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb10a.isChecked()) {
                    binding.fldGrprb11.setVisibility(View.VISIBLE);
                } else {
                    binding.rb11.clearCheck();
                    binding.rb12.clearCheck();
                    binding.fldGrprb11.setVisibility(View.GONE);
                }
            }
        });


        binding.rb1388.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb1388.isChecked()) {
                    binding.rb1388x.setVisibility(View.VISIBLE);
                } else {
                    binding.rb1388x.setText(null);
                    binding.rb1388x.setVisibility(View.GONE);
                }
            }
        });


        binding.rb1488.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb1488.isChecked()) {
                    binding.rb1488x.setVisibility(View.VISIBLE);
                } else {
                    binding.rb1488x.setText(null);
                    binding.rb1488x.setVisibility(View.GONE);
                }
            }
        });


        binding.rb1588.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb1588.isChecked()) {
                    binding.rb1588x.setVisibility(View.VISIBLE);
                } else {
                    binding.rb1588x.setText(null);
                    binding.rb1588x.setVisibility(View.GONE);
                }
            }
        });


        binding.rb1699.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb1699.isChecked()) {
                    binding.rb16.setText(null);
                    binding.rb16.setVisibility(View.GONE);
                } else {
                    binding.rb16.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.rb17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.rb17a.isChecked()) {
                    binding.fldGrprb18.setVisibility(View.VISIBLE);
                } else {
                    binding.rb18a.setChecked(false);
                    binding.rb18b.setChecked(false);
                    binding.rb18c.setChecked(false);
                    binding.rb18d.setChecked(false);
                    binding.rb1888.setChecked(false);

                    binding.fldGrprb18.setVisibility(View.GONE);
                }
            }
        });


        binding.rb1888.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.rb1888.isChecked()) {
                    binding.rb1888x.setVisibility(View.VISIBLE);
                } else {
                    binding.rb1888x.setText(null);
                    binding.rb1888x.setVisibility(View.GONE);
                }
            }
        });


    }


    /*public boolean ValidateForm() {

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


        if (binding.rb09vda.isChecked()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.rb07m), Toast.LENGTH_SHORT).show();
            binding.rb07m.setError("This data is required");
            binding.rb07m.requestFocus();
            Log.i(TAG, "rb07m: This Data is Required!");
            return false;
        } else {
            binding.rb07m.setError(null);
        }

        return true;
    }*/
}