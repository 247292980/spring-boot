package com.lgp.drools.mapper;

import com.lgp.drools.domain.ActivityRule;
import com.lgp.drools.domain.ActivityRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityRuleMapper {
    int countByExample(ActivityRuleExample example);

    int deleteByExample(ActivityRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRule record);

    int insertSelective(ActivityRule record);

    List<ActivityRule> selectByExample(ActivityRuleExample example);

    ActivityRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityRule record, @Param("example") ActivityRuleExample example);

    int updateByExample(@Param("record") ActivityRule record, @Param("example") ActivityRuleExample example);

    int updateByPrimaryKeySelective(ActivityRule record);

    int updateByPrimaryKey(ActivityRule record);
}