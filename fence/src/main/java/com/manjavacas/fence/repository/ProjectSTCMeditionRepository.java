package com.manjavacas.fence.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.ProjectSTCMedition;

@Repository
public interface ProjectSTCMeditionRepository extends MongoRepository<ProjectSTCMedition, String> {

	ProjectSTCMedition findBy_id(String id);

	List<ProjectSTCMedition> findByProject(String project);

	List<ProjectSTCMedition> findByDate(Date date);

	void deleteBy_id(ObjectId id);

}