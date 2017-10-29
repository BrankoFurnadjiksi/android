package com.example.branko.lab_intents;

import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

/**
 * Created by Branko on 25.10.2017.
 */

public class Application implements Comparable<Application>
{
    public String name;
    public ResolveInfo info;

    public Application(String name, ResolveInfo info) {
        this.name = name;
        this.info = info;
    }

    public ResolveInfo getInfo() {
    return info;
}

    public void setInfo(ResolveInfo info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void compare(String name) {

    }

    public String getPackageName(){
        return info.activityInfo.packageName;
    }


    @Override
    public int compareTo(@NonNull Application o) {
        return this.name.compareTo(o.getName());
    }
}
