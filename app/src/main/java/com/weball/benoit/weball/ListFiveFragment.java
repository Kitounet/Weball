package com.weball.benoit.weball;

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
import android.widget.ListView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 18/02/2016.
 */
public class ListFiveFragment extends Fragment {
    ListView mListView;
    private UserInfo mUserInfo;

    public ListFiveFragment() {
    }

    public static ListFiveFragment newInstance(UserInfo mUserInfo)
    {
        ListFiveFragment instance = new ListFiveFragment();
        instance.mUserInfo = mUserInfo;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.list_five, container, false);

        double location[] = {0,0};

        location = getLocation();
        mListView = (ListView) mView.findViewById(R.id.listView);
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
                    public final void onNext(List<FiveInfo> response) {
                        if (response != null) {
                            List<Five_Row> rows = generateRows(response);
                            FiveRowAdapter adapter = new FiveRowAdapter(mView.getContext(),rows);
                            mListView.setAdapter(adapter);
                        }
                    }
                });
        return mView;
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
                Log.d("TAG", "GPS is on");
                Log.d("TEST", location.toString());
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
        return null;
    }

    private List<Five_Row> generateRows(List<FiveInfo> fiveInfos)
    {
        List<Five_Row> rows = new ArrayList<Five_Row>();

        for (int i = 0; i < fiveInfos.size(); i++)
        {
            rows.add(new Five_Row(fiveInfos.get(i).getPhoto(), fiveInfos.get(i).getName(), fiveInfos.get(i).getCity()));
        }
        return rows;
    }
}
