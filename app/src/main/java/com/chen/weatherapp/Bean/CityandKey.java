package com.chen.weatherapp.Bean;

import java.util.List;

public class CityandKey {
    private List<CityKeyBean> CityKey;
    public List<CityKeyBean> getList() {
        return CityKey;
    }

    public  class CityKeyBean{
        private String Province;
        private List<cityBean> city;
        public String getProvince() {
            return Province;
        }
        public List getList() {
            return  city;
        }
        public  class cityBean{
            private String CityName;
            private String key;
            public cityBean(String C,String K){
                CityName = C;
                key = K;
            }
            public String getKey() {
                return key;
            }
            public String getCityName() {
                return CityName;
            }
        }
    }

}
