package module.zusearchive.vo.generated.formats.enums;

public enum ExcelEntryEnum {
	// Type ZIA_ID Author Title Year Directory File URL

	TYPE("Type", 0),
	ZIA_ID("ZIA ID", 1),
	GMD_NR("GMD number", 2),
	AUTHOR("Author", 3),
	YEAR("Year", 4),
	TITLE("Title", 5),
	FILE("File", 6),
	CHRONOLOGICAL("Chronological", 7),
	THEMATIC("Thematic", 8),
	LANGUAGE("Language", 9),
	DESCRIPTION("Description", 10),
	URL("Url", 11),
	DIRECTORY("directory", 12),
	PUBLISHED_BY("Published by", 13);
	

	private String type;
	private int ord;

	private ExcelEntryEnum(String type, int ord) {
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
