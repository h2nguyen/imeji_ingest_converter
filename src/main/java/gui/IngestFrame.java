package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.mapper.ItemsMapperTask;
import core.mapper.MdProfileMapperTask;
import core.task.enums.Task;
import core.task.enums.Update;
import core.vo.imeji.Items;
import core.vo.imeji.MetadataProfile;

import module.zusearchive.converter.ZuseExcelConverter;
import module.zusearchive.converter.ZuseXMLConverter;
import module.zusearchive.jaxb.JaxbZuseGenericObject;
import module.zusearchive.misc.ZuseNormalizer;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.ZUSE;
import module.zusearchive.vo.generated.formats.enums.ZuseXMLMDEnumType;
import javax.swing.JComboBox;

import jxl.read.biff.BiffException;

public class IngestFrame extends JFrame implements ActionListener,
		DropTargetListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572692269203819216L;
	private JPanel contentPane;
	private JTextField txtRawData;
	private JButton btnGenerateOfflineMetadataProfile;
	private JButton btnChooseRawData;
	private JFileChooser chooserFile;
	private JLabel lblNotification;
	private JProgressBar progressBar;
	private JButton btnNormalizeForImeji;

	private JLabel lblRawData;
	private JLabel lblNormalizedData;
	private JTextField txtNormalizedData;
	private JButton btnChooseNormalizedData;
	private JLabel lblMetadataProfile;
	private JTextField txtGeneratedOfflineMetadataProfile;
	private JButton btnChooseMetadataProfileOffline;
	private JLabel lblStep_1;
	private JLabel lblStep_2;
	private JLabel lblStep_3;
	private JButton btnMergeItems;
	private JTextField txtMetadataProfileOnline;
	private JTextField txtMetadataProfileMerged;
	private JButton btnChooseMetadataProfileOnline;
	private JButton btnChooseMetadataProfileMerged;
	private JButton btnCancel;
	private JButton btnGenerateItems;
	private JLabel lblStep_2a;
	private JLabel lblStep_2b;
	private JLabel lblGeneratedItems;
	private JTextField txtGeneratedItems;
	private JButton btnChooseItems;

	private JButton btnGetMetadataProfileOnline;
	private JLabel lblStep_2c;
	private JButton btnMergeMetadataProfile;
	private JLabel lblStep_3a;
	private JLabel lblStep_3b;
	private JLabel lblStep_3c;
	private JButton btnGetItemsOnline;
	private JTextField txtItemOnline;
	private JTextField txtItemsMerged;
	private JButton btnChooseItemsOnline;
	private JButton btnChooseItemsMerged;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JComboBox cbbFileType;
	private JComboBox cbbFileTypItems;

	private String cbbStringFileTyp[] = new String[] { "XML", "XLS-PDF", "XLS" };
	private JButton btnClearAll;
	private JTextField txtMdProfileAsExcel;
	private JButton btnChooseExcelMdProfile;

	/**
	 * Create the frame.
	 */
	public IngestFrame() {
		System.setProperty("file.encoding", "UTF-8");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 86, 61, 0, 167, 57, 0, 0,
				0, 168, 56, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 0,
				21, 0, 0, 0, 0, 0, 20, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0,
				1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblRawData = new JLabel("Raw data");
		GridBagConstraints gbc_lblRawData = new GridBagConstraints();
		gbc_lblRawData.gridwidth = 2;
		gbc_lblRawData.insets = new Insets(0, 0, 5, 5);
		gbc_lblRawData.gridx = 1;
		gbc_lblRawData.gridy = 0;
		contentPane.add(lblRawData, gbc_lblRawData);

		txtRawData = new JTextField();
		GridBagConstraints gbc_txtRawData = new GridBagConstraints();
		gbc_txtRawData.gridwidth = 5;
		gbc_txtRawData.fill = GridBagConstraints.BOTH;
		gbc_txtRawData.insets = new Insets(0, 0, 5, 5);
		gbc_txtRawData.gridx = 4;
		gbc_txtRawData.gridy = 0;
		contentPane.add(txtRawData, gbc_txtRawData);
		txtRawData.setColumns(10);

		btnChooseRawData = new JButton("...");
		GridBagConstraints gbc_btnChooseRawData = new GridBagConstraints();
		gbc_btnChooseRawData.fill = GridBagConstraints.BOTH;
		gbc_btnChooseRawData.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseRawData.gridx = 9;
		gbc_btnChooseRawData.gridy = 0;
		contentPane.add(btnChooseRawData, gbc_btnChooseRawData);

		separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.gridwidth = 12;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 1;
		contentPane.add(separator_3, gbc_separator_3);

		lblNormalizedData = new JLabel("Normalized data");
		GridBagConstraints gbc_lblNormalizedData = new GridBagConstraints();
		gbc_lblNormalizedData.gridwidth = 2;
		gbc_lblNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_lblNormalizedData.gridx = 1;
		gbc_lblNormalizedData.gridy = 2;
		contentPane.add(lblNormalizedData, gbc_lblNormalizedData);

		txtNormalizedData = new JTextField();
		GridBagConstraints gbc_txtNormalizedData = new GridBagConstraints();
		gbc_txtNormalizedData.fill = GridBagConstraints.BOTH;
		gbc_txtNormalizedData.gridwidth = 5;
		gbc_txtNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_txtNormalizedData.gridx = 4;
		gbc_txtNormalizedData.gridy = 2;
		contentPane.add(txtNormalizedData, gbc_txtNormalizedData);
		txtNormalizedData.setColumns(10);

		btnChooseNormalizedData = new JButton("...");
		GridBagConstraints gbc_btnChooseNormalizedData = new GridBagConstraints();
		gbc_btnChooseNormalizedData.fill = GridBagConstraints.BOTH;
		gbc_btnChooseNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseNormalizedData.gridx = 9;
		gbc_btnChooseNormalizedData.gridy = 2;
		contentPane.add(btnChooseNormalizedData, gbc_btnChooseNormalizedData);

		separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.gridwidth = 12;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 3;
		contentPane.add(separator_2, gbc_separator_2);

		lblMetadataProfile = new JLabel("Metadata profile");
		GridBagConstraints gbc_lblMetadataProfile = new GridBagConstraints();
		gbc_lblMetadataProfile.gridheight = 4;
		gbc_lblMetadataProfile.gridwidth = 2;
		gbc_lblMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetadataProfile.gridx = 1;
		gbc_lblMetadataProfile.gridy = 4;
		contentPane.add(lblMetadataProfile, gbc_lblMetadataProfile);
		
		txtMdProfileAsExcel = new JTextField();
		GridBagConstraints gbc_txtMdProfileAsExcel = new GridBagConstraints();
		gbc_txtMdProfileAsExcel.gridwidth = 5;
		gbc_txtMdProfileAsExcel.insets = new Insets(0, 0, 5, 5);
		gbc_txtMdProfileAsExcel.fill = GridBagConstraints.BOTH;
		gbc_txtMdProfileAsExcel.gridx = 4;
		gbc_txtMdProfileAsExcel.gridy = 4;
		contentPane.add(txtMdProfileAsExcel, gbc_txtMdProfileAsExcel);
		txtMdProfileAsExcel.setColumns(10);
		
		btnChooseExcelMdProfile = new JButton("...");
		GridBagConstraints gbc_btnChooseExcelMdProfile = new GridBagConstraints();
		gbc_btnChooseExcelMdProfile.fill = GridBagConstraints.BOTH;
		gbc_btnChooseExcelMdProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseExcelMdProfile.gridx = 9;
		gbc_btnChooseExcelMdProfile.gridy = 4;
		contentPane.add(btnChooseExcelMdProfile, gbc_btnChooseExcelMdProfile);

		txtMetadataProfileOnline = new JTextField();
		GridBagConstraints gbc_txtMetadataProfileOnline = new GridBagConstraints();
		gbc_txtMetadataProfileOnline.fill = GridBagConstraints.BOTH;
		gbc_txtMetadataProfileOnline.gridwidth = 5;
		gbc_txtMetadataProfileOnline.insets = new Insets(0, 0, 5, 5);
		gbc_txtMetadataProfileOnline.gridx = 4;
		gbc_txtMetadataProfileOnline.gridy = 5;
		contentPane.add(txtMetadataProfileOnline, gbc_txtMetadataProfileOnline);
		txtMetadataProfileOnline.setColumns(10);

		btnChooseMetadataProfileOnline = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfileOnline = new GridBagConstraints();
		gbc_btnChooseMetadataProfileOnline.fill = GridBagConstraints.BOTH;
		gbc_btnChooseMetadataProfileOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfileOnline.gridx = 9;
		gbc_btnChooseMetadataProfileOnline.gridy = 5;
		contentPane.add(btnChooseMetadataProfileOnline,
				gbc_btnChooseMetadataProfileOnline);

		txtGeneratedOfflineMetadataProfile = new JTextField();
		GridBagConstraints gbc_txtGeneratedOfflineMetadataProfile = new GridBagConstraints();
		gbc_txtGeneratedOfflineMetadataProfile.gridwidth = 5;
		gbc_txtGeneratedOfflineMetadataProfile.fill = GridBagConstraints.BOTH;
		gbc_txtGeneratedOfflineMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_txtGeneratedOfflineMetadataProfile.gridx = 4;
		gbc_txtGeneratedOfflineMetadataProfile.gridy = 6;
		contentPane.add(txtGeneratedOfflineMetadataProfile,
				gbc_txtGeneratedOfflineMetadataProfile);
		txtGeneratedOfflineMetadataProfile.setColumns(10);

		btnChooseMetadataProfileOffline = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfileOffline = new GridBagConstraints();
		gbc_btnChooseMetadataProfileOffline.fill = GridBagConstraints.BOTH;
		gbc_btnChooseMetadataProfileOffline.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfileOffline.gridx = 9;
		gbc_btnChooseMetadataProfileOffline.gridy = 6;
		contentPane.add(btnChooseMetadataProfileOffline,
				gbc_btnChooseMetadataProfileOffline);

		txtMetadataProfileMerged = new JTextField();
		GridBagConstraints gbc_txtMetadataProfileMerged = new GridBagConstraints();
		gbc_txtMetadataProfileMerged.gridwidth = 5;
		gbc_txtMetadataProfileMerged.fill = GridBagConstraints.BOTH;
		gbc_txtMetadataProfileMerged.insets = new Insets(0, 0, 5, 5);
		gbc_txtMetadataProfileMerged.gridx = 4;
		gbc_txtMetadataProfileMerged.gridy = 7;
		contentPane.add(txtMetadataProfileMerged, gbc_txtMetadataProfileMerged);
		txtMetadataProfileMerged.setColumns(10);

		btnChooseMetadataProfileMerged = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfileMerged = new GridBagConstraints();
		gbc_btnChooseMetadataProfileMerged.fill = GridBagConstraints.BOTH;
		gbc_btnChooseMetadataProfileMerged.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfileMerged.gridx = 9;
		gbc_btnChooseMetadataProfileMerged.gridy = 7;
		contentPane.add(btnChooseMetadataProfileMerged,
				gbc_btnChooseMetadataProfileMerged);

		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 12;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 8;
		contentPane.add(separator_1, gbc_separator_1);

		lblGeneratedItems = new JLabel("Items");
		GridBagConstraints gbc_lblGeneratedItems = new GridBagConstraints();
		gbc_lblGeneratedItems.gridheight = 3;
		gbc_lblGeneratedItems.gridwidth = 2;
		gbc_lblGeneratedItems.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneratedItems.gridx = 1;
		gbc_lblGeneratedItems.gridy = 9;
		contentPane.add(lblGeneratedItems, gbc_lblGeneratedItems);

		txtItemOnline = new JTextField();
		GridBagConstraints gbc_txtItemOnline = new GridBagConstraints();
		gbc_txtItemOnline.fill = GridBagConstraints.BOTH;
		gbc_txtItemOnline.gridwidth = 5;
		gbc_txtItemOnline.insets = new Insets(0, 0, 5, 5);
		gbc_txtItemOnline.gridx = 4;
		gbc_txtItemOnline.gridy = 9;
		contentPane.add(txtItemOnline, gbc_txtItemOnline);
		txtItemOnline.setColumns(10);

		btnChooseItemsOnline = new JButton("...");
		GridBagConstraints gbc_btnChooseItemsOnline = new GridBagConstraints();
		gbc_btnChooseItemsOnline.fill = GridBagConstraints.BOTH;
		gbc_btnChooseItemsOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItemsOnline.gridx = 9;
		gbc_btnChooseItemsOnline.gridy = 9;
		contentPane.add(btnChooseItemsOnline, gbc_btnChooseItemsOnline);

		txtGeneratedItems = new JTextField();
		GridBagConstraints gbc_txtGeneratedItems = new GridBagConstraints();
		gbc_txtGeneratedItems.fill = GridBagConstraints.BOTH;
		gbc_txtGeneratedItems.gridwidth = 5;
		gbc_txtGeneratedItems.insets = new Insets(0, 0, 5, 5);
		gbc_txtGeneratedItems.gridx = 4;
		gbc_txtGeneratedItems.gridy = 10;
		contentPane.add(txtGeneratedItems, gbc_txtGeneratedItems);
		txtGeneratedItems.setColumns(10);

		btnChooseItems = new JButton("...");
		GridBagConstraints gbc_btnChooseItems = new GridBagConstraints();
		gbc_btnChooseItems.fill = GridBagConstraints.BOTH;
		gbc_btnChooseItems.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItems.gridx = 9;
		gbc_btnChooseItems.gridy = 10;
		contentPane.add(btnChooseItems, gbc_btnChooseItems);

		txtItemsMerged = new JTextField();
		GridBagConstraints gbc_txtItemsMerged = new GridBagConstraints();
		gbc_txtItemsMerged.fill = GridBagConstraints.BOTH;
		gbc_txtItemsMerged.gridwidth = 5;
		gbc_txtItemsMerged.insets = new Insets(0, 0, 5, 5);
		gbc_txtItemsMerged.gridx = 4;
		gbc_txtItemsMerged.gridy = 11;
		contentPane.add(txtItemsMerged, gbc_txtItemsMerged);
		txtItemsMerged.setColumns(10);

		btnChooseItemsMerged = new JButton("...");
		GridBagConstraints gbc_btnChooseItemsMerged = new GridBagConstraints();
		gbc_btnChooseItemsMerged.fill = GridBagConstraints.BOTH;
		gbc_btnChooseItemsMerged.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItemsMerged.gridx = 9;
		gbc_btnChooseItemsMerged.gridy = 11;
		contentPane.add(btnChooseItemsMerged, gbc_btnChooseItemsMerged);

		separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.gridwidth = 12;
		gbc_separator_4.fill = GridBagConstraints.BOTH;
		gbc_separator_4.insets = new Insets(0, 0, 5, 0);
		gbc_separator_4.gridx = 0;
		gbc_separator_4.gridy = 12;
		contentPane.add(separator_4, gbc_separator_4);

		lblStep_1 = new JLabel("1. Step");
		GridBagConstraints gbc_lblStep_1 = new GridBagConstraints();
		gbc_lblStep_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_1.gridx = 1;
		gbc_lblStep_1.gridy = 13;
		contentPane.add(lblStep_1, gbc_lblStep_1);
		lblStep_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnNormalizeForImeji = new JButton("Normalize");
		GridBagConstraints gbc_btnNormalizeForImeji = new GridBagConstraints();
		gbc_btnNormalizeForImeji.fill = GridBagConstraints.BOTH;
		gbc_btnNormalizeForImeji.gridwidth = 6;
		gbc_btnNormalizeForImeji.insets = new Insets(0, 0, 5, 5);
		gbc_btnNormalizeForImeji.gridx = 4;
		gbc_btnNormalizeForImeji.gridy = 13;
		contentPane.add(btnNormalizeForImeji, gbc_btnNormalizeForImeji);

		lblStep_2a = new JLabel("2a. Step");
		GridBagConstraints gbc_lblStep_2a = new GridBagConstraints();
		gbc_lblStep_2a.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_2a.gridx = 2;
		gbc_lblStep_2a.gridy = 14;
		contentPane.add(lblStep_2a, gbc_lblStep_2a);

		lblStep_2 = new JLabel("2. Step");
		GridBagConstraints gbc_lblStep_2 = new GridBagConstraints();
		gbc_lblStep_2.gridheight = 3;
		gbc_lblStep_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_2.gridx = 1;
		gbc_lblStep_2.gridy = 14;
		contentPane.add(lblStep_2, gbc_lblStep_2);
		lblStep_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnGetMetadataProfileOnline = new JButton("Get online metadata profile");
		GridBagConstraints gbc_btnGetMetadataProfileOnline = new GridBagConstraints();
		gbc_btnGetMetadataProfileOnline.fill = GridBagConstraints.BOTH;
		gbc_btnGetMetadataProfileOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetMetadataProfileOnline.gridx = 4;
		gbc_btnGetMetadataProfileOnline.gridy = 14;
		contentPane.add(btnGetMetadataProfileOnline,
				gbc_btnGetMetadataProfileOnline);

		lblStep_3a = new JLabel("3a. Step");
		GridBagConstraints gbc_lblStep_3a = new GridBagConstraints();
		gbc_lblStep_3a.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_3a.gridx = 8;
		gbc_lblStep_3a.gridy = 14;
		contentPane.add(lblStep_3a, gbc_lblStep_3a);

		btnGetItemsOnline = new JButton("Get online items");
		GridBagConstraints gbc_btnGetItemsOnline = new GridBagConstraints();
		gbc_btnGetItemsOnline.fill = GridBagConstraints.BOTH;
		gbc_btnGetItemsOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetItemsOnline.gridx = 9;
		gbc_btnGetItemsOnline.gridy = 14;
		contentPane.add(btnGetItemsOnline, gbc_btnGetItemsOnline);

		lblStep_2b = new JLabel("2b. Step");
		GridBagConstraints gbc_lblStep_2b = new GridBagConstraints();
		gbc_lblStep_2b.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_2b.gridx = 2;
		gbc_lblStep_2b.gridy = 15;
		contentPane.add(lblStep_2b, gbc_lblStep_2b);

		lblStep_3 = new JLabel("3. Step");
		GridBagConstraints gbc_lblStep_3 = new GridBagConstraints();
		gbc_lblStep_3.gridheight = 3;
		gbc_lblStep_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_3.gridx = 7;
		gbc_lblStep_3.gridy = 14;
		contentPane.add(lblStep_3, gbc_lblStep_3);
		lblStep_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnGenerateOfflineMetadataProfile = new JButton("Get offline profile");
		GridBagConstraints gbc_btnGenerateOfflineMetadataProfile = new GridBagConstraints();
		gbc_btnGenerateOfflineMetadataProfile.fill = GridBagConstraints.BOTH;
		gbc_btnGenerateOfflineMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerateOfflineMetadataProfile.gridx = 4;
		gbc_btnGenerateOfflineMetadataProfile.gridy = 15;
		contentPane.add(btnGenerateOfflineMetadataProfile,
				gbc_btnGenerateOfflineMetadataProfile);

		cbbFileType = new JComboBox(cbbStringFileTyp);
		GridBagConstraints gbc_cbbFileType = new GridBagConstraints();
		gbc_cbbFileType.insets = new Insets(0, 0, 5, 5);
		gbc_cbbFileType.fill = GridBagConstraints.BOTH;
		gbc_cbbFileType.gridx = 5;
		gbc_cbbFileType.gridy = 15;
		contentPane.add(cbbFileType, gbc_cbbFileType);

		lblStep_3b = new JLabel("3b. Step");
		GridBagConstraints gbc_lblStep_3b = new GridBagConstraints();
		gbc_lblStep_3b.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_3b.gridx = 8;
		gbc_lblStep_3b.gridy = 15;
		contentPane.add(lblStep_3b, gbc_lblStep_3b);

		btnGenerateItems = new JButton("Get items with merged profile");
		GridBagConstraints gbc_btnGenerateItems = new GridBagConstraints();
		gbc_btnGenerateItems.fill = GridBagConstraints.BOTH;
		gbc_btnGenerateItems.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerateItems.gridx = 9;
		gbc_btnGenerateItems.gridy = 15;
		contentPane.add(btnGenerateItems, gbc_btnGenerateItems);

		cbbFileTypItems = new JComboBox(cbbStringFileTyp);
		GridBagConstraints gbc_cbbFileTypItems = new GridBagConstraints();
		gbc_cbbFileTypItems.insets = new Insets(0, 0, 5, 5);
		gbc_cbbFileTypItems.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbFileTypItems.gridx = 10;
		gbc_cbbFileTypItems.gridy = 15;
		contentPane.add(cbbFileTypItems, gbc_cbbFileTypItems);

		lblStep_2c = new JLabel("2c. Step");
		GridBagConstraints gbc_lblStep_2c = new GridBagConstraints();
		gbc_lblStep_2c.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_2c.gridx = 2;
		gbc_lblStep_2c.gridy = 16;
		contentPane.add(lblStep_2c, gbc_lblStep_2c);

		btnMergeMetadataProfile = new JButton("Merge metadata profile");
		GridBagConstraints gbc_btnMergeMetadataProfile = new GridBagConstraints();
		gbc_btnMergeMetadataProfile.fill = GridBagConstraints.BOTH;
		gbc_btnMergeMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnMergeMetadataProfile.gridx = 4;
		gbc_btnMergeMetadataProfile.gridy = 16;
		contentPane.add(btnMergeMetadataProfile, gbc_btnMergeMetadataProfile);

		lblStep_3c = new JLabel("3c. Step");
		GridBagConstraints gbc_lblStep_3c = new GridBagConstraints();
		gbc_lblStep_3c.insets = new Insets(0, 0, 5, 5);
		gbc_lblStep_3c.gridx = 8;
		gbc_lblStep_3c.gridy = 16;
		contentPane.add(lblStep_3c, gbc_lblStep_3c);

		btnMergeItems = new JButton("Merge items");
		GridBagConstraints gbc_btnMergeItems = new GridBagConstraints();
		gbc_btnMergeItems.fill = GridBagConstraints.BOTH;
		gbc_btnMergeItems.insets = new Insets(0, 0, 5, 5);
		gbc_btnMergeItems.gridx = 9;
		gbc_btnMergeItems.gridy = 16;
		contentPane.add(btnMergeItems, gbc_btnMergeItems);

		lblNotification = new JLabel("Notification");
		GridBagConstraints gbc_lblNotification = new GridBagConstraints();
		gbc_lblNotification.gridwidth = 3;
		gbc_lblNotification.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotification.gridx = 1;
		gbc_lblNotification.gridy = 17;
		contentPane.add(lblNotification, gbc_lblNotification);
		
		btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnClearAll = new GridBagConstraints();
		gbc_btnClearAll.fill = GridBagConstraints.BOTH;
		gbc_btnClearAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnClearAll.gridx = 4;
		gbc_btnClearAll.gridy = 17;
		contentPane.add(btnClearAll, gbc_btnClearAll);

		btnCancel = new JButton();
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 17;
		contentPane.add(btnCancel, gbc_btnCancel);
		btnCancel.setEnabled(false);
		btnCancel.setToolTipText("Click to cancel the process");
		btnCancel
				.setIcon(new ImageIcon(
						IngestFrame.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));

		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 8;
		gbc_progressBar.gridy = 17;
		contentPane.add(progressBar, gbc_progressBar);

		initOtherVariables();
		initActionListener();
	}

	private void initOtherVariables() {
		chooserFile = new JFileChooser(this.getClass().getProtectionDomain()
				.getCodeSource().getLocation().getPath());

		this.txtRawData.setDragEnabled(true);
		new DropTarget(txtRawData, this);

		this.txtNormalizedData.setDragEnabled(true);
		new DropTarget(txtNormalizedData, this);

		this.txtMetadataProfileOnline.setDragEnabled(true);
		new DropTarget(txtMetadataProfileOnline, this);

		this.txtGeneratedOfflineMetadataProfile.setDragEnabled(true);
		new DropTarget(txtGeneratedOfflineMetadataProfile, this);

		this.txtMetadataProfileMerged.setDragEnabled(true);
		new DropTarget(txtMetadataProfileMerged, this);

		this.txtItemOnline.setDragEnabled(true);
		new DropTarget(txtItemOnline, this);

		this.txtGeneratedItems.setDragEnabled(true);
		new DropTarget(txtGeneratedItems, this);

		this.txtItemsMerged.setDragEnabled(true);
		new DropTarget(txtItemsMerged, this);
		
		this.txtMdProfileAsExcel.setDragEnabled(true);
		new DropTarget(txtMdProfileAsExcel, this);
	}

	private void initActionListener() {
		this.btnChooseRawData.addActionListener(this);
		this.btnChooseNormalizedData.addActionListener(this);
		this.btnChooseExcelMdProfile.addActionListener(this);
		this.btnChooseMetadataProfileOffline.addActionListener(this);
		this.btnChooseMetadataProfileOnline.addActionListener(this);
		this.btnChooseMetadataProfileMerged.addActionListener(this);
		this.btnChooseItems.addActionListener(this);
		this.btnChooseItemsOnline.addActionListener(this);
		this.btnChooseItemsMerged.addActionListener(this);

		this.btnNormalizeForImeji.addActionListener(this);

		this.btnGenerateOfflineMetadataProfile.addActionListener(this);
		this.btnGetMetadataProfileOnline.addActionListener(this);
		this.btnMergeMetadataProfile.addActionListener(this);

		this.btnGenerateItems.addActionListener(this);
		this.btnGetItemsOnline.addActionListener(this);
		this.btnMergeItems.addActionListener(this);

		this.btnCancel.addActionListener(this);
		this.btnClearAll.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		// choosing buttons
		if (e.getSource() == this.btnChooseRawData) {
			this.chooserFile.setDialogTitle("Open raw data file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtRawData.setText(this.chooserFile.getSelectedFile()
						.getAbsolutePath());
				this.notifyMessage("A raw data file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseNormalizedData) {
			this.chooserFile.setDialogTitle("Open normalized data file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtNormalizedData.setText(this.chooserFile
						.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A normalized data file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseExcelMdProfile) {
			this.chooserFile.setDialogTitle("Open excel meta data profile file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtMdProfileAsExcel
						.setText(this.chooserFile.getSelectedFile()
								.getAbsolutePath());
				this.notifyMessage("An excel meta data profile file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseMetadataProfileOffline) {
			this.chooserFile.setDialogTitle("Open meta data profile file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtGeneratedOfflineMetadataProfile
						.setText(this.chooserFile.getSelectedFile()
								.getAbsolutePath());
				this.notifyMessage("A meta data profile file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseMetadataProfileOnline) {
			this.chooserFile
					.setDialogTitle("Open online meta data profile file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtMetadataProfileOnline.setText(this.chooserFile
						.getSelectedFile().getAbsolutePath());
				this.notifyMessage("An online meta data profile file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseMetadataProfileMerged) {
			this.chooserFile
					.setDialogTitle("Open a merged meta data profile file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtMetadataProfileMerged.setText(this.chooserFile
						.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A merged meta data profile file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseItems) {
			this.chooserFile.setDialogTitle("Open an items file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtGeneratedItems.setText(this.chooserFile
						.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A self created item file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseItemsOnline) {
			this.chooserFile.setDialogTitle("Open an online items file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtItemOnline.setText(this.chooserFile.getSelectedFile()
						.getAbsolutePath());
				this.notifyMessage("An online item file was seleted!");
			}
		}

		if (e.getSource() == this.btnChooseItemsMerged) {
			this.chooserFile.setDialogTitle("Open a merged items file");
			if (this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtItemsMerged.setText(this.chooserFile.getSelectedFile()
						.getAbsolutePath());
				this.notifyMessage("A merged item file was seleted!");
			}
		}

		// normalize the raw data into non-special character file
		if (e.getSource() == this.btnNormalizeForImeji) {
			while (this.txtRawData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open raw data file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtRawData.setText(this.chooserFile.getSelectedFile()
							.getAbsolutePath());
				}
			}

			// create a normalized file from raw file
			String inputFilename = this.txtRawData.getText();

			this.notifyMessage("Normalizing...");

			ZuseNormalizer n = new ZuseNormalizer(inputFilename);
			if (n.normalizeFile() != null) {
				this.notifyMessage("Done normalizing!");
				this.txtNormalizedData.setText(n.getOutputFile()
						.getAbsolutePath());
			} else {
				this.notifyMessage("Error while normalizing!");
			}
		}

		// gets the metedata profile from the online repository
		if (e.getSource() == this.btnGetMetadataProfileOnline) {
			while (this.txtMetadataProfileOnline.getText().isEmpty()) {
				this.chooserFile
						.setDialogTitle("Open an online meta data profile file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMetadataProfileOnline.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}
		}

		// generate the meta data profile from the raw data
		if (e.getSource() == this.btnGenerateOfflineMetadataProfile) {
			while (this.txtNormalizedData.getText().isEmpty()) {
				this.chooserFile
						.setDialogTitle("Open normalized raw data file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtNormalizedData.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}
			while (this.txtMdProfileAsExcel.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open excel meta data file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMdProfileAsExcel.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			try {

				String filenameEntriesNormalizedOffline = this.txtNormalizedData
						.getText();

				MetadataProfile raw_gen_mdp = null;

				this.notifyMessage("Generating metadata profile file...");

				if (cbbFileType.getSelectedIndex() == 0) { // XML handling
					ZUSE oz = new JaxbZuseGenericObject<ZUSE>(ZUSE.class)
							.unmarshal(filenameEntriesNormalizedOffline);
					ZuseXMLConverter zmdpconv = new ZuseXMLConverter();
					List<OUnterlagen> ouls = oz.getOUnterlagen();
					// generate an imeji meta data profile from the specific
					// object
					raw_gen_mdp = zmdpconv
							.getMdProfile(
									ouls.get(0),
									"Generated metadata profile",
									"The metadata profile is generated from the Zuse object",
									ZuseXMLMDEnumType.getEnumList());

				} else if (cbbFileType.getSelectedIndex() == 1) { // Excel handling
					raw_gen_mdp = new ZuseExcelConverter().getMdProfile4PDF(
							filenameEntriesNormalizedOffline, "Metadata file",
							"Metadata file generated from an Excel file");
				} else if (cbbFileType.getSelectedIndex() == 2) { // Excel handling
					ZUSE oz = new JaxbZuseGenericObject<ZUSE>(ZUSE.class)
							.unmarshal(filenameEntriesNormalizedOffline);
					ZuseXMLConverter zmdpconv = new ZuseXMLConverter();
					List<OUnterlagen> ouls = oz.getOUnterlagen();
					// generate an imeji meta data profile from the specific
					// object
					raw_gen_mdp = zmdpconv
							.getMdProfileExcel(
									ouls.get(0),
									"Generated metadata profile",
									"The metadata profile is generated from the Zuse object",
									new File(this.txtMdProfileAsExcel.getText()));
				}
				
				String filenameMdpOffline = ZuseXMLConverter
						.getOfflineMDFilename(filenameEntriesNormalizedOffline,"xml");

				FileOutputStream fos_raw_gen_mdp = new FileOutputStream(
						new File(filenameMdpOffline));

				JaxbGenericObject.writeToOutputStream(raw_gen_mdp,
						fos_raw_gen_mdp);
				this.notifyMessage("Done generating metadata profile file!");

				this.txtGeneratedOfflineMetadataProfile
						.setText(filenameMdpOffline);

			} catch (JAXBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SAXException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IntrospectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// merges the offline meta data profile with the online version
		if (e.getSource() == this.btnMergeMetadataProfile) {
			while (this.txtGeneratedOfflineMetadataProfile.getText().isEmpty()) {
				this.chooserFile
						.setDialogTitle("Open a generated meta data profile file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtGeneratedOfflineMetadataProfile
							.setText(this.chooserFile.getSelectedFile()
									.getAbsolutePath());
				}
			}

			while (this.txtMetadataProfileOnline.getText().isEmpty()) {
				this.chooserFile
						.setDialogTitle("Open an online meta data profile file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMetadataProfileOnline.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			try {
				String filenameMdpOnline = this.txtMetadataProfileOnline
						.getText(); // download from the internet
				String filenameMdpOffline = this.txtGeneratedOfflineMetadataProfile
						.getText();
				MdProfileMapperTask mdpmt = new MdProfileMapperTask(
						filenameMdpOnline, filenameMdpOffline, Task.UPDATE);
				this.notifyMessage("Generating merged metadata profile file...");
				mdpmt.execute();
				MetadataProfile mdpMerged = mdpmt.get();
				String filenameMdpMerged = MdProfileMapperTask
						.getOfflineMDFilename(filenameMdpOffline);
				FileOutputStream fos_mdp_merged = new FileOutputStream(
						new File(filenameMdpMerged));
				JaxbGenericObject
						.writeToOutputStream(mdpMerged, fos_mdp_merged);
				this.notifyMessage("Done generating merged metadata profile file!");
				this.txtMetadataProfileMerged.setText(filenameMdpMerged);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JAXBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SAXException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// gets the items data from the online repository
		if (e.getSource() == this.btnGetItemsOnline) {
			while (this.txtItemOnline.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online item file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtItemOnline.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}
		}

		// generate the items from the normalized raw data file
		if (e.getSource() == this.btnGenerateItems) {
			while (this.txtNormalizedData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open a normlized file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtNormalizedData.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			while (this.txtMetadataProfileMerged.getText().isEmpty()) {
				this.chooserFile
						.setDialogTitle("Open a merged meta data profile file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMetadataProfileMerged.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}
			
			while (this.txtMdProfileAsExcel.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open excel meta data file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMdProfileAsExcel.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			try {
				
				Items items = null;
				String filenameEntriesNormalizedOffline = this.txtNormalizedData.getText();
				String filenameMdpMerged = this.txtMetadataProfileMerged.getText();
				MetadataProfile merged_mdp = new JaxbGenericObject<MetadataProfile>(
						MetadataProfile.class).unmarshal(filenameMdpMerged);
				this.notifyMessage("Generating items...");
				if (cbbFileTypItems.getSelectedIndex() == 0) { // XML handling
					// generate the imeji items from the specific object (Zuse
					// object) with the provided final merged meta data profile
					ZUSE oz = new JaxbZuseGenericObject<ZUSE>(ZUSE.class)
							.unmarshal(filenameEntriesNormalizedOffline);
					ZuseXMLConverter zmdpconv = new ZuseXMLConverter();
					List<OUnterlagen> ouls = oz.getOUnterlagen();
					items = zmdpconv.getItems(ouls,
							ZuseXMLMDEnumType.getEnumList(), merged_mdp);
				} else if (cbbFileTypItems.getSelectedIndex() == 1) { // Excel handling
					ZuseExcelConverter zec = new ZuseExcelConverter();
					items = zec.getItems4PDF(filenameEntriesNormalizedOffline,merged_mdp);
				} else if (cbbFileTypItems.getSelectedIndex() == 2) { // XML handling
					// generate the imeji items from the specific object (Zuse
					// object) with the provided final merged meta data profile
					ZUSE oz = new JaxbZuseGenericObject<ZUSE>(ZUSE.class)
							.unmarshal(filenameEntriesNormalizedOffline);
					ZuseXMLConverter zmdpconv = new ZuseXMLConverter();
					List<OUnterlagen> ouls = oz.getOUnterlagen();
					items = zmdpconv.getItemsExcel(ouls,new File(this.txtMdProfileAsExcel.getText()), merged_mdp);
				}
				
				String filenameItemsOffline = ZuseXMLConverter
						.getOfflineItemsFilename(filenameEntriesNormalizedOffline,"xml");
				FileOutputStream fos_items = new FileOutputStream(new File(
						filenameItemsOffline));
				JaxbGenericObject.writeToOutputStream(items, fos_items);
				this.notifyMessage("Done generating items!");
				this.txtGeneratedItems.setText(filenameItemsOffline);
				
			} catch (JAXBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SAXException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IntrospectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// merges the offline with the online items information
		if (e.getSource() == this.btnMergeItems) {

			while (this.txtGeneratedItems.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an offline items file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtGeneratedItems.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			while (this.txtItemOnline.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online items file");
				if (this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtItemOnline.setText(this.chooserFile
							.getSelectedFile().getAbsolutePath());
				}
			}

			try {

				// merge the online items with the offline generated items
				String filenameItemsOnline = this.txtItemOnline.getText(); // download
																			// from
																			// the
																			// internet
				String filenameItemsWithMergedMD = this.txtGeneratedItems
						.getText();
				ItemsMapperTask ismt = new ItemsMapperTask(filenameItemsOnline,
						filenameItemsWithMergedMD, Task.OVERWRITE,
						Update.UPDATE_BY_FILENAME);
				ismt.execute();
				Items itemsMerged = ismt.get();
				String mergedItemsFilename = ItemsMapperTask
						.getMergedItemsFilename(filenameItemsWithMergedMD);
				FileOutputStream fos_final_items_to_ingest = new FileOutputStream(
						new File(mergedItemsFilename));

				JaxbGenericObject.writeToOutputStream(itemsMerged,
						fos_final_items_to_ingest);
				this.notifyMessage("Done generating final items!");
				this.txtItemsMerged.setText(mergedItemsFilename);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == this.btnClearAll) {
			this.txtGeneratedItems.setText("");
			this.txtGeneratedOfflineMetadataProfile.setText("");
			this.txtItemOnline.setText("");
			this.txtItemsMerged.setText("");
			this.txtMetadataProfileMerged.setText("");
			this.txtMetadataProfileOnline.setText("");
			this.txtNormalizedData.setText("");
			this.txtRawData.setText("");
		}
		//
		//
		// if(e.getSource() == this.btnCancel) {
		// if(zrt != null) {
		// this.zrt.stop();
		// }
		//
		// if(it != null) {
		// this.it.stop();
		// }
		// }
	}

	public void notifyMessage(String message) {
		this.lblNotification.setText(message);
	}

	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable tr = dtde.getTransferable();
			if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				List files = (List) tr
						.getTransferData(DataFlavor.javaFileListFlavor);

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtRawData)) {
					this.txtRawData.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtNormalizedData)) {
					this.txtNormalizedData.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtMetadataProfileOnline)) {
					this.txtMetadataProfileOnline.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtGeneratedOfflineMetadataProfile)) {
					this.txtGeneratedOfflineMetadataProfile
							.setText(((File) files.get(0)).getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtMetadataProfileMerged)) {
					this.txtMetadataProfileMerged.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtItemOnline)) {
					this.txtItemOnline.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtGeneratedItems)) {
					this.txtGeneratedItems.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				if (dtde.getDropTargetContext().getComponent()
						.equals(this.txtItemsMerged)) {
					this.txtItemsMerged.setText(((File) files.get(0))
							.getAbsolutePath());
				}

				dtde.getDropTargetContext().dropComplete(true);
			} else {
				System.err
						.println("DataFlavor.javaFileListFlavor is not supported, rejected");
				dtde.rejectDrop();
			}
		} catch (IOException ex) {
			System.err.println("IOException");
			ex.printStackTrace();
			dtde.rejectDrop();
		} catch (UnsupportedFlavorException ex) {
			System.err.println("UnsupportedFlavorException");
			ex.printStackTrace();
			dtde.rejectDrop();
		}
	}

}
