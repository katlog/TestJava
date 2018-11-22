package dfw.json.netjson;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @moudle: Risk 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年11月20日 下午2:10:21
 *
 */
public class Risk {
	private int code;
	private String codeDesc;
	private String message;
	private int found;
	private int idFound;
	private int riskScore;
	private List<RiskInfo> riskInfo;
	/**
	 * 取得 code
	 * @return code
	 */
	public int getCode() {
	    return code;
	}
	/**
	 * 設定 code
	 * @param code code
	 */
	public void setCode(int code) {
	    this.code = code;
	}
	/**
	 * 取得 codeDesc
	 * @return codeDesc
	 */
	public String getCodeDesc() {
	    return codeDesc;
	}
	/**
	 * 設定 codeDesc
	 * @param codeDesc codeDesc
	 */
	public void setCodeDesc(String codeDesc) {
	    this.codeDesc = codeDesc;
	}
	/**
	 * 取得 message
	 * @return message
	 */
	public String getMessage() {
	    return message;
	}
	/**
	 * 設定 message
	 * @param message message
	 */
	public void setMessage(String message) {
	    this.message = message;
	}
	/**
	 * 取得 found
	 * @return found
	 */
	public int getFound() {
	    return found;
	}
	/**
	 * 設定 found
	 * @param found found
	 */
	public void setFound(int found) {
	    this.found = found;
	}
	/**
	 * 取得 idFound
	 * @return idFound
	 */
	public int getIdFound() {
	    return idFound;
	}
	/**
	 * 設定 idFound
	 * @param idFound idFound
	 */
	public void setIdFound(int idFound) {
	    this.idFound = idFound;
	}
	/**
	 * 取得 riskScore
	 * @return riskScore
	 */
	public int getRiskScore() {
	    return riskScore;
	}
	/**
	 * 設定 riskScore
	 * @param riskScore riskScore
	 */
	public void setRiskScore(int riskScore) {
	    this.riskScore = riskScore;
	}
	/**
	 * 取得 riskInfo
	 * @return riskInfo
	 */
	public List<RiskInfo> getRiskInfo() {
	    return riskInfo;
	}
	/**
	 * 設定 riskInfo
	 * @param riskInfo riskInfo
	 */
	public void setRiskInfo(List<RiskInfo> riskInfo) {
	    this.riskInfo = riskInfo;
	}
	
	

	@Override public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Risk [code=").append(code).append(", ");
		if (codeDesc != null)
			builder.append("codeDesc=").append(codeDesc).append(", ");
		if (message != null)
			builder.append("message=").append(message).append(", ");
		builder.append("found=").append(found).append(", idFound=").append(idFound).append(", riskScore=")
				.append(riskScore).append(", ");
		if (riskInfo != null)
			builder.append("riskInfo=").append(riskInfo);
		builder.append("]");
		return builder.toString();
	}
	public static void main(String[] args) {
		String string ="{\"code\":0,\"codeDesc\":\"Success\",\"found\":1,\"idFound\":1,\"message\":\"No Error\",\"riskInfo\":[{\"riskCode\":5,\"riskCodeValue\":2}],\"riskScore\":40}";
		Map config = new  HashMap();  
		config.put("riskInfo", RiskInfo.class);  
		Risk bean = (Risk) JSONObject.toBean(JSONObject.fromObject(string), Risk.class,config);
		System.out.println(bean);
	}
	
}
