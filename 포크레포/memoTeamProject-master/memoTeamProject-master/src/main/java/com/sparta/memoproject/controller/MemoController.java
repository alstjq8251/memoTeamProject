package com.sparta.memoproject.controller;


import com.sparta.memoproject.dto.MemoMainResponseDto;
import com.sparta.memoproject.dto.MemoRequestDto;
import com.sparta.memoproject.dto.MemoSoloDto;
import com.sparta.memoproject.image.S3Uploader;

import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.MemoRepository;
import com.sparta.memoproject.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController // MemoController도 어딘가에서 쓰일 때 new MemoController 이렇게 해서 생성이 되고 사용되어야 하는데 이 어노테이션으로 그 작업을 생략하게 해줌
public class MemoController {  //생성 조회 변경 삭제가 필요한데 업데이트 -> service , 나머지 ->Repo가 필요함

    private final MemoService memoService;



/////////////////////
//, consumes = {MediaType.ALL_VALUE}
    @Secured("ROLE_USER")
    @DeleteMapping(value = "/delete")
    public void removeS3Image() {
        memoService.removeS3Image();
    }

/////////////////////

    @Secured("ROLE_USER")
    @PostMapping(value = "/api/auth/memos", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    //생성은 해당 주소로 post방식으로 들어올것고 그렇게 들어오면 아래를 실행한다.
    public Memo creatMemo(@RequestPart(value = "dto") MemoRequestDto requestDto,
                          @RequestPart(required = false) MultipartFile multipartFile) throws IOException {   //메모를 생성하려면 데이터를 물고다닐 Dto가 필요하다.  // 날아오는 녀석을 그대로 requestDto에 넣어주기 위해서 해당 어노테이션을 씀

        return memoService.creatMemo(requestDto,multipartFile);
    }


    @GetMapping("/api/memos")
    public List<MemoMainResponseDto> readMemo() {
        return memoService.readMemo();
    }

    @GetMapping("/api/memos/{id}")
    public MemoSoloDto showMemo(@PathVariable Long id) {
        return memoService.readMemoSoloDto(id);
    }

    @Secured("ROLE_USER")
    @PutMapping("/api/auth/memos/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return memoService.update(id, requestDto);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/api/auth/memos/{id}")
    public boolean deleteMemo(@PathVariable Long id) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return memoService.delete(id);
    }
}
