package com.http.okhttp.builder;

import com.http.okhttp.OkHttpUtils;
import com.http.okhttp.request.OtherRequest;
import com.http.okhttp.request.RequestCall;

/**
 *
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
