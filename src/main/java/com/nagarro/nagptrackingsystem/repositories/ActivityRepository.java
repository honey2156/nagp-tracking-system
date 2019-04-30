package com.nagarro.nagptrackingsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.nagptrackingsystem.entity.Activity;
import com.nagarro.nagptrackingsystem.entity.Batch;
import com.nagarro.nagptrackingsystem.entity.Level;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	public List<Activity> findByLevel(Level level);

	public List<Batch> findByBatch(Batch batch);

	public Activity findFirstByBatchAndLevelAndName(Batch batch, Level level, String name);
}
