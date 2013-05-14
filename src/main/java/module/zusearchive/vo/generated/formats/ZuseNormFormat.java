/**
 * 
 */
package module.zusearchive.vo.generated.formats;

import module.zusearchive.vo.generated.enums.ZuseOUnterlagenEMdProfileFormat;
import core.vo.generated.formats.NormFormat;
import core.vo.generated.formats.interfaces.IMdProfileFormat;


/**
 * @author hnguyen
 * 
 */
public final class ZuseNormFormat extends NormFormat {
	
	static ZuseOUnterlagenEMdProfileFormat zemdpf;

	static String[][] KEYSTOREPLACE_OUL = {
			NormFormat.norm("Vorl__Nr_"),
			NormFormat.norm("Rückseiten-Info"),
			NormFormat.norm("Priorität"),
			NormFormat.norm("Stenogramm"),
			NormFormat.norm("Steno-Seitenumfang"),
			NormFormat.norm("GMD-Transkription_Seite-n-"),
			NormFormat.norm("Dischinger-Transkription"),
			NormFormat.norm("GMD-DATEN"),
			NormFormat.norm("GMD-Nr_"),
			NormFormat.norm("Mikrofilm-Nr_"),
			NormFormat.norm("Syo"),
			NormFormat.norm("Sys"),
			NormFormat.norm("Sys2"),
			NormFormat.norm("Sys3"),
			NormFormat.norm("Gegenstand"),
			NormFormat.norm("Materialklassifikation"),
			NormFormat.norm("PND-Nr__Verfasser"),
			NormFormat.norm("GKD-Nr__Verfasser"),
			NormFormat.norm("PND-Nr__Empfänger"),
			NormFormat.norm("GKD-Nr__Empfänger"),
			NormFormat.norm("Alt-Signatur"),
			NormFormat.norm("Alt-Titel"),
			NormFormat.norm("Enthält"),
			NormFormat.norm("Intus"),
			NormFormat.norm("Beilage"),
			NormFormat.norm("Körperschaft"),
			NormFormat.norm("Vw_von_Körp__zu_Pers_"),
			NormFormat.norm("Zeichner-Hersteller"),
			NormFormat.norm("Format_-bxh_cm-"),
			NormFormat.norm("Bildformat_-hxb_cm-"),
			NormFormat.norm("Darstellung"),
			NormFormat.norm("Technik"),
			NormFormat.norm("Beschreibstoff"),
			NormFormat.norm("Maßstab"),
			NormFormat.norm("Bauteil-Nr_"),
			NormFormat.norm("Block-Nr_"),
			NormFormat.norm("Umfang"),
			NormFormat.norm("Mehrfachexemplar"),
			NormFormat.norm("Erhaltungszustand"),
			NormFormat.norm("Bildstellen-Nr_"),
			NormFormat.norm("Bildstellen-Negativ"),
			NormFormat.norm("Sprache"),
			NormFormat.norm("publiziert_in-"),
			NormFormat.norm("Literatur"),
			NormFormat.norm("Serie"),
			NormFormat.norm("Interna"),
			NormFormat.norm("Standort"),
			NormFormat.norm("Digitalisat"),
			NormFormat.norm("Zählfeld"),
			NormFormat.norm("Bestand"),
			NormFormat.norm("Signatur"),
			NormFormat.norm("sysbem"),
			NormFormat.norm("Übergabeliste_2005"),
			NormFormat.norm("Berlin_Schlagwort"),
			NormFormat.norm("Berlin_Beschreibung"),
			NormFormat.norm("Berlin_Datierung"),
			NormFormat.norm("Berlin_Bemerkung"),
			NormFormat.norm("Internet-Freigabe"),
			NormFormat.norm("Beschränkung"),
			NormFormat.norm("Erfassungsdaten"),
			NormFormat.norm("Erfasst"),
			NormFormat.norm("Korrektur"),
			NormFormat.norm("Bearbeiter"),
			NormFormat.norm("Person"),
			NormFormat.norm("Ort"),
			NormFormat.norm("Bemerkung"),
			NormFormat.norm("Empfänger"),
			NormFormat.norm("Titel"),
			NormFormat.norm("Schlagwort"),
			NormFormat.norm("Laufzeit"),
			NormFormat.norm("Datierung"),
			NormFormat.norm("Verfasser"),
			NormFormat.norm("Seite") };

	

	@Override
	public String[][] getKeyStorePlaces() {
		return this.KEYSTOREPLACE_OUL;
	}

	@Override
	public void setKeyStorePlace(String[][] keyStorePlaces) {
		this.KEYSTOREPLACE_OUL = keyStorePlaces;
	}

	@Override
	public void seteMdProfileFormat(
			Enum<? extends IMdProfileFormat> eMdProfileFormat) {
		this.zemdpf = (ZuseOUnterlagenEMdProfileFormat) eMdProfileFormat;
		
	}

	@Override
	public Enum<? extends IMdProfileFormat> geteMdProfileFormat() {
		return this.zemdpf;
	}

}
