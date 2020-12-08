package com.zcc.frame.bugly;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BuglyToTingTing {
    static String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=9f0000d810cd5d0ae75135fa081a94bd98b1f547a7015261ab71de9a48f775fe";
    static OkHttpClient client;
    static String cookie=" bugly_session=eyJpdiI6IktPVHNHcU5zeTZ2Y1NOeFwva1o1dFpBPT0iLCJ2YWx1ZSI6IjA4SGpBVEFPRUdqazRyc3dvaUtVa2Q2OXozMzlwc1p1a3dPNkk4XC9ycnNnTDNMeis0MmZLMnltbmRtVUZNS2ZrMnZFMHpsSDQ5bkVXeW1iM3NwZFl6UT09IiwibWFjIjoiYzU4NTMyZWU3ZmZmODdkYWZiM2Y4MjA5YWZjYmVkYWZiYjdkYzdiMWZlNzQ1NGNlNTc5MWY3NWU4MmE5NzgyNSJ9; referrer=eyJpdiI6IkJSXC9SVVVcL0grOEVGWlhCVU9MUUZDUT09IiwidmFsdWUiOiJ2VEUzMVBSS3h0TTJqUDBsSlZjOEpPbkRPN21TQlpLXC82VkZObUVoZjZ3M3BzcFd5Smdzc3J1RFFmT05RWTU3NEtnblpxWTJcL3UyNnIrR29wdTlhSldPK0VRSUtySEl0Ylk1Z010Z0RMK056SHNlYUJ0akw4cGFJR1hqUmRFbTNCY0F2dGc2TE5yZjBNOXR3ejd6RlwvU1dXaXMzRFZLc1ArWFRYdkt5RUFtSkU9IiwibWFjIjoiMWNlNTEyMmZmY2Y4NjU4MDQyOGY2YWQyNGNlMzA2MjUwNDA1MDhhNTc0ZGI5YmVhY2YxYTQwZGFjMzgxODMxNyJ9; _gid=GA1.2.723868825.1557021760; token-lifeTime=1557125741; token-skey=e89d8c4c-e267-c127-bc87-1052e4b6bd4a; csrfToken=JJQgDm5zOC6BHzGbBxc6RT13; NODINX_SESS=yEB0svcpq7SKhlWxUOY15m1qmzPP7xpncm7Jyw_69gymqCBY6BgetztMTwoN9d7Y; _gat=1; ptcz=fb07c8e962f7d24b25417aa30fe199cbe877b7358ecaab8cf54dedd9a084646f; ptisp=cnc; pgv_si=s5266797568; o_cookie=3026927488; pgv_pvid=3016843756; pac_uid=1_3026927488; vc=vc-aedbb893-6987-48c1-902a-07f02b2eb239; vc.sig=KqMdAqVs6bjjrkS4foH1D-BoMHBaHi_VaRkIMS4Pnmg; btcu_id=2814497c-127b-4d14-a6ab-c1a26cb3db05; vc=vc-54cd9eba-2e56-4a7f-9fee-e66df58ef506; btcu_id=d20fefd45e8e861c562af366cbf891b55bcf08da56189; RK=jQYF6lvxlN; pgv_pvi=5475609600; _ga=GA1.2.115038633.1540294873";
    public static void main(String[] args) {
        client = new OkHttpClient();
        buglyToTing();
    }

    private static void buglyToTing() {
        final Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(getUrl()).addHeader("Accept", "application/json;charset=utf-8")
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .addHeader("Accept-Encoding", "br, gzip, deflate")
                .addHeader("Host", "bugly.qq.com")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15")
                .addHeader("Accept-Language", "zh-cn")
                .addHeader("Referer", "https://bugly.qq.com/v2/crash-reporting/dashboard/900008888?pid=1&isRealTime=0&startDate1=20190214&endDate1=20190220&date1=last_7_day")
                .addHeader("Connection", "keep-alive")
                .addHeader("x-csrf-token:", "oWiQyd3xQvBa8E-MARhxYPWr")
                .addHeader("X-token", "605481358")
                .addHeader("Cookie", cookie).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败调用
                System.out.println("失败调用");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //成功调用
                //获取网络访问返回的字符串
                String str = response.body().string();
                System.out.println(str);
                if (str != null && !str.equals("")) {
                    try {
                        Gson gson = new Gson();
                        BugluBean bean = gson.fromJson(str, BugluBean.class);
                        if (bean == null || bean.status != 200 || bean.ret == null || bean.ret.data == null || bean.ret.data.size() == 0) {
                            return;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(bean.ret.data.get(6).date);
                        stringBuilder.insert(4, "年");
                        stringBuilder.insert(7, "月");
                        stringBuilder.insert(10, "日");
                        System.out.println(stringBuilder.toString());
                        //+ "      用户崩溃率：" + String.format("%.2f", (bean.ret.data.get(6).crashUser / bean.ret.data.get(6).accessUser) * 100) + "%"
                        DingBean dingBean = new DingBean("text", new TextBean(stringBuilder.toString() + "   Android  次数崩溃率：" + String.format("%.2f", (bean.ret.data.get(6).crashNum / bean.ret.data.get(6).accessNum) * 100) + "%"), new AtBean(false, null));
                        toDing(gson.toJson(dingBean));
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private static String getUrl() {
        return "https://bugly.qq.com/v2/getTrend/appId/900008888/platformId/1/version/-1/startDate/20190429/endDate/20190505/type/crash?dataType=trendData&fsn=11e59f18-2170-41cb-a489-00cf1d197ebe";
    }

    private static void toDing(String json) {
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder().url(dingUrl).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() >= 200 && response.code() < 300) {
                    System.out.println(response.body().string());
                }
            }
        });
    }

    class BugluBean {

        /**
         * status : 200
         * msg : ok
         * ret : {"data":[{"appId":"900008888","platformId":1,"version":"-1","date":"20190214","type":"crash","crashNum":1238,"crashUser":215,"accessNum":894798,"accessUser":21655},{"appId":"900008888","platformId":1,"version":"-1","date":"20190215","type":"crash","crashNum":1556,"crashUser":249,"accessNum":821248,"accessUser":21940},{"appId":"900008888","platformId":1,"version":"-1","date":"20190216","type":"crash","crashNum":1104,"crashUser":232,"accessNum":755064,"accessUser":24155},{"appId":"900008888","platformId":1,"version":"-1","date":"20190217","type":"crash","crashNum":995,"crashUser":252,"accessNum":674203,"accessUser":27132},{"appId":"900008888","platformId":1,"version":"-1","date":"20190218","type":"crash","crashNum":1425,"crashUser":260,"accessNum":573232,"accessUser":24337},{"appId":"900008888","platformId":1,"version":"-1","date":"20190219","type":"crash","crashNum":1448,"crashUser":247,"accessNum":546466,"accessUser":24718},{"appId":"900008888","platformId":1,"version":"-1","date":"20190220","type":"crash","crashNum":1262,"crashUser":254,"accessNum":495941,"accessUser":23213}],"code":200}
         */

        public int status;
        public String msg;
        public RetBean ret;

        public class RetBean {
            /**
             * data : [{"appId":"900008888","platformId":1,"version":"-1","date":"20190214","type":"crash","crashNum":1238,"crashUser":215,"accessNum":894798,"accessUser":21655},{"appId":"900008888","platformId":1,"version":"-1","date":"20190215","type":"crash","crashNum":1556,"crashUser":249,"accessNum":821248,"accessUser":21940},{"appId":"900008888","platformId":1,"version":"-1","date":"20190216","type":"crash","crashNum":1104,"crashUser":232,"accessNum":755064,"accessUser":24155},{"appId":"900008888","platformId":1,"version":"-1","date":"20190217","type":"crash","crashNum":995,"crashUser":252,"accessNum":674203,"accessUser":27132},{"appId":"900008888","platformId":1,"version":"-1","date":"20190218","type":"crash","crashNum":1425,"crashUser":260,"accessNum":573232,"accessUser":24337},{"appId":"900008888","platformId":1,"version":"-1","date":"20190219","type":"crash","crashNum":1448,"crashUser":247,"accessNum":546466,"accessUser":24718},{"appId":"900008888","platformId":1,"version":"-1","date":"20190220","type":"crash","crashNum":1262,"crashUser":254,"accessNum":495941,"accessUser":23213}]
             * code : 200
             */

            public int code;
            public List<DataBean> data;

            public class DataBean {
                /**
                 * appId : 900008888
                 * platformId : 1
                 * version : -1
                 * date : 20190214
                 * type : crash
                 * crashNum : 1238
                 * crashUser : 215
                 * accessNum : 894798
                 * accessUser : 21655
                 */

                public String appId;
                public int platformId;
                public String version;
                public String date;
                public String type;
                public double crashNum;
                public double crashUser;
                public double accessNum;
                public double accessUser;

            }
        }
    }

    static class DingBean {
        /**
         * msgtype : text
         * text : {"content":"我就是我,  @1825718XXXX 是不一样的烟火"}
         * at : {"atMobiles":["1825718XXXX"],"isAtAll":false}
         */

        public String msgtype;
        public TextBean text;
        public AtBean at;

        public DingBean(String msgtype, TextBean text, AtBean at) {
            this.msgtype = msgtype;
            this.text = text;
            this.at = at;
        }
    }

    public static class TextBean {
        /**
         * content : 我就是我,  @1825718XXXX 是不一样的烟火
         */

        public String content;

        public TextBean(String content) {
            this.content = content;
        }
    }

    public static class AtBean {
        /**
         * atMobiles : ["1825718XXXX"]
         * isAtAll : false
         */

        public boolean isAtAll;
        public List<String> atMobiles;

        public AtBean(boolean isAtAll, List<String> atMobiles) {
            this.isAtAll = isAtAll;
            this.atMobiles = atMobiles;
        }
    }
}