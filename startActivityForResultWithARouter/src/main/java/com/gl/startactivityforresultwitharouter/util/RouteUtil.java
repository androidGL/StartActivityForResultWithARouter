package com.gl.startactivityforresultwitharouter.util;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;


/**
 * @Author: gelan
 * @CreateDate: 2020/7/7 18:24
 * @Description: 路由跳转
 */
public class RouteUtil {
    public static boolean isLogin = true;
    public static void forwardActivity(String path) {
        forwardActivity(path,null);
    }
    public static void forwardActivity(String path,Bundle bundle) {
        forwardActivity(null,path,bundle);
    }

    public static void forwardActivity(Fragment context,String path,Bundle bundle) {
        if(context != null) {
            Postcard postcard = ARouter.getInstance().build(path);
            LogisticsCenter.completion(postcard);
            Intent intent = new Intent(context.getActivity(), postcard.getDestination());
            intent.putExtras(postcard.getExtras());
            if(null != bundle)
                intent.putExtras(bundle);
            context.startActivityForResult(intent, 100);
        }else {
            ARouter.getInstance().build(path).with(bundle)
                    .navigation();
        }
    }

}
