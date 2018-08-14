package com.lgp.mapper;

import com.lgp.domain.UrlLibrary;
import com.lgp.domain.UrlLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrlLibraryMapper {
    int countByExample(UrlLibraryExample example);

    int deleteByExample(UrlLibraryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UrlLibrary record);

    int insertSelective(UrlLibrary record);

    List<UrlLibrary> selectByExample(UrlLibraryExample example);

    UrlLibrary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UrlLibrary record, @Param("example") UrlLibraryExample example);

    int updateByExample(@Param("record") UrlLibrary record, @Param("example") UrlLibraryExample example);

    int updateByPrimaryKeySelective(UrlLibrary record);

    int updateByPrimaryKey(UrlLibrary record);
}