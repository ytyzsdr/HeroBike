package com.danchexia.bikehero.utils;

import android.content.Context;

import com.danchexia.bikehero.R;

import vc.thinker.tools.clearcache.ClearCacheUtils;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.dialog.WaittingDialog;
import vc.thinker.tools.dialog.WaittingDialogHavetext;

/**
 * Created by farley on 17/5/15.
 * description:
 */

public class DialogUtils {
    public ClearCacheUtils.OnClearListener onClearListener;

    public interface OnMainClickListener {
        void onClick();
    }

    public static void standerdDialog1(final Context activity, String title, String content, final OnMainClickListener onClearListener) {
        StanderdDialog dialog = new StanderdDialog(activity, title, content, activity.getString(R.string.toast_17),
                activity.getString(R.string.toast_18)
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {

            }

            @Override
            public void doMainClick() {
                onClearListener.onClick();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    public static WaittingDialog dialog;
    public static WaittingDialogHavetext dialogText;
    public static void showWaitText(Context activity) {
        if (dialogText != null) {
            dialogText.dismiss();
        }
        dialogText = new WaittingDialogHavetext(activity,false);
        dialogText.show();
        dialogText.setCanceledOnTouchOutside(false);
    }
    public static void successWaitDialogText() {
        if (dialogText != null) {
            dialogText.setSuccess();
        }
    }
    public static void waittingDialogUtils(Context activity) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new WaittingDialog(activity,false);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(false);
    }
    public static void waittingDialogUtilsForBattery(Context activity) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new WaittingDialog(activity,true);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(false);
    }

    public static void successWaitDialog() {
        if (dialog != null) {
            dialog.setSuccess();
        }
    }
    public static void successWaitDialogNoOk() {
        if (dialog != null) {
            dialog.setSuccessNoOk();
        }
    }
}
