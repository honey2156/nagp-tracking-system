package anton.nagptrackingsystem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anton.nagptrackingsystem.constant.BatchTechnology;
import anton.nagptrackingsystem.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	public List<Batch> findByBatchTechnology(BatchTechnology batchTechnology);

	public List<Batch> findByYear(int Year);

	public List<Batch> findByStartDate(Date startDate);

	public Batch findFirstByBatchName(String batchName);

}
