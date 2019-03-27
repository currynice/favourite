package com.cxy.favourite.domain.view;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NewsSummary  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String myPicture;
	private String title;

	//private String type;
	private String content;

	private String userName;
	private String image;
	private Integer likeCount;
	private Integer commentCount;
	private String createdDate;
	private boolean isPraise;//当前用户是否赞过,待处理 TODO











	public NewsSummary(){

	}

	public NewsSummary(NewsView view) {
		this.id = view.getId();
		this.userId = view.getUserId();
		this.myPicture = view.getMyPicture();
		this.title = view.getTitle();
		this.content = view.getContent();

		this.userName = view.getUserName();
		this.image = view.getImage();
		this.likeCount = view.getLikeCount();
		this.commentCount = view.getCommentCount();
	}

}