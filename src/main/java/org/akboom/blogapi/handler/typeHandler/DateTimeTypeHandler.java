package org.akboom.blogapi.handler.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Classname DateTimeTypeHandler
 * @Description 完成数据库bigint类型时间到对象String类型时间的转化
 * @Author AoLinChen
 */
public class DateTimeTypeHandler implements TypeHandler {
    /**
     * @param preparedStatement
     * @param i
     * @param o
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        Long time = timeStringToLong(o.toString());
        preparedStatement.setLong(i,time);
    }

    /**
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        long time = resultSet.getLong(s);
        return new DateTime(time).toString("yyyy-MM-dd HH:mm");
    }

    /**
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        long time = resultSet.getLong(i);
        return new DateTime(time).toString("yyyy-MM-dd HH:mm");
    }

    /**
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        long time = callableStatement.getLong(i);
        return new DateTime(time).toString("yyyy-MM-dd HH:mm");
    }

    public static long timeStringToLong(String time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long longtime=0;
        try {
            longtime=format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longtime;
    }

}


