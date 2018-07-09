package cn.com.pc.auction.service.impl;

import cn.com.base.constant.BaseConstant;
import cn.com.base.service.ImageService;
import cn.com.base.util.BaseLogger;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.base.vo.auction.*;
import cn.com.base.vo.imageenum.ImageTableName;
import cn.com.mobile.auction.service.AuctionMobileService;
import cn.com.mobile.user.dao.UserDao;
import cn.com.model.auction.*;
import cn.com.pc.auction.dao.*;
import cn.com.pc.auction.service.AuctionPcService;
import cn.com.util.DateUtil;
import cn.com.util.NumberUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @类功能说明：拍卖业务实现层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/30 上午9:23
 * @版本：V1.0
 */
@Service
public class AuctionPcServiceImpl extends BaseLogger implements AuctionPcService {


    @Autowired
    private BaseConstant baseConstant;

    /**
     * 大小类
     */
    @Autowired
    private GoodsClassIficationPcMapper goodsClassIficationPcMapper;

    /**
     * 拍卖场次结拍时间
     */
    @Autowired
    private AuctionEndTimePcMapper auctionEndTimePcMapper;

    /**
     * 拍卖
     */
    @Autowired
    private GoodsPcMapper goodsPcMapper;

    /**
     * 商品出价Mapper
     */
    @Autowired
    private GoodsOfferRecordPcMapper offerRecordPcMapper;

