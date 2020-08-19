package cn.bdqn.util;

public class Constants {

    public static class ErrorCode{
        public static String success="0000"; //
        public static String noAuth="0001"; //
        public static String exception="0002"; //
    }

    public static class User_AUTH {
        public final static String AUTH_UNKNOWN = "1001";
        public final static String AUTH_AUTHENTICATION_FAILED = "1002";//认证失败
        public final static String AUTH_PARAMETER_ERROR = "1003";//用户名密码参数错误，为空
        public final static String AUTH_TOKEN_INVALID = "1004";//token无效
    }

    public static class Redis_Expire {
        public static final long DEFAULT_EXPIRE = 60;//60s 有慢sql，超时时间设置长一点
        public final static int SESSION_TIMEOUT = 2 * 60 * 60;//默认2h
    }

    public static class MessageStatus {
        public final static Integer OVER = -1;//已结抢过
        public static final Integer SUCCESS = 1;//成功抢到
        public final static Integer FAIL = 0;//没抢到
    }

    public static class OrderStatus {
        public final static Integer TOPAY = 0;//待支付
        public static final Integer SUCCESS = 1;//支付成功
        public final static Integer FAIL = 2;//支付失败
    }

    public static class QueueName {
        public final static String TO_QG_QUEUE = "toQgQueue";
        public final static String TO_CREATE_ORDER = "toCreateOrder";

        public final static String TO_UPDATED_ORDER_QUEUE = "toUpdateOrderQueue";

        public final static String TO_UPDATED_GOODS_QUQUE = "toUpdateGoodsQueue";
    }

    public static class QgStatus {
        public final static String fail = "-1";//拿到商品
        public final static String getGoods = "1";//拿到商品
        public final static String getOrder = "2";//生成订单
        public final static String paySuccess = "3"; //支付成功
    }

    public static class WxpayStatus {
        public final static String DDZTYC = "4001";
        public final static String DDZFYC = "4002";
        public final static String DDYXYC = "4003";
    }

    //状态(0:锁定库存,1:成功支付,2:支付超时)

    public static class StockStatus {
        public final static Integer lock = 0;//锁定库存
        public final static Integer success = 1;//支付成功
        public final static Integer cancel = 2;//支付超时
    }
}
