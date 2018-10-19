package com.magdy.drweather.UI;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.magdy.drweather.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDataActivity extends AppCompatActivity {

    @BindView(R.id.name) TextInputEditText name;
    @BindView(R.id.age) TextInputEditText age;
    @BindView(R.id.height) TextInputEditText height ;
    @BindView(R.id.weight) TextInputEditText weight ;
    @BindView(R.id.h_press) TextInputEditText h_press ;
    @BindView(R.id.l_press) TextInputEditText l_press ;
    @BindView(R.id.s_level) TextInputEditText sugar_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        name.addTextChangedListener(new MyTextWatcher(name));
        age.addTextChangedListener(new MyTextWatcher(age));
        height.addTextChangedListener(new MyTextWatcher(height));
        weight.addTextChangedListener(new MyTextWatcher(weight));
        h_press.addTextChangedListener(new MyTextWatcher(h_press));
        l_press.addTextChangedListener(new MyTextWatcher(l_press));
        sugar_level.addTextChangedListener(new MyTextWatcher(sugar_level));
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
