package com.chder.hydrology_calculate.controller;

import com.chder.hydrology_calculate.pojo.*;
import com.chder.hydrology_calculate.service.CalculateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/calculateData")
public class CalculateDataController {

    @Autowired
    private CalculateDataService calculateDataService;

    /**
     * 保存数据
     * @param infoDto
     * @return
     */
    @PostMapping("/saveData")
    @ResponseBody
    public Result createHydrologyInfo(@RequestBody HydrologyInfoDto infoDto) {
        calculateDataService.saveHydrologyInfo(infoDto);
        return Result.success();
    }

    /**
     * 查询所有位置数据
     * @return
     */
    @GetMapping("/queryAllData")
    @ResponseBody
    public Result queryAllData() {
        List<HydrologyInfo> hydrologyInfoList = new ArrayList<>();
        return Result.success(hydrologyInfoList);
    }

    /**
     * 相询指定位置数据
     * @param longitude
     * @param latitude
     * @return
     */
    @GetMapping("/queryData")
    @ResponseBody
    public Result queryData(@RequestParam String longitude, String latitude) {
        HydrologyInfo hydrologyInfo = calculateDataService.getHydrologyInfoByLocation(longitude, latitude);
        return Result.success("查询成功",hydrologyInfo);
    }

    /**
     * 计算水利信息数据
     * @param calculateParams
     * @return
     */
    @GetMapping("/calculateData")
    @ResponseBody
    public Result calculateData(@RequestBody CalculateParams calculateParams) {
        CalculateResult calculateResult = calculateDataService.calculateData(calculateParams);
        return Result.success(calculateResult);
    }
}
