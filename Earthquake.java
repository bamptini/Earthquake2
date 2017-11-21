package com.example.android.quakereport;

/**
 * Created by jb704y on 31/10/2017.
 */

public class Earthquake {

    /** Magnitude of the earthquake */
    private Double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** Date of the earthquake */
    private long mTimeInMilliseconds;

    /** URL for the location of the earthquake */
    private String mUrl;

    //Constructor for all other activities which contains three arguments/input parameters
    public Earthquake (double magnitude, String location, long eventdate, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = eventdate;
        mUrl = url;

    }

    /**
     * Get Magnitude.
     */
    public double getMag() { return mMagnitude;
    }

    /**
     * Get the Location.
     */
    public String getLocation() { return mLocation;
    }

    /**
     * Get Date of event
     */
    public long getTimeInMilliseconds() { return mTimeInMilliseconds;
    }
    /**
     * Get URL of event
     */
    public String getUrl() { return mUrl;
    }

}

