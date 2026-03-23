package com.chder.hydrology_calculate.pojo;

import lombok.Data;

@Data
public class CalculateResult {

    /**
     * 摩擦阻力系数
     */
    private double rt;

    /**
     * 管段水流量
     */
    private double gti;

    /**
     * 比摩阻(沿程损失)
     */
    private double R;

    /**
     * 局部损失
     */
    private double pj;

    /**
     * 局部损失当量长度
     */
    private double ld;

    /**
     * 总压降
     */
    private double P;

    /**
     * 总流量
     */
    private double G;

    /**
     * 流速
     */
    private double vi;

    /**
     * 计算温差
     */
    private double tjs;
}
