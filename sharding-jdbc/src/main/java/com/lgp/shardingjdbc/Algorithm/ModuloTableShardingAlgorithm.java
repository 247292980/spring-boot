package com.lgp.shardingjdbc.Algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/1 17:28
 * @DESCRIPTION
 **/
public class ModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    /**
     * select * from t_order from t_order where order_id = 11
     * └── SELECT *  FROM t_order_1 WHERE order_id = 11
     * select * from t_order from t_order where order_id = 44
     * └── SELECT *  FROM t_order_0 WHERE order_id = 44
     */
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * select * from t_order from t_order where order_id in (11,44)
     * ├── SELECT *  FROM t_order_0 WHERE order_id IN (11,44)
     * └── SELECT *  FROM t_order_1 WHERE order_id IN (11,44)
     * select * from t_order from t_order where order_id in (11,13,15)
     * └── SELECT *  FROM t_order_1 WHERE order_id IN (11,13,15)
     * select * from t_order from t_order where order_id in (22,24,26)
     * └──SELECT *  FROM t_order_0 WHERE order_id IN (22,24,26)
     */
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % 2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * select * from t_order from t_order where order_id between 10 and 20
     * ├── SELECT *  FROM t_order_0 WHERE order_id BETWEEN 10 AND 20
     * └── SELECT *  FROM t_order_1 WHERE order_id BETWEEN 10 AND 20
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(i % 2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }
}
