package com.zcc.frame.common;

public class UrlConstant {
    /**
     * 挡板
     */
    public static final String HOST_HTTP_TEST = "http://192.168.199.193:8010/api";
    /**
     * 生产环境
     */
    public static final String HOST_HTTP_RELEASE = "http://www.wemem.com/mapi/";
    /**
     * 接口主机
     */
    public static String HOST;

    static {
        switch (ConstantManager.RELEASE_MODE) {
            case 0:// 生产环境
                HOST = HOST_HTTP_RELEASE;
                break;
            case 1:// 测试环境
                HOST = HOST_HTTP_TEST;
                break;
            default:
                HOST = HOST_HTTP_RELEASE;
                break;
        }
    }

    public static final String GET_API_REQUEST = HOST + "getapi.php";

}
