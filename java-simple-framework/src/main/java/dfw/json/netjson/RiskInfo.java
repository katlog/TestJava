package dfw.json.netjson;


/**
 * @moudle: RiskInfo 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年11月20日 下午2:09:35
 *
 */
public class RiskInfo {
	private int riskCode;
	private int riskCodeValue;
	/**
	 * 取得 riskCode
	 * @return riskCode
	 */
	public int getRiskCode() {
	    return riskCode;
	}
	/**
	 * 設定 riskCode
	 * @param riskCode riskCode
	 */
	public void setRiskCode(int riskCode) {
	    this.riskCode = riskCode;
	}
	/**
	 * 取得 riskCodeValue
	 * @return riskCodeValue
	 */
	public int getRiskCodeValue() {
	    return riskCodeValue;
	}
	/**
	 * 設定 riskCodeValue
	 * @param riskCodeValue riskCodeValue
	 */
	public void setRiskCodeValue(int riskCodeValue) {
	    this.riskCodeValue = riskCodeValue;
	}
	@Override public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RiskInfo [riskCode=").append(riskCode).append(", riskCodeValue=").append(riskCodeValue)
				.append("]");
		return builder.toString();
	}
}
