package com.hxg.lib_hrouter.contextprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.hxg.lib_hrouter.HRouter;

public class HRouterAppContentProvider extends ContentProvider {
    static Context mContext;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        //初始化全局Context提供者
        HRouter.getInstance().init(getContext().getApplicationContext());
        HRouterContextProvider.get();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
