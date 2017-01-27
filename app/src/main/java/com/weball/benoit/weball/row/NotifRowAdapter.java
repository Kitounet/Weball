package com.weball.benoit.weball.row;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.NotificationAnswer;
import com.weball.benoit.weball.requestClass.SimpleAnswer;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 14/04/2016.
 */
public class NotifRowAdapter extends ArrayAdapter<Notif_Row> {
    private NotifViewHolder viewHolder;
    private UserInfo    mUser;

    public NotifRowAdapter(Context context, List<Notif_Row> rows, UserInfo mUser) {
        super(context, 0, rows);
        this.mUser = mUser;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_notif,parent, false);
        }



        NotifViewHolder viewHolder = (NotifViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new NotifViewHolder();
            viewHolder.fullname = (TextView) convertView.findViewById(R.id.fullname_text);
            viewHolder.message = (TextView) convertView.findViewById(R.id.message);
            viewHolder.profilepicture = (ImageView) convertView.findViewById(R.id.picture_profile);
            viewHolder.deny = (ImageView) convertView.findViewById(R.id.picture_deny);
            viewHolder.accept = (ImageView) convertView.findViewById(R.id.picture_validate);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Notif_Row notif = getItem(position);


        //il ne reste plus qu'à remplir notre vue

        viewHolder.fullname.setText(notif.getFullName());

        viewHolder.message.setText(notif.getMessage());

        viewHolder.id = notif.get_id();


        if (!notif.getPhoto().isEmpty())
            Picasso.with(getContext()).load(notif.getPhoto()).placeholder(R.drawable.user_3x).fit().centerCrop().into(viewHolder.profilepicture);

        if (notif.getMessage().equals("vous invite à un match") || notif.getMessage().equals("vous invite à discuter"))
        {
            viewHolder.accept.setEnabled(false);
            viewHolder.accept.setVisibility(View.INVISIBLE);
            viewHolder.deny.setEnabled(false);
            viewHolder.deny.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View myView = v;
                    WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                    service.acceptRequest(getItem(position).get_id(), mUser.getToken())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<SimpleAnswer>() {
                                @Override
                                public final void onCompleted() {
                                    // do nothing
                                }

                                @Override
                                public final void onError(Throwable e) {
                                    Log.e("Weball", e.getMessage());
                                }

                                @Override
                                public final void onNext(SimpleAnswer response) {
                                    if (response != null) {
                                        Toast.makeText(myView.getContext(), "Requête accepté", Toast.LENGTH_SHORT).show();
                                        remove(getItem(position));
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                }
            });
            viewHolder.deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View myView = v;
                    WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                    service.denyRequest(getItem(position).get_id(), mUser.getToken())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<SimpleAnswer>() {
                                @Override
                                public final void onCompleted() {
                                    // do nothing
                                }

                                @Override
                                public final void onError(Throwable e) {
                                    Log.e("Weball", e.getMessage());
                                }

                                @Override
                                public final void onNext(SimpleAnswer response) {
                                    if (response != null) {
                                        Toast.makeText(myView.getContext(), "Requête refusé", Toast.LENGTH_SHORT).show();
                                        remove(getItem(position));
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                }
            });
        }

        return convertView;
    }

    private class NotifViewHolder{
        public TextView fullname;
        public String   id;
        public ImageView profilepicture;
        public TextView message;
        public ImageView deny;
        public ImageView accept;
    }
    
}
