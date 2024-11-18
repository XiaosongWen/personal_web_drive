package com.xiaosong.immichdemo.repository;

import com.xiaosong.immichdemo.model.entity.Asset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssetRepository  extends JpaRepository<Asset, UUID> {

    List<Asset> findAssetByOwnerId(UUID  ownerId, Pageable pageable);
    List<Asset> findAllAssetsByOwnerId(UUID ownerId);

}