package com.example.fidelmomolo.smartfirstaid;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Fidel M Omolo on 4/17/2018.
 */

public class Donor_Dialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_donor__registration,null);
        builder.setView(view);


        /*builder.setTitle("The Beast");
        builder.setMessage("The Monster mwenyewe");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(), "The Beast", Toast.LENGTH_LONG).show();

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "The Storm", Toast.LENGTH_LONG).show();

            }
        });*/
        Dialog dialog=builder.create();

        return dialog;
    }
}
