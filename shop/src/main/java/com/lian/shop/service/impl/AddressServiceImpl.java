package com.lian.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lian.shop.entity.Address;
import com.lian.shop.entity.District;
import com.lian.shop.mapper.AddressMapper;
import com.lian.shop.service.IAddressService;
import com.lian.shop.service.IDistrictService;
import com.lian.shop.service.ex.AccessDeniedException;
import com.lian.shop.service.ex.AddressNotFoundException;
import com.lian.shop.service.ex.DeleteException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.UpdateException;



/**
 * 處理收貨地址的業務層實現類
 */
@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IDistrictService districtService;
	
	@Override
	public Address creat(String username, Address address) throws InsertException {
		// 通過address.getUid()得到用戶id，並以查询該用户的收貨地址數量
		Integer count = getCountByUid(address.getUid());

		address.setIsDefault(count == 0 ? 1 : 0);
		
	    //處理district:根據代號獲取district值
		String district = getDistrict(address.getProvince(),address.getCity(),address.getArea());
		address.setDistrict(district);
		
	    // 封裝日誌
		Date now = new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		
	    // 執行創建新地址
		addnew(address);
		return address;
	}
	@Override
	@Transactional
	public void setDefault(Integer uid, Integer id) {
		//根據id查詢收貨地址數據：findById(id)
		Address data = findById(id);
		//判斷數據是否為null
		if(data == null){
			throw new AddressNotFoundException("設置默認收貨地址失敗！嘗試訪問的收貨地址數據不存在！");
		}
		//// 判断查询到的數據中的uid與參數uid是否一致
	    if (data.getUid() != uid) {
	        throw new AccessDeniedException("設置默認收貨地址失敗！訪問數據權限驗證不通過！");
	    }
	
		//將該用戶的所有收貨地址設置為非默認
	    updateNonDefault(uid);
	    //將指定id的收貨地址設置為默認
	    updateDefault(id);
	}
	
	@Override
	public List<Address> getListByUid(Integer uid) {
		return findByUid(uid);
	}
	@Override
	public Address getById(Integer id) {
		return findById(id);
	}
	@Override
	@Transactional
	public void delete(Integer uid, Integer id) throws DeleteException {
		// 根據id查询收貨地址數據：findById(id)
		Address data = findById(id);
	    // 檢查數據是否為null
		if(data == null){
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("刪除收貨地址錯誤!");
		}
	    // 檢查數據歸屬是否有誤
		if(data.getUid() != uid){
	    // 是：抛出AccessDeniedException
			throw new AccessDeniedException("刪除收貨地址錯誤!");
		}
	    // 執行刪除
	    deleteById(id);
	    // 檢查還有沒有收貨地址數據：getCountByUid(uid)
	    if(getCountByUid(uid)>0){
		    // 是：判断剛才判断的是否是默認收貨地址
	    	if(data.getIsDefault() == 1){
			    // -- 是：獲取最後修改的收貨地址：findLastModified(uid)
	    		Integer lastModifiedId = findLastModified(uid).getId();
			    // -- 將最後修改的收貨地址設置為默認收貨地址
	    		setDefault(uid, lastModifiedId);
	    	}
	    }
	}
	
	
	/**
	 * 增加新收穫地址
	 * @param address　收貨地址數據
	 */
	private void addnew(Address address){
		Integer rows  = addressMapper.addnew(address);
	    if (rows != 1) {
	        throw new InsertException("新增地址錯誤");
	    }
	}
	/**
	 * 將某用戶的收貨地址全部設置為非默認
	 * @param uid 用戶id
	 */
	private void updateNonDefault(Integer uid) {
	    Integer rows = addressMapper.updateNonDefault(uid);
	    if (rows < 1) {
	        throw new UpdateException("修改默認收貨地址出現未知錯誤!");
	    }
	}
	/**
	 * 將指定id的收貨地址設為默認
	 * @param id 數據id
	 */
	private void updateDefault(Integer id) {
	    Integer rows = addressMapper.updateDefault(id);
	    if (rows != 1) {
	        throw new UpdateException("修改默認收貨地址出現未知錯誤!");
	    }
	}
	/**
	 * 根據用戶id獲取該用戶的收貨地址數據的數量
	 * @param uid　用戶id
	 * @return　該用戶id獲取該用戶的收貨地址數據的數量，如果沒數據，則返回０
	 */
	private Integer getCountByUid(Integer uid){
		return addressMapper.getCountByUid(uid);
	}
	
	/**
	 * 獲取某用户的收貨地址列表
	 * @param uid 用戶id
	 * @return 收貨地址列表
	 */
	private List<Address> findByUid(Integer uid) {
	    return addressMapper.findByUid(uid);
	}
	/**
	 * 根據id查詢收貨地址
	 * @param id 收貨地址id
	 * @return 匹配的收貨地址數據，如果沒有匹配的數據，則返回null
	 */
	private Address findById(Integer id){
		return addressMapper.findById(id);
	}
	/**
	 * 查找某用戶最後修改的收貨地址信息
	 * @param uid 用戶的id
	 * @return 匹配的數據，如果沒有匹配數據則返回null
	 */
	private Address findLastModified(Integer uid){
		return addressMapper.findLastModified(uid);
	}
	/**
	 * 根據id刪除收貨地址數據
	 * @param id 收貨地址數據的id
	 */
	private void deleteById(Integer id){
		Integer rows = addressMapper.deleteById(id);
		if(rows != 1){
			throw new DeleteException("刪除收貨地址時出現未知錯誤");
		}
	}
	
	/**
	 * 根據省/市/區代號獲取名稱
	 * @param province 省代號
	 * @param city 市代號
	 * @param code 區代號
	 * @return 省市區名稱
	 */
	private String getDistrict(String province,String city,String area){
		String provinceName = null;
		String cityName = null;
		String areaName = null;
		
		District p = districtService.getByCode(province);
		District c =districtService.getByCode(city);
		District a =districtService.getByCode(area);

		if(p != null){
			provinceName = p.getName();
		}		
		if(c != null){
			cityName = c.getName();
		}	
		if(a != null){
			areaName = a.getName();
		}		
		return provinceName+cityName + areaName;
	}
	
	
}