    /**
     * 商品评论Mapper
     */
    @Autowired
    private GoodsCommentPcMapper commentPcMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuctionMobileService auctionMobileService;

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseParamVo getSizeOfClass(RequestParamVo vo) {
        try{
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();
            String classType = String.valueOf(bodyMap.get("class_type"));
            log.info("查询大小类类型为：{}",classType);
            if(!StringUtil.isNullOrBlank(classType))
            {
                List<GoodsClassIfication> lists = goodsClassIficationPcMapper.selectListByClassType(classType);
                if(lists != null && lists.size() > 0)
                {
                    List<ParentClassIfication> parentClassIficationsList = getParentAndSmallList(lists);
                    Map<String,Object> resultMap = new HashMap<>();
                    resultMap.put("data",parentClassIficationsList);
                    return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",resultMap);
                }
                else
                {
                    return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"大小类暂无数据","","","");
                }
            }
            else
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询大小类类型为空","","","");
            }
        }catch (Exception e)
        {
            log.error("查询大小类时异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询大小类异常","","","");
        }
    }

    @Override
    public ResponseParamVo getSession(RequestParamVo vo) {
        List<AuctionEndTime> endTimeLists = auctionEndTimePcMapper.selectAll();
        if(endTimeLists != null && endTimeLists.size() > 0)
        {
            Map<String,Object> resultMap = new HashMap<>();
            String session = endTimeLists.get(0).getAuctionSession();
            Integer days = endTimeLists.get(0).getDays();

            List<AuctionSessionDay> dasyList = new ArrayList<>();
            AuctionSessionDay day = null;
            List<Date> timeList = null;
            if(StringUtil.isNullOrBlank(session) || days == 0)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"暂无数据","","","");
            }
            else
            {
                for (int i = 0;i<days;i++) {
                    day = new AuctionSessionDay();
                    Date tempDay = DateUtil.getDateAfterDay(DateUtil.getTodayStartTime(),i);
                    String yearStr = DateUtil.getFormatDateByDate("yyyy-MM-dd",tempDay);

                    day.setDay(DateUtil.getDateByFormat(yearStr,"yyyy-MM-dd"));
                    timeList = new ArrayList<>();
                    for(String str : session.split(","))
                    {
                        String wholeTime = yearStr + str;
                        Date wholeDate = DateUtil.getDateByFormat(wholeTime,"yyyy-MM-ddHH:mm");
                        timeList.add(wholeDate);

                    }
                    day.setTimes(timeList);
                    dasyList.add(day);
                }
            }
            resultMap.put("data",dasyList);
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",resultMap);
        }
        else
        {
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"暂无数据","","","");
        }
    }

    @Override
    public ResponseParamVo publishAcution(RequestParamVo vo) {
        String userId = vo.getHeader().getUser_id();
        if(StringUtil.isNullOrBlank(userId))
        {
            log.error("发布拍卖，用户ID为空");
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"用户ID为空","","","");
        }

        try {
            String bodyJson = objectMapper.writeValueAsString(vo.getBody()).toString();
            GoodsRequestParam goodsParam = objectMapper.readValue(bodyJson,GoodsRequestParam.class);
            Goods goods = new Goods();
            goods.setGoodsDescription(goodsParam.getGoods_description());
            goods.setCityCode(goodsParam.getCity_code());
            goods.setGoodsName(goodsParam.getGoods_name());
            //goods.setMobileImageIds(goodsParam.getMobile_image_ids());
            goods.setStartPrice(goodsParam.getStart_price());
            goods.setMarkupPrice(goodsParam.getMarkup_price());
            goods.setBigClassCode(goodsParam.getBig_class_code());
            goods.setSmallClassCode(goodsParam.getSmall_class_code());
            goods.setInventory(goodsParam.getInventory());
            goods.setPostage(goodsParam.getPostage());
            goods.setId(NumberUtil.getSysJournalNo(32,false));
            goods.setUserId(vo.getHeader().getUser_id());
            goods.setGoodsType("2");//默认拍卖类型
            goods.setStatus("1");//默认上架
            goods.setFirstStepTime(new Date());
            goods.setBrowseNum(0l);
            goods.setPublishCustomer(vo.getHeader().getUser_id());
            goods.setUserId(vo.getHeader().getUser_id());
            goods.setPublishTime(new Date());
            goods.setCurrentPrice(goods.getStartPrice());
            goods.setCreateTime(new Date());
            goods.setDelFlag("0");

            //大类和小类二选其一
            if(StringUtil.isNullOrBlank(goods.getSmallClassCode()) && StringUtil.isNullOrBlank(goods.getBigClassCode()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"大类和小类必须选择一个","","","");
            }

            //商品标题
            if(StringUtil.isNullOrBlank(goods.getGoodsName()) || goods.getGoodsName().length() > 50)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"商品标题不能为空并且长度必须小于50个字","","","");
            }

            //商品描述
            if(StringUtil.isNullOrBlank(goods.getGoodsDescription()) || goods.getGoodsDescription().length() > 500)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"商品描述不能为空并且长度必须小于500个字","","","");
            }

            //城市Code
            if(StringUtil.isNullOrBlank(goods.getCityCode()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"城市编码不能为空","","","");
            }

            //商品描述
            if(StringUtil.isNullOrBlank(goods.getGoodsDescription()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"描述不能为空","","","");
            }

            //起拍价和竞价
            if(goods.getStartPrice() == null || goods.getMarkupPrice() == null)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"起拍价或竞拍价不能为空","","","");
            }

            //结拍时间
            if(goodsParam.getEnd_time() == null || goodsParam.getEnd_time() == 0l)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"结拍时间不能为空","","","");
            }
            else
            {
                goods.setEndTime(DateUtil.generatorDateByTimestamp(goodsParam.getEnd_time()));
            }

            //图片
            if(StringUtil.isNullOrBlank(goodsParam.getImage_ids()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"图片ID不能为空","","","");
            }

            goodsPcMapper.insertSelective(goods);

            log.info("============存储商品附件开始,商品ID:{}==================",goods.getId());
            Integer sort = 0;
            List<SysImageService> imageServiceList = new ArrayList<>();
            SysImageService imageRel = null;
            for(String str : goodsParam.getImage_ids().split(","))
            {
                sort++;
                imageRel = new SysImageService();
                imageRel.setId(NumberUtil.getSysJournalNo(32,false));
                imageRel.setImageId(str);
                imageRel.setServiceId(goods.getId());
                imageRel.setSort(sort);
                imageServiceList.add(imageRel);
            }
            imageService.batchSaveImageService(imageServiceList, ImageTableName.GOODS_TABLE_NAME.getTableName());
            log.info("===============发布拍卖成功==================");
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"发布拍卖成功","","","");
        } catch (Exception e) {
            log.error("发布拍卖异常,原因为:{}",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"发布拍卖异常","","","");
        }
    }

    @Override
    public ResponseParamVo getAuctionList(RequestParamVo vo) {

        try {
            String bodyJson = objectMapper.writeValueAsString(vo.getBody()).toString();
            GoodsQueryParams queryParams = objectMapper.readValue(bodyJson,GoodsQueryParams.class);
            queryParams.setPage_num(queryParams.getPage_num() == null ? baseConstant.getPageNum() : queryParams.getPage_num());
            queryParams.setPage_size(queryParams.getPage_size() == null ? baseConstant.getPageSize() : queryParams.getPage_size());
            if(queryParams.getEnd_time() != null && queryParams.getEnd_time() != 0l)
            {
                queryParams.setEnd_time_date(new Date(queryParams.getEnd_time()));
            }
            //queryParams.setStartTime(new Date());
            queryParams.setUser_id(vo.getHeader().getUser_id());
            //queryParams.setEndTime(DateUtil.getDateAfterHour(new Date(),2));
            //queryParams.setStartCreateTime((DateUtil.getDateAfterHour(new Date(),-2)));
            PageHelper.startPage(queryParams.getPage_num(),queryParams.getPage_size());
            List<AuctionGoods> auctionList = goodsPcMapper.getGoodsByType(queryParams);
            PageInfo<AuctionGoods> pageInfo = new PageInfo<>(auctionList);
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",pageInfo);
        } catch (Exception e) {
            log.error("查询拍卖列表异常，原因为：{}",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询拍卖列表异常","","","");
        }
    }

    @Override
    public ResponseParamVo getOtherAuctionsByUserId(RequestParamVo vo) {

        try {
            String bodyJson = objectMapper.writeValueAsString(vo.getBody()).toString();
            GoodsQueryParams queryParams = objectMapper.readValue(bodyJson,GoodsQueryParams.class);
            queryParams.setPage_num(queryParams.getPage_num() == null ? baseConstant.getPageNum() : queryParams.getPage_num());
            queryParams.setPage_size(queryParams.getPage_size() == null ? baseConstant.getPageSize() : queryParams.getPage_size());
            if(queryParams.getEnd_time() != null && queryParams.getEnd_time() != 0l)
            {
                queryParams.setEnd_time_date(new Date(queryParams.getEnd_time()));
            }
            PageHelper.startPage(queryParams.getPage_num(),queryParams.getPage_size());
            List<AuctionGoods> auctionList = goodsPcMapper.getOtherAuctionsByUserId(queryParams);
            PageInfo<AuctionGoods> pageInfo = new PageInfo<>(auctionList);
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",pageInfo);
        } catch (Exception e) {
            log.error("查询拍卖列表异常，原因为：{}",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询拍卖列表异常","","","");
        }
    }

    @Override
    public ResponseParamVo getGoodsDetailById(RequestParamVo vo) {
        Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();
        if(bodyMap == null || StringUtil.isNullOrBlank(String.valueOf(bodyMap.get("goods_id"))))
        {
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"加载拍卖详情时，拍卖ID不能为空","","","");
        }
        Map<String,Object> reusltMap = null;
        try {
            reusltMap = new HashMap<>();
            String goodsId = String.valueOf(bodyMap.get("goods_id"));
            //加载图片详情
            AuctionGoodsDetail goodsDetail = goodsPcMapper.getGoodsDetailById(goodsId);
            List<GoodsOfferRecordDetail> offerRecordDetails = offerRecordPcMapper.getTopFiveOfferRecord(goodsId);

            //加载前五个出价记录
            Map<String,Object> offerResultMap = new HashMap<>();
            offerResultMap.put("offer_count",goodsDetail.getOffer_count());
            offerResultMap.put("data_list" , offerRecordDetails);


            //我的在售宝贝（拍卖）
            Integer inSaleNum = goodsPcMapper.getInSaleOfMyGoods(goodsDetail.getUser_id());
            //我的已售宝贝（拍卖）
            Integer outSaleNum = goodsPcMapper.getOutSaleOfMyGoods(goodsDetail.getUser_id());
            //卖家店铺评论总分
            Integer totalScore = goodsPcMapper.getTotalScoreOfMyShop(goodsDetail.getUser_id());
            //卖家店铺总评论数
            Integer totalOrder = goodsPcMapper.getTotalOrderOfMyShop(goodsDetail.getUser_id());

            Map<String,Object> storeInfoMap = new HashMap<>();
            storeInfoMap.put("in_sale_num",inSaleNum);
            storeInfoMap.put("out_sale_num",outSaleNum);
            if(totalOrder == 0)
            {
                storeInfoMap.put("praise_rate", 100);
            }
            else
            {
                storeInfoMap.put("praise_rate", NumberUtil.divide(totalScore, totalOrder,2));
            }
            storeInfoMap.put("user_id",goodsDetail.getUser_id());
            storeInfoMap.put("store_name",goodsDetail.getNick_name());
            storeInfoMap.put("head_url",goodsDetail.getHead_url());
            storeInfoMap.put("head1_url",goodsDetail.getHead1_url());
            storeInfoMap.put("head2_url",goodsDetail.getHead2_url());

            reusltMap.put("goods_detail" ,goodsDetail);//存放商品详情
            reusltMap.put("offer_detail",offerResultMap);//存放出价信息
            reusltMap.put("store_info",storeInfoMap);//存放该店铺信息

            //浏览量加1
            Goods goods = goodsPcMapper.selectByPrimaryKey(goodsId);
            if(goods != null)
            {
                goods.setBrowseNum(goods.getBrowseNum() + 1);
                goodsPcMapper.updateByPrimaryKey(goods);
                log.info("刷新浏览量成功");

                auctionMobileService.addMyTrack(goodsId,vo.getHeader().getUser_id(),"2");
                log.info("添加我的足迹成功");
            }


            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"获取拍卖详情成功","","",reusltMap);
        } catch (Exception e) {
            log.error("加载拍卖详情异常，ID为：{}",bodyMap.get("goods_id"));
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"加载拍卖详情异常","","","");
        }
    }

    @Override
    public ResponseParamVo addGoodsOfferRecord(RequestParamVo vo) {
        try {
            String bodyJson = objectMapper.writeValueAsString(vo.getBody()).toString();
            GoodsOfferRecordDetail goodsOfferRecordDetail = objectMapper.readValue(bodyJson,GoodsOfferRecordDetail.class);
            if(StringUtil.isNullOrBlank(goodsOfferRecordDetail.getGoods_id()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"新增出价时商品ID不能为空","","","");
            }
            if(StringUtil.isNullOrBlank(goodsOfferRecordDetail.getUser_id()))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"新增出价时出价人ID不能为空","","","");
            }
