package com.example.theodhor.retrofit2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.Events.ApiTokenModel;
import com.example.theodhor.retrofit2.Events.ConvserationModel;
import com.example.theodhor.retrofit2.Events.ErrorEvent;
import com.example.theodhor.retrofit2.Events.ServerEvent;
import com.example.theodhor.retrofit2.Events.StartConversationModel;
import com.example.theodhor.retrofit2.interfaces.TokenCallback;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TokenCallback {

    private AzureCommunicator communicator;
    private String username, password;
    private EditText usernameET, passwordET;
    private Button loginButtonPost, loginButtonGet;
    private TextView information, extraInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getBranches();
      communicator = new AzureCommunicator(this);
        communicator.getToken("Bearer 4C4V4yUMvgI.cwA.cYw.qakgvIUDhYbrydkQgY59UniNz7HxTi5luInXlweDEZ8");

//        usernameET = (EditText)findViewById(R.id.usernameInput);
//        passwordET = (EditText)findViewById(R.id.passwordInput);

//        loginButtonPost = (Button)findViewById(R.id.loginButtonPost);
//        loginButtonPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                username = usernameET.getText().toString();
//                password = passwordET.getText().toString();
//                usePost(username, password);
//            }
//        });

        loginButtonGet = (Button) findViewById(R.id.loginButtonGet);
        loginButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                username = usernameET.getText().toString();
//                password = passwordET.getText().toString();
               // useGet(username, password);
                getBranches();
            }
        });

        information = (TextView) findViewById(R.id.information);
        extraInformation = (TextView) findViewById(R.id.extraInformation);

       /* bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);*/

    }

    private void usePost(String username, String password) {
//        communicator.loginPost(username, password);
    }

    private void useGet(String username, String password) {
        communicator.getTopCategories();
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {
        Toast.makeText(this, "" + serverEvent.getServerResponse().getMessage(), Toast.LENGTH_SHORT).show();
        if (serverEvent.getServerResponse().getUsername() != null) {
            information.setText("Username: " + serverEvent.getServerResponse().getUsername() + " || Password: " + serverEvent.getServerResponse().getPassword());
        }
        extraInformation.setText("" + serverEvent.getServerResponse().getMessage());
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(this, "" + errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private String generateNonce() {
        java.security.SecureRandom random = null;
        try {
            random = java.security.SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
            byte[] nonceBytes = new byte[16];
            random.nextBytes(nonceBytes);
            String nonce = new String(Base64.encodeToString(nonceBytes, Base64.NO_WRAP));
            System.out.print("NONCE= " + nonce);
            return nonce;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void getBranches() {

        new LongOperation().execute();


    }
    String convserationToken="";
    @Override
    public void getToken(Response<ApiTokenModel> apiTokenModel) {
        convserationToken=apiTokenModel.body().getToken();
        communicator.startConversation(apiTokenModel.body().getToken());
    }

    @Override
    public void startConvsersationCallbacl(Response<StartConversationModel> apiTokenModel) {
        communicator.sendActivityToBot(convserationToken,"what do you have?",apiTokenModel.body()
                .getConversationId());
    }

    @Override
    public void fetchResponseFromBot(Response<ConvserationModel> response) {

    }

    @Override
    public void showBotResponse(Response<ActivityModel> response) {

    }

    private class LongOperation extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String urlBranches = "http://95.85.55.146/api/rest/latest/desk/branches";
            String passwordDigest = "d6f31265af909a5acc99c2eb901cbeb5d9f68fcd";
            JSONParser jsonParser = new JSONParser(MainActivity.this);
            User user = new User();
            user.setUsername("admin");
            user.setPassword("urYK4H3868ZGZXdv");
            JSONObject jsonObject = jsonParser.getJSONFromUrl(urlBranches, JSONParser.GET, null, user);

            if (null != jsonObject) {
                return jsonObject.toString();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("RESPONSE BRANCHES=" + result);
            // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @Override
    public void productSelectionCallback(String productId) {

    }
}
