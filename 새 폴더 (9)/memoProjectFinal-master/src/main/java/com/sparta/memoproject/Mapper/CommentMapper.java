package com.sparta.memoproject.Mapper;


import com.sparta.memoproject.dto.CommentTfDto;
import com.sparta.memoproject.dto.MemoTfDto;
import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class); // 2  해당 인스턴스가 CommentMapper 상속받아 CommentMapperImpl로 구현되게 될것임을 선언

//    @Mapping(target = "id", expression = "") // 3 기존의 경우 제외되는 칼럼,변수가 있어 제외되게 정해놨지만 Memo MemoTfDto사이엔 제외되는 필드가 없는거같아 일단 주석
//    Memo MemoTfDtoToEntity(MemoTfDto MemoTfDto);  // MemoTfDto는 Memo로 변환되는 경우가 존재하지않음 주석

    @Mapping(target = "heartcnt", expression = "java(Long.valueOf(comment.getHeartList().size()))")// 4
    CommentTfDto CommentToDto(Comment comment);

}
