package com.example.books;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private static final int MAX_CHARACTERS_PER_SUBTITLE = 40;
    public BookAdapter(@NonNull Context context,  @NonNull List<Book> objects) {
        super(context, 0, objects);
    }
    private String getSubtitle(String subtitle){
        if(subtitle.length() >= MAX_CHARACTERS_PER_SUBTITLE){
            return  subtitle.substring(0, MAX_CHARACTERS_PER_SUBTITLE -1) + "...";
        }
        return  subtitle;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView =  LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }
        Book  currentBook = getItem(position);

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(currentBook.getTitle());

        TextView subtitle = listItemView.findViewById(R.id.subtitle);
        if(!TextUtils.isEmpty(currentBook.getSubTitle()))
        {
            subtitle.setText(getSubtitle(currentBook.getSubTitle()));
        }

        TextView publisher = listItemView.findViewById(R.id.publisher);
        publisher.setText(currentBook.getPublisher());

        TextView publishedDate  = listItemView.findViewById(R.id.publishedDate);
        publishedDate.setText(currentBook.getPublishedDate());

        return listItemView;
    }
}
