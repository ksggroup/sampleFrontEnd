package com.example.vfiguracion.altasfe.soap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.vfiguracion.altasfe.ScrollingActivity;
import com.example.vfiguracion.altasfe.model.AuthenticateRequest;
import com.example.vfiguracion.altasfe.model.User;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.UnknownHostException;

/**
 * Created by vfiguracion on 3/7/17.
 */

public class LoginAsync extends AsyncTask<AuthenticateRequest, String, User> {

    private Context activity;

    String ip = "192.168.0.24";
    String NAMESPACE = "http://service.altanet.ws.com/"; // Find namespace=
    String URL = "http://"+ip+":8080/altanet/service?wsdl"; // Just use the wsdl url
    String METHOD_NAME = "auth";   //Method
    String SOAP_ACTION = "";

    ProgressDialog pd;


    public LoginAsync(Context activity) throws UnknownHostException {
        this.activity = activity;
    }

    @Override
    protected User doInBackground(AuthenticateRequest... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        PropertyInfo pi = new PropertyInfo();
        User user = null;

        pi.setName("AuthenticateRequest");
        pi.setValue(params[0]);
        pi.setType(AuthenticateRequest.class);

        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.debug = true;
        try
        {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.getResponse();

            if(response.getProperty("return_code").toString().equals("0")) {
                System.out.println("response: " + androidHttpTransport.responseDump);

                SoapObject soapUser = (SoapObject)response.getProperty("user");
                user = new User();
                user.setFirst_name(soapUser.getProperty("first_name").toString());
                user.setLast_name(soapUser.getProperty("last_name").toString());
                user.setMiddle_name(soapUser.getProperty("middle_name").toString());
                user.setDob(soapUser.getProperty("dob").toString());
                user.setUser_id(Long.valueOf(soapUser.getProperty("user_id").toString()));

                System.out.println("First Name: " + user.getFirst_name());
                System.out.println("Middle Name: " + user.getMiddle_name());
                System.out.println("Last Name: " + user.getLast_name());
                System.out.println("DOB: " + user.getDob().toString());
                System.out.println("user_id: " + user.getUser_id().toString());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(this.activity);
        pd.setMessage("Authenticating...");
        pd.show();

    }

    @Override
    protected void onPostExecute(User user) {

        System.out.println("onPostExecute: working...");

        pd.dismiss();

        if(user != null) {

            Toast.makeText(this.activity, "Authentication Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, ScrollingActivity.class);
            activity.startActivity(intent);

        }


        else {
            Toast.makeText(this.activity, "Authentication failed!", Toast.LENGTH_SHORT).show();
        }
    }
}



