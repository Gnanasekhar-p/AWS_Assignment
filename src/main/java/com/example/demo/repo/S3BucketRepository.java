package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.S3BucketData;

@Repository
public interface S3BucketRepository extends JpaRepository<S3BucketData,Long>{
	long countByBucketName(String bucketName);
	List<S3BucketData> findByBucketNameContaining(String bucketName);
}
