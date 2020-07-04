package com.genworth.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genworth.demo.model.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {
	
	public List<Occupation> findByEarningsGreaterThan(int earnings);
	
	@Query("SELECT o.type,sum(o.earnings) FROM Occupation o group by o.type")
	List<Object[]> getJobByType(String type);

	public List<Occupation> findByEarningsLessThan(int earnings);
	
	public List<Occupation> findByEarningsEquals(int earnings);

}