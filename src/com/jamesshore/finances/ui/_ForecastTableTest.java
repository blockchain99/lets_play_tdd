package com.jamesshore.finances.ui;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.junit.*;
import com.jamesshore.finances.values.*;

public class _ForecastTableTest {

	@Test
	public void tableRowShouldUseStandardColor_WhenJustOneRow() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });
		JTable table = new ForecastTable(tableModel);

		assertEquals("row 0 should have standard background", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
	}

	@Test
	public void tableRowsShouldAlternateColors_WhenThereAreNoColumnHeaders() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		JTable table = new ForecastTable(tableModel);

		assertEquals("row 0 should have standard background", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
		assertEquals("row 1 should have alternate background", ForecastTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 1, 0));
		assertEquals("row 2 should have standard background", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 2, 0));
		assertEquals("row 3 should have alternate background", ForecastTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 3, 0));
	}

	@Test
	public void tableRowsShouldAlternateColors_WhenThereAreColumnHeaders() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] { "Header" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		JTable table = new ForecastTable(tableModel);

		assertEquals("row 0 should have standard background", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
		assertEquals("row 1 should have alternate background", ForecastTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 1, 0));
		assertEquals("row 2 should have standard background", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackground(table, 2, 0));
		assertEquals("row 3 should have alternate background", ForecastTable.ALTERNATE_BACKGROUND_COLOR, getCellBackground(table, 3, 0));
	}

	@Test
	public void tableRowsShouldUseSelectionBackgroundColor_WhenSelected() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });
		JTable table = new ForecastTable(tableModel);

		table.setRowSelectionInterval(0, 0);
		assertEquals("row 0 should have selection background", ForecastTable.SELECTION_BACKGROUND_COLOR, getCellBackground(table, 0, 0));
	}

	@Test
	@SuppressWarnings("serial")
	public void tableShouldHaveSelfRenderableObjectsRenderThemselves() {
		SelfRenderable renderable = new SelfRenderable() {
			public void render(Resources resources, RenderTarget target) {
				target.setText("I rendered myself");
			}
		};
		DefaultTableModel tableModel = new DefaultTableModel(0, 1) {
			public Class<?> getColumnClass(int column) {
				return SelfRenderable.class;
			}
		};
		tableModel.addRow(new SelfRenderable[] { renderable });
		JTable table = new ForecastTable(tableModel);

		assertEquals("I rendered myself", getCellText(table, 0, 0));
	}

	private Color getCellBackground(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component component = table.prepareRenderer(renderer, row, column);
		Color actualColor = component.getBackground();
		return actualColor;
	}

	private String getCellText(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		JLabel label = (JLabel)table.prepareRenderer(renderer, row, column);
		return label.getText();
	}
}
