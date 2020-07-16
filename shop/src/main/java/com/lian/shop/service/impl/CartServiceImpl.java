package com.lian.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lian.shop.entity.Address;
import com.lian.shop.entity.Cart;
import com.lian.shop.mapper.CartMapper;
import com.lian.shop.service.ICartService;
import com.lian.shop.service.ex.AccessDeniedException;
import com.lian.shop.service.ex.AddressNotFoundException;
import com.lian.shop.service.ex.CartNotFoundException;
import com.lian.shop.service.ex.DeleteException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.UpdateException;
import com.lian.shop.vo.CartVO;


/**
 * 購物車業務層實現類
 */
@Service
public class CartServiceImpl implements ICartService{
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public void addToCart(String uername,Cart cart) throws InsertException, UpdateException {
		//獲取當前時間
		Date now = new Date();
		// 根據cart中的uid和gid查數據
		Integer uid = cart.getUid();
		Long goodsId = cart.getGid();
		Cart data = findByUidAndGid(uid, goodsId);
	    // 判斷查詢結果是否是null
		if(data == null){
			// 是：此用户未在購物車中中添加該商品，則新增
			cart.setCreatedUser(uername);
			cart.setCreatedTime(now);
			cart.setModifiedUser(uername);
			cart.setModifiedTime(now);
			addnew(cart);
		}else{
			// 否：該用戶已經在購物車中添加該此商品，則取出此前查詢到的數據中的id和count
			Integer dataId = data.getId();
			Integer oldCount = data.getCount();
			// -- 根据上一步取出的count與參數cart中的count（此次用户提交的count），相加得到新的count
			Integer newCount = oldCount + cart.getCount();
		    // -- 更新	
			updateCount(dataId, newCount);
		}
	    
	    
	}
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}
	@Override
	public void addCount(Integer id, Integer uid) throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据id查數據
		Cart data = findById(id);
	    // 判斷數據是否是null
		if(data==null){
			// 是：拋出異常：CartNotFoundException
			throw new CartNotFoundException("嘗試訪問的購物車數據不存在!");
		}
	    // 判斷數據歸屬是否不匹配
		if(!data.getUid().equals(uid)){
			// 是：拋出異常：AccessDeniedException
			throw new AccessDeniedException("修改商品數量，訪問數據權限驗證不通過!");
		}
	    // 獲取原來的數量
		Integer count = data.getCount();
	    // 數量+1
		count ++;
	    // 更新購物車中數據的數量:updateCount(id, count)
		updateCount(id, count);
	}
	
	@Override
	public void reduceCount(Integer id, Integer uid) throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据id查數據
		Cart data = findById(id);
	    // 判斷數據是否是null
		if(data==null){
			// 是：拋出異常：CartNotFoundException
			throw new CartNotFoundException("嘗試訪問的購物車數據不存在!");
		}
	    // 判斷數據歸屬是否不匹配
		if(!data.getUid().equals(uid)){
			// 是：拋出異常：AccessDeniedException
			throw new AccessDeniedException("修改商品數量，訪問數據權限驗證不通過!");
		}
	    // 獲取原來的數量
		Integer count = data.getCount();
		
	    // 數量-1
		if(count > 1){
			count -- ;
			};
		
	    // 更新購物車中數據的數量:updateCount(id, count)
		updateCount(id, count);
	}
	
	
	
	
	@Override
	public List<CartVO> getByIds(Integer[] ids) {
		return findByIds(ids);
	}
	
	
	@Override
	@Transactional
	public void delete(Integer uid, Integer id) throws DeleteException {
		// 根據id查询購物車數據：findById(id)
		Cart data = findById(id);
	    // 檢查數據是否為null
		if(data == null){
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("刪除購物車數據錯誤!");
		}
	    // 檢查數據歸屬是否有誤
		if(data.getUid() != uid){
	    // 是：抛出AccessDeniedException
			throw new AccessDeniedException("刪除購物車數據錯誤!");
		}
	    // 執行刪除
	    deleteById(id);
	   
	}
	
	
	
	@Override
	@Transactional
	public void deleteByUid(Integer uid) throws DeleteException{
		List<CartVO> data = findByUid(uid);
		if(data == null){
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("刪除購物車數據錯誤!");
		}
	   
	    // 執行刪除
	    deleteUid(uid);
	   	
	}
	
	/**
	 * 根據用戶id和商品id查詢購物車數據
	 * @param uid 用戶id
	 * @param moneyId 商品id
	 * @return 匹配的購物車數據，如果沒有匹配的數據則返回null
	 */
	private Cart findByUidAndGid(@Param("uid") Integer uid, @Param("moneyId") Long moneyId){
		return cartMapper.findByUidAndGid(uid,moneyId);
	}
	/**
	 * 根據id獲取購物車數據
	 * @param id 購物車數據id
	 * @return 匹配的購物車數據，如果沒有匹配的數據則返回null
	 */
	private Cart findById(Integer id){
		return cartMapper.findById(id);
	}
	/**
	 * 新增購物車數據
	 * @param cart 購物車數據
	 */
	private void addnew(Cart cart){
		Integer rows = cartMapper.addnew(cart);
		if(rows !=1){
			throw new InsertException("創建購物車數據時發生未知錯誤!");
		}
	}
	/**
	 * 更新購物車中的商品數量
	 * @param id 購物車數據id
	 * @param count 新的數量
	 */
	private void updateCount(@Param("id") Integer id, @Param("count") Integer count){
		Integer rows = cartMapper.updateCount(id, count);
		if(rows !=1){
			throw new UpdateException("修改購物車商品數量時發生未知錯誤");
		}
	}
	
	/**
	 * 根據用戶id查詢該用戶的購物車數據列表
	 * @param uid 用戶id
	 * @return 該用戶的購物車數據列表
	 */
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}
	
	/**
	 * 根據無數個id查詢匹配的購物車數據集合
	 * @param ids 無數個id
	 * @return 匹配的購物車數據集合
	 */
	private List<CartVO> findByIds(Integer[] ids){
		return cartMapper.findByIds(ids);
	}


	/**
	 * 根據id刪除購物車數據
	 * @param id 購物車數據的id
	 */
	private void deleteById(Integer id){
		Integer rows = cartMapper.deleteById(id);
		if(rows != 1){
			throw new DeleteException("刪除購物車數據時出現未知錯誤");
		}
	}

	/**
	 * 根據uid刪除購物車數據
	 * @param uid
	 */
	private void deleteUid(Integer uid){
		Integer rows = cartMapper.deleteByUid(uid);
		if(rows < 1){
			throw new DeleteException("購物車無數據");
		}
	}
	
	
	
}
