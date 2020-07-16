package com.lian.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lian.shop.entity.District;
import com.lian.shop.mapper.DistrictMapper;
import com.lian.shop.service.IDistrictService;

/**
 * 處理省市區的業務層實現類
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private DistrictMapper districtMapper;

	@Override
	public List<District> getListByParent(String parent) {
		return findByParent(parent);
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}

	/**
	 * 根據父級代號獲取子級的省/市/區的列表
	 * @param parent 父級代號，如果需要獲取省的列表，則父級代號為86
	 * @return 省/市/區的列表
	 */
	private List<District> findByParent(String parent) {
		return districtMapper.findByParent(parent);
	}


	
	/**
	 * 根據代號獲取省/市/區的詳情
	 * @param code 省/市/區的代號
	 * @return 省/市/區的詳情，如果沒有匹配的數據，則返回null
	 */
	private District findByCode(String code) {
		return districtMapper.findByCode(code);
	}


	
	
}
