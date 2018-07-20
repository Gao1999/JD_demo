package com.bwie.jd_demo.mvp.shopping.model.bean;

import java.util.List;

public class OrdersBean {


    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-07-19T15:03:01","orderid":10987,"price":6666,"status":0,"title":"订单测试标题15314","uid":15314},{"createtime":"2018-07-19T15:03:17","orderid":10989,"price":111.99,"status":0,"title":"订单测试标题15314","uid":15314},{"createtime":"2018-07-19T15:03:24","orderid":10990,"price":111.99,"status":0,"title":"订单测试标题15314","uid":15314},{"createtime":"2018-07-19T15:03:59","orderid":10992,"price":111.99,"status":0,"title":"订单测试标题15314","uid":15314},{"createtime":"2018-07-19T15:05:34","orderid":10995,"price":6777.99,"status":0,"title":"订单测试标题15314","uid":15314},{"createtime":"2018-07-19T15:05:57","orderid":10996,"price":6777.99,"status":0,"title":"订单测试标题15314","uid":15314}]
     * page : 2
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-07-19T15:03:01
         * orderid : 10987
         * price : 6666
         * status : 0
         * title : 订单测试标题15314
         * uid : 15314
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
