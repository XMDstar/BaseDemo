package com.zcc.frame.tools;


public class ImageLoaderUtil {
//    private static final int MEMORY_CACHE_SIZE = 2 * 1024;
//    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
//
//    /**
//     * 加载网络图片，不处理
//     */
//    private static final DisplayImageOptions OPTIONS_NORMAL = new DisplayImageOptions.Builder()
//            .cacheOnDisk(true).cacheInMemory(true)
//            .imageScaleType(ImageScaleType.EXACTLY)
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            .considerExifParams(true)
//            .build();
//
//
//    /**
//     * 加载本地图片，不缓存，不存储
//     */
//    private static final DisplayImageOptions PHOTO_SELECT = new DisplayImageOptions.Builder()
//            .cacheOnDisk(false).cacheInMemory(false)
//            .imageScaleType(ImageScaleType.EXACTLY)
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            .considerExifParams(true)
//            .build();
//
//    /**
//     * 加载圆形图片，圆形边框为白色，边框宽度为5
//     */
//    private static final DisplayImageOptions OPTIONS_CIRCLE = new DisplayImageOptions.Builder()
//            .cacheOnDisk(true).cacheInMemory(true)
//            .imageScaleType(ImageScaleType.EXACTLY)
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
//            .considerExifParams(true)
//            .build();
//
//    /**
//     * 加载圆角图片，圆角弧度为8
//     */
//    private static final DisplayImageOptions OPTIONS_ROUND_RADIUS = new DisplayImageOptions.Builder()
//            .cacheOnDisk(true).cacheInMemory(true)
//            .imageScaleType(ImageScaleType.EXACTLY)
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            .displayer(new RoundedBitmapDisplayer(20))
//            .considerExifParams(true)
//            .build();
//
//    private static ImageLoader instance;
//
//    /**
//     * 获取缓存的图片路径
//     *
//     * @return
//     */
////    public static ImageLoader getImageLoader() {
////        if (instance == null) {
////            init(MyApplication.GetApplication());
////        }
////        return instance;
////    }
//
//    private static void init(Context appContext) {
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                appContext)
//                .threadPoolSize(3)
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .denyCacheImageMultipleSizesInMemory()
//                .imageDownloader(
//                        new BaseImageDownloader(appContext, 10000, 10000))
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .diskCacheSize(DISK_CACHE_SIZE)
//                .memoryCache(new WeakMemoryCache())
//                .memoryCacheSize(MEMORY_CACHE_SIZE).build();
//        ImageLoader.getInstance().init(config);
//        instance = ImageLoader.getInstance();
//    }
//
//    public static void display(String url, ImageView imageView,
//                               DisplayImageOptions options) {
//        if (instance == null) {
//            init(BaseApplication.getInstance());
//        }
//        instance.displayImage(url, imageView, options);
//    }
//
//    /**
//     * 获取普通不处理图片
//     *
//     * @param url
//     * @param imageView
//     */
//    public static void displayNormal(String url, ImageView imageView) {
//        display(url, imageView, OPTIONS_NORMAL);
//    }
//
//    /**
//     * 获取相册照片
//     *
//     * @param url
//     * @param imageView
//     */
//    public static void displayPhoto(String url, ImageView imageView) {
//        display(url, imageView, PHOTO_SELECT);
//    }
//
//    /**
//     * 获取圆形图片
//     *
//     * @param url
//     * @param imageView
//     */
//    public static void displayCircle(String url, ImageView imageView) {
//        display(url, imageView, OPTIONS_CIRCLE);
//    }
//
//    /**
//     * 获取圆角图片
//     *
//     * @param url
//     * @param imageView
//     */
//    public static void displayRoundRadius(String url, ImageView imageView) {
//        display(url, imageView, OPTIONS_ROUND_RADIUS);
//    }
//
//    /**
//     * 清除缓存
//     */
//    public static void clearCache() {
//        if (instance != null) {
//            instance.clearMemoryCache();
//            instance.clearDiskCache();
//        }
//    }
//
//    /**
//     * 获取url缓存的本地图片路径
//     *
//     * @return
//     */
////    public static File getImageLoaderFile(String url) {
////        if (instance == null) {
////            init(MyApplication.GetApplication());
////        }
////        File file = instance.getDiskCache().get(url);
////        return file;
////    }


}
