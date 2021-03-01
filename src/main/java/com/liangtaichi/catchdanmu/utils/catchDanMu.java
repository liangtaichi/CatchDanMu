package com.liangtaichi.catchdanmu.utils;

import cn.hutool.http.HttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class catchDanMu {
    public String catchByBv(String bv){
        String cid = getCid(bv);
        return  cid;
    }

    /**
     * 获取bv号对应的弹幕号cid
     * @param bv
     * @return
     */
    public static String getCid(String bv){
        String bvUrl = "https://api.bilibili.com/x/player/pagelist?bvid=" + bv + "J&jsonp=jsonp";
        String bvJson = HttpUtil.get(bvUrl);
        String cid = bvJson.substring(bvJson.indexOf("cid\":") +5,bvJson.indexOf(",\"page"));
        return cid;
    }

    public static List<String> getDm(String cid) throws DocumentException, UnsupportedEncodingException {
        String dmurl = "https://api.bilibili.com/x/v1/dm/list.so?oid=" + cid;
        //String dmFile = HttpUtil.get(dmurl);
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(HttpUtil.get(dmurl).getBytes("UTF-8")));
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator("d");
        List<String> dm = new ArrayList<>();
        while(iterator.hasNext()){
            Element stu = (Element) iterator.next();
            dm.add(stu.getStringValue());
        }
        //Iterator iterator = rootElement.attributeIterator();
        //List<String> dm = ReUtil.findAll("\"> (.*?)</d>",dmFile,1);
        return dm;
    }
}
