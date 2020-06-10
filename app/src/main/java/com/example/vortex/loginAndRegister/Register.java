package com.example.vortex.loginAndRegister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.dataAcess.DatabaseHandler;
import com.example.vortex.dataAcess.Encrypt;
import com.example.vortex.models.DTO.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private DatabaseHandler bd;
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private EditText Confirm;
    private EditText Phone;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    private Spinner spinner_code_phone;
    private ImageView back;
    private Button buttonRegister;
    private String[] table_spinner_code ={"+237", "+224", "+221", "+91", "+1","+331"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Username = (EditText) findViewById(R.id.register_username);
        Email = (EditText) findViewById(R.id.register_email);
        Phone = (EditText) findViewById(R.id.register_phone);
        Password = (EditText) findViewById(R.id.register_password);
        Confirm = (EditText) findViewById(R.id.register_password_verify);
        spinner_code_phone = findViewById(R.id.register_spinner);
        buttonRegister = findViewById(R.id.buttonRegister);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, table_spinner_code);
        spinner_code_phone.setAdapter(arrayAdapter);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(Register.this, gso);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = spinner_code_phone.getSelectedItem().toString() + Phone.getText().toString();
                boolean testRegister = register(Username.getText().toString(), Email.getText().toString(),
                        phoneNumber, Password.getText().toString().trim(), Confirm.getText().toString());

                if(testRegister){
                    Intent intent = new Intent(Register.this, Otp.class);
                    intent.putExtra("number", phoneNumber);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Register.this, "Remplissez bien les Champs", Toast.LENGTH_LONG).show();
                    Username.setText("");
                    Email.setText("");
                    Phone.setText("");
                    Password.setText("");
                    Confirm.setText("");
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch(v.getId()){
                     case R.id.sign_in_button:
                         signIn();
                         break;
                 }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        // [END on_start_sign_in]
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            //mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(Register.this, test.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(getApplicationContext(),"Sign in cancel " + e.getStatusCode(),Toast.LENGTH_LONG).show();
        }
    }

    private boolean register(String username, String email, String phone, String password, String confirm){

        bd = new DatabaseHandler(Register.this);

        boolean v1 = true;
        boolean v2 = true;
        boolean v3 = true;

        if(!password.equals(confirm)){
            v3 = false;
        }

        if(!isEmailValid(email)){
            v2 = false;
        }

        if(v1 && v2 && v3){
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            bd.addUser(user);
            bd.close();
        }

        return v1 && v2 && v3;
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();

    }
}
