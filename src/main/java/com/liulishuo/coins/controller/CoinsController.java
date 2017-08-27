package com.liulishuo.coins.controller;

import com.liulishuo.coins.dao.CoinsRepository;
import com.liulishuo.coins.model.Coins;
import com.liulishuo.coins.service.CoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * Created by jisongzhu on 2017/8/27.
 */

@RestController
public class CoinsController {

    @Autowired
    private CoinsRepository coinsRepository;

    @Autowired
    private CoinsService coinsService;


    /**
     * 添加用户和金币
     *
     * @param user_id
     * @param coins
     * @return
     */
    @PostMapping(value = "/user/add")
    public Coins coinsAdd(@RequestParam("user_id") BigInteger user_id,
                            @RequestParam("coins") int coins) {
        Coins coinsObject = new Coins();
        coinsObject.setUser_id(user_id);
        coinsObject.setCoins(coins);
        return coinsRepository.save(coinsObject);
    }

    /**
     * 给定用户ID, 返回当前金币金额
     *
     * @param userid
     * @return
     */
    @GetMapping(value = "/coins/user/{userid}")
    public Integer coinsFindByUserId(@PathVariable("userid") BigInteger userid) {
        Coins coins = coinsRepository.findByUserid(userid);
        if(null!=coins){
            return coinsRepository.findByUserid(userid).getCoins();
        }
        return 0;
    }

    /**
     *给定user_id1, user_id2, 金额500, 完成从user1 向user2 的账户转账500 金币
     *
     * @param from_user_id
     * @param to_user_id
     * @param coins
     * @return
     */
    @PostMapping(value = "/transaction/transfer")
    public void transferCoins(@RequestParam("from_user_id") BigInteger from_user_id,
                                 @RequestParam("to_user_id") BigInteger to_user_id,
                                 @RequestParam("coins") int coins ) {
         coinsService.transferCoins(from_user_id,to_user_id,coins);
    }

}
