package com.example.vortex.loginAndRegister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;
import com.example.vortex.models.Repository.UserRepository;
import com.example.vortex.models.SaveDatabase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Register extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = Register.this;

    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    private Spinner spinner_code_phone;
    private ImageView back;
    private Button buttonRegister;
    private String[] table_spinner_code = {"+237", "+224", "+221", "+91", "+1", "+331"};
    private EditText TextEmail;
    private EditText TextName;
    private EditText TextPassword;
    private EditText TextNumber;
    private EditText TextCoPassword;
    //private static UserDAO userDAO;
    String cod;
    private String Mail, Name, Pass, CoPass;
    private String Num;
    Spinner spiner;
    SignInButton signInButton;

    private User user;
    private InputValidation inputValidation;
    //private DatabaseHelper databaseHelper;

     //Long insertionResult;
    private SaveDatabase appDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        appDatabase=SaveDatabase.getAppDatabase(activity);
        spinner_code_phone = findViewById(R.id.register_spinner);
        TextName = findViewById(R.id.register_username);
        TextEmail = findViewById(R.id.register_email);
        TextPassword = findViewById(R.id.register_password);
        TextCoPassword = findViewById(R.id.register_password_verify);
        TextNumber = findViewById(R.id.register_phone);
        buttonRegister = findViewById(R.id.buttonRegister);
         signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, table_spinner_code);
        spinner_code_phone.setAdapter(arrayAdapter);*/

         spiner=findViewById(R.id.register_spinner);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        buttonRegister.setOnClickListener(this);
        signInButton.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        // databaseHelper = new DatabaseHelper(activity);
        user = new User();

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.codep, android.R.layout.simple_spinner_item );;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cod = (String) parent.getItemAtPosition(position);
                String text=cod +" selected";
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(Register.this, gso);

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonRegister:
                postDataToSQLite();
                break;
            case R.id.sign_in_button:
                signIn();
                break;
        }
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
            Toast.makeText(getApplicationContext(), "Sign in cancel " + e.getStatusCode(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(TextName, getString(R.string.error_message_name))) {
            return;
        } else if (!inputValidation.isInputEditTextFilled(TextEmail, getString(R.string.error_message_email))) {
            return;
        } else if (!inputValidation.isInputEditTextEmail(TextEmail, getString(R.string.error_message_email))) {
            return;
        } else if (!inputValidation.isInputEditTextFilled(TextNumber, getString(R.string.error_message_number))) {
            return;
        } else if (!inputValidation.isInputEditTextFilled(TextPassword, getString(R.string.error_message_password))) {
            return;
        } else if (!inputValidation.isInputEditTextMatches(TextPassword, TextCoPassword, getString(R.string.error_password_match))) {
            return;
        } else {

            String Email = TextEmail.getText().toString().trim();

            User user = appDatabase.userDao().findByEmail(Email);
        if (user != null) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();
        } else {

            String Name = TextName.getText().toString().trim();
            String email =  TextEmail.getText().toString().trim();
            String Number =  TextNumber.getText().toString().trim();
            String Spiner=  spiner.getSelectedItem().toString();
            String Password = TextPassword.getText().toString().trim();
            String CoPassword = TextCoPassword.getText().toString().trim();
            User newUser=new User(Name,email,Spiner,Number,Password, CoPassword);
            appDatabase.userDao().InsertUser(newUser);
            Toast.makeText(getApplicationContext(), getString(R.string.success_message), Toast.LENGTH_LONG).show();
            clearInterface();


            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor =pref.edit();
            editor.putString("Nam",Name);
            editor.putString("Email",Mail);
            editor.putString("Number",Num);
            editor.commit();

            Intent intent = new Intent(Register.this, Otp.class);
            startActivity(intent);
        }


        }
    }

    public void clearInterface(){
        TextNumber.setText("");
        TextName.setText("");
        TextEmail.setText("");
        TextPassword.setText("");
        TextCoPassword.setText("");
    }

}
