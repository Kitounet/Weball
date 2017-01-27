package com.weball.benoit.weball.fragment;

import android.app.SearchManager;
import android.content.Context;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;

import android.widget.ListView;
import android.widget.SearchView;

import android.widget.Switch;
import android.widget.TextView;


import com.algolia.search.saas.APIClient;
import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.algolia.search.saas.listeners.SearchListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.custom.HighlightRenderer;
import com.weball.benoit.weball.custom.HighlightedResult;
import com.weball.benoit.weball.requestClass.SearchFriend;
import com.weball.benoit.weball.requestClass.SearchResultsJsonParser;
import com.weball.benoit.weball.requestClass.UserInfo;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Created by benoi on 14/07/2016.
 */
public class ReservationFriendFragment extends Fragment implements SearchView.OnQueryTextListener, AbsListView.OnScrollListener{
    private onReservateFriendListener myListener;
    private APIClient apiClient;
    private String APP_ID = "1DLPJHZGI0";
    private String API_KEY ="0e98b21ffc2bbd1ff1fb7ef6327edd1d";
    private Index index;
    private Query query;
    private SearchResultsJsonParser resultsParser = new SearchResultsJsonParser();
    private int lastSearchedSeqNo;
    private int lastDisplayedSeqNo;
    private int lastRequestedPage;
    private int lastDisplayedPage;
    private boolean endReached;

    // UI:
    private SearchView searchView;
    private ListView mListView;
    private SearchFriendAdapter searchFriendAdapter;
    private ImageLoader imageLoader;
    private DisplayImageOptions displayImageOptions;
    private HighlightRenderer highlightRenderer;
    private UserInfo mUserInfo;
    private ArrayList<String>   friendlist;

    // Constants

    private static final int HITS_PER_PAGE = 20;

    /** Number of items before the end of the list past which we start loading more content. */
    private static final int LOAD_MORE_THRESHOLD = 5;

    public static ReservationFriendFragment newInstance(UserInfo mUserInfo)
    {
        ReservationFriendFragment instance = new ReservationFriendFragment();
        instance.mUserInfo = mUserInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.reservation_friend,container, false);

