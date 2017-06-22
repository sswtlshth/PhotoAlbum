package app.com.loginsqllite;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG="Debug";
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d("DeBUg","onAuthStateChanged: signed in:: "+user.getUid());
                }else {
                    Log.d("DeBUg","onAuthStateChanged: signed out:: ");
                }
            }
        };
        //create db instance
     /*   loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
*/
        //get reference of button
        btnSignIn=(Button) findViewById(R.id.buttonSignIN);
        btnSignUp=(Button) findViewById(R.id.buttonSignUP);

        //Set onclick listener on signup button
  /*      btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intentSignUP=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intentSignUP);
            }
        }); */
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intentSignUP=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public  void signIn(View v){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        // get the Refferences of views
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener(){
            String username=editTextUserName.getText().toString();
            String password=editTextPassword.getText().toString();
            @Override
            public void onClick(View v) {
                String username=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                Log.d(TAG,"The username entered::" + username);
                Log.d(TAG,"Tha password entered :: "+password);
                signInFirebase(username,password);
            }
        });
        /*  btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    Intent welcome=new Intent(getApplicationContext(),Welcome.class);
                    welcome.putExtra(EXTRA_MESSAGE, userName);
                    startActivity(welcome);
                }
                else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });*/
                dialog.show();
            }

        private void signInFirebase(String username,String password){
            mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG,"Login Successful");
                    }else{
                        Toast.makeText(MainActivity.this, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
                    }
                }
                private void changeUI(){
                    Intent welcome=new Intent(getApplicationContext(),Welcome.class);
                    welcome.putExtra(EXTRA_MESSAGE, mAuth.getCurrentUser().getDisplayName());
                    startActivity(welcome);
                }
            });
        }
            @Override
            protected void onDestroy() {
                super.onDestroy();
                //   loginDataBaseAdapter.close();
            }
        }
