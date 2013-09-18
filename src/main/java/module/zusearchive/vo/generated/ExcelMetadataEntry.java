package module.zusearchive.vo.generated;

public class ExcelMetadataEntry {
		
	private String position;
	private boolean active;
	private String original_tag;
	private String normalized_tag;
	private String method_name;
	private boolean multiplicity;
	private String type;
	private String language_de;
	private String language_en;
	private boolean caption;
	private boolean preview;

	public ExcelMetadataEntry(String position, boolean active, String original_tag, String normalized_tag, String method_name,
			boolean multiplicity, String type, String language_de, String language_en, boolean caption, boolean preview) {
		this.setPosition(position);
		this.setActive(active);
		this.setOriginal_tag(original_tag);
		this.setNormalized_tag(normalized_tag);
		this.setMethod_name(method_name);
		this.setMultiplicity(multiplicity);
		this.setType(type);
		this.setLanguage_de(language_de);
		this.setLanguage_en(language_en);
		this.setCaption(caption);
		this.setPreview(preview);
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the original_tag
	 */
	public String getOriginal_tag() {
		return original_tag;
	}

	/**
	 * @param original_tag the original_tag to set
	 */
	public void setOriginal_tag(String original_tag) {
		this.original_tag = original_tag;
	}

	/**
	 * @return the normalized_tag
	 */
	public String getNormalized_tag() {
		return normalized_tag;
	}

	/**
	 * @param normalized_tag the normalized_tag to set
	 */
	public void setNormalized_tag(String normalized_tag) {
		this.normalized_tag = normalized_tag;
	}

	/**
	 * @return the method_name
	 */
	public String getMethod_name() {
		return method_name;
	}

	/**
	 * @param method_name the method_name to set
	 */
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	/**
	 * @return the multiplicity
	 */
	public boolean isMultiplicity() {
		return multiplicity;
	}

	/**
	 * @param multiplicity the multiplicity to set
	 */
	public void setMultiplicity(boolean multiplicity) {
		this.multiplicity = multiplicity;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the language_de
	 */
	public String getLanguage_de() {
		return language_de;
	}

	/**
	 * @param language_de the language_de to set
	 */
	public void setLanguage_de(String language_de) {
		this.language_de = language_de;
	}

	/**
	 * @return the language_en
	 */
	public String getLanguage_en() {
		return language_en;
	}

	/**
	 * @param language_en the language_en to set
	 */
	public void setLanguage_en(String language_en) {
		this.language_en = language_en;
	}

	/**
	 * @return the caption
	 */
	public boolean isCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(boolean caption) {
		this.caption = caption;
	}
	
	/**
	 * @return the preview
	 */
	public boolean isPreview() {
		return preview;
	}

	/**
	 * @param preview the preview to set
	 */
	public void setPreview(boolean preview) {
		this.preview = preview;
	}
}
