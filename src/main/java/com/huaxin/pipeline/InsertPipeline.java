package com.huaxin.pipeline;

import com.huaxin.dao.InsertDao;
import com.huaxin.dao.daoImpl.InsertDaoImpl;
import com.huaxin.domain.Xinxi;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * Created by wang on 2015/11/23.
 */
public class InsertPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {

        InsertDao insertDao = new InsertDaoImpl();

        Xinxi xinxi = new Xinxi();

        //遍历所有结果，存进数据库
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            if (entry.getKey().equals("title")) {
                xinxi.setTitle(entry.getValue().toString());
            } else if (entry.getKey().equals("post_url")) {
                xinxi.setPost_url(entry.getValue().toString());
            } else if (entry.getKey().equals("content")) {
                xinxi.setContent(entry.getValue().toString());
            }
        }
        insertDao.insert(xinxi);
    }
}


