package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.appschool.bagrutproject.R;

import java.util.List;

/**
 * Created by elili on 2/4/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {
    Context context;
    List<User> objects;

    public UserAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<User> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.contact_layout,parent, false);
        TextView user = view.findViewById(R.id.tvUser);
        TextView name = view.findViewById(R.id.tvName);
        User temp = objects.get(position);

        user.setText(String.valueOf(temp.getUsername()));
        name.setText(String.valueOf(temp.getFirstname()));
        return view;
    }
}
