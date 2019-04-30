package anton.nagptrackingsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anton.nagptrackingsystem.entity.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {

	public Level findFirstByNumber(int number);

	public List<Level> findByName(String name);

}
