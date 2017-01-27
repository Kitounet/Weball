package com.weball.benoit.weball.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weball.benoit.weball.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

/**
 * Created by benoi on 20/01/2016.
 */
public class ProfilePictureFragment extends Fragment{

    private onTextViewListener4 myListener;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    private ImageView selectImage;
    private String picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.subscribe_picture,container, false);
        TextView next = (TextView) mView.findViewById(R.id.TextView2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = picture;
                if (picture == null)
                    first = "";
                else
                    first = picture;
                List<String> infos = new Vector<String>();
                infos.add(first);
                myListener.TextViewClicked(3, infos);
            }

        });
        ImageView prev = (ImageView) mView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myListener.ImageViewClicked(2);
            }
        });

        selectImage = (ImageView) mView.findViewById(R.id.add_picture);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v)
            {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        return mView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTextViewListener4) {
            myListener = (onTextViewListener4) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectFragmentListener");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                picture = encodeTobase64(bm);
                if (imgDecodableString.endsWith(".png"))
                    picture = "data:image/png;base64," + picture;
                else
                    picture = "data:image/jpeg;base64," + picture;
                Log.d("TEST", picture);
            } else {
                Toast.makeText(getActivity(), "Vous n'avez pas choisi de photo de profil",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.d("TEST", e.getMessage());
            Toast.makeText(getActivity(), "Un probleme est survenue " , Toast.LENGTH_LONG)
                    .show();
        }
      }


    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        StringBuilder imageEncoded = new StringBuilder(15000);
        imageEncoded.append(Base64.encodeToString(b,Base64.DEFAULT));

        Log.e("LOOK", imageEncoded.toString());
        return imageEncoded.toString();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public interface onTextViewListener4{
        public void TextViewClicked(int nb, List infos);
        public void ImageViewClicked(int nb);
        public void showResult(String msg);
    }
}
