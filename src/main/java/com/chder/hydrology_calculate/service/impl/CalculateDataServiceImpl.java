package com.chder.hydrology_calculate.service.impl;

import com.chder.hydrology_calculate.dto.HydrologyInfoRepository;
import com.chder.hydrology_calculate.model.HydrologyInfo;
import com.chder.hydrology_calculate.model.HydrologyInfoDto;
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
}
