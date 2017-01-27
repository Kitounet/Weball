package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.weball.benoit.weball.R;

import com.weball.benoit.weball.requestClass.UserFriend;
import com.weball.benoit.weball.requestClass.UserInfo;

import com.weball.benoit.weball.row.FiveRowAdapter;

import com.weball.benoit.weball.row.FriendRowAdapter;
import com.weball.benoit.weball.row.Friend_Row;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 09/06/2016.
 */
public class TeamMateFragment extends Fragment{
    private UserInfo    mUser;
    private ListView    mListView;
    private List<UserFriend>    mFriends;
    private onTeamMateListener  myListener;

    public TeamMateFragment() {
    }

    public static TeamMateFragment newInstance(UserInfo userinfo)
    {
        TeamMateFragment instance = new TeamMateFragment();
        instance.mUser = userinfo;
                return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.team_mate, container, false);
        ImageView search = (ImageView) mView.findViewById(R.id.search_image);
        ImageView prev = (ImageView) mView.findViewById(R.id.prev);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onSearchFriendClicked();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onPrevClicked();
            }
        });

        mListView = (ListView) mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendRowAdapter myadapter = (FriendRowAdapter) parent.getAdapter();
                if (myadapter != null)
                    myListener.onListClicked(myadapter.getFriendId(position), "listFriend");
            }
        });

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getUserFriends(mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserFriend>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage());
                    }

                    @Override
                    public final void onNext(List<UserFriend> response) {
                        if (response != null) {
                            mFriends = response;
                            List<Friend_Row> rows = generateRows(response);
                            FriendRowAdapter adapter = new FriendRowAdapter(mView.getContext(),rows);
                            mListView.setAdapter(adapter);
                        }
                    }
                });


        return mView;
    }

    private List<Friend_Row> generateRows(List<UserFriend> userFriends)
    {
        List<Friend_Row> rows = new ArrayList<Friend_Row>();

        for (int i = 0; i < userFriends.size(); i++)
        {
            rows.add(new Friend_Row(userFriends.get(i).getUser().getPhoto(), userFriends.get(i).getUser().getFullName(), userFriends.get(i).getUser().get_id()));
        }
        return rows;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTeamMateListener) {
            myListener = (onTeamMateListener) context;
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

    public interface onTeamMateListener{
        public void onListClicked(String id, String name);
        public void onPrevClicked();
        public void onSearchFriendClicked();
    }

}
