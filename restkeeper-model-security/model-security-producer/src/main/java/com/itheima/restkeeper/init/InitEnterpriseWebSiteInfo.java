package com.itheima.restkeeper.init;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.restkeeper.constant.SecurityCacheConstant;
import com.itheima.restkeeper.constant.SuperConstant;
import com.itheima.restkeeper.pojo.Enterprise;
import com.itheima.restkeeper.req.EnterpriseVo;
import com.itheima.restkeeper.service.IEnterpriseService;
import com.itheima.restkeeper.utils.BeanConv;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName initEnterpriseWebSIteInfo.java
 * @Description 初始化企业站点信息到redis
 */
@Component
public class InitEnterpriseWebSiteInfo {

    @Autowired
    IEnterpriseService enterpriseService;

    @Autowired
    RedissonClient redissonClient;

    /**
     *获得两时间的秒间隔
     */
    public Long secondInterval(Date date1, Date date2) {
        long secondInterval = (date2.getTime() - date1.getTime()) / 1000;
        return secondInterval;
    }

    /***
     * @description 初始化企业站点信息到redis
     */
    @PostConstruct
    public void init(){
        QueryWrapper<Enterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Enterprise::getEnableFlag, SuperConstant.YES)
                .and(wrapper->wrapper
                        .eq(Enterprise::getStatus,SuperConstant.TRIAL)
                        .or()
                        .eq(Enterprise::getStatus,SuperConstant.OFFICIAL));
        List<Enterprise> list = enterpriseService.list(queryWrapper);
        List<EnterpriseVo> enterpriseVos = BeanConv.toBeanList(list, EnterpriseVo.class);
        for (EnterpriseVo enterpriseVo : enterpriseVos) {
            String key = SecurityCacheConstant.INIT_EWEBSITE+enterpriseVo.getWebSite();
            RBucket<EnterpriseVo> bucket = redissonClient.getBucket(key);
            Long secondInterval = this.secondInterval(new Date(), enterpriseVo.getExpireTime());
            if (secondInterval.longValue()>0){
                bucket.set(enterpriseVo,secondInterval, TimeUnit.SECONDS);
            }
        }
    }

    /***
     * @description 添加缓存中的站点
     * @param webSite 站点
     * @param enterpriseVo 企业号
     * @return:
     */
    public void addWebSiteforRedis(String webSite,EnterpriseVo enterpriseVo){
        String key = SecurityCacheConstant.INIT_EWEBSITE+webSite;
        RBucket<EnterpriseVo> bucket = redissonClient.getBucket(key);
        Long secondInterval = this.secondInterval(new Date(), enterpriseVo.getExpireTime());
        if (secondInterval.longValue()>0){
            bucket.trySet(enterpriseVo,secondInterval, TimeUnit.SECONDS);
        }
    }

    /***
     * @description 移除缓存中的站点
     * @param webSite 站点
     * @param enterpriseVo 企业号
     * @return:
     */
    public void deleteWebSiteforRedis(String webSite, EnterpriseVo enterpriseVo){
        String key = SecurityCacheConstant.INIT_EWEBSITE+webSite;
        RBucket<EnterpriseVo> bucket = redissonClient.getBucket(key);
        bucket.delete();
    }


    /***
     * @description 更新缓存中的站点
     * @param webSite 站点
     * @param enterpriseVo 企业号
     * @return:
     */
    public void updataWebSiteforRedis(String webSite,EnterpriseVo enterpriseVo){
        String key = SecurityCacheConstant.INIT_EWEBSITE+webSite;
        RBucket<EnterpriseVo> bucket = redissonClient.getBucket(key);
        Long secondInterval = this.secondInterval(new Date(), enterpriseVo.getExpireTime());
        if (secondInterval.longValue()>0){
            bucket.set(enterpriseVo,secondInterval, TimeUnit.SECONDS);
        }
    }
}
