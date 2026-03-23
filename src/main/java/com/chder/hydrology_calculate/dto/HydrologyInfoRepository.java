package com.chder.hydrology_calculate.dto;

import com.chder.hydrology_calculate.pojo.HydrologyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HydrologyInfoRepository extends JpaRepository<HydrologyInfo,String>{

    //自定义查询方法
    // 1. 根据经纬度精确查询
    List<HydrologyInfo> findByLongitudeAndLatitude(String longitude, String latitude);

}
