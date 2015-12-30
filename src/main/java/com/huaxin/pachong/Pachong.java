package com.huaxin.pachong;


import com.huaxin.pipeline.ConsolePipeline;
import com.huaxin.pipeline.InsertPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
public class Pachong implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("title", page.getHtml().xpath("//div[@class='article_info']/span/tidyText()").toString());
        // page.putField("title", page.getHtml().xpath("//div[@class='title_box']/h2/tidyText()").toString());
        if (page.getResultItems().get("title") == null) {
            page.setSkip(true);
        }
        page.addTargetRequests(page.getHtml().links().regex("http://gsbys.btbu.edu.cn/contents/9/\\d+.html").all());
        page.putField("post_url", page.getUrl().regex("http://gsbys.btbu.edu.cn/contents/9/\\d+.html").toString());

        page.putField("content", page.getHtml().xpath("//div[@class='article_info']/span/tidyText()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new Pachong())
                //start_url
                .addUrl("http://gsbys.btbu.edu.cn/channels/9.html")
                        //输出地点
                .addPipeline(new InsertPipeline())
                        //线程数
                .thread(5)
                .run();
    }

}
