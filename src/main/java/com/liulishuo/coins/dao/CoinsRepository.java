package com.liulishuo.coins.dao;

import com.liulishuo.coins.model.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by jisongzhu on 2017/8/27.
 */
public interface CoinsRepository extends JpaRepository<Coins,Integer> {

    /**
     *  通过用户id查询
     *  方法名固定
     * @param Userid
     * @return
     */
    public Coins findByUserid(BigInteger Userid);
}
