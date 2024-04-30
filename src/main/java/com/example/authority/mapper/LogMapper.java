package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-11-16
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
