package module.zusearchive.vo.generated.deprecated;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import module.zusearchive.vo.generated.OUnterlagen;

@XmlRootElement(name="ZUSE")
public class OZuseDep {

	private List<OUnterlagen> oFoto;
	private List<OUnterlagen> oUnterlagen;
	
	public OZuseDep() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the oFoto
	 */
	public List<OUnterlagen> getoFoto() {
		return oFoto;
	}

	/**
	 * @param oFoto the oFoto to set
	 */
	public void setoFoto(List<OUnterlagen> oFoto) {
		this.oFoto = oFoto;
	}
	
	/**
	 * @return the oUnterlagen
	 */
	public List<OUnterlagen> getoUnterlagen() {
		return oUnterlagen;
	}

	/**
	 * @param oUnterlagen the oUnterlagen to set
	 */
	public void setoUnterlagen(List<OUnterlagen> oUnterlagen) {
		this.oUnterlagen = oUnterlagen;
	}
	
}
