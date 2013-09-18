package module.zusearchive.vo.generated.formats.enums;

public enum ExcelZuseEntryEnum {

	POSITION("Position", 0),
	ACTIVE("Active", 1),
	ORIGINAL_TAG("Original tag", 2),
	NORMALIZED_TAG("Normalized tag", 3),
	METHOD_NAME("Method name", 4),
	MULTIPLICITY("Multiplicity", 5),	
	TYPE("Type", 6),
	LANGUAGE_DE("Language DE", 7),
	LANGUAGE_EN("Language EN", 8),
	CAPTION("Caption", 9),
	PREVIEW("Preview", 10);

	private String type;
	private int ord;

	private ExcelZuseEntryEnum(String type, int ord) {
		this.setType(type);
		this.setOrd(ord);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
}
