package com.itheima.restkeeper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.exception.ProjectException;
import com.itheima.restkeeper.req.OrderItemVo;
import com.itheima.restkeeper.req.OrderVo;
import com.itheima.restkeeper.req.TradingVo;

import java.util.List;

/**
 * @ClassName OrderFace.java
 * @Description 订单接口
 */
public interface OrderFace {

    /**
     * @Description 订单列表
     * @param dishVo 查询条件
     * @return
     */
    Page<OrderVo> findOrderVoPage(OrderVo dishVo, int pageNum, int pageSize);


    /***
     * @description 调整订单项菜品数量
     * @param dishId 菜品ID
     * @param orderNo 订单
     * @param opertionType 操作类型
     * @return
     */
    OrderVo opertionToOrderItem(Long dishId, Long orderNo, String opertionType) throws ProjectException;

    /***
     * @description 订单交易处理
     * @param orderVo 订单信息
     * @return
     */
    TradingVo handleTrading(OrderVo orderVo);

    /***
     * @description 查询已支付订单
     * @param orderNo d订单编号
     * @return
     */
    OrderVo findOrderVoPaid(Long orderNo);

    /***
     * @description 查询FKZ的订单
     * @return
     */
    List<OrderVo> findOrderVoPaying();

    /***
     * @description 同步交易状态
     *
     * @param orderNo 订单号
     * @param tradingState 交易状态
     * @return
     */
    Boolean synchTradingState(Long orderNo,String tradingState);
}
