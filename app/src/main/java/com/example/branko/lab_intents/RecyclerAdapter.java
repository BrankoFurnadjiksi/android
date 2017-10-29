package com.example.branko.lab_intents;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.packageNames;
import static android.R.attr.start;

/**
 * Created by Branko on 22.10.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    Application[] applications;

    public RecyclerAdapter(List<Application> applications) {
        this.applications = new Application[applications.size()];
        int i = 0;
        for(Application app : applications) {
            this.applications[i] = app;
            i++;
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.packageName = applications[position].getPackageName();
        holder.text_app.setText(applications[position].getName());

    }

    @Override
    public int getItemCount() {
        return applications.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_app;
        String packageName;
        Context context;

        public RecyclerViewHolder(View view) {
            super(view);
            context = view.getContext();
            text_app = (TextView) view.findViewById(R.id.app_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                context.startActivity(launchIntent);
            } else {
                Toast.makeText(context, "Package not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
