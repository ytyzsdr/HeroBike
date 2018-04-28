package vc.thinker.tools.clearcache;

import android.content.Context;

import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.ShowToast;


/**
 * Created by farley on 17/3/10.
 * description:
 */

public class ClearCacheUtils {
    public OnClearListener onClearListener;
    public interface OnClearListener {
        void clear();
    }
    public static void clearCache(final Context activity, final OnClearListener onClearListener){
        StanderdDialog dialog = new StanderdDialog(activity, "清除缓存", "您确定清除缓存么？", "确定", "取消"
                , new StanderdDialog.OnDialogClickListener() {
            @Override
            public void doAnyClick() {

            }

            @Override
            public void doMainClick() {
                DataCleanManager.clearAllCache(activity);
                ShowToast.show(activity,"清除成功");
                onClearListener.clear();

//                Glide.get(activity).clearMemory();//清理内存缓存  可以在UI主线程中进行
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

}
