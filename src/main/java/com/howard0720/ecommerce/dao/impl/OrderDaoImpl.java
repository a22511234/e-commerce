package com.howard0720.ecommerce.dao.impl;

import com.howard0720.ecommerce.dao.OrderDao;
import com.howard0720.ecommerce.dto.OrdertQueryParrams;
import com.howard0720.ecommerce.dto.ProductQueryParrams;
import com.howard0720.ecommerce.model.Order;
import com.howard0720.ecommerce.model.OrderItem;
import com.howard0720.ecommerce.rowmapper.OrderItemRowMapper;
import com.howard0720.ecommerce.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer creteOder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id,total_amount,created_date,last_modified_date) VALUES (:userId, :totalAmount, :createDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);
        Date date = new Date();
        map.put("createDate", date);
        map.put("lastModifiedDate", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOderItems(Integer orderId, List<OrderItem> orderItemdList) {
        String sql = "INSERT INTO order_item (order_id,product_id,quantity,amount) VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemdList.size()];

        for (int i = 0; i < orderItemdList.size(); i++) {
            OrderItem orderItem = orderItemdList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date from `order` WHERE order_id= :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id,oi.order_id,oi.product_id,oi.quantity,oi.amount,p.product_name,p.image_url from order_item as oi LEFT JOIN product p on oi.product_id = p.product_id WHERE oi.order_id= :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;
    }

    @Override
    public List<Order> getOrders(OrdertQueryParrams ordertQueryParrams) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date from `order` WHERE 1=1";

        Map<String, Object> map = new HashMap<>();
        sql = addFilterSql(sql, map, ordertQueryParrams);

        sql += " ORDER BY created_date DESC";

        sql += " LIMIT :limit OFFSET :offset";

        map.put("limit", ordertQueryParrams.getLimit());
        map.put("offset", ordertQueryParrams.getOffset());
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList;
    }

    @Override
    public Integer countOrder(OrdertQueryParrams ordertQueryParrams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        sql = addFilterSql(sql, map, ordertQueryParrams);
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    private String addFilterSql(String sql, Map<String, Object> map, OrdertQueryParrams ordertQueryParrams) {
        if (ordertQueryParrams.getUserId() != null) {
            sql += " AND user_id = :userId";
            map.put("userId", ordertQueryParrams.getUserId());
        }
        return sql;
    }
}
