package com.csc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.mfs.model.Rank;

public interface RankRepository extends JpaRepository<Rank, Integer> {

	Rank findByName(String string);

}
