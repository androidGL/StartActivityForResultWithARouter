package com.gl.startactivityforresultwitharouter.util;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * @Author: gelan
 * @CreateDate: 2020/8/5 16:38
 * @Description:
 */

@Interceptor(name = "login",priority = 1)
public class LoginInterceptorImpl implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

    }

    @Override
    public void init(Context context) {

    }
}
