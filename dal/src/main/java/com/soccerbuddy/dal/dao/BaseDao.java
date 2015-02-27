package com.soccerbuddy.dal.dao;

public interface BaseDao<T,S> {
	T find(S request);
}
