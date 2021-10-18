package com.zxs.chat.web.dao.user;

import com.zxs.chat.bean.user.UserBase;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlConnection;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhanghua
 * @date 2021/10/15 17:16
 */
public class UserBaseDao {
    private  static  UserBaseDao dao;

    public static UserBaseDao getInstance(){
        if(dao==null){
            dao = new UserBaseDao();
        }
        return  dao;
    }

    private Collector<Row,?, List<UserBase>> collector = Collectors.mapping(row->{
        UserBase u = new UserBase();
        u.setId(row.getLong("id"));
        u.setUserName(row.getString("user_name"));
        u.setPassword(row.getString("password"));
        u.setStatus(row.getInteger("status"));
        u.setCreateTime(row.getLocalDateTime("create_time"));
        u.setLastLoginTime(row.getLocalDateTime("last_login_time"));
        u.setNickName(row.getString("nick_name"));
        u.setAvatar(row.getString("avatar"));
        u.setNote(row.getString("note"));
        return  u;
    },Collectors.toList());

    public Future<UserBase> getUserByUserName(SqlConnection conn, String userName){
        Promise<UserBase> promise = Promise.promise();
        String sql = "select *  from user_base where user_name = '%s' ";
        sql = String.format(sql,userName);
        conn.preparedQuery(sql).collecting(collector).execute(ar->{
            if(ar.succeeded()){
                List<UserBase> result = ar.result().value();
                if(result!=null&&result.size()>0){
                    promise.complete(result.get(0));
                }else{
                    promise.complete(null);
                }
            }else{
                ar.cause().printStackTrace();
            }
        });
        return promise.future();
    }
}
