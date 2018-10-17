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
    @BindView(R.id.height) TextInputEditText height ;
    @BindView(R.id.weight) TextInputEditText weight ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        name.addTextChangedListener(new MyTextWatcher(name));
        height.addTextChangedListener(new MyTextWatcher(height));
        weight.addTextChangedListener(new MyTextWatcher(weight));
    }
    boolean validateText(TextInputEditText editText)
    {
        if (Objects.requireNonNull(editText.getText()).toString().trim().isEmpty()) {
            editText.setError(getString(R.string.empty_field));
            requestFocus(editText);
            return false;
        } else {
            editText.setError("");
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
        private View view ;
        MyTextWatcher(View view)
        {
            this.view = view ;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().trim().isEmpty())
            {
                name.setError(getString(R.string.empty_field));
                return;
            }
            validateText((TextInputEditText) view);
        }
    }
}
