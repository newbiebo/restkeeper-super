package com.itheima.restkeeper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.req.ResourceVo;
import com.itheima.restkeeper.req.TreeVo;

/**
 * @Description：资源接口
 */
public interface ResourceFace {

    /**
     * @Description 资源列表
     * @param resourceVo 查询条件
     * @return
     */
    Page<ResourceVo> findResourceVoPage(ResourceVo resourceVo, int pageNum, int pageSize);

    /**
     * @Description 资源树
     * @param checkedIds 选中资源
     * @return
     */
    TreeVo initResourceTreeVo(String[] checkedIds);

    /**
     * @Description 创建资源
     * @param resourceVo 对象信息
     * @return
     */
    ResourceVo createResource(ResourceVo resourceVo);

    /**
     * @Description 修改资源
     * @param resourceVo 对象信息
     * @return
     */
    Boolean updateResource(ResourceVo resourceVo);

    /**
     * @Description 删除资源
     * @param checkedIds 选择IDS
     * @return
     */
    Boolean deleteResource(String[] checkedIds);

}
