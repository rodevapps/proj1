package com.example.springapp.repository;

import java.util.List;
import com.example.springapp.model.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna1 IN (SELECT kolumna1 FROM tabela_testowa GROUP BY kolumna1 HAVING COUNT(*) > 1)", nativeQuery = true)
	List<Test> findRecurrentByKolumna1();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna2 IN (SELECT kolumna2 FROM tabela_testowa GROUP BY kolumna2 HAVING COUNT(*) > 1)", nativeQuery = true)
	List<Test> findRecurrentByKolumna2();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna3 IN (SELECT kolumna3 FROM tabela_testowa GROUP BY kolumna3 HAVING COUNT(*) > 1)", nativeQuery = true)
	List<Test> findRecurrentByKolumna3();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna4 IN (SELECT kolumna4 FROM tabela_testowa GROUP BY kolumna4 HAVING COUNT(*) > 1)", nativeQuery = true)
	List<Test> findRecurrentByKolumna4();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna1 IN (SELECT kolumna1 FROM tabela_testowa GROUP BY kolumna1 HAVING COUNT(*) = 1)", nativeQuery = true)
	List<Test> findUniqueByKolumna1();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna2 IN (SELECT kolumna2 FROM tabela_testowa GROUP BY kolumna2 HAVING COUNT(*) = 1)", nativeQuery = true)
	List<Test> findUniqueByKolumna2();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna3 IN (SELECT kolumna3 FROM tabela_testowa GROUP BY kolumna3 HAVING COUNT(*) = 1)", nativeQuery = true)
	List<Test> findUniqueByKolumna3();

	@Query(value = "SELECT * FROM tabela_testowa WHERE kolumna4 IN (SELECT kolumna4 FROM tabela_testowa GROUP BY kolumna4 HAVING COUNT(*) = 1)", nativeQuery = true)
	List<Test> findUniqueByKolumna4();
}
