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
import android.widget.SearchView;
import android.widget.TextView;

import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.row.FiveRowAdapter;
import com.weball.benoit.weball.row.Five_Row;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoi on 13/04/2016.
 */
public class SearchFiveFragment extends Fragment {
    private UserInfo mUser;
    private List<FiveInfo>  mFives;
    private onListFiveClicked   myListener;
    private SearchView mysearch;
    private ListView            mListView;

    public SearchFiveFragment() {
    }

    public static SearchFiveFragment newInstance(UserInfo mUserInfo, List<FiveInfo> fives)
    {
        SearchFiveFragment instance = new SearchFiveFragment();
        instance.mFives = fives;
        instance.mUser = mUserInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.search_five_fragment, container, false);
        TextView    cancel = (TextView) mView.findViewById(R.id.cancel_text);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onCancelClicked();
            }
        });

        mListView = (ListView) mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FiveRowAdapter myadapter = (FiveRowAdapter)parent.getAdapter();
                if (myadapter != null)
                    myListener.onListClicked(myadapter.getFiveId(position), "searchFive");
            }
        });

        mysearch = (SearchView) mView.findViewById(R.id.searchbar);

        mysearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("TEST", "QUERYTEXTSUBMIT");
                if (query.length() != 0)
                {
                    List<FiveInfo>  ListFiveShow = new ArrayList<FiveInfo>();
                    for (int i = 0; i < mFives.size(); i++)
                    {
                        if (mFives.get(i).getName().toLowerCase().contains(query.toLowerCase()))
                        {
                            ListFiveShow.add(mFives.get(i));
                        }
                    }
                    List<Five_Row> rows = generateRows(ListFiveShow);
                    FiveRowAdapter  adapter = new FiveRowAdapter(mView.getContext(), rows);
                    mListView.setAdapter(adapter);
                }



                mView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("TEST", "JEQUERYTEXTCHANGE");
                if (newText.length() != 0)
                {
                    List<FiveInfo>  ListFiveShow = new ArrayList<FiveInfo>();
                    for (int i = 0; i < mFives.size(); i++)
                    {
                        if (mFives.get(i).getName().toLowerCase().contains(newText.toLowerCase()))
                        {
                            ListFiveShow.add(mFives.get(i));
                        }
                    }
                    List<Five_Row> rows = generateRows(ListFiveShow);
                    FiveRowAdapter  adapter = new FiveRowAdapter(mView.getContext(), rows);
                    mListView.setAdapter(adapter);
                }
                return false;
            }
        });


        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onListFiveClicked) {
            myListener = (onListFiveClicked) context;
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

    private List<Five_Row> generateRows(List<FiveInfo> fiveInfos)
    {
        List<Five_Row> rows = new ArrayList<Five_Row>();

        for (int i = 0; i < fiveInfos.size(); i++)
        {
            rows.add(new Five_Row(fiveInfos.get(i).getPhoto(), fiveInfos.get(i).getName(), fiveInfos.get(i).getCity(), fiveInfos.get(i).get_id()));
        }
        return rows;
    }



    public interface onListFiveClicked{
        public void onListClicked(String id, String name);
        public void onCancelClicked();

    }
}
