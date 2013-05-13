package module.zusearchive.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.ctc.wstx.util.StringUtil;

public class ZuseReplacerTask extends SwingWorker<String,Void> {

	private String inputFilename;
	private String outputFilename;
	private JProgressBar progressBar;
	private JLabel label;
	private boolean doneReading;
	private boolean doneReplacing;
	private String oldString;
	private String newString;
	private boolean stopFlag;
	
//	static final String[][] KEYSTOREPLACE_OUL = 
//		{		
//			{"Vorl__Nr_", "Vorl__Nr_".toLowerCase().replace("-", "_")},
//			{"Seite", "Seite".toLowerCase().replace("-", "_")},
//			{"Rückseiten-Info", "Rueckseiten-Info".toLowerCase().replace("-", "_")},			
//			{"Priorität", "Prioritaet".toLowerCase().replace("-", "_")},			
//			{"Stenogramm", "Stenogramm".toLowerCase().replace("-", "_")},
//			{"Steno-Seitenumfang", "steno-seitenumfang".toLowerCase().replace("-", "_")},
//			{"GMD-Transkription_Seite-n-", "GMD-Transkription_Seite-n-".toLowerCase().replace("-", "_")},
//			{"Dischinger-Transkription", "dischinger-transkription".toLowerCase().replace("-", "_")},
//			{"Datierung", "Datierung".toLowerCase().replace("-", "_")},
//			{"Laufzeit", "Laufzeit".toLowerCase().replace("-", "_")},
//			{"GMD-DATEN", "GMD-DATEN".toLowerCase().replace("-", "_")},
//			{"GMD-Nr_", "GMD-Nr_".toLowerCase().replace("-", "_")},
//			{"Mikrofilm-Nr_", "Mikrofilm-Nr_".toLowerCase().replace("-", "_")},
//			{"Syo", "Syo".toLowerCase().replace("-", "_")},
//			{"Sys", "Sys".toLowerCase().replace("-", "_")},
//			{"Sys2", "Sys2".toLowerCase().replace("-", "_")},
//			{"Sys3", "Sys3".toLowerCase().replace("-", "_")},
//			{"Gegenstand", "Gegenstand".toLowerCase().replace("-", "_")},
//			{"Materialklassifikation", "Materialklassifikation".toLowerCase().replace("-", "_")},
//			{"Verfasser", "Verfasser".toLowerCase().replace("-", "_")},
//			{"PND-Nr__Verfasser", "PND-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"GKD-Nr__Verfasser", "GKD-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"Empfänger", "Empfaenger".toLowerCase().replace("-", "_")},
//			{"PND-Nr__Empfänger", "PND-Nr__Empfaenger".toLowerCase().replace("-", "_")},
//			{"GKD-Nr__Empfänger", "GKD-Nr__Empfaenger".toLowerCase().replace("-", "_")},
//			{"Alt-Signatur", "Alt-Signatur".toLowerCase().replace("-", "_")},
//			{"Alt-Titel", "Alt-Titel".toLowerCase().replace("-", "_")},
//			{"Titel", "Titel".toLowerCase().replace("-", "_")},
//			{"Enthält", "Enthaelt".toLowerCase().replace("-", "_")},
//			{"Intus", "Intus".toLowerCase().replace("-", "_")},
//			{"Beilage", "Beilage".toLowerCase().replace("-", "_")},
//			{"Person", "Person".toLowerCase().replace("-", "_")},
//			{"Körperschaft", "Koerperschaft".toLowerCase().replace("-", "_")},
//			{"Vw_von_Körp__zu_Pers_", "Vw_von_Koerp__zu_Pers_".toLowerCase().replace("-", "_")},
//			{"Zeichner-Hersteller", "Zeichner-Hersteller".toLowerCase().replace("-", "_")},
//			{"Format_-bxh_cm-", "Format_-bxh_cm-".toLowerCase().replace("-", "_")},
//			{"Bildformat_-hxb_cm-", "Bildformat_-hxb_cm-".toLowerCase().replace("-", "_")},
//			{"Darstellung", "Darstellung".toLowerCase().replace("-", "_")},
//			{"Technik", "Technik".toLowerCase().replace("-", "_")},
//			{"Beschreibstoff", "Beschreibstoff".toLowerCase().replace("-", "_")},
//			{"Maßstab", "Massstab".toLowerCase().replace("-", "_")},
//			{"Bauteil-Nr_", "Bauteil-Nr_".toLowerCase().replace("-", "_")},
//			{"Block-Nr_", "Block-Nr_".toLowerCase().replace("-", "_")},
//			{"Schlagwort", "Schlagwort".toLowerCase().replace("-", "_")},
//			{"Ort", "Ort".toLowerCase().replace("-", "_")},
//			{"Umfang", "Umfang".toLowerCase().replace("-", "_")},
//			{"Mehrfachexemplar", "Mehrfachexemplar".toLowerCase().replace("-", "_")},
//			{"Bemerkung", "Bemerkung".toLowerCase().replace("-", "_")},
//			{"Erhaltungszustand", "Erhaltungszustand".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Nr_", "Bildstellen-Nr_".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Negativ", "Bildstellen-Negativ".toLowerCase().replace("-", "_")},
//			{"Sprache", "Sprache".toLowerCase().replace("-", "_")},
//			{"publiziert_in-", "publiziert_in-".toLowerCase().replace("-", "_")},
//			{"Literatur", "Literatur".toLowerCase().replace("-", "_")},
//			{"Serie", "Serie".toLowerCase().replace("-", "_")},
//			{"Interna", "Interna".toLowerCase().replace("-", "_")},
//			{"Standort", "Standort".toLowerCase().replace("-", "_")},
//			{"Digitalisat", "Digitalisat".toLowerCase().replace("-", "_")},
//			{"Zälfeld", "Zaehlfeld".toLowerCase().replace("-", "_")},
//			{"Bestand", "Bestand".toLowerCase().replace("-", "_")},
//			{"Signatur", "Signatur".toLowerCase().replace("-", "_")},
//			{"sysbem", "sysbem".toLowerCase().replace("-", "_")},
//			{"Übergabeliste_2005", "uebergabeliste_2005".toLowerCase().replace("-", "_")},
//			{"Berlin_Schlagwort", "Berlin_Schlagwort".toLowerCase().replace("-", "_")},
//			{"Berlin_Beschreibung", "Berlin_Beschreibung".toLowerCase().replace("-", "_")},
//			{"Berlin_Datierung", "Berlin_Datierung".toLowerCase().replace("-", "_")},
//			{"Berlin_Bemerkung", "Berlin_Bemerkung".toLowerCase().replace("-", "_")},
//			{"Internet-Freigabe", "Internet-Freigabe".toLowerCase().replace("-", "_")},
//			{"Beschränkung", "Beschraenkung".toLowerCase().replace("-", "_")},
//			{"Erfassungsdaten", "Erfassungsdaten".toLowerCase().replace("-", "_")},
//			{"Erfasst", "Erfasst".toLowerCase().replace("-", "_")},
//			{"Korrektur", "Korrektur".toLowerCase().replace("-", "_")},
//			{"Bearbeiter", "Bearbeiter".toLowerCase().replace("-", "_")}
//		};
	
//	static final String[][] KEYSTOREPLACE_OUL = 
//		{		
//			ZuseReplacer.convert4Readable("Vorl__Nr_"),
//			ZuseReplacer.convert4Readable("Seite"),
//			ZuseReplacer.convert4Readable("Rückseiten-Info"),			
//			ZuseReplacer.convert4Readable("Priorität"),			
//			ZuseReplacer.convert4Readable("Stenogramm"),
//			ZuseReplacer.convert4Readable("Steno-Seitenumfang"),
//			ZuseReplacer.convert4Readable("GMD-Transkription_Seite-n-"),
//			ZuseReplacer.convert4Readable("Dischinger-Transkription"),
//			ZuseReplacer.convert4Readable("Datierung"),
//			ZuseReplacer.convert4Readable("Laufzeit"),
//			ZuseReplacer.convert4Readable("GMD-DATEN"),
//			ZuseReplacer.convert4Readable("GMD-Nr_"),
//			ZuseReplacer.convert4Readable("Mikrofilm-Nr_"),
//			ZuseReplacer.convert4Readable("Syo"),
//			ZuseReplacer.convert4Readable("Sys"),
//			ZuseReplacer.convert4Readable("Sys2"),
//			ZuseReplacer.convert4Readable("Sys3"),
//			ZuseReplacer.convert4Readable("Gegenstand"),
//			ZuseReplacer.convert4Readable("Materialklassifikation"),
//			ZuseReplacer.convert4Readable("Verfasser"),
//			ZuseReplacer.convert4Readable("PND-Nr__Verfasser"),
//			ZuseReplacer.convert4Readable("GKD-Nr__Verfasser"),
//			ZuseReplacer.convert4Readable("Empfänger"),
//			ZuseReplacer.convert4Readable("PND-Nr__Empfänger"),
//			ZuseReplacer.convert4Readable("GKD-Nr__Empfänger"),
//			ZuseReplacer.convert4Readable("Alt-Signatur"),
//			ZuseReplacer.convert4Readable("Alt-Titel"),
//			ZuseReplacer.convert4Readable("Titel"),
//			ZuseReplacer.convert4Readable("Enthält"),
//			ZuseReplacer.convert4Readable("Intus"),
//			ZuseReplacer.convert4Readable("Beilage"),
//			ZuseReplacer.convert4Readable("Person"),
//			ZuseReplacer.convert4Readable("Körperschaft"),
//			ZuseReplacer.convert4Readable("Vw_von_Körp__zu_Pers_"),
//			ZuseReplacer.convert4Readable("Zeichner-Hersteller"),
//			ZuseReplacer.convert4Readable("Format_-bxh_cm-"),
//			ZuseReplacer.convert4Readable("Bildformat_-hxb_cm-"),
//			ZuseReplacer.convert4Readable("Darstellung"),
//			ZuseReplacer.convert4Readable("Technik"),
//			ZuseReplacer.convert4Readable("Beschreibstoff"),
//			ZuseReplacer.convert4Readable("Maßstab"),
//			ZuseReplacer.convert4Readable("Bauteil-Nr_"),
//			ZuseReplacer.convert4Readable("Block-Nr_"),
//			ZuseReplacer.convert4Readable("Schlagwort"),
//			ZuseReplacer.convert4Readable("Ort"),
//			ZuseReplacer.convert4Readable("Umfang"),
//			ZuseReplacer.convert4Readable("Mehrfachexemplar"),
//			ZuseReplacer.convert4Readable("Bemerkung"),
//			ZuseReplacer.convert4Readable("Erhaltungszustand"),
//			ZuseReplacer.convert4Readable("Bildstellen-Nr_"),
//			ZuseReplacer.convert4Readable("Bildstellen-Negativ"),
//			ZuseReplacer.convert4Readable("Sprache"),
//			ZuseReplacer.convert4Readable("publiziert_in-"),
//			ZuseReplacer.convert4Readable("Literatur"),
//			ZuseReplacer.convert4Readable("Serie"),
//			ZuseReplacer.convert4Readable("Interna"),
//			ZuseReplacer.convert4Readable("Standort"),
//			ZuseReplacer.convert4Readable("Digitalisat"),
//			ZuseReplacer.convert4Readable("Zälfeld"),
//			ZuseReplacer.convert4Readable("Bestand"),
//			ZuseReplacer.convert4Readable("Signatur"),
//			ZuseReplacer.convert4Readable("sysbem"),
//			ZuseReplacer.convert4Readable("Übergabeliste_2005"),
//			ZuseReplacer.convert4Readable("Berlin_Schlagwort"),
//			ZuseReplacer.convert4Readable("Berlin_Beschreibung"),
//			ZuseReplacer.convert4Readable("Berlin_Datierung"),
//			ZuseReplacer.convert4Readable("Berlin_Bemerkung"),
//			ZuseReplacer.convert4Readable("Internet-Freigabe"),
//			ZuseReplacer.convert4Readable("Beschränkung"),
//			ZuseReplacer.convert4Readable("Erfassungsdaten"),
//			ZuseReplacer.convert4Readable("Erfasst"),
//			ZuseReplacer.convert4Readable("Korrektur"),
//			ZuseReplacer.convert4Readable("Bearbeiter")
//		};
	
	
	static final String[][] KEYSTOREPLACE_OUL = 
		{		
			ZuseReplacerTask.convert4Readable("Vorl__Nr_"),
			ZuseReplacerTask.convert4Readable("Rückseiten-Info"),
			ZuseReplacerTask.convert4Readable("Priorität"),
			ZuseReplacerTask.convert4Readable("Stenogramm"),
			ZuseReplacerTask.convert4Readable("Steno-Seitenumfang"),
			ZuseReplacerTask.convert4Readable("GMD-Transkription_Seite-n-"),
			ZuseReplacerTask.convert4Readable("Dischinger-Transkription"),
			ZuseReplacerTask.convert4Readable("GMD-DATEN"),
			ZuseReplacerTask.convert4Readable("GMD-Nr_"),
			ZuseReplacerTask.convert4Readable("Mikrofilm-Nr_"),
			ZuseReplacerTask.convert4Readable("Syo"),
			ZuseReplacerTask.convert4Readable("Sys"),
			ZuseReplacerTask.convert4Readable("Sys2"),
			ZuseReplacerTask.convert4Readable("Sys3"),
			ZuseReplacerTask.convert4Readable("Gegenstand"),
			ZuseReplacerTask.convert4Readable("Materialklassifikation"),			
			ZuseReplacerTask.convert4Readable("PND-Nr__Verfasser"),
			ZuseReplacerTask.convert4Readable("GKD-Nr__Verfasser"),			
			ZuseReplacerTask.convert4Readable("PND-Nr__Empfänger"),
			ZuseReplacerTask.convert4Readable("GKD-Nr__Empfänger"),
			ZuseReplacerTask.convert4Readable("Alt-Signatur"),
			ZuseReplacerTask.convert4Readable("Alt-Titel"),
			ZuseReplacerTask.convert4Readable("Enthält"),
			ZuseReplacerTask.convert4Readable("Intus"),
			ZuseReplacerTask.convert4Readable("Beilage"),
			ZuseReplacerTask.convert4Readable("Körperschaft"),
			ZuseReplacerTask.convert4Readable("Vw_von_Körp__zu_Pers_"),
			ZuseReplacerTask.convert4Readable("Zeichner-Hersteller"),
			ZuseReplacerTask.convert4Readable("Format_-bxh_cm-"),
			ZuseReplacerTask.convert4Readable("Bildformat_-hxb_cm-"),
			ZuseReplacerTask.convert4Readable("Darstellung"),
			ZuseReplacerTask.convert4Readable("Technik"),
			ZuseReplacerTask.convert4Readable("Beschreibstoff"),
			ZuseReplacerTask.convert4Readable("Maßstab"),
			ZuseReplacerTask.convert4Readable("Bauteil-Nr_"),
			ZuseReplacerTask.convert4Readable("Block-Nr_"),
			ZuseReplacerTask.convert4Readable("Umfang"),
			ZuseReplacerTask.convert4Readable("Mehrfachexemplar"),
			ZuseReplacerTask.convert4Readable("Erhaltungszustand"),
			ZuseReplacerTask.convert4Readable("Bildstellen-Nr_"),
			ZuseReplacerTask.convert4Readable("Bildstellen-Negativ"),
			ZuseReplacerTask.convert4Readable("Sprache"),
			ZuseReplacerTask.convert4Readable("publiziert_in-"),
			ZuseReplacerTask.convert4Readable("Literatur"),
			ZuseReplacerTask.convert4Readable("Serie"),
			ZuseReplacerTask.convert4Readable("Interna"),
			ZuseReplacerTask.convert4Readable("Standort"),
			ZuseReplacerTask.convert4Readable("Digitalisat"),
			ZuseReplacerTask.convert4Readable("Zählfeld"),
			ZuseReplacerTask.convert4Readable("Bestand"),
			ZuseReplacerTask.convert4Readable("Signatur"),
			ZuseReplacerTask.convert4Readable("sysbem"),
			ZuseReplacerTask.convert4Readable("Übergabeliste_2005"),
			ZuseReplacerTask.convert4Readable("Berlin_Schlagwort"),
			ZuseReplacerTask.convert4Readable("Berlin_Beschreibung"),
			ZuseReplacerTask.convert4Readable("Berlin_Datierung"),
			ZuseReplacerTask.convert4Readable("Berlin_Bemerkung"),
			ZuseReplacerTask.convert4Readable("Internet-Freigabe"),
			ZuseReplacerTask.convert4Readable("Beschränkung"),
			ZuseReplacerTask.convert4Readable("Erfassungsdaten"),
			ZuseReplacerTask.convert4Readable("Erfasst"),
			ZuseReplacerTask.convert4Readable("Korrektur"),
			ZuseReplacerTask.convert4Readable("Bearbeiter"),			
			ZuseReplacerTask.convert4Readable("Person"),
			ZuseReplacerTask.convert4Readable("Ort"),
			ZuseReplacerTask.convert4Readable("Bemerkung"),
			ZuseReplacerTask.convert4Readable("Empfänger"),
			ZuseReplacerTask.convert4Readable("Titel"),
			ZuseReplacerTask.convert4Readable("Schlagwort"),
			ZuseReplacerTask.convert4Readable("Laufzeit"),
			ZuseReplacerTask.convert4Readable("Datierung"),
			ZuseReplacerTask.convert4Readable("Verfasser"),
			ZuseReplacerTask.convert4Readable("Seite")
		};
	
//	static final String[][] KEYSTOREPLACE_OUL = 
//		{		
//			ZuseReplacerTask.convert4Readable("<Vorl__Nr_>"),ZuseReplacerTask.convert4Readable("</Vorl__Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Rückseiten-Info>"),ZuseReplacerTask.convert4Readable("</Rückseiten-Info>"),
//			ZuseReplacerTask.convert4Readable("<Priorität>"),ZuseReplacerTask.convert4Readable("</Priorität>"),
//			ZuseReplacerTask.convert4Readable("<Stenogramm>"),ZuseReplacerTask.convert4Readable("</Stenogramm>"),
//			ZuseReplacerTask.convert4Readable("<Steno-Seitenumfang>"),ZuseReplacerTask.convert4Readable("</Steno-Seitenumfang>"),
//			ZuseReplacerTask.convert4Readable("<GMD-Transkription_Seite-n->"),ZuseReplacerTask.convert4Readable("</GMD-Transkription_Seite-n->"),
//			ZuseReplacerTask.convert4Readable("<Dischinger-Transkription>"),ZuseReplacerTask.convert4Readable("</Dischinger-Transkription>"),
//			ZuseReplacerTask.convert4Readable("<GMD-DATEN>"),ZuseReplacerTask.convert4Readable("</GMD-DATEN>"),
//			ZuseReplacerTask.convert4Readable("<GMD-Nr_>"),ZuseReplacerTask.convert4Readable("</GMD-Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Mikrofilm-Nr_>"),ZuseReplacerTask.convert4Readable("</Mikrofilm-Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Syo>"),ZuseReplacerTask.convert4Readable("</Syo>"),
//			ZuseReplacerTask.convert4Readable("<Sys>"),ZuseReplacerTask.convert4Readable("</Sys>"),
//			ZuseReplacerTask.convert4Readable("<Sys2>"),ZuseReplacerTask.convert4Readable("</Sys2>"),
//			ZuseReplacerTask.convert4Readable("<Sys3>"),ZuseReplacerTask.convert4Readable("</Sys3>"),
//			ZuseReplacerTask.convert4Readable("<Gegenstand>"),ZuseReplacerTask.convert4Readable("</Gegenstand>"),
//			ZuseReplacerTask.convert4Readable("<Materialklassifikation>"),ZuseReplacerTask.convert4Readable("</Materialklassifikation>"),
//			ZuseReplacerTask.convert4Readable("<PND-Nr__Verfasser>"),ZuseReplacerTask.convert4Readable("</PND-Nr__Verfasser>"),
//			ZuseReplacerTask.convert4Readable("<GKD-Nr__Verfasser>"),ZuseReplacerTask.convert4Readable("</GKD-Nr__Verfasser>"),
//			ZuseReplacerTask.convert4Readable("<PND-Nr__Empfänger>"),ZuseReplacerTask.convert4Readable("</PND-Nr__Empfänger>"),
//			ZuseReplacerTask.convert4Readable("<GKD-Nr__Empfänger>"),ZuseReplacerTask.convert4Readable("</GKD-Nr__Empfänger>"),
//			ZuseReplacerTask.convert4Readable("<Alt-Signatur>"),ZuseReplacerTask.convert4Readable("</Alt-Signatur>"),
//			ZuseReplacerTask.convert4Readable("<Alt-Titel>"),ZuseReplacerTask.convert4Readable("</Alt-Titel>"),
//			ZuseReplacerTask.convert4Readable("<Enthält>"),ZuseReplacerTask.convert4Readable("</Enthält>"),
//			ZuseReplacerTask.convert4Readable("<Intus>"),ZuseReplacerTask.convert4Readable("</Intus>"),
//			ZuseReplacerTask.convert4Readable("<Beilage>"),ZuseReplacerTask.convert4Readable("</Beilage>"),
//			ZuseReplacerTask.convert4Readable("<Körperschaft>"),ZuseReplacerTask.convert4Readable("</Körperschaft>"),
//			ZuseReplacerTask.convert4Readable("<Vw_von_Körp__zu_Pers_>"),ZuseReplacerTask.convert4Readable("</Vw_von_Körp__zu_Pers_>"),
//			ZuseReplacerTask.convert4Readable("<Zeichner-Hersteller>"),ZuseReplacerTask.convert4Readable("</Zeichner-Hersteller>"),
//			ZuseReplacerTask.convert4Readable("<Format_-bxh_cm->"),ZuseReplacerTask.convert4Readable("</Format_-bxh_cm->"),
//			ZuseReplacerTask.convert4Readable("<Bildformat_-hxb_cm->"),ZuseReplacerTask.convert4Readable("</Bildformat_-hxb_cm->"),
//			ZuseReplacerTask.convert4Readable("<Darstellung>"),ZuseReplacerTask.convert4Readable("</Darstellung>"),
//			ZuseReplacerTask.convert4Readable("<Technik>"),ZuseReplacerTask.convert4Readable("</Technik>"),
//			ZuseReplacerTask.convert4Readable("<Beschreibstoff>"),ZuseReplacerTask.convert4Readable("</Beschreibstoff>"),
//			ZuseReplacerTask.convert4Readable("<Maßstab>"),ZuseReplacerTask.convert4Readable("</Maßstab>"),
//			ZuseReplacerTask.convert4Readable("<Bauteil-Nr_>"),ZuseReplacerTask.convert4Readable("</Bauteil-Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Block-Nr_>"),ZuseReplacerTask.convert4Readable("</Block-Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Umfang>"),ZuseReplacerTask.convert4Readable("</Umfang>"),
//			ZuseReplacerTask.convert4Readable("<Mehrfachexemplar>"),ZuseReplacerTask.convert4Readable("</Mehrfachexemplar>"),
//			ZuseReplacerTask.convert4Readable("<Erhaltungszustand>"),ZuseReplacerTask.convert4Readable("</Erhaltungszustand>"),
//			ZuseReplacerTask.convert4Readable("<Bildstellen-Nr_>"),ZuseReplacerTask.convert4Readable("</Bildstellen-Nr_>"),
//			ZuseReplacerTask.convert4Readable("<Bildstellen-Negativ>"),ZuseReplacerTask.convert4Readable("</Bildstellen-Negativ>"),
//			ZuseReplacerTask.convert4Readable("<Sprache>"),ZuseReplacerTask.convert4Readable("</Sprache>"),
//			ZuseReplacerTask.convert4Readable("<publiziert_in->"),ZuseReplacerTask.convert4Readable("</publiziert_in->"),
//			ZuseReplacerTask.convert4Readable("<Literatur>"),ZuseReplacerTask.convert4Readable("</Literatur>"),
//			ZuseReplacerTask.convert4Readable("<Serie>"),ZuseReplacerTask.convert4Readable("</Serie>"),
//			ZuseReplacerTask.convert4Readable("<Interna>"),ZuseReplacerTask.convert4Readable("</Interna>"),
//			ZuseReplacerTask.convert4Readable("<Standort>"),ZuseReplacerTask.convert4Readable("</Standort>"),
//			ZuseReplacerTask.convert4Readable("<Digitalisat>"),ZuseReplacerTask.convert4Readable("</Digitalisat>"),
//			ZuseReplacerTask.convert4Readable("<Zählfeld>"),ZuseReplacerTask.convert4Readable("</Zählfeld>"),
//			ZuseReplacerTask.convert4Readable("<Bestand>"),ZuseReplacerTask.convert4Readable("</Bestand>"),
//			ZuseReplacerTask.convert4Readable("<Signatur>"),ZuseReplacerTask.convert4Readable("</Signatur>"),
//			ZuseReplacerTask.convert4Readable("<sysbem>"),ZuseReplacerTask.convert4Readable("</sysbem>"),
//			ZuseReplacerTask.convert4Readable("<Übergabeliste_2005>"),ZuseReplacerTask.convert4Readable("</Übergabeliste_2005>"),
//			ZuseReplacerTask.convert4Readable("<Berlin_Schlagwort>"),ZuseReplacerTask.convert4Readable("</Berlin_Schlagwort>"),
//			ZuseReplacerTask.convert4Readable("<Berlin_Beschreibung>"),ZuseReplacerTask.convert4Readable("</Berlin_Beschreibung>"),
//			ZuseReplacerTask.convert4Readable("<Berlin_Datierung>"),ZuseReplacerTask.convert4Readable("</Berlin_Datierung>"),
//			ZuseReplacerTask.convert4Readable("<Berlin_Bemerkung>"),ZuseReplacerTask.convert4Readable("</Berlin_Bemerkung>"),
//			ZuseReplacerTask.convert4Readable("<Internet-Freigabe>"),ZuseReplacerTask.convert4Readable("</Internet-Freigabe>"),
//			ZuseReplacerTask.convert4Readable("<Beschränkung>"),ZuseReplacerTask.convert4Readable("</Beschränkung>"),
//			ZuseReplacerTask.convert4Readable("<Erfassungsdaten>"),ZuseReplacerTask.convert4Readable("</Erfassungsdaten>"),
//			ZuseReplacerTask.convert4Readable("<Erfasst>"),ZuseReplacerTask.convert4Readable("</Erfasst>"),
//			ZuseReplacerTask.convert4Readable("<Korrektur>"),ZuseReplacerTask.convert4Readable("</Korrektur>"),
//			ZuseReplacerTask.convert4Readable("<Bearbeiter>"),ZuseReplacerTask.convert4Readable("</Bearbeiter>"),
//			ZuseReplacerTask.convert4Readable("<Person>"),ZuseReplacerTask.convert4Readable("</Person>"),
//			ZuseReplacerTask.convert4Readable("<Ort>"),ZuseReplacerTask.convert4Readable("</Ort>"),
//			ZuseReplacerTask.convert4Readable("<Bemerkung>"),ZuseReplacerTask.convert4Readable("</Bemerkung>"),
//			ZuseReplacerTask.convert4Readable("<Empfänger>"),ZuseReplacerTask.convert4Readable("</Empfänger>"),
//			ZuseReplacerTask.convert4Readable("<Titel>"),ZuseReplacerTask.convert4Readable("</Titel>"),
//			ZuseReplacerTask.convert4Readable("<Schlagwort>"),ZuseReplacerTask.convert4Readable("</Schlagwort>"),
//			ZuseReplacerTask.convert4Readable("</Laufzeit>"),ZuseReplacerTask.convert4Readable("</Laufzeit>"),
//			ZuseReplacerTask.convert4Readable("<Datierung>"),ZuseReplacerTask.convert4Readable("</Datierung>"),
//			ZuseReplacerTask.convert4Readable("<Verfasser>"),ZuseReplacerTask.convert4Readable("</Verfasser>"),
//			ZuseReplacerTask.convert4Readable("<Seite>"),ZuseReplacerTask.convert4Readable("</Seite>")
//		};
	
//	static final String[][] KEYSTOREPLACE_OFT = 
//		{
//			{"Vorl__Nr_", "Vorl__Nr_".toLowerCase().replace("-", "_")},
//			{"vorl__Dia-Nr_", "vorl__Dia-Nr_".toLowerCase().replace("-", "_")},
//			{"Übergabeliste_2005", "uebergabeliste_2005".toLowerCase().replace("-", "_")},
//			{"Zuse_Systematik", "Zuse_Systematik".toLowerCase().replace("-", "_")},
//			{"Zuse_Foto-Nr_", "Zuse_Foto-Nr_".toLowerCase().replace("-", "_")},
//			{"Zuse_weitere_Foto-Nr_", "Zuse_weitere_Foto-Nr_".toLowerCase().replace("-", "_")},
//			{"GMD-DATEN", "GMD-DATEN".toLowerCase().replace("-", "_")},
//			{"GMD-Nr_", "GMD-Nr_".toLowerCase().replace("-", "_")},
//			{"Mikrofilm-Nr_", "Mikrofilm-Nr_".toLowerCase().replace("-", "_")},
//			{"Syo", "Syo".toLowerCase().replace("-", "_")},
//			{"Sys", "Sys".toLowerCase().replace("-", "_")},
//			{"Sys2", "Sys2".toLowerCase().replace("-", "_")},
//			{"Sys3", "Sys3".toLowerCase().replace("-", "_")},
//			{"Gegenstand", "Gegenstand".toLowerCase().replace("-", "_")},
//			{"Verfasser", "Verfasser".toLowerCase().replace("-", "_")},
//			{"PND-Nr__Verfasser", "PND-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"GKD-Nr__Verfasser", "GKD-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"Titel", "Titel".toLowerCase().replace("-", "_")},
//			{"Enthält", "Enthaelt".toLowerCase().replace("-", "_")},
//			{"Beilage", "Beilage".toLowerCase().replace("-", "_")},
//			{"Person", "Person".toLowerCase().replace("-", "_")},
//			{"Körperschaft", "Koerperschaft".toLowerCase().replace("-", "_")},
//			{"Vw_von_Körp__zu_Pers_", "Vw_von_Koerp__zu_Pers_".toLowerCase().replace("-", "_")},
//			{"Schlagwort", "Schlagwort".toLowerCase().replace("-", "_")},
//			{"Datierung", "Datierung".toLowerCase().replace("-", "_")},
//			{"Laufzeit", "Laufzeit".toLowerCase().replace("-", "_")},
//			{"Ort", "Ort".toLowerCase().replace("-", "_")},
//			{"Umfang", "Umfang".toLowerCase().replace("-", "_")},
//			{"Mehrfachexemplar", "Mehrfachexemplar".toLowerCase().replace("-", "_")},
//			{"Format_-bxh_cm-", "Format_-bxh_cm-".toLowerCase().replace("-", "_")},
//			{"Bildformat_-hxb_cm-", "Bildformat_-hxb_cm-".toLowerCase().replace("-", "_")},
//			{"Sprache", "Sprache".toLowerCase().replace("-", "_")},
//			{"Bemerkung", "Bemerkung".toLowerCase().replace("-", "_")},
//			{"Erhaltungszustand", "Erhaltungszustand".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Nr_", "Bildstellen-Nr_".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Negativ", "Bildstellen-Negativ".toLowerCase().replace("-", "_")},
//			{"publiziert_in-", "publiziert_in-".toLowerCase().replace("-", "_")},
//			{"Interna", "Interna".toLowerCase().replace("-", "_")},
//			{"Digitalisat", "Digitalisat".toLowerCase().replace("-", "_")},
//			{"Zählfeld", "Zaehlfeld".toLowerCase().replace("-", "_")},
//			{"Bestand", "Bestand".toLowerCase().replace("-", "_")},
//			{"Signatur", "Signatur".toLowerCase().replace("-", "_")},
//			{"Dia-Nr_", "Dia-Nr_".toLowerCase().replace("-", "_")},
//			{"sysbem", "sysbem".toLowerCase().replace("-", "_")},
//			{"Erfassungsdaten", "Erfassungsdaten".toLowerCase().replace("-", "_")},
//			{"Erfasst", "Erfasst".toLowerCase().replace("-", "_")},
//			{"Korrektur", "Korrektur".toLowerCase().replace("-", "_")},
//			{"Bearbeiter", "Bearbeiter".toLowerCase().replace("-", "_")}
//		};
//	
//	static final String[][] KEYSTOREPLACE = 
//		{ 
//			{"Vorl__Nr_", "Vorl__Nr_".toLowerCase().replace("-", "_")},
//			{"Seite", "Seite".toLowerCase().replace("-", "_")},
//			{"Rückseiten-Info", "Rueckseiten-Info".toLowerCase().replace("-", "_")},
//			{"Priorität", "Prioritaet".toLowerCase().replace("-", "_")},
//			{"Stenogramm", "Stenogramm".toLowerCase().replace("-", "_")},
//			{"Steno-Seitenumfang", "Steno-Seitenumfang".toLowerCase().replace("-", "_")},
//			{"GMD-Transkription_Seite-n-", "GMD-Transkription_Seite-n-".toLowerCase().replace("-", "_")},
//			{"Dischinger-Transkription", "Dischinger-Transkription".toLowerCase().replace("-", "_")},
//			{"Datierung", "Datierung".toLowerCase().replace("-", "_")},
//			{"Laufzeit", "Laufzeit".toLowerCase().replace("-", "_")},
//			{"GMD-DATEN", "GMD-DATEN".toLowerCase().replace("-", "_")},
//			{"GMD-Nr_", "GMD-Nr_".toLowerCase().replace("-", "_")},
//			{"Mikrofilm-Nr_", "Mikrofilm-Nr_".toLowerCase().replace("-", "_")},
//			{"Syo", "Syo".toLowerCase().replace("-", "_")},
//			{"Sys", "Sys".toLowerCase().replace("-", "_")},
//			{"Sys2", "Sys2".toLowerCase().replace("-", "_")},
//			{"Sys3", "Sys3".toLowerCase().replace("-", "_")},
//			{"Gegenstand", "Gegenstand".toLowerCase().replace("-", "_")},
//			{"Materialklassifikation", "Materialklassifikation".toLowerCase().replace("-", "_")},
//			{"Verfasser", "Verfasser".toLowerCase().replace("-", "_")},
//			{"PND-Nr__Verfasser", "PND-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"GKD-Nr__Verfasser", "GKD-Nr__Verfasser".toLowerCase().replace("-", "_")},
//			{"Empfänger", "Empfaenger".toLowerCase().replace("-", "_")},
//			{"PND-Nr__Empfänger", "PND-Nr__Empfaenger".toLowerCase().replace("-", "_")},
//			{"GKD-Nr__Empfänger", "GKD-Nr__Empfaenger".toLowerCase().replace("-", "_")},
//			{"Alt-Signatur", "Alt-Signatur".toLowerCase().replace("-", "_")},
//			{"Alt-Titel", "Alt-Titel".toLowerCase().replace("-", "_")},
//			{"Titel", "Titel".toLowerCase().replace("-", "_")},
//			{"Enthält", "Enthaelt".toLowerCase().replace("-", "_")},
//			{"Intus", "Intus".toLowerCase().replace("-", "_")},
//			{"Beilage", "Beilage".toLowerCase().replace("-", "_")},
//			{"Person", "Person".toLowerCase().replace("-", "_")},
//			{"Körperschaft", "Koerperschaft".toLowerCase().replace("-", "_")},
//			{"Vw_von_Körp__zu_Pers_", "Vw_von_Koerp__zu_Pers_".toLowerCase().replace("-", "_")},
//			{"Zeichner-Hersteller", "Zeichner-Hersteller".toLowerCase().replace("-", "_")},
//			{"Format_-bxh_cm-", "Format_-bxh_cm-".toLowerCase().replace("-", "_")},
//			{"Bildformat_-hxb_cm-", "Bildformat_-hxb_cm-".toLowerCase().replace("-", "_")},
//			{"Darstellung", "Darstellung".toLowerCase().replace("-", "_")},
//			{"Technik", "Technik".toLowerCase().replace("-", "_")},
//			{"Beschreibstoff", "Beschreibstoff".toLowerCase().replace("-", "_")},
//			{"Maßstab", "Massstab".toLowerCase().replace("-", "_")},
//			{"Bauteil-Nr_", "Bauteil-Nr_".toLowerCase().replace("-", "_")},
//			{"Block-Nr_", "Block-Nr_".toLowerCase().replace("-", "_")},
//			{"Schlagwort", "Schlagwort".toLowerCase().replace("-", "_")},
//			{"Ort", "Ort".toLowerCase().replace("-", "_")},
//			{"Umfang", "Umfang".toLowerCase().replace("-", "_")},
//			{"Mehrfachexemplar", "Mehrfachexemplar".toLowerCase().replace("-", "_")},
//			{"Bemerkung", "Bemerkung".toLowerCase().replace("-", "_")},
//			{"Erhaltungszustand", "Erhaltungszustand".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Nr_", "Bildstellen-Nr_".toLowerCase().replace("-", "_")},
//			{"Bildstellen-Negativ", "Bildstellen-Negativ".toLowerCase().replace("-", "_")},
//			{"Sprache", "Sprache".toLowerCase().replace("-", "_")},
//			{"publiziert_in-", "publiziert_in-".toLowerCase().replace("-", "_")},
//			{"Literatur", "Literatur".toLowerCase().replace("-", "_")},
//			{"Serie", "Serie".toLowerCase().replace("-", "_")},
//			{"Interna", "Interna".toLowerCase().replace("-", "_")},
//			{"Standort", "Standort".toLowerCase().replace("-", "_")},
//			{"Digitalisat", "Digitalisat".toLowerCase().replace("-", "_")},
//			{"Zählfeld", "Zaehlfeld".toLowerCase().replace("-", "_")},
//			{"Bestand", "Bestand".toLowerCase().replace("-", "_")},
//			{"Signatur", "Signatur".toLowerCase().replace("-", "_")},
//			{"sysbem", "sysbem".toLowerCase().replace("-", "_")},
//			{"Übergabeliste_2005", "uebergabeliste_2005".toLowerCase().replace("-", "_")},
//			{"Berlin_Schlagwort", "Berlin_Schlagwort".toLowerCase().replace("-", "_")},
//			{"Berlin_Beschreibung", "Berlin_Beschreibung".toLowerCase().replace("-", "_")},
//			{"Berlin_Datierung", "Berlin_Datierung".toLowerCase().replace("-", "_")},
//			{"Berlin_Bemerkung", "Berlin_Bemerkung".toLowerCase().replace("-", "_")},
//			{"Internet-Freigabe", "Internet-Freigabe".toLowerCase().replace("-", "_")},
//			{"Beschränkung", "Beschraenkung".toLowerCase().replace("-", "_")},
//			{"Erfassungsdaten", "Erfassungsdaten".toLowerCase().replace("-", "_")},
//			{"Erfasst", "Erfasst".toLowerCase().replace("-", "_")},
//			{"Korrektur", "Korrektur".toLowerCase().replace("-", "_")},
//			{"Bearbeiter", "Bearbeiter".toLowerCase().replace("-", "_")},
//			{"vorl__Dia-Nr_", "vorl__Dia-Nr_".toLowerCase().replace("-", "_")},			
//			{"Zuse_Systematik", "Zuse_Systematik".toLowerCase().replace("-", "_")},
//			{"Zuse_Foto-Nr_", "Zuse_Foto-Nr_".toLowerCase().replace("-", "_")},
//			{"Zuse_weitere_Foto-Nr_", "Zuse_weitere_Foto-Nr_".toLowerCase().replace("-", "_")},						
//			{"Schlagwort", "Schlagwort".toLowerCase().replace("-", "_")},
//			{"Dia-Nr_", "Dia-Nr_".toLowerCase().replace("-", "_")},
//			{"ZUSE", "zuse".toLowerCase().replace("-", "_")},			
//			{"oUnterlagen", "ounterlagen".toLowerCase().replace("-", "_")}			
//		};
	
	
	public ZuseReplacerTask(String inputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this(inputFilename,getOutputFilename(inputFilename),progressBar,lblNotification);
	}
	
