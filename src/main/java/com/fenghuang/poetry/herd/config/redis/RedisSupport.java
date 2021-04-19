package com.fenghuang.poetry.herd.config.redis;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.common.constants.ErrMsg;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.service.model.dto.RankRedisDTO;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @Author fengbo.yue
 * @Date Created in 2019年09月04日 17:40
 * @Version 1.0
 * @Since 1.0
 */
@Component
public class RedisSupport<HK, V> {

    @Value("${spring.redis.prefix:evaluate-service::}")
    private String key_prefix;

    private final RedisTemplate<String, V> redisTemplate;
    private final HashOperations<String, HK, V> hashOperations;
    private final ListOperations<String, V> listOperations;
    private final ZSetOperations<String, V> zSetOperations;
    private final SetOperations<String, V> setOperations;
    private final ValueOperations<String, V> valueOperations;

    @Autowired
    public RedisSupport(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }


    public void hashPut(String key, HK hashKey, V value) {
        hashOperations.put(key(key), hashKey, value);
    }

    public void setIfAbsent(String key, V value, long expire) {
        valueOperations.setIfAbsent(key, value, expire, TimeUnit.MILLISECONDS);
    }

    public Map<HK, V> hashFindAll(String key) {
        return hashOperations.entries(key(key));
    }

    public V hashGet(String key, HK hashKey) {
        return hashOperations.get(key(key), hashKey);
    }

    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key(key), hashKey);
    }

    public Long listPush(String key, V value) {
        return listOperations.rightPush(key(key), value);
    }

    public Long listUnshift(String key, V value) {
        return listOperations.leftPush(key(key), value);
    }

    public List<V> listFindAll(String key) {
        if (!redisTemplate.hasKey(key(key))) {
            return null;
        }
        return listOperations.range(key(key), 0, listOperations.size(key(key)));
    }

    public V listLPop(String key) {
        return listOperations.leftPop(key(key));
    }

    public void setValue(String key, V value) {
        valueOperations.set(key(key), value);
    }

    public void setValue(String key, V value, long timeout) {
        valueOperations.set(key(key), value, timeout, TimeUnit.MILLISECONDS);
    }

    public V getValue(String key) {
        return valueOperations.get(key(key));
    }

    public String getStringValue(String key) {
        return (String) getValue(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key(key));
    }

    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key(key), timeout, timeUnit);
    }

    public Long zsetRemove(String key, V value) {
        return zSetOperations.remove(key, value);
    }

    public boolean zsetAdd(String key, V value, double score) {
        return zSetOperations.add(key, value, score);
    }

    public Long getUserCurrentRank(String key, V value) {
        Long rank = zSetOperations.reverseRank(key, value);
        if (Objects.isNull(rank)) {
            throw new BusinessException(ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
        }
        return rank + 1;
    }

    public Long getUserCount(String key) {
        Long count = zSetOperations.size(key);
        return count;
    }

    public List<RankRedisDTO> getRank(String key, double startScore, double endScore, long start, long end) {
        Set<String> set = (Set<String>) redisTemplate.opsForZSet().reverseRangeByScore(key, startScore, endScore, start, end);
        List<RankRedisDTO> setRedisDto = set.stream().map(s -> {
            return JSONObject.parseObject(s, RankRedisDTO.class);
        }).collect(Collectors.toList());
        return setRedisDto;
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key(key));
    }

    private String key(String key) {
        return key_prefix + key;
    }

}
