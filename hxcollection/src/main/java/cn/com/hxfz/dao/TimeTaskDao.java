package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;



/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.dao
 * @作者: qiangxuan
 * @时间: 2018/3/16 15:28
 * @版本: V1.0
 */
public interface TimeTaskDao {
	/**
	 * 
	 * @description：项目启动加载所有启动状态的定时任务列表  数据访问接口类 
	 * @method:  getTimeTaskList
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年4月26日 上午11:22:28 by qiangxuan
	 */
    List<Map<String,Object>> getTimeTaskList();
    /**
     * 
     * @description： 获取定时任务列表   数据访问接口类
     * @method:  getTimeTask
     * @param:   @param param
     * @param:   @return
     * @return:  List<Map<String,Object>>
     * @date:    2018年4月26日 上午11:23:56 by qiangxuan
     */
    List<Map<String,Object>> getTimeTask(Map<String, Object> param);
    /**
     * 
     * @description： 新增定时任务  数据访问接口类
     * @method:  saveTimeTask
     * @param:   @param paramsMap
     * @return:  void
     * @date:    2018年4月26日 上午11:24:09 by qiangxuan
     */
    void saveTimeTask(Map<String, Object> paramsMap);
    /**
     * 
     * @description： 编辑定时任务  数据访问接口类
     * @method:  updateTimeTaskById
     * @param:   @param paramsMap
     * @return:  void
     * @date:    2018年4月26日 上午11:24:12 by qiangxuan
     */
    void updateTimeTaskById(Map<String, Object> paramsMap);
    /**
     * 
     * @description： 通过ID删除定时任务  数据访问接口类
     * @method:  deleteTimeTaskById
     * @param:   @param paramsMap
     * @return:  void
     * @date:    2018年4月26日 上午11:24:17 by qiangxuan
     */
    void deleteTimeTaskById(Map<String, Object> paramsMap);
    /**
     * 
     * @description： 获取定时任务总数  数据访问接口类
     * @method:  getTimeTaskCount
     * @param:   @param param
     * @param:   @return
     * @return:  int
     * @date:    2018年4月26日 上午11:24:23 by qiangxuan
     */
	int getTimeTaskCount(Map<String, Object> param);
	/**
	 * 
	 * @description： 批量删除定时任务  数据访问接口类
	 * @method:  delMoreTimeTask
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:24:27 by qiangxuan
	 */
	void delMoreTimeTask(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 暂停定时任务  数据访问接口类
	 * @method:  pauseTimeTaskById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:24:32 by qiangxuan
	 */
	void pauseTimeTaskById(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 启动定时任务  数据访问接口类
	 * @method:  startTimeTaskById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:24:37 by qiangxuan
	 */
	void startTimeTaskById(Map<String, Object> paramsMap);
	
	int checkEditTask(Map<String, Object> paramsMap);
	
	int checkAddTask(Map<String, Object> paramsMap);
	
	String queryNameById(String id);
	
	String queryStatusById(String id);
}
