package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;

/**
 * Created by jb704y on 30/10/2017.
 */

public class DataAdapter extends ArrayAdapter<Earthquake> {

    /** Resource ID for the background color for this list of earthquakes */
    //private int mColorResourceId;

    /**
     * Create a new {@link ArrayAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param earthquakes is the list of {@link Earthquake}s to be displayed.
     //* @param colorResourceId is the resource ID for the background color for this list of earthquakes
     */

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */

    String primaryLocation;
    String offSetLocation;


    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


    private static final String LOCATION_SEPARATOR = " of ";

    public DataAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    private int getMagnitudeColor (double magnitude){

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){

            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
            break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
            break;

        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    //We override the getView method from the ArrayAdapter super class so we can control how the list items get created

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID mag_text_view
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_text_view);

        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMag());
        // Get the version name from the current translation object and
        // set this text on the Mag TextView
        magTextView.setText(formattedMagnitude);


        // Create a new String that accepts the value from current position in the array
       String originalLocation = currentEarthquake.getLocation();

        // Find the TextView in the list_item.xml layout with the ID primary location_text_view
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location_text_view);
        // Get the the currentWord object and
        // set this text on the default TextView
        primaryLocationTextView.setText(primaryLocation);

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offSetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            primaryLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Find the TextView in the list_item.xml layout with the ID offset location_text_view
        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.offset_location_text_view);
        // Get the the current location object and
        // set this text on the default TextView
        offsetLocationTextView.setText(offSetLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the ImageView in the list_item.xml layout with the ID date_text_view
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the ImageView in the list_item.xml layout with the ID date_text_view
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //return the list view with the correct data, takes all list items and displays them on screen.
        return listItemView;
    }
}
