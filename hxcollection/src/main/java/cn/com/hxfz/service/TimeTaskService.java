package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.util.mybatise.DataSource;

/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.service
 * @作者: qiangxuan
 * @时间: 2018/3/16 15:19
 * @版本: V1.0
 */
public interface TimeTaskService {


	/**
	 * 
	 * 方法功能说明：项目启动加载已存在的定时任务  
	 * 创建：@Date by qiangxuan 
	 * 调用类路径：cn.com.hxfz.task.quartz.LoadTask
	 * @version 1.0         
	 * @throws
	 */
    @DataSource("master")
    List<Map<String, Object>> getTaskList();
    

    /**
     * 
      * 方法功能说明： 前台获取定时任务列表
      * 创建：2018年3月27日 by qiangxuan
      * 返回类型：List<Map<String,Object>>
      * 参数： TODO
     */
    @DataSource("master")
	public List<Map<String, Object>> getTimeTask(Map<String, Object> param);
    
    /**
     * 
     * @description 新增定时任务
     * @method  addTimeTask
     * @param @param paramsMap
     * @return void
     * @date: 2018年4月2日 下午10:28:25
     * @author:qiangxuan
     * @throws ClassNotFoundException 
     */
    public void saveTimeTask(Map<String, Object> paramsMap) throws ClassNotFoundException;

    /**
     * 
     * @description 编辑定时任务
     * @method  updateTimeTask
     * @param @param paramsMap
     * @return void
     * @date: 2018年4月2日 下午10:28:49
     * @author:qiangxuan
     */
    public void updateTimeTask(Map<String, Object> paramsMap);

    /**
     * 
     * @description 删除定时任务
     * @method  deleteTimeTask
     * @param @param paramsMap
     * @return void
     * @date: 2018年4月2日 下午10:29:19
     * @author:qiangxuan
     */
    public void deleteTimeTask(Map<String, Object> paramsMap);

    /**
     * 
     * @description 获取定时任务总数量
     * @method  getTimeTaskCount
     * @param @param param
     * @param @return
     * @return int
     * @date: 2018年4月2日 下午10:29:36
     * @author:qiangxuan
     */
	int getTimeTaskCount(Map<String, Object> param);

	/**
	 * 
	 * @description： 通过id批量删除定时任务
	 * @method:  delMoreTimeTask
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:16:45 by qiangxuan
	 */
	void delMoreTimeTask(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description： 通过ID暂停定时任务
	 * @method:  pauseTimeTaskById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:17:06 by qiangxuan
	 */
	void pauseTimeTaskById(Map<String, Object> paramsMap);

	/**
	 * 
	 * @description： 通过ID启动定时任务
	 * @method:  startTimeTaskById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月26日 上午11:21:03 by qiangxuan
	 */
	void startTimeTaskById(Map<String, Object> paramsMap);

	/**
	 * 
	 * @description： 校验定时任务名称的唯一性
	 * @method:  checkSameTaskName
	 * @param:   @param paramsMap
	 * @param:   @return
	 * @return:  Boolean
	 * @date:    2018年4月27日 上午10:29:02 by qiangxuan
	 */
	Boolean checkSameTaskName(Map<String, Object> paramsMap);

	/**
	 * 
	 * @description： 通过ID查询定时任务名称
	 * @method:  queryNameById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年4月27日 上午10:29:26 by qiangxuan
	 */
	String queryNameById(String id);


	String queryStatusById(String id);
    
}
