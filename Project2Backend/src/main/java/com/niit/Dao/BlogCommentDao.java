package com.niit.Dao;

import java.util.List;

import com.niit.Model.BlogComment;

public interface BlogCommentDao {
	
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComment(int blogPostId);
	void deleteBlogComment(BlogComment blogComment);

}
