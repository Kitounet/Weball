package com.weball.benoit.weball.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.weball.benoit.weball.requestClass.NotificationAnswer;
import com.weball.benoit.weball.row.NotifRowAdapter;
import com.weball.benoit.weball.row.Notif_Row;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 13/04/2016.
 */
public class NotificationFragment extends Fragment {
    private UserInfo mUser;
    private ListView mListView;

    public NotificationFragment() {
    }

    public static NotificationFragment newInstance(UserInfo mUserInfo)
    {
        NotificationFragment instance = new NotificationFragment();
        instance.mUser = mUserInfo;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.notification_page, container, false);
        mListView = (ListView) mView.findViewById(R.id.listView);

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getNotifications(mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NotificationAnswer>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage());
                    }

                    @Override
                    public final void onNext(NotificationAnswer response) {
                        if (response != null) {

                                List<Notif_Row> rows = generateRows(response);
                                Log.d("TEST", "OnNextNotificationFragment");
                                NotifRowAdapter adapter = new NotifRowAdapter(mView.getContext(),rows, mUser);
                                mListView.setAdapter(adapter);
                        }
                    }
                });

        return mView;
    }


    private List<Notif_Row> generateRows(NotificationAnswer request)
    {
        List<Notif_Row> rows = new ArrayList<Notif_Row>();


        for (int i = 0 ; i < request.getRequests().size(); i++)
        {
            rows.add(new Notif_Row(request.getRequests().get(i).getUser().getPhoto(), request.getRequests().get(i).getUser().getFullName(), " vous a ajouté", request.getRequests().get(i).getUser().getId()));
        }
        for (int i = 0; i < request.getNotifications().size(); i++)
        {
            if (request.getNotifications().get(i).getType().equals("join match"))
                rows.add(new Notif_Row(request.getNotifications().get(i).getFrom().getPhoto(), request.getNotifications().get(i).getFrom().getFullName(), "vous invite à un match", request.getNotifications().get(i).getId()));
            else if (request.getNotifications().get(i).getType().equals("chat room"))
                rows.add(new Notif_Row(request.getNotifications().get(i).getFrom().getPhoto(), request.getNotifications().get(i).getFrom().getFullName(), "vous invite à discuter", request.getNotifications().get(i).getId()));
        }
        return rows;
    }
}

