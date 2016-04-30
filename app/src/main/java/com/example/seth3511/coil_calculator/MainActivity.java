package com.example.seth3511.coil_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Calculator calculator;
    double resistence;
    double rho=1.45;
    double diameter;
    double guage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator=new Calculator();
        final TextView ans = (TextView)findViewById(R.id.answer);

        Spinner spinner1 = (Spinner) findViewById(R.id.guage);
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(this,
                R.array.guages, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner1!=null){
            spinner1.setAdapter(adapter1);
            spinner1.setOnItemSelectedListener(this);
        }

         EditText diameterbox=(EditText)findViewById(R.id.diameter);
        if (diameterbox != null) {
            diameterbox.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String theText = s.toString();
                    Double num=Double.parseDouble(theText);
                    setDiameter(num);
                }
            });
        }

        EditText res=(EditText)findViewById(R.id.resBox);
        if (res != null) {
            res.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String theText = s.toString();
                    Double num=Double.parseDouble(theText);
                    setResistence(num);
                }
            });
        }

        Button calc= (Button) findViewById(R.id.submit);
        if (calc != null) {
            calc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int num=calculate();
                    if(ans!=null){
                        String temp=(num+" wraps");
                        ans.setText(temp);
                    }
                }
            });
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        double num=Double.parseDouble((String)parent.getItemAtPosition(pos));
        setGuage(num);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void setResistence(double resistence){
        this.resistence=resistence;
    }

    public void setDiameter(double diameter){
        this.diameter=diameter;
    }

    public void setGuage(double guage){
        this.guage=0.127*Math.pow(92,((36.0-guage)/39.0));
    }

    public int calculate(){
        double length=((Math.pow(guage/2,2)*Math.PI*resistence)/rho)*1000;
        double circumference=diameter*2*Math.PI;

        return (int)(length/circumference);
    }
}
