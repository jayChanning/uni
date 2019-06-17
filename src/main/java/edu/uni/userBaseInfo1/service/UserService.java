package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.User;


public interface UserService {

    /**
     * Author: chenenru 23:16 2019/4/29
     * @param id
     * @return User
     * @apiNote: 根据id查询用户
     */
    User selectUserById(long id);
}
