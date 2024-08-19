package me.rryan.tinyurl.repository;

import me.rryan.tinyurl.entity.TinyUrlInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TinyUrlRepository extends JpaRepository<TinyUrlInfoEntity, Long> {

    TinyUrlInfoEntity findOneByCode(@Param("code") String code);
}
