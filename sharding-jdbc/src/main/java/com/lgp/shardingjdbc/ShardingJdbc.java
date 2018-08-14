package com.lgp;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSource;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.lgp.shardingjdbc.Algorithm.ModuloDataBaseShardingAlgorithm;
import com.lgp.shardingjdbc.Algorithm.ModuloTableShardingAlgorithm;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/1714:50
 * @DESCRIPTION 分库分表
 **/
public class ShardingJdbc {
    /**
     * main方法
     */
    public static void main(String[] args) {
        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>(2);
        dataSourceMap.put("sharding_0", createDataSource("sharding_0"));
        dataSourceMap.put("sharding_1", createDataSource("sharding_1"));

        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);

        //分表分库的表，第一个参数是逻辑表名，第二个是实际表名，第三个是实际库
        TableRule orderTableRule = new TableRule("t_order", Arrays.asList("t_order_0", "t_order_1"), dataSourceRule);
        TableRule orderItemTableRule = new TableRule("t_order_item", Arrays.asList("t_order_item_0", "t_order_item_1"), dataSourceRule);

        /**
         * DatabaseShardingStrategy 分库策略
         * 参数一：根据哪个字段分库
         * 参数二：分库路由函数
         *
         * TableShardingStrategy 分表策略
         * 参数一：根据哪个字段分表
         * 参数二：分表路由函数
         *
         * user_id选择哪个库
         * order_id选择那个表
         *
         * ModuloDataBaseShardingAlgorithm
         * ModuloTableShardingAlgorithm
         * 被2整除是0，反之是1
         *
         */

        ShardingRule shardingRule = new ShardingRule(dataSourceRule, Arrays.asList(orderTableRule, orderItemTableRule)
                , Arrays.asList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule)))
                , new DatabaseShardingStrategy("user_id", new ModuloDataBaseShardingAlgorithm())
                , new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm()));

        DataSource dataSource = new ShardingDataSource(shardingRule);
        String sql =
                "SELECT i.* FROM t_order o JOIN t_order_item i " +
                        "ON o.order_id=i.order_id " +
                        "WHERE o.user_id= ? AND o.order_id = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

//            preparedStatement.setInt(1, 10);
//            preparedStatement.setInt(2, 1001);

//             先根据分库规则去了sharding_1
//             o.user_id=11
            preparedStatement.setInt(1, 11);
//            再根据分表规则去了t_order_0,t_order_item_0
//            o.order_id=1000
            preparedStatement.setInt(2, 1000);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println("1--------" + result.getInt(1));
                System.out.println("2--------" + result.getInt(2));
                System.out.println("3--------" + result.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dataSourceName
     * @return dataSource
     * @DESCRIPTION 创建数据源
     */
    private static DataSource createDataSource(String dataSourceName) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        dataSource.setUsername("root");
        dataSource.setPassword("123456789");
        return dataSource;
    }
}
