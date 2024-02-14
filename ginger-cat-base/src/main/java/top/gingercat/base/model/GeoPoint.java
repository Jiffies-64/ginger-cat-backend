package top.gingercat.base.model;

import java.io.Serializable;


public class GeoPoint implements Serializable {
    /**
     * 纬度
     */
    private double lat;
    /**
     * 经度
     */
    private double lng;

    public GeoPoint() {
    }

    /**
     * 创建经维度坐标
     *
     * @param lat 纬度
     * @param lng 经度
     */
    public GeoPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
