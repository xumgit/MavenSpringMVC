package com.springdemo.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonParseException;

public class CommonUtils {

	private static final Logger LOG = LogManager.getLogger(CommonUtils.class);

	public static String getCloneItemStatus() {
		StringBuilder cloneItem = new StringBuilder();

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "MainFirmware" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "TPM1532HE.5.249.5.148" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "TVSettings" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "23/04/2018:09:52" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "RoomSpecificSettings" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "01/02/2018:17:20" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "TVChannelList" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "26/03/2018:16:48" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "Script" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "AndroidApps" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "00/00/0000:--:--" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "SmartInfoImages" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "25/04/2018:11:02" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "CustomDashboardFallback" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "" + "\"");
		cloneItem.append("}},");

		cloneItem.append("{\"CloneItemDetails\":{");
		cloneItem.append("\"CloneItemName\":\"" + "WelcomeLogo" + "\",");
		cloneItem.append("\"CloneItemVersionNo\":\"" + "10/04/2018:14:14" + "\"");
		cloneItem.append("}},");

		String cloneItemString = "";
		if (cloneItem.toString().length() > 0) {
			cloneItemString = cloneItem.toString().substring(0, cloneItem.length() - 1);
		}
		return cloneItemString;
	}

	public static String getUpgradeData(List<String> retryUpgradeCloneItem) {
		StringBuilder upgradeData = new StringBuilder();
		String sourceUpgradeData = getSourceUpgradeData();
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(sourceUpgradeData);
			if (jsonObject != null && jsonObject.get("Fun") != null) {
				JSONObject commandDetails = (JSONObject) jsonObject.get("CommandDetails");
				JSONObject webServiceParameters = (JSONObject) commandDetails.get("WebServiceParameters");
				upgradeData.append("{");
				upgradeData.append("\"Svc\":\"WebServices\",");
				upgradeData.append("\"SvcVer\":\"2.0\",");
				upgradeData.append("\"Cookie\":293,");
				upgradeData.append("\"CmdType\":\"Change\",");
				upgradeData.append("\"Fun\":\"IPCloneService\",");
				upgradeData.append("\"CommandDetails\":{");
				upgradeData.append("\"WebServiceParameters\":{");
				upgradeData.append("\"PollingFrequency\":" + webServiceParameters.get("PollingFrequency") + ",");
				upgradeData
						.append("\"PollingFrequencyGreen\":" + webServiceParameters.get("PollingFrequencyGreen") + ",");
				upgradeData.append("\"TVUniqueID\":\"" + webServiceParameters.get("TVUniqueID") + "\"");
				upgradeData.append("},");
				upgradeData.append("\"IPCloneParameters\":{");
				upgradeData.append("\"CloneItemDownloadDetails\":[");
				JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
				JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
				int cloneItemSize = cloneItemDownloadDetails.size();
				int upgradeCloneItemSize = retryUpgradeCloneItem.size();
				if (upgradeCloneItemSize > 0) {
					StringBuilder cloneData = new StringBuilder();
					for (String upgradeCloneItemName : retryUpgradeCloneItem) {
						for (int i = 0; i < cloneItemSize; i++) {
							JSONObject item = (JSONObject) cloneItemDownloadDetails.get(i);
							JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
							String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
							String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
							String uRL = item.get("URL").toString();
							if (upgradeCloneItemName != null && upgradeCloneItemName.equalsIgnoreCase(cloneItemName)) {
								cloneData.append("{");
								cloneData.append("\"CloneItemDetails\":{");
								cloneData.append("\"CloneItemName\":\"" + cloneItemName + "\",");
								cloneData.append("\"CloneItemVersionNo\":\"" + cloneItemVersionNo + "\"");
								cloneData.append("},");
								cloneData.append("\"URL\":\"" + uRL + "\"");
								cloneData.append("},");
							}
						}
					}
					if (cloneData.length() > 0) {
						upgradeData.append(cloneData.substring(0, cloneData.length() - 1));
					}
				}
				upgradeData.append("]");
				upgradeData.append("}");
				upgradeData.append("}");
				upgradeData.append("}");
			} else {
				LOG.error("parse error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return upgradeData.toString();
	}
	
	public static String retryUpgradeData(List<String> retryUpgradeCloneItem, String data) {
		StringBuilder upgradeData = new StringBuilder();
		try {
			JSONObject dataObject = (JSONObject) new JSONParser().parse(data);		
			if (dataObject != null && dataObject.get("UpgradeData") != null) {
				JSONObject upgradeDataObject = (JSONObject) dataObject.get("UpgradeData");
				if (upgradeDataObject != null && upgradeDataObject.get("Fun") != null) {
					JSONObject commandDetails = (JSONObject) upgradeDataObject.get("CommandDetails");
					JSONObject webServiceParameters = (JSONObject) commandDetails.get("WebServiceParameters");
					upgradeData.append("{");
					upgradeData.append("\"Svc\":\"WebServices\",");
					upgradeData.append("\"SvcVer\":\"2.0\",");
					upgradeData.append("\"Cookie\":293,");
					upgradeData.append("\"CmdType\":\"Change\",");
					upgradeData.append("\"Fun\":\"IPCloneService\",");
					upgradeData.append("\"CommandDetails\":{");
					upgradeData.append("\"WebServiceParameters\":{");
					upgradeData.append("\"PollingFrequency\":" + webServiceParameters.get("PollingFrequency") + ",");
					upgradeData
							.append("\"PollingFrequencyGreen\":" + webServiceParameters.get("PollingFrequencyGreen") + ",");
					upgradeData.append("\"TVUniqueID\":\"" + webServiceParameters.get("TVUniqueID") + "\"");
					upgradeData.append("},");
					upgradeData.append("\"IPCloneParameters\":{");
					upgradeData.append("\"CloneItemDownloadDetails\":[");
					JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
					JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
					int cloneItemSize = cloneItemDownloadDetails.size();
					int upgradeCloneItemSize = retryUpgradeCloneItem.size();
					if (upgradeCloneItemSize > 0) {
						StringBuilder cloneData = new StringBuilder();
						for (String upgradeCloneItemName : retryUpgradeCloneItem) {
							for (int i = 0; i < cloneItemSize; i++) {
								JSONObject item = (JSONObject) cloneItemDownloadDetails.get(i);
								JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
								String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
								String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
								String uRL = item.get("URL").toString();
								if (upgradeCloneItemName != null && upgradeCloneItemName.equalsIgnoreCase(cloneItemName)) {
									cloneData.append("{");
									cloneData.append("\"CloneItemDetails\":{");
									cloneData.append("\"CloneItemName\":\"" + cloneItemName + "\",");
									cloneData.append("\"CloneItemVersionNo\":\"" + cloneItemVersionNo + "\"");
									cloneData.append("},");
									cloneData.append("\"URL\":\"" + uRL + "\"");
									cloneData.append("},");
								}
							}
						}
						if (cloneData.length() > 0) {
							upgradeData.append(cloneData.substring(0, cloneData.length() - 1));
						}
					}
					upgradeData.append("]");
					upgradeData.append("}");
					upgradeData.append("}");
					upgradeData.append("}");
				}			
			} else {
				LOG.error("parse error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return upgradeData.toString();
	}

	public static String getSourceUpgradeData() {
		String sourceUpgradeData = "{\"Svc\": \"WebServices\", \"SvcVer\": \"2.0\",\"Cookie\": 293, \"CmdType\": \"Change\",\"Fun\":\"IPCloneService\", "
				+ "\"CommandDetails\" : { \"WebServiceParameters\" : { \"PollingFrequency\" : 3,\"PollingFrequencyGreen\" : 10,\"TVUniqueID\" : \"1234567890123494e979d2aae5\"},"
				+ "\"IPCloneParameters\" :{\"CloneItemDownloadDetails\": [{\"CloneItemDetails\" : { \"CloneItemName\" : \"TVSettings\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/TVSettings.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"TVChannelList\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/ChannelList.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"WelcomeLogo\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/WelcomeLogo.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"SmartInfoImages\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/SmartInfoShow.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"SmartInfoPages\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/SmartInfoBrowser.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"CustomDashboardFallback\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/LocalCustomDashboard.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"AndroidApps\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/AndroidApps.zip\"},"
				+ "{\"CloneItemDetails\" : { \"CloneItemName\" : \"RoomSpecificSettings\",\"CloneItemVersionNo\" : \"2018-04-25 09:33:46.0\"},\"URL\" : \"http://192.168.0.72:8080/SmartInstall/Profile/Clone/13/RoomSpecificSettings.zip\"}]}}}";
		return sourceUpgradeData;
	}
	
	public static String handleCloneRetryAnother(String clone) {
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.add("TVChannelList");
		retryUpgradeCloneItem.add("AndroidApps");
		String _clone = handleCloneAnotherWithFilter(clone, retryUpgradeCloneItem);
		return _clone.toString();
	}
	
	public static String handleCloneAnotherWithFilter(String data, List<String> retryUpgradeCloneItem) {
		StringBuilder clone = new StringBuilder();
		String cloneItemStatus = "";
		StringBuilder upgradeData = new StringBuilder();
		upgradeData.append("\"UpgradeData\":");
		upgradeData.append("[");
		try {
			JSONObject dataObject = (JSONObject) new JSONParser().parse(data);
			if (dataObject != null && dataObject.get("CloneItemStatus") != null) {
				cloneItemStatus = dataObject.get("CloneItemStatus").toString();
			}
			if (dataObject != null && dataObject.get("UpgradeData") != null) {
				JSONArray upgradeDataArr = (JSONArray) dataObject.get("UpgradeData");
				int upgradeDataArrCount = upgradeDataArr.size();
				int retryUpgradeCloneCount = retryUpgradeCloneItem.size();
				StringBuilder cloneData = new StringBuilder();
				if (retryUpgradeCloneCount > 0) {
					for (String upgradeCloneItemName : retryUpgradeCloneItem) {
						for (int i = 0; i < upgradeDataArrCount; i++) {
							JSONObject cloneItem = (JSONObject) upgradeDataArr.get(i);
							String cloneItemName = cloneItem.get("CloneItemName").toString();
							if (upgradeCloneItemName != null && upgradeCloneItemName.equalsIgnoreCase(cloneItemName)) {
								cloneData.append("{\"CloneItemName\":\"" + cloneItemName + "\"},");
								break;
							}						
						}
					}
					if (cloneData.toString().length() > 0) {
						upgradeData.append(cloneData.substring(0, cloneData.toString().length() - 1));
					}
				}
			} else {
				LOG.error("handle other thing");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		upgradeData.append("]");
		clone.append("{");	
		clone.append("\"CloneItemStatus\":");
		clone.append(cloneItemStatus);
		clone.append(",");
		clone.append(upgradeData.toString());
		clone.append("}");
		return clone.toString();
	}
	
	public static String handleCloneRetry(String clone) {
		StringBuilder cloneData = new StringBuilder();
		
		cloneData.append("{");
		String cloneItemStatus = handleCloneItemStatus(clone);
		cloneData.append(cloneItemStatus);		
		cloneData.append(",");	
		List<String> retryUpgradeCloneItem = new ArrayList<String>();
		retryUpgradeCloneItem.add("TVChannelList");
		retryUpgradeCloneItem.add("AndroidApps");
		// upgradeData should be run a runnable to tv
		String upgradeData = retryUpgradeData(retryUpgradeCloneItem, clone);	
		cloneData.append("\"UpgradeData\":");
		cloneData.append(upgradeData);	
		cloneData.append("}");
		
		return cloneData.toString();
	}
	
	public static String handleClone(String clone) {
		StringBuilder cloneData = new StringBuilder();
		
		cloneData.append("{");
		String cloneItemStatus = handleCloneItemStatus(clone);
		cloneData.append(cloneItemStatus);	
		cloneData.append(",");		
		String sourceUpgradeData = getSourceUpgradeData(); // this step maybe not need
		String upgradeData = handleUpgradeData(sourceUpgradeData); // this step also maybe not need	
		cloneData.append("\"UpgradeData\":");
		cloneData.append(upgradeData);	
		cloneData.append("}");

		return cloneData.toString();
	}
	
	public static String handleCloneAnother(String clone) {
		StringBuilder cloneData = new StringBuilder();		
		if (clone.length() > 0) {
			clone = clone.substring(0, clone.length() - 1);
			cloneData.append(clone);
			cloneData.append(",");
		} else {
			cloneData.append("{");
		}

		String sourceUpgradeData = getSourceUpgradeData(); // this step maybe not need
		String upgradeData = handleUpgradeDataAnother(sourceUpgradeData); // this step also maybe not need	
		cloneData.append(upgradeData);	
		cloneData.append("}");
		return cloneData.toString();
	}
	
	public static String handleUpgradeDataAnother(String data) {
		StringBuilder upgradeData = new StringBuilder();
		upgradeData.append("\"UpgradeData\":");
		upgradeData.append("[");
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
			if (jsonObject != null && jsonObject.get("Fun") != null) {
				JSONObject commandDetails = (JSONObject) jsonObject.get("CommandDetails");
				JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
				JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
				int cloneItemCount = cloneItemDownloadDetails.size();
				StringBuilder cloneData = new StringBuilder();
				if (cloneItemCount > 0) {
					for (int i = 0; i < cloneItemCount; i++) {
						JSONObject item = (JSONObject) cloneItemDownloadDetails.get(i);
						JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
						String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
						cloneData.append("{\"CloneItemName\":\"" + cloneItemName + "\"},");
					}
				}
				if (cloneData.toString().length() > 0) {
					upgradeData.append(cloneData.substring(0, cloneData.toString().length() - 1));
				}
			} else {
				LOG.error("parse error");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		upgradeData.append("]");
		return upgradeData.toString();
	}
	
	public static String getRetryTvUpgradeData(List<String> retryTvUpgradeItem, String data) {
		StringBuilder retryTvUpgradeData = new StringBuilder();
		
		int retryTvUpgradeItemCount = retryTvUpgradeItem.size();
		if (retryTvUpgradeItemCount > 0) {
			retryTvUpgradeData.append(handleUpgradeData(data));
		} else {
			retryTvUpgradeData.append(data);
		}
			
		return retryTvUpgradeData.toString();
	}
	
	public static String getTvUpgradeData(List<String> retryTvUpgradeItem, String data) {
		StringBuilder retryTvUpgradeData = new StringBuilder();
		retryTvUpgradeData.append("{");
		retryTvUpgradeData.append("\"Svc\":\"WebServices\",");
		retryTvUpgradeData.append("\"SvcVer\":\"2.0\",");
		retryTvUpgradeData.append("\"Cookie\":293,");
		retryTvUpgradeData.append("\"CmdType\":\"Change\",");
		retryTvUpgradeData.append("\"Fun\":\"IPCloneService\",");
		retryTvUpgradeData.append("\"CommandDetails\":{");
		retryTvUpgradeData.append("\"WebServiceParameters\":");
		
		StringBuilder webServiceParametersData = new StringBuilder();
		StringBuilder ipCloneParametersData = new StringBuilder();
		ipCloneParametersData.append("\"IPCloneParameters\":{");
		ipCloneParametersData.append("\"CloneItemDownloadDetails\":[");
		
		try {		
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
			if (jsonObject != null && jsonObject.get("Fun") != null) {
				JSONObject commandDetails = (JSONObject) jsonObject.get("CommandDetails");
				JSONObject webServiceParameters = (JSONObject) commandDetails.get("WebServiceParameters");
				if (webServiceParameters != null) {
					webServiceParametersData.append("{\"PollingFrequency\":" + webServiceParameters.get("PollingFrequency") + ",");
					webServiceParametersData.append("\"PollingFrequencyGreen\":" + webServiceParameters.get("PollingFrequencyGreen") + ",");
					webServiceParametersData.append("\"TVUniqueID\":\"" + webServiceParameters.get("TVUniqueID") + "\"");
					webServiceParametersData.append("},");
				} 						
				JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
				JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
				int cloneItemDownloadCount = cloneItemDownloadDetails.size();
				int retryTvUpgradeItemCount = retryTvUpgradeItem.size();
				LOG.info("[Utils]retryTvUpgradeItemCount="+retryTvUpgradeItemCount);
				StringBuilder cloneItemDownloadData = new StringBuilder();
				for (String tvUpgradeItem : retryTvUpgradeItem) {
					for (int i = 0; i < cloneItemDownloadCount; i++) {
						JSONObject cloneItem = (JSONObject) cloneItemDownloadDetails.get(i);
						JSONObject cloneItemDetails = (JSONObject) cloneItem.get("CloneItemDetails");
						String cloneItemName = cloneItemDetails.get("CloneItemName").toString();					
						if (tvUpgradeItem != null && tvUpgradeItem.equalsIgnoreCase(cloneItemName)) {
							String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
							String uRL = cloneItem.get("URL").toString();
							cloneItemDownloadData.append("{");
							cloneItemDownloadData.append("\"CloneItemDetails\":{");
							cloneItemDownloadData.append("\"CloneItemName\":\"" + cloneItemName + "\",");
							cloneItemDownloadData.append("\"CloneItemVersionNo\":\"" + cloneItemVersionNo + "\"");
							cloneItemDownloadData.append("},");
							cloneItemDownloadData.append("\"URL\":\"" + uRL + "\"");
							cloneItemDownloadData.append("},");
						}					
					}
				}
				String cloneItemDownloadString = cloneItemDownloadData.toString();
				if (cloneItemDownloadString.length() > 0) {
					cloneItemDownloadString = cloneItemDownloadString.substring(0, cloneItemDownloadString.length() - 1);
					ipCloneParametersData.append(cloneItemDownloadString);
				}
			} else {
				LOG.error("parse error");
			}
		} catch (JsonParseException e) {
			LOG.error(e.getMessage(), e);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		String webServiceParametersString = webServiceParametersData.toString();
		if (webServiceParametersString.length() > 0) {
			retryTvUpgradeData.append(webServiceParametersString);
		} else {
			// IPProfile lastConfig = IPProfile.loadIPProfile();
			// data = JAPITUtils.ChangeIPCloneService(lastConfig.getFastMode(), lastConfig.getGreenMode(), tvUniqueId);
			retryTvUpgradeData.append("{\"PollingFrequency\":" + 3 + ",");
			retryTvUpgradeData.append("\"PollingFrequencyGreen\":" + 10 + ",");
			retryTvUpgradeData.append("\"TVUniqueID\":\"" + "123456789" + "\"");
			retryTvUpgradeData.append("},");
		}
		
		String ipCloneParametersString = ipCloneParametersData.toString();
		if (ipCloneParametersString.length() > 0) {
			retryTvUpgradeData.append(ipCloneParametersString);
		}		
		retryTvUpgradeData.append("]");
		retryTvUpgradeData.append("}");
		
		retryTvUpgradeData.append("}");
		retryTvUpgradeData.append("}");
		
		return retryTvUpgradeData.toString();
	}
	
	public static String handleUpgradeData(String data) {
		StringBuilder upgradeData = new StringBuilder();
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
			if (jsonObject != null && jsonObject.get("Fun") != null) {
				JSONObject commandDetails = (JSONObject) jsonObject.get("CommandDetails");
				JSONObject webServiceParameters = (JSONObject) commandDetails.get("WebServiceParameters");
				upgradeData.append("{");
				upgradeData.append("\"Svc\":\"WebServices\",");
				upgradeData.append("\"SvcVer\":\"2.0\",");
				upgradeData.append("\"Cookie\":293,");
				upgradeData.append("\"CmdType\":\"Change\",");
				upgradeData.append("\"Fun\":\"IPCloneService\",");
				upgradeData.append("\"CommandDetails\":{");
				upgradeData.append("\"WebServiceParameters\":{");
				upgradeData.append("\"PollingFrequency\":" + webServiceParameters.get("PollingFrequency") + ",");
				upgradeData
						.append("\"PollingFrequencyGreen\":" + webServiceParameters.get("PollingFrequencyGreen") + ",");
				upgradeData.append("\"TVUniqueID\":\"" + webServiceParameters.get("TVUniqueID") + "\"");
				upgradeData.append("},");
				upgradeData.append("\"IPCloneParameters\":{");
				upgradeData.append("\"CloneItemDownloadDetails\":[");
				JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
				JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
				int cloneItemDownloadCount = cloneItemDownloadDetails.size();
				LOG.info("cloneItemDownloadCount="+cloneItemDownloadCount);
				StringBuilder cloneData = new StringBuilder();
				for (int i = 0; i < cloneItemDownloadCount; i++) {
					JSONObject cloneItem = (JSONObject) cloneItemDownloadDetails.get(i);
					JSONObject cloneItemDetails = (JSONObject) cloneItem.get("CloneItemDetails");
					String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
					String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
					String uRL = cloneItem.get("URL").toString();
					cloneData.append("{");
					cloneData.append("\"CloneItemDetails\":{");
					cloneData.append("\"CloneItemName\":\"" + cloneItemName + "\",");
					cloneData.append("\"CloneItemVersionNo\":\"" + cloneItemVersionNo + "\"");
					cloneData.append("},");
					cloneData.append("\"URL\":\"" + uRL + "\"");
					cloneData.append("},");
				}
				if (cloneData.length() > 0) {
					upgradeData.append(cloneData.substring(0, cloneData.length() - 1));
				}
				upgradeData.append("]");
				upgradeData.append("}");
				upgradeData.append("}");
				upgradeData.append("}");
			} else {
				LOG.error("parse error");
			}
		} catch (JsonParseException e) {
			LOG.error(e.getMessage(), e);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return upgradeData.toString();
	}

	public static String handleCloneItemStatus(String data) {
		StringBuilder cloneItemStatus = new StringBuilder();
		cloneItemStatus.append("\"CloneItemStatus\":");
		cloneItemStatus.append("[");
		String tvUniqueId = "";
		try {
			StringBuilder cloneItemBuilder = new StringBuilder();
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
			LOG.info("[xum]jsonObject=" + jsonObject);
			if (jsonObject != null && jsonObject.get("CloneItemStatus") != null) {
				LOG.info("[xum]CloneItemStatus=" + jsonObject.get("CloneItemStatus").toString());
				tvUniqueId = jsonObject.get("TVUniqueID").toString();
				JSONArray CloneItemStatus = (JSONArray) jsonObject.get("CloneItemStatus");
				int cloneItemSize = CloneItemStatus.size();
				LOG.info("[xum]cloneItemSize=" + cloneItemSize);
				for (int i = 0; i < cloneItemSize; i++) {
					JSONObject cloneItem = (JSONObject) CloneItemStatus.get(i);
					JSONObject cloneItemDetails = (JSONObject) cloneItem.get("CloneItemDetails");
					String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
					String cloneItemVersionNo = cloneItemDetails.get("CloneItemVersionNo").toString();
					cloneItemBuilder.append("{\"CloneItemDetails\":{");
					cloneItemBuilder.append("\"CloneItemName\":\"" + cloneItemName + "\",");
					cloneItemBuilder.append("\"CloneItemVersionNo\":\"" + cloneItemVersionNo + "\"");
					cloneItemBuilder.append("}},");
				}
				String cloneItemBuilderString = "";
				if (cloneItemBuilder.toString().length() > 0) {
					cloneItemBuilderString = cloneItemBuilder.toString().substring(0, cloneItemBuilder.length() - 1);
				}
				cloneItemStatus.append(cloneItemBuilderString);
			} else {
				LOG.info("[xum]error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("[xum]e=" + e.getMessage());
		}
		cloneItemStatus.append("]");
		cloneItemStatus.append(",\"TVUniqueID\":");
		cloneItemStatus.append("\"" + tvUniqueId + "\"");
		return cloneItemStatus.toString();
	}

	public static void test(String data) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(data);
			LOG.info("[xum]jsonObject=" + jsonObject);
			if (jsonObject != null && jsonObject.get("Fun") != null) {
				LOG.info("[xum]Fun=" + jsonObject.get("Fun").toString());
				JSONObject commandDetails = (JSONObject) jsonObject.get("CommandDetails");
				JSONObject webServiceParameters = (JSONObject) commandDetails.get("WebServiceParameters");
				String tvUniqueId = "";
				if (webServiceParameters.get("TVUniqueID") != null) {
					tvUniqueId = webServiceParameters.get("TVUniqueID").toString();
				}
				JSONObject ipCloneParameters = (JSONObject) commandDetails.get("IPCloneParameters");
				JSONArray cloneItemDownloadDetails = (JSONArray) ipCloneParameters.get("CloneItemDownloadDetails");
				int cloneItemSize = cloneItemDownloadDetails.size();
				LOG.info("[xum]cloneItemSize=" + cloneItemSize);
				for (int i = 0; i < cloneItemSize; i++) {
					JSONObject item = (JSONObject) cloneItemDownloadDetails.get(i);
					JSONObject cloneItemDetails = (JSONObject) item.get("CloneItemDetails");
					String cloneItemName = cloneItemDetails.get("CloneItemName").toString();
					String url = item.get("URL").toString();
					LOG.info("[xum]cloneItemName=" + i + " : " + cloneItemName);
					LOG.info("[xum]url=" + i + " : " + url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("[xum]e" + e.getMessage());
		}
	}

}
