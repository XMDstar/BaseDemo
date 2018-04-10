package com.zcc.frame.tools;

import android.content.Context;

import java.io.File;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 鲁班压缩图片工具类
 * Created by zcc on 2018/4/10.
 */

public class Compress {
    private Context context;
    private Luban.Builder builder;

    private Compress() {
    }

    public static Compress getInstance(Context context) {
        return CompressInner.ins.setContext(context);
    }

    private Compress setContext(Context context) {
        this.context = context;
        builder = Luban.with(context)
                .ignoreBy(400)
                .setCompressListener(null)
                .setTargetDir(context.getCacheDir().getAbsolutePath());
        return this;
    }

    public void compress(String path, OnCompressListener listener) {
        builder.load(path).setCompressListener(listener).launch();
    }

    public void compress(File file, OnCompressListener listener) {
        builder.load(file).setCompressListener(listener).launch();
    }

    public void compress(List<String> paths, OnCompressListener listener) {
        builder.load(paths).setCompressListener(listener).launch();
    }


    protected static class CompressInner {
        static Compress ins = new Compress();
    }

}
