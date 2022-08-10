package com.sparta.memoproject.Mapper;

import com.sparta.memoproject.dto.MemoSoloDto;
import com.sparta.memoproject.dto.MemoTfDto;
import com.sparta.memoproject.model.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemoSoloMapper {
    MemoSoloMapper INSTANCE = Mappers.getMapper(MemoSoloMapper.class); // 2  해당 인스턴스가 MemoSoloMapper를 상속받아 MemoSoloMapperImpl로 구현되게 될것임을 선언

    @Mapping(target ="commentCnt", expression = "java(memo.getCommentList().size())")
    @Mapping(target ="heartCnt", expression = "java(memo.getHeartList().size())")
    MemoSoloDto MemoToDto(Memo memo);
}
