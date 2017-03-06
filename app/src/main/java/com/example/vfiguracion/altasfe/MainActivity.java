package com.example.vfiguracion.altasfe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vfiguracion.altasfe.soap.AsyncSoap;
import com.example.vfiguracion.altasfe.soap.SoapClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btnget);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView)findViewById(R.id.tv);
               AsyncSoap a = new AsyncSoap();
               String txt = "";
                a.execute(null,null, txt);

                t.setText(a.get().);
            }
        });

    }


}
