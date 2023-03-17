package com.example.benchsquatdl2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class searchActivity extends AppCompatActivity {

    private String selectedCountry,  selectedState;                 //vars to hold the values of selected State and District
    private TextView tvCountrySpinner, tvStateSpinner;             //declaring TextView to show the errors
    private Spinner countrySpinner, stateSpinner;                  //Spinners
    private ArrayAdapter<CharSequence> countryAdapter, stateAdapter;  //declare adapters for the spinners

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strength_search_main);

        //Country Spinner Initialisation
        countrySpinner = findViewById(R.id.spinner_country);    //Finds a view that was identified by the android:id attribute

        //Country Spinner Initialisation
        countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_country, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(countryAdapter);            //Set the adapter to the spinner to populate the Country Spinner

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //State Spinner Initialisation
                stateSpinner = findViewById(R.id.spinner_states);    //Finds a view that was identified by the android:id attribute in xml
                selectedCountry = countrySpinner.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.spinner_country){
                    switch (selectedCountry){
                        case "Select Your Country": stateAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_state, R.layout.spinner_layout);
                            break;
                        case "Germany": stateAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_german_states, R.layout.spinner_layout);
                            break;

                        case "USA": stateAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_american_states, R.layout.spinner_layout);
                            break;



                        default:  break;
                    }

                    // Specify the layout to use when the list of choices appear
                    stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    stateSpinner.setAdapter(stateAdapter);            //Set the adapter to the spinner to populate the State Spinner

                    //When any item of the stateSpinner is selected
                    stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            //Define City Spinner but we will populate the options through the selected state

                            selectedState = stateSpinner.getSelectedItem().toString();      //Obtain the selected State

                            int parentID = parent.getId();





                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        Button submitButton;                                //To display the selected State and District
        submitButton = findViewById(R.id.button_submit);
        tvCountrySpinner = findViewById(R.id.textView_country);
        tvStateSpinner = findViewById(R.id.textView_indian_states);


        submitButton.setOnClickListener(v -> {
            if (selectedCountry.equals("Select Your Country")) {
                Toast.makeText(searchActivity.this, "Please select your country from the list", Toast.LENGTH_LONG).show();
                tvCountrySpinner.setError("Country is required!");      //To set error on TextView
                tvCountrySpinner.requestFocus();
            } else if (selectedState.equals("Select Your State")) {
                Toast.makeText(searchActivity.this, "Please select your state from the list", Toast.LENGTH_LONG).show();
                tvStateSpinner.setError("State is required!");      //To set error on TextView
                tvStateSpinner.requestFocus();
            } else {
                tvStateSpinner.setError(null);

                Toast.makeText(searchActivity.this, "Selected Country: "+selectedCountry+"\nSelected State: "+selectedState, Toast.LENGTH_LONG).show();
            }
        });
    }
}