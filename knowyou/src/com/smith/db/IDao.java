package com.smith.db;

import java.util.List;

import com.smith.entity.Bean_UI;



public interface IDao {
	public boolean add_ui(Bean_UI ui);
	public boolean add_uis(List<Bean_UI> uis);
	public boolean delete_table(String table);
	public List<Bean_UI> get_ui();
}
