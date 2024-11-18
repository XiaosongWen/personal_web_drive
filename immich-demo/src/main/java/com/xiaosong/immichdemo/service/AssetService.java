package com.xiaosong.immichdemo.service;

import com.xiaosong.immichdemo.model.entity.Asset;
import com.xiaosong.immichdemo.model.vo.AssetVO;
import com.xiaosong.immichdemo.model.vo.UserVO;
import com.xiaosong.immichdemo.repository.AssetRepository;
import com.xiaosong.immichdemo.util.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final String SOURCEDIR = "C:\\Users\\wxsst\\Desktop\\project\\immich-app";
    private final String TARGETDIR = "D:\\photos";
    public List<AssetVO> getAssetByOwnerId(UserVO user, int page){
        List<Asset> assetByOwnerId = assetRepository.findAssetByOwnerId(user.getId(), PageRequest.of(page, 100));
        List<AssetVO> assetVOs = new ArrayList<>();
        for (Asset asset : assetByOwnerId) {
            AssetVO assetVO = new AssetVO();
            assetVO.setOriginalPath(asset.getOriginalPath());
            assetVOs.add(assetVO);
        }
        return assetVOs;
    }
    private long copy(String originalPath, String email){
        Path source = Paths.get(SOURCEDIR, originalPath);
        long size = 0;
        try {
            BasicFileAttributes attrs = Files.readAttributes(source, BasicFileAttributes.class);
            size = attrs.size();

            FileTime creationTime = attrs.creationTime();
            FileTime lastModifiedTime = attrs.lastModifiedTime();
            FileTime lastAccessTime = attrs.lastAccessTime();
            Instant earliestTime = Stream.of(
                    creationTime.toInstant(),
                    lastModifiedTime.toInstant(),
                    lastAccessTime.toInstant()
            ).min(Instant::compareTo).orElseThrow();
            ZonedDateTime zonedDateTime = earliestTime.atZone(ZoneId.systemDefault());
            String year = zonedDateTime.getYear() + "";
            String month = zonedDateTime.getMonthValue() + "";
            Path targetPath = Paths.get(TARGETDIR, email, year, year + "-" + month, source.getFileName().toString());
            FileUtil.copyFile(source, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size;
    }

    @Async
    public void migrate(UserVO user) {
        System.out.println("Job triggered asynchronously");
        List<Asset> assetByOwnerId = assetRepository.findAllAssetsByOwnerId(user.getId());
        System.out.println("Assets found: " + assetByOwnerId.size());
        long totalSize = 0;
        long startTime = System.currentTimeMillis();
        int alreadyMigrated = 0;
        for (Asset asset : assetByOwnerId) {
            totalSize += copy(asset.getOriginalPath(), user.getEmail());
            if (totalSize / (1024*1024*1024) > alreadyMigrated){
                System.out.println("Already migrated: " + alreadyMigrated + "GB; ");
                alreadyMigrated++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total size of files copied: " + totalSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime)/1000 + "s");
        System.out.println("Average speed: " + (1.0 * totalSize / 1024 / 1024/ (endTime - startTime) * 1000) + "mb/s");
    }
    @Async
    public void restructure() {
        final String dirStr = "D:\\photos\\xiaosongwen@test.com";
        Path startDir = Paths.get(dirStr);
        //8185
        List<Path> files = null;
        long startTime = System.currentTimeMillis();
        try (Stream<Path> fileStream = Files.walk(startDir)) {
            files = fileStream.filter(Files::isRegularFile)  // Filter only regular files
                    .collect(Collectors.toList()); // Print each file
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Assets found: " + files.size());
        System.out.println("Time taken: " + (endTime - startTime)/1000 + "s");
        startTime = System.currentTimeMillis();
        long totalSize = 0;
        for (Path file : files) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
                totalSize += attrs.size();
                FileTime creationTime = attrs.creationTime();
                FileTime lastModifiedTime = attrs.lastModifiedTime();
                FileTime lastAccessTime = attrs.lastAccessTime();
                Instant earliestTime = Stream.of(
                        creationTime.toInstant(),
                        lastModifiedTime.toInstant(),
                        lastAccessTime.toInstant()
                ).min(Instant::compareTo).orElseThrow();
                ZonedDateTime zonedDateTime = earliestTime.atZone(ZoneId.systemDefault());
                String year = zonedDateTime.getYear() + "";
                String month = zonedDateTime.getMonthValue() + "";
                Path targetPath = Paths.get(dirStr, year, month, file.getFileName().toString());
                Files.createDirectories(targetPath.getParent());
                Files.move(file, targetPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        endTime = System.currentTimeMillis();
        System.out.println("Total size of files copied: " + totalSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime)/1000 + "s");
        System.out.println("Average speed: " + (1.0 * totalSize / 1024 / 1024/ (endTime - startTime) * 1000) + "mb/s");
    }
}
