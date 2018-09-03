package com.lgp.droolsdrt.mapper;

import com.lgp.droolsdrt.domain.ActivityRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lgp
 */
@Mapper
public interface ActivityRuleMapper {

    /**
     * select
     *
     * @param id
     * @return
     */
    ActivityRule selectByPrimaryKey(Integer id);

    List<ActivityRule> selectAll();

}