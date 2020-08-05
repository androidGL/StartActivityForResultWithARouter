package com.gl.startactivityforresultwitharouter.util;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gl.startactivityforresultwitharouter.ProcessFragment;
import com.gl.startactivityforresultwitharouter.callback.ActivityResultCallback;
import com.gl.startactivityforresultwitharouter.callback.PermissionRequestCallback;

/**
 * @Author: gelan
 * @CreateDate: 2020/6/11 14:51
 * @Description: 处理跳转页面返回结果的工具类
 */
public class ActivityResultUtil {

    protected ProcessFragment mFragment;


    public ActivityResultUtil(FragmentActivity activity) {
        mFragment = new ProcessFragment();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.add(mFragment, "ProcessFragment").commit();
        //commit()方法并不立即执行transaction中包含的动作,而是把它加入到UI线程队列中.
        //如果想要立即执行,可以在commit之后立即调用FragmentManager的executePendingTransactions()方法.
        fragmentManager.executePendingTransactions();
    }

    /**
     * 请求权限
     * @param permissions
     * @param callback 请求权限返回结果
     */
    public void requestPermissions(String[] permissions, PermissionRequestCallback callback) {
        mFragment.requestPermissions(permissions, callback);
    }

    public void startActivityForResult(Intent intent, ActivityResultCallback callback){
        mFragment.startActivityForResult(intent,callback);
    }

    public void startActivityForResult(String path, ActivityResultCallback callback){
        this.startActivityForResult(path,null,callback);
    }
    public void startActivityForResult(String path, Bundle bundle, ActivityResultCallback callback){
        mFragment.startActivityForResult(path,bundle,callback);
    }

    public void release(){
        if(mFragment!=null){
            mFragment.release();
        }
        mFragment = null;
    }

}