	public ZuseReplacerTask(String inputFilename, String outputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this.inputFilename = inputFilename;
		this.outputFilename = outputFilename;
		this.progressBar = progressBar;
		this.label = lblNotification;
		this.init();
	}
	
	private void init() {
		this.doneReading = false;
		this.doneReplacing = false;
		this.oldString = "";
		this.newString = "";
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_normalized";
		return inputFilename.substring(0, i) + "_normalized" + inputFilename.substring(i);		
	}

	
	public static String[] convert4Readable(String string) {
		
		System.setProperty("file.encoding", "UTF-8");
		
		String[] filteredString = new String[2];
		
		
		filteredString[0] = string;
		
		
//		filteredString[1] = string.toLowerCase();
//		filteredString[1] = filteredString[1].replace("-", "_");
//		filteredString[1] = filteredString[1].replace("ä", "ae");
//		filteredString[1] = filteredString[1].replace("ö", "oe");
//		filteredString[1] = filteredString[1].replace("ü", "ue");
//		filteredString[1] = filteredString[1].replace("Ä", "Ae");
//		filteredString[1] = filteredString[1].replace("Ö", "Oe");
//		filteredString[1] = filteredString[1].replace("Ü", "Ue");
//		filteredString[1] = filteredString[1].replace("ß", "ss");
		

		filteredString[1] = string.toLowerCase()
				.replace("-", "_").replace("ä", "ae").replace("ö", "oe")
				.replace("ü", "ue").replace("ß", "ss");
		
		return filteredString;
	}

