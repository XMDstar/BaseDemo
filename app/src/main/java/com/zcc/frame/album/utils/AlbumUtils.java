package com.zcc.frame.album.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import com.zcc.frame.album.bean.AlbumData;
import com.zcc.frame.album.bean.MaterialBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlbumUtils {
    private ContentResolver resolver;

    public AlbumUtils(ContentResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * 获取本地所有的视频
     *
     * @return list
     */
    private List<MaterialBean> getAllLocalVideos() {
        List<MaterialBean> list = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return list;
        }
        try {
            while (cursor.moveToNext()) {
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)); // 大小
                if (size < 300 * 1024 * 1024) {//<300M
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)); // 路径
                    long date = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN));
                    long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)); // 时长
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String dec = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                    String Sdate = DateUtil.formatDate(date);
                    String formatDur = formatTime(duration);
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    MaterialBean materialBean = new MaterialBean(path, Sdate, id, dec, name, 2, size, date, duration, formatDur);
                    list.add(materialBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return list;
    }

    /**
     * 获取本地所有的图片
     *
     * @return list
     */
    private List<MaterialBean> getAllLocalPhotos() {
        List<MaterialBean> list = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_TAKEN + " desc");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                long date = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN));
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String dec = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String Sdate = DateUtil.formatDate(date);
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                MaterialBean materialBean = new MaterialBean(path, Sdate, id, dec, name, 1, size, date, 0, null);
                list.add(materialBean);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public List<AlbumData> getFormatData(ArrayList<MaterialBean> beans) {
        ArrayList<AlbumData> datas = new ArrayList<>();
        ArrayList<MaterialBean> list = new ArrayList<>();
        List<String> photoDates = new ArrayList<>();
        String dateTag = "";
        for (int i = 0; i < beans.size(); i++) {
            if (!photoDates.contains(beans.get(i).getFormatDate())) {
                if (list != null && list.size() > 0) {
                    ArrayList<MaterialBean> mList = new ArrayList<>();
                    mList.addAll(list);
                    AlbumData data = new AlbumData();
                    data.setDate(dateTag);
                    data.setList(mList);
                    datas.add(data);
                    list.clear();
                }
                photoDates.add(beans.get(i).getFormatDate());
                dateTag = beans.get(i).getFormatDate();
            }
            if (dateTag.equals(beans.get(i).getFormatDate())) {
                if (i == 0) {
                    MaterialBean materialBean = new MaterialBean(null, null, 0, null, null, 3, 0, 0, 0, null);
                    list.add(materialBean);
                }
                list.add(beans.get(i));
            }
        }
        return datas;
    }

    public static Bitmap getThumbnail(String path) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        Bitmap bitmap = retriever.getFrameAtTime();
        retriever.release();
        return bitmap;
    }

    private String formatTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    public ArrayList<MaterialBean> getSortData() {
        ArrayList<MaterialBean> beans = new ArrayList<>();
        beans.addAll(getAllLocalPhotos());
        beans.addAll(getAllLocalVideos());
        Collections.sort(beans, new Comparator<MaterialBean>() {
            @Override
            public int compare(MaterialBean bean1, MaterialBean bean2) {
                return Long.valueOf(bean2.getDate()).compareTo(Long.valueOf(bean1.getDate()));
            }
        });
        return beans;
    }
}
