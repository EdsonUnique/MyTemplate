package edson.MyTemplate.multiDataSource;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 15:45
 */
/**
 * @Description: 动态数据源切换
 */
public class DynamicDataSourceContextHolder {
    /***
     * @description: 使用ThreadLocal维护变量，TThreadLocal为每个使用该变量的县城提供独立的变量副本，
     * 所以每一个线程都可以独立改变自己的副本，而不会影响其他线程所对应的副本
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();


    /***
     * @description: 设置数据源变量
     */
    public static void setDataSource(String dataSource) {
//        System.out.println("切换到 "+ dataSource+"数据源");
        CONTEXT_HOLDER.set(dataSource);
    }

    /***
     * @description: 获取数据源变量
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
