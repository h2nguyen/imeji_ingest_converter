package module.zusearchive.vo.generated;

public class ExcelEntry4PDF {
	private String type;
	private boolean typeEnabled = false;
	private boolean typeMultiEnabled = false;
	
	private String zia_id;
	private boolean zia_idEnabled = false;
	private boolean zia_idMultiEnabled = false;
	
	private String gmd_number;
	private boolean gmd_numberEnabled = false;
	private boolean gmd_numberMultiEnabled = false;
	
	private String author;
	private boolean authorEnabled = false;
	private boolean authorMultiEnabled = false;
	
	private String year;
	private boolean yearEnabled = false;
	private boolean yearMultiEnabled = false;
	
	private String title;
	private boolean titleEnabled = false;
	private boolean titleMultiEnabled = false;
	
	private String file;
	private boolean fileEnabled = false;
	private boolean fileMultiEnabled = false;
	
	private String chronological;
	private boolean chronologicalEnabled = false;
	private boolean chronologicalMultiEnabled = false;
	
	private String thematic;
	private boolean thematicEnabled = false;
	private boolean thematicMultiEnabled = false;
	
	private String language;
	private boolean languageEnabled = false;
	private boolean languageMultiEnabled = false;
	
	private String description;
	private boolean descriptionEnabled = false;
	private boolean descriptionMultiEnabled = false;
	
	private String url;
	private boolean urlEnabled = false;
	private boolean urlMultiEnabled = false;
	
	private String publishedBy;
	private boolean publishedByEnabled = false;
	private boolean publishedByMultiEnabled = false;

