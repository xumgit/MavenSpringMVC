package com.springdemo.daomanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.springdemo.dao.AppPackage;
import com.springdemo.dao.User;
import com.springdemo.dto.UserAppPackageDTO;

/*
 * para=?  => 使用query.setParameter(0, "xx"); 按照顺序0,1,2,3...
 * para=:name  => 使用query.setParameter("name", "xx");
 * 
 * 
 * 基于投影的查询，如果返回多个值，这些值都是保存在一个object[]数组当中          
	List<Object[]> stus = (List<Object[]>)session.createQuery("select stu.name, stu.sex from Student stu where name like 
　　　　　　　　　　　　　　　　　　　　　　　　　　　　:name and sex like :sex")
                                                .setParameter("name", "%张%").setParameter("sex", "%男%")
                                                .list();
 * 
 * 
 * 可以使用in设置基于列表的查询，使用in查询时需要使用别名来进行参数设置，
 * 通过setParameterList方法即可设置，在使用别名和?的hql语句查询时，?形式的查询必须放在别名前面           
     List<Student> stus = (List<Student>)session.createQuery("select stu from Student stu where sex like ? and stu.room.id in (:room)")
                                                .setParameter(0, "%女%").setParameterList("room", new Integer[]{1, 2})
                                                .list();
     List<Student> stus = (List<Student>)session.createQuery("select stu from Student stu where stu.room.id in (:room) and stu.sex like :sex")
                                                .setParameterList("room", new Integer[]{1, 2}).setParameter("sex", "%女%")
                                                .list();
*                                                
*
* 通过setFirstResult(0).setMaxResults(10)可以设置分页查询，相当于offset和pagesize           
     List<Student> stus = (List<Student>)session.createQuery("select stu from Student stu where stu.room.name like :room and sex like :sex")
                                                .setParameter("room", "%计算机应用%").setParameter("sex", "%女%").setFirstResult(0).setMaxResults(10)
                                                .list();
 * */

public class AppPackageManager extends BaseManager<AppPackage>{
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    /**
     * select a.name, a.platform, a.number, u.firstName, u.height from apppackage a left join use u on a.name = u.firstName
     * @return Object[]
     */
	@SuppressWarnings("unchecked")
	public List<Object[]> getManyTableTest() {
		String hql = "select a.name, a.platform, a.number, u.firstName, u.height"
					  + " from " + AppPackage.class.getSimpleName() + " a left join " + User.class.getSimpleName() + " u"
					  + " on a.name = u.firstName";
		Query<Object[]> query = sessionFactory.getCurrentSession().createQuery(hql);
		LOG.info("[getManyTableTest()]query=" + query.getQueryString());
		return query.list();
	}
	
    /**
     * select UserAppPackageDTO(a.name, a.platform, a.number, u.firstName, u.height) from apppackage a, use u where a.name = u.firstName
     * @return UserAppPackageDTO
     */
	@SuppressWarnings("unchecked")
	public List<UserAppPackageDTO> getManyTable() {
		String hql = "select new com.springdemo.dto.UserAppPackageDTO(a.name, a.platform, a.number, u.firstName, u.height)"
					  + " from " + AppPackage.class.getSimpleName() + " a, " + User.class.getSimpleName() + " u"
					  + " where a.name = u.firstName";
		Query<UserAppPackageDTO> query = sessionFactory.getCurrentSession().createQuery(hql);
		LOG.info("[getManyTable()]query=" + query.getQueryString());
		return query.list();
	}
	
    /**
     * select a from apppackage a where a.platform =? and a.number in (?,?,?,?)
     * @return UserAppPackage
     */
	@SuppressWarnings("unchecked")
	public List<AppPackage> getTwo() {
		String hql = "select en from " + AppPackage.class.getSimpleName() + " en where en.platform=? and en.number in (?,?,?,?)";
		Query<AppPackage> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, "2016 MS");
		query.setParameter(1, 11);
		query.setParameter(2, 24);
		query.setParameter(3, 53);
		query.setParameter(4, 72);
		LOG.info("[getTwo()]query=" + query.getQueryString());
		return query.list();
	}
	
    /**
     * select a from apppackage a where a.platform =? and a.number in (?,?,?,?)
     * @return UserAppPackage
     */
	@SuppressWarnings("unchecked")
	public List<AppPackage> getOne() {
		String hql = "select en from " + AppPackage.class.getSimpleName() + " en where en.platform=:platform and en.number in (:numbers)";
		Query<AppPackage> query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer[] numbers = new Integer[]{11,24,53,72};
		query.setParameter("platform", "2016 MS");
		query.setParameterList("numbers", (Object[])numbers);
		LOG.info("[getOne()]query=" + query.getQueryString());
		return query.list();
	}
	
    /**
     * select a from apppackage a where a.platform =? and a.number in (?,?,?,?)
     * @return UserAppPackage
     */
	@SuppressWarnings({ "unchecked" })
	public List<AppPackage> getAppPackageByOther() {
		//List<AppPackage> appPackage = null;
		String hql = "select en from " + AppPackage.class.getSimpleName() + " en where en.platform=:platform and number in (:numbers)";
		//String hql = "select platform, number, size from " + AppPackage.class.getSimpleName() + " where platform=? and number in (?,?,?,?)"; 
		Query<AppPackage> query = sessionFactory.getCurrentSession().createQuery(hql);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer[] numbers = new Integer[]{11,24,53,72};
		map.put("platform", "2016 MS");
		map.put("numbers", numbers);
		
		query.setParameter("platform", map.get("platform")); 
		query.setParameterList("numbers", (Object[])map.get("numbers")); 
/*		query.setString(0, "2016 MS");
		query.setParameter(1, 11);
		query.setParameter(2, 24);
		query.setParameter(3, 53);
		query.setParameter(4, 72);*/
		LOG.info("[getAppPackageByOther()]query=" + query.getQueryString());
		return query.list();
/*		if (map != null) {  
            Set<String> keySet = map.keySet();  
            for (String string : keySet) {  
                Object obj = map.get(string);  
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(obj instanceof Collection<?>){  
                    query.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                    query.setParameterList(string, (Object[])obj);  
                }else{  
                    query.setParameter(string, obj);  
                }  
            }  
        }*/
		
		//return appPackage;
	}
	
	public AppPackage getAppPackage(int id) {
        return get(sessionFactory, AppPackage.class, id);
    }

	public List<AppPackage> getAllAppPackage() {
    	return findAll(sessionFactory, AppPackage.class);
    }

    public void addAppPackage(AppPackage appPackage) {
        save(sessionFactory, appPackage);
    }

    public boolean delAppPackage(int id) {
        return delete(sessionFactory, AppPackage.class, id);
    }

    public boolean updateAppPackage(AppPackage appPackage) {
        update(sessionFactory, appPackage);
        return true;
        
//        String hql = "update User u set u.userName = ?0,u.age=?1 where u.id = ?2";
        
//        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter(0, user.getUserName());
//        query.setParameter(1, user.getAge());
//        query.setParameter(2, user.getId());
//        
//        return (query.executeUpdate() > 0);
    }

}

