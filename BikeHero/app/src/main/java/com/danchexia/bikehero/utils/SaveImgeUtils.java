package com.danchexia.bikehero.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/8/15.
 * description:
 */

public class SaveImgeUtils implements Runnable {
    private String url;
    private Context context;
    public SaveImgeUtils(Context context, String url) {
        this.url = url;
        this.context = context;
    }
    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap != null){
                // 在这里执行图片保存方法
                saveImageToGallery(context,bitmap);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        File myFile = MyUtils.fileIsHave();
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        LogUtils.d("myFile="+ myFile.getAbsolutePath()+System.currentTimeMillis());
        String fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(myFile, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//
//        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                Uri.fromFile(new File(currentFile.getPath()))));
    }
}
