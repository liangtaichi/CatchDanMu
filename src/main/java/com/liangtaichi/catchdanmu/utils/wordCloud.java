package com.liangtaichi.catchdanmu.utils;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.util.List;

public class wordCloud {
    public static void make(List<String> dm, String bv){
        //初始化
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        //分词返回数量（最高600个）
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        //最小分词长度（2）
        frequencyAnalyzer.setMinWordLength(2);
        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        //获取词语频率数据
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(dm);
        //图片分辨率
        Dimension dimension = new Dimension(1000,1000);
        //创建词云
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //防止中文乱码
        java.awt.Font font = new java.awt.Font("STSong-Light",2,18);
        wordCloud.setKumoFont(new KumoFont(font));
        //设置边缘留空
        wordCloud.setPadding(2);
        //颜色频率越高使用越前的颜色
        wordCloud.setColorPalette(new ColorPalette(
                        new Color(0xed1941), new Color(0xf26522), new Color(0x845538)
                )
        );
        //设置形状
        wordCloud.setBackground(new CircleBackground(400));
        //字体大小范围
        wordCloud.setFontScalar(new SqrtFontScalar(8,40));
        //背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //生成图片
        wordCloud.build(wordFrequencies);
        //输出
//        OutputStream outputStream = new ByteArrayOutputStream();
//        wordCloud.writeToStream("png",outputStream);
//        byte[] outputByte = ((ByteArrayOutputStream)outputStream).toByteArray();
//        return Base64.getMimeEncoder().encodeToString(outputByte);
        wordCloud.writeToFile("d://"+ bv +".png");
    }
}
