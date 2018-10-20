package com.magdy.drweather.UI;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.magdy.drweather.BuildConfig;
import com.magdy.drweather.Data.CaseData;
import com.magdy.drweather.Data.PatentData;
import com.magdy.drweather.Data.WeatherData;
import com.magdy.drweather.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDataActivity extends AppCompatActivity {

    final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    final String AQI_BASE_URL = "https://api.waqi.info/feed/geo:";
    @BindView(R.id.name) TextInputEditText name;
    @BindView(R.id.height) TextInputEditText height ;
    @BindView(R.id.weight) TextInputEditText weight ;
    @BindView(R.id.h_press) TextInputEditText h_press ;
    @BindView(R.id.l_press) TextInputEditText l_press ;
    @BindView(R.id.heart_rate) TextInputEditText heart_rate ; //0
    @BindView(R.id.gender) AppCompatSpinner genderSpinner;
    @BindView(R.id.preg) AppCompatSpinner pregSpinner;
    @BindView(R.id.diabetic) AppCompatSpinner diabetSpinner;
    @BindView(R.id.asthmatic) AppCompatSpinner asthmaticSpinner;
    @BindView(R.id.blood_type) AppCompatSpinner bloodTypeSpinner;
    @BindView(R.id.daily_ex) AppCompatSpinner dailyExSpinner;
    @BindView(R.id.body_temp) TextInputEditText body_temp;
    @BindView(R.id.choose_day) Button chooseDay;
    @BindView(R.id.pick) Button pick;
    @BindView(R.id.submit_data) Button submit;
    @BindView(R.id.date) TextView dateText;
    @BindView(R.id.toolbar) Toolbar toolbar ;
    @BindView(R.id.preg_text) TextView pregText;
    @BindView(R.id.progress)
    FrameLayout progress;
    ArrayAdapter<String> bloodAdapter,genderAdapter , pregAdapter, diabetAdapter , asthmaticAdapter , dailyExAdapter;
    Calendar calendar;
    Place place ;
    WeatherData weatherData ;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        queue = Volley.newRequestQueue(this);
        progress.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.add_data);
        }
        name.addTextChangedListener(new MyTextWatcher(name));
        height.addTextChangedListener(new MyTextWatcher(height));
        weight.addTextChangedListener(new MyTextWatcher(weight));
        h_press.addTextChangedListener(new MyTextWatcher(h_press));
        l_press.addTextChangedListener(new MyTextWatcher(l_press));
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
                    pregText.setVisibility(View.VISIBLE);
                }
                else
                {
                    pregSpinner.setVisibility(View.GONE);
                    pregText.setVisibility(View.GONE);
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
        diabetAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.yes_no)));
        diabetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabetSpinner.setAdapter(diabetAdapter);
        asthmaticAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.yes_no)));
        asthmaticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asthmaticSpinner.setAdapter(asthmaticAdapter);
        dailyExAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.yes_no)));
        dailyExAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dailyExSpinner.setAdapter(dailyExAdapter);
        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener myDateListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int arg1, int arg2, int arg3) {
                        calendar.setTimeInMillis(0);
                        calendar.set(arg1,arg2,arg3);
                        showDate();
                    }
                };

        final DatePickerDialog dialog = new DatePickerDialog(AddDataActivity.this,
                myDateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DAY_OF_MONTH));
        chooseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(AddDataActivity.this), 1);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (place==null)
                {
                    Toast.makeText(getBaseContext(),R.string.error_pick,Toast.LENGTH_SHORT).show();
                    pick.requestFocus();
                    return;
                }
                if (!validateText(name)||!validateText(height)||!validateText(weight))
                {
                    return;
                }
                float h = Float.parseFloat(Objects.requireNonNull(height.getText()).toString()) , w=Float.parseFloat(Objects.requireNonNull(weight.getText()).toString()) ;
                if (h<10||w<0.5f)
                {
                    height.setText("");
                    weight.setText("");
                    validateText(height);
                    validateText(weight);
                    return;
                }
                int age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);

                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR)){
                    age--;
                }
                PatentData patentData = new PatentData(
                    Objects.requireNonNull(name.getText()).toString(),
                        (String) genderSpinner.getSelectedItem(),
                        (String) pregSpinner.getSelectedItem(),
                        (String) diabetSpinner.getSelectedItem(),
                        (String) asthmaticSpinner.getSelectedItem(),
                        (String) bloodTypeSpinner.getSelectedItem(),
                        h,
                        w,
                        Float.parseFloat(Objects.requireNonNull(body_temp.getText()).toString()),
                        (float) (w/Math.pow((h/100f),2)),
                        Integer.parseInt(Objects.requireNonNull(h_press.getText()).toString()),
                        Integer.parseInt(Objects.requireNonNull(l_press.getText()).toString()),
                        age,
                        Integer.parseInt(Objects.requireNonNull(heart_rate.getText()).toString()),
                        true
                );
                View dialogLayout = getLayoutInflater().inflate(R.layout.done_dialog,null);
                TextView message = dialogLayout.findViewById(R.id.text);
                message.setText(getString(R.string.done_upload));
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddDataActivity.this);
                builder.setView(dialogLayout);
                builder.setPositiveButton(getString(R.string.done), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        back();
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        back();
                    }
                });
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("cases").push();
                CaseData data = new CaseData(patentData,weatherData);
                dbref.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            final AlertDialog dialog =builder.create();
                            dialog.show();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),R.string.error_upload,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    void beforeExitAlert()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(getString(R.string.exit_uploading));
        builder.setMessage(getString(R.string.are_you_sure));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                back();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog =builder.create();
        dialog.show();
    }
    private void back() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        progress.setVisibility(View.GONE);
        beforeExitAlert();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                weatherData = new WeatherData();
                progress.setVisibility(View.VISIBLE);
                place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                Uri weatherBuiltUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                        .appendQueryParameter("lat",""+place.getLatLng().latitude)
                        .appendQueryParameter("lon",""+place.getLatLng().longitude)
                        .appendQueryParameter("units","metric")
                        .appendQueryParameter("appid", BuildConfig.OPEN_WEATHER_MAP_API_KEY).build();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, weatherBuiltUri.toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject main = new JSONObject(response).getJSONObject("main");
                                    weatherData.setTemp((float) main.getDouble("temp"));
                                    weatherData.setPressure( main.getInt("pressure"));
                                    weatherData.setHumidity( main.getInt("humidity"));
                                    Toast.makeText(getBaseContext(),"temp: "+weatherData.getTemp(),Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                finally {
                                    progress.setVisibility(View.GONE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                queue.add(stringRequest);
                Uri airQualityBuiltUri = Uri.parse(AQI_BASE_URL+place.getLatLng().latitude+";"+place.getLatLng().longitude+"/")
                        .buildUpon()
                        .appendQueryParameter("token", BuildConfig.AQI_API_KEY).build();
                stringRequest = new StringRequest(Request.Method.GET, airQualityBuiltUri.toString()
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject main = new JSONObject(response).getJSONObject("data").getJSONObject("iaqi");
                            weatherData.setPm25(main.getJSONObject("pm25").getInt("v"));
                            weatherData.setCo(main.getJSONObject("co").getInt("v"));
                            weatherData.setNo2(main.getJSONObject("no2").getInt("v"));
                            weatherData.setO3(main.getJSONObject("o3").getInt("v"));
                            Toast.makeText(getBaseContext(),"PM25: "+weatherData.getPm25(),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finally {
                            progress.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        }
    }
    void showDate()
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyTextWatcher implements TextWatcher {
        private TextInputEditText editText;

        MyTextWatcher(TextInputEditText editText) {
            this.editText = editText;
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
