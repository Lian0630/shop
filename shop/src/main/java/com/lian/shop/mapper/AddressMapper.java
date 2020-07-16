package com.lian.shop.mapper;

import java.util.List;

import com.lian.shop.entity.Address;


/**
 * 收貨地址的持久層接口
 */
public interface AddressMapper {
	/**
	 * 增加新收穫地址
	 * @param address　收貨地址數據
	 * @return　受影響行數
	 */
	Integer addnew(Address address);
	/**
	 * 將某用戶的收貨地址全部設置為非默認
	 * @param uid 用戶id
	 * @return 受影響行數
	 */
	Integer updateNonDefault(Integer uid);
	/**
	 * 將指定id的收貨地址設為默認
	 * @param id 數據id
	 * @return 受影響行數
	 */
	Integer updateDefault(Integer id);
		
	/**
	 * 根據用戶id獲取該用戶的收貨地址數據的數量
	 * @param uid　用戶id
	 * @return　該用戶id獲取該用戶的收貨地址數據的數量，如果沒數據，則返回０
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * 獲取某用戶的收貨地址
	 * @param uid 用戶id
	 * @return 收貨地址列表
	 */
	List<Address> findByUid(Integer uid);
	
	/**
	 * 根據id查詢收貨地址
	 * @param id 收貨地址id
	 * @return 匹配的收貨地址數據，如果沒有匹配數據則返回null
	 */
	Address findById(Integer id);
	/**
	 * 查找某用戶最後修改的收貨地址信息
	 * @param uid 用戶的id
	 * @return 匹配的數據，如果沒有匹配數據則返回null
	 */
	Address findLastModified(Integer uid);
	/**
	 * 根據id刪除收貨地址數據
	 * @param id 收貨地址數據的id
	 * @return 受影響行數
	 */
	Integer deleteById(Integer id);

}
