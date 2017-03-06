package com.example.vfiguracion.altasfe.soap;

import com.example.vfiguracion.altasfe.model.SampleWSRequest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by vfiguracion on 3/6/17.
 */

public class SoapClient {


    public static String get() {
        String result = "";
        String NAMESPACE = "http://service.sample.webservice.com/"; // Find namespace=
        String URL = "http://192.168.0.21:8080/sample-ws/sample?wsdl"; // Just use the wsdl url
        String METHOD_NAME = "getRecipe";   //Method
        String SOAP_ACTION = "";


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        PropertyInfo pi = new PropertyInfo();

        pi.setName("SampleWSRequest");
        pi.setValue(new SampleWSRequest("Pork Adobo"));
        pi.setType(SampleWSRequest.class);

        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        try
        {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.getResponse();
            result = response.getProperty(0).toString();

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("host:" + androidHttpTransport.requestDump);
            System.out.println("host:" + androidHttpTransport.toString());
        }

        return result;
    }

}
