package com.example.aldokana.Utils;

/**
 * Created by MAHMOUD on 12/12/2018.
 */

public abstract class GlobalVariables {

    public static final String GET_ADDRESSES_FROM_LATLNG_URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=";


    public static double LOCATION_LAT ;
    public static double LOCATION_LNG ;



    public static String qrCode = "";

    public static boolean FINISH_ACTIVITY = false;

    public static boolean FINISH_ACTIVITY_2 = false;

    public static boolean FINISH_ACTIVITY_3 = false;

    public static boolean FINISH_ACTIVITY_REGISTER = false;


    public static final int MAIN_FRAGMENT_ID = 1;
    //public static final String MAIN_FRAGMENT_TAG = HomeFragment.class.getSimpleName();
    public static final int ORDERS_FRAGMENT_ID = 2;
    //public static final String ORDERS_FRAGMENT_TAG = OrdersFragment.class.getSimpleName();
    public static final int OFFERS_FRAGMENT_ID = 3;
    //public static final String NOTIFICATIONS_FRAGMENT_TAG = NotificationFragment.class.getSimpleName();
    public static final int PROFILE_FRAGMENT_ID = 4;
    //public static final String JOURNEY_FRAGMENT_TAG = JourneyFragment.class.getSimpleName();
    public static final int MORE_FRAGMENT_ID = 5;
   // public static final String MORE_FRAGMENT_TAG = MoreFragment.class.getSimpleName();
    public static final int ACTIVE_ORDERS_FRAGMENT_ID = 6;
   // public static final String ACTIVE_ORDERS_FRAGMENT_TAG = ActiveOrdersFragment.class.getSimpleName();
    public static final int FINISHED_ORDERS_FRAGMENT_ID = 7;
   // public static final String FINISHED_ORDERS_FRAGMENT_TAG = FinishedOrdersFragment.class.getSimpleName();
    public static final int ACTIVE_JOURNEY_FRAGMENT_ID = 8;
    //public static final String ACTIVE_JOURNEY_FRAGMENT_TAG = ActiveJourneyFragment.class.getSimpleName();
    public static final int NEW_JOURNEY_FRAGMENT_ID = 9;
    //public static final String NEW_JOURNEY_FRAGMENT_TAG = NewJourneyFragment.class.getSimpleName();


}