        TextView cancel = (TextView)mView.findViewById(R.id.cancel);
        TextView validate = (TextView)mView.findViewById(R.id.validation);
        friendlist = new ArrayList<String>();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onPrevClicked(1);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onValidateFriendClicked(friendlist);
            }
        });

        mListView = (ListView) mView.findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchFriendAdapter myadapter = (SearchFriendAdapter) parent.getAdapter();
                Log.d("TEST", "CHECK JE SUIS LA DEDANS FRERE");
                if (myadapter != null) {
                    View v = mListView.getChildAt(position);
                    if(v == null)
                        return;

                    if (myadapter.updateRow(position))
                        friendlist.add(myadapter.getUserId(position));
                    else
                        friendlist.remove(myadapter.getUserId(position));
                    Log.d("TEST", "BLBLBLBLBLBLBLBLBLBL");
                    Log.d("TEST", friendlist.toString());
                    myadapter.notifyDataSetChanged();
                }
            }
        });
        mListView.setAdapter(searchFriendAdapter = new SearchFriendAdapter(mView.getContext(), R.layout.row_reservation_friend));




        // Init Algolia.
        apiClient = new APIClient(APP_ID, API_KEY);
        index = apiClient.initIndex("Users");

        // Pre-build query.
        query = new Query();
        query.setAttributesToRetrieve(Arrays.asList("fullName", "photo", "_id"));
        query.setAttributesToHighlight(Arrays.asList("fullName"));
        query.setHitsPerPage(HITS_PER_PAGE);

        // Configure Universal Image Loader.
        displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageLoader = ImageLoader.getInstance();
                ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getActivity())
                        .memoryCacheSize(2 * 1024 * 1024)
                        .memoryCacheSizePercentage(13) // default
                        .build();
                imageLoader.init(configuration);
            }
        }).start();

        highlightRenderer = new HighlightRenderer(getActivity());
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) mView.findViewById(R.id.searchbar);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(this);




        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onReservateFriendListener) {
            myListener = (onReservateFriendListener) context;
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

    public interface onReservateFriendListener{
        public void onPrevClicked(int nb);
        public void onValidateFriendClicked(ArrayList<String> friendList);

    }
    private class SearchFriendAdapter extends ArrayAdapter<HighlightedResult<SearchFriend>>
    {

        public SearchFriendAdapter(Context context, int resource)
        {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup cell = (ViewGroup)convertView;
            if (cell == null) {
                cell = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.row_reservation_friend, null);
            }

            ImageView posterImageView = (ImageView) cell.findViewById(R.id.picture_profile);
            TextView titleTextView = (TextView) cell.findViewById(R.id.name);
            TextView status = (TextView) cell.findViewById(R.id.status);
            HighlightedResult<SearchFriend> result = searchFriendAdapter.getItem(position);


            imageLoader.displayImage(result.getResult().getPhoto(), posterImageView, displayImageOptions);
            if (result.getResult().getStatus().equals("INVITER"))
            {
                status.setText("INVITER");
                status.setTextColor(getResources().getColor(R.color.light_gray));
            }
            else
            {
                status.setText("INVITÉ");
                status.setTextColor(getResources().getColor(R.color.green));
            }
            titleTextView.setText(highlightRenderer.renderHighlights(result.getHighlight("fullName").getHighlightedValue()));

            return cell;
        }

        public String getUserId(int position)
        {
            HighlightedResult<SearchFriend> result = getItem(position);

             return result.getResult().get_id();
        }

        public boolean  updateRow(int position)
        {
            HighlightedResult<SearchFriend> result = getItem(position);

            if (result.getResult().getStatus().equals("INVITER"))
            {
                result.getResult().setStatus("INVITÉ");
                return true;
            }
            result.getResult().setStatus("INVITER");
            return false;
        }


        @Override
        public  void addAll(Collection<? extends HighlightedResult<SearchFriend>> items) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                super.addAll(items);
            } else {
                for (HighlightedResult<SearchFriend> item : items) {
                    add(item);
                }
            }
        }
    }

    private void search()
    {
        final int currentSearchSeqNo = ++lastSearchedSeqNo;
        query.setQueryString(searchView.getQuery().toString());
        lastRequestedPage = 0;
        lastDisplayedPage = -1;
        endReached = false;
        index.searchASync(query, new SearchListener() {
            @Override
            public void searchResult(Index index, Query query, JSONObject results) {
                if (currentSearchSeqNo <= lastDisplayedSeqNo)
                    return;

                List<HighlightedResult<SearchFriend>> result = resultsParser.parseResults(results);
                if (result.isEmpty()) {
                    endReached = true;
                }
                else {
                    searchFriendAdapter.clear();
                    searchFriendAdapter.addAll(result);
                    searchFriendAdapter.notifyDataSetChanged();
                    lastDisplayedSeqNo = currentSearchSeqNo;
                    lastDisplayedPage = 0;
                }

                // Scroll the list back to the top.
                mListView.smoothScrollToPosition(0);
            }

            @Override
            public void searchError(Index index, Query query, AlgoliaException e) {

            }

            // [...]
        });
    }

    private void loadMore()
    {
        Query loadMoreQuery = new Query(query);
        loadMoreQuery.setPage(++lastRequestedPage);
        final int currentSearchSeqNo = lastSearchedSeqNo;
        index.searchASync(loadMoreQuery, new SearchListener() {
            @Override
            public void searchResult(Index index, Query query, JSONObject results) {
                // Ignore results if they are for an older query.
                if (lastDisplayedSeqNo != currentSearchSeqNo)
                    return;

                List<HighlightedResult<SearchFriend>> result = resultsParser.parseResults(results);
                if (result.isEmpty()) {
                    endReached = true;
                }
                else {
                    searchFriendAdapter.addAll(result);
                    searchFriendAdapter.notifyDataSetChanged();
                    lastDisplayedPage = lastRequestedPage;
                }
            }

            @Override
            public void searchError(Index index, Query query, AlgoliaException e) {

            }
        });
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        // Abort if list is empty or the end has already been reached.
        if (totalItemCount == 0 || endReached)
            return;

        // Ignore if a new page has already been requested.
        if (lastRequestedPage > lastDisplayedPage)
            return;

        // Load more if we are sufficiently close to the end of the list.
        int firstInvisibleItem = firstVisibleItem + visibleItemCount;
        if (firstInvisibleItem + 5 >= totalItemCount)
            loadMore();
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        // Nothing to do: the search has already been performed by `onQueryTextChange()`.
        // We do try to close the keyboard, though.
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        search();
        return true;
    }

    // AbsListView.OnScrollListener

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        // Nothing to do.
    }

}
