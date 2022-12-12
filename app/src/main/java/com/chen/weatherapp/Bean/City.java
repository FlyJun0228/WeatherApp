package com.chen.weatherapp.Bean;

import java.io.Serializable;
import java.util.List;

public class City {
    private String message;
    private int status;
    private String date;
    private String time;
    private cityInfoBean cityInfo;
    private dataBean data;
    public static class cityInfoBean {
        private String city;
        private String citykey;
        private String parent;
        private String updateTime;

        public String getCity() {
            return city;
        }

        public String getCitykey() {
            return citykey;
        }

        public String getParent() {
            return parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }
    }
    public static class dataBean {

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private List<forecast> forecast;

        public String getGanmao() {
            return ganmao;
        }

        public int getPm10() {
            return pm10;
        }

        public int getPm25() {
            return pm25;
        }

        public String getQuality() {
            return quality;
        }

        public String getShidu() {
            return shidu;
        }

        public String getWendu() {
            return wendu;
        }

        public List<forecast> getForecasts() {
            return forecast;
        }
        public class forecast implements Serializable{
            private String date;
            private String high;
            private String low;
            private String ymd;
            private String week;
            private String sunrise;
            private String sunset;
            private int aqi;
            private String fx;
            private String fL;
            private String type;
            private String notice;

            public String getType() {
                return type;
            }

            public int getAqi() {
                return aqi;
            }

            public String getDate() {
                return date;
            }

            public String getfL() {
                return fL;
            }

            public String getFx() {
                return fx;
            }

            public String getHigh() {
                return high;
            }

            public String getLow() {
                return low;
            }

            public String getNotice() {
                return notice;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSunrise() {
                return sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public String getWeek() {
                return week;
            }

            public String getYmd() {
                return ymd;
            }
        }
        }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    public cityInfoBean getCityinfos() {
        return cityInfo;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public dataBean getDataList() {
        return data;
    }




}
