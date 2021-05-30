package com.command;

import java.util.List;

public interface IDaoCommand<T> {
	 //查询所有的数据
	 public List<T> searchAllData();
	 //新增数据
	 public int addData(T t);
	 //修改数据
	 public int update(T t); 
	 //删除数据
	 public int delete(int id);
	 //查询一条数据通过ID
	 public T getDataById(int id);
}
