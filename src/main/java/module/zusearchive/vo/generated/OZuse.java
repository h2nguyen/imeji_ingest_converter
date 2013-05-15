package module.zusearchive.vo.generated;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ZUSE")
public class OZuse {

	private List<OUnterlagen> oFoto;
	private List<OUnterlagen> oUnterlagen;
	
	public OZuse() {
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
