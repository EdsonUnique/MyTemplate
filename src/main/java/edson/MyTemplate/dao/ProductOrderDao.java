package edson.MyTemplate.dao;

import edson.MyTemplate.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductOrderDao extends JpaRepository<Order,String> {



}
