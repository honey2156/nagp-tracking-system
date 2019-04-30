package anton.nagptrackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anton.nagptrackingsystem.entity.ApplicantActivity;

@Repository
public interface ApplicantActivityRepository extends JpaRepository<ApplicantActivity, Integer> {

}
