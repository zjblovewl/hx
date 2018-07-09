package cn.com.base.vo.auction;

import java.util.Date;

/**
 * @类功能说明：分页查询评论bean
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/8 上午15:38
 * @版本：V1.0
 */
public class GoodsCommentList {

    private String comment_user_id;//评论人ID

    private String comment_nick_name;//评论人昵称

    private String comment_head_url;//评论人头像(原图)

    private String comment_head1_url;//评论人头像(200*200)

    private String comment_head2_url;//评论人头像(800*800)

    private String replay_user_id;//被回复人ID

    private String replay_nick_name;//被回复人昵称

    private String replay_head_url;//备回复人头像(原图)

    private String replay_head1_url;//备回复人头像(200*200)

    private String replay_head2_url;//备回复人头像(800*800)

    private String comment_content;//评论内容

    private Date create_time;//创建时间

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    private String comment_type;//评论类型 （1、评论 2、回复）

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getComment_head1_url() {
        return comment_head1_url;
    }

    public void setComment_head1_url(String comment_head1_url) {
        this.comment_head1_url = comment_head1_url;
    }

    public String getComment_head2_url() {
        return comment_head2_url;
    }

    public void setComment_head2_url(String comment_head2_url) {
        this.comment_head2_url = comment_head2_url;
    }

    public String getReplay_head1_url() {
        return replay_head1_url;
    }

    public void setReplay_head1_url(String replay_head1_url) {
        this.replay_head1_url = replay_head1_url;
    }

    public String getReplay_head2_url() {
        return replay_head2_url;
    }

    public void setReplay_head2_url(String replay_head2_url) {
        this.replay_head2_url = replay_head2_url;
    }

    public String getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(String comment_user_id) {
        this.comment_user_id = comment_user_id;
    }

    public String getComment_nick_name() {
        return comment_nick_name;
    }

    public void setComment_nick_name(String comment_nick_name) {
        this.comment_nick_name = comment_nick_name;
    }

    public String getComment_head_url() {
        return comment_head_url;
    }

    public void setComment_head_url(String comment_head_url) {
        this.comment_head_url = comment_head_url;
    }

    public String getReplay_user_id() {
        return replay_user_id;
    }

    public void setReplay_user_id(String replay_user_id) {
        this.replay_user_id = replay_user_id;
    }

    public String getReplay_nick_name() {
        return replay_nick_name;
    }

    public void setReplay_nick_name(String replay_nick_name) {
        this.replay_nick_name = replay_nick_name;
    }

    public String getReplay_head_url() {
        return replay_head_url;
    }

    public void setReplay_head_url(String replay_head_url) {
        this.replay_head_url = replay_head_url;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

}