	@Override
	protected String doInBackground() {
		if(this.readFileJob()) {
			this.replaceJob();
		}

		return this.outputFilename;
	}
	
	private boolean readFileJob() {
		try {
			this.label.setText("Reading file...");
			File file = new File(this.inputFilename);
			
			if(!file.exists()) {
				this.label.setText("File does not exists!");
				return false;
			}
			
			long filesize = file.length();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	        	         
	        String line = "";

	        long currentSize = 0;
	        
	        while((line = reader.readLine()) != null && !stopFlag) {
	        	this.oldString += line + "\r\n";
	        	currentSize += (long) line.length();
	        	this.progressBar.setValue((int) (this.progressBar.getMaximum() * currentSize / filesize));
	        	this.label.setText("Reading file job: "+ (100 * this.progressBar.getValue() / this.progressBar.getMaximum()) + " % done!" );
	        }
	        reader.close();
	        this.doneReading = true;

	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		return this.doneReading;
	}
	
	private boolean replaceJob() {
		try {
		       
	        
	        this.newString = new String(oldString);
	        
	        for (int i = 0; i < KEYSTOREPLACE_OUL.length && !stopFlag; i++) {
	        	
	        	this.newString = this.newString.replaceAll(KEYSTOREPLACE_OUL[i][0], KEYSTOREPLACE_OUL[i][1]);
	        	this.progressBar.setValue((int) (this.progressBar.getMaximum() * i / KEYSTOREPLACE_OUL.length));
	        	this.label.setText("Replacing job: "+ (100 * this.progressBar.getValue() / this.progressBar.getMaximum()) + " % done!" );
	        }
	        
	        FileWriter writer = new FileWriter(this.outputFilename);
	        writer.write(this.newString);	        
	        writer.close();
	        
	        this.doneReplacing = true;
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		return this.doneReplacing;
	}
	
	
	public void done() {
		
		this.oldString = "";
	    this.newString = "";
	    this.stopFlag = false;
		
	    progressBar.setValue(progressBar.getMinimum());
	    
		if(this.doneReplacing && this.doneReading) {
			this.doneReplacing = false;
		    this.doneReading = false;
		    this.label.setText("Normalizing job done!" );
		}
	}

	public void stop() {
		this.stopFlag = true;
	}
	
}
