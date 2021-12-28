package com.example.a219_lemonade_stand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class LoginSystemActivity extends AppCompatActivity {


    //Declaring Views & Variables:

    //Images
    private ImageView logo;
    private ImageView loginscreenimage;

    //Define variables which will be used to login.
    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;

    private Button b_createAccount;
    private TextView reveal;    // used to help display playerdata.txt
    boolean isValid = false;    //boolean variable for checking login

    //admin user. and pass.
    String adminUsername = "admin";
    String adminPassword = "12345";

    //Current temp variables for functionality
    String stringsplittemp = "";
    String usertemp = "";
    String passtemp = "";


    private boolean initCheck = false;

    private String usernameInput;


    private static Socket s;
    //private static ServerSocket ss;
    //private static InputStreamReader osr;
    //private static BufferedReader br;
    private static PrintWriter printWriter;

    String message = "";




    ///     https://developer.android.com/training/volley/simple

    /// http://localhost:8080/api/v1/player
    /// [{"id":1,"name":"Rick","dob":"2000-01-05","email":"Rick@morty.com"},{"id":2,"name":"alexis","dob":"2004-09-21","email":"alexis@gmail.com"}]


    //  https://www.youtube.com/watch?v=y2xtLqP8dSQ&ab_channel=CodinginFlows


    //ipconfig ipv4
    //private static String ip = "191.168.";
    private static String ip = "192.168.0.10";

    RequestQueue queue = Volley.newRequestQueue(LoginSystemActivity.this);

    public void checkURL() {

        String url = "http://localhost:8080/api/v1/player";

//        //JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
//
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        String playerName = "";
//                        try {
//
//                            JSONArray jsonArray = response.getJSONArray("players");
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                            //doesnt exist
//
//                            //
//                        }
//                        Toast.makeText(LoginSystemActivity.this, "Something wrong.", Toast.LENGTH_SHORT);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });


    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loginsystem);

        //Referencing xml elements

        //login screen background image
        loginscreenimage = (ImageView) findViewById(R.id.loginscreenimageview);
        loginscreenimage.setImageResource(R.drawable.lemonadestandloginscreen);
        loginscreenimage.setImageAlpha(30);

        //logo image
        logo = (ImageView) findViewById(R.id.LogoView);
        logo.setImageResource(R.drawable.lemonlogo);

        //Username and password elements
        eUsername = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
        eLogin =  findViewById(R.id.bLogin);
        reveal = (TextView) findViewById(R.id.revealtext);







        /**
         * Function to read player data file text and allocate the data into the appropriate strings
         */
        try {
            InputStream instream = openFileInput("app/src/main/java/com/example/a219_lemonade_stand/playerdata.txt");
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line = "";
                try
                {
                    while ((line = buffreader.readLine()) != ";")
                        stringsplittemp += line;

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("doesn't read");
            String error="";
            error=e.getMessage();
        }
        //allocation of the data into the strings
        usertemp.equals(stringsplittemp.split(",", 0));
        System.out.println(usertemp);
        passtemp.equals(stringsplittemp.split(",", 1));
        stringsplittemp.substring(usertemp.length(), passtemp.length() );
        System.out.println(stringsplittemp);
        passtemp.equals(stringsplittemp);

        //reveal text set
        reveal.setText(stringsplittemp + "trying to print username and password");

        /**
         *Function to compare user input with login data, and to prove authenticity.
         *
         */
        eLogin.setOnClickListener(new View.OnClickListener() {

            /**
             * Function to return boolean value based on user data.
             * @param v
             */
            @Override
            public void onClick(View v) {


                String inputName = eUsername.getText().toString();
                String inputPassword = ePassword.getText().toString();

                //Checks if user input is empty
                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginSystemActivity.this, "Please enter details correctly.", Toast.LENGTH_SHORT).show();
                }

                //
                else{

                    //Function to return a boolean variable to compare to.
                    isValid = validate(inputName, inputPassword);

                    //If user input is false.
                    if (!isValid) {
                        Toast.makeText(LoginSystemActivity.this, "Incorrect details entered.", Toast.LENGTH_SHORT).show();
                    }

                    //If user input is true, sends state to HomePageActivity.
                    else {
                        Toast.makeText(LoginSystemActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                        // setContentView(R.layout.homepage_main);
                        // startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                        Intent i = new Intent(LoginSystemActivity.this, MainMenuActivity.class);
                        LoginSystemActivity.this.startActivity(i);
                    }

                }
            }
        });

    }

    /**
     * send text to view?
     */
    public void send_text(View v) {

        message = eUsername.getText().toString();
        myTask mt = new myTask();
        mt.execute();

        Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_LONG).show();


    }

    /**
     * class myTask
     */
    class myTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                s = new Socket(ip, 5000);       //connect to socket at port 5000
                printWriter = new PrintWriter((s.getOutputStream()));   //set the outputstream
                printWriter.write(message); //send the message through the server
                printWriter.flush();
                printWriter.close();
                s.close();



            } catch(IOException e) {

                e.printStackTrace();
            }



            return null;
        }
    }

    /**
     * Function to validate user input and return a boolean value.
     * @param name      Variable for username
     * @param password  Variable for password
     * @return          Returns boolean value
     */
    private boolean validate(String name, String password){

        if
        ((name.equals(adminUsername) && password.equals(adminPassword))
        ) {
            return true;
        }

        if
        ((name.equals(usertemp) && password.equals(passtemp))
        ){
            return true;
        }
        else {
            return false;
        }

    }

}
