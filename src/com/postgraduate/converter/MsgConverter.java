package com.postgraduate.converter;

import com.postgraduate.entity.Msg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/12/7.
 */
public class MsgConverter {

    public static Msg getMsg(ResultSet rs) {
        Msg msg = new Msg();
        try {
            msg.setFlag(rs.getInt("flag"));
            msg.setMain(rs.getString("main"));
            msg.setRead(rs.getInt("read"));
            msg.setLastDate(rs.getString("last_date"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return msg;
    }

    public static List<String> getMsgsJson(List<Msg> msgs) {
        List<String> jsons = new ArrayList<>();
        for (Msg msg : msgs) {
            String json = getMsgJson(msg);
            if (json != null)
                jsons.add(json);
        }
        return jsons;
    }

    public static String getMsgJson(Msg msg) {
        String json = msg.getMain();
        return json;
    }

}
