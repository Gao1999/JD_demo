package com.bwie.jd_demo.utils;

import android.content.Context;

import com.bwie.jd_demo.database.DaoMaster;
import com.bwie.jd_demo.database.DaoSession;


public class DaoManager {
    private static DaoManager daoManager;
    private final DaoSession daoSession;

    private DaoManager(Context context) {
        daoSession = DaoMaster.newDevSession(context, "my.db");
    }

    public static DaoManager instance(Context context) {
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                if (daoManager == null) {
                    daoManager = new DaoManager(context);
                }
            }
        }
        return daoManager;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
