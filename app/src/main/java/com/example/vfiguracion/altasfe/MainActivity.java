package com.example.vfiguracion.altasfe;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vfiguracion.altasfe.model.AuthenticateRequest;
import com.example.vfiguracion.altasfe.model.User;
import com.example.vfiguracion.altasfe.soap.LoginAsync;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog pd = new ProgressDialog(this);
        final Button loginBtn = (Button)findViewById(R.id.login);
        final EditText usernameEt = (EditText) findViewById(R.id.username);
        final EditText passwordEt = (EditText) findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LoginAsync la = null;
                try {
                    la = new LoginAsync(v.getContext());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                la.execute(new AuthenticateRequest(usernameEt.getText().toString(), passwordEt.getText().toString()));

            }
        });

    }


}
