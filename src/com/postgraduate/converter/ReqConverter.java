package com.postgraduate.converter;

import com.postgraduate.entity.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/13.
 */
public class ReqConverter {

    public static List<Request> getReqs(ResultSet rs) {
        List<Request> requests = new ArrayList<>();
        try {
            while (rs.next()) {
                Request req = new Request();
                req.setTeaId(rs.getInt("tea_id"));
                req.setStuId(rs.getInt("stu_id"));
                req.setFirstMsgId(rs.getInt("first_msg_id"));
                req.setFlag(rs.getInt("status"));
                req.setLastDate(rs.getString("last_date"));
                req.setReqId(rs.getInt("req_id"));
                requests.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
