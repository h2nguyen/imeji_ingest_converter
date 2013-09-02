package module.zusearchive.vo.generated;

public class ExcelEntry {
	private String type;
	private boolean typeEnabled = false;
	
	private String zia_id;
	private boolean zia_idEnabled = false;
	
	private String gmd_nr;
	private boolean gmd_nrEnabled = false;
	
	private String author;
	private boolean authorEnabled = false;
	
	private String year;
	private boolean yearEnabled = false;
	
	private String title;
	private boolean titleEnabled = false;
	
	private String file;
	private boolean fileEnabled = false;
	
	private String chronological;
	private boolean chronologicalEnabled = false;
	
	private String thematic;
	private boolean thematicEnabled = false;
	
	private String language;
	private boolean languageEnabled = false;
	
	private String description;
	private boolean descriptionEnabled = false;
	
	private String directory;
	private boolean directoryEnabled = false;
	
	private String url;
	private boolean urlEnabled = false;
	
	private String publishedBy;
	private boolean publishedByEnabled = false;

	public ExcelEntry(
			String type, boolean typeEnabled,
			String zia_id, boolean zia_idEnabled,
			String gmd_nr, boolean gmd_nrEnabled,
			String author, boolean authorEnabled,
			String year, boolean yearEnabled,
			String title, boolean titleEnabled,
			String file, boolean fileEnabled,
			String chronological, boolean chronologicalEnabled,
			String thematic, boolean thematicEnabled,
			String language, boolean languageEnabled, 
			String description, boolean descriptionEnabled, 
			String url, boolean urlEnabled,
			String directory, boolean directoryEnabled,
			String publishedBy, boolean publishedByEnabled) {
		this.type = type;
		this.typeEnabled = typeEnabled;
		
		this.zia_id = zia_id;
		this.zia_idEnabled = zia_idEnabled;
		
		this.setGmd_nr(gmd_nr);
		this.gmd_nrEnabled = gmd_nrEnabled;
		
		this.author = author;
		this.authorEnabled = authorEnabled;
		
		this.year = year;
		this.yearEnabled = yearEnabled;
		
		this.title = title;
		this.titleEnabled = titleEnabled;
		
		this.file = file;
		this.fileEnabled = fileEnabled;
		
		this.setChronological(chronological);
		this.chronologicalEnabled = chronologicalEnabled;
		
		this.setThematic(thematic);
		this.thematicEnabled = thematicEnabled;
		
		this.setLanguage(language);
		this.languageEnabled = languageEnabled;
		
		this.directory = directory;
		this.directoryEnabled = directoryEnabled;
		
		this.url = url;
		this.urlEnabled = urlEnabled;
		
		this.setDescription(description);
		this.descriptionEnabled = descriptionEnabled;
		
		this.setPublishedBy(publishedBy);
		this.publishedByEnabled = publishedByEnabled;
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

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
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

	public String getGmd_nr() {
		return gmd_nr;
	}

	public void setGmd_nr(String gmd_nr) {
		this.gmd_nr = gmd_nr;
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
	 * @return the gmd_nrEnabled
	 */
	public boolean isGmd_nrEnabled() {
		return gmd_nrEnabled;
	}

	/**
	 * @param gmd_nrEnabled the gmd_nrEnabled to set
	 */
	public void setGmd_nrEnabled(boolean gmd_nrEnabled) {
		this.gmd_nrEnabled = gmd_nrEnabled;
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
	 * @return the directoryEnabled
	 */
	public boolean isDirectoryEnabled() {
		return directoryEnabled;
	}

	/**
	 * @param directoryEnabled the directoryEnabled to set
	 */
	public void setDirectoryEnabled(boolean directoryEnabled) {
		this.directoryEnabled = directoryEnabled;
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

}
