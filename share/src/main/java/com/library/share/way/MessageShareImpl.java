package com.library.share.way;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.library.share.info.IShareInfo;

/**
 *
 */
public class MessageShareImpl extends BaseShareWay {

    public MessageShareImpl(Activity activity, int resIcon, String title) {
        super(activity, resIcon, title);
    }

    @Override
    public void onShare(IShareInfo shareInfo) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.putExtra("address", "");
            intent.putExtra("sms_body", shareInfo.getShareContent());
            intent.setType("vnd.android-dir/mms-sms");
            mActivity.startActivityForResult(intent,1);
        } catch (Exception e) {
            Uri uri = Uri.parse("smsto:" + "");
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", shareInfo.getShareContent());
            mActivity.startActivityForResult(it,1);
        }
    }
}
