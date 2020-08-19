package cn.bdqn.controller;

import cn.bdqn.config.AlipayConfig;
import cn.bdqn.service.PayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("WIDout_trade_no") != null) {
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(out_trade_no + "-------------" + subject);
            // 付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
            // 商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
            // 超时时间 可空
            String timeout_express = "2m";
            // 销售产品码 必填
            String product_code = "QUICK_WAP_PAY";
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

            // 封装请求支付信息
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);
            // form表单生产
            String form = "";
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @RequestMapping("/mynotifyurl")
    public void mynotifyurl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("异步 方法");
        PrintWriter out = response.getWriter();
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("状态----:"+trade_status);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            out.println("success");    //请不要修改或删除

            //////////////////////////////////////////////////////////////////////////////////////////
        } else {//验证失败
            out.println("fail");
        }
    }
    //http://localhost:15001/myreturnurl?charset=UTF-8&out_trade_no=121915167439851520&method=alipay.trade.wap.pay.return&total_amount=10.00&sign=KoAglkSmCvfW4%2BInsz2K%2B2HjKjCOcqqKoauVfB2tA7e0%2BpIql1vEifYCEzvRWGw3poXs0sV%2Byaa2A%2FHbD9h6KnXsk0jz5%2Bb1vWAjw6cFYQU9l4zrF9%2BYsOrKdY9WBqeQ%2Fg953WZSygInXwulIkbaph7%2FARJ%2BXk37QE8yUlrfm1YVCty75SwpEViixa9g2UmlKNhOsRN%2B4Ps%2FWwlCvVys8q1WV%2FW2ADMJdPEqZBz%2FpC%2BymuTUoUx%2BaW58Uo16yX%2BRcI9EVlLst4Y1CCoQ4cv%2FPHkCaz%2BK1%2F555dKQvMGK01fCD1Fm%2FzNXQbjLf%2FJoiqxYjeCG43043r80ZUzXXSv4ZA%3D%3D&trade_no=2020072122001409770501047991&auth_app_id=2016091500515925&version=1.0&app_id=2016091500515925&sign_type=RSA2&seller_id=2088102175719850&timestamp=2020-07-21+09%3A46%3A49
    @RequestMapping("/myreturnurl")
    public void myreturnulr(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("同步方法");
        PrintWriter out = response.getWriter();
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");


        if (!verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            out.println("out_trade_no:" + out_trade_no + ",trade_no" + trade_no);
//            payService.sendOrder(out_trade_no);
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            //response.sendRedirect("http://127.0.0.1:8080/xxx.html");
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑
            out.println("error");
        }
    }

}
