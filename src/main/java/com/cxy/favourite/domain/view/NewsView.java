package com.cxy.favourite.domain.view;

public interface NewsView{
	//News相关
	Long getId();
	String getTitle();


	Integer getCommentCount();
	Integer getLikeCount();
	String getImage();


	Long getCreatedDate();
	String getContent();
	//User相关
	Long getUserId();
	String getUserName();
	String getMyPicture();

	//Category相关
	//String getCategory(); TODO
	//TODO 收藏
}