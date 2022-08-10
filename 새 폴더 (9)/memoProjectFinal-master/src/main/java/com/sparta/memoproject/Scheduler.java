package com.sparta.memoproject;

import com.sparta.memoproject.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final MemoService memoService;

    @Scheduled(cron = "0 0 1 * * *")
    public void removeImage() {
        memoService.removeS3Image();
    }
}
