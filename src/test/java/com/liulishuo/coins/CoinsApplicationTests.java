package com.liulishuo.coins;

import com.liulishuo.coins.dao.CoinsRepository;
import com.liulishuo.coins.model.Coins;
import com.liulishuo.coins.service.CoinsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

/**
 * Created by jisongzhu on 2017/8/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoinsApplication.class)
public class CoinsApplicationTests {

    @Autowired
    private CoinsRepository coinsRepository;

    @Autowired
    private CoinsService coinsService;



    @Test
    public void coinsAdd(){
        Coins coinsObject = new Coins();
        coinsObject.setUser_id(new BigInteger("22"));
        coinsObject.setCoins(12);
        coinsRepository.save(coinsObject);
    }


    @Test
    public void coinsFindByUserId(){
        Coins coins = coinsRepository.findByUserid(new BigInteger("22"));
    }

    @Test
    public void transferCoins(){
       coinsService.transferCoins(new BigInteger("22"),new BigInteger("1"),10);
    }



}