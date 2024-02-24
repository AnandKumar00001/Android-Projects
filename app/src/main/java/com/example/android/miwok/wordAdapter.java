package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<Word> {
    @NonNull
    private final int mColorResourceID;
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
//        convertView represents the recycler view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView defaultTextView = listItemView.findViewById(R.id.default_text);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView2 = listItemView.findViewById(R.id.miwok_text);
        miwokTextView2.setText(currentWord.getMiwokTranslation());
        ImageView imageView = listItemView.findViewById(R.id.image_View);


        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        View textViewContainer = listItemView.findViewById(R.id.textContainer);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        textViewContainer.setBackgroundColor(color);

        return listItemView;
    }

    public wordAdapter(Activity context, ArrayList<Word> list , int colorResourceID) {

        super(context, 0, list);
        mColorResourceID = colorResourceID;
    }
}
