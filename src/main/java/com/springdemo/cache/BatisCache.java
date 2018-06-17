package com.springdemo.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BatisCache implements Cache {
	
	private static final Logger LOG = LogManager.getLogger(BatisCache.class);
	
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentHashMap<Object,Object> cache = new ConcurrentHashMap<Object, Object>();
    private String id;

    public  BatisCache(){
    	LOG.info("initialize 1");
    }

  //必须有该构造函数
    public BatisCache(String id){
    	LOG.info("initialize 2");
        this.id = id;
    }

    // 获取缓存编号
    public String getId() {
    	LOG.info("得到ID：" + id);
        return id;
    }

    //获取缓存对象的大小
    public int getSize() {
    	LOG.info("获取缓存大小！");
        return 0;
    }
    // 保存key值缓存对象
    public void putObject(Object key, Object value) {
    	LOG.info("往缓存中添加元素：key=" + key+",value=" + value);
        cache.put(key,value);
    }

    //通过KEY
    public Object getObject(Object key) {
    	LOG.info("通过kEY获取值：" + key);
    	LOG.info("OVER");
    	LOG.info("=======================================================");
    	LOG.info("值为：" + cache.get(key));
    	LOG.info("=====================OVER==============================");
        return cache.get(key);
    }

    // 通过key删除缓存对象
    public Object removeObject(Object key) {
    	LOG.info("移除缓存对象：" + key);
        return null;
    }

    // 清空缓存
    public void clear() {
    	LOG.info("清除缓存！");
        cache.clear();
    }

    // 获取缓存的读写锁
    public ReadWriteLock getReadWriteLock() {
        System.out.println("获取锁对象！！！");
        return lock;
    }
}
