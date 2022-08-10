package com.sparta.memoproject.Mapper;


import com.sparta.memoproject.dto.MemoTfDto;
import com.sparta.memoproject.model.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper   // 1 -> Mapstruct를 쓰기 위해 스프링에 등록하는 어노테이션
public interface MemoMapper {
    MemoMapper INSTANCE = Mappers.getMapper(MemoMapper.class); // 2  해당 인스턴스가 MemoMapper를 상속받아 MemoMapperImpl로 구현되게 될것임을 선언

//    @Mapping(target = "id", expression = "") // 3 기존의 경우 제외되는 칼럼,변수가 있어 제외되게 정해놨지만 Memo MemoTfDto사이엔 제외되는 필드가 없는거같아 일단 주석
//      Memo MemoTfDtoToEntity(MemoTfDto MemoTfDto);  // MemoTfDto는 Memo로 변환되는 경우가 존재하지않음 주석

    @Mapping(target = "commentCnt", expression = "java(memo.getCommentList().size())") // 4
    @Mapping(target = "heartCnt", expression = "java(memo.getHeartList().size())")
    MemoTfDto MemoToDto(Memo memo);

}
