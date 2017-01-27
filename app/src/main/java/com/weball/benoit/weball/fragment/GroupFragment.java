package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.NotificationAnswer;
import com.weball.benoit.weball.requestClass.Relationship;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.row.FiveRowAdapter;
import com.weball.benoit.weball.row.NotifRowAdapter;
import com.weball.benoit.weball.row.Notif_Row;
import com.weball.benoit.weball.row.UserFriendAdapter;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 11/11/2016.
 */

public class GroupFragment extends Fragment {
    private UserInfo mUser;
    private onGroupListener myListener;
    private ListView mListView;

    public GroupFragment() {
    }

    public static GroupFragment newInstance(UserInfo mUserInfo) {
        GroupFragment instance = new GroupFragment();
        instance.mUser = mUserInfo;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.friend_list_page, container, false);
        mListView = (ListView) mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserFriendAdapter myadapter = (UserFriendAdapter)parent.getAdapter();
                if (myadapter != null)
                    myListener.onGroupListClicked(myadapter.getFriendId(position));
            }
        });

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getRelashionship(mUser.get_id(), mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Relationship>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage());
                    }

                    @Override
                    public final void onNext(List<Relationship> response) {
                        if (response != null) {
                            Log.d("TEST", "OnNextGroupFragment");
                            UserFriendAdapter adapter = new UserFriendAdapter(mView.getContext(), response);
                            mListView.setAdapter(adapter);
                        }
                    }
                });

        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GroupFragment.onGroupListener) {
            myListener = (GroupFragment.onGroupListener) context;
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

    public interface onGroupListener{
        public void onGroupListClicked(String id);
    }
}