package com.zcc.frame.common;

/**
 * 常量管理
 * Created by zcc on 2017/6/12.
 */

public class ConstantManager {
    /**
     * 0为生产模式 ,其他为调试模式
     * 发版时改为0
     */
    public static  int RELEASE_MODE = 1;
    /** 值为0时关闭所有级别的日志打印 */
    public static int LOG_LEVEL = 5;
    /** ListView 初始数据 默认显示的个数 =20 */
    public static final int LISTVIEW_ITEM_SIZE = 20;
    static {
        if (RELEASE_MODE==0) {// 生产模式
            LOG_LEVEL = 0;// 关闭日志打印
        }
    }

}
