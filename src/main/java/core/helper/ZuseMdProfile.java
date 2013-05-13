/**
 * 
 */
package main.java.core.helper;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import main.java.core.converter.MetadataProfileConverter;

/**
 * @author hnguyen
 *
 */
public enum ZuseMdProfile {	
	ALT_SIGNATUR("getAltSignatur","Alt-Signatur","Alt-Signatur","Former signature","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ALT_TITLE("getAltTitel","Alt-Titel","Alt-Titel","Former title","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BAUTEIL_NR_("getBauteilNr","Bauteil-Nr_","Bauteilnummer","Construction part number","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	BEILAGE("getBeilage","Beilage","Beilage","Attachment","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	BEMERKUNG("getBemerkung","Bemerkung","Bemerkung","Remark","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BERLIN_BEMERKUNG("getBerlinBemerkung","Berlin_Bemerkung","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BERLIN_BESCHREIBUNG("getBerlinBeschreibung","Berlin_Beschreibung","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BERLIN_DATIERUNG("getBerlinDatierung","Berlin_Datierung","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BERLIN_SCHLAGWORT("getBerlinSchlagwort","Berlin_Schlagwort","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BESCHRAEKUNG("getBeschraenkung","Beschränkung","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BESCHREIBSTOFF("getBeschreibstoff","Beschreibstoff","Beschreibstoff","Material","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BESTAND("getBestand","Bestand","Bestandsbezeichnung im DM","Inventory identifier at DM","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
//	BESTANDSBEZEICHNUNG_IM_DTM("","Bestandsbezeichnung im DTM","Bestandsbezeichnung im DTM","Inventory identifier at DTM","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BILDFORMAT__HXB_CM_("getBildformatHxbCm","Bildformat_-hxb_cm-","Bildformat (Höhe x Breite in cm)","Image size (height x width in cm)","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BILDSTELLEN_NEGATIV("getBildstellenNegativ","Bildstellen-Negativ","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BILDSTELLEN_NR_("getBildstellenNr","Bildstellen-Nr_","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	BLOCK_NR_("getBlockNr","Block-Nr_","Blocknummer","Block number","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
//	BNF_ID("","BNF ID","BNF ID (des Urhebers)","BNF ID (of the creator)","Text","Multiple","Zeichner-Hersteller",MetadataProfileConverter.getNewStatementURI().toString()),
	DARSTELLUNG("getDarstellung","Darstellung","Darstellung","Illustration technique","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
//	DATEINAME("","Dateiname","Dateiname","Filename","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	DATIERUNG("getDatierung","Datierung","Datierung","Dating","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
//	DIA_NR_("","Dia-Nr_","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	DIGITALISAT("getDigitalisat","Digitalisat","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	DISCHINGER_TRANSKRIPTION("getDischingerTranskription","Dischinger-Transkription","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	EMPFAENGER("getEmpfaenger","Empfänger","Empfänger","Recipient","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	ENTHAELT("getEnthaelt","Enthält","Enthält","Contains","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ERFASSUNGSDATEN_BEARBEITER("getErfassungsdatenBearbeiter","Bearbeiter","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ERFASSUNGSDATEN_ERFASST("getErfassungsdatenErfasst","Erfasst","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ERFASSUNGSDATEN_KORRECTUR("getErfassungsdatenKorrektur","Korrektur","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ERHALTUNGSZUSTAND("getErhaltungszustand","Erhaltungszustand","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	FORMAT__BXH_CM_("getFormatBxhCm","Format_-bxh_cm-","Format (Breite x Höhe in cm)","Dimensions (width x height in cm)","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GEGENSTAND("getGegenstand","Gegenstand","Gegenstand","Object","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GKD_NR__EMPFAENGER("getGkdNrEmpfaenger","GKD-Nr__Empfänger","GND (Körperschaft des Empfängers)","GND (body of the recipient)","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GKD_NR__VERFASSER("getGkdNrVerfasser","GKD-Nr__Verfasser","GND (Körperschaft des Verfassers)","GND (body of the author)","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GMD_DATEN_GMD_NR_("getGmdDatenGmdNr","GMD-Nr_","GMD-Nummer","GMD number","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GMD_DATEN_GMD_MIKROFILM_NR_("getGmdDatenMikrofilmNr","Mikrofilm-Nr_","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	GMD_TRANSKRIPTION_SEITE_N_("getGmdTranskriptionSeiteN","GMD-Transkription_Seite-n-","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
//	GND_DER_KOERPERSCHAFT("","GND (der Körperschaft)","GND (der Körperschaft)","GND (of the body)","Text","","Körperschaft",MetadataProfileConverter.getNewStatementURI().toString()),	
	INTERNA("getInterna","Interna","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	INTERNET_FREIGABE("getInternetFreigabe","Internet-Freigabe","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	INTUS("getIntus","Intus","Intus","Within","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	KOEPERSCHAFT("getKoerperschaft","Körperschaft","Körperschaft","Body","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),	
	LAUFZEIT("getLaufzeit","Laufzeit","Laufzeit","Duration","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	LITERATUR("getLiteratur","Literatur","Literatur","Literature","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
//	LCCN("","LCCN","LCCN (des Urhebers)","LCCN (of the creator)","Text","Multiple","Zeichner-Hersteller",MetadataProfileConverter.getNewStatementURI().toString()),	
	MASSSTAB("getMassstab","Maßstab","Maßstab","Scale","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	MATERIALKLASSIFIKATION("getMaterialklassifikation","Materialklassifikation","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	MEHRFACHEXEMPLAR("getMehrfachexemplar","Mehrfachexemplar","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ORT("getOrt","Ort","Entstehungsort","Location of creation","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	PERSON("getPerson","Person","Person","Person","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	PND_NR__EMPFAENGER("getPndNrEmpfaenger","PND-Nr__Empfänger","GND (des Empfängers)","GND (of the recipient)","Text","Multiple","Empfänger",MetadataProfileConverter.getNewStatementURI().toString()),
	PND_NR__VERFASSER("getPndNrVerfasser","PND-Nr__Verfasser","GND (des Urhebers)","GND (of the creator)","Text","Multiple","Zeichner-Hersteller",MetadataProfileConverter.getNewStatementURI().toString()),
	PRIORITAET("getPrioritaet","Priorität","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	PUBLIZIERT_IN_("getPubliziertIn","publiziert_in-","Publikation","Publication","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	RUECKSEITEN_INFO("getRueckseitenInfo","Rückseiten-Info","Rückseite","Reverse side","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	SCHLAGWORT("getSchlagwort","Schlagwort","Schlagwort","Keyword","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	SERIE("getSerie","Serie","Serie","Series","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	SEITE("getSeite","Seite","Seite","Page","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	SINGATUR("getSignatur","Signatur","Signatur","Signatory","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
	SPRACHE("getSprache","Sprache","Sprache","Language","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	STANDORT("getStandort","Standort","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	STENO_SEITENUMFANG("getStenoSeitenumfang","Steno-Seitenumfang","Stenogrammumfang","Extent of shorthand","Text","","Stenogramm",MetadataProfileConverter.getNewStatementURI().toString()),
	STENOGRAMM("getStenogramm","Stenogramm","Stenogramm","Shorthand","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	SYO("getSyo","Syo","1. Gliederungsebene","Classification level 1","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	SYS("getSys","Sys","2. Gliederungsebene","Classification level 2","Text","","Syo",MetadataProfileConverter.getNewStatementURI().toString()),
	SYS2("getSys2","Sys2","3. Gliederungsebene","Classification level 3","Text","","Sys",MetadataProfileConverter.getNewStatementURI().toString()),
	SYS3("getSys3","Sys3","4. Gliederungsebene","Classification level 4","Text","","Sys2",MetadataProfileConverter.getNewStatementURI().toString()),
	SYSBEM("getSysbem","sysbem","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	TECHNIK("getTechnik","Technik","Technik","Technique","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	TITEL("getTitel","Titel","Titel","Title","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	UEBERGABELISTE_2005("getUebergabeliste2005","Übergabeliste_2005","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	UMFANG("getUmfang","Umfang","Umfang","Extent","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	VERFASSER("getVerfasser","Verfasser","Verfasser","Author","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString()),
//	VIAF("","VIAF","VIAF (des Urhebers)","VIAF (of the creator)","Text","Multiple","Zeichner-Hersteller",MetadataProfileConverter.getNewStatementURI().toString()),
	VORL__NR_("getVorlNr","Vorl__Nr_","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	VW_VON_KOEP_ZU_PERS_("getVwVonKoerpZuPers","Vw_von_Köp__zu_Pers_","Verweis von Körperschaften auf Personen","Link from bodies to persons","Text","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ZAEHLFELD("getZaehlfeld","Zählfeld","","","","","",MetadataProfileConverter.getNewStatementURI().toString()),
	ZEICHNER_HERSTELLER("getZeichnerHersteller","Zeichner-Hersteller","Urheber","Creator","Text","Multiple","",MetadataProfileConverter.getNewStatementURI().toString());
	
	public enum Column {METHOD_NAME, ORIGINAL_LABEL, DE_LABEL, EN_LABEL, TYPE, MUTIPLE, PARENT, STATEMENT_ID};
	
	private static final String namespace = "http://zuse.zib.de/terms/";
	private static final String kind = "statement";
	
	public static final String url = namespace + kind + "/";
	
	private String[] attributes;	

	ZuseMdProfile(String ... attributes){
		this.setAttributes(attributes);
	}

	/**
	 * @return the attributes
	 */
	public String[] getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(String[] attributes) {		
		this.attributes = attributes;
	}
	
	public static List<?> enum2list(Class<? extends ZuseMdProfile> cls) {
		   return (List<?>) Arrays.asList(cls.getEnumConstants());
	}
}
