package com.itheima.restkeeper.enums;

import com.itheima.restkeeper.basic.IBasicEnum;

/**
 * @ClassName OrderEnum.java
 * @Description TODO
 */
public enum  OrderEnum implements IBasicEnum {
    
    SUCCEED("200","操作成功"),
    FAIL("1000","操作失败"),
    PAGE_FAIL("22005", "查询订单列表失败"),
    STATUS_FAIL("22006", "订单状态非待付款，不可修改"),
    DISH_STATUS_FAIL("22016", "菜品状态非起售，不可修改"),
    REFUND_FAIL("22007", "收付款员工不同，不可退款"),
    SELECT_TABLE_ORDER_FAIL("22008", "查询桌台订单"),
    OPERTION_SHOPPING_CART_FAIL("22009", "操作购物车失败"),
    CLEAR_SHOPPING_CART_FAIL("22010", "清理购物车失败"),
    PLACE_ORDER_FAIL("22010", "下单失败"),
    ;

    private String code;
    private String msg;

    OrderEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
