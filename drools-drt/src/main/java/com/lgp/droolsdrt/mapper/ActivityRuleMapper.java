package com.lgp.droolsdrt.mapper;

import com.lgp.droolsdrt.domain.ActivityRule;
import com.lgp.droolsdrt.domain.ActivityRuleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lgp
 */
@Mapper
public interface ActivityRuleMapper {

    /**
     * select
     * @param id
     * @return
     */
    ActivityRule selectByPrimaryKey(Integer id);

}