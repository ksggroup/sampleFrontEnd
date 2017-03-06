package com.example.vfiguracion.altasfe.soap;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by vfiguracion on 3/6/17.
 */

public class AsyncSoap extends AsyncTask<Object, Object, String> {
    @Override
    protected String doInBackground(Object... params) {

        return SoapClient.get();
    }
}
