package com.magdy.drweather.UI;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.magdy.drweather.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDataActivity extends AppCompatActivity {

    @BindView(R.id.name) TextInputEditText name;
    @BindView(R.id.height) TextInputEditText height ;
    @BindView(R.id.weight) TextInputEditText weight ;
    @BindView(R.id.h_press) TextInputEditText h_press ;
    @BindView(R.id.l_press) TextInputEditText l_press ;
    @BindView(R.id.heart_rate) TextInputEditText heart_rate ;
    @BindView(R.id.gender) AppCompatSpinner genderSpinner;
    @BindView(R.id.preg) AppCompatSpinner pregSpinner;
    @BindView(R.id.blood_type) AppCompatSpinner bloodTypeSpinner;
    @BindView(R.id.body_temp) TextInputEditText body_temp;
    @BindView(R.id.s_level) TextInputEditText sugar_level;
    @BindView(R.id.choose_day) Button chooseDay;
    @BindView(R.id.date) TextView dateText;
    ArrayAdapter<String> bloodAdapter,genderAdapter , pregAdapter;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        name.addTextChangedListener(new MyTextWatcher(name));
        height.addTextChangedListener(new MyTextWatcher(height));
        weight.addTextChangedListener(new MyTextWatcher(weight));
        h_press.addTextChangedListener(new MyTextWatcher(h_press));
        l_press.addTextChangedListener(new MyTextWatcher(l_press));
        sugar_level.addTextChangedListener(new MyTextWatcher(sugar_level));
        genderAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.gender)));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pregSpinner.setSelection(0);
                if (i>0)
                {
                    pregSpinner.setVisibility(View.VISIBLE);
                }
                else
                {
                    pregSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bloodAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.blood)));
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(bloodAdapter);
        pregAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.yes_no)));
        pregAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pregSpinner.setAdapter(pregAdapter);
        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener myDateListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        // arg1 = year
                        // arg2 = month
                        // arg3 = day
                        calendar.setTimeInMillis(0);
                        calendar.set(arg1,arg2,arg3);
                        showDate(arg1, arg2+1, arg3);
                    }
                };


        chooseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getBaseContext(),
                        myDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH)+1,
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

    }

    void showDate(int y , int m, int d)
    {
        dateText.setText(String.format(Locale.getDefault(),"%d/%d/%d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH)));
    }
    boolean validateText(TextInputEditText editText)
    {
        if (Objects.requireNonNull(editText.getText()).toString().trim().isEmpty()) {
            editText.setError(getString(R.string.empty_field));
            requestFocus(editText);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher
    {
        private TextInputEditText editText ;
        MyTextWatcher(TextInputEditText editText)
        {
            this.editText = editText ;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            validateText(editText);
        }
    }
}
