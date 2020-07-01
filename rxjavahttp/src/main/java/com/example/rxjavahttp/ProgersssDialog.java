package com.example.rxjavahttp;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class ProgersssDialog extends Dialog {
    private ImageView img;
    private Context mContext;

    public ProgersssDialog(Context context) {
        super(context, R.style.MyHttpProgressDiaLog);
        if (Build.VERSION.SDK_INT >= 26) {
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else {
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        setCancelable(false);
        img = (ImageView) findViewById(R.id.progress_dialog_img);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.loading_dialog_progressbar);
        img.setAnimation(anim);
    }

    public void showDia() {
        this.show();
    }

    public void dismissDia() {
        this.dismiss();
    }

}
