package com.itheima.restkeeper.face;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.LogBusinessFace;
import com.itheima.restkeeper.pojo.LogBusiness;
import com.itheima.restkeeper.req.LogBusinessVo;
import com.itheima.restkeeper.service.ILogBusinessService;
import com.itheima.restkeeper.utils.BeanConv;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description：日志查询
 */
@DubboService(version = "${dubbo.application.version}",retries = 0,timeout = 5000)
public class LogBusinessFaceImpl implements LogBusinessFace {

    @Autowired
    ILogBusinessService logBusinessService;

    @Override
    public Page<LogBusinessVo> findLogBusinessVoPage(LogBusinessVo logBusinessVo, int pageNum, int pageSize) {
        Page<LogBusiness> page = logBusinessService.findLogBusinessVoPage(logBusinessVo, pageNum, pageSize);
        Page<LogBusinessVo> pageVo = new Page<>();
        BeanConv.toBean(page,pageVo);
        //结果集转换
        List<LogBusiness> routeList = page.getRecords();
        List<LogBusinessVo> routeVoList = BeanConv.toBeanList(routeList,LogBusinessVo.class);
        pageVo.setRecords(routeVoList);
        return pageVo;
    }
}
