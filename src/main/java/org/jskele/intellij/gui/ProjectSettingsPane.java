package org.jskele.intellij.gui;

import javax.swing.*;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaCodeFragment.VisibilityChecker.Visibility;
import com.intellij.psi.PsiClass;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.EditorTextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;
import org.jskele.intellij.core.AbstractSettings;

public class ProjectSettingsPane extends JPanel {

	@NotNull
	private final Project project;

	private LabeledComponent<EditorTextFieldWithBrowseButton> daoInterfaceNameField;

	private LabeledComponent<EditorTextField> generateSqlAnnotationNameField;

	public ProjectSettingsPane(@NotNull Project project) {
		super(new VerticalFlowLayout());
		this.project = project;

		final EditorTextFieldWithBrowseButton field = new EditorTextFieldWithBrowseButton(project, true, (declaration, place) -> {
			if (declaration instanceof PsiClass) {
				//final PsiClass aClass = (PsiClass) declaration;
				//if (aClass.isAnnotationType()) {
				return Visibility.VISIBLE;
				//}
			}
			return Visibility.NOT_VISIBLE;
		});

		daoInterfaceNameField = new LabeledComponent<>();
		daoInterfaceNameField.setText("Dao interface");
		daoInterfaceNameField.setComponent(field);
		add(daoInterfaceNameField);

		generateSqlAnnotationNameField = new LabeledComponent<>();
		generateSqlAnnotationNameField.setText("Generate sql annotation");
		generateSqlAnnotationNameField.setComponent(new EditorTextField());
		add(generateSqlAnnotationNameField);
	}

	void reset(@NotNull final AbstractSettings settings) {
		daoInterfaceNameField.getComponent().setText(settings.daoInterfaceName);
		generateSqlAnnotationNameField.getComponent().setText(settings.generateSqlAnnotationName);
	}

	boolean isModified(@NotNull final AbstractSettings settings) {
		return !StringUtil.equals(settings.daoInterfaceName, daoInterfaceNameField.getComponent().getText()) ||
				!StringUtil.equals(settings.generateSqlAnnotationName, generateSqlAnnotationNameField.getComponent().getText());
	}

	public void apply(AbstractSettings settings) {
		settings.daoInterfaceName = daoInterfaceNameField.getComponent().getText();
		settings.generateSqlAnnotationName = generateSqlAnnotationNameField.getComponent().getText();
	}
}
