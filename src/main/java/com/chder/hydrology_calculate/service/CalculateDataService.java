package com.chder.hydrology_calculate.service;

import com.chder.hydrology_calculate.model.HydrologyInfo;
import com.chder.hydrology_calculate.model.HydrologyInfoDto;

public interface CalculateDataService {

    Integer getHydrologyCount();

    // 保存数据
    HydrologyInfo saveHydrologyInfo(HydrologyInfoDto infoDto);

    HydrologyInfo getHydrologyInfoByLocation(String longitude, String latitude);
}
