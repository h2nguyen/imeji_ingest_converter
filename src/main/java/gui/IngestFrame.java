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
	private JPanel panelStatus;
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
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
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
	private JSeparator separator_4;
	private JLabel lblStatementMapping;
	private JLabel lblStep_0;
	private JButton btnGenerateMetadataProfileAndItems;
	
	/**
	 * Create the frame.
	 */
	public IngestFrame() {
		System.setProperty("file.encoding", "UTF-8");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{265, 0};
		gbl_contentPane.rowHeights = new int[]{58, 62, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelOpenFile = new JPanel();
		GridBagConstraints gbc_panelOpenFile = new GridBagConstraints();
		gbc_panelOpenFile.insets = new Insets(0, 0, 5, 0);
		gbc_panelOpenFile.fill = GridBagConstraints.BOTH;
		gbc_panelOpenFile.gridx = 0;
		gbc_panelOpenFile.gridy = 0;
		contentPane.add(panelOpenFile, gbc_panelOpenFile);		
		
		lblRawData = new JLabel("Raw data");
		panelOpenFile.add(lblRawData, "2, 2, center, fill");
		
		txtRawData = new JTextField();
		panelOpenFile.add(txtRawData, "4, 2, fill, fill");
		txtRawData.setColumns(10);
		
		btnChooseRawData = new JButton("...");
		panelOpenFile.add(btnChooseRawData, "6, 2, fill, fill");
		
		separator = new JSeparator();
		panelOpenFile.add(separator, "1, 4, 6, 1");
		
		lblNormalizedData = new JLabel("Normalized data");
		panelOpenFile.add(lblNormalizedData, "2, 6, center, fill");
		
		txtNormalizedData = new JTextField();
		panelOpenFile.add(txtNormalizedData, "4, 6, fill, fill");
		txtNormalizedData.setColumns(10);
		
		btnChooseNormalizedData = new JButton("...");
		panelOpenFile.add(btnChooseNormalizedData, "6, 6, fill, fill");
		
		separator_1 = new JSeparator();
		panelOpenFile.add(separator_1, "1, 8, 6, 1");
		
		txtGeneratedMetadataProfile = new JTextField();		
		panelOpenFile.add(txtGeneratedMetadataProfile, "4, 10, fill, fill");
		txtGeneratedMetadataProfile.setColumns(10);
		
		btnChooseMetadataProfile = new JButton("...");
		panelOpenFile.add(btnChooseMetadataProfile, "6, 10, fill, fill");
		
		lblMetadataProfile = new JLabel("Metadata profile");
		panelOpenFile.add(lblMetadataProfile, "2, 10, 1, 5, center, fill");
		
		txtMetadataProfileOnlineInformation = new JTextField();
		panelOpenFile.add(txtMetadataProfileOnlineInformation, "4, 12, fill, fill");
		txtMetadataProfileOnlineInformation.setColumns(10);
		
		btnChooseMetadataProfileOnline = new JButton("...");
		panelOpenFile.add(btnChooseMetadataProfileOnline, "6, 12, fill, fill");
		
		txtMetadataProfileMerged = new JTextField();		
		panelOpenFile.add(txtMetadataProfileMerged, "4, 14, fill, fill");
		txtMetadataProfileMerged.setColumns(10);
		
		btnChooseMetadataProfileMerged = new JButton("...");
		panelOpenFile.add(btnChooseMetadataProfileMerged, "6, 14, fill, fill");
		
		separator_4 = new JSeparator();
		panelOpenFile.add(separator_4, "1, 16, 6, 1");
		
		lblStatementMapping = new JLabel("Statement mapping");
		panelOpenFile.add(lblStatementMapping, "2, 18, 1, 3, right, default");
		
		txtStatementMappingFile = new JTextField();
		panelOpenFile.add(txtStatementMappingFile, "4, 18, fill, fill");
		txtStatementMappingFile.setColumns(10);
		
		btnChooseStatementMappingFile = new JButton("...");
		panelOpenFile.add(btnChooseStatementMappingFile, "6, 18");
		
		txtMergedStatementMappingFile = new JTextField();
		panelOpenFile.add(txtMergedStatementMappingFile, "4, 20, fill, fill");
		txtMergedStatementMappingFile.setColumns(10);
		
		btnChooseMergedStatementMappingFile = new JButton("...");
		panelOpenFile.add(btnChooseMergedStatementMappingFile, "6, 20");
		
		separator_2 = new JSeparator();
		panelOpenFile.add(separator_2, "1, 22, 6, 1");
		
		txtGeneratedItems = new JTextField();
		panelOpenFile.add(txtGeneratedItems, "4, 24, fill, fill");
		txtGeneratedItems.setColumns(10);
		
		btnChooseItems = new JButton("...");
		panelOpenFile.add(btnChooseItems, "6, 24, fill, fill");
		
		lblGeneratedItems = new JLabel("Items");
		panelOpenFile.add(lblGeneratedItems, "2, 24, 1, 5, center, fill");
		
		txtItemOnlineInformation = new JTextField();
		panelOpenFile.add(txtItemOnlineInformation, "4, 26, fill, fill");
		txtItemOnlineInformation.setColumns(10);
		
		btnChooseItemsOnline = new JButton("...");
		panelOpenFile.add(btnChooseItemsOnline, "6, 26, fill, fill");
		
		txtItemsMerged = new JTextField();
		panelOpenFile.add(txtItemsMerged, "4, 28, fill, fill");
		txtItemsMerged.setColumns(10);
		
		btnChooseItemsMerged = new JButton("...");
		panelOpenFile.add(btnChooseItemsMerged, "6, 28, fill, fill");
		
		separator_3 = new JSeparator();
		panelOpenFile.add(separator_3, "1, 30, 6, 1");
		
		JPanel panelButtons = new JPanel();
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.gridx = 0;
		gbc_panelButtons.gridy = 1;
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
		
		panelStatus = new JPanel();
		GridBagConstraints gbc_panelStatus = new GridBagConstraints();
		gbc_panelStatus.fill = GridBagConstraints.BOTH;
		gbc_panelStatus.gridx = 0;
		gbc_panelStatus.gridy = 2;
		contentPane.add(panelStatus, gbc_panelStatus);
		
		
		lblNotification = new JLabel("Notification");
		panelStatus.add(lblNotification, "2, 2, center, fill");
		
		btnCancel = new JButton();
		btnCancel.setEnabled(false);
		btnCancel.setToolTipText("Click to cancel the process");
		btnCancel.setIcon(new ImageIcon(IngestFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		panelStatus.add(btnCancel, "4, 2, center, center");
		
		progressBar = new JProgressBar();
		panelStatus.add(progressBar, "6, 2, fill, fill");
		
		
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
