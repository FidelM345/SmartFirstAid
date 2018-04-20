package com.example.fidelmomolo.smartfirstaid;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Donor_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__registration);

    }

    public void alertDialog(View view) {
      Donor_Dialog donor_dialog=new Donor_Dialog();
      donor_dialog.show(getFragmentManager(),"MyTag");


    }
}
