# Service层

- 注意在接口和实现类上都需加`@Service`注解
- 对于数据库的更新操作需要添加事务，`@Transactional`注解使得操作失败后事务回滚。
- 选择返回的是数据库模型对象还是值对象。
- 分页查询时PageRequest是Pageable的子类，注意其创建方式：PageRequest.of(...)



