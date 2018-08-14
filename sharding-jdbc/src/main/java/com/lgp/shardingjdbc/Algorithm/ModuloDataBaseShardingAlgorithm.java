package com.lgp.shardingjdbc.Algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/1 17:09
 * @DESCRIPTION 分库的逻辑
 **/
public class ModuloDataBaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        for (String name : availableTargetNames) {
            /*分成两个库*/
            if (name.endsWith(shardingValue.getValue() % 2 + "")) {
                return name;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String name : availableTargetNames) {
                if (name.endsWith(value % 2 + "")) {
                    result.add(name);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String name : availableTargetNames) {
                if (name.endsWith(i % 2 + "")) {
                    result.add(name);
                }
            }
        }
        return result;
    }
}
