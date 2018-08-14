package com.lgp.mapper;

import com.lgp.domain.LookRecord;
import com.lgp.domain.LookRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LookRecordMapper {
    int countByExample(LookRecordExample example);

    int deleteByExample(LookRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LookRecord record);

    int insertSelective(LookRecord record);

    List<LookRecord> selectByExample(LookRecordExample example);

    LookRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LookRecord record, @Param("example") LookRecordExample example);

    int updateByExample(@Param("record") LookRecord record, @Param("example") LookRecordExample example);

    int updateByPrimaryKeySelective(LookRecord record);

    int updateByPrimaryKey(LookRecord record);
}