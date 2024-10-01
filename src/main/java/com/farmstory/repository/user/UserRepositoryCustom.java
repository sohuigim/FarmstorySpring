package com.farmstory.repository.user;

import com.farmstory.dto.pageDTO.PageRequestDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    public Page<Tuple> selectUserAllForList(PageRequestDTO pageRequestDTO, Pageable pageable);
}
