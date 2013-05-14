/**
 * 
 */
package module.zusearchive.vo.generated.enums;

import java.util.Arrays;
import java.util.List;

import core.helper.IdentifierUtil;
import core.vo.generated.formats.interfaces.IMdProfileFormat;
import core.vo.imeji.Statement;

/**
 * @author hnguyen
 *
 */
public enum ZuseOUnterlagenEMdProfileFormat implements IMdProfileFormat {
	
	ALT_SIGNATUR("getAltSignatur","Alt-Signatur","Alt-Signatur","Former signature","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	ALT_TITLE("getAltTitel","Alt-Titel","Alt-Titel","Former title","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	BAUTEIL_NR_("getBauteilNr","Bauteil-Nr_","Bauteilnummer","Construction part number","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	BEILAGE("getBeilage","Beilage","Beilage","Attachment","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	BEMERKUNG("getBemerkung","Bemerkung","Bemerkung","Remark","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	BERLIN_BEMERKUNG("getBerlinBemerkung","Berlin_Bemerkung","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BERLIN_BESCHREIBUNG("getBerlinBeschreibung","Berlin_Beschreibung","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BERLIN_DATIERUNG("getBerlinDatierung","Berlin_Datierung","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BERLIN_SCHLAGWORT("getBerlinSchlagwort","Berlin_Schlagwort","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BESCHRAEKUNG("getBeschraenkung","Beschränkung","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BESCHREIBSTOFF("getBeschreibstoff","Beschreibstoff","Beschreibstoff","Material","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	BESTAND("getBestand","Bestand","Bestandsbezeichnung im DM","Inventory identifier at DM","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
//	BESTANDSBEZEICHNUNG_IM_DTM("","Bestandsbezeichnung im DTM","Bestandsbezeichnung im DTM","Inventory identifier at DTM","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	BILDFORMAT__HXB_CM_("getBildformatHxbCm","Bildformat_-hxb_cm-","Bildformat (Höhe x Breite in cm)","Image size (height x width in cm)","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	BILDSTELLEN_NEGATIV("getBildstellenNegativ","Bildstellen-Negativ","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BILDSTELLEN_NR_("getBildstellenNr","Bildstellen-Nr_","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	BLOCK_NR_("getBlockNr","Block-Nr_","Blocknummer","Block number","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
//	BNF_ID("","BNF ID","BNF ID (des Urhebers)","BNF ID (of the creator)","Text","Multiple","Zeichner-Hersteller",IdentifierUtil.newURI(Statement.class).toString()),
	DARSTELLUNG("getDarstellung","Darstellung","Darstellung","Illustration technique","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
//	DATEINAME("","Dateiname","Dateiname","Filename","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	DATIERUNG("getDatierung","Datierung","Datierung","Dating","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
//	DIA_NR_("","Dia-Nr_","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	DIGITALISAT("getDigitalisat","Digitalisat","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	DISCHINGER_TRANSKRIPTION("getDischingerTranskription","Dischinger-Transkription","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	EMPFAENGER("getEmpfaenger","Empfänger","Empfänger","Recipient","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	ENTHAELT("getEnthaelt","Enthält","Enthält","Contains","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	ERFASSUNGSDATEN_BEARBEITER("getErfassungsdatenBearbeiter","Bearbeiter","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	ERFASSUNGSDATEN_ERFASST("getErfassungsdatenErfasst","Erfasst","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	ERFASSUNGSDATEN_KORRECTUR("getErfassungsdatenKorrektur","Korrektur","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	ERHALTUNGSZUSTAND("getErhaltungszustand","Erhaltungszustand","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	FORMAT__BXH_CM_("getFormatBxhCm","Format_-bxh_cm-","Format (Breite x Höhe in cm)","Dimensions (width x height in cm)","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	GEGENSTAND("getGegenstand","Gegenstand","Gegenstand","Object","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	GKD_NR__EMPFAENGER("getGkdNrEmpfaenger","GKD-Nr__Empfänger","GND (Körperschaft des Empfängers)","GND (body of the recipient)","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	GKD_NR__VERFASSER("getGkdNrVerfasser","GKD-Nr__Verfasser","GND (Körperschaft des Verfassers)","GND (body of the author)","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	GMD_DATEN_GMD_NR_("getGmdDatenGmdNr","GMD-Nr_","GMD-Nummer","GMD number","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	GMD_DATEN_GMD_MIKROFILM_NR_("getGmdDatenMikrofilmNr","Mikrofilm-Nr_","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	GMD_TRANSKRIPTION_SEITE_N_("getGmdTranskriptionSeiteN","GMD-Transkription_Seite-n-","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
//	GND_DER_KOERPERSCHAFT("","GND (der Körperschaft)","GND (der Körperschaft)","GND (of the body)","Text","","Körperschaft",IdentifierUtil.newURI(Statement.class).toString()),	
	INTERNA("getInterna","Interna","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	INTERNET_FREIGABE("getInternetFreigabe","Internet-Freigabe","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	INTUS("getIntus","Intus","Intus","Within","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	KOEPERSCHAFT("getKoerperschaft","Körperschaft","Körperschaft","Body","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),	
	LAUFZEIT("getLaufzeit","Laufzeit","Laufzeit","Duration","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	LITERATUR("getLiteratur","Literatur","Literatur","Literature","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
//	LCCN("","LCCN","LCCN (des Urhebers)","LCCN (of the creator)","Text","Multiple","Zeichner-Hersteller",IdentifierUtil.newURI(Statement.class).toString()),	
	MASSSTAB("getMassstab","Maßstab","Maßstab","Scale","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	MATERIALKLASSIFIKATION("getMaterialklassifikation","Materialklassifikation","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	MEHRFACHEXEMPLAR("getMehrfachexemplar","Mehrfachexemplar","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	ORT("getOrt","Ort","Entstehungsort","Location of creation","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	PERSON("getPerson","Person","Person","Person","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	PND_NR__EMPFAENGER("getPndNrEmpfaenger","PND-Nr__Empfänger","GND (des Empfängers)","GND (of the recipient)","Text","Multiple","Empfänger",IdentifierUtil.newURI(Statement.class).toString()),
	PND_NR__VERFASSER("getPndNrVerfasser","PND-Nr__Verfasser","GND (des Urhebers)","GND (of the creator)","Text","Multiple","Zeichner-Hersteller",IdentifierUtil.newURI(Statement.class).toString()),
	PRIORITAET("getPrioritaet","Priorität","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	PUBLIZIERT_IN_("getPubliziertIn","publiziert_in-","Publikation","Publication","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	RUECKSEITEN_INFO("getRueckseitenInfo","Rückseiten-Info","Rückseite","Reverse side","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	SCHLAGWORT("getSchlagwort","Schlagwort","Schlagwort","Keyword","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	SERIE("getSerie","Serie","Serie","Series","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	SEITE("getSeite","Seite","Seite","Page","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	SINGATUR("getSignatur","Signatur","Signatur","Signatory","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
	SPRACHE("getSprache","Sprache","Sprache","Language","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	STANDORT("getStandort","Standort","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	STENO_SEITENUMFANG("getStenoSeitenumfang","Steno-Seitenumfang","Stenogrammumfang","Extent of shorthand","Text","","Stenogramm",IdentifierUtil.newURI(Statement.class).toString()),
	STENOGRAMM("getStenogramm","Stenogramm","Stenogramm","Shorthand","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	SYO("getSyo","Syo","1. Gliederungsebene","Classification level 1","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	SYS("getSys","Sys","2. Gliederungsebene","Classification level 2","Text","","Syo",IdentifierUtil.newURI(Statement.class).toString()),
	SYS2("getSys2","Sys2","3. Gliederungsebene","Classification level 3","Text","","Sys",IdentifierUtil.newURI(Statement.class).toString()),
	SYS3("getSys3","Sys3","4. Gliederungsebene","Classification level 4","Text","","Sys2",IdentifierUtil.newURI(Statement.class).toString()),
	SYSBEM("getSysbem","sysbem","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	TECHNIK("getTechnik","Technik","Technik","Technique","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	TITEL("getTitel","Titel","Titel","Title","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	UEBERGABELISTE_2005("getUebergabeliste2005","Übergabeliste_2005","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	UMFANG("getUmfang","Umfang","Umfang","Extent","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	VERFASSER("getVerfasser","Verfasser","Verfasser","Author","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString()),
//	VIAF("","VIAF","VIAF (des Urhebers)","VIAF (of the creator)","Text","Multiple","Zeichner-Hersteller",IdentifierUtil.newURI(Statement.class).toString()),
	VORL__NR_("getVorlNr","Vorl__Nr_","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	VW_VON_KOEP_ZU_PERS_("getVwVonKoerpZuPers","Vw_von_Köp__zu_Pers_","Verweis von Körperschaften auf Personen","Link from bodies to persons","Text","","",IdentifierUtil.newURI(Statement.class).toString()),
	ZAEHLFELD("getZaehlfeld","Zählfeld","","","","","",IdentifierUtil.newURI(Statement.class).toString()),
	ZEICHNER_HERSTELLER("getZeichnerHersteller","Zeichner-Hersteller","Urheber","Creator","Text","Multiple","",IdentifierUtil.newURI(Statement.class).toString());
	
	public enum Column {METHOD_NAME, ORIGINAL_LABEL, DE_LABEL, EN_LABEL, TYPE, MUTIPLE, PARENT, STATEMENT_ID};
	
	private static final String namespace = "http://zuse.zib.de/terms/";
	private static final String kind = "statement";
	
	public static final String url = namespace + kind + "/";
	
	private String[] attributes;
	
	ZuseOUnterlagenEMdProfileFormat(){	
		
	}
	
	ZuseOUnterlagenEMdProfileFormat(String ... attributes){
		this.setAttributes(attributes);
	}

	@Override
	public String[] getAttributes() {
		return this.attributes;
	}

	@Override
	public void setAttributes(String[] attributes) {
		this.attributes = attributes; 
		
	}

	public static List<?> enum2list(Class<? extends IMdProfileFormat> cls) {
		return (List<?>) Arrays.asList(cls.getEnumConstants());		
	}

}
