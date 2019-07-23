package com.fedorov.alex.whattheweatheristoday;

public class DataCache {
    private static int imageRes = -1;
    private static String city, temp;

    private static DataCache dataCache = null;
    private static Object synchObj;

    private DataCache() {
        synchObj = new Object();
    }

    public static DataCache getDataCache() {
        if (dataCache == null) {
            dataCache = new DataCache();
        }

        return dataCache;
    }

    public int getImageRes() {
        return imageRes;

    }

    public void setImageRes(int imageRes) {
        synchronized (synchObj) {
            this.imageRes = imageRes;
        }
    }

    public String getTemp() {
        return temp;

    }

    public void setTemp(String temp) {
        synchronized (synchObj) {
            this.temp = temp;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        synchronized (synchObj) {
            this.city = city;
        }
    }
}
