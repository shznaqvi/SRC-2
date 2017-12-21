package edu.aku.hassannaqvi.src_preg.ui.PregnancyFollowUp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import edu.aku.hassannaqvi.src_preg.R;
import edu.aku.hassannaqvi.src_preg.databinding.ActivitySectionPfbBinding;
import edu.aku.hassannaqvi.src_preg.ui.EndingActivity;
import edu.aku.hassannaqvi.src_preg.ui.Recruitment.SecRBActivity;
import edu.aku.hassannaqvi.src_preg.validation.validatorClass;

public class SectionPFBActivity extends Activity {

    private static final String TAG = SecRBActivity.class.getSimpleName();

    ActivitySectionPfbBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_pfb);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_pfb);
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
        /*DatabaseHelper db = new DatabaseHelper(this);


        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSB();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
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

        //JSONObject sB = new JSONObject();


        //MainApp.fc.setsB(String.valueOf(sB));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }


    public void BtnEnd() {
        finish();
        Toast.makeText(this, "complete Section", Toast.LENGTH_SHORT).show();
        Intent endSec = new Intent(this, EndingActivity.class);
        endSec.putExtra("complete", false);
        startActivity(endSec);
    }

    public void setupViews() {

        binding.pfb02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb02a.isChecked()) {
                } else {
                    binding.pfb03.clearCheck();
                    binding.pfb04.clearCheck();
                    binding.pfb05.clearCheck();
                    binding.pfb06.clearCheck();
                }
            }
        });


        binding.pfb10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb10a.isChecked()) {
                } else {
                    binding.pfb11.setText(null);
                    binding.pfb12.clearCheck();
                }
            }
        });


        binding.pfb13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb13a.isChecked()) {
                } else {
                    binding.pfb12.clearCheck();
                    binding.pfb13.clearCheck();
                    binding.pfb14.setText(null);
                    binding.pfb15.clearCheck();
                    binding.pfb16.clearCheck();
                    binding.pfb17.clearCheck();
                    binding.pfb18.clearCheck();
                    binding.pfb19.setText(null);
                }
            }
        });


        binding.pfb20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb20a.isChecked()) {
                } else {
                    binding.pfb21.setText(null);
                }
            }
        });


        binding.pfb22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb22a.isChecked()) {
                } else {
                    binding.pfb23.setText(null);
                    binding.pfb24.clearCheck();
                }
            }
        });


        binding.pfb2788.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.pfb2788.isChecked()) {
                    binding.pfb2788x.setVisibility(View.VISIBLE);
                    binding.pfb2788x.requestFocus();
                } else {
                    binding.pfb2788x.setText(null);
                    binding.pfb2788x.setVisibility(View.GONE);
                }
            }
        });


        binding.pfb2888.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.pfb2888.isChecked()) {
                    binding.pfb2888x.setVisibility(View.VISIBLE);
                    binding.pfb2888x.requestFocus();
                } else {
                    binding.pfb2888x.setText(null);
                    binding.pfb2888x.setVisibility(View.GONE);
                }
            }
        });


        binding.pfb29.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.pfb29a.isChecked()) {
                } else {
                    binding.pfb30.clearCheck();
                    binding.pfb31.clearCheck();
                }
            }
        });


        binding.pfb3088.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.pfb3088.isChecked()) {
                    binding.pfb3088x.setVisibility(View.VISIBLE);
                    binding.pfb3088x.requestFocus();
                } else {
                    binding.pfb3088x.setText(null);
                    binding.pfb3088x.setVisibility(View.GONE);
                }
            }
        });

    }


    private boolean ValidateForm() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(this, binding.pfb01, binding.pfb0199, getString(R.string.pfb01))) {
            return false;
        }


        if (binding.pfb01a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, binding.pfb02, binding.pfb0299, getString(R.string.pfb02))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb03, binding.pfb0377, getString(R.string.pfb03))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb04, binding.pfb0477, getString(R.string.pfb04))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb05, binding.pfb0577, getString(R.string.pfb05))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb06, binding.pfb0677, getString(R.string.pfb06))) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, binding.pfb07, binding.pfb0799, getString(R.string.pfb07))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb08, binding.pfb0877, getString(R.string.pfb08))) {
            return false;
        }


        if (binding.pfb08a.isChecked() && !validatorClass.EmptyRadioButton(this, binding.pfb09, binding.pfb0999, getString(R.string.pfb09))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb10, binding.pfb1099, getString(R.string.pfb10))) {
            return false;
        }

        if (binding.pfb10a.isChecked()) {

            if (!binding.pfb1199.isChecked() && !binding.pfb1177.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfb11, getString(R.string.pfb11))) {
                    return false;
                }
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb12, binding.pfb1277, getString(R.string.pfb12))) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, binding.pfb13, binding.pfb1399, getString(R.string.pfb13))) {
            return false;
        }


        if (binding.pfb13a.isChecked()) {

            if (!binding.pfb1499.isChecked() && !binding.pfb1477.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfb14, getString(R.string.pfb14))) {
                    return false;
                }
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb15, binding.pfb1577, getString(R.string.pfb15))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb16, binding.pfb1677, getString(R.string.pfb16))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb17, binding.pfb1777, getString(R.string.pfb17))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb18, binding.pfb1877, getString(R.string.pfb18))) {
                return false;
            }


            if (!validatorClass.EmptyTextBox(this, binding.pfb19, getString(R.string.pfb19))) {
                return false;
            }

        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb20, binding.pfb2099, getString(R.string.pfb20))) {
            return false;
        }


        if (binding.pfb20a.isChecked()) {
            if (!binding.pfb2199.isChecked() && !binding.pfb2177.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfb21, getString(R.string.pfb21))) {
                    return false;
                }
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb22, binding.pfb2299, getString(R.string.pfb22))) {
            return false;
        }


        if (binding.pfb22a.isChecked()) {

            if (!binding.pfb2399.isChecked() && !binding.pfb2377.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfb23, getString(R.string.pfb23))) {
                    return false;
                }
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb24, binding.pfb2477, getString(R.string.pfb24))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb25, binding.pfb2599, getString(R.string.pfb25))) {
            return false;
        }


        if (!validatorClass.EmptyCheckBox(this, binding.pfb26, binding.pfb26a, getString(R.string.pfb26))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb27, binding.pfb2788, getString(R.string.pfb27))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb28, binding.pfb2877, getString(R.string.pfb28))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb29, binding.pfb2999, getString(R.string.pfb29))) {
            return false;
        }


        if (binding.pfb29a.isChecked()) {

            if (!validatorClass.EmptyRadioButton(this, binding.pfb30, binding.pfb3077, getString(R.string.pfb30))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb31, binding.pfb3177, getString(R.string.pfb31))) {
                return false;
            }


            if (!validatorClass.EmptyRadioButton(this, binding.pfb32, binding.pfb3299, getString(R.string.pfb32))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb33, binding.pfb3377, getString(R.string.pfb33))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.pfb34, getString(R.string.pfb34))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb35, binding.pfb3588, getString(R.string.pfb35))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb36, binding.pfb3688, getString(R.string.pfb36))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb37, binding.pfb3788, getString(R.string.pfb37))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb38, binding.pfb3888, getString(R.string.pfb38))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb39, binding.pfb3988, getString(R.string.pfb39))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.pfb40, binding.pfb4088, getString(R.string.pfb40))) {
            return false;
        }


        return !validatorClass.EmptyRadioButton(this, binding.pfb41, binding.pfb4188, getString(R.string.pfb41));

    }
}