package com.gl.startactivityforresultwitharouter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.gl.startactivityforresultwitharouter.callback.ActivityResultCallback;
import com.gl.startactivityforresultwitharouter.callback.PermissionRequestCallback;
import com.gl.startactivityforresultwitharouter.util.RouteUtil;

import static android.app.Activity.RESULT_OK;

/**
 * @Author: gelan
 * @CreateDate: 2020/6/11 14:51
 * @Description: 处理 检查权限和 startActivityForResult 的回调的Fragment
 */
public class ProcessFragment extends Fragment {

    private Context mContext;
    private PermissionRequestCallback mPermissionCallback;
    private ActivityResultCallback mActivityResultCallback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    /**
     * 检查是否具有多个权限
     *
     * @param permissions
     * @return true 有权限 false无权限
     */
    private boolean checkPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (isAllGranted(permissions, grantResults)) {
            if (mPermissionCallback != null) {
                mPermissionCallback.onAgree();
            }
        }
        mPermissionCallback = null;
    }


    /**
     * 判断申请的权限有没有被允许
     */
    private boolean isAllGranted(String[] permissions, int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                //拒绝某项权限时候的回调
                mPermissionCallback.onRefused(permissions[i]);
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(String[] permissions, PermissionRequestCallback callback) {
        if (callback != null) {
            if (checkPermissions(permissions)) {
                callback.onAgree();
            } else {
                mPermissionCallback = callback;
                requestPermissions(permissions, 0);
            }
        }
    }

    public void startActivityForResult(Intent intent, ActivityResultCallback callback) {
        mActivityResultCallback = callback;
        super.startActivityForResult(intent, 0);
    }
    public void startActivityForResult(String path,Bundle bundle, ActivityResultCallback callback) {
        mActivityResultCallback = callback;
        RouteUtil.forwardActivity(this,path,bundle);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mActivityResultCallback != null) {
            if (resultCode == RESULT_OK) {
                mActivityResultCallback.onSuccess(data);
            } else {
                mActivityResultCallback.onFailure();
            }
        }
    }

    public void release(){
        mPermissionCallback=null;
        mActivityResultCallback=null;
    }
}
