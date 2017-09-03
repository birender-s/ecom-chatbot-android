package com.example.theodhor.retrofit2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theodhor.retrofit2.Events.ErrorEvent;
import com.example.theodhor.retrofit2.Events.ServerEvent;
import com.squareup.otto.Subscribe;

import moltin.android_sdk.Moltin;

public class MainActivity extends AppCompatActivity {

    private AzureCommunicator communicator;
    private String username, password;
    private EditText usernameET, passwordET;
    private Button loginButtonPost, loginButtonGet;
    private TextView information, extraInformation;
    private Handler.Callback moltinAuthCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        communicator = new AzureCommunicator();

        Moltin moltin = new Moltin(this);

        moltinAuthCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                return false;
            }
        };

        try {
            moltin.authenticate("2wO8V9Fi9JHL61B5iadWnPLWbcoPOlkrL4UBVtLuIx", moltinAuthCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }



        loginButtonGet = (Button)findViewById(R.id.loginButtonGet);
        loginButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useGet(username, password);
            }
        });

        information = (TextView)findViewById(R.id.information);
        extraInformation = (TextView)findViewById(R.id.extraInformation);

       /* bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);*/

    }

    private void usePost(String username, String password){
//        communicator.loginPost(username, password);
    }

    private void useGet(String username, String password){
        communicator.getTopCategories();
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        Toast.makeText(this, ""+serverEvent.getServerResponse().getMessage(), Toast.LENGTH_SHORT).show();
        if(serverEvent.getServerResponse().getUsername() != null){
            information.setText("Username: "+serverEvent.getServerResponse().getUsername() + " || Password: "+serverEvent.getServerResponse().getPassword());
        }
        extraInformation.setText("" + serverEvent.getServerResponse().getMessage());
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}
