package com.huaxin.dao.daoImpl;

import com.huaxin.dao.InsertDao;
import com.huaxin.domain.Xinxi;
import com.huaxin.util.Utils;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by wang on 2015/11/23.
 */
public class InsertDaoImpl implements InsertDao {
    @Override
    public void insert(Xinxi xinxi) {

        //获取一个SqlSession对象
        SqlSession session = Utils.getSqlSession();
        //添加操作，用insert方法，第一个参数必须是mapping中唯一的id的值。
        session.insert("insertXinxi", xinxi);
        //涉及insert、update、delete的DML，要手动的commit呢，注意close方法是不会监测有木有commit，幻想close方法去commit会让你死的很惨滴。
        session.commit();
        //session也是相当于缓冲池技术一样的，所以用完也要记得close哦。
        session.close();

    }
}
