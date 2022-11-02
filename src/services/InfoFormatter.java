package services;

import models.Info;

public class InfoFormatter {

    public InfoFormatter() {}

    public String getFormatInfo(Info info){
        String result = info.getDate()+"||"+info.getLatin()+"||"+info.getRussian()+"||"+info.getRandomInt()+"||"+info.getRandomFloat()+"||";
        return result;
    }
}
