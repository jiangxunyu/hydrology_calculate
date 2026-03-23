package com.chder.hydrology_calculate.service;

import com.chder.hydrology_calculate.pojo.CalculateParams;
import com.chder.hydrology_calculate.pojo.CalculateResult;
import com.chder.hydrology_calculate.pojo.HydrologyInfo;
import com.chder.hydrology_calculate.pojo.HydrologyInfoDto;

public interface CalculateDataService {

    Integer getHydrologyCount();

    // 保存数据
    HydrologyInfo saveHydrologyInfo(HydrologyInfoDto infoDto);

    HydrologyInfo getHydrologyInfoByLocation(String longitude, String latitude);

    CalculateResult calculateData(CalculateParams calculateParams);
}
