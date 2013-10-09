package com.smith.db;

import java.util.List;

import com.smith.entity.Bean_module;



public interface IDao {
	public boolean add_module(Bean_module module);
	public boolean delete_table(String table);
	public List<Bean_module> get_modules();
}
