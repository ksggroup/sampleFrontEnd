package com.example.vfiguracion.altasfe.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;


public class SampleWSRequest implements KvmSerializable {


    private String viandName;

    public SampleWSRequest(String viandName) {
        this.viandName = viandName;
    }

    @Override
    public Object getProperty(int i) {
        return this.viandName;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void setProperty(int i, Object o) {
        this.viandName = o.toString();
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        propertyInfo.setName("viandName");
        propertyInfo.setType(propertyInfo.STRING_CLASS);
    }
}
