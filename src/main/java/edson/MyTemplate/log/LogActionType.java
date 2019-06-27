package edson.MyTemplate.log;

/**
 * 用户动作类型记录
 */
public class LogActionType {

    public class ActionType{

        /**
         * 用户领取优惠券
         */
        public static final String ASSIGNED_TEMPLATE="assigned_template";

        /**
         * 用户消费优惠券
         */
        public static final String CONSUME_TEMPLATE="consume_template";

        /**
         *用户查看优惠券信息
         */
        public static final String PASSTEMPLATE_INFO="passtemplate_info";

        /**
         * 用户查看已使用的优惠券信息
         */
        public static final String USED_TEMPLATE_INFO="used_template_info";

        /**
         * 用户查看未使用的优惠券信息
         */
        public static final String UNUSED_TEMPLATE_INFO="UNUSED_TEMPLATE_INFO";

        /**
         * 用户创建评论
         */
        public static final String CREATE_FEEDBACK="CreateFeedback";

        /**
         * 用户查看评论
         */
        public static final String VIEW_FEEDBACK="viewFeedback";

        /**
         * 用户删除评论等
         */


    }

}
