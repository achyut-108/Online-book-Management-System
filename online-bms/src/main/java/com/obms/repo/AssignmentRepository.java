package com.obms.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.AssignmentEntity;

/**
 * @author saurabh.shyam
 * @since Feb 25, 2021 5:48 PM
 */
@Repository
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, BigInteger> {

     AssignmentEntity findByIdAndTrainerId(BigInteger id,Long user);
     List<AssignmentEntity> findAllByTrainerId(Long user);

}
