package com.anteoy.controller;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class AccessController {

    private static String TOKEN = "zhoudazhuanganteoylxj";
    /*@Autowired
    private MappingJacksonJsonView mappingJacksonJsonView;*/

	/*@Autowired
    private  AccessMapper accessMapper;
	*/

    /**
     * 微信事件调用本地服务入口
     *
     * @param request
     * @param response
     */
    @RequestMapping("access")
    public void  access(HttpServletRequest request, HttpServletResponse response) {

        boolean isGet = request.getMethod().toLowerCase().equals("get");
        System.out.println("请求类型:" + request.getMethod());
        String echostr = request.getParameter("echostr"); // 随机字符串

        if (isGet) {
			/*
			 * 通过检验signature对请求进行校验。
			 * 若确认此次GET请求来自微信服务器，
			 * 请原样返回echostr参数内容，
			 * 则接入生效，成为开发者成功，
			 * 否则接入失败。 */
            String signature = request.getParameter("signature"); // 验证码
            String timestamp = request.getParameter("timestamp"); // 时间戳
            String nonce = request.getParameter("nonce"); // 随机数


            List<String> params = new ArrayList<String>();
            params.add(TOKEN);
            params.add(timestamp);
            params.add(nonce);

            Collections.sort(params); // 排序, 默认顺序
            String text = params.toString().replaceAll(" |\\[|\\]|,", ""); // 拼装成一个字符串
            String local_signature = DigestUtils.sha512Hex(text); // sha1计算
			
			/* 如果相等, 说明确实是微信服务器发起不是伪造请求, 通过返回原样随机字符串 */
            if (signature.equals(local_signature)) {
                System.out.println("=============");
            }
            try {
                response.getWriter().write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           /* try {
                HashMap<String, String> map = ParseXml.parseXml(request);
                for (String key : map.keySet()) {
                    System.out.println(key + "=" + map.get(key));
                }
                String openid = (String) map.get("FromUserName");
                String toUserName = (String) map.get("ToUserName");
                String event = (String) map.get("Event");
                if ("subscribe".equals(event)) {
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    int result = accessMapper.isDoctor(openid);
                    if (result > 0) {
                        String getXml = getBackXMLTypeText(toUserName, openid, "由于您是医生，请不要随意点击下列菜单，请暂时退出公众号，系统为您配置个人信息，请于五分钟后再进入公众号！");
                        WeChatUtil.moveGroup(openid, Group.YISHENG);
                        out.print(getXml);
                    } else {
                        String getxml = CreateXml.getBackXMLTypeImg(toUserName, openid, "欢迎关注优家医", "什么是家庭医生? 家庭医生也叫全科大夫，是对服务对象实行全面的、连续的、有效的、及时的和个性化医疗保健服务和照顾的新型医生。", "http://mp.weixin.qq.com/s?__biz=MzIyNzE3MzAzNA==&mid=402697765&idx=1&sn=2379b8760090d3910d050a34ca863f0e", "https://mmbiz.qlogo.cn/mmbiz/EpFibrmjK8xPcPDJPuZ0iaxHRnwEOCrK1IH33LFxZ0ATHwsPBoagI1gchGf6gajSK3Oz0K5BKcicYBibVrcSjRBgcg/0?wx_fmt=png");
                        System.out.println(getxml);
                        out.print(getxml);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }

   /* private String getBackXMLTypeText(String toName, String fromName, String content) {

        String returnStr = "";

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String times = format.format(new Date());

        Element rootXML = new Element("xml");

        rootXML.addContent(new Element("ToUserName").setText(fromName));
        rootXML.addContent(new Element("FromUserName").setText(toName));
        rootXML.addContent(new Element("CreateTime").setText(times));
        rootXML.addContent(new Element("MsgType").setText("text"));
        rootXML.addContent(new Element("Content").setText(content));

        Document doc = new Document(rootXML);

        XMLOutputter XMLOut = new XMLOutputter();
        returnStr = XMLOut.outputString(doc);

        return returnStr;
    }*/

}