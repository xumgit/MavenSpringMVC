package com.springdemo.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.springdemo.common.JsonUtils;
import com.springdemo.dao.AuthorMapper;
import com.springdemo.pojo.Author;
import com.springdemo.redis.RedisServiceImpl;

@Service(value="authorService")
public class AuthorService {
	
	private static final String KEY = "author";
	private static final Logger LOG = LogManager.getLogger(Author.class);
	
	@Resource
	private AuthorMapper authorMapper;
	
	@Resource
	private RedisServiceImpl redisService;
	
	/*
	 * 启动redis: redis-server /etc/redis/redis.conf(配置文件,有更改)
	 * 关闭redis: redis-cli shutdown 或则 ctrl+c
	 * 
	 * */
	public Author selectByPrimaryKey(Integer id) {
		Author author = null;
		String json = this.redisService.get(KEY+id);
		LOG.info("json="+json);
		if (json == null) {
			author = this.authorMapper.selectByPrimaryKey(id);
			if (author != null) {
				LOG.info("author="+author);
				this.redisService.put(KEY+id, author, 1, TimeUnit.HOURS);
			}
			return author;
		}
		
		return JsonUtils.fromJson(json, Author.class);
	}
	
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> author = null;
		author = this.authorMapper.selectAll();
		return author;
	}
	
	public List<Map<String, Object>> selectAuthorByBootGrid(Map<String, Object> mapPara) {
		List<Map<String, Object>> author = null;
		author = this.authorMapper.selectAuthorByBootGrid(mapPara);
		return author;
	}
	
	public List<Author> selectByParameterOne(Author author) {
		List<Author> authorList = null;
		authorList = this.authorMapper.selectByParameterOne(author);
		return authorList;
	}
	
	public List<Author> selectByParameterTwo(String name, Integer age, String country) {
		List<Author> authorList = null;
		authorList = this.authorMapper.selectByParameterTwo(name,age,country);
		return authorList;
	}
	
	public List<Author> selectByParameterThree(Author author, String country) {
		List<Author> authorList = null;
		authorList = this.authorMapper.selectByParameterThree(author,country);
		return authorList;
	}
	
	public int selectAllCount() {
		int count = 0;
		count = this.authorMapper.selectAllCount();
		return count;
	}
	
	public int updateByPrimaryKeySelective(Author author) {
		int affectRow = -1;
		affectRow = this.authorMapper.updateByPrimaryKeySelective(author);
		return affectRow;
	}
	
	public int batchUpdate(List<Author> authorList) {
		int affectRow = -1;
		affectRow = this.authorMapper.batchUpdate(authorList);
		return affectRow;
	}
	
	public int batchUpdateCaseWhen(List<Author> authorList) {
		int affectRow = -1;
		affectRow = this.authorMapper.batchUpdateCaseWhen(authorList);
		return affectRow;
	}
	
}
