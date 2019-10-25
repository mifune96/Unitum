package com.timbabe.unitum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tiper.MaterialSpinner;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.carType)
    Spinner carType;

    @BindView(R.id.carWeight)
    TextView carWeight;

    @BindView(R.id.maximumWeight)
    TextInputEditText maxWeight;

    @BindView(R.id.emptyWeight)
    TextInputEditText emptyWeight;

    @BindView(R.id.carLength)
    TextInputEditText carLength;

    @BindView(R.id.carWidth)
    TextInputEditText carWidth;

    @BindView(R.id.carHeight)
    TextInputEditText carHeight;

    @BindView(R.id.btTrack)
    Button track;

    @BindView(R.id.txtStatus)
    TextView status;

    FirebaseDatabase mDatabase;
    List<CarSpec> carSpecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        carSpecs = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CarData data = dataSnapshot.getValue(CarData.class);

                carWeight.setText("" + data.getBerat());

                iniSpinnerItem();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cannot retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

        track.setOnClickListener(v -> {
            startActivity(new Intent(this, MapsActivity.class));
        });

    }

    private void iniSpinnerItem() {
        CarSpec ccdBox = new CarSpec("CDD Box", 670, 200, 220
                , 2500, 8000);
        CarSpec ccdBak = new CarSpec("CDD Bak", 670, 200, 220
                , 2300, 7500);
        CarSpec ccdLosBak = new CarSpec("CDD Los Bak", 670, 200, 220
                , 2500, 8000);
        CarSpec ccdCarCarrier = new CarSpec("CDD Carrier", 670, 200, 220
                , 2500, 8000);
        CarSpec ccdLongBox = new CarSpec("CDD Long Box", 940, 240, 270
                , 4000, 14000);
        CarSpec ccdMotorCarrierLong = new CarSpec("CDD Motor Carrier Long", 940
                , 240, 270, 4000, 14000);
        CarSpec ccdBakLong = new CarSpec("CDD Bak Long", 940, 240, 270
                , 4000, 14000);
        CarSpec ccdBakAirGalon = new CarSpec("CDD Bak Air Galon", 870, 240, 270
                , 4000, 14000);
        CarSpec ccdBoxReefer = new CarSpec("CDD Box Reefer", 670, 200, 220
                , 2500, 8000);

        carSpecs.add(ccdBox);
        carSpecs.add(ccdBak);
        carSpecs.add(ccdLosBak);
        carSpecs.add(ccdCarCarrier);
        carSpecs.add(ccdLongBox);
        carSpecs.add(ccdMotorCarrierLong);
        carSpecs.add(ccdBakLong);
        carSpecs.add(ccdBakAirGalon);
        carSpecs.add(ccdBoxReefer);

        ArrayAdapter<CarSpec> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, carSpecs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        carType.setAdapter(adapter);

        carType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarSpec carSpec = (CarSpec) parent.getSelectedItem();
                displayCarData(carSpec);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*public void getSelectedCarType(View v) {
        CarSpec carSpec = (CarSpec) carType.getSelectedItem();
        displayCarData(carSpec);
    }*/

    private void displayCarData(CarSpec carSpec) {
        int maxWeightLimit = (carSpec.getCarMaxWeight() * 20 / 100) + carSpec.getCarMaxWeight();

        maxWeight.setText(maxWeightLimit + " Kg");
        emptyWeight.setText(carSpec.getCarEmptyWeight() + " Kg");
        carLength.setText(carSpec.getCarLength() + " cm");
        carWidth.setText(carSpec.getCarWidth() + " cm");
        carHeight.setText(carSpec.getCarHeight() + " cm");

        if (Integer.parseInt(carWeight.getText().toString()) > maxWeightLimit) {
            status.setText(getString(R.string.status_over));
            status.setBackground(getDrawable(R.drawable.overload_status_background));
        } else {
            status.setText(getString(R.string.status_normal));
            status.setBackground(getDrawable(R.drawable.normal_status_background));
        }
    }

}
