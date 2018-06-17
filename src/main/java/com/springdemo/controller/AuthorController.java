package com.springdemo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springdemo.common.CommonUtils;
import com.springdemo.pojo.Author;
import com.springdemo.service.AuthorService;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

	private static final Logger LOG = LogManager.getLogger(AuthorController.class);
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value="/first", method = RequestMethod.GET)
	public String firstStep(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis(); 
		StringBuilder si_Identifiers = new StringBuilder();
		si_Identifiers.append("{\"CloneItemStatus\":");
		si_Identifiers.append("[");		
		String cloneItemStatus = CommonUtils.getCloneItemStatus();				
		si_Identifiers.append(cloneItemStatus);	
		si_Identifiers.append("]");
		si_Identifiers.append(",\"TVUniqueID\":");
		si_Identifiers.append("\"" + "123456789012341C5A6BBC6565" + "\"}");	
		String si_Identifiers_data = si_Identifiers.toString();
		LOG.info("si_Identifiers="+si_Identifiers_data);
		
		List<Author> authorList = new ArrayList<Author>();
		int total = authorService.selectAllCount();
		for (int i = 1; i <= total; i++) {
			Author author = new Author();
			author.setId(i);
			author.setClone(si_Identifiers_data);
			authorList.add(author);
		}
	
		int affectRow = -1;
		affectRow = authorService.batchUpdate(authorList);	
		model.addAttribute("affectRow", affectRow);
		model.addAttribute("step", "first");
		long endTime = System.currentTimeMillis();
		long timeConsume = endTime - startTime;
		model.addAttribute("timeConsume", timeConsume);
		LOG.info("first=time consuming="+timeConsume+"ms");
		return "author/step";
	}
	
	@RequestMapping(value="/second", method = RequestMethod.GET)
	public String secondStep(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis(); 
		Author author = null;
		List<Author> authorList = new ArrayList<Author>();
		int total = authorService.selectAllCount();
		for (int i = 1; i <= total; i++) {		
			author = authorService.selectByPrimaryKey(i);
			String si_Identifiers = author.getClone();
			String _si_Identifiers = CommonUtils.handleCloneAnother(si_Identifiers);
			Author author1 = new Author();
			author1.setId(i);
			author1.setClone(_si_Identifiers);
			authorList.add(author1);
		}
	
		int affectRow = -1;
		affectRow = authorService.batchUpdate(authorList);	
		model.addAttribute("affectRow", affectRow);
		model.addAttribute("step", "second");
		long endTime = System.currentTimeMillis();
		long timeConsume = endTime - startTime;
		model.addAttribute("timeConsume", timeConsume);
		LOG.info("second=time consuming="+timeConsume+"ms");
		return "author/step";
	}
	
	@RequestMapping(value="/third", method = RequestMethod.GET)
	public String thirdStep(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis(); 
		Author author = null;
		List<Author> authorList = new ArrayList<Author>();
		int total = authorService.selectAllCount();
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.add("TVChannelList");
		retryUpgradeCloneItem.add("AndroidApps");
		for (int i = 1; i <= total; i++) {		
			author = authorService.selectByPrimaryKey(i);
			String si_Identifiers = author.getClone();
			String _si_Identifiers = CommonUtils.handleCloneAnotherWithFilter(si_Identifiers, retryUpgradeCloneItem);
			Author author1 = new Author();
			author1.setId(i);
			author1.setClone(_si_Identifiers);
			authorList.add(author1);
		}
			
		int affectRow = -1;
		affectRow = authorService.batchUpdate(authorList);	
		model.addAttribute("affectRow", affectRow);
		model.addAttribute("step", "third");
		long endTime = System.currentTimeMillis();
		long timeConsume = endTime - startTime;
		model.addAttribute("timeConsume", timeConsume);
		LOG.info("third=time consuming="+timeConsume+"ms");
		return "author/step";
	}
	
	@RequestMapping(value="/four", method = RequestMethod.GET)
	public String fourStep(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis(); 
		Author author = null;
		List<Author> authorList = new ArrayList<Author>();
		int total = authorService.selectAllCount();
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.add("AndroidApps");
		for (int i = 1; i <= total; i++) {		
			author = authorService.selectByPrimaryKey(i);
			String si_Identifiers = author.getClone();
			String _si_Identifiers = CommonUtils.handleCloneAnotherWithFilter(si_Identifiers, retryUpgradeCloneItem);
			Author author1 = new Author();
			author1.setId(i);
			author1.setClone(_si_Identifiers);
			authorList.add(author1);
		}
			
		int affectRow = -1;
		affectRow = authorService.batchUpdate(authorList);	
		model.addAttribute("affectRow", affectRow);
		model.addAttribute("step", "four");
		long endTime = System.currentTimeMillis();
		long timeConsume = endTime - startTime;
		model.addAttribute("timeConsume", timeConsume);
		LOG.info("third=time consuming="+timeConsume+"ms");
		return "author/step";
	}
	
	@RequestMapping(value="/five", method = RequestMethod.GET)
	public String fiveStep(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis(); 
		Author author = null;
		List<Author> authorList = new ArrayList<Author>();
		int total = authorService.selectAllCount();
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.clear();
		for (int i = 1; i <= total; i++) {		
			author = authorService.selectByPrimaryKey(i);
			String si_Identifiers = author.getClone();
			String _si_Identifiers = CommonUtils.handleCloneAnotherWithFilter(si_Identifiers, retryUpgradeCloneItem);
			Author author1 = new Author();
			author1.setId(i);
			author1.setClone(_si_Identifiers);
			authorList.add(author1);
		}
			
		int affectRow = -1;
		affectRow = authorService.batchUpdate(authorList);	
		model.addAttribute("affectRow", affectRow);
		model.addAttribute("step", "five");
		long endTime = System.currentTimeMillis();
		long timeConsume = endTime - startTime;
		model.addAttribute("timeConsume", timeConsume);
		LOG.info("third=time consuming="+timeConsume+"ms");
		return "author/step";
	}
	
	@RequestMapping(value="/request")
	@ResponseBody
	public Author requestExample(@RequestBody Author[] authors) {
		for (Author author : authors) {
			LOG.info("name="+author.getName()+",age="+author.getAge()+",country="+author.getCountry());
		}	
		Author author = new Author();
		author.setName("authorFour");
		author.setAge(25);
		author.setCountry("Germany");
		return author;
	}
	
	@RequestMapping(value="/redis", method = RequestMethod.GET)
	public String redisExample(ModelMap model) {
		Author author = null;
		author = this.authorService.selectByPrimaryKey(1);
		model.addAttribute("author", author);
		return "author/redis";
	}
	
	@RequestMapping(value="/pojo", method = RequestMethod.GET)
	public String pojoParameter(ModelMap model) {
		Author author = new Author();
		author.setName("lishi");
		author.setAge(16);
		author.setCountry("Germany");		
		List<Author> authorListOne = null;
		authorListOne = authorService.selectByParameterOne(author);
		model.addAttribute("authorListOne", authorListOne);
		
		List<Author> authorListTwo = null;
		authorListTwo = authorService.selectByParameterTwo("lishi", 16, "Germany");
		model.addAttribute("authorListTwo", authorListTwo);
		
		Author author1 = new Author();
		author1.setName("lishi");
		author1.setAge(16);
		List<Author> authorListThree = null;
		authorListThree = authorService.selectByParameterThree(author1, "Germany");
		model.addAttribute("authorListThree", authorListThree);
		
		return "author/pojo";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.GET)
	public String saveAuthor(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		StringBuilder si_Identifiers = new StringBuilder();
		si_Identifiers.append("{\"CloneItemStatus\":");
		si_Identifiers.append("[");		
		String _cloneItemStatus = CommonUtils.getCloneItemStatus();				
		si_Identifiers.append(_cloneItemStatus);	
		si_Identifiers.append("]");
		
		si_Identifiers.append(",\"TVUniqueID\":");
		si_Identifiers.append("\"" + "123456789012341C5A6BBC6565" + "\"");
		
		si_Identifiers.append(",\"UpgradeData\":");	
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.add("TVChannelList");
		retryUpgradeCloneItem.add("AndroidApps");
		retryUpgradeCloneItem.add("TVSettings");
		retryUpgradeCloneItem.add("SmartInfoImages");
		retryUpgradeCloneItem.add("SmartInfoPages");
		retryUpgradeCloneItem.add("CustomDashboardFallback");
		retryUpgradeCloneItem.add("RoomSpecificSettings");
		retryUpgradeCloneItem.add("WelcomeLogo");
		String upgradeData = CommonUtils.getUpgradeData(retryUpgradeCloneItem);		
		si_Identifiers.append(upgradeData + "}");
		
		String si_Identifiers_data = si_Identifiers.toString();
		LOG.info("si_Identifiers="+si_Identifiers_data);
		
		Author author = new Author();
		author.setId(2);
		author.setClone(si_Identifiers_data);
		int affectRow = -1;
		affectRow = authorService.updateByPrimaryKeySelective(author);
		model.addAttribute("affectRow", affectRow);
		
		List<Map<String, String>> cloneItemStatusList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> upgradeDataList = new ArrayList<Map<String, String>>();
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(si_Identifiers_data);
			
			JSONArray cloneItemStatus = (JSONArray)jsonObject.get("CloneItemStatus");
			for(int i = 0; i < cloneItemStatus.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject item = (JSONObject) cloneItemStatus.get(i);
				JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
				String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
				String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
				map.put("CloneItemName", cloneItemName);
				map.put("CloneItemVersionNo", cloneItemVersionNo);
				cloneItemStatusList.add(map);
				//if ("RoomSpecificSettings".equalsIgnoreCase(cloneItemName)) {
					//si_Identifiers_data = si_Identifiers_data.replace(cloneItemVersionNo, "2018/12/12:12:12");
				//}
			}
			
			String tvUniqueID = jsonObject.get("TVUniqueID").toString();
			model.addAttribute("tvUniqueID", tvUniqueID);
			
			JSONObject upgradeDataObject = (JSONObject) jsonObject.get("UpgradeData");
			JSONObject commandDetailsObject = (JSONObject) upgradeDataObject.get("CommandDetails");
			JSONObject ipCloneParameters = (JSONObject)commandDetailsObject.get("IPCloneParameters");     		
    		JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");		
    		int cloneItemSize = cloneItemDownloadDetails.size();
    		LOG.info("[xum]cloneItemSize="+cloneItemSize);
    		for (int i=0;i<cloneItemSize;i++) {
    			Map<String, String> map = new HashMap<String, String>();
    			JSONObject item = (JSONObject) cloneItemDownloadDetails.get(i);
    			JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
    			String cloneItemName = cloneItemDetails.get("CloneItemName").toString();	
    			String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
    			String url = item.get("URL").toString();
    			map.put("CloneItemName", cloneItemName);
    			map.put("CloneItemVersionNo", cloneItemVersionNo);
    			map.put("URL", url);
    			upgradeDataList.add(map);
    		}
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("cloneItemStatusList", cloneItemStatusList);
		model.addAttribute("upgradeDataList", upgradeDataList);
		return "author/save";
	}
	
	@RequestMapping(value="/select", method = RequestMethod.GET)
	public void selectAll(HttpServletRequest request, HttpServletResponse response) {
		JsonObject data = new JsonObject();
		JsonArray array = new JsonArray();
		List<Map<String, Object>> authors = authorService.selectAll();
		JsonObject jsonObj = null;	
		int authorsLen = authors.size();
		for (Map<String, Object> kv : authors) {
			jsonObj = new JsonObject();
			for (Map.Entry<String, Object> entry : kv.entrySet()) {
				jsonObj.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
			}
			array.add(jsonObj);
		}
		data.addProperty("current", 1);
		data.addProperty("rowCount", 10);
		data.addProperty("total", authorsLen);
		data.add("rows", array);
		LOG.info("data="+data.toString());
		
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(data.toString());
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/showauthor", method = RequestMethod.GET)
	public String showAuthorByBootGrid() {
		return "author/bootgrid";
	}
	
	@RequestMapping(value="/bootgrid", method = RequestMethod.POST)
	public void authorBootGrid(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> mapPara = new HashMap<String, Object>();
		
		String paraid = request.getParameter("paraid");
		String type = request.getParameter("type");
		LOG.info("paraid="+paraid+",type="+type);
		
		String _current = request.getParameter("current");
		String _rowCount = request.getParameter("rowCount");
		int	current = (_current != null) ? Integer.valueOf(_current) : 1;	
		int rowCount = (_rowCount != null && !("-1".equalsIgnoreCase(_rowCount))) ? Integer.valueOf(_rowCount) : 10;
		String searchPhrase = request.getParameter("searchPhrase");
		LOG.info("current="+current+",rowCount="+rowCount);
		LOG.info("searchPhrase="+searchPhrase);
		
		int total = 0;
		total = this.authorService.selectAllCount();
		LOG.info("total="+total);
		
		int offset = 0;
		if (current > 0) {
			offset = (current - 1) * rowCount;
		}
		mapPara.put("offset", offset);
		mapPara.put("rowCount", rowCount);
		
		if (StringUtils.isNotBlank(searchPhrase)) {
			mapPara.put("searchPhrase", searchPhrase);
		}
		
		String idSort = request.getParameter("sort[id]");
		String ageSort = request.getParameter("sort[age]");
				
		if (idSort != null) {
			mapPara.put("idSort", idSort);
		} else if (ageSort != null) {
			mapPara.put("ageSort", ageSort);
		}
		
		List<Map<String, Object>> authors = authorService.selectAuthorByBootGrid(mapPara);
		JsonObject data = new JsonObject();
		JsonArray array = new JsonArray();
		JsonObject jsonObj = null;	
		for (Map<String, Object> kv : authors) {
			jsonObj = new JsonObject();
			for (Map.Entry<String, Object> entry : kv.entrySet()) {
				jsonObj.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
			}
			array.add(jsonObj);
		}
		data.addProperty("current", current);
		data.addProperty("rowCount", rowCount);
		data.addProperty("total", total);
		data.add("rows", array);
		LOG.info("data="+data.toString());
		
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(data.toString());
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value="/bootgridTwo", method = RequestMethod.POST)
	@ResponseBody
	public String authorBootGridTwo(@RequestParam(value="current", required=true, defaultValue="1") Integer current,
			@RequestParam(value="rowCount", required=true, defaultValue="10") Integer rowCount,
			@RequestParam(value="searchPhrase", required=false) String searchPhrase,
			@RequestParam(value="sort[id]", required=false) String sortId, 
			@RequestParam(value="sort[age]", required=false) String sortAge,
			@RequestParam(value="paraid", required=false) String paraid, 
			@RequestParam(value="type", required=false) String type) {
		LOG.info("current="+current);
		LOG.info("rowCount="+rowCount);
		LOG.info("searchPhrase="+searchPhrase);
		LOG.info("sortId="+sortId);
		LOG.info("sortAge="+sortAge);
		LOG.info("paraid="+paraid);
		LOG.info("type="+type);
		
		JsonObject data = new JsonObject();
		rowCount = (rowCount != null && !(-1 == rowCount)) ? rowCount : 10;
		
		Map<String, Object> mapPara = new HashMap<String, Object>();
		
		int total = 0;
		total = this.authorService.selectAllCount();
		LOG.info("total="+total);
		
		int offset = 0;
		if (current > 0) {
			offset = (current - 1) * rowCount;
		}
		mapPara.put("offset", offset);
		mapPara.put("rowCount", rowCount);
		
		if (StringUtils.isNotBlank(searchPhrase)) {
			mapPara.put("searchPhrase", searchPhrase);
		}
					
		if (sortId != null) {
			mapPara.put("idSort", sortId);
		} else if (sortAge != null) {
			mapPara.put("ageSort", sortAge);
		}
		
		List<Map<String, Object>> authors = authorService.selectAuthorByBootGrid(mapPara);
		JsonArray array = new JsonArray();
		JsonObject jsonObj = null;	
		for (Map<String, Object> kv : authors) {
			jsonObj = new JsonObject();
			for (Map.Entry<String, Object> entry : kv.entrySet()) {
				jsonObj.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
			}
			array.add(jsonObj);
		}
		data.addProperty("current", current);
		data.addProperty("rowCount", rowCount);
		data.addProperty("total", total);
		data.add("rows", array);
		LOG.info("data="+data.toString());
		
		return data.toString();
	}
}
