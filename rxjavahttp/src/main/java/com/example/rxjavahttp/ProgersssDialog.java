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
        if (Build.VERSION.SDK_INT>=28) {//8.0新特性 改了代码竟然不是别 卧槽
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        }else{
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        this.mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // cesh    是不是  要先把这里提交了啊   要不要全部删除了再来一遍 git的仓库也删除了
        //等我在看看   不行就删除了  重新绑定一下git 我试试   这里还没关联git呢
        




        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        setCancelable(false);
        img = (ImageView) findViewById(R.id.progress_dialog_img);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.loading_dialog_progressbar);
        img.setAnimation(anim);
    }

    public void showDia(){
        this.show();
    }

    public void dismissDia(){
        this.dismiss();
    }

}
