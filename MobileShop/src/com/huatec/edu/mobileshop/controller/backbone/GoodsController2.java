package com.huatec.edu.mobileshop.controller.backbone;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatec.edu.mobileshop.service.GoodsService;
import com.huatec.edu.mobileshop.util.MSUtil;
import com.huatec.edu.mobileshop.util.Result;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/backbone/goods")
public class GoodsController2 {
	@Resource
	private GoodsService goodsService;
	//前台使用backbone框架
	//新增商品
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="新增商品")
	public Result add2(HttpServletRequest request){
		System.out.println("/backbone/goods");
		Map jmap=MSUtil.getParam(request);
		String name=(String) jmap.get("name");
        String sn=(String) jmap.get("sn");
        String brief=(String) jmap.get("brief");
        String description=(String) jmap.get("description");
        double price=Double.parseDouble((String) jmap.get("price"));
        double cost=Double.parseDouble((String) jmap.get("cost"));
        double mktprice=Double.parseDouble((String) jmap.get("mktprice"));
        int catId=Integer.parseInt((String) jmap.get("catId"));
        int brandId=Integer.parseInt((String) jmap.get("brandId"));
        double weight=Double.parseDouble((String) jmap.get("weight"));
        String intro=(String) jmap.get("intro");
        Result result=goodsService.addGoods(name, sn, brief, description, 
				price, cost, mktprice, catId, brandId, weight, intro);
		return result;
	}
		
		
	//根据分类名称或品牌名称或商品关键字查询
	@RequestMapping(value="/find",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="搜索商品")
	public Result search(String input){
		/*Map jmap=getParam(request);
		String input=(String) jmap.get("input");
		System.out.println("input="+input);*/
		Result result =new Result();
		System.out.println("input1="+input);
		try {
			 input = URLDecoder.decode(input, "UTF-8");
			 System.out.println("input2="+input);
			 result=goodsService.searchGoods(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/*//根据分类名称或品牌名称或商品关键字查询
		@RequestMapping(value="/find/{input}",method=RequestMethod.GET)
		@ResponseBody
		@ApiOperation(value="搜索商品")
		public Result search(@PathVariable("input") String input){
			System.out.println("/backbone/goods/find");
			Map jmap=getParam(request);
			String input=(String) jmap.get("input");
			   System.out.println("input="+input);
			Result result =new Result();
			try {
				 input = URLDecoder.decode(input, "UTF-8");
				 System.out.println(input);
				 result=goodsService.searchGoods(input);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
			return result;
		}
	*/
	
	//根据id更新
	@RequestMapping(value="/{goodsId}",method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新商品信息")
	public Result update(HttpServletRequest request){
		
		Map jmap=MSUtil.getParam(request);
		int goodsId=2;
		System.out.println("goodsId:"+goodsId);
		//int goodsId=Integer.parseInt((String) jmap.get("goodsId"));
		String name=(String) jmap.get("name");
        String sn=(String) jmap.get("sn");
        String brief=(String) jmap.get("brief");
        String description=(String) jmap.get("description");
        double price=Double.parseDouble((String) jmap.get("price"));
        double cost=Double.parseDouble((String) jmap.get("cost"));
        double mktprice=Double.parseDouble((String) jmap.get("mktprice"));
        //int mktEnable=(int) jmap.get("mktEnable");
        int mktEnable=Integer.parseInt((String) jmap.get("mktEnable"));
        System.out.println("mktEnable:"+mktEnable);
        int catId=Integer.parseInt((String) jmap.get("catId"));
        System.out.println("catId:"+catId);
        int brandId=Integer.parseInt((String) jmap.get("brandId"));
        double weight=Double.parseDouble((String) jmap.get("weight"));
        String intro=(String) jmap.get("intro");
		Result result=goodsService.updateGoods(goodsId, name, sn, brief, description, 
				price, cost, mktprice, mktEnable, catId, brandId, weight, intro);
		return result;
	}
	//根据id更新mkt_enable
	@RequestMapping(value="/enable/{goodsId}",method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新商品状态")
	public Result updateEnable(@PathVariable("goodsId") int goodsId,HttpServletRequest request){
		Map jmap=MSUtil.getParam(request);
		//int goodsId=(int) jmap.get("goodsId");
		//int goodsId=Integer.parseInt((String) jmap.get("goodsId"));
		System.out.println("goodsId:"+goodsId);
		int mktEnable=Integer.parseInt((String) jmap.get("mktEnable"));
		Result result=goodsService.updateEnable(goodsId, mktEnable);
		return result;
	}
	//根据id更新params
	@RequestMapping(value="/params/{goodsId}",method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新商品参数")
	public Result updateParams(@PathVariable("goodsId") int goodsId,HttpServletRequest request){
		Map jmap=MSUtil.getParam(request);
//		int goodsId=(int) jmap.get("goodsId");
//		int goodsId=Integer.parseInt((String) jmap.get("goodsId"));
		String param1=(String) jmap.get("param1");
		String param2=(String) jmap.get("param2");
		String param3=(String) jmap.get("param3");
		String param4=(String) jmap.get("param4");
		String param5=(String) jmap.get("param5");
		System.out.println(param1+","+param2+","+param3+","+param4+","+param5);
		String[] paramsValue=new String[]{param1,param2,param3,param4,param5};
		Result result=goodsService.updateParams(goodsId, paramsValue);
		return result;
	}
	
	//加载所有商品（关联查询+分页）
	@RequestMapping(value="/page/{pageId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="加载所有商品")
	public Result loadAllByPage(@PathVariable("pageId") int pageId){
		Result result=goodsService.loadAllGoodsByPage(pageId);
		return result;
	}
	//加载所有可用商品（分页）
	@RequestMapping(value="/enable/page/{pageId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="加载所有上架商品")
	public Result loadAllEnableByPage(@PathVariable("pageId") int pageId){
		Result result=goodsService.loadAllEnableGoodsByPage(pageId);
		return result;
	}
	//根据id加载商品
	@RequestMapping(value="/{goodsId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="加载商品信息")
	public Result loadById(@PathVariable("goodsId") int goodsId){
		Result result=goodsService.loadGoodsById(goodsId);
		return result;
	}
	//根据id加载商品(关联查询)
	@RequestMapping(value="/union/{goodsId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="商品详情")
	public Result loadByIdUnion(@PathVariable("goodsId") int goodsId){
		Result result=goodsService.loadGoodsByIdUnion(goodsId);
		return result;
	}
	//根据分类id加载商品（已上架的）
	@RequestMapping(value="/cat/{catId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据分类编号加载商品")
	public Result loadByCatId(@PathVariable("catId") int catId){
		Result result=goodsService.loadGoodsByCatId(catId);
		return result;
	}
	//根据品牌id加载商品（已上架的）
	@RequestMapping(value="/brand/{brandId}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据品牌编号加载商品")
	public Result loadByBrandId(@PathVariable("brandId") int brandId){
		Result result=goodsService.loadGoodsByBrandId(brandId);
		return result;
	}
	
	//根据id删除
	@RequestMapping(value="/{goodsId}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除商品")
	public Result deleteById(@PathVariable("goodsId") int goodsId){
		Result result=goodsService.deleteGoodsById(goodsId);
		return result;
	}
	
	//计算页数（全部商品）
	@RequestMapping(value="/page/count",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="计算页数（全部商品）")
	public Result count1(){
		Result result=goodsService.countPage1();
		return result;
	}
	
	//计算页数（上架商品）
	@RequestMapping(value="/page/enable/count",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="计算页数（上架商品）")
	public Result count2(){
		Result result=goodsService.countPage2();
		return result;
	}
	
	/*public Map getParam(HttpServletRequest request){
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(
			        (ServletInputStream) request.getInputStream()));
			String line = null;
	        StringBuffer sb = new StringBuffer();
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        String appParam=sb.toString();
	        System.out.println(appParam);
	        JSONObject jsonParam=JSONObject.fromObject(appParam);
	        Map jmap=jsonParam;
			return jmap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
