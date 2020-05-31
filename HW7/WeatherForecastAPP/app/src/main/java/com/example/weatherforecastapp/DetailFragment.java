package com.example.weatherforecastapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String lon,lat,WeatherDescription,Temperature,WindSpeed;
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle data = getArguments();
        String detailData = null;
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(getString(R.string.OPEN_WEATHER_MAP_API_KEY));

        final String[] DataAll = new String[7];//0 = lat , 1 = lat , 2= 날씨 , 3 = 온도, 4= 바람 , 5= 도시 , 6=나라

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView CountryTextView = (TextView)rootView.findViewById(R.id.Country_id);
        //detailTextView.setText(detailData);
        if (data != null) {
            detailData = data.getString("data");
            System.out.println(detailData);



            helper.getCurrentWeatherByCityName(detailData,new CurrentWeatherCallback() {

                @Override
                public void onSuccess(CurrentWeather currentWeather) {
                    Log.v(TAG, "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon() +"\n"
                            +"Weather Description: " + currentWeather.getWeather().get(0).getDescription() + "\n"
                            +"Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                            +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                            +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                    );


                    DataAll[0]= String.valueOf(currentWeather.getCoord().getLat());
                    DataAll[1]= String.valueOf(currentWeather.getCoord().getLon());
                    DataAll[2]= String.valueOf(currentWeather.getWeather().get(0).getDescription());
                    DataAll[3]= String.valueOf(currentWeather.getMain().getTempMax());
                    DataAll[4]= String.valueOf(currentWeather.getWind().getSpeed());
                    DataAll[5]= String.valueOf(currentWeather.getName());
                    DataAll[6]= String.valueOf(currentWeather.getSys().getCountry());


                    CountryTextView.setText("국가 : "+currentWeather.getSys().getCountry()+"\n도시 : " + currentWeather.getName()  +"\n위도 : " + currentWeather.getCoord().getLat() + "\n경도 : "+currentWeather.getCoord().getLon() +"\n"
                            +"Weather Description: " + currentWeather.getWeather().get(0).getDescription() + "\n"
                            +"온도 : " + currentWeather.getMain().getTempMax()+" F\n"
                            +"Wind Speed(풍속) : " + currentWeather.getWind().getSpeed() + "\n"
                    );


                }
                @Override
                public void onFailure(Throwable throwable) {e
                    Log.v(TAG, throwable.getMessage());
                }
            });

        }




        return rootView;

    }
}
