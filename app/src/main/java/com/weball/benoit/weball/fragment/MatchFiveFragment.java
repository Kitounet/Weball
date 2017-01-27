package com.weball.benoit.weball.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.service.WeballService;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 17/02/2016.
 */
public class MatchFiveFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    private UserInfo mUserInfo;
    private GoogleMap mMap;
    private onMapListener myListener;
    private List<FiveInfo>  list_fives;
    private ImageView       search;

    public MatchFiveFragment() {
    }

    public static MatchFiveFragment newInstance(UserInfo mUserInfo)
    {
        MatchFiveFragment instance = new MatchFiveFragment();
        instance.mUserInfo = mUserInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View mView = inflater.inflate(R.layout.map_five,container, false);

        search = (ImageView) mView.findViewById(R.id.button_search);
        search.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double location[] = {0,0};
         location = getLocation();
            if (location != null) {
                LatLng me = new LatLng(location[0], location[1]);
                mMap.addMarker(new MarkerOptions().position(me).title("Votre localisation"));
                float zoomLevel = 12; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, zoomLevel));
                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.getAllFives(mUserInfo.getToken(), location[1], location[0])
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<FiveInfo>>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("Weball", e.getMessage());
                            }

                            @Override
                            public final void onNext(final List<FiveInfo> response) {
                                list_fives = response;
                                if (response != null) {
                                    Drawable dr = getResources().getDrawable(R.drawable.pins);
                                    Bitmap b = ((BitmapDrawable) dr).getBitmap();
                                    int sizeX = Math.round(dr.getIntrinsicWidth() / 3);
                                    int sizeY = Math.round(dr.getIntrinsicHeight() / 3);
                                    Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
                                    MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmapResized));
                                    for (int i = 0; i < response.size(); i++)
                                    {
                                        //lattitude first and after longitude
                                        LatLng others = new LatLng(response.get(i).getGps().get(1), response.get(i).getGps().get(0));
                                        mMap.addMarker(markerOptions.position(others).title(response.get(i).getName()));
                                    }
                                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        @Override
                                        public boolean onMarkerClick(Marker marker) {
                                            FiveInfo    five;

                                            LatLng pos = marker.getPosition();
                                            five = findInFiveList(response, pos.latitude, pos.longitude);
                                            if (five != null)
                                                myListener.onMapClicked(five);
                                            return false;
                                        }
                                    });

                                }
                            }
                        });
            }
    }

    protected FiveInfo findInFiveList(List<FiveInfo> list, double lat, double longi)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getGps().get(1) == lat && list.get(i).getGps().get(0) == longi)
                return list.get(i);
        }
        return null;
    }

    protected double[] getLocation() {
        double longitude;
        double latitude;
        double tab[] = {0, 0};
        LocationManager lm = (LocationManager) this.getContext().getSystemService(Context.LOCATION_SERVICE);
        if ( ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            else{
                //create our own location at Paris
                latitude = 48.858454;
                longitude = 2.294694;
            }
            tab[0] = latitude;
            tab[1] = longitude;
            return tab;
        }
        tab[0] = 48.858454;
        tab[1] =  2.294694;
        return tab;
    }

    @Override
    public void onClick(View v)
    {
        if (list_fives != null)
            myListener.onSearchClicked(list_fives, "matchFive");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onMapListener) {
            myListener = (onMapListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public interface onMapListener{
        public void onMapClicked(FiveInfo infos);
        public void onSearchClicked(List<FiveInfo> list_five, String name);
    }

}
