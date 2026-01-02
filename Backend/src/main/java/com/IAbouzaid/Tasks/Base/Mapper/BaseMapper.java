package com.IAbouzaid.Tasks.Base.Mapper;

public interface BaseMapper<T,DTO> {

    DTO toDto(T entity);
    T toEntity(DTO dto);
}
