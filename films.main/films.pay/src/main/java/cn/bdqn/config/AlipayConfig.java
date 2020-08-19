package cn.bdqn.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016091500515925";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWQU2O65d2+bT67zbOv96y60oDT6DdVLKsatlghJOiE8ZvGGnABPf8TNMl4cCVoKcZVLtAvS4tTb06EfLabELq4FUJZ9iM3760FqyatLAY+lcocopmuCYEF0LIiRnh6P2SI0hMBOHPKCJpPSLSda4tAd2r1p39ovbGIMPSWxSkHaZkYqw0E7YwprlZdsn6seoIb/iqC+mOR+/xw46+VpdU1GxQaFk76sjxxF5sLTGDAfsv5jgoDD2QAFCAQd1DD7RFuqVcrZ1T62lAxkr1QOBQ+TvoPbZXkttilaIov79/XfD7AlKGvF0vVAxRg4R3Hc6ZI3RQguEavEHNITpdSl+7AgMBAAECggEABTA2FGRQM5yFwlB2TQdVEkqLoLekL2BYVxnNbcbGLkeOh5GJPUcupPimJd24v7HvczF40dB2grv3d8Tc5OvuF5wtc4ixcpPbSu6pgSISHpquXcguvUNIlMWU2cXaLfHUp7IuMenNPlD9dzLUO/4ySH2TAPcFN5fj+41wFZhtiwkpNDaHd45nJYKhISFClQsIMP0sNQFIDDea4xXf2j7C7Ux69jbrnxSPHRei2T1iz7+Coj8c+BAtxWv72fk78mtF3yqJpseMJ+NHmDEXH6dBOUa0a5Ntu40ZdLYgDmOhOqgOgdsNN0p/dD2dFOUbhk0vnyyW+R0IHlmVrRfjnaGKiQKBgQDzXg0QGSeL1JYrCVTeYgF8jwDpcQbenCUU6hO4PKHf09Od/4MCTHyeavxRNioVOdaCT9SvcIsbpjqo1OykD4lSfmNQBzOtvFgcHzitUD8l2hITD9LYxTWAgWsb3sqnEN59NyTY/3MmTBUrFj0DXT7cJCukQURwUmnohSovT5VjHwKBgQCeDfGk60/cij6jYZ9oVo4f3mmbegLGORqy5+0LjGId395GCTA/EOUJpZRcTH9QpatCYH441MlwA5eVHymRBl0PCQUc2VtJXQp6x6e2OLRAkdWo3sT9lpG7DnaR/uDjrvJBNWDquhxyMb8DVmdgP6/laCk7OMKLGOZP7aDDKS2r5QKBgEUiDYHqM2K0dCgrkXFO0+9HTFIEKphoMNx9Fjb6+lzz9qRmZhfsFGaIK78CLPg0ouwHs3pW9R1YnkdBnSuJwk+t2ZZtRU/wNfNfJyGMrzOYfBPwJcwwUV985ClZo6XL0/gsBwqhnltdt5Gy0og3H9k3aTJS3BcwQiesVGx2JhCvAoGASslKR8DYPwBi9yfgJ5ydD1NMITcwTN8EVRcMwOQiIQSmxfjfDVhPJ6tjcfD4ZQ6e5H+H+hglYI0ykmr2XhrmuZCJDMeEPEh8BvCl2MEn+U2IDamrCFrQhfQoApjZjKJhGp3JSAfcIeSL/2ZI7+/yqNgKem03EF4tzDAiW3YGmEkCgYEAyjTrwGPmPDLrQ0tH4OcGnZaPKr9Fy9ctw7HBC7xcH+VnHa92bBEy1lc+JaPNKyNR91LJngZA3ooow+Ub8ThIqX7U01ca9QTIwaPTRMwAera0O+jy4dht5PIsRcTX6L1U2KcJXQB5QWy0uxkkyaXOMFtbaTn9yaOfe88614j2yGs=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:15001/mynotifyurl";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:15001/myreturnurl";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn/Rd/8JXzHP9UTbpdqB4UTzeFZTniTb2JFq7mdvERNKXglMMkHABA+MfKTh1Rd98KkKQ8iDTAgPgFuKnCQ6NRycNcuImOrq/KUZlAfD+EUFj/Tl7P0Q7f3BclmK1VcmhV3NJQkJZIUaTSU5tQJWOpb8jUBwFYOqD+783UExwwKLz3UwM4SGNlH0JM0IGvYcRN/Lca8da//Rttd9o2By7K0wQi0U5ujrNFcxBcT8OJykcTcKpJFO6cfNAB67m0VHWIJMiYdR26zrEB7b5qSdY2YU6IEoTpSjNfS5PnP4Ij2bERE4dgXIqYWDbpLxzIIDpnzvaG2236Qd83848Y1BRPwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
