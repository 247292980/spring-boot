package com.lgp.mapper;

import com.lgp.domain.Letter;
import com.lgp.domain.LetterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LetterMapper {
    int countByExample(LetterExample example);

    int deleteByExample(LetterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Letter record);

    int insertSelective(Letter record);

    List<Letter> selectByExampleWithBLOBs(LetterExample example);

    List<Letter> selectByExample(LetterExample example);

    Letter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByExampleWithBLOBs(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByExample(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByPrimaryKeySelective(Letter record);

    int updateByPrimaryKeyWithBLOBs(Letter record);

    int updateByPrimaryKey(Letter record);
}