//            if(StringUtil.isNullOrBlank(goodsOfferRecordDetail.getNick_name()))
//            {
//                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"新增出价时出价人昵称不能为空","","","");
//            }
            if(goodsOfferRecordDetail.getOffer_price() == null)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"新增出价时出价不能为空","","","");
            }

            Map<String,String> paraMap = new HashMap<>();
            paraMap.put("userId",goodsOfferRecordDetail.getUser_id());
            List<Object> userList = userDao.getUserList(paraMap);

            //判断出价人是否存在
            GoodsOfferRecord goodsOfferRecord = new GoodsOfferRecord();
            if(userList == null || userList.size() <= 0)
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"当前出价人不存在","","","");
            }
            else
            {
                Map<String,Object> resultMap = (Map<String, Object>) userList.get(0);
                if(resultMap.containsKey("nick_name"))
                {
                    goodsOfferRecord.setNickName(resultMap.get("nick_name").toString());
                }
            }

            goodsOfferRecord.setId(NumberUtil.getSysJournalNo(32,false));
            goodsOfferRecord.setCreateTime(new Date());
            goodsOfferRecord.setCustomerId(goodsOfferRecordDetail.getUser_id());
            goodsOfferRecord.setGoodsId(goodsOfferRecordDetail.getGoods_id());
            goodsOfferRecord.setOfferPrice(goodsOfferRecordDetail.getOffer_price());
            offerRecordPcMapper.insertSelective(goodsOfferRecord);
            log.info("新增出价记录成功，出价人ID:{}", goodsOfferRecord.getCustomerId());

            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"新增出价记录成功","","","");

        } catch (Exception e) {
            log.error("出价异常，原因为：{}",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询拍卖列表异常","","","");
        }
    }

    @Override
    public ResponseParamVo getGoodsOfferRecord(RequestParamVo vo) {
        Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();
        Integer pageNum = baseConstant.getPageNum();
        Integer pageSize = baseConstant.getPageSize();
        String goodsId = "";
        if(bodyMap != null)
        {
            goodsId = String.valueOf(bodyMap.get("goods_id"));
            String tempPageNum = String.valueOf(bodyMap.get("page_num").toString());
            String tempPageSize = String.valueOf(bodyMap.get("page_size").toString());
            if(!StringUtil.isNullOrBlank(tempPageNum))
            {
                pageNum = Integer.parseInt(tempPageNum);
            }
            if(!StringUtil.isNullOrBlank(tempPageSize))
            {
                pageSize = Integer.parseInt(tempPageSize);
            }
        }

        if(StringUtil.isNullOrBlank(goodsId))
        {
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询拍卖出价记录ID不能为空","","","");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<GoodsOfferRecordDetail> offerRecords = offerRecordPcMapper.selectAllByGoodsId(goodsId);
        PageInfo<GoodsOfferRecordDetail> pageInfo = new PageInfo<>(offerRecords);
        return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",pageInfo);
    }

    @Override
    public ResponseParamVo getAcutionCommentList(RequestParamVo vo) {
        Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();
        Integer pageNum = baseConstant.getPageNum();
        Integer pageSize = baseConstant.getPageSize();
        String goodsId = "";
        String commentType = "";
        if(bodyMap != null)
        {
            goodsId = String.valueOf(bodyMap.get("goods_id"));
            commentType = String.valueOf(bodyMap.get("comment_type").toString());
            String tempPageNum = String.valueOf(bodyMap.get("page_num").toString());
            String tempPageSize = String.valueOf(bodyMap.get("page_size").toString());
            if(!StringUtil.isNullOrBlank(tempPageNum))
            {
                pageNum = Integer.parseInt(tempPageNum);
            }
            if(!StringUtil.isNullOrBlank(tempPageSize))
            {
                pageSize = Integer.parseInt(tempPageSize);
            }
        }

        if(StringUtil.isNullOrBlank(goodsId))
        {
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询拍卖出价记录ID不能为空","","","");
        }

        PageHelper.startPage(pageNum,pageSize);

        List<GoodsCommentList> commentLists = commentPcMapper.selectByGoodsIdAndCommentType(goodsId,commentType);
        PageInfo<GoodsCommentList> pageInfo = new PageInfo<>(commentLists);
        return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询成功","","",pageInfo);
    }

    /**
     * @description 根据大小类数据封装对象
     * @method  getParentAndSmallList
     * @param  * @param list 大小类集合
     * @return java.util.List<cn.com.base.vo.auction.ParentClassIfication>
     * @date: 2018/3/30 15:46:18
     * @author:zhoujinbing
     */
    public List<ParentClassIfication> getParentAndSmallList(List<GoodsClassIfication> list)
    {
        List<ParentClassIfication> parentClassIficationsList = null;
        if(list != null && list.size() > 0)
        {
            parentClassIficationsList = new ArrayList<>();
            ParentClassIfication parentClassIfication = null;
            for(GoodsClassIfication classIfication :list)
            {
                //判断是否是大类
                if(classIfication.getParentCode().equals("0"))
                {
                    parentClassIfication = new ParentClassIfication();
                    parentClassIfication.setParent_class_code(classIfication.getClassCode());
                    parentClassIfication.setParent_class_id(classIfication.getId());
                    parentClassIfication.setParent_class_name(classIfication.getClassName());
                    parentClassIfication.setImageUrl(classIfication.getImageUrl());
                    parentClassIfication.setImage1Url(classIfication.getImage1Url());
                    parentClassIfication.setImage2Url(classIfication.getImage2Url());
                    parentClassIfication.setImage3Url(classIfication.getImage3Url());
                    parentClassIfication.setImage4Url(classIfication.getImage4Url());

                    //获取当前当类中的小类
                    List<ChildClassIfication> childClassIficationsList = new ArrayList<>();

                    //添加全部
                    ChildClassIfication all = new ChildClassIfication();
                    all.setClass_code("");
                    all.setClass_id("");
                    all.setClass_name("全部");
                    all.setImageUrl("");
                    all.setImage1Url("");
                    all.setImage2Url("");
                    all.setImage3Url("");
                    all.setImage4Url("");
                    //childClassIficationsList.add(all);

                    ChildClassIfication childClassIfication = null;
                    //判断大类中是否包含小类
                    for(GoodsClassIfication childClass : list)
                    {
                        if(childClass.getParentCode().equals(classIfication.getClassCode()))
                        {
                            childClassIfication = new ChildClassIfication();
                            childClassIfication.setClass_code(childClass.getClassCode());
                            childClassIfication.setClass_id(childClass.getId());
                            childClassIfication.setClass_name(childClass.getClassName());
                            childClassIfication.setImageUrl(childClass.getImageUrl());
                            childClassIfication.setImage1Url(childClass.getImage1Url());
                            childClassIfication.setImage2Url(childClass.getImage2Url());
                            childClassIfication.setImage3Url(childClass.getImage3Url());
                            childClassIfication.setImage4Url(childClass.getImage4Url());
                            childClassIficationsList.add(childClassIfication);
                        }
                    }
                    parentClassIfication.setSmall_class_list(childClassIficationsList);
                    parentClassIficationsList.add(parentClassIfication);

                }
            }
        }

        return parentClassIficationsList;
    }
}
