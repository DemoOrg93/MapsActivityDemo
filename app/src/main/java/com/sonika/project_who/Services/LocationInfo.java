package com.sonika.project_who.Services;

import com.sonika.project_who.pojo.HospitalsPojo;

import java.util.ArrayList;

/**
 * Created by sonika on 8/11/2017.
 */

public class LocationInfo
{
    public static final LocationInfo instance = new LocationInfo();

    public static LocationInfo getInstance()
    {
        return instance;
    }

    private LocationInfo()
     {
    }

    public ArrayList<HospitalsPojo> getHospitalLocations(double latitude, double longitude)
    {
        ArrayList<HospitalsPojo> locationlist = new ArrayList<>();
        locationlist.add(new HospitalsPojo(27.7048, 85.3137, "Bir hospital" ));
        locationlist.add(new HospitalsPojo(27.6683, 85.3206, "Patan hospital"));
        locationlist.add(new HospitalsPojo(27.6842, 85.2985, "Vayoda"));
        locationlist.add(new HospitalsPojo(27.6900, 85.3191,  "Norvic"));
        locationlist.add(new HospitalsPojo(27.6649, 85.3296,  "B & B"));
        locationlist.add(new HospitalsPojo(27.7246, 85.2958, "National Kidney Center"));
        locationlist.add(new HospitalsPojo(27.7360, 85.3303, "Teaching Hospital"));
        return  locationlist;
    }


}
