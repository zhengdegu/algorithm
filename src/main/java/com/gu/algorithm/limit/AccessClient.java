package com.gu.algorithm.limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 接口限流
 * 单体应用： 1.固定窗口计数器 2.滑动窗口计数器 3.漏桶散发 4.令牌筒算法
 * 分布式限流： Hystrix Sentinel Redis实现
 * Redis热点大Key的优化过程
 * Redis使用过程中经常会有各种大key的情况， 比如单个简单的key存储的value很大。
 * 由于redis是单线程运行的，如果一次操作的value很大会对整个redis的响应时间造成负面影响，导致IO网络拥塞。
 * 将整存整取的大对象，分拆为多个小对象。可以尝试将对象分拆成几个key-value， 使用multiGet获取值，这样分拆的意义在于分拆单次操作的压力，将操作压力平摊到多个redis实例中，降低对单个redis的IO影响；
 * where条件中 1＝1 之后的条件是通过 if 块动态变化的
 * @author gu
 * @create 2021/1/11 上午9:43
 */
public class AccessClient {

    //每秒只发出5个令牌
    RateLimiter rateLimiter = RateLimiter.create(5.0);

}
