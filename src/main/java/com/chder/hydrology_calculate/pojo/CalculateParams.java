package com.chder.hydrology_calculate.pojo;

import lombok.Data;

@Data
public class CalculateParams {

    /**
     * 实际供热面积
     */
    private double asg;

    /**
     * 联网面积
     */
    private double alw;

    /**
     * 采暖热指标
     */
    private double qf;

    /**
     * 供水温度
     */
    private double tg;

    /**
     * 回水温度
     */
    private double th;

    /**
     * 管长（固定）
     */
    private double l;

    /**
     * 管径（固定）
     */
    private double d;

    /**
     * 管壁当量绝对粗糙度（固定）
     */
    private double k;

    /**
     * 水的密度(固定)
     */
    private double p;

}
