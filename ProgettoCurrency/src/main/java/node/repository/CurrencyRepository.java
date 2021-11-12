package node.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import node.entity.CurrencyEntity;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity,Long> { 
	public List<CurrencyEntity> findByPair1(String pair1);
}