package com.liulishuo.coins.service;

import com.liulishuo.coins.dao.CoinsRepository;
import com.liulishuo.coins.model.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * Created by jisongzhu on 2017/8/27.
 */
@Service
public class CoinsService {

    @Autowired
    private CoinsRepository coinsRepository;

    /**
     * 事务管理
     * from_user_id-coins
     * to_user_id+coins
     * 保证数据库数据的完整性和一致性
     */
    @Transactional
    public String transferCoins(BigInteger from_user_id,BigInteger to_user_id,int coins){
        Coins fromUser = coinsRepository.findByUserid(from_user_id);
        Coins toUser = coinsRepository.findByUserid(to_user_id);
        if(null!=fromUser&&null!=toUser&&fromUser.getCoins()>coins){
            fromUser.setCoins(fromUser.getCoins()-coins);
            toUser.setCoins(toUser.getCoins()+coins);
            coinsRepository.save(fromUser);
            coinsRepository.save(toUser);
            return "success";
        }
        return "failed";
    }
}
