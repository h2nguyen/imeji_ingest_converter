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
import java.io.File;
import java.io.IOException;
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

import module.zusearchive.tasks.GenerateItemAndMetadataProfileTask;
import module.zusearchive.tasks.GenerateMetadataProfileTask;
import module.zusearchive.tasks.GenerateStatementMappingFileTask;
import module.zusearchive.tasks.GenerateZuseMergedMetadataProfileTask;
import module.zusearchive.tasks.GenerateZuseItemsTask;
import module.zusearchive.tasks.MergeOfflineAndOnlineItemsTask;
import module.zusearchive.tasks.ZuseReplacerTask;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;


public class IngestFrame extends JFrame implements ActionListener, DropTargetListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572692269203819216L;
	private JPanel contentPane;
	private JTextField txtRawData;
	private JButton btnConvertToImeji;
	private JButton btnGenerateMetadataProfile;
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
	private JTextField txtGeneratedMetadataProfile;
	private JButton btnChooseMetadataProfile;
	private JLabel lblStep_1;
	private JLabel lblStep_2;
	private JLabel lblStep_3;
	private JLabel lblStep_4;
	private JButton btnMergeItems;
	private JTextField txtMetadataProfileOnlineInformation;
	private JTextField txtMetadataProfileMerged;
	private JButton btnChooseMetadataProfileOnline;
	private JButton btnChooseMetadataProfileMerged;
	private JButton btnCancel;
	private JPanel panelMetadataProfileIssue;
	private JButton btnGenerateItems;
	private JLabel lblStep_2a;
	private JLabel lblStep_2b;
	private JLabel lblGeneratedItems;
	private JTextField txtGeneratedItems;
	private JButton btnChooseItems;

	
	private ZuseReplacerTask zrt;
	private GenerateZuseItemsTask git;
	private GenerateMetadataProfileTask gmdt;
	private GenerateItemAndMetadataProfileTask gimdt;
	private GenerateZuseMergedMetadataProfileTask gmmdt;
	private MergeOfflineAndOnlineItemsTask mooit;
	private GenerateStatementMappingFileTask gsmf;
	
	private JButton btnGetMetadataProfileOnline;
	private JLabel lblStep_2c;
	private JButton btnMergeMetadataProfile;
	private JPanel panelItemIssue;
	private JLabel lblStep_4a;
	private JLabel lblStep_4b;
	private JLabel lblStep_4c;
	private JButton btnGetItemsOnline;
	private JTextField txtItemOnlineInformation;
	private JTextField txtItemsMerged;
	private JButton btnChooseItemsOnline;
	private JButton btnChooseItemsMerged;
	private JLabel lblStep_3a;
	private JButton btnGetStatementMappingFile;
	private JTextField txtStatementMappingFile;
	private JButton btnChooseStatementMappingFile;
	private JPanel panelStatementIssue;
	private JLabel lblStep_5;
	private JLabel lblbStep_3b;
	private JButton btnGetMergedStatementMappingFile;
	private JTextField txtMergedStatementMappingFile;
	private JButton btnChooseMergedStatementMappingFile;
	private JLabel lblStatementMapping;
	private JLabel lblStep_0;
	private JButton btnGenerateMetadataProfileAndItems;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	
	/**
	 * Create the frame.
	 */
	public IngestFrame() {
		System.setProperty("file.encoding", "UTF-8");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 49, 61, 0, 364, 0, 0, 71, 265, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 259, 45, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblRawData = new JLabel("Raw data");
		GridBagConstraints gbc_lblRawData = new GridBagConstraints();
		gbc_lblRawData.gridwidth = 2;
		gbc_lblRawData.insets = new Insets(0, 0, 5, 5);
		gbc_lblRawData.gridx = 1;
		gbc_lblRawData.gridy = 1;
		contentPane.add(lblRawData, gbc_lblRawData);
		
		txtRawData = new JTextField();
		GridBagConstraints gbc_txtRawData = new GridBagConstraints();
		gbc_txtRawData.gridwidth = 4;
		gbc_txtRawData.fill = GridBagConstraints.BOTH;
		gbc_txtRawData.insets = new Insets(0, 0, 5, 5);
		gbc_txtRawData.gridx = 4;
		gbc_txtRawData.gridy = 1;
		contentPane.add(txtRawData, gbc_txtRawData);
		txtRawData.setColumns(10);
		
		btnChooseRawData = new JButton("...");
		GridBagConstraints gbc_btnChooseRawData = new GridBagConstraints();
		gbc_btnChooseRawData.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseRawData.gridx = 8;
		gbc_btnChooseRawData.gridy = 1;
		contentPane.add(btnChooseRawData, gbc_btnChooseRawData);
		
		separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.gridwidth = 10;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 2;
		contentPane.add(separator_3, gbc_separator_3);
		
		lblNormalizedData = new JLabel("Normalized data");
		GridBagConstraints gbc_lblNormalizedData = new GridBagConstraints();
		gbc_lblNormalizedData.gridwidth = 2;
		gbc_lblNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_lblNormalizedData.gridx = 1;
		gbc_lblNormalizedData.gridy = 3;
		contentPane.add(lblNormalizedData, gbc_lblNormalizedData);
		
		txtNormalizedData = new JTextField();
		GridBagConstraints gbc_txtNormalizedData = new GridBagConstraints();
		gbc_txtNormalizedData.fill = GridBagConstraints.BOTH;
		gbc_txtNormalizedData.gridwidth = 4;
		gbc_txtNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_txtNormalizedData.gridx = 4;
		gbc_txtNormalizedData.gridy = 3;
		contentPane.add(txtNormalizedData, gbc_txtNormalizedData);
		txtNormalizedData.setColumns(10);
		
		btnChooseNormalizedData = new JButton("...");
		GridBagConstraints gbc_btnChooseNormalizedData = new GridBagConstraints();
		gbc_btnChooseNormalizedData.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseNormalizedData.gridx = 8;
		gbc_btnChooseNormalizedData.gridy = 3;
		contentPane.add(btnChooseNormalizedData, gbc_btnChooseNormalizedData);
		
		separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.gridwidth = 10;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 4;
		contentPane.add(separator_2, gbc_separator_2);
		
		txtGeneratedMetadataProfile = new JTextField();
		GridBagConstraints gbc_txtGeneratedMetadataProfile = new GridBagConstraints();
		gbc_txtGeneratedMetadataProfile.gridwidth = 4;
		gbc_txtGeneratedMetadataProfile.fill = GridBagConstraints.BOTH;
		gbc_txtGeneratedMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_txtGeneratedMetadataProfile.gridx = 4;
		gbc_txtGeneratedMetadataProfile.gridy = 5;
		contentPane.add(txtGeneratedMetadataProfile, gbc_txtGeneratedMetadataProfile);
		txtGeneratedMetadataProfile.setColumns(10);
		
		btnChooseMetadataProfile = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfile = new GridBagConstraints();
		gbc_btnChooseMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfile.gridx = 8;
		gbc_btnChooseMetadataProfile.gridy = 5;
		contentPane.add(btnChooseMetadataProfile, gbc_btnChooseMetadataProfile);
		
		lblMetadataProfile = new JLabel("Metadata profile");
		GridBagConstraints gbc_lblMetadataProfile = new GridBagConstraints();
		gbc_lblMetadataProfile.gridwidth = 2;
		gbc_lblMetadataProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetadataProfile.gridx = 1;
		gbc_lblMetadataProfile.gridy = 6;
		contentPane.add(lblMetadataProfile, gbc_lblMetadataProfile);
		
		txtMetadataProfileOnlineInformation = new JTextField();
		GridBagConstraints gbc_txtMetadataProfileOnlineInformation = new GridBagConstraints();
		gbc_txtMetadataProfileOnlineInformation.fill = GridBagConstraints.BOTH;
		gbc_txtMetadataProfileOnlineInformation.gridwidth = 4;
		gbc_txtMetadataProfileOnlineInformation.insets = new Insets(0, 0, 5, 5);
		gbc_txtMetadataProfileOnlineInformation.gridx = 4;
		gbc_txtMetadataProfileOnlineInformation.gridy = 6;
		contentPane.add(txtMetadataProfileOnlineInformation, gbc_txtMetadataProfileOnlineInformation);
		txtMetadataProfileOnlineInformation.setColumns(10);
		
		btnChooseMetadataProfileOnline = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfileOnline = new GridBagConstraints();
		gbc_btnChooseMetadataProfileOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfileOnline.gridx = 8;
		gbc_btnChooseMetadataProfileOnline.gridy = 6;
		contentPane.add(btnChooseMetadataProfileOnline, gbc_btnChooseMetadataProfileOnline);
		
		txtMetadataProfileMerged = new JTextField();
		GridBagConstraints gbc_txtMetadataProfileMerged = new GridBagConstraints();
		gbc_txtMetadataProfileMerged.gridwidth = 4;
		gbc_txtMetadataProfileMerged.fill = GridBagConstraints.BOTH;
		gbc_txtMetadataProfileMerged.insets = new Insets(0, 0, 5, 5);
		gbc_txtMetadataProfileMerged.gridx = 4;
		gbc_txtMetadataProfileMerged.gridy = 7;
		contentPane.add(txtMetadataProfileMerged, gbc_txtMetadataProfileMerged);
		txtMetadataProfileMerged.setColumns(10);
		
		btnChooseMetadataProfileMerged = new JButton("...");
		GridBagConstraints gbc_btnChooseMetadataProfileMerged = new GridBagConstraints();
		gbc_btnChooseMetadataProfileMerged.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMetadataProfileMerged.gridx = 8;
		gbc_btnChooseMetadataProfileMerged.gridy = 7;
		contentPane.add(btnChooseMetadataProfileMerged, gbc_btnChooseMetadataProfileMerged);
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 10;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 8;
		contentPane.add(separator_1, gbc_separator_1);
		
		lblStatementMapping = new JLabel("Statement mapping");
		GridBagConstraints gbc_lblStatementMapping = new GridBagConstraints();
		gbc_lblStatementMapping.gridwidth = 2;
		gbc_lblStatementMapping.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatementMapping.gridx = 1;
		gbc_lblStatementMapping.gridy = 9;
		contentPane.add(lblStatementMapping, gbc_lblStatementMapping);
		
		txtStatementMappingFile = new JTextField();
		GridBagConstraints gbc_txtStatementMappingFile = new GridBagConstraints();
		gbc_txtStatementMappingFile.fill = GridBagConstraints.BOTH;
		gbc_txtStatementMappingFile.gridwidth = 4;
		gbc_txtStatementMappingFile.insets = new Insets(0, 0, 5, 5);
		gbc_txtStatementMappingFile.gridx = 4;
		gbc_txtStatementMappingFile.gridy = 9;
		contentPane.add(txtStatementMappingFile, gbc_txtStatementMappingFile);
		txtStatementMappingFile.setColumns(10);
		
		btnChooseStatementMappingFile = new JButton("...");
		GridBagConstraints gbc_btnChooseStatementMappingFile = new GridBagConstraints();
		gbc_btnChooseStatementMappingFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseStatementMappingFile.gridx = 8;
		gbc_btnChooseStatementMappingFile.gridy = 9;
		contentPane.add(btnChooseStatementMappingFile, gbc_btnChooseStatementMappingFile);
		
		txtMergedStatementMappingFile = new JTextField();
		GridBagConstraints gbc_txtMergedStatementMappingFile = new GridBagConstraints();
		gbc_txtMergedStatementMappingFile.fill = GridBagConstraints.BOTH;
		gbc_txtMergedStatementMappingFile.gridwidth = 4;
		gbc_txtMergedStatementMappingFile.insets = new Insets(0, 0, 5, 5);
		gbc_txtMergedStatementMappingFile.gridx = 4;
		gbc_txtMergedStatementMappingFile.gridy = 10;
		contentPane.add(txtMergedStatementMappingFile, gbc_txtMergedStatementMappingFile);
		txtMergedStatementMappingFile.setColumns(10);
		
		btnChooseMergedStatementMappingFile = new JButton("...");
		GridBagConstraints gbc_btnChooseMergedStatementMappingFile = new GridBagConstraints();
		gbc_btnChooseMergedStatementMappingFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseMergedStatementMappingFile.gridx = 8;
		gbc_btnChooseMergedStatementMappingFile.gridy = 10;
		contentPane.add(btnChooseMergedStatementMappingFile, gbc_btnChooseMergedStatementMappingFile);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 10;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 11;
		contentPane.add(separator, gbc_separator);
		
		txtGeneratedItems = new JTextField();
		GridBagConstraints gbc_txtGeneratedItems = new GridBagConstraints();
		gbc_txtGeneratedItems.fill = GridBagConstraints.BOTH;
		gbc_txtGeneratedItems.gridwidth = 4;
		gbc_txtGeneratedItems.insets = new Insets(0, 0, 5, 5);
		gbc_txtGeneratedItems.gridx = 4;
		gbc_txtGeneratedItems.gridy = 12;
		contentPane.add(txtGeneratedItems, gbc_txtGeneratedItems);
		txtGeneratedItems.setColumns(10);
		
		btnChooseItems = new JButton("...");
		GridBagConstraints gbc_btnChooseItems = new GridBagConstraints();
		gbc_btnChooseItems.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItems.gridx = 8;
		gbc_btnChooseItems.gridy = 12;
		contentPane.add(btnChooseItems, gbc_btnChooseItems);
		
		lblGeneratedItems = new JLabel("Items");
		GridBagConstraints gbc_lblGeneratedItems = new GridBagConstraints();
		gbc_lblGeneratedItems.gridwidth = 2;
		gbc_lblGeneratedItems.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneratedItems.gridx = 1;
		gbc_lblGeneratedItems.gridy = 13;
		contentPane.add(lblGeneratedItems, gbc_lblGeneratedItems);
		
		txtItemOnlineInformation = new JTextField();
		GridBagConstraints gbc_txtItemOnlineInformation = new GridBagConstraints();
		gbc_txtItemOnlineInformation.fill = GridBagConstraints.BOTH;
		gbc_txtItemOnlineInformation.gridwidth = 4;
		gbc_txtItemOnlineInformation.insets = new Insets(0, 0, 5, 5);
		gbc_txtItemOnlineInformation.gridx = 4;
		gbc_txtItemOnlineInformation.gridy = 13;
		contentPane.add(txtItemOnlineInformation, gbc_txtItemOnlineInformation);
		txtItemOnlineInformation.setColumns(10);
		
		btnChooseItemsOnline = new JButton("...");
		GridBagConstraints gbc_btnChooseItemsOnline = new GridBagConstraints();
		gbc_btnChooseItemsOnline.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItemsOnline.gridx = 8;
		gbc_btnChooseItemsOnline.gridy = 13;
		contentPane.add(btnChooseItemsOnline, gbc_btnChooseItemsOnline);
		
		txtItemsMerged = new JTextField();
		GridBagConstraints gbc_txtItemsMerged = new GridBagConstraints();
		gbc_txtItemsMerged.fill = GridBagConstraints.BOTH;
		gbc_txtItemsMerged.gridwidth = 4;
		gbc_txtItemsMerged.insets = new Insets(0, 0, 5, 5);
		gbc_txtItemsMerged.gridx = 4;
		gbc_txtItemsMerged.gridy = 14;
		contentPane.add(txtItemsMerged, gbc_txtItemsMerged);
		txtItemsMerged.setColumns(10);
		
		btnChooseItemsMerged = new JButton("...");
		GridBagConstraints gbc_btnChooseItemsMerged = new GridBagConstraints();
		gbc_btnChooseItemsMerged.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseItemsMerged.gridx = 8;
		gbc_btnChooseItemsMerged.gridy = 14;
		contentPane.add(btnChooseItemsMerged, gbc_btnChooseItemsMerged);
		
		JPanel panelButtons = new JPanel();
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridwidth = 7;
		gbc_panelButtons.insets = new Insets(0, 0, 5, 5);
		gbc_panelButtons.gridx = 2;
		gbc_panelButtons.gridy = 15;
		contentPane.add(panelButtons, gbc_panelButtons);
		panelButtons.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblStep_0 = new JLabel("0. Step");
		lblStep_0.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_0, "2, 2");
		
		btnNormalizeForImeji = new JButton("Normalize");
		panelButtons.add(btnNormalizeForImeji, "4, 2, 5, 1, fill, fill");
		
		lblStep_1 = new JLabel("1. Step");
		lblStep_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_1, "2, 4, center, fill");
		
		btnGenerateMetadataProfileAndItems = new JButton("Generate metadata profile and items");
		panelButtons.add(btnGenerateMetadataProfileAndItems, "4, 4, 5, 1");
		
		lblStep_2 = new JLabel("2. Step");
		lblStep_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_2, "2, 6, center, fill");
		
		panelMetadataProfileIssue = new JPanel();
		panelButtons.add(panelMetadataProfileIssue, "4, 6, fill, fill");
		panelMetadataProfileIssue.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblStep_2a = new JLabel("2a. Step");
		panelMetadataProfileIssue.add(lblStep_2a, "2, 2");
		
		btnGenerateMetadataProfile = new JButton("Generate profile");
		btnGenerateMetadataProfile.setEnabled(false);
		panelMetadataProfileIssue.add(btnGenerateMetadataProfile, "4, 2");
		
		lblStep_2b = new JLabel("2b. Step");
		panelMetadataProfileIssue.add(lblStep_2b, "2, 4");
		
		btnGetMetadataProfileOnline = new JButton("Get online metadata profile");
		panelMetadataProfileIssue.add(btnGetMetadataProfileOnline, "4, 4");
		
		lblStep_2c = new JLabel("2c. Step");
		panelMetadataProfileIssue.add(lblStep_2c, "2, 6");
		
		btnMergeMetadataProfile = new JButton("Merge metadata profile");
		panelMetadataProfileIssue.add(btnMergeMetadataProfile, "4, 6");
		
		lblStep_3 = new JLabel("3. Step");
		lblStep_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_3, "6, 6, fill, fill");
		
		panelStatementIssue = new JPanel();
		panelButtons.add(panelStatementIssue, "8, 6, fill, fill");
		panelStatementIssue.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblStep_3a = new JLabel("3a. Step");
		panelStatementIssue.add(lblStep_3a, "2, 2");
		
		btnGetStatementMappingFile = new JButton("Get statement mapping file");
		panelStatementIssue.add(btnGetStatementMappingFile, "4, 2");
		
		lblbStep_3b = new JLabel("3b. Step");
		panelStatementIssue.add(lblbStep_3b, "2, 4");
		
		btnGetMergedStatementMappingFile = new JButton("Get merged statment mapping file");
		panelStatementIssue.add(btnGetMergedStatementMappingFile, "4, 4");
		
		lblStep_4 = new JLabel("4. Step");
		lblStep_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_4, "2, 8, center, fill");
		
		panelItemIssue = new JPanel();
		panelButtons.add(panelItemIssue, "4, 8, fill, fill");
		panelItemIssue.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblStep_4a = new JLabel("4a. Step");
		panelItemIssue.add(lblStep_4a, "2, 2");
		
		btnGenerateItems = new JButton("Generate items");
		btnGenerateItems.setEnabled(false);
		panelItemIssue.add(btnGenerateItems, "4, 2");
		
		lblStep_4b = new JLabel("4b. Step");
		panelItemIssue.add(lblStep_4b, "2, 4");
		
		btnGetItemsOnline = new JButton("Get online items");
		panelItemIssue.add(btnGetItemsOnline, "4, 4");
		
		lblStep_4c = new JLabel("4c. Step");
		panelItemIssue.add(lblStep_4c, "2, 6");
		
		btnMergeItems = new JButton("Merge items with mapping file");
		panelItemIssue.add(btnMergeItems, "4, 6");
		
		lblStep_5 = new JLabel("5. Step");
		lblStep_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblStep_5, "6, 8");
		
		btnConvertToImeji = new JButton("Convert to Imeji");
		btnConvertToImeji.setEnabled(false);
		panelButtons.add(btnConvertToImeji, "8, 8, fill, fill");
		
		
		lblNotification = new JLabel("Notification");
		GridBagConstraints gbc_lblNotification = new GridBagConstraints();
		gbc_lblNotification.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotification.gridx = 1;
		gbc_lblNotification.gridy = 16;
		contentPane.add(lblNotification, gbc_lblNotification);
		
		btnCancel = new JButton();
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 16;
		contentPane.add(btnCancel, gbc_btnCancel);
		btnCancel.setEnabled(false);
		btnCancel.setToolTipText("Click to cancel the process");
		btnCancel.setIcon(new ImageIcon(IngestFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 4;
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 5;
		gbc_progressBar.gridy = 16;
		contentPane.add(progressBar, gbc_progressBar);
		
		
		initOtherVariables();
		initActionListener();
	}
	
	private void initOtherVariables() {
		chooserFile = new JFileChooser();
		
		txtRawData.setDragEnabled(true);
		
		new DropTarget(txtRawData, this);
		
	}

	private void initActionListener() {
		this.btnChooseRawData.addActionListener(this);
		this.btnChooseNormalizedData.addActionListener(this);		
		this.btnChooseMetadataProfile.addActionListener(this);
		this.btnChooseMetadataProfileOnline.addActionListener(this);
		this.btnChooseMetadataProfileMerged.addActionListener(this);
		this.btnChooseStatementMappingFile.addActionListener(this);
		this.btnChooseMergedStatementMappingFile.addActionListener(this);
		this.btnChooseItems.addActionListener(this);
		this.btnChooseItemsOnline.addActionListener(this);
		this.btnChooseItemsMerged.addActionListener(this);
		
		this.btnNormalizeForImeji.addActionListener(this);
		this.btnGenerateMetadataProfileAndItems.addActionListener(this);
		
		this.btnGenerateMetadataProfile.addActionListener(this);
		this.btnGetMetadataProfileOnline.addActionListener(this);
		this.btnMergeMetadataProfile.addActionListener(this);
		
		this.btnGetStatementMappingFile.addActionListener(this);
		this.btnGetMergedStatementMappingFile.addActionListener(this);
		
		this.btnGenerateItems.addActionListener(this);
		this.btnGetItemsOnline.addActionListener(this);
		this.btnMergeItems.addActionListener(this);
		
		this.btnConvertToImeji.addActionListener(this);
		
		this.btnCancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {		
		
		// choosing buttons
		if(e.getSource() == this.btnChooseRawData) {
			this.chooserFile.setDialogTitle("Open raw data file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtRawData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A raw data file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseNormalizedData) {
			this.chooserFile.setDialogTitle("Open normalized data file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtNormalizedData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A normalized data file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseMetadataProfile) {
			this.chooserFile.setDialogTitle("Open meta data profile file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtGeneratedMetadataProfile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A meta data profile file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseMetadataProfileOnline) {
			this.chooserFile.setDialogTitle("Open online meta data profile file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtMetadataProfileOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("An online meta data profile file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseMetadataProfileMerged) {
			this.chooserFile.setDialogTitle("Open a merged meta data profile file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtMetadataProfileMerged.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A merged meta data profile file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseStatementMappingFile) {
			this.chooserFile.setDialogTitle("Open a statement mapping file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtStatementMappingFile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A statement mapping file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseMergedStatementMappingFile) {
			this.chooserFile.setDialogTitle("Open a merged statement mapping file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtStatementMappingFile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A statement mapping file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseItems) {
			this.chooserFile.setDialogTitle("Open an items file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtGeneratedItems.setText(this.chooserFile.getSelectedFile().getAbsolutePath());				
				this.notifyMessage("A self created item file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseItemsOnline) {
			this.chooserFile.setDialogTitle("Open an online items file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtItemOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("An online item file was seleted!");
			}
		}
		
		if(e.getSource() == this.btnChooseItemsMerged) {
			this.chooserFile.setDialogTitle("Open a merged items file");
			if(this.chooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.txtItemsMerged.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				this.notifyMessage("A merged item file was seleted!");
			}
		}
		
		// normalize the raw data into non-special character file
		if(e.getSource() == this.btnNormalizeForImeji) {
			if(this.txtRawData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open raw data file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtRawData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.zrt = new ZuseReplacerTask(this.txtRawData.getText(), progressBar, lblNotification);
			
			if(this.zrt != null) {
				this.notifyMessage("Normalizing...");
				this.zrt.execute();
				this.notifyMessage("Done normalizing!");
			}
			
			try {
				this.txtNormalizedData.setText(this.zrt.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// generate the meta data profile and the items from the raw data
		if(e.getSource() == this.btnGenerateMetadataProfileAndItems) {
			if(this.txtNormalizedData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open normalized raw data file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtNormalizedData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.gimdt = new GenerateItemAndMetadataProfileTask(this.txtNormalizedData.getText(), progressBar, lblNotification);
			
			if(this.gimdt != null) {
				this.notifyMessage("Generating metadata profile and items file...");
				this.gimdt.execute();
				this.notifyMessage("Done generating metadata profile file!");
			}
			
			try {
				String output[] = this.gimdt.get();
				this.txtGeneratedItems.setText(output[0]);
				this.txtGeneratedMetadataProfile.setText(output[1]);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// generate the meta data profile from the raw data
		if(e.getSource() == this.btnGenerateMetadataProfile) {
			if(this.txtNormalizedData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open normalized raw data file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtNormalizedData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.gmdt = new GenerateMetadataProfileTask(this.txtNormalizedData.getText(), progressBar, lblNotification);
			
			if(this.gmdt != null) {
				this.notifyMessage("Generating metadata profile file...");
				this.gmdt.execute();
				this.notifyMessage("Done generating metadata profile file!");
			}
			
			try {
				this.txtGeneratedMetadataProfile.setText(this.gmdt.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// gets the metedata profile from the online repository
		if(e.getSource() == this.btnGetMetadataProfileOnline) {
			if(this.txtMetadataProfileOnlineInformation.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online meta data profile file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMetadataProfileOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
		}
		
		
		// merges the offline meta data profile with the online version
		if(e.getSource() == this.btnMergeMetadataProfile) {
			if(this.txtGeneratedMetadataProfile.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open a generated meta data profile file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtGeneratedMetadataProfile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			if(this.txtMetadataProfileOnlineInformation.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online meta data profile file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMetadataProfileOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.gmmdt = new GenerateZuseMergedMetadataProfileTask(
					this.txtGeneratedMetadataProfile.getText(), this.txtMetadataProfileOnlineInformation.getText(), 
					progressBar, lblNotification);
			
			if(this.gmmdt != null) {
				this.notifyMessage("Generating merged metadata profile file...");
				this.gmmdt.execute();
				this.notifyMessage("Done generating merged metadata profile file!");
			}
			
			try {
				this.txtMetadataProfileMerged.setText(this.gmmdt.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// generate the statement mapping file 
		if(e.getSource() == this.btnGetStatementMappingFile) {
			if(this.txtMetadataProfileMerged.getText().isEmpty()) {
				
				if(this.txtGeneratedMetadataProfile.getText().isEmpty()) {
					this.chooserFile.setDialogTitle("Open a merged meta data profile file");
					if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
						return;
					} else {
						this.txtMetadataProfileMerged.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
					}
				} else {
					this.txtMetadataProfileMerged.setText(this.txtGeneratedMetadataProfile.getText());
				}
				
				
			}
			
			this.gsmf = new GenerateStatementMappingFileTask(this.txtMetadataProfileMerged.getText(), progressBar, lblNotification);
			
			if(this.gsmf != null) {
				this.notifyMessage("Generating merged metadata profile file...");
				this.gsmf.execute();
				this.notifyMessage("Done generating merged metadata profile file!");
			}
			
			try {
				this.txtStatementMappingFile.setText(this.gsmf.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		// get the merged statement file
		if(e.getSource() == this.btnGetMergedStatementMappingFile) {
			if(this.txtMergedStatementMappingFile.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open a merged statement mapping file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtMergedStatementMappingFile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
		}
		
		// generate the items from the normalized raw data file
		if(e.getSource() == this.btnGenerateItems) {
			if(this.txtNormalizedData.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open a normlized file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtNormalizedData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.git = new GenerateZuseItemsTask(this.txtNormalizedData.getText(), progressBar, lblNotification);
			
			if(this.git != null) {
				this.notifyMessage("Generating items...");
				this.git.execute();
				this.notifyMessage("Done generating items!");
			}
			
			try {
				this.txtGeneratedItems.setText(this.git.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		// gets the items data from the online repository
		if(e.getSource() == this.btnGetItemsOnline) {
			if(this.txtItemOnlineInformation.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online item file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtItemOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
		}
		
		
//		// merges the items with the information of a merged statement file
//		if(e.getSource() == this.btnMergeItems) {
//			
//			if(this.txtMetadataProfileMerged.getText().isEmpty()) {
//				this.chooserFile.setDialogTitle("Open a merged meta data profile file");
//				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
//					return;
//				} else {
//					this.txtMetadataProfileMerged.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
//				}
//			}
//			
//			if(this.txtGeneratedItems.getText().isEmpty()) {
//				this.chooserFile.setDialogTitle("Open an items file");
//				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
//					return;
//				} else {
//					this.txtGeneratedItems.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
//				}
//			}
//			
//			if(this.txtMergedStatementMappingFile.getText().isEmpty()) {
//				this.chooserFile.setDialogTitle("Open a merged statement mapping file");
//				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
//					return;
//				} else {
//					this.txtMergedStatementMappingFile.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
//				}
//			}
//			
//			this.giwpt = new GenerateItemsWithStatementProfileTask(
//					this.txtGeneratedItems.getText(),
//					this.txtMetadataProfileMerged.getText(),
//					this.txtMergedStatementMappingFile.getText(),
//					progressBar, lblNotification);
//			
//			if(this.giwpt != null) {
//				this.notifyMessage("Generating items with profile and statement mapping...");
//				this.giwpt.execute();
//				this.notifyMessage("Done generating items!");
//			}
//			
//			try {
//				this.txtItemsMerged.setText(this.git.get());
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (ExecutionException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
		// merges the offline with the online items information
		if(e.getSource() == this.btnMergeItems) {
			
			if(this.txtGeneratedItems.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an offline items file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtGeneratedItems.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			if(this.txtItemOnlineInformation.getText().isEmpty()) {
				this.chooserFile.setDialogTitle("Open an online items file");
				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					this.txtItemOnlineInformation.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
				}
			}
			
			this.mooit = new MergeOfflineAndOnlineItemsTask(
					this.txtGeneratedItems.getText(),
					this.txtItemOnlineInformation.getText(),
					progressBar, lblNotification);
			
			if(this.mooit != null) {
				this.notifyMessage("Generating items with profile and statement mapping...");
				this.mooit.execute();
				this.notifyMessage("Done generating items!");
			}
			
			try {
				this.txtItemsMerged.setText(this.mooit.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
//		if(e.getSource() == this.btnConvertToImeji) {
//			if(this.txtRawData.getText().isEmpty()) {
//				if(this.chooserFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
//					return;
//				} else {
//					this.txtRawData.setText(this.chooserFile.getSelectedFile().getAbsolutePath());
//				}
//			}
//			this.it = new IngestTask(this.txtRawData.getText(), progressBar, lblNotification);
//			
//			if(this.it != null) {
//				this.notifyMessage("Converting...");
//				this.it.execute();
//				this.notifyMessage("Done converting!");
//			}
//		}
//		
//		
//		if(e.getSource() == this.btnCancel) {			
//			if(zrt != null) {
//				this.zrt.stop();
//			}
//			
//			if(it != null) {
//				this.it.stop();
//			}
//		}
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

				this.txtRawData.setText(((File) files.get(0)).getAbsolutePath());

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