	public ExcelEntry4PDF(
			String type, boolean typeEnabled, boolean typeMultiEnabled,
			String zia_id, boolean zia_idEnabled, boolean zia_idMultiEnabled,
			String gmd_number, boolean gmd_numberEnabled, boolean gmd_numberMultiEnabled,
			String author, boolean authorEnabled, boolean authorMultiEnabled,
			String year, boolean yearEnabled, boolean yearMultiEnabled,
			String title, boolean titleEnabled, boolean titleMultiEnabled,
			String file, boolean fileEnabled, boolean fileMultiEnabled,
			String chronological, boolean chronologicalEnabled, boolean chronologicalMultiEnabled,
			String thematic, boolean thematicEnabled, boolean thematicMultiEnabled,
			String language, boolean languageEnabled, boolean languageMultiEnabled,
			String description, boolean descriptionEnabled, boolean descriptionMultiEnabled, 
			String url, boolean urlEnabled, boolean urlMultiEnabled,
			String publishedBy, boolean publishedByEnabled, boolean publishedByMultiEnabled) {
		this.type = type;
		this.typeEnabled = typeEnabled;
		this.typeMultiEnabled = typeMultiEnabled;
		
		this.zia_id = zia_id;
		this.zia_idEnabled = zia_idEnabled;
		this.zia_idMultiEnabled = zia_idMultiEnabled;
		
		this.gmd_number = gmd_number;
		this.gmd_numberEnabled = gmd_numberEnabled;
		this.gmd_numberMultiEnabled = gmd_numberMultiEnabled;
		
		this.author = author;
		this.authorEnabled = authorEnabled;
		this.authorMultiEnabled = authorMultiEnabled;
		
		this.year = year;
		this.yearEnabled = yearEnabled;
		this.yearMultiEnabled = yearMultiEnabled;
		
		this.title = title;
		this.titleEnabled = titleEnabled;
		this.titleMultiEnabled = titleMultiEnabled;
		
		this.file = file;
		this.fileEnabled = fileEnabled;
		this.fileMultiEnabled = fileMultiEnabled;
		
		this.setChronological(chronological);
		this.chronologicalEnabled = chronologicalEnabled;
		this.chronologicalMultiEnabled = chronologicalMultiEnabled;
		
		this.setThematic(thematic);
		this.thematicEnabled = thematicEnabled;
		this.thematicMultiEnabled = thematicMultiEnabled;
		
		this.setLanguage(language);
		this.languageEnabled = languageEnabled;
		this.languageMultiEnabled = languageMultiEnabled;
		
		this.url = url;
		this.urlEnabled = urlEnabled;
		this.urlMultiEnabled = urlMultiEnabled;
		
		this.setDescription(description);
		this.descriptionEnabled = descriptionEnabled;
		this.descriptionMultiEnabled = descriptionMultiEnabled;
		
		this.setPublishedBy(publishedBy);
		this.publishedByEnabled = publishedByEnabled;
		this.publishedByMultiEnabled = publishedByMultiEnabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZia_id() {
		return zia_id;
	}

	public void setZia_id(String zia_id) {
		this.zia_id = zia_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChronological() {
		return chronological;
	}

	public void setChronological(String chronological) {
		this.chronological = chronological;
	}

	public String getThematic() {
		return thematic;
	}

	public void setThematic(String thematic) {
		this.thematic = thematic;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	/**
	 * @return the typeEnabled
	 */
	public boolean isTypeEnabled() {
		return typeEnabled;
	}

	/**
	 * @param typeEnabled the typeEnabled to set
	 */
	public void setTypeEnabled(boolean typeEnabled) {
		this.typeEnabled = typeEnabled;
	}

	/**
	 * @return the zia_idEnabled
	 */
	public boolean isZia_idEnabled() {
		return zia_idEnabled;
	}

	/**
	 * @param zia_idEnabled the zia_idEnabled to set
	 */
	public void setZia_idEnabled(boolean zia_idEnabled) {
		this.zia_idEnabled = zia_idEnabled;
	}

	/**
	 * @return the authorEnabled
	 */
	public boolean isAuthorEnabled() {
		return authorEnabled;
	}

	/**
	 * @param authorEnabled the authorEnabled to set
	 */
	public void setAuthorEnabled(boolean authorEnabled) {
		this.authorEnabled = authorEnabled;
	}

	/**
	 * @return the yearEnabled
	 */
	public boolean isYearEnabled() {
		return yearEnabled;
	}

	/**
	 * @param yearEnabled the yearEnabled to set
	 */
	public void setYearEnabled(boolean yearEnabled) {
		this.yearEnabled = yearEnabled;
	}

	/**
	 * @return the titleEnabled
	 */
	public boolean isTitleEnabled() {
		return titleEnabled;
	}

	/**
	 * @param titleEnabled the titleEnabled to set
	 */
	public void setTitleEnabled(boolean titleEnabled) {
		this.titleEnabled = titleEnabled;
	}

	/**
	 * @return the fileEnabled
	 */
	public boolean isFileEnabled() {
		return fileEnabled;
	}

	/**
	 * @param fileEnabled the fileEnabled to set
	 */
	public void setFileEnabled(boolean fileEnabled) {
		this.fileEnabled = fileEnabled;
	}

	/**
	 * @return the chronologicalEnabled
	 */
	public boolean isChronologicalEnabled() {
		return chronologicalEnabled;
	}

	/**
	 * @param chronologicalEnabled the chronologicalEnabled to set
	 */
	public void setChronologicalEnabled(boolean chronologicalEnabled) {
		this.chronologicalEnabled = chronologicalEnabled;
	}

	/**
	 * @return the thematicEnabled
	 */
	public boolean isThematicEnabled() {
		return thematicEnabled;
	}

	/**
	 * @param thematicEnabled the thematicEnabled to set
	 */
	public void setThematicEnabled(boolean thematicEnabled) {
		this.thematicEnabled = thematicEnabled;
	}

	/**
	 * @return the languageEnabled
	 */
	public boolean isLanguageEnabled() {
		return languageEnabled;
	}

	/**
	 * @param languageEnabled the languageEnabled to set
	 */
	public void setLanguageEnabled(boolean languageEnabled) {
		this.languageEnabled = languageEnabled;
	}

	/**
	 * @return the descriptionEnabled
	 */
	public boolean isDescriptionEnabled() {
		return descriptionEnabled;
	}

	/**
	 * @param descriptionEnabled the descriptionEnabled to set
	 */
	public void setDescriptionEnabled(boolean descriptionEnabled) {
		this.descriptionEnabled = descriptionEnabled;
	}

	/**
	 * @return the urlEnabled
	 */
	public boolean isUrlEnabled() {
		return urlEnabled;
	}

	/**
	 * @param urlEnabled the urlEnabled to set
	 */
	public void setUrlEnabled(boolean urlEnabled) {
		this.urlEnabled = urlEnabled;
	}

	/**
	 * @return the publishedByEnabled
	 */
	public boolean isPublishedByEnabled() {
		return publishedByEnabled;
	}

	/**
	 * @param publishedByEnabled the publishedByEnabled to set
	 */
	public void setPublishedByEnabled(boolean publishedByEnabled) {
		this.publishedByEnabled = publishedByEnabled;
	}

	/**
	 * @return the typeMultiEnabled
	 */
	public boolean isTypeMultiEnabled() {
		return typeMultiEnabled;
	}

	/**
	 * @param typeMultiEnabled the typeMultiEnabled to set
	 */
	public void setTypeMultiEnabled(boolean typeMultiEnabled) {
		this.typeMultiEnabled = typeMultiEnabled;
	}

	/**
	 * @return the zia_idMultiEnabled
	 */
	public boolean isZia_idMultiEnabled() {
		return zia_idMultiEnabled;
	}

	/**
	 * @param zia_idMultiEnabled the zia_idMultiEnabled to set
	 */
	public void setZia_idMultiEnabled(boolean zia_idMultiEnabled) {
		this.zia_idMultiEnabled = zia_idMultiEnabled;
	}

	/**
	 * @return the authorMultiEnabled
	 */
	public boolean isAuthorMultiEnabled() {
		return authorMultiEnabled;
	}

	/**
	 * @param authorMultiEnabled the authorMultiEnabled to set
	 */
	public void setAuthorMultiEnabled(boolean authorMultiEnabled) {
		this.authorMultiEnabled = authorMultiEnabled;
	}

	/**
	 * @return the yearMultiEnabled
	 */
	public boolean isYearMultiEnabled() {
		return yearMultiEnabled;
	}

	/**
	 * @param yearMultiEnabled the yearMultiEnabled to set
	 */
	public void setYearMultiEnabled(boolean yearMultiEnabled) {
		this.yearMultiEnabled = yearMultiEnabled;
	}

	/**
	 * @return the titleMultiEnabled
	 */
	public boolean isTitleMultiEnabled() {
		return titleMultiEnabled;
	}

	/**
	 * @param titleMultiEnabled the titleMultiEnabled to set
	 */
	public void setTitleMultiEnabled(boolean titleMultiEnabled) {
		this.titleMultiEnabled = titleMultiEnabled;
	}

	/**
	 * @return the fileMultiEnabled
	 */
	public boolean isFileMultiEnabled() {
		return fileMultiEnabled;
	}

	/**
	 * @param fileMultiEnabled the fileMultiEnabled to set
	 */
	public void setFileMultiEnabled(boolean fileMultiEnabled) {
		this.fileMultiEnabled = fileMultiEnabled;
	}

	/**
	 * @return the chronologicalMultiEnabled
	 */
	public boolean isChronologicalMultiEnabled() {
		return chronologicalMultiEnabled;
	}

	/**
	 * @param chronologicalMultiEnabled the chronologicalMultiEnabled to set
	 */
	public void setChronologicalMultiEnabled(boolean chronologicalMultiEnabled) {
		this.chronologicalMultiEnabled = chronologicalMultiEnabled;
	}

	/**
	 * @return the thematicMultiEnabled
	 */
	public boolean isThematicMultiEnabled() {
		return thematicMultiEnabled;
	}

	/**
	 * @param thematicMultiEnabled the thematicMultiEnabled to set
	 */
	public void setThematicMultiEnabled(boolean thematicMultiEnabled) {
		this.thematicMultiEnabled = thematicMultiEnabled;
	}

	/**
	 * @return the languageMultiEnabled
	 */
	public boolean isLanguageMultiEnabled() {
		return languageMultiEnabled;
	}

	/**
	 * @param languageMultiEnabled the languageMultiEnabled to set
	 */
	public void setLanguageMultiEnabled(boolean languageMultiEnabled) {
		this.languageMultiEnabled = languageMultiEnabled;
	}

	/**
	 * @return the descriptionMultiEnabled
	 */
	public boolean isDescriptionMultiEnabled() {
		return descriptionMultiEnabled;
	}

	/**
	 * @param descriptionMultiEnabled the descriptionMultiEnabled to set
	 */
	public void setDescriptionMultiEnabled(boolean descriptionMultiEnabled) {
		this.descriptionMultiEnabled = descriptionMultiEnabled;
	}

	/**
	 * @return the urlMultiEnabled
	 */
	public boolean isUrlMultiEnabled() {
		return urlMultiEnabled;
	}

	/**
	 * @param urlMultiEnabled the urlMultiEnabled to set
	 */
	public void setUrlMultiEnabled(boolean urlMultiEnabled) {
		this.urlMultiEnabled = urlMultiEnabled;
	}

	/**
	 * @return the publishedByMultiEnabled
	 */
	public boolean isPublishedByMultiEnabled() {
		return publishedByMultiEnabled;
	}

	/**
	 * @param publishedByMultiEnabled the publishedByMultiEnabled to set
	 */
	public void setPublishedByMultiEnabled(boolean publishedByMultiEnabled) {
		this.publishedByMultiEnabled = publishedByMultiEnabled;
	}

	/**
	 * @return the gmd_number
	 */
	public String getGmd_number() {
		return gmd_number;
	}

	/**
	 * @param gmd_number the gmd_number to set
	 */
	public void setGmd_number(String gmd_number) {
		this.gmd_number = gmd_number;
	}

	/**
	 * @return the gmd_numberEnabled
	 */
	public boolean isGmd_numberEnabled() {
		return gmd_numberEnabled;
	}

	/**
	 * @param gmd_numberEnabled the gmd_numberEnabled to set
	 */
	public void setGmd_numberEnabled(boolean gmd_numberEnabled) {
		this.gmd_numberEnabled = gmd_numberEnabled;
	}

	/**
	 * @return the gmd_numberMultiEnabled
	 */
	public boolean isGmd_numberMultiEnabled() {
		return gmd_numberMultiEnabled;
	}

	/**
	 * @param gmd_numberMultiEnabled the gmd_numberMultiEnabled to set
	 */
	public void setGmd_numberMultiEnabled(boolean gmd_numberMultiEnabled) {
		this.gmd_numberMultiEnabled = gmd_numberMultiEnabled;
	}

}
