package com.chder.hydrology_calculate.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "hydrology_info")
public class HydrologyInfo {

    @Id
    @GeneratedValue(generator = "uuid2") // 使用名为"uuid2"的生成器
    @GenericGenerator(name = "uuid2", strategy = "uuid2") // 定义生成器，策略为uuid2
    @Column(name = "id", length = 36)
    private String id;

    /**
     * 经度
     */
    @Column(name = "longitude", length = 50)
    private String longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude", length = 50)
    private String latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
