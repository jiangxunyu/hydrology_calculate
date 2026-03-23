package com.chder.hydrology_calculate.service.impl;

import com.chder.hydrology_calculate.dto.HydrologyInfoRepository;
import com.chder.hydrology_calculate.pojo.CalculateParams;
import com.chder.hydrology_calculate.pojo.CalculateResult;
import com.chder.hydrology_calculate.pojo.HydrologyInfo;
import com.chder.hydrology_calculate.pojo.HydrologyInfoDto;
import com.chder.hydrology_calculate.service.CalculateDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CalculateDataServiceImpl implements CalculateDataService {

    private final JdbcTemplate jdbcTemplate;
    public CalculateDataServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private HydrologyInfoRepository hydrologyInfoRepository;


    public Integer getHydrologyCount() {
        String sql = "SELECT COUNT(1) FROM hydrology_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 保存数据
     * @param infoDto
     * @return
     */
    public HydrologyInfo saveHydrologyInfo(HydrologyInfoDto infoDto) {
        HydrologyInfo hydrologyInfo = new HydrologyInfo();
        BeanUtils.copyProperties(infoDto, hydrologyInfo);
        return hydrologyInfoRepository.save(hydrologyInfo);
    }

    /**
     * 根据经纬度查询
     * @param longitude
     * @param latitude
     * @return
     */
    public HydrologyInfo getHydrologyInfoByLocation(String longitude, String latitude) {
        Integer hydrologyCount = getHydrologyCount();
        List<HydrologyInfo> byLongitudeAndLatitude = hydrologyInfoRepository.findByLongitudeAndLatitude(longitude, latitude);
        if (!CollectionUtils.isEmpty(byLongitudeAndLatitude)) {
            return byLongitudeAndLatitude.get(0);
        }
        return null;
    }

    /**
     * 计算数据
     * @param calculateParams
     * @return
     */
    @Override
    public CalculateResult calculateData(CalculateParams calculateParams) {
        double asg = calculateParams.getAsg();
        double alw = calculateParams.getAlw();
        double l = calculateParams.getL();
        double k = calculateParams.getK();
        double d = calculateParams.getD();
        double qf = calculateParams.getQf();
        double p = calculateParams.getP();
        double tg = calculateParams.getTg();
        double th = calculateParams.getTh();
        //计算温差
        double tjs = tg - th;
        //摩擦阻力系数
        double r = Math.round(0.11 * Math.pow(k / d, 0.25) * 100.0) / 100.0;
        //管段水流量
        double Gti = Math.round((0.86 * qf * asg) / tjs * 100.0) / 100.0;
        //比摩阻(沿程损失)
        double R = Math.round(0.0625 * (r / p) * (Gti * Gti / (Math.pow(d, 5))) * 100.0) / 100.0;
        //流速
        double vi = Math.round(( 4 * Gti / (Math.PI * d * d)) * 3600 * 100.0) / 100.0;

        double[] zetas = {};
        double sumZeta = 0;
        for (double zeta : zetas) {
            sumZeta += zeta;
        }
        //局部损失
        double pj = Math.round(sumZeta * p * vi * vi / 2 * 100.0) / 100.0;
        sumZeta = 0;
        for (double zeta : zetas) {
            sumZeta += zeta;
        }
        //局部损失当量长度
        double ld = Math.round(9.1 * Math.pow(d, 1.25) / Math.pow(k, 0.25) * sumZeta * 100.0) / 100.0;
        //总压降
        double P = Math.round(R * (l + ld) * 100.0) / 100.0;
        //总流量


        return null;
    }
}
