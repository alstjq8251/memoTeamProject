package com.sparta.memoproject.repository;

import com.sparta.memoproject.model.DeletedUrlPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedUrlPathRepository extends JpaRepository<DeletedUrlPath,Long> {
}
