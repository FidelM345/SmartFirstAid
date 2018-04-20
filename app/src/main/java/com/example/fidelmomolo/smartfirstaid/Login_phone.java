package com.example.fidelmomolo.smartfirstaid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login_phone extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar1;
    ProgressBar progressBar;
    EditText phone,code203;
    Button verification,button_confirm;
    FirebaseAuth firebaseAuth;
    String mVerificationId;
    String phoneNumber;
    FrameLayout frameLayout;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_login);


        /*//toolbar1=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("The Beast");
*/

         //referencing views
           phone=findViewById(R.id.mato1);
           code203=findViewById(R.id.code_no1);

           verification =findViewById(R.id.submit_phone_no);

           button_confirm=findViewById(R.id.button_confirm);
           progressBar=findViewById(R.id.progress_phoneno);
           frameLayout=findViewById(R.id.master);

           button_confirm.setOnClickListener(this);

            firebaseAuth=FirebaseAuth.getInstance();//method is being initialized
           //verification button handling click action
            verification.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

             case R.id.submit_phone_no:


                 progressBar.setVisibility(View.VISIBLE);

                 phoneNumber= "+254"+phone.getText().toString().trim();

                 PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        this,               // Activity (for callback binding)
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                frameLayout.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                phone.setEnabled(false);
                                verification.setEnabled(false);

                                frameLayout.setVisibility(View.VISIBLE);

                              //  code.setVisibility(View.VISIBLE);
                                 Toast.makeText(Login_phone.this, "Wait for confirmation Text "+phoneAuthCredential, Toast.LENGTH_LONG).show();

                                //signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.

                                String exception=e.getMessage();

                                Toast.makeText(Login_phone.this, "Text Error is: "+exception, Toast.LENGTH_LONG).show();


                            }



                            @Override
                            public void onCodeSent(String verificationId,
                                                   PhoneAuthProvider.ForceResendingToken token) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // by combining the code with a verification ID.
                                // Save verification ID and resending token so we can use them later
                                mVerificationId = verificationId;
                                mResendToken = token;
                                

                                // ...
                            }

                        });        // OnVerificationStateChangedCallbacks
                break;

                 case R.id.button_confirm:

                     String verificationcode=code203.getText().toString();
                     Toast.makeText(Login_phone.this, ""+verificationcode, Toast.LENGTH_LONG).show();
                     PhoneAuthCredential  credential1 = PhoneAuthProvider.getCredential( mVerificationId,verificationcode);
                     signInWithPhoneAuthCredential(credential1);


        }


    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(Login_phone.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                             //FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(Login_phone.this, "Text Error is: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was
                                Toast.makeText(Login_phone.this, "The Verification code entered is invalid ", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }
}
