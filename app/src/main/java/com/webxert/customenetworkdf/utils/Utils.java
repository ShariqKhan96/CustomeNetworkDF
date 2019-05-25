package com.webxert.customenetworkdf.utils;

import android.content.Context;
import android.support.annotation.Nullable;

import com.kaopiz.kprogresshud.KProgressHUD;

public class Utils {
    public static KProgressHUD progressDialog(final Context ctx, @Nullable String _title, String _message/*, @DrawableRes @Nullable int icon*/) {


        KProgressHUD pd = KProgressHUD.create(ctx)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(_message)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        return pd;

    }

}
