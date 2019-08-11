package com.hfxt.bean;

public class Permission {

	private Integer mid;
	private String menuName;
	private String menuPath;
	private Integer pareMenuId;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getPareMenuId() {
		return pareMenuId;
	}

	public void setPareMenuId(Integer pareMenuId) {
		this.pareMenuId = pareMenuId;
	}

}
