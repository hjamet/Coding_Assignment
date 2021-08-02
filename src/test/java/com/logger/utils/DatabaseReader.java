package com.logger.utils;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.logger.services.models.Event;

public final class DatabaseReader {
    public boolean containsLog(final List<Log> list, final Log... logs){
        for (Log log : logs){
            if (!list.stream().filter(o -> o.id.equals(log.id) && o.duration.equals(log.duration) && o.host.equals(log.host) && o.type.equals(log.type)).findFirst().isPresent()){
                return false;
            }
        }
        return true;
    }
    
    
    public final ArrayList<Log> databaseToList(){
        try{
            final String url = "jdbc:h2:" + Paths.get("logDatabase").toAbsolutePath().toString();
            final Connection connexion = DriverManager.getConnection(url);
            Statement stm;
            stm = connexion.createStatement();
            String sql = "Select * From LOGS";
            ResultSet rst;
            rst = stm.executeQuery(sql);
            ArrayList<Log> logList = new ArrayList<Log>();
            while (rst.next()) {
                logList.add(new Log(rst.getString("ID"), rst.getLong("DURATION"), rst.getString("HOST"), rst.getString("TYPE"), false));
            }
            return logList;
        }catch(Exception e){
            return new ArrayList<Log>();
        }
    }
    
    public final Boolean areEventEqual(Event firstEvent, Event secondEvent){
        if (!firstEvent.getId().equals(secondEvent.getId())){
            return false;
        }
        if (!(firstEvent.getStartTime() == null && secondEvent.getStartTime() == null) && !firstEvent.getStartTime().equals(secondEvent.getStartTime())){
            return false;
        }
        if (!(firstEvent.getEndTime() == null && secondEvent.getEndTime() == null) && !firstEvent.getEndTime().equals(secondEvent.getEndTime())){
            return false;
        }
        if (!(firstEvent.getHost() == null && secondEvent.getHost() == null) && !firstEvent.getHost().equals(secondEvent.getHost())){
            return false;
        }
        if (!(firstEvent.getType() == null && secondEvent.getType() == null) && !firstEvent.getType().equals(secondEvent.getType())){
            return false;
        }
        return true;
    }
}
