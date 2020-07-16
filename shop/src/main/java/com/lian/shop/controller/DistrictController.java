package com.lian.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lian.shop.entity.District;
import com.lian.shop.service.IDistrictService;
import com.lian.shop.util.ResponseResult;



@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
	@Autowired
	private IDistrictService districtService;
	
	@RequestMapping("/list/{parent}")
	public ResponseResult<List<District>> getListByParent(@PathVariable("parent") String parent){
		List<District> list = districtService.getListByParent(parent);
		return new ResponseResult<List<District>>(SUCCESS,list);
	}
	
}
