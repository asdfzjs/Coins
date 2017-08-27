package com.liulishuo.coins.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by jisongzhu on 2017/8/27.
 */
@Entity
@Table(name = "coins")
public class Coins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private BigInteger userid;

    @Column(name = "coins")
    private int coins;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getUser_id() {
        return userid;
    }

    public void setUser_id(BigInteger user_id) {
        this.userid = user_id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
